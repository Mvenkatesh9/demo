package com.clinivapps.entity;

import java.io.*;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "c_otp")
public class OTPEntity implements Serializable
{
    private Integer otpId;
    private String otp;
    private String type;
    private Date validUpto;
    private String verificationId;
    private Integer securityId;
    private String status;
    
    @Column(name = "STATUS")
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(final String status) {
        this.status = status;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OTP_ID", nullable = false)
    public Integer getOtpId() {
        return this.otpId;
    }
    
    public void setOtpId(final Integer otpId) {
        this.otpId = otpId;
    }
    
    @Column(name = "OTP")
    public String getOtp() {
        return this.otp;
    }
    
    public void setOtp(final String otp) {
        this.otp = otp;
    }
    
    @Column(name = "TYPE")
    public String getType() {
        return this.type;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "VALID_UPTO", length = 19)
    public Date getValidUpto() {
        return this.validUpto;
    }
    
    public void setValidUpto(final Date validUpto) {
        this.validUpto = validUpto;
    }
    
    @Column(name = "VERIFICATION_ID")
    public String getVerificationId() {
        return this.verificationId;
    }
    
    public void setVerificationId(final String verificationId) {
        this.verificationId = verificationId;
    }
    
    @Column(name = "SECURITY_ID", nullable = false)
    public Integer getSecurityId() {
        return this.securityId;
    }
    
    public void setSecurityId(final Integer securityId) {
        this.securityId = securityId;
    }
}