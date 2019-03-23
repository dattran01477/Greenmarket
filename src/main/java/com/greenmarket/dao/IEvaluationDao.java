package com.greenmarket.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.greenmarket.entity.Evaluation;


public interface IEvaluationDao extends IGenericDao<Integer, Evaluation> {

}
