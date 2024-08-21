
package com.clinivapps.controller.rest;

import com.clinivapps.model.QuestionModel;

import java.util.Calendar;
import java.util.List;
import com.clinivapps.model.SectionModel;
import com.clinivapps.model.UpdateCRFQuestionModel;
import com.clinivapps.model.VisitSectionModel;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.clinivapps.model.LogReportModel;
import com.clinivapps.model.AppResponse;
import com.clinivapps.model.CRFQuestionModel;
import org.springframework.web.bind.annotation.RequestBody;
import com.clinivapps.service.MasterDataService;
import com.clinivapps.service.QuestionService;
import com.clinivapps.service.SectionService;
import com.clinivapps.service.StudyDoctorsService;
import com.clinivapps.service.StudyPatientsService;
import com.clinivapps.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import com.clinivapps.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/question" })
public class QuestionsRestController{
	public static final String GET_ALL_SECTIONS_BY_STUDY = "/getAllSectionsById";
	public static final String GET_ALL_QUESTIONS_BYID = "/getAllQuestionsById";
	public static final String GET_FORM_BY_TYPE = "/getFormByType";
	public static final String GET_QUESTION_DETAILS_BY_ID = "/getQuestionDetailsById";
	public static final String SUBMIT_QUESTIONS = "/submitQuestions";
	public static final String SUBMIT_AE_FORM = "/submitAEForm";
	public static final String SUBMIT_COMMON_MEDICATION_FORM = "/submitMedicationForm";
	public static final String GET_FORMS_BY_PATIENT = "/getUpdatedFormsByPatient";
	public static final String GET_FORMS_BY_PATIENT_DATE = "/getUpdatedFormsByPatientDate";
	public static final String UPDATE_FORM = "/updateForm";
	public static final String GET_FORM_DATA_BY_CATEGORY = "/getUpdatedDataByCategory";
	public static final String SUBMIT_SUBJECT_STATUS = "/submitStatusForm";

	@Autowired
	UserService userService;
	@Autowired
	StudyService studyService;
	@Autowired
	StudyDoctorsService studyDoctorsService;
	@Autowired
	StudyPatientsService studyPatientService;
	@Autowired
	SectionService sectionService;
	@Autowired
	QuestionService questionService;
	@Autowired
	MasterDataService masterDataService;

