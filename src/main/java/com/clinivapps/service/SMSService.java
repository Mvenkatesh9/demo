package com.clinivapps.service;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import org.apache.commons.logging.*;
import com.clinivapps.model.*;
import org.springframework.web.client.*;
import java.io.*;
import com.clinivapps.util.*;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.*;
import org.springframework.http.*;

@Service("smsService")
public class SMSService
{
    public static final String VERIFY_NO = "VERIFY_NO";
    public static final String RESET_PASSWORD = "RESET_PASSWORD";
    public static final String ACCOUNT_ACTIVATION = "ACCOUNT_ACTIVATION";
    public static final String ENROLL_CONFIRMAION = "ENROLL_CONFIRMATION";
    public static final String NEW_APPOINTMENT = "NEW_APPOINTMENT";
    public static final String CONFIRM_APPOINTMENT = "CONFIRM_APPOINTMENT";
    public static final String FEEDBACK = "FEEDBACK";
    public static final String NOTIFICATION = "NOTIFICATION";
    public static final String INVITE = "INVITE";
    private Map<String, String> templateMap;
    private static final Log log;
   
    
    static {
        log = LogFactory.getLog(SMSService.class);
    }
    
    public SMSService() {
        (templateMap = new HashMap<String, String>()).put("VERIFY_NO_TEMPLATE", "Mobile_Verification.vm");
        templateMap.put("RESET_PASSWORD_TEMPLATE", "Mobile_PasswordReset.vm");
        templateMap.put("ACCOUNT_ACTIVATION_TEMPLATE", "Mobile_Activation.vm");
        templateMap.put("ENROLL_CONFIRMATION_TEMPLATE", "Mobile_ConfirmStudy.vm");
        templateMap.put("NEW_APPOINTMENT_TEMPLATE", "Mobile_NewAppointment.vm");
        templateMap.put("CONFIRM_APPOINTMENT_TEMPLATE", "Mobile_ConfirmAppointment.vm");
        templateMap.put("FEEDBACK_TEMPLATE", "Mobile_Feedback.vm");
        templateMap.put("NOTIFICATION_TEMPLATE", "Mobile_Notification.vm");
        templateMap.put("INVITE_TEMPLATE", "Mobile_Invite.vm");
    }
    
    public void sendSMS(SMS sms, String type) {
        try {
        	String messageStr = "";
        	if (type.equals("OTP")) {
            	messageStr = "Dear " + sms.getValueMap().get("NAME") + " your One-Time Password is " + sms.getValueMap().get("OTP") + " Please note that this is valid only for the next 30 minutes.";
			}
        	else if (type.equals("REGISTRATION")) {
            	messageStr = "Dear " + sms.getValueMap().get("NAME") + " You are enrolled into to new Study. To get App click here. " + sms.getValueMap().get("LINK");
			}
			String mobileNumber = "+91" + sms.getPhoneNumber();
			log.info(("Msg: " + messageStr));
 	       	
//	          Twilio.init(ClinIVProperty.getInstance().getProperties("sms.accountSID"), ClinIVProperty.getInstance().getProperties("sms.token"));
//	          Message message = Message
//	        	      .creator(new PhoneNumber(mobileNumber), new PhoneNumber(ClinIVProperty.getInstance().getProperties("sms.number")),
//	        	        "ClinIV")
//	        	      .setBody(messageStr)
//	        	      .create();
//	          System.out.println(message.getSid());
        }
        catch (Exception e) {
            SMSService.log.error((Object)"Send SMS failed", (Throwable)e);
        }
    }
}

