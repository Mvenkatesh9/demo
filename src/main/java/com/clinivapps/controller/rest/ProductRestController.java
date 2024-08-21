
package com.clinivapps.controller.rest;

import com.clinivapps.model.ProductModel;

import java.util.Calendar;
import java.util.List;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.clinivapps.model.LogReportModel;
import com.clinivapps.model.AppResponse;
import com.clinivapps.service.EmailService;
import com.clinivapps.service.MasterDataService;
import com.clinivapps.service.ProductService;
import com.clinivapps.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import com.clinivapps.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/product" })
public class ProductRestController
{
    public static final String GET_ALL_PRODUCTS_BY_COMPANYID = "/getAllCompanysByCompanyID";
    public static final String GET_ALL_PRODUCTS_BY_STUDYID = "/getAllProductsByStudyId";
    public static final String GET_ALL_DRUG_STOCK_BY_PATIENT = "/getAllDrugStockByPatient";
    public static final String CREATE_DRUG_STOCK = "/createDrugStock";
	public static final String GET_PATIENT_PRODUCTS = "/getPatientProducts";
    
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    SMSService smsService;
    @Autowired
    EmailService emailService;
    @Autowired
    MasterDataService masterDataService;

  
    @RequestMapping(value = ProductRestController.GET_ALL_PRODUCTS_BY_STUDYID, method = { RequestMethod.GET })
    @ResponseBody
    public AppResponse getAllProductsByStudyId(@RequestParam("studyId") Integer studyId, @RequestParam("userId") Integer userId) {
        final AppResponse response = new AppResponse();
        try {
        	List<ProductModel> productList = productService.findByStudyId(studyId);
    		response.setResult(productList);
            response.setStatus("Success");
            LogReportModel logModel = new LogReportModel();
			logModel.setActivity("STUDY DRUGS");
			logModel.setCreatedDate(Calendar.getInstance().getTime());
			logModel.setUserId(userId);
			logModel.setDescription("Study Drug Details");
			masterDataService.createLogReport(logModel);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Error occuured getting data");
            response.setStatus("Error");
        }
        return response;
    }
 
    @RequestMapping(value = ProductRestController.GET_PATIENT_PRODUCTS, method = { RequestMethod.GET })
    @ResponseBody
    public AppResponse getAllProductsByStudyId(@RequestParam("patientId") String patientId,@RequestParam("userId") Integer userId) {
        final AppResponse response = new AppResponse();
        try {
        	List<ProductModel> productList = productService.findByPatientId(patientId);
    		response.setResult(productList);
            response.setStatus("Success");
            LogReportModel logModel = new LogReportModel();
			logModel.setActivity("PATIENT DRUGS");
			logModel.setCreatedDate(Calendar.getInstance().getTime());
			logModel.setUserId(userId);
			logModel.setTrailParticipant(patientId);
			logModel.setDescription("Patient Products");
			masterDataService.createLogReport(logModel);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Error occuured getting data");
            response.setStatus("Error");
        }
        return response;
    }
}
