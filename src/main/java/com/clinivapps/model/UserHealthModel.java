package com.clinivapps.model;

import java.util.Date;

public class UserHealthModel {
	private Integer healthId;
	private String patientId;
	private String title;
	private String answer;
	private Date createdDate;
	public Integer getHealthId() {
		return healthId;
	}
	public void setHealthId(Integer healthId) {
		this.healthId = healthId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	

	
	
}
