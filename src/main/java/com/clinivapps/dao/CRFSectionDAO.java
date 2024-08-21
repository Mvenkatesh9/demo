package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.clinivapps.entity.CRFSectionsEntity;

@Repository
public class CRFSectionDAO extends ClinIVDAO<CRFSectionsEntity>{
	
	public List<CRFSectionsEntity> findAll(){
		List<CRFSectionsEntity> entities = new ArrayList<CRFSectionsEntity>();
		try {
			entities = entityManager.createQuery("select d from CRFSectionsEntity d",CRFSectionsEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	public CRFSectionsEntity findByCategoryId(Integer sectionId){
		CRFSectionsEntity entities = new CRFSectionsEntity();
		try {
			entities = entityManager.createQuery("select d from CRFSectionsEntity d where d.sectionId=:sectionId",CRFSectionsEntity.class).setParameter("sectionId", sectionId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
	
	public List<CRFSectionsEntity> findBySectionId(Integer studySectionId){
		List<CRFSectionsEntity> entities = new ArrayList<CRFSectionsEntity>();
		try {
			entities = entityManager.createQuery("select d from CRFSectionsEntity d where d.studySectionId=:studySectionId",CRFSectionsEntity.class)
					.setParameter("studySectionId", studySectionId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
	public CRFSectionsEntity findBySectionQuestionAndUserIds(Integer studySectionId, Integer questionId, Integer userId){
		CRFSectionsEntity entities = new CRFSectionsEntity();
		try {
			entities = entityManager.createQuery("select d from CRFSectionsEntity d where d.studySectionId=:studySectionId and d.questionId=:questionId and d.userId=:userId",CRFSectionsEntity.class)
					.setParameter("studySectionId", studySectionId).setParameter("questionId", questionId).setParameter("userId", userId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<CRFSectionsEntity> findByQuestionAndPatient(Integer questionId, String patientId){
		List<CRFSectionsEntity> entities = new ArrayList<CRFSectionsEntity>();
		try {
			entities = entityManager.createQuery("select d from CRFSectionsEntity d where d.questionId=:questionId and d.patientId=:patientId",CRFSectionsEntity.class)
					.setParameter("questionId", questionId).setParameter("patientId", patientId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<CRFSectionsEntity> findBySectionAndPatient(Integer studySectionId, String patientId){
		List<CRFSectionsEntity> entities = new ArrayList<CRFSectionsEntity>();
		try {
			entities = entityManager.createQuery("select d from CRFSectionsEntity d where d.studySectionId=:studySectionId and d.patientId=:patientId",CRFSectionsEntity.class)
					.setParameter("studySectionId", studySectionId).setParameter("patientId", patientId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<CRFSectionsEntity> findBySectionAndPatientDate(Integer studySectionId, String patientId, Date startDate, Date endDate){
		List<CRFSectionsEntity> entities = new ArrayList<CRFSectionsEntity>();
		try {
			entities = entityManager.createQuery("select d from CRFSectionsEntity d where d.studySectionId=:studySectionId and d.patientId=:patientId and DATE(d.createdDate) BETWEEN :startDate and :endDate",CRFSectionsEntity.class)
					.setParameter("studySectionId", studySectionId).setParameter("patientId", patientId).setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<CRFSectionsEntity> findByPatientAndDate(String patientId, Date startDate, Date endDate){
		List<CRFSectionsEntity> entities = new ArrayList<CRFSectionsEntity>();
		try {
			entities = entityManager.createQuery("select d from CRFSectionsEntity d where d.patientId=:patientId and DATE(d.createdDate) BETWEEN :startDate and :endDate",CRFSectionsEntity.class)
					.setParameter("startDate", startDate).setParameter("endDate", endDate).setParameter("patientId", patientId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<CRFSectionsEntity> findByPatientId( String patientId){
		List<CRFSectionsEntity> entities = new ArrayList<CRFSectionsEntity>();
		try {
			entities = entityManager.createQuery("select d from CRFSectionsEntity d where d.patientId=:patientId",CRFSectionsEntity.class)
					.setParameter("patientId", patientId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<CRFSectionsEntity> findByStudySectionId(Integer studySectionId){
		List<CRFSectionsEntity> entities = new ArrayList<CRFSectionsEntity>();
		try {
			entities = entityManager.createQuery("select d from CRFSectionsEntity d where d.studySectionId=:studySectionId",CRFSectionsEntity.class)
					.setParameter("studySectionId", studySectionId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
}
