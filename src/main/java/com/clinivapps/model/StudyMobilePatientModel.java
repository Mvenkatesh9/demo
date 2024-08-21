package com.clinivapps.model;

import java.util.Date;

public class StudyMobilePatientModel {

	private String patientId;
	private String fullName;
	private String gender;
	private String dob;
	private Integer age;
	private Integer siteId;
	private String siteName;
	private String mobileNumber;
	private String emailId;
	private String status;
	private String studyStatus;
	private String icfSignType;
	private String icfSignURL;
	private String registeredDate;
	private String screenedDate;
	private String treatmentStartDate;
	private String completedDate;
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getStudyStatus() {
		return studyStatus;
	}
	public void setStudyStatus(String studyStatus) {
		this.studyStatus = studyStatus;
	}
	public String getIcfSignType() {
		return icfSignType;
	}
	public void setIcfSignType(String icfSignType) {
		this.icfSignType = icfSignType;
	}
	public String getIcfSignURL() {
		return icfSignURL;
	}
	public void setIcfSignURL(String icfSignURL) {
		this.icfSignURL = icfSignURL;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getSiteId() {
		return siteId;
	}
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(String registeredDate) {
		this.registeredDate = registeredDate;
	}
	public String getScreenedDate() {
		return screenedDate;
	}
	public void setScreenedDate(String screenedDate) {
		this.screenedDate = screenedDate;
	}
	public String getTreatmentStartDate() {
		return treatmentStartDate;
	}
	public void setTreatmentStartDate(String treatmentStartDate) {
		this.treatmentStartDate = treatmentStartDate;
	}
	public String getCompletedDate() {
		return completedDate;
	}
	public void setCompletedDate(String completedDate) {
		this.completedDate = completedDate;
	}
	
	
	
}
