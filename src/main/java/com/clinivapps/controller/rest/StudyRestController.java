
package com.clinivapps.controller.rest;

import com.clinivapps.model.InclusionExcusionArrayModel;

import com.clinivapps.model.InclusionExcusionModel;

import java.util.Calendar;
import java.util.List;
import com.clinivapps.model.StudyDocumentModel;
import com.clinivapps.model.StudyFaqsModel;
import com.clinivapps.model.StudyModel;
import com.clinivapps.model.StudyVisitModel;
import com.clinivapps.model.User;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.clinivapps.model.LogReportModel;
import com.clinivapps.model.QueryDetailsModel;
import com.clinivapps.model.QueryModel;
import com.clinivapps.model.AppResponse;
import com.clinivapps.service.EmailService;
import com.clinivapps.service.MasterDataService;
import com.clinivapps.service.QueryService;
import com.clinivapps.service.SMSService;
import com.clinivapps.service.StudyDoctorsService;
import com.clinivapps.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import com.clinivapps.service.UserService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping({ "/study" })
public class StudyRestController{
	public static final String GET_ALL_STUDYS_BY_DOCTORID = "/getAllStudysByDocId";
	public static final String GET_ALL_STUDYS_BY_COMPANY_DOCTORID = "/getAllStudysByCompanyDocId";
	public static final String GET_COMPANY_DETAILS_BYID = "/getCompanyDetailsById";
	public static final String GET_STUDY_DETAILS_BYID = "/getStudyDetailsById";
	public static final String GET_INCLUSION_EXCLUSION_DATA = "/getInExDataByStudyId";
	public static final String GET_FAQS_BYID = "/getFaqsById";
	public static final String GET_STUDY_DOCUMENTS_BY_ID = "/getStudyDocumentsById";
	public static final String GET_STUDY_VISITS_BY_ID = "/getStudyVisitsById";
	public static final String GET_QUERIES_BY_SITE = "/getQueriesBySite";
	public static final String GET_QUERIES_BY_SITE_FILTER = "/getQueriesBySiteFilter";
	public static final String GET_QUERY_DETAILS_BY_ID = "/getQueryDetailsById";
	public static final String UPDATE_QUERY_STATUS = "/updateQueryStatus";

	@Autowired
	UserService userService;
	@Autowired
	StudyService studyService;
	@Autowired
	StudyDoctorsService studyDoctorsService;
	@Autowired
	SMSService smsService;
	@Autowired
	EmailService emailService;
	@Autowired
	MasterDataService masterDataService;
	@Autowired
	QueryService queryService;

