package com.fnst.paper.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fnst.paper.pojo.KnowledgePoint;

@Repository("knowledgePoint")
public class KnowledgePointDao extends BaseDAOImpl<KnowledgePoint>{
	@SuppressWarnings("unchecked")
	public List<KnowledgePoint> getKPoints(String hql) {
		return this.getCurrentSession().createQuery(hql).list();
	}
	
	public Serializable saveKp(KnowledgePoint k){
		return this.getCurrentSession().save(k);
	}
}
