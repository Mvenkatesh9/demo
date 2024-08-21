
package com.clinivapps.controller.rest;

import com.clinivapps.model.PatientProfileModel;
import com.clinivapps.model.PatientStudyVisitModel;
import com.clinivapps.model.PatientVisitModel;
import com.clinivapps.model.Mail;
import java.util.Map;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import com.clinivapps.model.SMS;
import com.clinivapps.model.SectionModel;
import com.clinivapps.model.StudyMobilePatientModel;
import com.clinivapps.model.StudyPatientModel;
import com.clinivapps.model.VisitSectionModel;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.clinivapps.model.AppResponse;
import com.clinivapps.model.CRFQuestionModel;
import com.clinivapps.model.LabReportModel;
import org.springframework.web.bind.annotation.RequestBody;
import org.slf4j.LoggerFactory;
import com.clinivapps.service.EmailService;
import com.clinivapps.service.OTPService;
import com.clinivapps.service.SMSService;
import com.clinivapps.service.StudyService;
import com.clinivapps.service.StudyPatientsService;
import com.clinivapps.model.LogReportModel;
import com.clinivapps.service.MasterDataService;
import com.clinivapps.dao.DeviceTokenDAO;
import org.springframework.beans.factory.annotation.Autowired;
import com.clinivapps.service.UserService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/patient" })
public class PatientRestController{
	public static final String ENROLL_NEW_PATIENT = "/enrollNewPatient";
	public static final String GET_ALL_PATIENTS_BY_STUDY_ID = "/getAllPatientsByStudyId";
	public static final String GET_ALL_PATIENTS_BY_SITE_ID = "/getAllPatientsBySite";
	public static final String GET_STUDY_BY_MOBILE_NUMBER_AND_PATIENT_ID = "/getStudyByPatientNumberAndPatient";
	public static final String APPROVE_PATIENT_ICF = "/approvePatientICF";
	public static final String UPDATE_PATIENT_LAB = "/updatePatientLab";
	public static final String SUBMIT_MEDICATION = "/submitMedication";
	public static final String GET_PATTIENT_VISITS_BY_SECTION_ID = "/getPatientVisitsBySection";
	public static final String GET_PATTIENT_VISITS_BY_STUDYID = "/getPatientVisitsByStudy";
	public static final String APPROVE_PATTIENT_VISIT = "/approvePatientVisit";
	public static final String GET_PATIENT_DETAILS = "/getPatientDetailsById";
	public static final String GET_ALL_PATIENTS_BY_STUDY_STATUS = "/getAllPatientsByStudyAndStatus";
	public static final String GET_ALL_PATIENTS_BY_STUDY_NURSE = "/getAllPatientsByStudyAndNurse";
	public static final String WITHDRAW_PATIENT = "/withdrawPatient";
	public static final String DISPOSE_PATIENT = "/disposePatient";
	public static final String UPDATE_LAB_REPORTS = "/updateLabReports";
	public static final String GET_LAB_REPORTS_BY_PATIENT = "/getLabReports";
	public static final String GET_PATIENT_DAIRY_BY_PATIENT = "/getPatientDairyByPatient";

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
	StudyPatientsService studyPatientService;
	@Autowired
	StudyService studyService;
	@Autowired
	MasterDataService masterDataService;
	
	static {
		logger = LoggerFactory.getLogger(PatientRestController.class);
	}

