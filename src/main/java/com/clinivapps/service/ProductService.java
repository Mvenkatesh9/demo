package com.clinivapps.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.clinivapps.dao.ProductDAO;
import com.clinivapps.dao.StudyDAO;
import com.clinivapps.dao.StudyPatientsDAO;
import com.clinivapps.entity.ProductsEntity;
import com.clinivapps.entity.StudyPatientsEntity;
import com.clinivapps.model.ProductModel;

@Service("productService")
@Transactional
public class ProductService {

	private static final Log log = LogFactory.getLog(ProductService.class);

	@Autowired
	ProductDAO productDAO;

	@Autowired
	StudyDAO studyDAO;

	@Autowired
	StudyPatientsDAO studyPatientsDAO;

	SimpleDateFormat dateTimeformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd");

	public List<ProductModel> findAll(){
		List<ProductModel> drugList = new ArrayList<ProductModel>();
		try {
			List<ProductsEntity> entities = productDAO.findAll();
			if(entities != null)
				for(ProductsEntity e : entities) {
					ProductModel model = new ProductModel();
					model.setId(e.getProductId());
					model.setProductTitle(e.getProductTitle());
					model.setProductDesc(e.getProductDesc());
					model.setProductImage(e.getProductImage());
					model.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(e.getCreatedDate()));
					model.setApprovedIndication(e.getApprovedIndication());
					model.setComposition(e.getComposition());
					model.setUsageDescription(e.getUsageDescription());
					drugList.add(model);
				}
		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return drugList;
	} 
	
	public List<ProductModel> findByPatientId(String patientId){
		List<ProductModel> drugList = new ArrayList<ProductModel>();
		try {
			StudyPatientsEntity patientEntity = studyPatientsDAO.findByPatientId(patientId);
			if (patientEntity.getProductIds().contains(",")) {
				String[] products = patientEntity.getProductIds().split(",");
				for(int i=0; i<products.length; i++) {
					ProductsEntity e = productDAO.findByDrugId(Integer.parseInt(products[i]));
					if(e != null) {
						ProductModel model = new ProductModel();
						model.setId(e.getProductId());
						model.setProductTitle(e.getProductTitle());
						model.setProductDesc(e.getProductDesc());
						model.setProductImage(e.getProductImage());
						model.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(e.getCreatedDate()));
						model.setApprovedIndication(e.getApprovedIndication());
						model.setComposition(e.getComposition());
						model.setUsageDescription(e.getUsageDescription());
						drugList.add(model);
					}
				}
			}
			else {
				ProductsEntity e = productDAO.findByDrugId(Integer.parseInt(patientEntity.getProductIds()));
				if(e != null) {
					ProductModel model = new ProductModel();
					model.setId(e.getProductId());
					model.setProductTitle(e.getProductTitle());
					model.setProductDesc(e.getProductDesc());
					model.setProductImage(e.getProductImage());
					model.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(e.getCreatedDate()));
					model.setApprovedIndication(e.getApprovedIndication());
					model.setComposition(e.getComposition());
					model.setUsageDescription(e.getUsageDescription());
					drugList.add(model);
				}
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return drugList;
	} 
	public List<ProductModel> findByStudyId(Integer studyId){
		List<ProductModel> drugList = new ArrayList<ProductModel>();
		try {
			List<ProductsEntity> products = productDAO.findByStudyId(studyId);
			for(ProductsEntity ent : products) {
				ProductsEntity e = productDAO.findByDrugId(ent.getProductId());
				if(e != null) {
					ProductModel model = new ProductModel();
					model.setId(e.getProductId());
					model.setProductTitle(e.getProductTitle());
					model.setProductDesc(e.getProductDesc());
					model.setProductImage(e.getProductImage());
					model.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(e.getCreatedDate()));
					model.setApprovedIndication(e.getApprovedIndication());
					model.setComposition(e.getComposition());
					model.setUsageDescription(e.getUsageDescription());
					drugList.add(model);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return drugList;
	} 


}

