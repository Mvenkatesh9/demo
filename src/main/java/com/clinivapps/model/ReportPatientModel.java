package com.clinivapps.model;


public class ReportPatientModel {
	private Integer patientId;
	private String fullName;
	private String doctorName;
	private String gender;
	private String mobileNumber;
	private String emailId;
	private String joinedDate;
	private Integer totalVisits;
	private Integer completedVisits;
	private Integer patientDuration;
	private String location;
	private String hospital;

	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(String joinedDate) {
		this.joinedDate = joinedDate;
	}
	
	public Integer getTotalVisits() {
		return totalVisits;
	}
	public void setTotalVisits(Integer totalVisits) {
		this.totalVisits = totalVisits;
	}
	public Integer getPatientDuration() {
		return patientDuration;
	}
	public void setPatientDuration(Integer patientDuration) {
		this.patientDuration = patientDuration;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public Integer getCompletedVisits() {
		return completedVisits;
	}
	public void setCompletedVisits(Integer completedVisits) {
		this.completedVisits = completedVisits;
	}

	
	
	
}
