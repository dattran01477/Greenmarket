package com.greenmarket.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.greenmarket.entity.User;

public interface IUserDao extends IGenericDao<Integer, User> {
		
	public User getUserByName(String userName);
}
