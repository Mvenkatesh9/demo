package com.clinivapps.model;

public class ForgotPassword
{
    private Integer userId;
    private Integer empId;
    private String oldPassword;
    private String userName;
    private String otp;
    private String newPassword;
    private String confirmPassword;
    private String verificationType;
    
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(final String userName) {
        this.userName = userName;
    }
    
    public String getOtp() {
        return this.otp;
    }
    
    public void setOtp(final String otp) {
        this.otp = otp;
    }
    
    public String getNewPassword() {
        return this.newPassword;
    }
    
    public void setNewPassword(final String newPassword) {
        this.newPassword = newPassword;
    }
    
    public String getConfirmPassword() {
        return this.confirmPassword;
    }
    
    public void setConfirmPassword(final String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    public String getVerificationType() {
        return this.verificationType;
    }
    
    public void setVerificationType(final String verificationType) {
        this.verificationType = verificationType;
    }
    
    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(final Integer userId) {
        this.userId = userId;
    }
    
    public Integer getEmpId() {
        return this.empId;
    }
    
    public void setEmpId(final Integer empId) {
        this.empId = empId;
    }
    
    public String getOldPassword() {
        return this.oldPassword;
    }
    
    public void setOldPassword(final String oldPassword) {
        this.oldPassword = oldPassword;
    }
}