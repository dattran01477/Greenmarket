package com.greenmarket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenmarket.dao.IEvaluationDao;
import com.greenmarket.entity.Evaluation;
import com.greenmarket.service.IEvaluationService;

@Service
@Transactional
public class EvaluationService implements IEvaluationService {

	@Autowired
	private IEvaluationDao _evaluationDao;
	
	public EvaluationService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Evaluation> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Evaluation getByid(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Evaluation objetc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Evaluation objetc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
