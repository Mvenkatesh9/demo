package com.clinivapps.controller.web;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
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
import com.clinivapps.model.QuestionModel;
import com.clinivapps.model.SectionModel;
import com.clinivapps.model.SiteReportModel;
import com.clinivapps.model.StudyDocumentModel;
import com.clinivapps.model.TrialSiteModel;
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
import com.clinivapps.service.TherapeuticAreaService;
import com.clinivapps.service.UserService;
import com.clinivapps.util.ClinIVException;

@Controller
@RequestMapping("/web/sponsor")
public class SponsorDashboardController {

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
	TherapeuticAreaService therapeuticService;	
	
	@Autowired
	QueryService queryService;	
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
				logModel.setActivity("SPONSOR LOGGED IN");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userEnt.getUserId());
				logModel.setDescription("Sponsor Logged into the System");
				masterDataService.createLogReport(logModel);
			}
			mnv.setViewName("/web/sponsor/dashboard");    	 
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
			mnv.setViewName("/web/sponsor/sections");  	 
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
			mnv.setViewName("/web/sponsor/questions");    	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}
	@RequestMapping(value = "/createQuestion", method = RequestMethod.POST)
	public ModelAndView createQuestion(@RequestParam Map<String, String[]> reqParam,
			@RequestParam("sectionId") String sectionId,
			@RequestParam("crf") String crfId,
			HttpServletRequest request, Model model) {
		ModelAndView mnv = new ModelAndView();

		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			System.out.println(userSecuritId);
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));

			try {
				ArrayList<String> questionArray = new ArrayList<String>();
				ArrayList<String> typeArray = new ArrayList<String>();
				ArrayList<String> commentsArray = new ArrayList<String>();
				ArrayList<String> optionsArray = new ArrayList<String>();

				Map map = request.getParameterMap();
				for (Object key: map.keySet()){
					String keyStr = (String)key;
					String[] value = (String[])map.get(keyStr);
					System.out.println(" Length: "+ value.length); 
					for (int i = 0; i < value.length; i++) {
						if (key.equals("question")){
							System.out.println( "question : "+ value[i]); 
							questionArray.add(value[i]);
						}
						else if (key.equals("type")){
							System.out.println("type : "+ value[i]); 
							typeArray.add(value[i]);
						}
						else if (key.equals("comments")){
							System.out.println("comments : "+ value[i]); 
							commentsArray.add(value[i]);
						}
						else if (key.equals("options")){
							System.out.println("options : "+ value[i]); 
							optionsArray.add(value[i]);
						}
					}
				}
				if (questionArray.size() == typeArray.size()) {
					for (int i = 0; i < questionArray.size(); i++) {
						QuestionModel questionModel = new QuestionModel();
						if (typeArray.get(i).equals("Fill_Blank")) {
							questionModel.setSectionId(Integer.parseInt(sectionId));
							questionModel.setQuestionTitle(questionArray.get(i));
							questionModel.setQuestionType(typeArray.get(i));
							questionModel.setQuestionComments(commentsArray.get(i));
							model.addAttribute("question", questionModel);
							questionService.createQuestion(model);

						}
						else {
							questionModel.setSectionId(Integer.parseInt(sectionId));
							questionModel.setQuestionTitle(questionArray.get(i));
							questionModel.setQuestionType(typeArray.get(i));
							questionModel.setQuestionComments(commentsArray.get(i));
							questionModel.setOptions(optionsArray.get(i));
							model.addAttribute("question", questionModel);
							questionService.createQuestion(model);
						}
					}  
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			mnv.setViewName("redirect:/web/sponsor/questions"); 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}

		return mnv;

	}
	@RequestMapping(value = "/getSectionByCRF", method = RequestMethod.GET)
	public @ResponseBody String getSectionByCRF(@RequestParam String crfId,Model model) {
		StringBuilder buffer =new StringBuilder();
		List<SectionModel> sectionsList = new ArrayList<SectionModel>();
		Map<String,String> tmap = new HashMap<String,String>();
		sectionsList = sectionService.findByStudyId(Integer.parseInt(crfId));
		if(null!=sectionsList &sectionsList.size() > 0) {
			for(int j=0;j<sectionsList.size();j++) {
				tmap.put(sectionsList.get(j).getSectionId().toString(), sectionsList.get(j).getTitle());
			}

		}
		model.addAttribute("sectionsList",sectionsList);
		for(String key : tmap.keySet()){
			buffer.append("<option value="+key +">" +tmap.get(key) +"</option>");
		}
		return buffer.toString();
	}

	@RequestMapping(value = "/createSection", method = RequestMethod.POST)
	public ModelAndView createSection(@RequestParam Map<String, String[]> reqParam,
			@RequestParam("crfId") String crfId,
			HttpServletRequest request, Model model) {
		ModelAndView mnv = new ModelAndView();
		if (request.getSession().getAttribute("UserSecuritId") != null) {
			String userSecuritId = request.getSession().getAttribute("UserSecuritId").toString();
			UserEntity userEnt = userDAO.findBySecurityId(Integer.parseInt(userSecuritId));
			mnv.addObject("FirstName",userEnt.getFirstName());
			mnv.addObject("LastName", userEnt.getLastName());
			mnv.addObject("timeStr", dateTimeFormat.format(Calendar.getInstance().getTime()));

			try {
				ArrayList<String> sectionArray = new ArrayList<String>();
				ArrayList<String> descriptionArray = new ArrayList<String>();

				Map map = request.getParameterMap();
				for (Object key: map.keySet()){
					String keyStr = (String)key;
					String[] value = (String[])map.get(keyStr);
					System.out.println(" Length: "+ value.length); 
					for (int i = 0; i < value.length; i++) {
						if (key.equals("section")){
							System.out.println( "Section : "+ value[i]); 
							sectionArray.add(value[i]);
						}
						else if (key.equals("description")){
							System.out.println("Description : "+ value[i]); 
							descriptionArray.add(value[i]);
						}

					}
				}

				if (sectionArray.size() == descriptionArray.size()) {

					for (int i = 0; i < sectionArray.size(); i++) {
						SectionModel sectionModel = new SectionModel();
						sectionModel.setStudyId(Integer.parseInt(crfId));
						sectionModel.setTitle(sectionArray.get(i));
						sectionModel.setDescription(descriptionArray.get(i));
						model.addAttribute("section", sectionModel);
						sectionService.createSection(model);
					}  
				}

				LogReportModel logModel = new LogReportModel();
				logModel.setActivity("CREATE SECTION");
				logModel.setCreatedDate(Calendar.getInstance().getTime());
				logModel.setUserId(userEnt.getUserId());
				logModel.setDescription("Sponsor Logged into the System");
				masterDataService.createLogReport(logModel);
			}
			catch(Exception e) {
				e.printStackTrace();
			}

			mnv.setViewName("redirect:/web/sponsor/sections"); 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}

		return mnv;


	}


	@RequestMapping(value = "/sponsorTeam", method = RequestMethod.GET)
	public ModelAndView getAllSaleTeamByCompanyId(HttpServletRequest request, Model model) {
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
			mnv.setViewName("/web/sponsor/commonTeam");    	 
		}
		else {
			mnv.addObject("url", "./support.do");
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
		mnv.setViewName("redirect:/web/sponsor/dashboard");  
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
			mnv.setViewName("/web/sponsor/changePassword");    	 
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
			mnv.setViewName("/web/sponsor/products");  	 
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
					model.addAttribute("therapeuticList", therapeuticService.findAll());

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
			mnv.setViewName("/web/sponsor/studyInfo");    	 
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
			mnv.setViewName("/web/sponsor/userManuals");  	 
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
					model.addAttribute("faqsList", studyService.getFaqsData(1));
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
			mnv.setViewName("/web/sponsor/studyFaqs");  	 
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
			mnv.setViewName("/web/sponsor/studyDocuments");  	 
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
					studyDocModel.setStudyId(1);
					model.addAttribute("study",studyDocModel);
					model.addAttribute("file",file);

					studyService.createStudyDocument(model);

				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			mnv.setViewName("redirect:/web/sponsor/studyDocuments"); 
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
			mnv.setViewName("/web/sponsor/trailsites");  	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}
	@RequestMapping(value = "/createTrailSite", method = RequestMethod.POST)
	public ModelAndView createTrailSite(@RequestParam("siteName") String siteName,
			@RequestParam("location") String location,
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
					TrialSiteModel trailSiteModel = new TrialSiteModel();
					trailSiteModel.setLocation(location);
					trailSiteModel.setSiteName(siteName);
					model.addAttribute("trailSite",trailSiteModel);
					studyService.createTrailSite(model);

				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			mnv.setViewName("redirect:/web/sponsor/trailSites"); 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}

		return mnv;

	}
	public ModelAndView uploadDcFile(Model model, @RequestParam("docExcel") MultipartFile file, HttpServletRequest request) throws IOException {
		ModelAndView mnv = new ModelAndView();
		int count = 0;
		String path = file.getOriginalFilename();

		String filePath = request.getServletContext().getRealPath("/");
		File f1 = new File(filePath+"/"+file.getOriginalFilename());
		file.transferTo(f1);

		FileInputStream fileStream = new FileInputStream(f1);
		Workbook workbook = null;

		XWPFDocument document = new XWPFDocument();
		XWPFDocument docx = new XWPFDocument(fileStream);
		XWPFWordExtractor we = new XWPFWordExtractor(docx);
		String text = we.getText() ;
		if(text.contains("SMS")){
			text = text.replace("SMS", "sms");
			System.out.println(text);
		}
		char[] c = text.toCharArray();
		for(int i= 0; i < c.length;i++){

			if(c[i] == '\n'){
				count ++;
			}
		}
		System.out.println(c[0]);
		StringTokenizer st = new StringTokenizer(text,"\n");

		XWPFParagraph para = document.createParagraph();
		para.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run = para.createRun();
		run.setBold(true);
		run.setFontSize(36);
		run.setText("Apache POI works well!");

		List<XWPFParagraph>paragraphs = new ArrayList<XWPFParagraph>();
		List<XWPFRun>runs = new ArrayList<XWPFRun>();
		int k = 0;
		for(k=0;k<count+1;k++){
			paragraphs.add(document.createParagraph());
		}
		k=0;
		while(st.hasMoreElements()){
			paragraphs.get(k).setAlignment(ParagraphAlignment.LEFT);
			paragraphs.get(k).setSpacingAfter(0);
			paragraphs.get(k).setSpacingBefore(0);
			run = paragraphs.get(k).createRun();
			run.setText(st.nextElement().toString());
			k++;
		}
		model.addAttribute("docUrl", docx);

		document.write(new FileOutputStream("test2.docx"));

		mnv.setViewName("/web/sponsor/wordDoc");  	
		return mnv;
	}    

	@RequestMapping(value = "/openword", method = RequestMethod.POST)
	public ModelAndView uploadDocFile(Model model, @RequestParam("docExcel") MultipartFile file, HttpServletRequest request) throws URISyntaxException {

		ModelAndView mnv = new ModelAndView();
		String filePath = request.getServletContext().getRealPath("/");
		File f1 = new File(filePath+"/"+file.getOriginalFilename());
		try {
			file.transferTo(f1);
			FileInputStream fileStream = new FileInputStream(f1);
			//Create file object
			Desktop.getDesktop().open(f1);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			mnv.setViewName("/web/sponsor/siteReport");  	 
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
			mnv.setViewName("/web/sponsor/studyPatients");  	 

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
			mnv.setViewName("/web/sponsor/labReports");  	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}
	@RequestMapping(value = "/adverseEventsAll", method = RequestMethod.GET)
	public ModelAndView getAdverseByStudy(HttpServletRequest request, Model model) {
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
			mnv.setViewName("/web/sponsor/advEventsList");  	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}
	@RequestMapping(value = "/adverseEvents", method = RequestMethod.GET)
	public ModelAndView getAdverseByCategory(HttpServletRequest request, Model model, @RequestParam("categoryId") Integer categoryId, @RequestParam("patientId") String patientId) {
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
				logModel.setDescription("Adverse Events List");
				masterDataService.createLogReport(logModel);
			}
			mnv.setViewName("/web/sponsor/advEvents");  	 
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
			mnv.setViewName("/web/sponsor/logReports");
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
				view.setViewName("/web/sponsor/logReports");
			}
		}
		return view;
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
					model.addAttribute("subjectsList", studyPatientService.findByStudy(1));
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
			mnv.setViewName("/web/sponsor/subjects");    	 
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
			mnv.setViewName("/web/sponsor/subjects");    	 
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
					model.addAttribute("dataList", studyPatientService.findWebPatientVisit(1,patientId));
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
			mnv.setViewName("/web/sponsor/subjectCRF");  	 
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
				VisitSectionModel modelData = studyPatientService.findWebVisitsByPatientAndStudyId(1, patientId,sectionId,categoryId);
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
			mnv.setViewName("/web/sponsor/subjectQuestions");    	 
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
			mnv.setViewName("/web/sponsor/queries");  	 
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
			mnv.setViewName("/web/sponsor/queries");  	 
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
			mnv.setViewName("/web/sponsor/queryDetails");  	 
		}
		else {
			mnv.addObject("url", "./support.do");
			mnv.addObject("url_name","Support");
			mnv.setViewName("/web/auth/loginpage"); 
		}
		return mnv;
	}
}

