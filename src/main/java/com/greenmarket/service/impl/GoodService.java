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
	private IGoodDao goodDao;

	@Override
	public void delete(int id) {
		goodDao.delete(id);
	}
	@Override
	public Good getByid(int id) {
		Good goods= goodDao.getByid(id);
		return goods;
	}
	@Override
	public void insert(Good objetc) {
		goodDao.insert(objetc);
		
	}
	@Override
	public void update(Good objetc) {
		goodDao.update(objetc);		
	}
	@Override
	public List<Good> getAll() {
		return goodDao.getAll();
	}
	@Override
	public List<Good> getAllByUser(int id) {
		return goodDao.getAllGoodByUser(id);
		
	}
	
	

}
