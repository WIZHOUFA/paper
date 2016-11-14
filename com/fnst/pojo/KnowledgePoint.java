package com.fnst.paper.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 知识点类
 * @author zhouw.fnst
 *
 */
public class KnowledgePoint implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5715369201219118417L;
	
	private Integer kId;
	
	private String content;
    
	private Set<Question> questions = new HashSet<Question>();
	
	public Integer getkId() {
		return kId;
	}

	public void setkId(Integer kId) {
		this.kId = kId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	
	
	
}
