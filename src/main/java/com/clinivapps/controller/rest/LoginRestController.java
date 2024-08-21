
package com.clinivapps.controller.rest;

import org.springframework.ui.Model;
import com.clinivapps.util.PasswordProtector;
import com.clinivapps.model.OTP;
import com.clinivapps.model.ForgotPassword;
import com.clinivapps.model.Mail;
import java.util.Map;
import java.util.Calendar;
import java.util.HashMap;
import com.clinivapps.model.SMS;
import com.clinivapps.model.SiteUserModel;
import com.clinivapps.model.User;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.clinivapps.model.LoginResponseModel;
import com.clinivapps.model.AppResponse;
import org.springframework.web.bind.annotation.RequestBody;
import com.clinivapps.model.LoginRequestModel;
import org.slf4j.LoggerFactory;
import com.clinivapps.service.EmailService;
import com.clinivapps.service.MasterDataService;
import com.clinivapps.service.OTPService;
import com.clinivapps.service.SMSService;
import com.clinivapps.model.LogReportModel;
import com.clinivapps.dao.DeviceTokenDAO;
import com.clinivapps.dao.TrailSiteDAO;
import com.clinivapps.entity.TrailSiteEntity;

import org.springframework.beans.factory.annotation.Autowired;
import com.clinivapps.service.UserService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/login" })
public class LoginRestController
{
	public static final String FORGOT_PASSWORD = "/forgotPassword";
	public static final String REGISTER = "/register";
	private static final String SEND_SMS_OTP = "/sendSMSOtp";
	private static final String CHANGE_PASSWORD = "/changepassword";
	private static final String VERIFY_MOBILE = "/verifyMobile";
	private static final String AUTHENTICATE = "/authenticate";
	private static final String GET_SITE_PROFILE = "/getSiteProfile";
	private static final Logger logger;
	@Autowired
	UserService userService;
	@Autowired
	DeviceTokenDAO deviceTokenDAO;
	@Autowired
	OTPService otpService;
	@Autowired
	SMSService smsService;
	@Autowired
	EmailService emailService;
	@Autowired
	MasterDataService masterDataService;

	@Autowired
	TrailSiteDAO siteDAO;
	
	static {
		logger = LoggerFactory.getLogger(LoginRestController.class);
	}
	@RequestMapping(value = LoginRestController.AUTHENTICATE, method = { RequestMethod.POST })
	@ResponseBody
	public AppResponse authenticate(@RequestBody final LoginRequestModel loginDetails) {
		final AppResponse response = new AppResponse();
		try {
			if (loginDetails != null) {
				final LoginResponseModel loginResponse = this.userService.isValid(loginDetails);
				if (loginResponse != null) {
					response.setResult(loginResponse);
					String token = "";
					response.setStatus("Success");
					response.setMessage(token);    
					LogReportModel logModel = new LogReportModel();
					logModel.setActivity("MOBILE USER LOGGED IN");
					logModel.setCreatedDate(Calendar.getInstance().getTime());
					logModel.setUserId(loginResponse.getUserId());
					logModel.setDescription("User Logged in form Mobile App");
					masterDataService.createLogReport(logModel);
				}
				else {
					response.setMessage("Invalid Details, Please Try Again!");
					response.setStatus("Fail");
				}
			}
		}
		catch (Exception e) {
			response.setMessage("Please enter valid details");
			response.setStatus("Fail");
		}
		return response;
	}

