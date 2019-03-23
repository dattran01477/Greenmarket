package com.greenmarket.dao.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.greenmarket.dao.ICommentgoodDao;
import com.greenmarket.entity.Commentgood;

@Repository
@Transactional
public class CommentgoodDaoImpl extends AbstractDao<Integer, Commentgood> implements ICommentgoodDao {

}
