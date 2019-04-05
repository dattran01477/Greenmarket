package com.greenmarket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenmarket.dao.IFarmDao;
import com.greenmarket.entity.Farm;
import com.greenmarket.service.IFarmService;

@Service
@Transactional
public class FarmService implements IFarmService{

	@Autowired
	private IFarmDao _farmDao;
	public FarmService() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public List<Farm> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Farm getByid(int id) {
		return _farmDao.getByid(id);
	}
	@Override
	public void insert(Farm objetc) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(Farm objetc) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Farm> getAllByUserId(int userId) {
		return _farmDao.getAllByUserId(userId);

	}

	
}
