package com.fnst.paper.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Paper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4864069336077489055L;
	
	private Integer paperId;
	
	/**
	 * 试卷名称
	 */
	private String paperName;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 类型 （A，B）
	 */
	private String type;
	
	/**
	 * 总分
	 */
	private Integer totalScore;
	
	
	/**
	 * 选择题数目
	 */
	private Integer chooseNum;
	
	/**
	 * 选择题分数
	 */
	private Integer chooseScore;
	
	/**
	 * 填空题数目
	 */
	private Integer blankNum;
	
	/**
	 * 填空题分数
	 */
	private Integer blankScore;
	
	/**
	 * 选择题数目
	 */
	private Integer answerNum;
	
	/**
	 * 选择题分数
	 */
	private Integer answerScore;
	
	private String content;
	
	private String answerContent;
	public Paper() {
		
	}
	
	public Paper(String paperName, Date createTime, String type,
			Integer totalScore) {
		super();
		this.paperName = paperName;
		this.createTime = createTime;
		this.type = type;
		this.totalScore = totalScore;
	}

	private Set<PaperQuestionRel> rels = new HashSet<PaperQuestionRel>();
	
	public Integer getPaperId() {
		return paperId;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	
	public Set<PaperQuestionRel> getRels() {
		return rels;
	}

	public void setRels(Set<PaperQuestionRel> rels) {
		this.rels = rels;
	}
	
	public Integer getChooseNum() {
		return chooseNum;
	}

	public void setChooseNum(Integer chooseNum) {
		this.chooseNum = chooseNum;
	}

	public Integer getChooseScore() {
		return chooseScore;
	}

	public void setChooseScore(Integer chooseScore) {
		this.chooseScore = chooseScore;
	}

	public Integer getBlankNum() {
		return blankNum;
	}

	public void setBlankNum(Integer blankNum) {
		this.blankNum = blankNum;
	}

	public Integer getBlankScore() {
		return blankScore;
	}

	public void setBlankScore(Integer blankScore) {
		this.blankScore = blankScore;
	}

	public Integer getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(Integer answerNum) {
		this.answerNum = answerNum;
	}

	public Integer getAnswerScore() {
		return answerScore;
	}

	public void setAnswerScore(Integer answerScore) {
		this.answerScore = answerScore;
	}

	@Override
	public String toString() {
		return "Paper [paperId=" + paperId + ", paperName=" + paperName
				+ ", createTime=" + createTime + ", type=" + type
				+ ", totalScore=" + totalScore + "]";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	
	
}
