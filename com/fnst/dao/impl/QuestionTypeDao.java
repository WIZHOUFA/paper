package com.fnst.paper.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fnst.paper.pojo.QuestionType;

@Repository("questionTypeDao")
public class QuestionTypeDao extends BaseDAOImpl<QuestionType>{
	
	@SuppressWarnings("unchecked")
	public List<QuestionType> getTypes(String hql) {
		return this.getCurrentSession().createQuery(hql).list();
	}

	public Serializable saveQt(QuestionType qt) {
		return this.getCurrentSession().save(qt);
	}
}
