package com.greenmarket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenmarket.dao.IPostDao;
import com.greenmarket.entity.Post;
import com.greenmarket.service.IPostService;

@Service
@Transactional
public class PostService implements IPostService {

	@Autowired
	private IPostDao _postDao;
	public PostService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Post> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post getByid(int id) {
		return _postDao.getByid(id);
	}

	@Override
	public void insert(Post objetc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Post objetc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
