package com.clinivapps.model;


public class VisitQuestionModel {

	private Integer questionId;
	private Integer sectionId;
	private Integer length;
	private String questionTitle;
	private String questionType;
	private String questionComments;
	private String options;
	private String answer;
	private String sdtm;
	private String cdash;
	private String remarks;
	private Boolean required;

	public Integer getQuestionId() {
		return questionId;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getSectionId() {
		return sectionId;
	}
	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String getQuestionComments() {
		return questionComments;
	}
	public void setQuestionComments(String questionComments) {
		this.questionComments = questionComments;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getSdtm() {
		return sdtm;
	}
	public void setSdtm(String sdtm) {
		this.sdtm = sdtm;
	}
	public String getCdash() {
		return cdash;
	}
	public void setCdash(String cdash) {
		this.cdash = cdash;
	}
	public Boolean getRequired() {
		return required;
	}
	public void setRequired(Boolean required) {
		this.required = required;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
 
	
	
}
