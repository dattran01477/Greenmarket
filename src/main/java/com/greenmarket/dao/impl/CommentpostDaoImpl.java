package com.greenmarket.dao.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.greenmarket.dao.ICommentpostDao;
import com.greenmarket.entity.Commentpost;

@Repository
@Transactional
public class CommentpostDaoImpl extends AbstractDao<Integer, Commentpost> implements ICommentpostDao {

}
