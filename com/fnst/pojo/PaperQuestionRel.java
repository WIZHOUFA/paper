package com.fnst.paper.pojo;

import java.io.Serializable;

public class PaperQuestionRel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6684155846434112228L;
	
	private Integer id;
	
	private Question question;
	
	private Paper paper;
	
	private Integer score;
	
	private Integer order;
    
	private Double rate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}
	
	
}
