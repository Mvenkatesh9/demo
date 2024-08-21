package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.clinivapps.entity.AdverseEventsEntity;
import com.clinivapps.entity.CommonFormEntity;

@Repository
public class CommonFormDAO extends ClinIVDAO<CommonFormEntity>{
	
	public List<CommonFormEntity> findAll(){
		List<CommonFormEntity> entities = new ArrayList<CommonFormEntity>();
		try {
			entities = entityManager.createQuery("select d from CommonFormEntity d",CommonFormEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	public List<CommonFormEntity> findByPatientId(String patientId){
		List<CommonFormEntity> entities = new ArrayList<CommonFormEntity>();
		try {
			entities = entityManager.createQuery("select d from CommonFormEntity d where d.patientId=:patientId",CommonFormEntity.class)
					.setParameter("patientId", patientId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<CommonFormEntity> findBySectionId(Integer sectionId){
		List<CommonFormEntity> entities = new ArrayList<CommonFormEntity>();
		try {
			entities = entityManager.createQuery("select d from CommonFormEntity d where d.sectionId=:sectionId",CommonFormEntity.class)
					.setParameter("sectionId", sectionId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<CommonFormEntity> findByPatientSectionId(Integer sectionId, String patientId){
		List<CommonFormEntity> entities = new ArrayList<CommonFormEntity>();
		try {
			entities = entityManager.createQuery("select d from CommonFormEntity d where d.sectionId=:sectionId and d.patientId=:patientId",CommonFormEntity.class)
					.setParameter("sectionId", sectionId).setParameter("patientId", patientId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public CommonFormEntity findByPatientAndCategory(String patientId, Integer categoryId){
		CommonFormEntity entities = new CommonFormEntity();
		try {
			entities = entityManager.createQuery("select d from CommonFormEntity d where d.patientId=:patientId and d.categoryId=:categoryId",CommonFormEntity.class)
					.setParameter("patientId", patientId).setParameter("categoryId", categoryId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public CommonFormEntity findByPatientAndQuestionCategory(String patientId, Integer categoryId, Integer questionId){
		CommonFormEntity entities = new CommonFormEntity();
		try {
			entities = entityManager.createQuery("select d from CommonFormEntity d where d.patientId=:patientId and d.questionId=:questionId and d.categoryId=:categoryId",CommonFormEntity.class)
					.setParameter("patientId", patientId).setParameter("questionId", questionId).setParameter("categoryId", categoryId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
}
