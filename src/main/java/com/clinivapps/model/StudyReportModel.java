package com.clinivapps.model;

import java.util.List;

public class StudyReportModel {

	private List<TrialSiteModel> siteList;
	private List<ReportPatientModel> patientList;
	private Integer siteCount;
	private Integer patCount;
	

	public List<TrialSiteModel> getSiteList() {
		return siteList;
	}
	public void setSiteList(List<TrialSiteModel> siteList) {
		this.siteList = siteList;
	}
	public List<ReportPatientModel> getPatientList() {
		return patientList;
	}
	public void setPatientList(List<ReportPatientModel> patientList) {
		this.patientList = patientList;
	}
	public Integer getSiteCount() {
		return siteCount;
	}
	public void setSiteCount(Integer siteCount) {
		this.siteCount = siteCount;
	}
	public Integer getPatCount() {
		return patCount;
	}
	public void setPatCount(Integer patCount) {
		this.patCount = patCount;
	}

	
	
}
