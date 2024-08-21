package com.clinivapps.controller.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.clinivapps.dao.DisplayPictureDAO;
import com.clinivapps.dao.UserDAO;
import com.clinivapps.entity.UserEntity;
import com.clinivapps.model.StudyModel;
import com.clinivapps.model.StudyReportModel;
import com.clinivapps.model.LogReportModel;
import com.clinivapps.model.QueryDetailsModel;
import com.clinivapps.model.QueryModel;
import com.clinivapps.model.SiteReportModel;
import com.clinivapps.model.StudyDocumentModel;
import com.clinivapps.model.VisitReportModel;
import com.clinivapps.model.VisitSectionModel;
import com.clinivapps.service.StudyService;
import com.clinivapps.service.CommonTeamService;
import com.clinivapps.service.MasterDataService;
import com.clinivapps.service.ProductService;
import com.clinivapps.service.QueryService;
import com.clinivapps.service.QuestionService;
import com.clinivapps.service.SectionService;
import com.clinivapps.service.StudyPatientsService;
import com.clinivapps.service.UserService;
import com.clinivapps.util.ClinIVException;

@Controller
@RequestMapping("/web/datamanager")
public class DataManagerDashboardController {

	@Autowired
	DisplayPictureDAO dispictureDAO;

	@Autowired
	UserDAO userDAO;	

	@Autowired
	UserService  userService;	

	@Autowired
	ProductService productService;	

	@Autowired
	StudyService studyService;	

	@Autowired
	StudyPatientsService studyPatientService;	

	@Autowired
	CommonTeamService commonTeamService;	

	@Autowired
	SectionService sectionService;	

	@Autowired
	QuestionService questionService;

	@Autowired
	MasterDataService masterDataService;	

	@Autowired
	QueryService queryService;	

