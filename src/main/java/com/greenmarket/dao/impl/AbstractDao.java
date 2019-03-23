package com.greenmarket.dao.impl;
import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.greenmarket.dao.IGenericDao;
import com.greenmarket.ultils.ClassUltil;



@Transactional
public  class AbstractDao<ID extends Serializable,T> implements IGenericDao<ID, T> {

	@Autowired
	protected SessionFactory sessionFactory;
	
	private Class<T> persistenceClass;
	
	private Class className;
	
	public String getPersistenceClassName()
	{
		return persistenceClass.getSimpleName();
	}
	
	public  AbstractDao() {
		this.persistenceClass=(Class<T>) ((java.lang.reflect.ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
		this.className=ClassUltil.classForName(getPersistenceClassName());
	}
	
	
	@Override
	public List<T> getAll() {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(this.className);
		return criteria.list();
	}


	@Override
	public T getByid(ID id) {
		return (T) sessionFactory.getCurrentSession().get(this.className, id);
	}


	@Override
	public void insert(T objetc) {
		sessionFactory.getCurrentSession().save(objetc);
	}


	@Override
	public void update(T objetc) {
		sessionFactory.getCurrentSession().merge(objetc);
	}


	@Override
	public void delete(ID id) {
		sessionFactory.getCurrentSession().delete(getByid(id));
	}

}
