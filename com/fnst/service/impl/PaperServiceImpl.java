package com.fnst.paper.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fnst.paper.dao.impl.PaperDAO;
import com.fnst.paper.pojo.Paper;
import com.fnst.paper.service.PaperService;

@Transactional
@Service("paperService")
public class PaperServiceImpl extends BaseServiceImpl<Paper> implements PaperService{
	
	@Resource
	private PaperDAO paperDAO;
	
	@Override
	public void savePaper(Paper paper) {
		paperDAO.savePaper(paper);
	}

	@Override
	public List<Paper> listPaper() {
		String hql = "Select p from Paper p";
		
		return paperDAO.find(hql);
	}

	@Override
	public void deletePaper(Paper model) {
		paperDAO.delete(model);
	}

}
