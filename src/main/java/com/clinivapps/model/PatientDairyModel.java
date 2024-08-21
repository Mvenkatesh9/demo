package com.clinivapps.model;

import com.clinivapps.entity.AdverseEventsEntity;

public class PatientDairyModel {
	private Integer dairyId;
	private Integer studyId;
	private Integer userId;
	private Integer dayNumber;
	private String patientId;
	private AdverseEventsEntity aeEntity;
	private String createdDate;
	private String remarks;

	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	public Integer getStudyId() {
		return studyId;
	}
	public void setStudyId(Integer studyId) {
		this.studyId = studyId;
	}
	public Integer getDairyId() {
		return dairyId;
	}
	public void setDairyId(Integer dairyId) {
		this.dairyId = dairyId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getDayNumber() {
		return dayNumber;
	}
	public void setDayNumber(Integer dayNumber) {
		this.dayNumber = dayNumber;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public AdverseEventsEntity getAeEntity() {
		return aeEntity;
	}
	public void setAeEntity(AdverseEventsEntity aeEntity) {
		this.aeEntity = aeEntity;
	}
	
	
	
	
}
