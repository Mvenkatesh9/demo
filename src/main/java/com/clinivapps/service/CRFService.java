package com.clinivapps.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import com.clinivapps.dao.ProductDAO;
import com.clinivapps.dao.TherapeuticDAO;
import com.clinivapps.dao.TrailSiteDAO;
import com.clinivapps.entity.TrailSiteEntity;
import com.clinivapps.model.TrialSiteModel;


@Service("crfService")
@Transactional
public class CRFService {
		
	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	TrailSiteDAO trailSiteDAO;
	
	@Autowired
	TherapeuticDAO therapeuticDAO;
	
	
	public void createTrailSite(Model model) throws IOException{
		TrialSiteModel trailSiteModel=(TrialSiteModel) model.asMap().get("trailSite");
		  try {

			  TrailSiteEntity trailSiteEntity = new TrailSiteEntity();
			  trailSiteEntity.setLocation(trailSiteModel.getLocation());
			  trailSiteEntity.setSiteName(trailSiteModel.getSiteName());
			  trailSiteEntity.setCreatedDate(Calendar.getInstance().getTime());
			
			  trailSiteDAO.persist(trailSiteEntity);
			    
		   }
		   catch(Exception e) {
			  System.out.println(e);
	 	 		

		   }
	}
	
}
