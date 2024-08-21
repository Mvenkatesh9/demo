package com.clinivapps.service;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import org.apache.velocity.app.*;
import org.apache.commons.logging.*;
import com.clinivapps.util.*;
import com.clinivapps.model.*;
import org.springframework.mail.javamail.*;
import org.apache.velocity.context.*;
import java.io.*;
import javax.mail.internet.*;
import org.apache.velocity.*;
import java.util.*;
import org.springframework.scheduling.annotation.*;

@Service("emailService")
@EnableAsync
public class EmailService
{
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private VelocityEngine velocityEngine;
    public static final String VERIFY_EMAIL = "Please Verify your Email Address";
    public static final String RESET_PASSWORD = "One Time Password";
    public static final String ACCOUNT_ACTIVATION = "Your Account is now Active";
    public static final String ENROLL_CONFIRMATION = "Enroll Confirmation";
    public static final String FEEDBACK_ACK = "FEEDBACK_ACK";
    public static final String FEEDBACK_ADMIN_ACK = "FEEDBACK_ADMIN_ACK";
    public static final String EMAIL_OTP = "One Time Password";
    public static final String DOCTOR_ACCOUNT_ACTIVATION_INTIMATE = "Verification Process Initiated";
    public static final String SURVEY_REPORT = "SURVEY_REPORT";
    public static final String SAMPLE_REQUEST = "SAMPLE_REQUEST";
    public static final String HELP_SUPPORT = "HELP_SUPPORT";
    private Map<String, String> templateMap;
    private static final Log log;
    
    static {
        log = LogFactory.getLog(EmailService.class);
    }
    
    public EmailService() {
        (this.templateMap = new HashMap<String, String>()).put("Please Verify your Email Address_SUBJECT", "Please Verify your Email Address");
        this.templateMap.put("Please Verify your Email Address_TEMPLATE", "Email_Verification.vm");
        this.templateMap.put("One Time Password_SUBJECT", "Reset your password on cliniv app");
        this.templateMap.put("One Time Password_TEMPLATE", "Email_PasswordReset.vm");
        this.templateMap.put("Your Account is now Active_SUBJECT", "Your Account is activated now");
        this.templateMap.put("Your Account is now Active_TEMPLATE", "Email_P_Activation.vm");
        this.templateMap.put("Enroll Confirmation_SUBJECT", "New Clinical Trail Enrollment Confirmation");
        this.templateMap.put("Enroll Confirmation_TEMPLATE", "Email_Enroll_Confirmation.vm");
        this.templateMap.put("FEEDBACK_ACK_SUBJECT", "Thanks for writing to us");
        this.templateMap.put("FEEDBACK_ACK_TEMPLATE", "Email_Feedback.vm");
        this.templateMap.put("FEEDBACK_ADMIN_ACK_SUBJECT", "Contact request received.");
        this.templateMap.put("FEEDBACK_ADMIN_ACK_TEMPLATE", "Email_Admin_Feedback.vm");
        this.templateMap.put("One Time Password_SUBJECT", "One Time Password");
        this.templateMap.put("One Time Password_TEMPLATE", "Email_OTP.vm");
        this.templateMap.put("Verification Process Initiated_SUBJECT", "Verification Process Initiated");
        this.templateMap.put("Verification Process Initiated_TEMPLATE", "Email_D_Accont_Activation_Intimate.vm");
        this.templateMap.put("SAMPLE_REQUEST_SUBJECT", " Sample Requested by Doctor [Campaign: ");
        this.templateMap.put("SAMPLE_REQUEST_TEMPLATE", "Sample_Request.vm");
        this.templateMap.put("SURVEY_REPORT_TEMPLATE", "SurveyReport.vm");
        this.templateMap.put("SURVEY_REPORT_SUBJECT", "Requested Survey");
        this.templateMap.put("HELP_SUPPORT_SUBJECT", "Help and Supporr");
        this.templateMap.put("HELP_SUPPORT_TEMPLATE", "Email_OTP.vm");
        this.templateMap.put("EMAIL_FROM", ClinIVProperty.getInstance().getProperties("cliniv.email.info.id"));
    }
    
