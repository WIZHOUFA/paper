package com.fnst.paper.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.fnst.paper.pojo.Question;

@Repository("questionDao")
public class QuestionDAO extends BaseDAOImpl<Question>{

	@SuppressWarnings("unchecked")
	public List<Question> find(String hql, int page, int rows) {
		if ( page < 1) {
			page = 1;
		}
		if (rows < 1) {
			rows = 10;
		}
		Query q = this.getCurrentSession().createQuery(hql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}
	@SuppressWarnings("unchecked")
	public Question findOne(String hql){
		List<Question> l = (List<Question>)this.getCurrentSession().createQuery(hql).list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}
	
	public void updateQues(Question q){
		this.getCurrentSession().update(q);
	}
	public Map<String, Long> statisticsQuestion(String sql) {
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
		query.addScalar("typeName", StringType.INSTANCE)
		.addScalar("num", LongType.INSTANCE);
		List<Object[]> data = query.list();
		Map<String, Long> item = new HashMap<String, Long>();
		for(Object[] objs:data) {
			item.put((String)objs[0], (Long)objs[1]);
		}
		return item;
	}
}
