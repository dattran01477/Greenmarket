package com.greenmarket.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.greenmarket.entity.Location;

public interface ILocationDao extends IGenericDao<Integer, Location> {

}
