package com.fnst.paper.dao.impl;
import org.springframework.stereotype.Repository;

import com.fnst.paper.pojo.Paper;

@Repository("paperDao")
public class PaperDAO extends BaseDAOImpl<Paper>{

	public void savePaper(Paper paper) {
		this.getCurrentSession().save(paper);
	}


}
