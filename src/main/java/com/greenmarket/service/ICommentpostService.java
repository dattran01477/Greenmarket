package com.greenmarket.service;

import java.util.List;

import com.greenmarket.entity.Commentpost;;

public interface ICommentpostService {
	public List<Commentpost> getAll();
	
	public Commentpost getByid(int id);
	
	public void insert(Commentpost objetc);
	
	public void update(Commentpost objetc);
	
	public void delete(int id);
}
