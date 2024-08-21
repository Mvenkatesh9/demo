package com.clinivapps.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class InclusionExcusionModel {
	private Integer id;
	private Integer studyId;
	private String createdDate;
	private String title;
	private String validityPoint;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getValidityPoint() {
		return validityPoint;
	}
	public void setValidityPoint(String validityPoint) {
		this.validityPoint = validityPoint;
	}
		
	
}
