package com.greenmarket.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.greenmarket.entity.Commentpost;

@Repository
@Transactional
public interface ICommentpostDao extends IGenericDao<Integer, Commentpost> {

}
