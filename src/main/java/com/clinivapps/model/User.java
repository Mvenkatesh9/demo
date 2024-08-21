package com.clinivapps.model;

import java.io.Serializable;
import com.clinivapps.entity.UserSecurityEntity;

public class User extends UserSecurityContext implements Serializable{
    private static final long serialVersionUID = 2542911871310426635L;
    private UserSecurityEntity security;
    private Integer roleId;
    private String roleName;
    private String firstName;
    private String lastName;
    private String title;
    private String gender;
    private String dob;
    private String mobileNo;
    private String emailId;
    private String status;
    private String password;
    private Integer userId;
    private String type;
    private String createdDate;
    private String displayPicture;
    private Integer userSecurityId;
    
    public String getType() {
        return this.type;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(final Integer userId) {
        this.userId = userId;
    }
    
    public Integer getUserSecurityId() {
        return this.userSecurityId;
    }
    
    public void setUserSecurityId(final Integer userSecurityId) {
        this.userSecurityId = userSecurityId;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(final String status) {
        this.status = status;
    }
    
    public Integer getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(final Integer roleId) {
        this.roleId = roleId;
    }
    
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(final String roleName) {
        this.roleName = roleName;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public String getGender() {
        return this.gender;
    }
    
    public void setGender(final String gender) {
        this.gender = gender;
    }
    
    public String getDob() {
        return this.dob;
    }
    
    public void setDob(final String dob) {
        this.dob = dob;
    }
    
    public String getMobileNo() {
        return this.mobileNo;
    }
    
    public void setMobileNo(final String mobileNo) {
        this.mobileNo = mobileNo;
    }
    
    public String getEmailId() {
        return this.emailId;
    }
    
    public void setEmailId(final String emailId) {
        this.emailId = emailId;
    }
    
    public UserSecurityEntity getSecurity() {
        return this.security;
    }
    
    public void setSecurity(final UserSecurityEntity security) {
        this.security = security;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }

	public String getDisplayPicture() {
		return displayPicture;
	}

	public void setDisplayPicture(String displayPicture) {
		this.displayPicture = displayPicture;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

    
	
}
