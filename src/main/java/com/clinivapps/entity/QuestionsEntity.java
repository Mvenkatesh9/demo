package com.clinivapps.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "c_questions")
public class QuestionsEntity {

	private Integer questionId;
	private Integer length;
	private Integer sectionId;
	private String questionTitle;
	private String questionType;
	private String questionComments;
	private String options;
	private String sdtm;
	private String cdash;
	private Boolean required;
	private Date createdDate;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", nullable = false)
	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}


	@Column(name = "TITLE")
	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	@Column(name = "OPTIONS")
	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	@Column(name = "TYPE")
	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	@Column(name = "SECTION_ID")
	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}
	
	@Column(name = "COMMENTS")
	public String getQuestionComments() {
		return questionComments;
	}	

	public void setQuestionComments(String questionComments) {
		this.questionComments = questionComments;
	}

	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name = "LENGTH")
	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
	@Column(name = "SDTM")
	public String getSdtm() {
		return sdtm;
	}

	public void setSdtm(String sdtm) {
		this.sdtm = sdtm;
	}
	@Column(name = "CDASH")
	public String getCdash() {
		return cdash;
	}

	public void setCdash(String cdash) {
		this.cdash = cdash;
	}
	@Column(name = "REQUIRED")
	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	


}

