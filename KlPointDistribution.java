package com.fnst.paper.criteria;

import java.io.Serializable;

public class KlPointDistribution  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7358859995737177828L;
	
	/**
	 * 
	 */
	private Integer kId;
	
	/**
	 * 
	 */
	private Integer kNum;
	
	public KlPointDistribution() {
		super();
	}

	public KlPointDistribution(Integer kId, Integer kNum) {
		super();
		this.kId = kId;
		this.kNum = kNum;
	}
	
	public Integer getkId() {
		return kId;
	}

	public void setkId(Integer kId) {
		this.kId = kId;
	}

	public Integer getkNum() {
		return kNum;
	}

	public void setkNum(Integer kNum) {
		this.kNum = kNum;
	}

	@Override
	public String toString() {
		return "KlPointDistribution [kId=" + kId + ", kNum=" + kNum + "]";
	}
	

}
