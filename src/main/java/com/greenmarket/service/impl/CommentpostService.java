package com.greenmarket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenmarket.dao.ICommentpostDao;
import com.greenmarket.entity.Commentpost;
import com.greenmarket.service.ICommentpostService;

@Service
@Transactional
public class CommentpostService implements ICommentpostService {

	@Autowired
	private ICommentpostDao _commentpostDao;
	
	public CommentpostService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Commentpost> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Commentpost getByid(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Commentpost objetc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Commentpost objetc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