	@RequestMapping(value = StudyRestController.GET_STUDY_DETAILS_BYID, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getStudyDetailsByStudyId(@RequestParam("studyId") Integer studyId,@RequestParam("userId")Integer userId) {
		final AppResponse response = new AppResponse();
		try {
			StudyModel studyModel = studyService.findByStudyId(studyId);
			if (studyModel != null) {
				response.setResult(studyModel);
				response.setStatus("Success");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("STUDY DETAILS");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userId);
				logModel.setDescription("All Study Details");
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("Invalid Data");
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
	@RequestMapping(value = StudyRestController.GET_INCLUSION_EXCLUSION_DATA, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getInclusionExcusionDataByStudyId(@RequestParam("studyId") Integer studyId,@RequestParam("userId")Integer userId) {
		final AppResponse response = new AppResponse();
		try {
			List<InclusionExcusionModel> inclusionList = studyService.getInclusionData(studyId);
			List<InclusionExcusionModel> exclusionList = studyService.getExclusionData(studyId);
			InclusionExcusionArrayModel arrayModel = new InclusionExcusionArrayModel();
			if (inclusionList != null || exclusionList != null) {
				arrayModel.setInclusionList(inclusionList);
				arrayModel.setExclusionList(exclusionList);
				response.setResult(arrayModel);
				response.setStatus("Success");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("STUDY INCLUSION|EXCLUSION CRETERIA");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userId);
				logModel.setDescription("All Study Inclusion | Exclusion creteria");
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("No Data");
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
	@RequestMapping(value = StudyRestController.GET_FAQS_BYID, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getFaqsById(@RequestParam("studyId") Integer studyId,@RequestParam("userId")Integer userId) {
		final AppResponse response = new AppResponse();
		try {
			List<StudyFaqsModel> faqsList = studyService.getFaqsData(studyId);
			if (faqsList != null) {
				response.setResult(faqsList);
				response.setStatus("Success");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("STUDY FAQS");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userId);
				logModel.setDescription("All Study Faqs");
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("No Data");
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
	@RequestMapping(value = StudyRestController.GET_STUDY_DOCUMENTS_BY_ID, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getStudyDocumentsById(@RequestParam("studyId") Integer studyId,@RequestParam("type") String type,@RequestParam("userId")Integer userId) {
		final AppResponse response = new AppResponse();
		try {
			List<StudyDocumentModel> documentList = studyService.getStudyDocumentData(studyId,type);
			if (documentList != null) {
				response.setResult(documentList);
				response.setStatus("Success");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("STUDY DOCUMENTS");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userId);
				logModel.setDescription("All Study Documents");
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("No Data");
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
	@RequestMapping(value = StudyRestController.GET_STUDY_VISITS_BY_ID, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getStudyVisitsById(@RequestParam("studyId") Integer studyId,@RequestParam("userId")Integer userId) {
		final AppResponse response = new AppResponse();
		try {
			List<StudyVisitModel> visitList = studyService.getStudyVisitsData(studyId);
			if (visitList != null) {
				response.setResult(visitList);
				response.setStatus("Success");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("STUDY VISITS");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userId);
				logModel.setDescription("All Study Visits");
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("No Data");
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
	@RequestMapping(value = StudyRestController.GET_QUERIES_BY_SITE, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getQueriesById(@RequestParam("siteId") Integer siteId,@RequestParam("userId")Integer userId) {
		final AppResponse response = new AppResponse();
		try {
			List<QueryModel> queryList = queryService.findByAssignedId(siteId);
			if (queryList != null) {
				response.setResult(queryList);
				response.setStatus("Success");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("STUDY VISITS");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userId);
				logModel.setDescription("All Study Visits");
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("No Data");
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
	@RequestMapping(value = StudyRestController.GET_QUERIES_BY_SITE_FILTER, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getQueriesByIdFilterStatus(@RequestParam("siteId") Integer siteId,@RequestParam("userId")Integer userId, @RequestParam("status")String status) {
		final AppResponse response = new AppResponse();
		try {
			List<QueryModel> queryList = queryService.findByAssignedIdAndStatus(siteId, status);
			if (queryList != null) {
				response.setResult(queryList);
				response.setStatus("Success");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("SITE QUERIES BY STATUS");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userId);
				logModel.setDescription("All Site Queries with " + status);
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("No Data");
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
	@RequestMapping(value = StudyRestController.GET_QUERY_DETAILS_BY_ID, method = { RequestMethod.GET })
	@ResponseBody
	public AppResponse getQueryDetailsById(@RequestParam("queryId") Integer queryId,@RequestParam("userId")Integer userId) {
		final AppResponse response = new AppResponse();
		try {
			List<QueryDetailsModel> queryList = queryService.findDetailsByQueryId(queryId);
			if (queryList != null) {
				response.setResult(queryList);
				response.setStatus("Success");
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("STUDY VISITS");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userId);
				logModel.setDescription("All Study Visits");
				masterDataService.createLogReport(logModel);
			}
			else {
				response.setMessage("No Data");
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
	@RequestMapping(value = StudyRestController.UPDATE_QUERY_STATUS, method = { RequestMethod.POST })
	@ResponseBody
	public AppResponse updateQueryDetails(@RequestBody QueryDetailsModel queryDataModel) {
		final AppResponse response = new AppResponse();
		try {
			User userEnt = userService.findByUserId(queryDataModel.getUpdatedUserId());
			queryService.updateQuery(queryDataModel, userEnt.getRoleId());
			response.setMessage("Updated Successfully!");
			response.setStatus("Success");

		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occured getting data");
			response.setStatus("Error");
		}
		return response;
	}

}
