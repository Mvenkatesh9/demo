package com.clinivapps.model;

import java.util.List;

public class VisitSectionModel {
	private Integer sectionId;
	private String title;
	private String description;
	private List<VisitQuestionModel> questions;
	
	public Integer getSectionId() {
		return sectionId;
	}
	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<VisitQuestionModel> getQuestions() {
		return questions;
	}
	public void setQuestions(List<VisitQuestionModel> questions) {
		this.questions = questions;
	}
	
	
	
}
