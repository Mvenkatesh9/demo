package com.clinivapps.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "c_study_visits")
public class StudyVisitEntity {

	private Integer visitId;
	private Integer studyId;
	private Integer sectionId;
	private Integer fromDay;
	private Integer toDay;
	private String title;
	private Date createdDate;
	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", nullable = false)
	public Integer getVisitId() {
		return visitId;
	}
	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}
	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name = "STUDY_ID")
	public Integer getStudyId() {
		return studyId;
	}
	public void setStudyId(Integer studyId) {
		this.studyId = studyId;
	}
	@Column(name = "FROM_DAY")
	public Integer getFromDay() {
		return fromDay;
	}
	public void setFromDay(Integer fromDay) {
		this.fromDay = fromDay;
	}
	@Column(name = "TO_DAY")
	public Integer getToDay() {
		return toDay;
	}
	public void setToDay(Integer toDay) {
		this.toDay = toDay;
	}
	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "SECTION_ID")
	public Integer getSectionId() {
		return sectionId;
	}
	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	

}
