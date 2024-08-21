package com.clinivapps.model;

import java.io.*;

public class LoginResponseModel implements Serializable
{
    protected static final long serialVersionUID = -1740566795590994197L;
    private Integer userId;
    private String status;
    private String displayName;
    private String displayPictureUrl;
    private Integer roleId;
    private Integer otherId;
    private Integer higherUserId;
    
    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(final Integer userId) {
        this.userId = userId;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(final String status) {
        this.status = status;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }
    
    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayPictureUrl() {
        return this.displayPictureUrl;
    }
    
    public void setDisplayPictureUrl(final String displayPictureUrl) {
        this.displayPictureUrl = displayPictureUrl;
    }
    
    public Integer getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(final Integer roleId) {
        this.roleId = roleId;
    }

	public Integer getOtherId() {
		return otherId;
	}

	public void setOtherId(Integer otherId) {
		this.otherId = otherId;
	}

	public Integer getHigherUserId() {
		return higherUserId;
	}

	public void setHigherUserId(Integer higherUserId) {
		this.higherUserId = higherUserId;
	}

	
    
}

