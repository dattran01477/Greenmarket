package com.greenmarket.service;

import java.util.List;

import com.greenmarket.entity.Roleuser;;

public interface IRoleuserService {
	public List<Roleuser> getAll();
	
	public Roleuser getByid(int id);
	
	public void insert(Roleuser objetc);
	
	public void update(Roleuser objetc);
	
	public void delete(int id);
}
