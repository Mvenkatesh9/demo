package com.clinivapps.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.clinivapps.dao.StudyDAO;
import com.clinivapps.dao.StudyDocumentsDAO;
import com.clinivapps.dao.ProductDAO;
import com.clinivapps.dao.TherapeuticDAO;
import com.clinivapps.dao.TrailSiteDAO;
import com.clinivapps.entity.StudyEntity;
import com.clinivapps.entity.TherapeuticAreaEntity;
import com.clinivapps.model.StudyModel;

@Service("studyDoctorsService")
@Transactional
public class StudyDoctorsService {

	private static final Log log = LogFactory.getLog(StudyDoctorsService.class);

	@Autowired
	StudyDAO studyDAO;

	@Autowired
	ProductDAO productDAO;

	@Autowired
	StudyDocumentsDAO ethicsDAO;

	@Autowired
	TrailSiteDAO trailSiteDAO;

	@Autowired
	TherapeuticDAO therapeuticDAO;



	public List<StudyModel> findAll(){
		List<StudyModel> drugList = new ArrayList<StudyModel>();
		try {
			List<StudyEntity> entities = studyDAO.findAll();
			if(entities != null)
				for(StudyEntity e : entities) {
					StudyModel model = new StudyModel();
					model.setStudyId(e.getStudyId());
					model.setStudyName(e.getStudyName());
					model.setStartDate(e.getStartDate().toString());
					model.setEndDate(e.getEndDate().toString());
					model.setCreatedDate(e.getCreatedDate());
					model.setTotalVisits(e.getVisitCount());
					drugList.add(model);
				}
		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return drugList;
	} 
	public List<StudyModel> findByCompanyId(Integer companyId){
		List<StudyModel> studyList = new ArrayList<StudyModel>();
		try {
			List<StudyEntity> entities = studyDAO.findByCompanyId(companyId);
			if(entities != null)
				for(StudyEntity e : entities) {
					StudyModel model = new StudyModel();
					model.setStudyId(e.getStudyId());
					model.setStudyName(e.getStudyName());
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");   
					model.setStartDate(formatter.format(e.getStartDate()));
					model.setEndDate(formatter.format(e.getEndDate()));
					model.setTherapeuticIds(e.getTherapeuticIds());
					model.setCreatedDate(e.getCreatedDate());
					model.setTotalVisits(e.getVisitCount());
					String therapeuticName = "";
					if (e.getTherapeuticIds().contains(",")) {
						String thrIDs[]=e.getTherapeuticIds().split(",");
						for (int i=0; i <thrIDs.length; i++){
							TherapeuticAreaEntity thrEntity = new TherapeuticAreaEntity();
							thrEntity = therapeuticDAO.findByThId(Integer.parseInt(thrIDs[i]));
							if (i+1 < thrIDs.length) {
								therapeuticName = therapeuticName + thrEntity.getTherapeuticName() + ", ";
							}
							else {
								therapeuticName = therapeuticName + thrEntity.getTherapeuticName();
							}
						}
						model.setTherapeuticNames(therapeuticName);
					}
					else {
						TherapeuticAreaEntity thrEntity = new TherapeuticAreaEntity();
						thrEntity = therapeuticDAO.findByThId(Integer.parseInt(e.getTherapeuticIds()));
						model.setTherapeuticNames(thrEntity.getTherapeuticName());
					}

					studyList.add(model);
				}
		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return studyList;
	} 	
//	public boolean submitDoctorICF(StudyDoctorModel studyDoctorModel){
//		boolean status = false;
//		try {
//			SiteUsersEntity entity = siteUse.findByStudyAndDoctorId(studyDoctorModel.getStudyId(),studyDoctorModel.getDoctorId());
//			if(entity.getDoctorId() != null) {
//
//				String picStr = ClinIVProperty.getInstance().getProperties("cliniv.home") + "/images/icf/";
//				if (studyDoctorModel.getDoctorSign().length() >0) {
//					picStr += FileUtil.copyBinaryData(studyDoctorModel.getDoctorSign().getBytes(),ClinIVProperty.getInstance().getProperties("images.loc")+File.separator+"icf",studyDoctorModel.getDoctorId()+".jpg");
//				}
//				entity.setDoctorSign(picStr);
//				entity.setLatitude(studyDoctorModel.getLatitude());
//				entity.setLongitude(studyDoctorModel.getLongitude());
//				entity.setJoinedDate(Calendar.getInstance().getTime());
//				studyDoctorsDAO.merge(entity);
//				status = true;
//			}
//
//		}catch(Exception e)
//		{
//			e.printStackTrace();	
//		}
//		return status;
//	}
}
