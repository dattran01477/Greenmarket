package vn.zalopay.fabackoffice.service.ticket.zas.impl

import io.micrometer.core.instrument.MeterRegistry
import org.apache.commons.collections4.CollectionUtils
import org.apache.commons.compress.utils.Lists
import org.apache.commons.lang3.exception.ExceptionUtils
import org.apache.logging.log4j.LogManager
import org.slf4j.helpers.MessageFormatter
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import vn.zalopay.fabackoffice.dto.reponse.TriggerTicketResponse
import vn.zalopay.fabackoffice.enumeration.ClientId
import vn.zalopay.fabackoffice.enumeration.ReferenceType
import vn.zalopay.fabackoffice.enumeration.ReturnCode
import vn.zalopay.fabackoffice.enumeration.storage.TransactionStatus
import vn.zalopay.fabackoffice.event.SimpleTicketStatusEvent
import vn.zalopay.fabackoffice.event.publisher.TicketEventPublisher
import vn.zalopay.fabackoffice.exception.DefaultException
import vn.zalopay.fabackoffice.model.Entry
import vn.zalopay.fabackoffice.model.Error
import vn.zalopay.fabackoffice.model.TransResult
import vn.zalopay.fabackoffice.service.interop.ZasClient
import vn.zalopay.fabackoffice.service.ticket.common.Queue
import vn.zalopay.fabackoffice.service.ticket.common.Queue.Companion.newBuilder
import vn.zalopay.fabackoffice.service.ticket.common.dto.ReverseInfo
import vn.zalopay.fabackoffice.service.ticket.common.dto.TicketInfo
import vn.zalopay.fabackoffice.service.ticket.common.dto.TicketStatus
import vn.zalopay.fabackoffice.service.ticket.common.dto.TicketType
import vn.zalopay.fabackoffice.service.ticket.common.model.Ticket
import vn.zalopay.fabackoffice.service.ticket.zas.*
import vn.zalopay.fabackoffice.service.ticket.zas.model.TicketEntry
import vn.zalopay.fabackoffice.service.ticket.zas.model.Transaction
import vn.zalopay.fabackoffice.utils.DateTimeUtils
import vn.zalopay.fabackoffice.utils.ProtoJsonUtil
import vn.zalopay.zas.mapping.protobuf.BankAccountingQueryRequest
import vn.zalopay.zas.protobuf.*
import vn.zalopay.zas.shared.protobuf.Code
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong
import java.util.function.Consumer
import java.util.stream.Collectors