	Integer studyId = 1;
	protected static Logger logger = Logger.getLogger("controller");
	SimpleDateFormat dateTimeFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView geUserValidatorPage(HttpServletRequest request) {
		logger.debug("Received request to show admin page");
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			System.out.println(userSecuritId);
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {
				StudyReportModel reportModel = studyService.getStudyDashboardReportsData();
				mnv.addObject("reportModel",reportModel);
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("DM LOGGED IN");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userEnt.getUserId());
				logModel.setDescription("DM Logged into the System");
				masterDataService.createLogReport(logModel);
			}
			mnv.setViewName("/web/datamanager/dashboard");    	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}
	@RequestMapping(value = "/crf", method = RequestMethod.GET)
	public ModelAndView getCrf(HttpServletRequest request, Model model) {
		logger.debug("Received request to show admin page");
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {
				try {
					model.addAttribute("dataList", sectionService.findAll());
					LogReportModel logModel = new LogReportModel();
					logModel.setActivity("ECRF");
					logModel.setCreatedDate(Calendar.getInstance().getTime());
					logModel.setUserId(userEnt.getUserId());
					logModel.setDescription("Case Report Form");
					masterDataService.createLogReport(logModel);
				}catch(Exception e) {
					model.addAttribute("dataList", e.getMessage());
				}
			}
			mnv.setViewName("/web/datamanager/sections");  	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}
	@RequestMapping(value = "/formFields", method = RequestMethod.GET)
	public ModelAndView getFormFields(HttpServletRequest request, Model model,@RequestParam Integer sectionId, @RequestParam String sectionName) {
		logger.info("Request received to show druglist");
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			System.out.println(userSecuritId);
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {

				try {
					model.addAttribute("questionsList", questionService.findBySectionId(sectionId));
					model.addAttribute("section", sectionName);
				}catch(Exception e) {
					model.addAttribute("questionList", e.getMessage());
				}

				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("FORM FIELDS");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userEnt.getUserId());
				logModel.setDescription("List of Fields for " + sectionName);
				masterDataService.createLogReport(logModel);
			}
			mnv.setViewName("/web/datamanager/questions");    	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}

	@RequestMapping(value = "/sponsorTeam", method = RequestMethod.GET)
	public ModelAndView getSponsorTeam(HttpServletRequest request, Model model) {
		logger.info("Request received to show druglist");
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {
				try {
					model.addAttribute("managerList", commonTeamService.findAllTeam());
					model.addAttribute("type", "Sponsor Team");
				}catch(Exception e) {
					model.addAttribute("managerList", e.getMessage());
				}
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("SPONSOR TEAM");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userEnt.getUserId());
				logModel.setDescription("Sponsor Team List");
				masterDataService.createLogReport(logModel);
			}
			mnv.setViewName("/web/datamanager/commonTeam");    	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}
	@RequestMapping(value = "/subjects", method = RequestMethod.GET)
	public ModelAndView getAllSubjects(HttpServletRequest request, Model model) {
		logger.info("Request received to show druglist");
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {
				try {
					model.addAttribute("subjectsList", studyPatientService.findByStudy(studyId));
				}catch(Exception e) {
					model.addAttribute("subjectsList", e.getMessage());
				}
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("SUBJECTS LIST");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userEnt.getUserId());
				logModel.setDescription("Subjects List");
				masterDataService.createLogReport(logModel);
			}
			mnv.setViewName("/web/datamanager/subjects");    	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}
	@RequestMapping(value = "/subjectsBySite", method = RequestMethod.GET)
	public ModelAndView getAllSubjectsBySite(HttpServletRequest request, Model model,@RequestParam Integer siteIdS) {
		logger.info("Request received to show druglist");
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {
				try {
					model.addAttribute("subjectsList", studyPatientService.findBySite(siteIdS));
				}catch(Exception e) {
					model.addAttribute("subjectsList", e.getMessage());
				}
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("SUBJECTS LIST BY SITE");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userEnt.getUserId());
				logModel.setDescription("Subjects List By Site");
				masterDataService.createLogReport(logModel);
			}
			mnv.setViewName("/web/datamanager/subjects");    	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}
	@RequestMapping(value = "/subjectCrf", method = RequestMethod.GET)
	public ModelAndView getSubjectCrf(HttpServletRequest request, Model model, @RequestParam String patientId) {
		logger.debug("Received request to show admin page");
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {
				try {
					model.addAttribute("dataList", studyPatientService.findWebPatientVisit(studyId,patientId));
					model.addAttribute("patientId", patientId);
					LogReportModel logModel = new LogReportModel();
					logModel.setActivity("SUBJECT E-CRF");
					logModel.setCreatedDate(Calendar.getInstance().getTime());
					logModel.setUserId(userEnt.getUserId());
					logModel.setDescription("Subject Case Report Form");
					masterDataService.createLogReport(logModel);
				}catch(Exception e) {
					model.addAttribute("dataList", e.getMessage());
				}
			}
			mnv.setViewName("/web/datamanager/subjectCRF");  	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}
	@RequestMapping(value = "/subjectFields", method = RequestMethod.GET)
	public ModelAndView getFormFieldsBySubject(HttpServletRequest request, Model model,
			@RequestParam String patientId,@RequestParam Integer sectionId, @RequestParam Integer categoryId) {
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			System.out.println(userSecuritId);
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {
				VisitSectionModel modelData = studyPatientService.findWebVisitsByPatientAndStudyId(studyId, patientId,sectionId,categoryId);
				try {
					model.addAttribute("questionsData", modelData);
					model.addAttribute("patientId", patientId);
					model.addAttribute("sectionId", sectionId);
					model.addAttribute("categoryId", categoryId);
				}catch(Exception e) {
					model.addAttribute("questionsData", e.getMessage());
				}

				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("SUBJECT FORM FIELDS");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userEnt.getUserId());
				logModel.setDescription("List of Fields for " + modelData.getTitle());
				masterDataService.createLogReport(logModel);
			}
			mnv.setViewName("/web/datamanager/subjectQuestions");    	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}
	@RequestMapping(value = "/queries", method = RequestMethod.GET)
	public ModelAndView getAllQueries(HttpServletRequest request, Model model) {
		logger.debug("Received request to show admin page");
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {
				try {
					model.addAttribute("dataList", queryService.findAll());
					LogReportModel logModel = new LogReportModel();
					logModel.setActivity("ALL QUERIES");
					logModel.setCreatedDate(Calendar.getInstance().getTime());
					logModel.setUserId(userEnt.getUserId());
					logModel.setDescription("All Queries");
					masterDataService.createLogReport(logModel);
				}catch(Exception e) {
					model.addAttribute("dataList", e.getMessage());
				}
			}
			mnv.setViewName("/web/datamanager/queries");  	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}
	@RequestMapping(value = "/subjectQueries", method = RequestMethod.GET)
	public ModelAndView getSubjectQueries(HttpServletRequest request, Model model, @RequestParam String patientId, @RequestParam Integer questionId) {
		logger.debug("Received request to show admin page");
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {
				try {
					model.addAttribute("dataList", queryService.findByPaientQuestionId(patientId, questionId));
					LogReportModel logModel = new LogReportModel();
					logModel.setActivity("SUBJECT QUERIES");
					logModel.setCreatedDate(Calendar.getInstance().getTime());
					logModel.setUserId(userEnt.getUserId());
					logModel.setDescription("Subject Queries");
					masterDataService.createLogReport(logModel);
				}catch(Exception e) {
					model.addAttribute("dataList", e.getMessage());
				}
			}
			mnv.setViewName("/web/datamanager/queries");  	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}
	@RequestMapping(value = "/queryDetails", method = RequestMethod.GET)
	public ModelAndView getAllQueryDetails(HttpServletRequest request, Model model, @RequestParam Integer queryId) {
		logger.debug("Received request to show admin page");
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {
				try {
					model.addAttribute("dataList", queryService.findDetailsByQueryId(queryId));
					LogReportModel logModel = new LogReportModel();
					logModel.setActivity("ALL QUERIES DETAILS");
					logModel.setCreatedDate(Calendar.getInstance().getTime());
					logModel.setUserId(userEnt.getUserId());
					logModel.setDescription("All Queries DEtails");
					masterDataService.createLogReport(logModel);
				}catch(Exception e) {
					model.addAttribute("dataList", e.getMessage());
				}
			}
			mnv.setViewName("/web/datamanager/queryDetails");  	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}
	@RequestMapping(value = "/createQuery", method = RequestMethod.POST)
	public ModelAndView createQuery(
			@RequestParam("patientId") String patientId,
			@RequestParam("sectionId") Integer sectionId,
			@RequestParam("categoryId") Integer categoryId,
			@RequestParam("questionId") Integer questionId,
			@RequestParam("assignedRole") String assignedRole,
			@RequestParam("comments") String comments,
			HttpServletRequest request, Model model) {
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {
				try {
					QueryModel queryDataModel = new QueryModel();
					queryDataModel.setAssignedRole(assignedRole);
					queryDataModel.setComments(comments);
					queryDataModel.setPatientId(patientId);
					queryDataModel.setQuestionId(questionId);
					queryDataModel.setRaisedUserId(userEnt.getUserId());
					queryDataModel.setSectionId(sectionId);
					queryDataModel.setCategoryId(categoryId);
					model.addAttribute("query",queryDataModel);
					queryService.createQuery(model,userEnt.getRole());

					LogReportModel logModel = new LogReportModel();
					logModel.setActivity("CREATE QUERY");
					logModel.setCreatedDate(Calendar.getInstance().getTime());
					logModel.setUserId(userEnt.getUserId());
					logModel.setDescription("Create query ");
					masterDataService.createLogReport(logModel);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			mnv.addObject("patientId",patientId);
			mnv.addObject("sectionId",sectionId);
			mnv.addObject("categoryId",categoryId);
			mnv.setViewName("redirect:/web/datamanager/subjectFields"); 
		}
		else {
			mnv.addObject("url", "./support");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}
	@RequestMapping(value = "/updateQuery", method = RequestMethod.POST)
	public ModelAndView updateQueryStatus(
			@RequestParam("queryDetailId") Integer queryDetailId,
			@RequestParam("queryId") Integer queryId,
			@RequestParam("comments") String comments,
			@RequestParam("status") String status,
			HttpServletRequest request, Model model) {
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {
				try {
					QueryDetailsModel queryDataModel = new QueryDetailsModel();
					queryDataModel.setComments(comments);
					queryDataModel.setDetailId(queryDetailId);
					queryDataModel.setQueryId(queryId);
					queryService.updateQueryStatus(queryDataModel, userEnt.getRole(), status, userEnt.getUserId());
					LogReportModel logModel = new LogReportModel();
					logModel.setActivity("UPDATED QUERY STATUS");
					logModel.setCreatedDate(Calendar.getInstance().getTime());
					logModel.setUserId(userEnt.getUserId());
					logModel.setDescription("Create query ");
					masterDataService.createLogReport(logModel);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			mnv.addObject("queryId",queryId);
			mnv.setViewName("redirect:/web/datamanager/queryDetails"); 
		}
		else {
			mnv.addObject("url", "./support");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public ModelAndView updatePassword(Model model, @RequestParam("email") String email, @RequestParam("newPassword") String newPassword,
			@RequestParam("confirmPassword") String confirmPassword) throws ClinIVException {
		logger.debug("Received request to show admin page");
		ModelAndView mnv = new ModelAndView();
		//companyManagerService.updateManagerStatus(managerId,status);
		userService.updatePassword(email, newPassword);
		mnv.setViewName("redirect:/web/datamanager/dashboard");  
		return mnv;
	}
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public ModelAndView forgotPasswordByCompanyId(HttpServletRequest request, Model model) {
		logger.info("Request received to show druglist");
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {
				try {
					model.addAttribute("email", userEnt.getEmailId());
				}catch(Exception e) {
					model.addAttribute("email", e.getMessage());
				}
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("CHANGE PASSWORD");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userEnt.getUserId());
				logModel.setDescription("Change Password");
				masterDataService.createLogReport(logModel);
			}
			mnv.setViewName("/web/datamanager/changePassword");    	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView getCompanyProductsById(HttpServletRequest request, Model model) {
		logger.debug("Received request to show admin page");
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {
				try {
					model.addAttribute("productsList", productService.findAll());
					LogReportModel logModel = new LogReportModel();
					logModel.setActivity("STYDY PRODUCTS");
					logModel.setCreatedDate(Calendar.getInstance().getTime());
					logModel.setUserId(userEnt.getUserId());
					logModel.setDescription("Study Products");
					masterDataService.createLogReport(logModel);
				}catch(Exception e) {
					model.addAttribute("productsList", e.getMessage());
				}
			}
			mnv.setViewName("/web/datamanager/products");  	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}
	@RequestMapping(value = "/studyInfo", method = RequestMethod.GET)
	public ModelAndView getStudyData(HttpServletRequest request, Model model) {
		logger.info("Request received to show druglist");
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {
				try {
					List<StudyModel> studyList = studyService.findAll();
					model.addAttribute("studyData", studyList.get(0));

				}catch(Exception e) {
					model.addAttribute("studyList", e.getMessage());
				}
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("STYDY INFORMATION");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userEnt.getUserId());
				logModel.setDescription("Complete Study Information");
				masterDataService.createLogReport(logModel);
			}
			mnv.setViewName("/web/datamanager/studyInfo");    	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}
	@RequestMapping(value = "/userManuals", method = RequestMethod.GET)
	public ModelAndView getUserManuals(HttpServletRequest request, Model model) {
		logger.debug("Received request to show admin page");
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("USER MANUALS");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userEnt.getUserId());
				logModel.setDescription("All Stakeholder User Manuals");
				masterDataService.createLogReport(logModel);
			}
			mnv.setViewName("/web/datamanager/userManuals");  	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}

	@RequestMapping(value = "/studyFaqs", method = RequestMethod.GET)
	public ModelAndView getStudyFaqsByStudy(HttpServletRequest request, Model model) {
		logger.debug("Received request to show admin page");
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {
				try {
					model.addAttribute("faqsList", studyService.getFaqsData(studyId));
				}catch(Exception e) {
					model.addAttribute("faqsList", e.getMessage());
				}
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("STUDY FAQS");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userEnt.getUserId());
				logModel.setDescription("Study Faqs");
				masterDataService.createLogReport(logModel);
			}
			mnv.setViewName("/web/datamanager/studyFaqs");  	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}
	@RequestMapping(value = "/studyDocuments", method = RequestMethod.GET)
	public ModelAndView getStudyDocumentsByStudy(HttpServletRequest request, Model model) {
		logger.debug("Received request to show admin page");
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {
				try {
					model.addAttribute("docsList", studyService.getStudyDocumentData(1, "ALL"));
				}catch(Exception e) {
					model.addAttribute("docsList", e.getMessage());
				}
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("STUDY DOCUMENTS");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userEnt.getUserId());
				logModel.setDescription("All the Study Documents List");
				masterDataService.createLogReport(logModel);
			}
			mnv.setViewName("/web/datamanager/studyDocuments");  	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}


	@RequestMapping(value = "/createDocument", method = RequestMethod.POST)
	public ModelAndView createDocument(
			@RequestParam("title") String documentTitle,
			@RequestParam("file") MultipartFile file,
			HttpServletRequest request, Model model) {
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {

				try {
					StudyDocumentModel studyDocModel = new StudyDocumentModel();
					studyDocModel.setFileType("PDF");
					studyDocModel.setTitle(documentTitle);
					studyDocModel.setStudyId(studyId);
					model.addAttribute("study",studyDocModel);
					model.addAttribute("file",file);

					studyService.createStudyDocument(model);

				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			mnv.setViewName("redirect:/web/datamanager/studyDocuments"); 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}

		return mnv;

	}

	@RequestMapping(value = "/trailSites", method = RequestMethod.GET)
	public ModelAndView getTrailSitesById(HttpServletRequest request, Model model) {
		logger.debug("Received request to show admin page");
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {

				try {
					model.addAttribute("trailSiteList", studyService.getAllSites());
				}catch(Exception e) {
					model.addAttribute("trailSiteList", e.getMessage());
				}
			}
			mnv.setViewName("/web/datamanager/trailsites");  	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}

	@RequestMapping(value = "/studyReports", method = RequestMethod.GET)
	public ModelAndView getStudyReportsByStudy(HttpServletRequest request, Model model) {
		logger.debug("Received request to show admin page");
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {

				try {
					model.addAttribute("siteList", studyService.getAllSites());
					model.addAttribute("reportData", studyPatientService.findAllSiteReport());
				}
				catch(Exception e) {
					model.addAttribute("reportModel", e.getMessage());
				}

				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("STUDY REPORTS");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userEnt.getUserId());
				logModel.setDescription("Study Reports of All Enrolled");
				masterDataService.createLogReport(logModel);
			}
			mnv.setViewName("/web/datamanager/siteReport");  	 
		}
		else {
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}
	@RequestMapping(value = "/siteReportFilter", method = RequestMethod.GET)
	public @ResponseBody SiteReportModel getSiteReportFilter(@RequestParam Integer siteId, Model model) {
		SiteReportModel reportData = new SiteReportModel();
		if (siteId == 0) {
			reportData = studyPatientService.findAllSiteReport();
		}
		else {
			reportData = studyPatientService.findAllSiteReportBySite(siteId);

		}
		return reportData;
	}
	@RequestMapping(value = "/patStudyReportsByStudy", method = RequestMethod.GET)
	public ModelAndView getStudyPatDataReportsByStudy(HttpServletRequest request, Model model, @RequestParam("studyIdP") Integer studyId, @RequestParam("typeP") String type) {
		logger.debug("Received request to show admin page");
		ModelAndView mnv = new ModelAndView();
		StudyReportModel reportModel = new StudyReportModel();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {

				try {
					reportModel = studyService.getStudyPatientReportsData(studyId, type);
					model.addAttribute("patientsList",reportModel.getPatientList());
				}
				catch(Exception e) {
					model.addAttribute("patientsList", e.getMessage());
				}
			}
			mnv.setViewName("/web/datamanager/studyPatients");  	 

		}
		else {
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}

	@RequestMapping(value = "/visitReportsByStudy", method = RequestMethod.GET)
	public @ResponseBody VisitReportModel getStudyVisitReportsByStudy(HttpServletRequest request, Model model, @RequestParam("studyId") Integer studyId) {
		logger.debug("Received request to show admin page");
		ModelAndView mnv = new ModelAndView();
		VisitReportModel reportModel = new VisitReportModel();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {

				try {
					reportModel = studyPatientService.getStudyVisitReportsData(studyId);
					model.addAttribute("reportModel",reportModel);
					mnv.addObject("reportModel", reportModel);
				}
				catch(Exception e) {
					model.addAttribute("reportModel", e.getMessage());
				}

			}
		}
		else {
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return reportModel;
	}

	@RequestMapping(value = "/labReports", method = RequestMethod.GET)
	public ModelAndView getLabReports(HttpServletRequest request, Model model) {
		logger.debug("Received request to show admin page");
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));

			try {
				model.addAttribute("labsList", studyPatientService.getAllLabReports());
			}catch(Exception e) {
				model.addAttribute("labsList", e.getMessage());
			}
			LogReportModel logModel = new LogReportModel();
			logModel.setActivity("LAB REPORTS");
			logModel.setCreatedDate(Calendar.getInstance().getTime());
			logModel.setUserId(userEnt.getUserId());
			logModel.setDescription("Lab Reports in the Study");
			masterDataService.createLogReport(logModel);
			mnv.setViewName("/web/datamanager/labReports");  	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}

	@RequestMapping(value = "/adverseEventsAll", method = RequestMethod.GET)
	public ModelAndView getAdverseAll(HttpServletRequest request, Model model) {
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {
				try {
					model.addAttribute("advList", sectionService.findByAllAdv(6));
				}catch(Exception e) {
					model.addAttribute("advList", e.getMessage());
				}
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("ADVERSE EVENTS LIST");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userEnt.getUserId());
				logModel.setDescription("Adverse Events List");
				masterDataService.createLogReport(logModel);
			}
			mnv.setViewName("/web/datamanager/advEventsList");  	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}
	@RequestMapping(value = "/adverseEvents", method = RequestMethod.GET)
	public ModelAndView getAdverseByCat(HttpServletRequest request, Model model, @RequestParam("categoryId") Integer categoryId, @RequestParam("patientId") String patientId) {
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {
				model.addAttribute("patientId", patientId);
				try {
					model.addAttribute("advList", sectionService.findByAllByCategoryId(categoryId, patientId));
				}catch(Exception e) {
					model.addAttribute("advList", e.getMessage());
				}
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("ADVERSE EVENTS BY PATIENT");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userEnt.getUserId());
				logModel.setDescription("Adverse Events By Patient");
				masterDataService.createLogReport(logModel);
			}
			mnv.setViewName("/web/datamanager/advEvents");  	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}
	@RequestMapping(value = "/logReports", method = RequestMethod.GET)
	public ModelAndView getLogReportsById(HttpServletRequest request, Model model) {
		logger.debug("Received request to show admin page");
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));
			if (userEnt != null) {
				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("PRE DOWNLOAD LOG REPORTS");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userEnt.getUserId());
				logModel.setDescription("Accessed Complete Log Reports");
				masterDataService.createLogReport(logModel);	
			}
			mnv.setViewName("/web/datamanager/logReports");
		}
		else {
			mnv.addObject("url", "./support");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}
	@RequestMapping(value = { "/logReports.xls" }, method = { RequestMethod.POST })
	public ModelAndView downloadLogReportData(final Model model, @RequestParam String startDate, @RequestParam String endDate,HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();

			Map<String, List<LogReportModel>> reportDataMap = (Map<String, List<LogReportModel>>)masterDataService.getAllLogReportsDownload(startDate, endDate);

			if (reportDataMap != null) {
				view = new ModelAndView("LogReports", "LogReports", reportDataMap);
				UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
				if (userEnt != null) {
					LogReportModel logModel = new LogReportModel();
					logModel.setActivity("DOWNLOAD LOG REPORTS");
					logModel.setCreatedDate(Calendar.getInstance().getTime());
					logModel.setUserId(userEnt.getUserId());
					logModel.setDescription("Accessed Complete Log Reports");
					masterDataService.createLogReport(logModel);
				}
			}
			else {
				view.addObject("deviceMsg", "No Data Found!");
				view.setViewName("/web/datamanager/logReports");
			}
		}
		return view;
	}
}

