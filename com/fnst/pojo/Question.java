package com.fnst.paper.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 问题表类
 * @author zhouw.fnst
 *
 */
public class Question implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5818954930793935184L;
	
	/**
	 * key
	 */
	private Integer qId;
	
	/**
	 * 题干内容
	 */
	private String content;
	
	/**
	 * 答案
	 */
	private String answer;
	
	/**
	 * 选项
	 */
	private String choice;
	
	/**
	 * 题目类型 (1:选择题;2:填空题;3:问答题)
	 */
	private QuestionType type;
	
	private String questionType;
	/**
	 * 知识点
	 */
	private KnowledgePoint knowledge;
	
	private String knowledgeContent;
	
	/**
	 * 困难等级 1,2,3,4,5
	 */
	private Integer level;
	
	/**
	 *  附件
	 */
	private String attachMent;
	
	/** 
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 修改时间
	 */
	private Date modifyTime;
	
	private Set<PaperQuestionRel> rels = new HashSet<PaperQuestionRel>();
	
	/**
	 * 是否删除
	 */
	private Integer avaliable;
	
	public Question() {
		super();
	}
	
	public Question(Integer qId, String content) {
		super();
		this.qId = qId;
		this.content = content;
	}

	
	public Question(Integer qId, String content, String questionType,
			String knowledgeContent, Date createTime) {
		super();
		this.qId = qId;
		this.content = content;
		this.questionType = questionType;
		this.knowledgeContent = knowledgeContent;
		this.createTime = createTime;
	}

	public Integer getqId() {
		return qId;
	}

	public void setqId(Integer qId) {
		this.qId = qId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getAttachMent() {
		return attachMent;
	}

	public void setAttachMent(String attachMent) {
		this.attachMent = attachMent;
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	public KnowledgePoint getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(KnowledgePoint knowledge) {
		this.knowledge = knowledge;
	}

	public Integer getAvaliable() {
		return avaliable;
	}

	public void setAvaliable(Integer avaliable) {
		this.avaliable = avaliable;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Set<PaperQuestionRel> getRels() {
		return rels;
	}

	public void setRels(Set<PaperQuestionRel> rels) {
		this.rels = rels;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getKnowledgeContent() {
		return knowledgeContent;
	}

	public void setKnowledgeContent(String knowledgeContent) {
		this.knowledgeContent = knowledgeContent;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
    
	
}
