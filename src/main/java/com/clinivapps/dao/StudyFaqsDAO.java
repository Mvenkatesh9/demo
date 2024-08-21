package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.clinivapps.entity.StudyFaqsEntity;

@Repository
public class StudyFaqsDAO extends ClinIVDAO<StudyFaqsEntity>{
	
	public List<StudyFaqsEntity> findAll(){
		List<StudyFaqsEntity> entities = new ArrayList<StudyFaqsEntity>();
		try {
			entities = entityManager.createQuery("select d from StudyFaqsEntity d",StudyFaqsEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	public List<StudyFaqsEntity> findByStudyId(Integer studyId){
		List<StudyFaqsEntity> entities = new ArrayList<StudyFaqsEntity>();
		try {
			entities = entityManager.createQuery("select d from StudyFaqsEntity d where d.studyId=:studyId",StudyFaqsEntity.class)
					.setParameter("studyId", studyId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
}