    @Async
    public void sendMail(final Mail mail) {
        try {
            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            helper.setFrom((String)this.templateMap.get("EMAIL_FROM"));
            if (mail.getMailTo() != null && mail.getMailTo().length() > 0) {
                helper.setTo(mail.getMailTo());
                if (mail.getMailBcc() != null && mail.getMailBcc().length() > 0) {
                    helper.setBcc(mail.getMailBcc());
                }
                if (mail.getMailCc() != null && mail.getMailCc().length() > 0) {
                    helper.setCc(mail.getMailCc());
                }
                helper.setSubject(mail.getTemplateName());
                final Template template = this.velocityEngine.getTemplate((String)this.templateMap.get(String.valueOf(mail.getTemplateName()) + "_TEMPLATE"));
                final VelocityContext velocityContext = new VelocityContext();
                final Map<String, String> valueMap = (Map<String, String>)mail.getValueMap();
                valueMap.put("INFOMAILID", ClinIVProperty.getInstance().getProperties("cliniv.email.info.id"));
                if (valueMap != null) {
                    for (final String key : valueMap.keySet()) {
                        velocityContext.put(key, (Object)valueMap.get(key));
                    }
                }
                velocityContext.put("TITLE", (Object)this.templateMap.get(String.valueOf(mail.getTemplateName()) + "_SUBJECT"));
                final StringWriter stringWriter = new StringWriter();
                template.merge((Context)velocityContext, (Writer)stringWriter);
                mimeMessage.setContent((Object)stringWriter.toString(), "text/html");
                this.mailSender.send(mimeMessage);
            }
        }
        catch (Exception e) {
            EmailService.log.error((Object)"Send Email Error ", (Throwable)e);
        }
    }
    
    @Async
    public void sendMultipleMail(final Mail mail) {
        try {
            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            helper.setFrom((String)this.templateMap.get("EMAIL_FROM"));
            if (mail.getMultipleMailTo() != null) {
                helper.setTo(mail.getMultipleMailTo());
                if (mail.getMailBcc() != null && mail.getMailBcc().length() > 0) {
                    helper.setBcc(mail.getMailBcc());
                }
                if (mail.getMailCc() != null && mail.getMailCc().length() > 0) {
                    helper.setCc(mail.getMailCc());
                }
                final Map<String, String> valueMap = (Map<String, String>)mail.getValueMap();
                if (valueMap != null) {
                    helper.setSubject(String.valueOf(this.templateMap.get(new StringBuilder(String.valueOf(mail.getTemplateName())).append("_SUBJECT").toString())) + valueMap.get("campaginName"));
                }
                final Template template = this.velocityEngine.getTemplate((String)this.templateMap.get(String.valueOf(mail.getTemplateName()) + "_TEMPLATE"));
                final VelocityContext velocityContext = new VelocityContext();
                if (valueMap != null) {
                    for (final String key : valueMap.keySet()) {
                        velocityContext.put(key, (Object)valueMap.get(key));
                    }
                }
                velocityContext.put("TITLE", (Object)this.templateMap.get(String.valueOf(mail.getTemplateName()) + "_SUBJECT"));
                final StringWriter stringWriter = new StringWriter();
                template.merge((Context)velocityContext, (Writer)stringWriter);
                mimeMessage.setContent((Object)stringWriter.toString(), "text/html");
                this.mailSender.send(mimeMessage);
            }
        }
        catch (Exception e) {
            EmailService.log.error((Object)"Send Email Error ", (Throwable)e);
        }
    }
    
    @Async
    public void sendMail(final String to, final String bcc, final String cc, final String subject, final String message) {
        try {
            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            helper.setFrom((String)this.templateMap.get("EMAIL_FROM"));
            if (to != null && to.length() > 0) {
                helper.setTo(to);
                if (bcc != null && bcc.length() > 0) {
                    helper.setBcc(bcc);
                }
                if (cc != null && cc.length() > 0) {
                    helper.setCc(cc);
                }
                helper.setSubject(subject);
                mimeMessage.setContent((Object)message, "text/html");
                this.mailSender.send(mimeMessage);
            }
        }
        catch (Exception e) {
            EmailService.log.error((Object)"Send Email Error ", (Throwable)e);
        }
    }
}