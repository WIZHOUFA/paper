package com.fnst.paper.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fnst.paper.dao.impl.PaperQuestionRelDAO;
import com.fnst.paper.pojo.PaperQuestionRel;
import com.fnst.paper.service.PaperQuestionRelService;

@Transactional
@Service("paperQuestionService")
public class PaperQuestionRelServiceImpl extends BaseServiceImpl<PaperQuestionRel> 
					implements PaperQuestionRelService {
	
	@Resource
	private PaperQuestionRelDAO paperQuestionDAO;
	
	@Override
	public void savePaperQuestionRel(PaperQuestionRel paperQuestionRel) {
		paperQuestionDAO.save(paperQuestionRel);
	}

	@Override
	public void deleteRel(Integer paperId) {
		String hql = "delete from PaperQuestionRel p where p.paper.paperId = " + paperId;
		paperQuestionDAO.executeHql(hql);
	}
	
}
