package com.greenmarket.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenmarket.dao.ICategoryDao;
import com.greenmarket.entity.Category;
import com.greenmarket.service.ICategoryService;

@Service
@Transactional
public class CategoryService implements ICategoryService {
	@Autowired
	private ICategoryDao _categoryDao;

	public CategoryService() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category getByid(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Category objetc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Category objetc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	
}
