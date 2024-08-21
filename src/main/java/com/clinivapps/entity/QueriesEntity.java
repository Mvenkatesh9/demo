package com.clinivapps.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "c_queries")
public class QueriesEntity {

	private Integer id;
	private Integer raiseduserId;  
	private Integer assigneduserId;  
	private Integer categoryId;
	private Integer sectionId;
	private String patientId;
	private Integer questionId;
	private String status;
	private String assignedRole;
	private Date createdDate;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "QUESTION_ID")
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name = "CATEGORY_ID")
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	@Column(name = "PATIENT_ID")
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	@Column(name = "RAISED_USER_ID")
	public Integer getRaiseduserId() {
		return raiseduserId;
	}

	public void setRaiseduserId(Integer raiseduserId) {
		this.raiseduserId = raiseduserId;
	}
	@Column(name = "ASSIGNED_USER_ID")
	public Integer getAssigneduserId() {
		return assigneduserId;
	}

	public void setAssigneduserId(Integer assigneduserId) {
		this.assigneduserId = assigneduserId;
	}
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "ASSIGNED_ROLE")
	public String getAssignedRole() {
		return assignedRole;
	}

	public void setAssignedRole(String assignedRole) {
		this.assignedRole = assignedRole;
	}
	@Column(name = "SECTION_ID")
	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	


}
