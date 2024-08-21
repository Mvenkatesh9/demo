package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.clinivapps.entity.CRFQuestionsEntity;

@Repository
public class CRFQuestionDAO extends ClinIVDAO<CRFQuestionsEntity>{
	
	public List<CRFQuestionsEntity> findAll(){
		List<CRFQuestionsEntity> entities = new ArrayList<CRFQuestionsEntity>();
		try {
			entities = entityManager.createQuery("select d from CRFQuestionsEntity d",CRFQuestionsEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	public List<CRFQuestionsEntity> findByCompanyId(Integer companyId){
		List<CRFQuestionsEntity> entities = new ArrayList<CRFQuestionsEntity>();
		try {
			entities = entityManager.createQuery("select d from CRFQuestionsEntity d where d.companyId=:companyId",CRFQuestionsEntity.class).setParameter("companyId", companyId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<CRFQuestionsEntity> findByStudyId(Integer studyId){
		List<CRFQuestionsEntity> entities = new ArrayList<CRFQuestionsEntity>();
		try {
			entities = entityManager.createQuery("select d from CRFQuestionsEntity d where d.studyId=:studyId",CRFQuestionsEntity.class)
					.setParameter("studyId", studyId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
	public List<CRFQuestionsEntity> findBySectionId(Integer sectionId){
		List<CRFQuestionsEntity> entities = new ArrayList<CRFQuestionsEntity>();
		try {
			entities = entityManager.createQuery("select d from CRFQuestionsEntity d where d.sectionId=:sectionId",CRFQuestionsEntity.class)
					.setParameter("sectionId", sectionId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
	public CRFQuestionsEntity findBySectionQuestionAndUserIds(Integer sectionId, Integer questionId, Integer userId){
		CRFQuestionsEntity entities = new CRFQuestionsEntity();
		try {
			entities = entityManager.createQuery("select d from CRFQuestionsEntity d where d.sectionId=:sectionId and d.questionId=:questionId and d.userId=:userId",CRFQuestionsEntity.class)
					.setParameter("sectionId", sectionId).setParameter("questionId", questionId).setParameter("userId", userId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<CRFQuestionsEntity> findByQuestionAndPatient(Integer questionId, String patientId){
		List<CRFQuestionsEntity> entities = new ArrayList<CRFQuestionsEntity>();
		try {
			entities = entityManager.createQuery("select d from CRFQuestionsEntity d where d.questionId=:questionId and d.patientId=:patientId",CRFQuestionsEntity.class)
					.setParameter("questionId", questionId).setParameter("patientId", patientId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public CRFQuestionsEntity findBySectionQuestionAndCategory(String patientId, Integer questionId, Integer categoryId){
		CRFQuestionsEntity entities = new CRFQuestionsEntity();
		System.out.print(patientId + "-" + questionId + "-" + categoryId);
		try {
			entities = entityManager.createQuery("select d from CRFQuestionsEntity d where d.patientId=:patientId and d.questionId=:questionId and d.categoryId=:categoryId",CRFQuestionsEntity.class)
					.setParameter("patientId", patientId).setParameter("questionId", questionId).setParameter("categoryId", categoryId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public CRFQuestionsEntity findBySectionQuestionAndSection(String patientId, Integer questionId, Integer sectionId){
		CRFQuestionsEntity entities = new CRFQuestionsEntity();
		try {
			entities = entityManager.createQuery("select d from CRFQuestionsEntity d where d.patientId=:patientId and d.questionId=:questionId and d.sectionId=:sectionId",CRFQuestionsEntity.class)
					.setParameter("patientId", patientId).setParameter("questionId", questionId).setParameter("sectionId", sectionId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
}

