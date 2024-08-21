
package com.clinivapps.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clinivapps.model.AppResponse;
import com.clinivapps.model.StudyReportModel;
import com.clinivapps.model.VisitReportModel;
import com.clinivapps.service.StudyPatientsService;
import com.clinivapps.service.StudyService;
import com.clinivapps.service.UserService;

@Controller
@RequestMapping("/cra")
public class CRARestController {
    public static final String GET_MY_PROFILE = "/getMyProfile";
	public static final String GET_ALL_STUDYS_BY_COMPANY = "/getAllStudysByCompanyId";
	public static final String GET_ALL_DOCTORS_BY_STUDY = "/getAllDoctorsByStudy";
	public static final String GET_LATITUDES_BY_STUDY = "/getLocationsByStudy";
	public static final String GET_USER_COUNT_BY_STUDY = "/getUserCountByStudy";
	public static final String GET_VISIT_REPORT_BY_STUDY = "/getVisitReportByStudy";

	@Autowired
	UserService userService;

	@Autowired
	StudyService studyService;
	
	@Autowired
	StudyPatientsService StudyPatientsService;

	
	 @RequestMapping(value = CRARestController.GET_USER_COUNT_BY_STUDY, method = { RequestMethod.GET })
		@ResponseBody
		public AppResponse getUserCountStudy(@RequestParam("studyId") Integer studyId,@RequestParam("userId") Integer userId) {
			final AppResponse response = new AppResponse();
			try {
						StudyReportModel reportList = studyService.getStudyReportsData();
						response.setResult(reportList);
						response.setStatus("Success");
					
				
			}
			catch (Exception e) {
				e.printStackTrace();
				response.setMessage("Error occured getting data");
				response.setStatus("Error");
			}
			return response;
		}
	 @RequestMapping(value = CRARestController.GET_VISIT_REPORT_BY_STUDY, method = { RequestMethod.GET })
		@ResponseBody
		public AppResponse getVisitReportByStudy(@RequestParam("studyId") Integer studyId,@RequestParam("userId") Integer userId) {
			final AppResponse response = new AppResponse();
			try {
						VisitReportModel reportList = StudyPatientsService.getStudyVisitReportsData(studyId);
						response.setResult(reportList);
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
