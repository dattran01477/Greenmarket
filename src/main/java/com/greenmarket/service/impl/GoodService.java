package com.greenmarket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenmarket.dao.IGoodDao;
import com.greenmarket.entity.Good;
import com.greenmarket.service.IGoodService;

@Service
@Transactional
public class GoodService implements IGoodService {
	@Autowired
	private IGoodDao _goodDao;
	public GoodService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Good> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Good getByid(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Good objetc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Good objetc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
