package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.clinivapps.entity.ExclusionEntity;

@Repository
public class ExclusionDAO extends ClinIVDAO<ExclusionEntity>{
	
	public List<ExclusionEntity> findAll(){
		List<ExclusionEntity> entities = new ArrayList<ExclusionEntity>();
		try {
			entities = entityManager.createQuery("select d from ExclusionEntity d",ExclusionEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	public List<ExclusionEntity> findByStudyId(Integer studyId){
		List<ExclusionEntity> entities = new ArrayList<ExclusionEntity>();
		try {
			entities = entityManager.createQuery("select d from ExclusionEntity d where d.studyId=:studyId",ExclusionEntity.class).setParameter("studyId", studyId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
}
