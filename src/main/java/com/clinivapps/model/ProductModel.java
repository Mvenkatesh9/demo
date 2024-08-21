package com.clinivapps.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class ProductModel {
	private Integer id;
	private Integer companyId;
	private String productTitle;
	private String productDesc;
	private String productImage;
	private String brandName;
	private String batchNumbers;
	private String expiryDates;
	private String composition;
	private String approvedIndication;
	private String usageDescription;
	private String createdDate;
	
	
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBatchNumbers() {
		return batchNumbers;
	}
	public void setBatchNumbers(String batchNumbers) {
		this.batchNumbers = batchNumbers;
	}
	public String getExpiryDates() {
		return expiryDates;
	}
	public void setExpiryDates(String expiryDates) {
		this.expiryDates = expiryDates;
	}
	public String getComposition() {
		return composition;
	}
	public void setComposition(String composition) {
		this.composition = composition;
	}
	public String getApprovedIndication() {
		return approvedIndication;
	}
	public void setApprovedIndication(String approvedIndication) {
		this.approvedIndication = approvedIndication;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getUsageDescription() {
		return usageDescription;
	}
	public void setUsageDescription(String usageDescription) {
		this.usageDescription = usageDescription;
	}
	
	
}