	@RequestMapping(value = LoginRestController.REGISTER, method = { RequestMethod.POST })
	@ResponseBody
	public AppResponse register(@RequestBody final User user) {
		LoginRestController.logger.info("Request received for login");
		final AppResponse response = new AppResponse();
		try {
			if (user != null) {
				final User checkUser = this.userService.findByMobileNumberAndRoleId(user.getMobileNo(), user.getRoleId());
				if (checkUser != null) {
					response.setMessage("You already have an Account, Please login to continue..");
					response.setStatus("Fail");
				}
				else {
					userService.createUser(user);
					final SMS sms = new SMS();
					sms.setPhoneNumber(user.getMobileNo());
					sms.setTemplate("ACCOUNT_ACTIVATION");
					final Map<String, String> valueMap = new HashMap<String, String>();
					valueMap.put("LOGIN", "Login Id: " + user.getMobileNo() + ", Password: " + user.getPassword());
					valueMap.put("NAME", String.valueOf(user.getFirstName()) + " " + user.getLastName());
					sms.setValueMap(valueMap);
					this.smsService.sendSMS(sms, "REGISTRATION");
					final Mail mail = new Mail();
					mail.setTemplateName("Your Account is now Active");
					mail.setMailTo(user.getEmailId());
					mail.setValueMap(valueMap);
					this.emailService.sendMail(mail);
					response.setMessage("User Created Successfully!");
					response.setStatus("Success");
				}
			}
			else {
				response.setMessage("Already Created");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occuured while Creating User");
			response.setStatus("Error");
		}
		return response;
	}

	@RequestMapping(value = LoginRestController.FORGOT_PASSWORD, method = { RequestMethod.POST })
	@ResponseBody
	public AppResponse forgotPassword(@RequestBody final ForgotPassword forgotPassword) {
		LoginRestController.logger.info("Request received for login");
		final AppResponse response = new AppResponse();
		try {
			final OTP otp = new OTP();
			otp.setOtp(forgotPassword.getOtp());
			final User user = this.userService.findByLoginId(forgotPassword.getUserName());
			if (forgotPassword.getNewPassword() != null && forgotPassword.getConfirmPassword() != null && forgotPassword.getNewPassword().equals(forgotPassword.getConfirmPassword())) {
				if (user != null) {
					otp.setVerificationId(user.getMobileNo());
					otp.setType("MOBILE");
					otpService.verifyOTP(otp);
					if ("VERIFIED".equals(otp.getStatus())) {
						this.userService.updatePassword(forgotPassword, user.getUserSecurityId());
						response.setMessage("Success");
						response.setStatus("Success");
					}
					else if ("EXPIRED".equals(otp.getStatus())) {
						response.setMessage("OTP Expired. Please generate another OTP");
						response.setStatus("Fail");
					}
					else {
						response.setMessage("Invalid OTP");
						response.setStatus("Fail");
					}
				}
				else {
					response.setMessage("Invalid Login Id");
					response.setStatus("Fail");
				}
			}
			else {
				response.setMessage("Password and confirm  password does not match");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occuured while updating password");
			response.setStatus("Error");
		}
		return response;
	}

	@RequestMapping(value = LoginRestController.SEND_SMS_OTP, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse sendSmsOtp(@RequestParam("loginId") final String loginId) {
		final AppResponse response = new AppResponse();
		try {
			final User user = this.userService.findByLoginId(loginId);
			if (user != null) {
				OTP otp = new OTP();
				otp.setVerificationId(user.getMobileNo());
				otp.setType("MOBILE");
				otp.setSecurityId(user.getUserSecurityId());
				otp = this.otpService.reCreateOTP(otp);
				final SMS sms = new SMS();
				sms.setPhoneNumber(user.getMobileNo());
				sms.setTemplate("VERIFY_NO");
				final Map<String, String> valueMap = new HashMap<String, String>();
				valueMap.put("OTP", PasswordProtector.decrypt(otp.getOtp()));
				sms.setValueMap(valueMap);
				this.smsService.sendSMS(sms, "OTP");
				final Mail mail = new Mail();
				mail.setTemplateName("One Time Password");
				mail.setMailTo(user.getEmailId());
				mail.setValueMap(valueMap);
				this.emailService.sendMail(mail);
				response.setMessage("Success");
				response.setStatus("Success");
			}
			else {
				response.setMessage("Invalid user Id");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occuured while creating OTP");
			response.setStatus("Error");
		}
		return response;
	}

	@RequestMapping(value = LoginRestController.CHANGE_PASSWORD, method = { RequestMethod.POST })
	@ResponseBody
	public AppResponse changePassword(final Model model, @RequestBody final ForgotPassword reset) {
		final AppResponse response = new AppResponse();
		try {
			final String status = this.userService.changePassword(reset);
			if (status.equals("Success")) {
				response.setMessage("Password Changed Successfully!");
				response.setStatus("Success");
			}
			else if (status.equals("Worng Password")) {
				response.setMessage("Password that you Entered is not Matched.");
				response.setStatus("Fail");
			}
			else {
				response.setMessage("No Details Found.");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	@RequestMapping(value = LoginRestController.GET_SITE_PROFILE, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getUserProfile(@RequestParam("userId")Integer userId, @RequestParam("siteId")Integer siteId) {
		final AppResponse response = new AppResponse();
		try {
			User user = userService.findByUserId(userId);
			if (user != null) {
				response.setStatus("Success");
				SiteUserModel model = new SiteUserModel();
				model.setEmail(user.getEmailId());
				model.setMobile(user.getMobileNo());
				model.setFullName(user.getFirstName() + " " + user.getLastName());
				TrailSiteEntity siteEnt = siteDAO.findBySiteId(siteId);
				model.setSiteLocation(siteEnt.getLocation());
				model.setSiteName(siteEnt.getSiteName());
				response.setResult(model);
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("SITE USER PROFILE");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userId);
				logModel.setDescription("Site User Profile");
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("Invalid user Id");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occuured while creating OTP");
			response.setStatus("Error");
		}
		return response;
	}
	@RequestMapping(value = LoginRestController.VERIFY_MOBILE, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse verifyMobile(@RequestParam("mobile") final String mobileNumber, @RequestParam("roleId")final Integer roleId) {
		final AppResponse response = new AppResponse();
		try {
			final User user = this.userService.findByMobileNumberAndRoleId(mobileNumber, roleId);
			if (user != null) {
				String encryptedPass = PasswordProtector.encrypt(user.getMobileNo());
				if (!user.getPassword().equals(encryptedPass)) {
					response.setMessage("Verified");
					response.setStatus("Success");
					User userEnt = userService.findByMobileNumberAndRoleId(mobileNumber, roleId);
					LogReportModel logModel = new LogReportModel();
					logModel.setActivity("USER VERIFICATION");
					logModel.setCreatedDate(Calendar.getInstance().getTime());
					logModel.setUserId(userEnt.getUserId());
					logModel.setDescription("User Verification is Active or Not");
					masterDataService.createLogReport(logModel);
				}
				else {
					OTP otp = new OTP();
					otp.setVerificationId(user.getMobileNo());
					otp.setType("MOBILE");
					otp.setSecurityId(user.getUserSecurityId());
					otp = this.otpService.reCreateOTP(otp);
					final SMS sms = new SMS();
					sms.setPhoneNumber(user.getMobileNo());
					sms.setTemplate("VERIFY_NO");
					final Map<String, String> valueMap = new HashMap<String, String>();
					valueMap.put("OTP", PasswordProtector.decrypt(otp.getOtp()));
					sms.setValueMap(valueMap);
					this.smsService.sendSMS(sms, "OTP");
					final Mail mail = new Mail();
					mail.setTemplateName("One Time Password");
					mail.setMailTo(user.getEmailId());
					mail.setValueMap(valueMap);
					this.emailService.sendMail(mail);
					response.setMessage("Not-Verified");
					response.setStatus("Update");
				}
			}
			else {
				response.setMessage("Invalid user Id");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occuured while creating OTP");
			response.setStatus("Error");
		}
		return response;
	}
}
