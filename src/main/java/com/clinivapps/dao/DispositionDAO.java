package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.clinivapps.entity.DispositionEntity;

@Repository
public class DispositionDAO extends ClinIVDAO<DispositionEntity>{
	
	public List<DispositionEntity> findAll(){
		List<DispositionEntity> entities = new ArrayList<DispositionEntity>();
		try {
			entities = entityManager.createQuery("select d from DispositionEntity d",DispositionEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	public List<DispositionEntity> findByPatientId(String patientId){
		List<DispositionEntity> entities = new ArrayList<DispositionEntity>();
		try {
			entities = entityManager.createQuery("select d from DispositionEntity d where d.patientId=:patientId",DispositionEntity.class)
					.setParameter("patientId", patientId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
}
