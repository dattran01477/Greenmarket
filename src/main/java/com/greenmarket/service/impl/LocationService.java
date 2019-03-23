package com.greenmarket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenmarket.dao.ILocationDao;
import com.greenmarket.entity.Location;
import com.greenmarket.service.ILocationService;

@Service
@Transactional
public class LocationService implements ILocationService {
	@Autowired
	private ILocationDao _locationDao;
	
	public LocationService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Location> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location getByid(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Location objetc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Location objetc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
