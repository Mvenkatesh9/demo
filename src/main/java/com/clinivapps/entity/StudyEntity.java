package com.clinivapps.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "c_study")
public class StudyEntity {

	private Integer studyId;
	private String studyName;
	private Date startDate;
	private Date endDate;
	private Date createdDate;
	private String therapeuticIds;
	private String studyShortName;
	private String principalInvestogator;
	private String icfUrl;
	private String pisUrl;
	private String studyBannerURL;
	private String protocolNumber;
	private Integer visitCount;
	private Integer patientDuration;
	private Integer doseDuration;
	private String contactName;
	private String contactNumber;
	private String contactEmail;
	private String docAggreementUrl;
	private String coPrincipalInvestogators;
	private String studyStatus;

	public StudyEntity() {
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", nullable = false)
	public Integer getStudyId() {
		return studyId;
	}

	public void setStudyId(Integer studyId) {
		this.studyId = studyId;
	}
	
	@Column(name = "STUDY_NAME")
	public String getStudyName() {
		return studyName;
	}

	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}
	
	@Column(name = "START_DATE")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Column(name = "END_DATE")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "THERAPEUTIC_IDS")
	public String getTherapeuticIds() {
		return therapeuticIds;
	}

	public void setTherapeuticIds(String therapeuticIds) {
		this.therapeuticIds = therapeuticIds;
	}

	@Column(name = "STUDY_SHORT_NAME")
	public String getStudyShortName() {
		return studyShortName;
	}
	public void setStudyShortName(String studyShortName) {
		this.studyShortName = studyShortName;
	}

	@Column(name = "ICF_URL")
	public String getIcfUrl() {
		return icfUrl;
	}
	public void setIcfUrl(String icfUrl) {
		this.icfUrl = icfUrl;
	}
	@Column(name = "STUDY_BANNER_URL")
	public String getStudyBannerURL() {
		return studyBannerURL;
	}
	public void setStudyBannerURL(String studyBannerURL) {
		this.studyBannerURL = studyBannerURL;
	}
	@Column(name = "PROTOCOL_NUMBER")
	public String getProtocolNumber() {
		return protocolNumber;
	}
	public void setProtocolNumber(String protocolNumber) {
		this.protocolNumber = protocolNumber;
	}
	@Column(name = "VISIT_COUNT")
	public Integer getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}
	@Column(name = "PATIENT_DURATION")
	public Integer getPatientDuration() {
		return patientDuration;
	}
	public void setPatientDuration(Integer patientDuration) {
		this.patientDuration = patientDuration;
	}
	@Column(name = "CONTACT_NAME")
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	@Column(name = "CONTACT_NUMBER")
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	@Column(name = "CONTACT_EMAIL")
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	@Column(name = "DOC_AGGREEMENT")
	public String getDocAggreementUrl() {
		return docAggreementUrl;
	}
	public void setDocAggreementUrl(String docAggreementUrl) {
		this.docAggreementUrl = docAggreementUrl;
	}
	@Column(name = "PIS_URL")
	public String getPisUrl() {
		return pisUrl;
	}
	public void setPisUrl(String pisUrl) {
		this.pisUrl = pisUrl;
	}
	@Column(name = "PI_NAME")
	public String getPrincipalInvestogator() {
		return principalInvestogator;
	}
	public void setPrincipalInvestogator(String principalInvestogator) {
		this.principalInvestogator = principalInvestogator;
	}
	@Column(name = "CO_PRINCIPAL_INVESTIGATORS")
	public String getCoPrincipalInvestogators() {
		return coPrincipalInvestogators;
	}
	public void setCoPrincipalInvestogators(String coPrincipalInvestogators) {
		this.coPrincipalInvestogators = coPrincipalInvestogators;
	}
	@Column(name = "STATUS")
	public String getStudyStatus() {
		return studyStatus;
	}
	public void setStudyStatus(String studyStatus) {
		this.studyStatus = studyStatus;
	}
	@Column(name = "DOSE_DURATION")
	public Integer getDoseDuration() {
		return doseDuration;
	}
	public void setDoseDuration(Integer doseDuration) {
		this.doseDuration = doseDuration;
	}
	
	

}
