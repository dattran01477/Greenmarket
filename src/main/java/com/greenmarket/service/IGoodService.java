package com.greenmarket.service;

import java.util.List;

import com.greenmarket.entity.Good;

public interface IGoodService {
	public List<Good> getAll();
	
	public Good getByid(int id);
	
	public void insert(Good objetc);
	
	public void update(Good objetc);
	
	public void delete(int id);
	
	public List<Good> getAllByUser(int id);
}