	@RequestMapping(value = QuestionsRestController.GET_ALL_SECTIONS_BY_STUDY, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getAllSections(@RequestParam("mobileNumber") String mobileNumber, @RequestParam("studyId") Integer studyId, @RequestParam("userId") Integer userId) {
		final AppResponse response = new AppResponse();
		try {

			List<SectionModel> sectionList = sectionService.findByStudyId(studyId);
			response.setResult(sectionList);
			response.setStatus("Success");
			LogReportModel logModel = new LogReportModel();
			logModel.setActivity("STUDY CRF SECTIONS");
			logModel.setCreatedDate(Calendar.getInstance().getTime());
			logModel.setUserId(userId);
			logModel.setDescription("Study CRF Section Details");
			masterDataService.createLogReport(logModel);
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occured getting data");
			response.setStatus("Error");
		}
		return response;
	}
	@RequestMapping(value = QuestionsRestController.GET_ALL_QUESTIONS_BYID, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getAllQuestions(@RequestParam("sectionId") Integer sectionId,@RequestParam("userId") Integer userId) {
		final AppResponse response = new AppResponse();
		try {
			List<QuestionModel> questionList = questionService.findBySectionId(sectionId);
			if (questionList != null) {
				response.setResult(questionList);
				response.setStatus("Success");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("STUDY CRF QUESTIONS");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userId);
				logModel.setDescription("Study CRF Question Details");
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("Invalid user Id");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occured getting data");
			response.setStatus("Error");
		}
		return response;
	}
	@RequestMapping(value = QuestionsRestController.GET_QUESTION_DETAILS_BY_ID, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getQuestionDetails(@RequestParam("questionId") Integer questionId,@RequestParam("userId") Integer userId) {
		final AppResponse response = new AppResponse();
		try {
			QuestionModel questionList = questionService.findByQuestionId(questionId);
			if (questionList != null) {
				response.setResult(questionList);
				response.setStatus("Success");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("DETAILED QUESTION");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userId);
				logModel.setDescription("Question Details");
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("Invalid user Id");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occured getting data");
			response.setStatus("Error");
		}
		return response;
	}
	@RequestMapping(value = QuestionsRestController.GET_FORM_BY_TYPE, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getFormByType(@RequestParam("type") String type,@RequestParam("userId") Integer userId) {
		final AppResponse response = new AppResponse();
		try {
			Integer sectionId = 0;
			if (type.equals("TREATMENT")) 
				sectionId = 5;
			else if (type.equals("AE")) 
				sectionId = 6;
			else if (type.equals("CONCOMITANT")) 
				sectionId = 7;
			
			List<QuestionModel> questionList = questionService.findBySectionId(sectionId);
			if (questionList != null) {
				response.setResult(questionList);
				response.setStatus("Success");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("STUDY FORM " + type);
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userId);
				logModel.setDescription("Study CRF Question Details");
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("Invalid user Id");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occured getting data");
			response.setStatus("Error");
		}
		return response;
	}
	@RequestMapping(value = QuestionsRestController.SUBMIT_QUESTIONS, method = { RequestMethod.POST })
	@ResponseBody
	public AppResponse submiQuestions(@RequestBody List<CRFQuestionModel> crfQuestionModel) {
		final AppResponse response = new AppResponse();
		try {
			if (crfQuestionModel != null) {
				sectionService.submitQuestions(crfQuestionModel);
				response.setStatus("Success");
				response.setMessage("Data Updated Successfully!");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("CRF SUBMITTED");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(crfQuestionModel.get(0).getUserId());
				logModel.setDescription("CRF Submitted");
				logModel.setTrailParticipant(crfQuestionModel.get(0).getPatientId());
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("Invalid Data!");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occured getting data");
			response.setStatus("Error");
		}
		return response;
	}
	@RequestMapping(value = QuestionsRestController.SUBMIT_AE_FORM, method = { RequestMethod.POST })
	@ResponseBody
	public AppResponse submitAEQuestions(@RequestBody List<CRFQuestionModel> crfQuestionModel) {
		final AppResponse response = new AppResponse();
		try {
			if (crfQuestionModel != null) {
				sectionService.submitAEQuestions(crfQuestionModel);
				response.setStatus("Success");
				response.setMessage("AE Updated Successfully!");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("AE SUBMITTED");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(crfQuestionModel.get(0).getUserId());
				logModel.setDescription("AE Submitted");
				logModel.setTrailParticipant(crfQuestionModel.get(0).getPatientId());
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("Invalid Data!");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occured getting data");
			response.setStatus("Error");
		}
		return response;
	}
	@RequestMapping(value = QuestionsRestController.SUBMIT_SUBJECT_STATUS, method = { RequestMethod.POST })
	@ResponseBody
	public AppResponse submitStatusQuestions(@RequestBody List<CRFQuestionModel> crfQuestionModel) {
		final AppResponse response = new AppResponse();
		try {
			if (crfQuestionModel != null) {
				sectionService.submitAEQuestions(crfQuestionModel);
				response.setStatus("Success");
				response.setMessage("Subject Status Updated Successfully!");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("SUBJECT STATUS SUBMITTED");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(crfQuestionModel.get(0).getUserId());
				logModel.setDescription("Subject Status Submitted");
				logModel.setTrailParticipant(crfQuestionModel.get(0).getPatientId());
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("Invalid Data!");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occured getting data");
			response.setStatus("Error");
		}
		return response;
	}
	@RequestMapping(value = QuestionsRestController.SUBMIT_COMMON_MEDICATION_FORM, method = { RequestMethod.POST })
	@ResponseBody
	public AppResponse submitFormQuestions(@RequestBody List<CRFQuestionModel> crfQuestionModel) {
		final AppResponse response = new AppResponse();
		try {
			if (crfQuestionModel != null) {
				String formTitle = "";
				sectionService.submitCommonQuestions(crfQuestionModel);
				if (crfQuestionModel.get(0).getSectionId() == 5) {
					formTitle = "TREATMENT FORM";
				}
				else if (crfQuestionModel.get(0).getSectionId() == 7) {
					formTitle = "CONCOMITANT MEDICATION FORM";
				}
				response.setStatus("Success");
				response.setMessage("Data Updated Successfully!");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity( formTitle + " SUBMITTED");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(crfQuestionModel.get(0).getUserId());
				logModel.setDescription("Data Submitted Successfully!");
				logModel.setTrailParticipant(crfQuestionModel.get(0).getPatientId());
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("Invalid Data!");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occured getting data");
			response.setStatus("Error");
		}
		return response;
	}
	@RequestMapping(value = QuestionsRestController.GET_FORMS_BY_PATIENT, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getUpdatedFormsByType(@RequestParam("patientId") String patientId, @RequestParam("sectionId") Integer sectionId, @RequestParam("userId") Integer userId) {
		final AppResponse response = new AppResponse();
		try {
			List<SectionModel> sectionList = sectionService.findCategoryFromByPatientSection(patientId, sectionId);
			response.setResult(sectionList);
			response.setStatus("Success");
			LogReportModel logModel = new LogReportModel();
			logModel.setActivity("STUDY CRF UPDATED FORMS");
			logModel.setCreatedDate(Calendar.getInstance().getTime());
			logModel.setUserId(userId);
			logModel.setDescription("Study CRF Forms Updated Details");
			masterDataService.createLogReport(logModel);
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occured getting data");
			response.setStatus("Error");
		}
		return response;
	}
	@RequestMapping(value = QuestionsRestController.GET_FORMS_BY_PATIENT_DATE, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getUpdatedFormsByTypeDate(@RequestParam("patientId") String patientId, @RequestParam("sectionId") Integer sectionId,
			@RequestParam("userId") Integer userId, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
		final AppResponse response = new AppResponse();
		try {
			List<SectionModel> sectionList = sectionService.findCategoryFromByPatientSectionDate(patientId, sectionId, fromDate, toDate);
			response.setResult(sectionList);
			response.setStatus("Success");
			LogReportModel logModel = new LogReportModel();
			logModel.setActivity("STUDY CRF UPDATED FORMS");
			logModel.setCreatedDate(Calendar.getInstance().getTime());
			logModel.setUserId(userId);
			logModel.setDescription("Study CRF Forms Updated Details");
			masterDataService.createLogReport(logModel);
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occured getting data");
			response.setStatus("Error");
		}
		return response;
	}
	
	@RequestMapping(value = QuestionsRestController.UPDATE_FORM, method = { RequestMethod.POST })
	@ResponseBody
	public AppResponse updateForm(@RequestBody List<UpdateCRFQuestionModel> crfQuestionModel) {
		final AppResponse response = new AppResponse();
		try {
			if (crfQuestionModel != null) {
				sectionService.updateForm(crfQuestionModel);
				response.setStatus("Success");
				response.setMessage("Data Updated Successfully!");
			}
			else {
				response.setMessage("Invalid Data!");
				response.setStatus("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occured getting data");
			response.setStatus("Error");
		}
		return response;
	}
	@RequestMapping(value = QuestionsRestController.GET_FORM_DATA_BY_CATEGORY, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getVisitDataByVisitPatient(@RequestParam("patientId")String patientId, @RequestParam("categoryId")Integer categoryId,
			@RequestParam("userId")Integer userId) {
		final AppResponse response = new AppResponse();
		try {
			VisitSectionModel patientVisitData = sectionService.findVisitsByPatientAndCategory(categoryId, patientId);
			if (patientVisitData != null) {
				response.setResult(patientVisitData);
				response.setStatus("Success");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("FORM DETAILED DATA");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userId);
				logModel.setTrailParticipant(patientId);
				logModel.setDescription("Form Data");
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
