package com.greenmarket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenmarket.dao.IRoleuserDao;
import com.greenmarket.entity.Roleuser;
import com.greenmarket.service.IRoleuserService;

@Service
@Transactional
public class RoleuserService implements IRoleuserService {
	@Autowired
	private IRoleuserDao _roleuserDao;
	
	public RoleuserService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Roleuser> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Roleuser getByid(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Roleuser objetc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Roleuser objetc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
