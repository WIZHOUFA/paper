package com.fnst.paper.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 问题类型类
 * @author zhouw.fnst
 *
 */
public class QuestionType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5523865851027471721L;
	
	private Integer qtId;
	
	private String typeName;
	
	private Set<Question> questions = new HashSet<Question>();
	
	public Integer getQtId() {
		return qtId;
	}

	public void setQtId(Integer qtId) {
		this.qtId = qtId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	
}
