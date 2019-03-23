package com.greenmarket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenmarket.dao.IUserDao;
import com.greenmarket.entity.User;
import com.greenmarket.service.IUserService;

@Service
@Transactional
public class UserService implements IUserService {
	@Autowired
	private IUserDao _userDao;
	
	public UserService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getByid(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(User objetc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User objetc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
