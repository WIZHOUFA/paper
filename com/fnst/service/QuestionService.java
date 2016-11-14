package com.fnst.paper.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fnst.paper.criteria.CriteriaPaper;
import com.fnst.paper.criteria.KlPointDistribution;
import com.fnst.paper.pojo.KnowledgePoint;
import com.fnst.paper.pojo.Question;
import com.fnst.paper.pojo.QuestionType;

public interface QuestionService extends BaseService<Question> {
	List<Question> getAllQuestions();

	boolean saveQues(Question ques);

	Question one(Question ques);

	List<QuestionType> findAllType();

	List<KnowledgePoint> findAllKP();

	// 筛选试题
	List<Question> getPaperQuestions(CriteriaPaper riteriaPaper);

	List<Question> getMatchQuestions(Integer totalNum, Integer matchYears,
			Integer matchDegree, Set<KlPointDistribution> klPointDistributions,
			String levelStr);

	List<Question> getQuestionByPage(int rows, int page);

	boolean updateQues(Question ques);

	int getTotal();
	
	int getTotalByCondition(String searchContent, String searchType, String searchLevel, String searchKnowledge);

	List<Question> searchByConditon(String searchContent, String searchType, String searchLevel, String searchKnowledge, int rows, int page);

	int isTypeExist(String typeContent);

	boolean saveType(QuestionType qt);
	
	Map<String, Long> statisticsQuestion(String type);
}
