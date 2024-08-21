package com.clinivapps.model;

import java.util.Date;

public class LogReportModel {

	private Integer userId;
	private String siteId;
	private String roleName;
	private String activity;
	private String fieldName;
	private String originalAnswer;
	private String modifiedAnswer;
	private Date createdDate;
	private String description;
	private String trailParticipant;

	
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getTrailParticipant() {
		return trailParticipant;
	}
	public void setTrailParticipant(String trailParticipant) {
		this.trailParticipant = trailParticipant;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
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
	
	
		
}
