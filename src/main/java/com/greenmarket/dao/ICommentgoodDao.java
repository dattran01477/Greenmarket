package com.greenmarket.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.greenmarket.entity.Commentgood;;

@Repository
@Transactional
public interface ICommentgoodDao extends IGenericDao<Integer, Commentgood> {

}
