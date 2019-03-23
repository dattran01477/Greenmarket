package com.greenmarket.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.greenmarket.entity.Roleuser;
@Repository
@Transactional
public interface IRoleuserDao extends IGenericDao<Integer, Roleuser> {

}
