package com.greenmarket.service;

import java.util.List;

import com.greenmarket.entity.Category;

public interface ICategoryService {

	public List<Category> getAll();
	
	public Category getByid(int id);
	
	public void insert(Category objetc);
	
	public void update(Category objetc);
	
	public void delete(int id);
}
