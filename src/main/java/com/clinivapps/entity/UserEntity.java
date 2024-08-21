package com.clinivapps.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "c_user", uniqueConstraints = { @UniqueConstraint(columnNames = "ID"), 
		@UniqueConstraint(columnNames = "DP_ID") })
public class UserEntity implements java.io.Serializable {

	private Integer userId;
	private UserSecurityEntity security;
	private Integer role;
	private String firstName;
	private String lastName;
	private String mobileNo;
	private String emailId;
	private String status;
	private String title;
	private DisplayPictureEntity displayPicture;	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="SECURITY_ID",nullable=false,unique=true)
	public UserSecurityEntity getSecurity() {
		return this.security;
	}

	public void setSecurity(UserSecurityEntity security) {
		this.security = security;
	}

	@Column(name = "ROLE_ID")
	public Integer getRole() {
		return this.role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
	@Column(name = "FIRST_NAME")
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	

	@Column(name = "LAST_NAME")
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	@Column(name = "TITLE")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

	@Column(name = "MOBILE_NO")
	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Column(name = "EMAIL_ID")
	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name="DP_ID",nullable=false,unique=true)
	public DisplayPictureEntity getDisplayPicture() {
		return this.displayPicture;
	}

	public void setDisplayPicture(DisplayPictureEntity dp) {
		this.displayPicture = dp;
	}
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


	
}


