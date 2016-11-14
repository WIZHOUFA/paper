package com.fnst.paper.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fnst.paper.pojo.PaperQuestionRel;

@Repository("pqrDao")
public class PqrDAO extends BaseDAOImpl<PaperQuestionRel>{


	
	@SuppressWarnings("unchecked")
	public List<Integer> getQids(String hql) {
		return this.getCurrentSession().createQuery(hql).list();
	}
}
