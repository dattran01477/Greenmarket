package com.greenmarket.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.greenmarket.entity.Good;
@Repository
@Transactional
public interface IGoodDao extends IGenericDao<Integer, Good> {

}