	@RequestMapping(value = PatientRestController.ENROLL_NEW_PATIENT, method = { RequestMethod.POST })
	@ResponseBody
	public AppResponse register(@RequestBody final StudyPatientModel studyPatientModel) {
		final AppResponse response = new AppResponse();
		try {
			if (studyPatientModel != null) {
				StudyPatientModel checkUser = studyPatientService.findByPatientAndDoctor(studyPatientModel);
				if (checkUser.getEnrolledUserId() != null) {
					response.setMessage("Patient is already enrolled on " + checkUser.getRegisteredDate());
					response.setStatus("Fail");
				}
				else {
					if (studyPatientModel.getAge() >= 50) {
						studyPatientService.enrollNewPatient(studyPatientModel);
						final SMS sms = new SMS();
						sms.setPhoneNumber(studyPatientModel.getMobileNumber());
						sms.setTemplate("ACCOUNT_ACTIVATION");
						final Map<String, String> valueMap = new HashMap<String, String>();
						valueMap.put("NAME", studyPatientModel.getFullName());
						valueMap.put("LINK", "https://bit.ly/3m7chlm");
						valueMap.put("TRAIL_NAME", "Demo Study");
//						sms.setValueMap(valueMap);
//						this.smsService.sendSMS(sms, "REGISTRATION");
						final Mail mail = new Mail();
						mail.setTemplateName("Enroll Confirmation");
						mail.setMailTo(studyPatientModel.getEmailId());
						mail.setValueMap(valueMap);
						this.emailService.sendMail(mail);
						response.setMessage("Patient Enrolled Successfully!");
						response.setStatus("Success");
						
						LogReportModel logModel = new LogReportModel();
						logModel.setActivity("ENROLLED NEW PATIENT");
						logModel.setCreatedDate(Calendar.getInstance().getTime());
						logModel.setUserId(6);
						logModel.setTrailParticipant(checkUser.getPatientId());
						logModel.setDescription("Enrolled new patient");
						masterDataService.createLogReport(logModel);
					}
					else {
						response.setMessage("Patient Age is not matched as per the Protocol");
						response.setStatus("Fail");
					}
				}
			}
			else {
				response.setMessage("Already Created");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occuured, Please contact customer support");
			response.setStatus("Error");
		}
		return response;
	}
	@RequestMapping(value = PatientRestController.GET_ALL_PATIENTS_BY_STUDY_ID, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getAllPatientsByStudyId(@RequestParam("studyId")Integer studyId, @RequestParam("userId")Integer userId) {
		final AppResponse response = new AppResponse();
		try {
			List<StudyMobilePatientModel> studyPatientModel = studyPatientService.findByStudy(studyId);
			if (studyPatientModel != null) {
				response.setResult(studyPatientModel);
				response.setStatus("Success");
				
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("ALL PATIENTS");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userId);
				logModel.setDescription("All Patients in the Study");
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("Invalid user Id");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occuured, Please contact customer support");
			response.setStatus("Error");
		}
		return response;
	}

	@RequestMapping(value = PatientRestController.GET_ALL_PATIENTS_BY_SITE_ID, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getAllPatientsByStudyDocId(@RequestParam("siteId")Integer siteId, @RequestParam("studyId")Integer studyId,
			@RequestParam("userId")Integer userId) {
		final AppResponse response = new AppResponse();
		try {
			List<StudyMobilePatientModel> studyPatientModel = new ArrayList<StudyMobilePatientModel>();
			
				studyPatientModel = studyPatientService.findBySite(siteId);
			
			if (studyPatientModel != null) {
				response.setResult(studyPatientModel);
				response.setStatus("Success");
			}
			else {
				response.setMessage("Invalid user Id");
				response.setStatus("Fail");
			}
			LogReportModel logModel = new LogReportModel();
			logModel.setActivity("ALL PATIENTS");
			logModel.setCreatedDate(Calendar.getInstance().getTime());
			logModel.setUserId(userId);
			logModel.setDescription("All Patients in the Site");
			masterDataService.createLogReport(logModel);
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occuured, Please contact customer support");
			response.setStatus("Error");
		}
		return response;
	}
	@RequestMapping(value = PatientRestController.GET_ALL_PATIENTS_BY_STUDY_NURSE, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getAllPatientsByStudyNurseId(@RequestParam("nurseUserId")Integer nurseUserId, @RequestParam("studyId")Integer studyId ) {
		final AppResponse response = new AppResponse();
		try {
			List<StudyPatientModel> studyPatientModel = studyPatientService.findByStudyAndNurse(nurseUserId, studyId);
			if (studyPatientModel != null) {
				response.setResult(studyPatientModel);
				response.setStatus("Success");
			}
			else {
				response.setMessage("Invalid user Id");
				response.setStatus("Fail");
			}
			LogReportModel logModel = new LogReportModel();
			logModel.setActivity("ALL PATIENTS BY COORDINATOR");
			logModel.setCreatedDate(Calendar.getInstance().getTime());
			logModel.setUserId(nurseUserId);
			logModel.setDescription("All Patients in the Study");
			masterDataService.createLogReport(logModel);
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occuured, Please contact customer support");
			response.setStatus("Error");
		}
		return response;
	}
	@RequestMapping(value = PatientRestController.GET_ALL_PATIENTS_BY_STUDY_STATUS, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getAllPatientsByStudyAndStatus( @RequestParam("status")String status, @RequestParam("studyId")Integer studyId,@RequestParam("userId")Integer userId) {
		final AppResponse response = new AppResponse();
		try {
			List<StudyPatientModel> studyPatientModel = studyPatientService.findByStudyAndStatus(status, studyId);
			if (studyPatientModel != null) {
				response.setResult(studyPatientModel);
				response.setStatus("Success");
			}
			else {
				response.setMessage("Invalid user Id");
				response.setStatus("Fail");
			}
			LogReportModel logModel = new LogReportModel();
			logModel.setActivity("ALL PATIENTS BY STUDY");
			logModel.setCreatedDate(Calendar.getInstance().getTime());
			logModel.setUserId(userId);
			logModel.setDescription("All Patients in the Study");
			masterDataService.createLogReport(logModel);
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occuured, Please contact customer support");
			response.setStatus("Error");
		}
		return response;
	}

	@RequestMapping(value = PatientRestController.GET_STUDY_BY_MOBILE_NUMBER_AND_PATIENT_ID, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getAllStudyDataByPatientNumber(@RequestParam("mobileNumber")String mobileNumber,@RequestParam("patientId")String patientId ) {
		final AppResponse response = new AppResponse();
		try {
			StudyPatientModel studyPatientModel = studyPatientService.findByPatientMobileNumberAndPatientId(mobileNumber, patientId);
			if (studyPatientModel != null) {
				response.setResult(studyPatientModel);
				response.setStatus("Success");
				String token = "";
				response.setMessage(token);
				
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("STUDY DETAILS BY PATIENT");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(0);
				logModel.setTrailParticipant(patientId);
				logModel.setDescription("Study Details by Patient");
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("Invalid user Id");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occuured, Please contact customer support");
			response.setStatus("Error");
		}
		return response;
	}
	@RequestMapping(value = PatientRestController.APPROVE_PATIENT_ICF, method = { RequestMethod.POST })
	@ResponseBody
	public AppResponse approvePatientICF(@RequestBody LabReportModel labReportModel) {
		PatientRestController.logger.info("Request received for login");
		final AppResponse response = new AppResponse();
		try {
			if (studyPatientService.approvePatientICF(labReportModel)) {
				response.setMessage("ICF Approved Successfully!");
				response.setStatus("Success");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("APPROVE PATIENT ICF");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(labReportModel.getUserId());
				logModel.setTrailParticipant(labReportModel.getPatientId());
				logModel.setDescription("Approve Patient Icf");
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("No Data Found!");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occuured, Please contact customer support");
			response.setStatus("Error");
		}
		return response;
	}
	@RequestMapping(value = PatientRestController.UPDATE_PATIENT_LAB, method = { RequestMethod.POST })
	@ResponseBody
	public AppResponse approvePatientLabData(@RequestBody LabReportModel labReportModel) {
		final AppResponse response = new AppResponse();
		try {
			if (studyPatientService.approvePatientLabData(labReportModel)) {
				response.setMessage("Lab Report Added Successfully!");
				response.setStatus("Success");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("UPDATE PATIENT LAB DATA");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(labReportModel.getUserId());
				logModel.setTrailParticipant(labReportModel.getPatientId());
				logModel.setDescription("Update Patient Lab Data");
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("No Data Found!");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occuured, Please contact customer support");
			response.setStatus("Error");
		}
		return response;
	}
	@RequestMapping(value = PatientRestController.UPDATE_LAB_REPORTS, method = { RequestMethod.POST })
	@ResponseBody
	public AppResponse updateLabReports(@RequestBody LabReportModel labReportModel) {
		final AppResponse response = new AppResponse();
		try {
			if (studyPatientService.updateLabReport(labReportModel)) {
				response.setMessage("Lab Report Updated Successfully!");
				response.setStatus("Success");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("UPDATE LAB REPORT");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(labReportModel.getUserId());
				logModel.setTrailParticipant(labReportModel.getPatientId());
				logModel.setDescription("Lab Report Added");
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("No Data Found!");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occuured, Please contact customer support");
			response.setStatus("Error");
		}
		return response;
	}
	
	@RequestMapping(value = PatientRestController.GET_PATTIENT_VISITS_BY_SECTION_ID, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getVisitDataBySectionPatient(@RequestParam("patientId")String patientId, @RequestParam("sectionId")Integer sectionId,
			@RequestParam("visitId")Integer visitId,@RequestParam("userId")Integer userId) {
		final AppResponse response = new AppResponse();
		try {
			VisitSectionModel patientVisitData = studyPatientService.findVisitsByPatientAndSection(sectionId, patientId, visitId);
			if (patientVisitData != null) {
				response.setResult(patientVisitData);
				response.setStatus("Success");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("PATIENT VISITS");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userId);
				logModel.setTrailParticipant(patientId);
				logModel.setDescription("Patient Visits");
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("Invalid user Id");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occuured, Please contact customer support");
			response.setStatus("Error");
		}
		return response;
	}
	@RequestMapping(value = PatientRestController.GET_PATTIENT_VISITS_BY_STUDYID, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getVisitDataByStudyPatient(@RequestParam("patientId")String patientId, @RequestParam("studyId")Integer studyId,@RequestParam("userId")Integer userId) {
		final AppResponse response = new AppResponse();
		try {
			List<PatientStudyVisitModel> patientVisitData = studyPatientService.findVisitDataByPatientAndStudyId(studyId, patientId);
			if (patientVisitData != null) {
				response.setResult(patientVisitData);
				response.setStatus("Success");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("PATIENT VISITS BY STUDY");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userId);
				logModel.setTrailParticipant(patientId);
				logModel.setDescription("Patient Visits By Study");
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("Invalid user Id");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occuured, Please contact customer support");
			response.setStatus("Error");
		}
		return response;
	}
	@RequestMapping(value = PatientRestController.GET_LAB_REPORTS_BY_PATIENT, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getLabReportsByPatient(@RequestParam("patientId")String patientId,@RequestParam("userId")Integer userId) {
		final AppResponse response = new AppResponse();
		try {
			List<LabReportModel> patientLabData = studyPatientService.getLabReportsByPatient(patientId);
			if (patientLabData != null) {
				response.setResult(patientLabData);
				response.setStatus("Success");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("PATIENT LAB REPORTS");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userId);
				logModel.setTrailParticipant(patientId);
				logModel.setDescription("Patient Lab Reports");
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("Invalid Data");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occuured, Please contact customer support");
			response.setStatus("Error");
		}
		return response;
	}
	
	@RequestMapping(value = PatientRestController.APPROVE_PATTIENT_VISIT, method = { RequestMethod.POST })
	@ResponseBody
	public AppResponse approvePatientVisit(@RequestBody PatientVisitModel patientVisitModel) {
		final AppResponse response = new AppResponse();
		try {
			if (studyPatientService.approvePatientVisit(patientVisitModel)) {
				response.setMessage("Visit Approved Successfully!");
				response.setStatus("Success");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("APPROVE PATIENT VISIT");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(patientVisitModel.getUserId());
				logModel.setTrailParticipant(patientVisitModel.getPatientId());
				logModel.setDescription("Approve Patient Visit");
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("Already Approved");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occuured, Please contact customer support");
			response.setStatus("Error");
		}
		return response;
	}
	@RequestMapping(value = PatientRestController.GET_PATIENT_DETAILS, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getPatientDetailsById(@RequestParam("patientId")String patientId,@RequestParam("userId")Integer userId) {
		final AppResponse response = new AppResponse();
		try {
			PatientProfileModel profileModel = studyPatientService.findByPatientId(patientId);
			if (profileModel != null) {
				response.setResult(profileModel);
				response.setStatus("Success");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("PATIENT DETAILS");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userId);
				logModel.setTrailParticipant(patientId);
				logModel.setDescription("Patient Details");
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("Invalid user Id");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occuured, Please contact customer support");
			response.setStatus("Error");
		}
		return response;
	}
	@RequestMapping(value = PatientRestController.WITHDRAW_PATIENT, method = { RequestMethod.POST })
	@ResponseBody
	public AppResponse withdrawPatient(@RequestBody List<CRFQuestionModel> crfQuestionModel) {
		final AppResponse response = new AppResponse();
		try {
			if (studyPatientService.terminatePatient(crfQuestionModel)) {
				response.setMessage("Patient Dropped Out Successfully!");
				response.setStatus("Success");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("WITHDRAWL PATIENT");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(crfQuestionModel.get(0).getUserId());
				logModel.setTrailParticipant(crfQuestionModel.get(0).getPatientId());
				logModel.setDescription("Withdrawl Patient");
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("Invalid user Id");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occuured, Please contact customer support");
			response.setStatus("Error");
		}
		return response;
	}
	@RequestMapping(value = PatientRestController.DISPOSE_PATIENT, method = { RequestMethod.POST })
	@ResponseBody
	public AppResponse disposePatient(@RequestBody List<CRFQuestionModel> crfQuestionModel) {
		final AppResponse response = new AppResponse();
		try {
			if (studyPatientService.disposePatient(crfQuestionModel)) {
				response.setMessage("Patient Disposed Successfully!");
				response.setStatus("Success");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("DIPOSE PATIENT");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(crfQuestionModel.get(0).getUserId());
				logModel.setTrailParticipant(crfQuestionModel.get(0).getPatientId());
				logModel.setDescription("Diposition of Patient Updated");
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("Invalid user Id");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occuured, Please contact customer support");
			response.setStatus("Error");
		}
		return response;
	}
	@RequestMapping(value = PatientRestController.GET_PATIENT_DAIRY_BY_PATIENT, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getPatientDataByStudyMonth(@RequestParam("patientId")String patientId, @RequestParam("fromDate")String fromDate, 
			@RequestParam("toDate")String toDate,@RequestParam("userId")Integer userId) {
		final AppResponse response = new AppResponse();
		try {
			List<SectionModel> patientDairyList = studyPatientService.getPatientDairyDataBydates(patientId, fromDate, toDate);
			if (patientDairyList != null) {
				response.setResult(patientDairyList);
				response.setStatus("Success");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("PATIENT DIARY DATA");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userId);
				logModel.setTrailParticipant(patientId);
				logModel.setDescription("Patient Diary Data");
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("Invalid user Id");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occuured, Please contact customer support");
			response.setStatus("Error");
		}
		return response;
	}
}