/** Created by ToanHDA at Aug 27, 2020 */
// TODO: don't extend ZasService here. Use composition (DI) instead
@Component
class TicketImporterImpl(
    private val zasClient: ZasClient,
    private val storage: TicketStorage,
    private val validator: TicketValidator,
    private val watcher: TicketWatcher,
    private val registry: MeterRegistry,
    private val ticketEventPublisher: TicketEventPublisher
) : TicketImporter {
  private val LOGGER = LogManager.getLogger(TicketImporterImpl::class.java)
  private val INTERNAL_ERROR_CODE = -1
  private val INTERNAL_ERROR_MESSAGE = "FABO's Exception. Context: "
  private val transactionIdGenerator = TransactionIdGenerator.getInstance()
  private val ONE_MINUTE: Long = 60000
  private val FABO_FLOW_ID = "0"
  private val BANK_FLOW_ID  = "99"

  val taskQueue: Queue<Ticket> = newBuilder(this::onTicket).build()

  // TODO: We may need to add tracking here
  @Throws(Exception::class)
  private fun onTicket(ticket: Ticket, ticketQueue: Queue<Ticket>) {
    LOGGER.info(
        "Submitting ticket id{}, ticketId={} with totalRows={} is in processing",
        ticket.getId(),
        ticket.getTicketId(),
        ticket.getTotalRows().toString() + "")

    val context: TicketContext = createContext(ticket) ?: return
    handleTicket(ticket, context)
    onTicketHandled(ticket, context)
  }

  private fun createContext(ticket: Ticket): TicketContext? {
    var originTicket: Ticket? = null
    var ticketToReverse: TicketInfo? = null

    // TODO: move this logic into TicketValidator
    if (ticket.isReverseType) {
      if (ticket.isEmptyReverseHistory) {
        rejectTicket(ticket, ReturnCode.TICKET_WITH_INVALID_TRANSACTION)
        return null
      }

      val originTicketId = ticket.getHistory().findFirstOne().id
      originTicket = storage.getTicketById(originTicketId)
      if (originTicket == null) {
        rejectTicket(ticket, ReturnCode.TICKET_HAS_BEEN_REVERSED_FROM_UNKNOWN)
        return null
      }

      ticketToReverse = originTicket.getHistory().findOneByTicketId(ticket.getTicketId())
      if (ticketToReverse == null) {
        rejectTicket(ticket, ReturnCode.TICKET_WITH_INVALID_TRANSACTION)
        return null
      }
    }
    return TicketContext(ReverseInfo(originTicket, ticketToReverse))
  }

  private fun rejectTicket(ticket: Ticket, reason: ReturnCode) {
    ticket.setStatus(TicketStatus.FAILED)
    ticket.setRejectReason(reason.message)
    storage.saveTicket(ticket)
  }

  private fun onTicketHandled(ticket: Ticket, context: TicketContext) {

    ticket.setErrors(context.errors.get())
    val status =
        if (context.errors.get() != ticket.getTotalRows()) TicketStatus.UPDATING
        else TicketStatus.FAILED
    ticket.setStatus(status)
    val lastRecordedTime = context.lastRecordedTime.get()
    if (lastRecordedTime != 0L) ticket.setRecordedTimestamp(lastRecordedTime)

    LOGGER.info(
        ">> Ticket {} is handled : status={} errors={}, totalRows={}",
        ticket.ticketId,
        status,
        context.errors.get(),
        ticket.getTotalRows())

    if (ticket.isReverseType) {
      with(context.reverseInfo) {
        if (this.originTicket == null) {
          return
        }
        this.originTicket.getHistory().replaceOneByTicketId(ticket.id, this.ticketToReverse, status)
        storage.saveTicket(this.originTicket)
      }
    }
    ticket.updatingAt = DateTimeUtils.getCurrentMillisecond()
    storage.saveTicket(ticket)
    if (status != TicketStatus.FAILED) {
      Thread.sleep(ONE_MINUTE)
      zasClient.reportCompute(ticket.accountingTime, lastRecordedTime)
      watcher.watch(ticket)
    }
  }

  private fun handleTicket(ticket: Ticket, context: TicketContext) {
    val refSourceTransactions: MutableList<Transaction> = ArrayList()
    val ledgers = zasClient.ledgers
    var lastRefSourceTransId = ""
    val id = ticket.getId()
    val totalRows = ticket.getTotalRows()
    val limitBatch = 10
    val numBatch = totalRows / limitBatch
    val ticketStatusEvent = SimpleTicketStatusEvent(this, ticket, ArrayList<Transaction>(), ticket.status, ticket.id)
    for (n in 0..numBatch) {
      val transactions = storage.getTransactions(id, PageRequest.of(n, limitBatch))
      validateTransactions(ticket, transactions, context,ticket.type, ledgers)
      ticketStatusEvent.data.addAll(transactions)
      for (transaction in transactions) {
        when {
          transaction.referenceType != ReferenceType.SOURCE_TRANS_ID -> {
            onTransaction(ticket, transaction, context)
            storage.saveTransaction(transaction, ticket.id)
            lastRefSourceTransId = ""
          }
          transaction.key != lastRefSourceTransId -> {
            onTransactions(ticket, refSourceTransactions, context)
            refSourceTransactions.clear()
            lastRefSourceTransId = transaction.key
            refSourceTransactions.add(transaction)
          }
          else -> refSourceTransactions.add(transaction)
        }
      }
    }
    ticketEventPublisher.follow(ticketStatusEvent)
    onTransactions(ticket, refSourceTransactions, context)
  }

  private fun validateTransactions(
    ticket: Ticket,
    transactions: List<Transaction>,
    context: TicketContext,
    ticketType: TicketType,
    ledgers: MutableMap<String, Ledger>
  ) {
    updateTicketEntries(transactions, ticketType, context.accounts)
    for (transaction in transactions) {
      val validation = validator.validateForImport(transaction, ledgers)
      // TODO: verify !! (if null)
      // TODO: check if we can simply invoke `transaction.status = FAILED`
      if (validation == ReturnCode.SUCCESSFUL && ticket.isReverseType) {
        context.reverseInfo.ticketToReverse.matchedTransInfo[transaction.id]!!.status =
            TransactionStatus.FAILED
      }
    }
  }

  private fun updateTicketEntries(
      transactions: List<Transaction>,
      ticketType: TicketType,
      accounts: HashMap<String, Account>
  ){
    for (transaction in transactions) {
      updateTransaction(ticketType, transaction)
      val entries = setEntries(transaction, accounts)
      transaction.entries = entries
    }
  }

  private fun updateTransaction(ticketType: TicketType, transaction: Transaction) {
    if (ticketType == TicketType.REVERSE) transaction.doReverse()
  }

  private fun validateAndUpdateReverseEntryStatus(
      ticket: Ticket,
      transaction: Transaction,
      context: TicketContext
  ) {
    if (context.reverseInfo.ticketToReverse == null) return
    val originTransId = getOriginTransId(context.reverseInfo.ticketToReverse, transaction.id)

    if (StringUtils.isEmpty(originTransId)) {
      updateFailedTicket(ticket, transaction.id, ReturnCode.TRANSACTION_ENTRY_WAS_MISSED)
      throw DefaultException(
          ReturnCode.TRANSACTION_ENTRY_WAS_MISSED.toString() + " - rowId: " + transaction.rowId)
    }

    with(context.reverseInfo.originTicket.getHistory()) {
      val entryWasReversedBefore = this.successEntries.containsKey(originTransId)
      if (entryWasReversedBefore) {
        updateFailedTicket(ticket, transaction.id, ReturnCode.TRANSACTION_ENTRY_WAS_REVERSED)
        throw DefaultException(
            ReturnCode.TRANSACTION_ENTRY_WAS_REVERSED.toString() + ": " + originTransId)
      }
      this.addSuccessEntry(originTransId, ticket.getTicketId())
    }
  }

  private fun updateFailedTicket(ticket: Ticket, transId: String, transMessage: ReturnCode) {
    ticket.getHistory().failEntries[transId] = transMessage.message
    ticket.setStatus(TicketStatus.FAILED)
    storage.saveTicket(ticket)
  }

  private fun getOriginTransId(reverseTicket: TicketInfo, refTransId: String): String? {
    val isTransExistedInLog = reverseTicket.matchedTransInfo.containsKey(refTransId)
    return if (!isTransExistedInLog) null else reverseTicket.matchedTransInfo[refTransId]!!.transId
  }

  data class TicketContext(val reverseInfo: ReverseInfo) {
    val accounts = HashMap<String, Account>()
    val errors = AtomicInteger(0)

    // Last Transaction Recorded Time
    // This's extracted from Zas response
    val lastRecordedTime = AtomicLong(0)

    var lastError: Throwable? = null
    var zasIdsMaster: MutableList<String> = ArrayList()
  }

  private fun onTransactions(
      ticket: Ticket,
      transactions: List<Transaction>,
      context: TicketContext
  ) {
    if (transactions.isEmpty()) {
      return
    }

    // Group to 1 transaction and update result
    val transaction = groupTransactions(transactions)
    onTransaction(ticket, transaction, context)
    // Update result
    transactions.forEach(Consumer { trans: Transaction -> trans.copyResult(transaction) })
    storage.saveTransactions(transactions, ticket.id)
  }

  private fun onTransaction(ticket: Ticket, transaction: Transaction, context: TicketContext) {
    // check if the transaction is already processed
    val transStatus = transaction.transStatus
    val numOfEntries = transaction.entries.size
    if (transStatus == TransStatus.TRANS_STATUS_SUCCESSFUL) {
      return
    }
    if (transStatus == TransStatus.TRANS_STATUS_FAILED) {
      context.errors.getAndAdd(numOfEntries)
      return
    }
    try {
      validateAndUpdateReverseEntryStatus(ticket, transaction, context)
      transRecord(ticket, transaction, context)
      return
    } catch (e: Exception) {
      context.lastError = e
      onTransactionFailed(ticket, transaction, context)
    }
    context.errors.getAndAdd(numOfEntries)
  }

  private fun groupTransactions(transactions: List<Transaction>): Transaction {
    val transaction = transactions[0].shallowCopy()
    val entries: MutableList<TicketEntry> = ArrayList()
    transactions.forEach(Consumer { trans: Transaction -> entries.addAll(trans.entries) })
    transaction.entries = entries
    return transaction
  }

  private fun onTransactionFailed(
      ticket: Ticket,
      transaction: Transaction,
      context: TicketContext
  ) {

    LOGGER.warn(
        MessageFormatter.format(
                "Ticket {} has an error while posting to ZAS.\nTrace: {}",
                ticket.ticketId,
                ExceptionUtils.getStackTrace(context.lastError))
            .message)

    if (transaction.transResult == null) {
      val transResult = TransResult()
      transResult.error =
          Error.builder()
              .codeNumber(INTERNAL_ERROR_CODE)
              .message(INTERNAL_ERROR_MESSAGE + ExceptionUtils.getMessage(context.lastError))
              .build()
      transaction.transResult = transResult
    }

    transaction.transStatus = TransStatus.TRANS_STATUS_FAILED

    if (ticket.isReverseType) {
      val addedTransId = getOriginTransId(context.reverseInfo.ticketToReverse, transaction.id)
      context
          .reverseInfo
          .originTicket
          .getHistory()
          .removeSuccessEntryById(addedTransId, ticket.getTicketId())
    }
  }

  @Throws(Exception::class)
  override fun handle(ticket: Ticket) {
    taskQueue.add(ticket)
  }

  override fun triggerPendingTicket(ticketObjIds: MutableList<String>): TriggerTicketResponse {
    val pendingTickets: MutableList<Ticket> = if (ticketObjIds.isEmpty()) {
      storage.getTicketsByStatusIn(listOf(TicketStatus.PROCESSING, TicketStatus.APPROVED))
    } else {
      storage.getTicketsByIdIn(ticketObjIds)
    }
    if (CollectionUtils.isEmpty(pendingTickets)) {
      return TriggerTicketResponse(
              "Pending ticket not found",
              Collections.emptyList(),
              Collections.emptyList())
    }

    val addQueueSuccess = ArrayList<String>()
    val addQueueFail = ArrayList<String>()
    var message = ""
    pendingTickets.forEach(
        Consumer { ticket: Ticket ->
          try {
            if (ticket.getStatus() == TicketStatus.PROCESSING || ticket.getStatus() == TicketStatus.APPROVED) {
              taskQueue.add(ticket)
              addQueueSuccess.add(ticket.getTicketId())
            } else {
              LOGGER.warn("#triggerPendingTicket# ticket unmatched status with id = {}", ticket.getTicketId())
              addQueueFail.add(ticket.getTicketId())
              message += "Ticket id: " + ticket.getTicketId() + " unmatched status; "
            }
          } catch (e: Exception) {
            LOGGER.error("Exception when processing previous ticket with id = {}", ticket.getTicketId())
            message += "Exception when processing ticket with id: " + ticket.getTicketId() + "; "
          }
        })
    return TriggerTicketResponse(message, addQueueSuccess, addQueueFail)
  }

  private fun setEntries(
      transaction: Transaction,
      accounts: HashMap<String, Account>
  ): List<TicketEntry> {
    // Import: 1 ticketDetail have 1 entry
    val ledgers = zasClient.ledgers
    val entries = transaction.entries

    try {
      for (ticketEntry: TicketEntry in entries) {
        updateEntry(ticketEntry, ledgers, accounts, true)
        updateEntry(ticketEntry, ledgers, accounts, false)
      }
    } catch (e: Exception) {
      val transResult = TransResult()
      transResult.error = Error.builder().message(e.message).build()
      transaction.transStatus = TransStatus.TRANS_STATUS_FAILED
      transaction.transResult = transResult
    }

    return entries
  }

  @Throws(Exception::class)
  private fun updateEntry(
      entry: TicketEntry,
      ledgers: Map<String, Ledger>,
      accountsMap: HashMap<String, Account>,
      isDebit: Boolean
  ) {
    val zasId = if (isDebit) entry.debitZasId else entry.creditZasId
    if (accountsMap.containsKey(zasId)) {
      addValueEntry(entry, accountsMap[zasId], ledgers, isDebit)
      return
    }
    if (!isParsableAsLong(zasId)) {
      throw Exception(ReturnCode.ZAS_ID_MUST_BE_LONG_VALUE.message)
    }
    val accountQueryRequest =
        AccountQueryRequest.newBuilder()
            .setId(zasId.toLong())
            .build()
    val accountQueryResponse = zasClient.queryAccount(accountQueryRequest)

    if (isResponseFailed(accountQueryResponse)) {
      return
    }
    val accounts = accountQueryResponse.data.accountsList

    if (Objects.isNull(accounts) || accounts.size == 0) {
      return
    }
    val account = accounts[0]
    accountsMap[account.id.toString()] = account
    addValueEntry(entry, account, ledgers, isDebit)
  }

  private fun isParsableAsLong(s: String): Boolean {
    return try {
      s.toLong()
      true
    } catch (numberFormatException: NumberFormatException) {
      false
    }
  }

  private fun isResponseFailed(accountQueryResponse: AccountQueryResponse): Boolean {
    return (Objects.isNull(accountQueryResponse) || accountQueryResponse.error.code != Code.SUCCESS)
  }

  private fun addValueEntry(
      entry: TicketEntry,
      account: Account?,
      ledgers: Map<String, Ledger>,
      isDebit: Boolean
  ) {
    if (isDebit) {
      entry.debitUid = account!!.uid
      entry.debitAccountingCode = account.ledgerAccountingCode.toLong()
      val ledger = ledgers[account.ledgerAccountingCode]
      if (!Objects.isNull(ledger)) {
        entry.debitAccountingName = ledger!!.title
        val tag = ledger.getTags(0)
        entry.debitObject = tag
        if (tag == "BANK") {
          mappingBankAccount(entry, account, isDebit)
        }
      }
      return
    }
    entry.creditUid = account!!.uid
    entry.creditAccountingCode = account.ledgerAccountingCode.toLong()
    val ledger = ledgers[account.ledgerAccountingCode]
    if (!Objects.isNull(ledger)) {
      entry.creditAccountingName = ledger!!.title
      // 1 ledger ~ 1 tag
      val tag = ledger.getTags(0)
      entry.creditObject = tag
      if (tag == "BANK") {
        mappingBankAccount(entry, account, isDebit)
      }
    }
  }

  private fun mappingBankAccount(entry: TicketEntry, account: Account?, isDebit: Boolean) {
    val bankAccountingQueryRequest =
        BankAccountingQueryRequest.newBuilder().setAccountingId(account!!.id).build()
    val bankAccountingQueryResponse = zasClient.getBankAccounting(bankAccountingQueryRequest)
    if (!Objects.isNull(bankAccountingQueryResponse) &&
        bankAccountingQueryResponse.error.code == Code.SUCCESS &&
        bankAccountingQueryResponse.data.listBankAccountingList.isNotEmpty()) {
      val bankAccounting = bankAccountingQueryResponse.data.getListBankAccounting(0)
      val bankAccountCode = bankAccounting.bankAccountCode
      setExtraBank(entry, bankAccountCode, isDebit)
    }
  }

  private fun setExtraBank(entry: TicketEntry, bankAccountCode: String, isDebit: Boolean) {
    val extra = entry.extraObject
    if (isDebit) {
      extra.debitBankAccount = bankAccountCode
    } else {
      extra.creditBankAccount = bankAccountCode
    }
    entry.setExtra(extra)
  }

  class TransactionException : Exception()

  @Throws(TransactionException::class)
  private fun transRecord(ticket: Ticket, transaction: Transaction, context: TicketContext) {
    val response: TransRecordResponse = tryTransRecord(ticket, transaction, context)
    val transResult = getTransResult(response)
    transaction.transStatus = transResult.status
    transaction.transResult = transResult

    if (!this.isSuccessful(response)) {
      throw TransactionException()
    }
  }

  private fun getTransResult(response: TransRecordResponse?): TransResult {
    val codeValue = -1
    val message = "Internal error. Something wrong with our importer"

    val transResult = TransResult()
    if (response == null) {
      return getTransResult(codeValue, message, TransStatus.TRANS_STATUS_FAILED)
    }

    if (!isSuccessful(response)) {
      return getTransResult(
          response.error.codeValue, response.error.message, TransStatus.TRANS_STATUS_FAILED)
    }

    val transaction = response.data.transaction
    transResult.status = transaction.status
    transResult.voucherId = transaction.voucherId
    transResult.globalTransId = transaction.request.environment.globalTransId
    transResult.transId = transaction.transId
    transResult.recordedTime = transaction.recordedTime
    transResult.rawData = ProtoJsonUtil.toJsonString(response)

    return transResult
  }

  private fun getTransResult(codeValue: Int, message: String, status: TransStatus): TransResult {
    val error = Error.builder().codeNumber(codeValue).message(message).build()
    val transResult = TransResult()
    transResult.error = error
    transResult.status = status
    return transResult
  }

  private fun getEntries(
      transactions: List<Transaction>,
      extra: HashMap<String, String>
  ): List<Entry> {
    val entries: MutableList<Entry> = ArrayList()
    for (transaction in transactions) {
      entries.addAll(buildEntries(transaction.entries, extra, transaction))
    }
    return entries
  }

  private fun buildEntries(
      ticketEntries: List<TicketEntry>,
      extra: HashMap<String, String>,
      transaction: Transaction
  ): List<Entry> {
    val entries: MutableList<Entry> = Lists.newArrayList()
    for (entry in ticketEntries) {
      entries.add(buildEntry(entry, extra, transaction))
    }
    return entries
  }

  private fun buildEntry(
      ticketEntry: TicketEntry,
      extra: HashMap<String, String>,
      transaction: Transaction
  ): Entry {
    return Entry.builder()
        .debitAccount(ticketEntry.debitZasId)
        .creditAccount(ticketEntry.creditZasId)
        .amount(ticketEntry.amount)
        .currencyCode(ticketEntry.currency)
        .description(if (transaction.description != null) transaction.description else "")
        .extra(extra)
        .build()
  }

  private fun buildEntry(entry: Entry): vn.zalopay.zas.protobuf.Entry {
    return vn.zalopay.zas.protobuf.Entry.newBuilder()
        .setType(EntryType.DEBIT_CREDIT)
        .setSourceId(java.lang.Long.valueOf(entry.debitAccount))
        .setDestinationId(java.lang.Long.valueOf(entry.creditAccount))
        .setAmount(entry.amount)
        .setCurrencyCode(entry.currencyCode)
        .putAllExtra(entry.extra)
        .putExtra("description", entry.description)
        .build()
  }

  private fun tryTransRecord(
      ticket: Ticket,
      transaction: Transaction,
      context: TicketContext
  ): TransRecordResponse {
    val extra = buildExtra(ticket, transaction)
    val entries = getEntries(listOf(transaction), extra)
    val transEnvironment =
        TransEnvironment.newBuilder()
            .setAccountingTime(transaction.accountingTime)
            .setGlobalTransId(transaction.globalTransId)
            .setFlowId(
                if (ClientId.BANK_RECONCILE.equalByName(ticket.createdUser))
                  BANK_FLOW_ID else FABO_FLOW_ID) // Define by Accounting PO
            .build()
    val request =
        TransRecordRequest.newBuilder()
            .setTransId(transaction.sourceTransId)
            .addAllEntries(
                entries
                    .stream()
                    .map { entry: Entry -> this.buildEntry(entry) }
                    .collect(Collectors.toList()))
            .setEnvironment(transEnvironment)
            .build()
    val response = zasClient.transRecord(request)

    if (isSuccessful(response)) {
      context.lastRecordedTime.set(response.data.transaction.recordedTime)
    }

    return response
  }

  private fun buildExtra(ticket: Ticket, transaction: Transaction): HashMap<String, String> {
    val extra = HashMap<String, String>()
    extra["preparer"] = ticket.getCreatedUser()
    extra["approver"] = ticket.getApprovedUser()
    extra["ticketId"] = ticket.getTicketId()
    if (!StringUtils.isEmpty(transaction.refSourceTransId)) {
      extra["refSourceTransId"] = transaction.refSourceTransId
    }
    if (!StringUtils.isEmpty(transaction.refTransId)) {
      extra["refTransId"] = transaction.refTransId
    }
    if (!StringUtils.isEmpty(transaction.refGlobalTransId)) {
      extra["refGlobalTransId"] = transaction.refGlobalTransId
    }
    return extra
  }

  private fun isSuccessful(response: TransRecordResponse?): Boolean {
    return response != null && response.error.code == Code.SUCCESS
  }

  init {
    taskQueue.start()
//    fetchPendingTicket()
  }
}
