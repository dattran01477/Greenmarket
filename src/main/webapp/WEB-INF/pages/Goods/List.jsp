<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="u"%>
<div class="alert alert-success img-rounded" style="margin:50px"> <h2 class="text-center"><b>Danh sách nông sản </b> </h2> </div>
<div class="row" style="margin:50px">
	<c:forEach items="${lstGoods}" var="good">
		<a class="col-xs-12 col-sm-6 col-md-2" style="padding: 10px" href="${pageContext.request.contextPath}/goods/get/${good.id}">
            	<div class="img-rounded">
            		<img src="${pageContext.request.contextPath}/static/assets/paper_img/traicheri.jpg" alt="Rounded Image" class="img-rounded img-responsive" >
            		<div class="img-details">
                      	<div class="author">
                            <img src="${pageContext.request.contextPath}/static/assets/paper_img/chet_faker_1.jpg" alt="Circle Image" class="img-circle img-no-padding img-responsive">
                        </div>
                                <p><b>${good.product}</b> </p>
                                <p style="color:#0000ff"> ${good.price} VND - ${good.createdtime }</p>
                 		</div>
               		</div>
        	</a>
	</c:forEach>
	<div class="row">
	<c:forEach items="${lstGoods}" var="good">
		<a class="col-xs-12 col-sm-6 col-md-2" style="padding: 10px" href="${pageContext.request.contextPath}/goods/get/${good.id}">
            	<div class="img-rounded">
            		<img src="${pageContext.request.contextPath}/static/assets/paper_img/traicheri.jpg" alt="Rounded Image" class="img-rounded img-responsive" >
            		<div class="img-details">
                      	<div class="author">
                            <img src="${pageContext.request.contextPath}/static/assets/paper_img/chet_faker_1.jpg" alt="Circle Image" class="img-circle img-no-padding img-responsive">
                        </div>
                                   <p><b> ${good.product} </b> </p>
                                <p style="color:#0000ff"> ${good.price} VND - ${good.createdtime }</p>
                 		</div>
               		</div>
        	</a>
	</c:forEach>
 </div>
        	
</div>