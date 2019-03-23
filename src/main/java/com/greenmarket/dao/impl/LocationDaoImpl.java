package com.greenmarket.dao.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.greenmarket.dao.ILocationDao;
import com.greenmarket.entity.Location;

@Repository
@Transactional
public class LocationDaoImpl extends AbstractDao<Integer, Location> implements ILocationDao {

}
