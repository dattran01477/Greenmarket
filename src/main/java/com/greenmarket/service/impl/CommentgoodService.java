package com.greenmarket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenmarket.dao.ICommentgoodDao;
import com.greenmarket.entity.Commentgood;
import com.greenmarket.service.ICommentgoodService;

@Service
@Transactional
public class CommentgoodService implements ICommentgoodService {

	@Autowired
	private ICommentgoodDao _commentDao;
	public CommentgoodService()
	{
		// TODO Auto-generated constructor stub
	}
	@Override
	public List<Commentgood> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Commentgood getByid(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Commentgood objetc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Commentgood objetc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
