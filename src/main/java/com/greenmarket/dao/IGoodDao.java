package com.greenmarket.dao;

import com.greenmarket.entity.Good;

public interface IGoodDao extends IGenericDao<Integer, Good> {
	public java.util.List<Good> getAllGoodByUser(int id);

}
