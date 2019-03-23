package com.greenmarket.dao.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.greenmarket.dao.IEvaluationDao;
import com.greenmarket.entity.Evaluation;

@Repository
@Transactional
public class EvaluationDaoImpl extends AbstractDao<Integer, Evaluation> implements IEvaluationDao {

}
