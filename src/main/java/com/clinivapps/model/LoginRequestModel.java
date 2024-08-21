package com.clinivapps.model;

import java.io.*;

public class LoginRequestModel implements Serializable
{
    protected static final long serialVersionUID = -1740566795590994197L;
    private String username;
    private String password;
    private String deviceToken;
    private String platform;
    private String roleId;
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(final String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public String getDeviceToken() {
        return this.deviceToken;
    }
    
    public void setDeviceToken(final String deviceToken) {
        this.deviceToken = deviceToken;
    }
    
    public String getPlatform() {
        return this.platform;
    }
    
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
    
}

