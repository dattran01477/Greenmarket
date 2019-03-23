package com.greenmarket.service;

import java.util.List;

import com.greenmarket.entity.Evaluation;;

public interface IEvaluationService {
	public List<Evaluation> getAll();
	
	public Evaluation getByid(int id);
	
	public void insert(Evaluation objetc);
	
	public void update(Evaluation objetc);
	
	public void delete(int id);
}
