package com.greenmarket.dao.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.greenmarket.dao.IGoodDao;
import com.greenmarket.entity.Good;

@Repository
@Transactional
public class GoodDaoImpl extends AbstractDao<Integer, Good> implements IGoodDao {

}
