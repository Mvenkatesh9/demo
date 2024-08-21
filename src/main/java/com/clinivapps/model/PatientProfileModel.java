package com.clinivapps.model;

import java.util.Date;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

public class PatientProfileModel {
	private Integer patientId;
	private String fullName;
	private String gender;
	private String mobileNumber;
	private String emailId;
	private String joinedDate;
	private String location;
	private String latitude;
	private String longitude;
	private String icfImageUrl;
	private String icfType;
	private String idProofImageUrl;

	
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
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getIcfImageUrl() {
		return icfImageUrl;
	}
	public void setIcfImageUrl(String icfImageUrl) {
		this.icfImageUrl = icfImageUrl;
	}
	public String getIdProofImageUrl() {
		return idProofImageUrl;
	}
	public void setIdProofImageUrl(String idProofImageUrl) {
		this.idProofImageUrl = idProofImageUrl;
	}
	public String getIcfType() {
		return icfType;
	}
	public void setIcfType(String icfType) {
		this.icfType = icfType;
	}
	
	
	
	
}
