package com.greenmarket.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.greenmarket.dao.IFarmDao;
import com.greenmarket.entity.Farm;

@Repository
@Transactional
public class FarmDaoImpl extends AbstractDao<Integer, Farm> implements IFarmDao {

	@Override
	public List<Farm> getAllByUserId(int userId) {
		
		String sql="FROM Farm E WHERE E.user.id=:idUser";
		
		Query query=sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("idUser", userId);
		return query.list();
	}

}
