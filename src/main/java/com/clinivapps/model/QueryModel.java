package com.clinivapps.model;


public class QueryModel {
	private Integer queryId;
	private String patientId;
	private Integer sectionId;
	private Integer categoryId;
	private Integer questionId;
	private Integer raisedUserId;
	private Integer assignedUserId;
	private String assignedRole;
	private String createdDate;
	private String visitName;
	private String status;
	private String questionName;
	private String comments;
	private String answer;
	
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
	public Integer getRaisedUserId() {
		return raisedUserId;
	}
	public void setRaisedUserId(Integer raisedUserId) {
		this.raisedUserId = raisedUserId;
	}
	public Integer getAssignedUserId() {
		return assignedUserId;
	}
	public void setAssignedUserId(Integer assignedUserId) {
		this.assignedUserId = assignedUserId;
	}
	public String getAssignedRole() {
		return assignedRole;
	}
	public void setAssignedRole(String assignedRole) {
		this.assignedRole = assignedRole;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getVisitName() {
		return visitName;
	}
	public void setVisitName(String visitName) {
		this.visitName = visitName;
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
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getSectionId() {
		return sectionId;
	}
	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}
	

	
}
