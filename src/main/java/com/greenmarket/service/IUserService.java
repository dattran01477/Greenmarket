package com.greenmarket.service;

import java.util.List;

import com.greenmarket.entity.User;

public interface IUserService {
	public List<User> getAll();
	
	public User getByid(int id);
	
	public void insert(User objetc);
	
	public void update(User objetc);
	
	public void delete(int id);
}
