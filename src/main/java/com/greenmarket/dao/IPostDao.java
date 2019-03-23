package com.greenmarket.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.greenmarket.entity.Post;

public interface IPostDao extends IGenericDao<Integer, Post> {

}
