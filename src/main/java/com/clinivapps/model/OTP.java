package com.clinivapps.model;

import java.io.*;

public class OTP implements Serializable
{
    private static final long serialVersionUID = 2542911871310426635L;
    private Integer id;
    private String otp;
    private String type;
    private String verificationId;
    private Integer securityId;
    private String status;
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(final String status) {
        this.status = status;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(final Integer id) {
        this.id = id;
    }
    
    public String getOtp() {
        return this.otp;
    }
    
    public void setOtp(final String otp) {
        this.otp = otp;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    public String getVerificationId() {
        return this.verificationId;
    }
    
    public void setVerificationId(final String verificationId) {
        this.verificationId = verificationId;
    }
    
    public Integer getSecurityId() {
        return this.securityId;
    }
    
    public void setSecurityId(final Integer securityId) {
        this.securityId = securityId;
    }
}