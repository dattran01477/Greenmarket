package com.greenmarket.service;

import java.util.List;

import com.greenmarket.entity.Location;

public interface ILocationService {
	public List<Location> getAll();
	
	public Location getByid(int id);
	
	public void insert(Location objetc);
	
	public void update(Location objetc);
	
	public void delete(int id);
}
