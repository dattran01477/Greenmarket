package com.greenmarket.dao.impl;

import org.hibernate.Query;

import com.greenmarket.dao.IUserDao;
import com.greenmarket.entity.User;

public class UserDaoImpl extends AbstractDao<Integer, User> implements IUserDao {

	@Override
	public User getUserByName(String userName) {
		String sql="FROM User E WHERE E.username=:username";
		Query query=sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("userName", userName);
		return (User) query.uniqueResult();
	}




}
