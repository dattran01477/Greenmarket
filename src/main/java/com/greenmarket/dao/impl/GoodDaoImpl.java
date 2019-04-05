package com.greenmarket.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.greenmarket.dao.IGoodDao;
import com.greenmarket.entity.Good;

@Repository
@Transactional
public class GoodDaoImpl extends AbstractDao<Integer, Good> implements IGoodDao {

	@Override
	public List<Good> getAllGoodByUser(int id) {
		String sql="FROM Good E WHERE E.farmBean.user.id=:id";
		Query query=sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("id", id);
		return query.list();
	}
		
	
}
