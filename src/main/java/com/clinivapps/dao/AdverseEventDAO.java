package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.clinivapps.entity.AdverseEventsEntity;

@Repository
public class AdverseEventDAO extends ClinIVDAO<AdverseEventsEntity>{

	public List<AdverseEventsEntity> findAll(){
		List<AdverseEventsEntity> entities = new ArrayList<AdverseEventsEntity>();
		try {
			entities = entityManager.createQuery("select d from AdverseEventsEntity d",AdverseEventsEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	public List<AdverseEventsEntity> findByPatientId(String patientId){
		List<AdverseEventsEntity> entities = new ArrayList<AdverseEventsEntity>();
		try {
			entities = entityManager.createQuery("select d from AdverseEventsEntity d where d.patientId=:patientId",AdverseEventsEntity.class)
					.setParameter("patientId", patientId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<AdverseEventsEntity> findByAeId(Integer aeId){
		List<AdverseEventsEntity> entities = new ArrayList<AdverseEventsEntity>();
		try {
			entities = entityManager.createQuery("select d from AdverseEventsEntity d where d.aeId=:aeId",AdverseEventsEntity.class)
					.setParameter("aeId", aeId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	public List<AdverseEventsEntity> findByPatientAndCategory(String patientId, Integer categoryId){
		List<AdverseEventsEntity> entities = new ArrayList<AdverseEventsEntity>();
		try {
			entities = entityManager.createQuery("select d from AdverseEventsEntity d where d.patientId=:patientId and d.categoryId=:categoryId",AdverseEventsEntity.class)
					.setParameter("patientId", patientId).setParameter("categoryId", categoryId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public AdverseEventsEntity findByPatientAndQuestionCategory(String patientId, Integer categoryId, Integer questionId){
		AdverseEventsEntity entities = new AdverseEventsEntity();
		try {
			entities = entityManager.createQuery("select d from AdverseEventsEntity d where d.patientId=:patientId and d.questionId=:questionId and d.categoryId=:categoryId",AdverseEventsEntity.class)
					.setParameter("patientId", patientId).setParameter("questionId", questionId).setParameter("categoryId", categoryId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

}
