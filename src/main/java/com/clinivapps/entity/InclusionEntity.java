package com.clinivapps.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "c_inclusion_creteria")
public class InclusionEntity implements java.io.Serializable {

	private Integer id;
	private Integer studyId;
	private Date createdDate;
	private String title;
	private String validityPoint;

	public InclusionEntity() {
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "VALIDITY_POINT")
	public String getValidityPoint() {
		return validityPoint;
	}
	public void setValidityPoint(String validityPoint) {
		this.validityPoint = validityPoint;
	}

	
	

}
