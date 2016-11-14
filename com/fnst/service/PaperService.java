package com.fnst.paper.service;

import java.util.List;

import com.fnst.paper.pojo.Paper;

public interface PaperService extends BaseService<Paper>{
	void savePaper(Paper paper);

	List<Paper> listPaper();

	void deletePaper(Paper model);
}
