package com.clinivapps.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "c_user_security")
public class UserSecurityEntity implements java.io.Serializable {

	private Integer userSecId;
	private String loginId;
	private String password;
	private Date createdDate;
	private String status;
	private String type;
	private Date lastLoginDate;
	

	public UserSecurityEntity() {
	}

	public UserSecurityEntity(String loginId, String password, Date createdDate,
			String status,String type) {
		this.loginId = loginId;
		this.password = password;
		this.createdDate = createdDate;
		this.status = status;
		this.type = type;
		
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", nullable = false)
	public Integer getUserSecId() {
		return this.userSecId;
	}

	public void setUserSecId(Integer userSecId) {
		this.userSecId = userSecId;
	}

	@Column(name = "LOGIN_ID", unique=true)
	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", length = 19)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	@Column(name = "STATUS")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_LOGIN_DATE", length = 19)
	public Date getLastLoginDate() {
		return this.lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}


}
