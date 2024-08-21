package com.clinivapps.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class ProtocolModel {
	private Integer id;
	private Integer crfId;
	private Integer companyId;
	private String protocolId;
	private Date createdDate;
	private String fileUrl;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCrfId() {
		return crfId;
	}
	public void setCrfId(Integer crfId) {
		this.crfId = crfId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getProtocolId() {
		return protocolId;
	}
	public void setProtocolId(String protocolId) {
		this.protocolId = protocolId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	
	
}
