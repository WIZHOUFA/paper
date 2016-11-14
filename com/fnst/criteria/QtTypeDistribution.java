package com.fnst.paper.criteria;

import java.io.Serializable;

public class QtTypeDistribution implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -134040547616564301L;

	/**
	 * 题型ID
	 */
	private Integer qtId;
	
	/**
	 * 该题型的题量
	 */
	private Integer qtNum;
	
	/**
	 * 该题型的分值
	 */
	private Integer qtScore;
	
	public QtTypeDistribution(Integer qtId, Integer qtNum, Integer qtScore) {
		super();
		this.qtId = qtId;
		this.qtNum = qtNum;
		this.qtScore = qtScore;
	}

	public QtTypeDistribution() {
		super();
	}

	public Integer getQtId() {
		return qtId;
	}

	public void setQtId(Integer qtId) {
		this.qtId = qtId;
	}

	public Integer getQtNum() {
		return qtNum;
	}

	public void setQtNum(Integer qtNum) {
		this.qtNum = qtNum;
	}

	public Integer getQtScore() {
		return qtScore;
	}

	public void setQtScore(Integer qtScore) {
		this.qtScore = qtScore;
	}

	@Override
	public String toString() {
		return "QtTypeDistribution [qtId=" + qtId + ", qtNum=" + qtNum
				+ ", qtScore=" + qtScore + "]";
	}
	
	

}
