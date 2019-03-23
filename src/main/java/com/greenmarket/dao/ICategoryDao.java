package com.greenmarket.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.greenmarket.entity.Category;

@Repository
@Transactional
public interface ICategoryDao extends IGenericDao<Integer, Category> {
		
}
