package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.clinivapps.entity.CRFSectionsEntity;

@Repository
public class PatientDairyDAO extends ClinIVDAO<CRFSectionsEntity>{
	
	public List<CRFSectionsEntity> findAll(){
		List<CRFSectionsEntity> entities = new ArrayList<CRFSectionsEntity>();
		try {
			entities = entityManager.createQuery("select d from PatientDairyEntity d",CRFSectionsEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<CRFSectionsEntity> findAdverseEvnets(){
		List<CRFSectionsEntity> entities = new ArrayList<CRFSectionsEntity>();
		try {
			entities = entityManager.createQuery("select d from PatientDairyEntity d where length(d.adverseEvents)>2",CRFSectionsEntity.class)
					.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	public List<CRFSectionsEntity> findByPatientAndStudyId(String patientId, Integer studyId){
		List<CRFSectionsEntity> entities = new ArrayList<CRFSectionsEntity>();
		try {
			entities = entityManager.createQuery("select d from PatientDairyEntity d where d.patientId=:patientId and d.studyId=:studyId",CRFSectionsEntity.class)
					.setParameter("patientId", patientId).setParameter("studyId", studyId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public CRFSectionsEntity  findByPatientAndStudyIdAndDate(String patientId, Integer studyId, Date startDate, Date endDate){
		CRFSectionsEntity entities = new CRFSectionsEntity();
		try {
			entities = entityManager.createQuery("select d from PatientDairyEntity d where d.patientId=:patientId and d.studyId=:studyId and DATE(d.createdDate) BETWEEN :startDate and :endDate",CRFSectionsEntity.class)
					.setParameter("patientId", patientId).setParameter("studyId", studyId).setParameter("startDate", startDate).setParameter("endDate", endDate).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<CRFSectionsEntity> findByPatientAndStudyIdAndDates(String patientId, Integer studyId, Date startDate, Date endDate){
		List<CRFSectionsEntity> entities = new ArrayList<CRFSectionsEntity>();
		try {
			entities = entityManager.createQuery("select d from PatientDairyEntity d where d.patientId=:patientId and d.studyId=:studyId and DATE(d.createdDate) BETWEEN :startDate and :endDate",CRFSectionsEntity.class)
					.setParameter("patientId", patientId).setParameter("studyId", studyId).setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
	public List<CRFSectionsEntity> findByPatientAndStudyIdAndMonth(Integer patientId, Integer studyId){
		List<CRFSectionsEntity> entities = new ArrayList<CRFSectionsEntity>();
		try {
			entities = entityManager.createQuery("select d from PatientDairyEntity d where d.patientId=:patientId and d.studyId=:studyId",CRFSectionsEntity.class)
					.setParameter("patientId", patientId).setParameter("studyId", studyId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
}
