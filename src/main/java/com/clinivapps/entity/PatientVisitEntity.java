package com.clinivapps.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "c_patient_visits")
public class PatientVisitEntity implements java.io.Serializable {

	private Integer id;
	private Integer visitId;
	private Integer studyId;
	private String patientId;
	private Integer siteId;
	private Integer studyNurseUserId;
	private String status;
	private String approvalStatus;
	private String doctorRemarks;
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
	@Column(name = "VISIT_ID")
	public Integer getVisitId() {
		return visitId;
	}
	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}
	@Column(name = "PATIENT_ID")
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	@Column(name = "SITE_ID")
	public Integer getSiteId() {
		return siteId;
	}
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "APPROVAL_STATUS")
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	@Column(name = "DOCTOR_REMARKS")
	public String getDoctorRemarks() {
		return doctorRemarks;
	}
	public void setDoctorRemarks(String doctorRemarks) {
		this.doctorRemarks = doctorRemarks;
	}
	
	@Column(name = "USER_ID")
	public Integer getStudyNurseUserId() {
		return studyNurseUserId;
	}
	public void setStudyNurseUserId(Integer studyNurseUserId) {
		this.studyNurseUserId = studyNurseUserId;
	}

	

}
