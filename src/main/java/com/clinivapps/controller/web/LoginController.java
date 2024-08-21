package com.clinivapps.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.clinivapps.model.DisplayPicture;
import com.clinivapps.model.Role;
import com.clinivapps.model.UserSecurityContext;
import com.clinivapps.service.DisplayPictureService;
import com.clinivapps.service.MasterDataService;
import com.clinivapps.service.UserService;

@Controller
@RequestMapping("/web/auth")
public class LoginController {

	@Autowired
	UserService userService;

	@Autowired
	MasterDataService masterDataService;

	@Autowired
	DisplayPictureService displayPictureService;


	protected static Logger logger = Logger.getLogger("controller");

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public ModelAndView getLoginPage(@RequestParam(value="error", required=false) boolean error, ModelMap model) {
		logger.debug("Received request to show login page");
		ModelAndView mnv = new ModelAndView();
        System.out.println("Received request to show login page");
		if (error == true) {
			// Assign an error message
			mnv.addObject("error", "You have entered an invalid username or password! or Study Access disabled!");
		}
		else{
			mnv.addObject("error", "");
		}
		mnv.addObject("url", "./support.do");
		mnv.addObject("url_name","Support");
	   	mnv.setViewName("/web/auth/loginpage");
		return mnv;
	}

    @RequestMapping(value = "/common.do", method = RequestMethod.GET)
    public String getCommonPage(HttpServletRequest request) {
    	System.out.println("Received request to show common page");
    	UserSecurityContext context = null;
    	String page = "";
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserSecurityContext)
        {
        	boolean isAdmin = false;
                	
        	boolean isCompany = false;  // Sponsor Login
        	
        	boolean isDataManager = false;  // Data Manager Login

        	context = (UserSecurityContext)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	if(context != null)
        	{
        		for(Role role : context.getAuthorities())
        		{
        			if("ROLE_ADMIN".equalsIgnoreCase(role.getName())){
        				isAdmin = true;
					}
        			
        			else if("ROLE_SPONSOR_ADMIN".equalsIgnoreCase(role.getName())){
        				isCompany = true;
					}
        			else if("ROLE_DATA_MANAGER".equalsIgnoreCase(role.getName())){
        				isDataManager = true;
					}
        		}
        	}
        	request.getSession().setAttribute("UserSecuritId", context.getUserSecurityId());
        	DisplayPicture dp = displayPictureService.getUserDisplayPicture(context.getUserSecurityId());
        	if(dp != null){
        		request.getSession().setAttribute("profiePicture",dp);
        	}

        	if(isAdmin){
        		page = "redirect:/web/admin/dashboard.do";
        	}
       
        	else if(isCompany){
        		page = "redirect:/web/sponsor/dashboard";
        	}
        	
        	else if(isDataManager){
        		page = "redirect:/web/datamanager/dashboard";
        	}
        }

    	return page;
	}

}
