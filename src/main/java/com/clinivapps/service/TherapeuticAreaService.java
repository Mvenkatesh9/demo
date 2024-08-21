package com.clinivapps.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.clinivapps.dao.TherapeuticDAO;
import com.clinivapps.entity.TherapeuticAreaEntity;
import com.clinivapps.model.TherapeuticModel;


@Service("therapeuticService")
@Transactional
public class TherapeuticAreaService {
	
	private static final Log log = LogFactory.getLog(TherapeuticAreaService.class);
	
	@Autowired
	TherapeuticDAO therapeuticDAO;
	
	
	public List<TherapeuticModel> findAll(){
		List<TherapeuticModel> therapeuticList = new ArrayList<TherapeuticModel>();
		try {
			List<TherapeuticAreaEntity> entities = therapeuticDAO.findAll();
			if(entities != null)
				for(TherapeuticAreaEntity e : entities) {
					TherapeuticModel model = new TherapeuticModel();
					model.setTherapeuticId(e.getTherapeuticId());
					model.setTherapeuticName(e.getTherapeuticName());
					model.setTherapeuticDesc(e.getTherapeuticDesc());

					therapeuticList.add(model);
				}
		}catch(Exception e)
		{
		e.printStackTrace();	
		}
		return therapeuticList;
	} 
	
	
	
	
}
