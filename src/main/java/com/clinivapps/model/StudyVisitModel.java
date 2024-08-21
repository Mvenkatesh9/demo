package com.clinivapps.model;

import java.util.Date;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

public class StudyVisitModel {
	private Integer studyId;
	private String studyName;
	private String visitName;
	private Integer fromDay;
	private Integer toDay;
	private Integer visitId;

	
	public Integer getStudyId() {
		return studyId;
	}
	public void setStudyId(Integer studyId) {
		this.studyId = studyId;
	}
	
	public String getStudyName() {
		return studyName;
	}
	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}
	public String getVisitName() {
		return visitName;
	}
	public void setVisitName(String visitName) {
		this.visitName = visitName;
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
	public Integer getVisitId() {
		return visitId;
	}
	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}
	
	
	
}
