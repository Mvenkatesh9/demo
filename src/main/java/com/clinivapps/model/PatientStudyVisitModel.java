package com.clinivapps.model;



public class PatientStudyVisitModel {
	private Integer studyId;
	private Integer visitId;
	private Integer sectionId;
	private String patientId;
	private Integer fromDay;
	private Integer toDay;
	private String userStatus;
	private String visitName;
	private String doctorStatus;
	private String createdDate;
	private Integer categoryId;

	public Integer getStudyId() {
		return studyId;
	}
	public void setStudyId(Integer studyId) {
		this.studyId = studyId;
	}
	
	public Integer getVisitId() {
		return visitId;
	}
	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getFromDay() {
		return fromDay;
	}
	public void setFromDay(Integer fromDay) {
		this.fromDay = fromDay;
	}
	public Integer getToDay() {
		return toDay;
	}
	public void setToDay(Integer toDay) {
		this.toDay = toDay;
	}
	public String getVisitName() {
		return visitName;
	}
	public void setVisitName(String visitName) {
		this.visitName = visitName;
	}
	public String getDoctorStatus() {
		return doctorStatus;
	}
	public void setDoctorStatus(String doctorStatus) {
		this.doctorStatus = doctorStatus;
	}
	public Integer getSectionId() {
		return sectionId;
	}
	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
}
