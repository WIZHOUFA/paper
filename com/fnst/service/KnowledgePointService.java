package com.fnst.paper.service;

import java.util.List;

import com.fnst.paper.pojo.KnowledgePoint;

public interface KnowledgePointService extends BaseService<KnowledgePoint>{
	List<KnowledgePoint> listAllKnowledge(String content);

	boolean saveKp(KnowledgePoint kp);
	
	int isExsit(String content);
}
