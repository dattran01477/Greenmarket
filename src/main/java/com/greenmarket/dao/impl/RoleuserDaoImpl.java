package com.greenmarket.dao.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.greenmarket.dao.IRoleuserDao;
import com.greenmarket.entity.Roleuser;

@Repository
@Transactional
public class RoleuserDaoImpl extends AbstractDao<Integer, Roleuser> implements IRoleuserDao {

}
