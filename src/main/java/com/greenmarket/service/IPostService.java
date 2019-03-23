package com.greenmarket.service;

import java.util.List;

import com.greenmarket.entity.Post;;

public interface IPostService {
	public List<Post> getAll();
	
	public Post getByid(int id);
	
	public void insert(Post objetc);
	
	public void update(Post objetc);
	
	public void delete(int id);
}
