package com.greenmarket.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.greenmarket.entity.Good;

public interface IGoodDao extends IGenericDao<Integer, Good> {

}
