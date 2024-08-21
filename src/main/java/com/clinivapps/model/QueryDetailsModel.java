package com.clinivapps.model;


public class QueryDetailsModel {
	private Integer detailId;
	private Integer queryId;
	private String patientId;
	private Integer visitId;
	private Integer questionId;
	private Integer updatedUserId;
	private String questionName;
	private String questionType;
	private String questionOptions;
	private String questionComments;
	private String comments;
	private String originalAnswer;
	private String modifiedAnswer;
	private String status;
	private String updatedFrom;
	private String createdDate;
	
	public Integer getQueryId() {
		return queryId;
	}
	public void setQueryId(Integer queryId) {
		this.queryId = queryId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public Integer getVisitId() {
		return visitId;
	}
	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}
	
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getOriginalAnswer() {
		return originalAnswer;
	}
	public void setOriginalAnswer(String originalAnswer) {
		this.originalAnswer = originalAnswer;
	}
	public String getModifiedAnswer() {
		return modifiedAnswer;
	}
	public void setModifiedAnswer(String modifiedAnswer) {
		this.modifiedAnswer = modifiedAnswer;
	}
	public String getUpdatedFrom() {
		return updatedFrom;
	}
	public void setUpdatedFrom(String updatedFrom) {
		this.updatedFrom = updatedFrom;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getUpdatedUserId() {
		return updatedUserId;
	}
	public void setUpdatedUserId(Integer updatedUserId) {
		this.updatedUserId = updatedUserId;
	}
	public Integer getDetailId() {
		return detailId;
	}
	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String getQuestionOptions() {
		return questionOptions;
	}
	public void setQuestionOptions(String questionOptions) {
		this.questionOptions = questionOptions;
	}
	public String getQuestionComments() {
		return questionComments;
	}
	public void setQuestionComments(String questionComments) {
		this.questionComments = questionComments;
	}
	

	
}
