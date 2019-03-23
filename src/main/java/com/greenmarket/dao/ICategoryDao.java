package com.greenmarket.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.greenmarket.entity.Category;


public interface ICategoryDao extends IGenericDao<Integer, Category> {
		
}
