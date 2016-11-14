package com.fnst.paper.criteria;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fnst.paper.pojo.Paper;

public class CriteriaPaper extends Paper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 题目总量
	 */
	private Integer totalNum;
	
	private String createDate;
	/**
	 * 试卷难易度
	 * 1：容易; 2: 一般; 3: 困难
	 */
	private Integer paperLevel;
	
	/**
	 * 与近几年的匹配
	 * 
	 */
	private Integer matchYears = 1;
	
	/**
	 * 匹配度
	 * 10：小于10%; 20:小于20%; 3:小于30%
	 */
	private Integer matchDegree;

	private String[] qId; 
	/**
	 * 题型分布
	 * 
	 */
	private Set<QtTypeDistribution> QtTypeDistributions = new HashSet<QtTypeDistribution>();
	
	/**
	 * 知识点分布
	 * 
	 */
	private Set<KlPointDistribution> KlPointDistributions = new HashSet<KlPointDistribution>();
	
	public CriteriaPaper() {
		
	}
	
	public CriteriaPaper(Integer totalNum, String createDate,
			Integer paperLevel, Integer matchYears, Integer matchDegree,
			Set<QtTypeDistribution> qtTypeDistributions,
			Set<KlPointDistribution> klPointDistributions) {
		super();
		this.totalNum = totalNum;
		this.createDate = createDate;
		this.paperLevel = paperLevel;
		this.matchYears = matchYears;
		this.matchDegree = matchDegree;
		QtTypeDistributions = qtTypeDistributions;
		KlPointDistributions = klPointDistributions;
	}

	public CriteriaPaper(CriteriaPaper paper) {
		super();
		this.totalNum = paper.getTotalNum();
		this.createDate = paper.getCreateDate();
		this.paperLevel = paper.getPaperLevel();
		this.matchYears = paper.getMatchYears();
		this.matchDegree = paper.getMatchDegree();
		QtTypeDistributions = paper.getQtTypeDistributions();
		KlPointDistributions = paper.getKlPointDistributions();
	}
	
	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getPaperLevel() {
		return paperLevel;
	}

	public void setPaperLevel(Integer paperLevel) {
		this.paperLevel = paperLevel;
	}

	public Integer getMatchYears() {
		return matchYears;
	}

	public void setMatchYears(Integer matchYears) {
		this.matchYears = matchYears;
	}

	public Integer getMatchDegree() {
		return matchDegree;
	}

	public void setMatchDegree(Integer matchDegree) {
		this.matchDegree = matchDegree;
	}

	public Set<QtTypeDistribution> getQtTypeDistributions() {
		return QtTypeDistributions;
	}

	public void setQtTypeDistributions(Set<QtTypeDistribution> qtTypeDistributions) {
		QtTypeDistributions = qtTypeDistributions;
	}

	public Set<KlPointDistribution> getKlPointDistributions() {
		return KlPointDistributions;
	}

	public void setKlPointDistributions(
			Set<KlPointDistribution> klPointDistributions) {
		KlPointDistributions = klPointDistributions;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String[] getqId() {
		return qId;
	}

	public void setqId(String[] qId) {
		this.qId = qId;
	}

}
