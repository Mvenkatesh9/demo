package com.clinivapps.model;

import java.util.Date;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

public class CRFModel {
	private Integer crfId;
	private String studyName;
	private Integer productId;
	private String startDate;
	private String endDate;
	private Date createdDate;
	private String therapeuticIds;
	private String therapeuticNames;
	private String productName;
	private String studyShortName;
	private String trailSiteId;
	private String trailSiteName;
	private Integer invistigatorDocId;
	
	
	public String getTrailSiteName() {
		return trailSiteName;
	}
	public void setTrailSiteName(String trailSiteName) {
		this.trailSiteName = trailSiteName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getTherapeuticNames() {
		return therapeuticNames;
	}
	public void setTherapeuticNames(String therapeuticNames) {
		this.therapeuticNames = therapeuticNames;
	}
	public Integer getCrfId() {
		return crfId;
	}
	public void setCrfId(Integer crfId) {
		this.crfId = crfId;
	}
	public String getStudyName() {
		return studyName;
	}
	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
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
	
	public String getTrailSiteId() {
		return trailSiteId;
	}
	public void setTrailSiteId(String trailSiteId) {
		this.trailSiteId = trailSiteId;
	}
	
	public Integer getInvistigatorDocId() {
		return invistigatorDocId;
	}
	public void setInvistigatorDocId(Integer invistigatorDocId) {
		this.invistigatorDocId = invistigatorDocId;
	}
	
}
