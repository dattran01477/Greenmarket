package com.greenmarket.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.greenmarket.dao.ICategoryDao;
import com.greenmarket.entity.Category;


@Repository
@Transactional
public class CategoryDaoImpl extends AbstractDao<Integer, Category> implements ICategoryDao {

}
