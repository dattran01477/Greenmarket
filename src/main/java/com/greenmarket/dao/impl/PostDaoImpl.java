package com.greenmarket.dao.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.greenmarket.dao.IPostDao;
import com.greenmarket.entity.Post;

@Repository
@Transactional
public class PostDaoImpl extends AbstractDao<Integer, Post> implements IPostDao {

}
