package com.clinivapps.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "c_site_users")
public class SiteUsersEntity implements java.io.Serializable {

	private Integer id;
	private Integer userId;
	private Integer siteId;
	private Integer thereapeuticId;
	

	public SiteUsersEntity() {
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "SITE_ID")
	public Integer getSiteId() {
		return siteId;
	}
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	@Column(name = "USER_ID")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}	
	@Column(name = "THERAPEUTIC_ID")
	public Integer getThereapeuticId() {
		return thereapeuticId;
	}
	public void setThereapeuticId(Integer thereapeuticId) {
		this.thereapeuticId = thereapeuticId;
	}
	

}
