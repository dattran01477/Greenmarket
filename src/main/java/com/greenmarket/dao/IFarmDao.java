package com.greenmarket.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.greenmarket.entity.Farm;
@Repository
@Transactional
public interface IFarmDao extends IGenericDao<Integer, Farm> {
	public List<Farm> getAllByUserId(int userId);
}
