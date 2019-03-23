package com.greenmarket.service;

import java.util.List;

import com.greenmarket.entity.Farm;

public interface IFarmService {
	public List<Farm> getAll();
	
	public Farm getByid(int id);
	
	public void insert(Farm objetc);
	
	public void update(Farm objetc);
	
	public void delete(int id);

	public List<Farm> getAllByUserId(int userId);
}
