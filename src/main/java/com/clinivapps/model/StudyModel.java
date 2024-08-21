package com.clinivapps.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

public class StudyModel {
	private Integer studyId;
	private String studyName;
	private String startDate;
	private String endDate;
	private Date createdDate;
	private String therapeuticIds;
	private String therapeuticNames;
	private String studyShortName;
	private String studyBannerURL;
	private String icfUrl;
	private String pisUrl;
	private String protocolNumber;
	private String principalInvestigator;
	private Integer totalVisits;
	private String userStatus;
	private Integer patientDuration;
	private Integer doseDuration;
	private String contactName;
	private String contactMobile;
	private String contactEmail;
	private String docAggreementUrl;
	private String trailSiteName;
	private String coPrincipalInvestigator;
	private List<TrialSiteModel>trailSites;
	private Integer trailSiteId;
	private String studyStatus;

	
	public String getTherapeuticNames() {
		return therapeuticNames;
	}
	public void setTherapeuticNames(String therapeuticNames) {
		this.therapeuticNames = therapeuticNames;
	}
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
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getTherapeuticIds() {
		return therapeuticIds;
	}
	public void setTherapeuticIds(String therapeuticIds) {
		this.therapeuticIds = therapeuticIds;
	}
	public String getStudyShortName() {
		return studyShortName;
	}
	public void setStudyShortName(String studyShortName) {
		this.studyShortName = studyShortName;
	}

	public String getStudyBannerURL() {
		return studyBannerURL;
	}
	public void setStudyBannerURL(String studyBannerURL) {
		this.studyBannerURL = studyBannerURL;
	}
	public String getProtocolNumber() {
		return protocolNumber;
	}
	public void setProtocolNumber(String protocolNumber) {
		this.protocolNumber = protocolNumber;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public Integer getTotalVisits() {
		return totalVisits;
	}
	public void setTotalVisits(Integer totalVisits) {
		this.totalVisits = totalVisits;
	}

	public Integer getPatientDuration() {
		return patientDuration;
	}
	public void setPatientDuration(Integer patientDuration) {
		this.patientDuration = patientDuration;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getDocAggreementUrl() {
		return docAggreementUrl;
	}
	public void setDocAggreementUrl(String docAggreementUrl) {
		this.docAggreementUrl = docAggreementUrl;
	}
	public List<TrialSiteModel> getTrailSites() {
		return trailSites;
	}
	public void setTrailSites(List<TrialSiteModel> trailSites) {
		this.trailSites = trailSites;
	}
	public String getTrailSiteName() {
		return trailSiteName;
	}
	public void setTrailSiteName(String trailSiteName) {
		this.trailSiteName = trailSiteName;
	}
	public String getPrincipalInvestigator() {
		return principalInvestigator;
	}
	public void setPrincipalInvestigator(String principalInvestigator) {
		this.principalInvestigator = principalInvestigator;
	}
	public String getCoPrincipalInvestigator() {
		return coPrincipalInvestigator;
	}
	public void setCoPrincipalInvestigator(String coPrincipalInvestigator) {
		this.coPrincipalInvestigator = coPrincipalInvestigator;
	}
	public Integer getTrailSiteId() {
		return trailSiteId;
	}
	public void setTrailSiteId(Integer trailSiteId) {
		this.trailSiteId = trailSiteId;
	}
	public String getStudyStatus() {
		return studyStatus;
	}
	public void setStudyStatus(String studyStatus) {
		this.studyStatus = studyStatus;
	}
	public String getIcfUrl() {
		return icfUrl;
	}
	public void setIcfUrl(String icfUrl) {
		this.icfUrl = icfUrl;
	}
	public String getPisUrl() {
		return pisUrl;
	}
	public void setPisUrl(String pisUrl) {
		this.pisUrl = pisUrl;
	}
	public Integer getDoseDuration() {
		return doseDuration;
	}
	public void setDoseDuration(Integer doseDuration) {
		this.doseDuration = doseDuration;
	}
	
	
	
}
