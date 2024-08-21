package com.clinivapps.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "c_products")
public class ProductsEntity {

	private Integer productId;
	private Integer studyId;
	private String productTitle;
	private String productDesc;
	private String productImage;
	private String composition;
	private String approvedIndication;
	private String usageDescription;
	private Integer unitsPerDose;
	private Date createdDate;

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", nullable = false)
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	@Column(name = "STUDY_ID")
	public Integer getStudyId() {
		return studyId;
	}

	public void setStudyId(Integer studyId) {
		this.studyId = studyId;
	}

	@Column(name = "GENERIC_COMPOSITION")
	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}
	
	@Column(name = "APPROVED_INDICATIONS")
	public String getApprovedIndication() {
		return approvedIndication;
	}

	public void setApprovedIndication(String approvedIndication) {
		this.approvedIndication = approvedIndication;
	}
	
	@Column(name = "TITLE")
	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	
	@Column(name = "DESCRIPTION")
	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	
	@Column(name = "IMAGE_URL")
	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", length = 19)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name = "USAGE_DESCRIPTION")
	public String getUsageDescription() {
		return usageDescription;
	}

	public void setUsageDescription(String usageDescription) {
		this.usageDescription = usageDescription;
	}

	public ProductsEntity() {
	}
	@Column(name = "UNITS_PER_DOSE")
	public Integer getUnitsPerDose() {
		return unitsPerDose;
	}

	public void setUnitsPerDose(Integer unitsPerDose) {
		this.unitsPerDose = unitsPerDose;
	}

	

}


