package com.fnst.paper.service;

import com.fnst.paper.pojo.PaperQuestionRel;

public interface PaperQuestionRelService extends BaseService<PaperQuestionRel>{
	void savePaperQuestionRel(PaperQuestionRel paperQuestionRel);

	void deleteRel(Integer paperId);
}
