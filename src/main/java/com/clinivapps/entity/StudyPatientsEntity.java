package com.clinivapps.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "c_study_patients")
public class StudyPatientsEntity {

	private Integer id;
	private Integer studyId;
	private String patientId;
	private String mobileNumber;
	private String fullName;
	private String emailId;
	private String gender;
	private String status;
	private String icfSignType;
	private String icfSignURL;
	private String productIds;
	private Date dateOfBirth;
	private Integer trailSiteId;
	private Integer enrolledUserId;
	private Integer age;
	private String idProofUrl;
	private String remarks;
	private Date registeredDate;
	private Date screenedDate;
	private Date treatmentStartDate;
	private Date completedDate;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "PATIENT_ID")
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	@Column(name = "STUDY_ID")
	public Integer getStudyId() {
		return studyId;
	}
	public void setStudyId(Integer studyId) {
		this.studyId = studyId;
	}
	
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "MOBILE_NUMBER")
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	@Column(name = "GENDER")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name = "EMAIL_ID")
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	@Column(name = "ICF_SIGN_TYPE")
	public String getIcfSignType() {
		return icfSignType;
	}
	public void setIcfSignType(String icfSignType) {
		this.icfSignType = icfSignType;
	}
	@Column(name = "ICF_SIGN_URL")
	public String getIcfSignURL() {
		return icfSignURL;
	}
	public void setIcfSignURL(String icfSignURL) {
		this.icfSignURL = icfSignURL;
	}
	@Column(name = "TRAIL_SITE_ID")
	public Integer getTrailSiteId() {
		return trailSiteId;
	}
	public void setTrailSiteId(Integer trailSiteId) {
		this.trailSiteId = trailSiteId;
	}
	@Column(name = "PRODUCT_IDS")
	public String getProductIds() {
		return productIds;
	}
	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}

	@Column(name = "FULL_NAME")
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	@Column(name = "ENROLLED_USER_ID")
	public Integer getEnrolledUserId() {
		return enrolledUserId;
	}
	public void setEnrolledUserId(Integer enrolledUserId) {
		this.enrolledUserId = enrolledUserId;
	}
	@Column(name = "AGE")
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Column(name = "ID_PROOF_URL")
	public String getIdProofUrl() {
		return idProofUrl;
	}
	public void setIdProofUrl(String idProofUrl) {
		this.idProofUrl = idProofUrl;
	}
	@Column(name = "REMARKS")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name = "DATE_OF_BIRTH")
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	@Column(name = "REGISTERED_DATE")
	public Date getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}
	@Column(name = "SCREENED_DATE")
	public Date getScreenedDate() {
		return screenedDate;
	}
	public void setScreenedDate(Date screenedDate) {
		this.screenedDate = screenedDate;
	}
	@Column(name = "TREATMENT_START_DATE")
	public Date getTreatmentStartDate() {
		return treatmentStartDate;
	}
	public void setTreatmentStartDate(Date treatmentStartDate) {
		this.treatmentStartDate = treatmentStartDate;
	}
	@Column(name = "COMPLETED_DATE")
	public Date getCompletedDate() {
		return completedDate;
	}
	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	
}
