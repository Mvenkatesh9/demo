package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.clinivapps.entity.InclusionEntity;

@Repository
public class InclusionDAO extends ClinIVDAO<InclusionEntity>{
	
	public List<InclusionEntity> findAll(){
		List<InclusionEntity> entities = new ArrayList<InclusionEntity>();
		try {
			entities = entityManager.createQuery("select d from InclusionEntity d",InclusionEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	public List<InclusionEntity> findByStudyId(Integer studyId){
		List<InclusionEntity> entities = new ArrayList<InclusionEntity>();
		try {
			entities = entityManager.createQuery("select d from InclusionEntity d where d.studyId=:studyId",InclusionEntity.class)
					.setParameter("studyId", studyId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
}
