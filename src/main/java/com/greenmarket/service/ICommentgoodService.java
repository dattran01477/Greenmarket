package com.greenmarket.service;

import java.util.List;

import com.greenmarket.entity.Commentgood;;

public interface ICommentgoodService {
	public List<Commentgood> getAll();
	
	public Commentgood getByid(int id);
	
	public void insert(Commentgood objetc);
	
	public void update(Commentgood objetc);
	
	public void delete(int id);
}
