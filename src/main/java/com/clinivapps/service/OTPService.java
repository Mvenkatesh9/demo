package com.clinivapps.service;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import com.clinivapps.dao.*;
import org.springframework.beans.factory.annotation.*;
import org.apache.commons.logging.*;
import com.clinivapps.model.*;
import com.clinivapps.entity.*;
import java.util.*;
import com.clinivapps.util.*;

@Service("otpService")
@Transactional
public class OTPService
{
    @Autowired
    OTPDAO otpdao;
    private static final Log log;
    
    static {
        log = LogFactory.getLog(OTPService.class);
    }
    
    public OTP verifyOTP(final OTP otp) {
        final OTPEntity otpEntity = otpdao.findByVerificationId(otp.getVerificationId());
        if (otpEntity != null && "WAITING".equals(otpEntity.getStatus())) {
            if (otp.getType().equals(otpEntity.getType()) && PasswordProtector.encrypt(otp.getOtp()).equals(otpEntity.getOtp())) {
                if (otpEntity.getValidUpto().getTime() > System.currentTimeMillis()) {
                    otp.setStatus("VERIFIED");
                }
                else {
                    otp.setStatus("EXPIRED");
                }
                otpEntity.setStatus(otp.getStatus());
                otpdao.merge(otpEntity);
            }
            else {
                otp.setStatus("INVALID");
            }
        }
        else {
            otp.setStatus("INVALID");
        }
        return otp;
    }
    
    public OTP createOTP(final OTP otp) {
        final OTPEntity otpEntity = new OTPEntity();
        otpEntity.setSecurityId(otp.getSecurityId());
        otpEntity.setVerificationId(otp.getVerificationId());
        otpEntity.setType(otp.getType());
        final Calendar calendar = Calendar.getInstance();
        if ("EMAIL".equals(otp.getType())) {
            calendar.add(2, 1);
            otpEntity.setValidUpto(new Date(calendar.getTimeInMillis()));
            otp.setOtp(PasswordProtector.encrypt(OTPUtil.generateToken()));
            otpEntity.setOtp(otp.getOtp());
        }
        else {
            calendar.add(12, 30);
            otpEntity.setValidUpto(new Date(calendar.getTimeInMillis()));
            otp.setOtp(PasswordProtector.encrypt(OTPUtil.generateIntToken()));
            otpEntity.setOtp(otp.getOtp());
        }
        otpEntity.setStatus("WAITING");
        otpdao.persist(otpEntity);
        return otp;
    }
    
    public OTP reCreateOTP(OTP otp) {
        final OTPEntity otpEntity = otpdao.findByVerificationId(otp.getVerificationId());
        if (otpEntity != null && otpEntity.getOtpId() != null) {
            otpEntity.setVerificationId(otp.getVerificationId());
            otpEntity.setType(otp.getType());
            final Calendar calendar = Calendar.getInstance();
            if ("EMAIL".equals(otp.getType())) {
                calendar.add(2, 1);
                otpEntity.setValidUpto(new Date(calendar.getTimeInMillis()));
                otp.setOtp(PasswordProtector.encrypt(OTPUtil.generateToken()));
                otpEntity.setOtp(otp.getOtp());
            }
            else {
                calendar.add(12, 30);
                otpEntity.setValidUpto(new Date(calendar.getTimeInMillis()));
                otp.setOtp(PasswordProtector.encrypt(OTPUtil.generateIntToken()));
                otpEntity.setOtp(otp.getOtp());
            }
            otpEntity.setStatus("WAITING");
            otpEntity.setSecurityId(otp.getSecurityId());
            otpdao.merge(otpEntity);
        }
        else {
            otp = createOTP(otp);
        }
        return otp;
    }
}