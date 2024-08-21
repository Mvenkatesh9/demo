package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.clinivapps.entity.StudyDocumentsEntity;

@Repository
public class StudyDocumentsDAO extends ClinIVDAO<StudyDocumentsEntity>{	
	public List<StudyDocumentsEntity> findAll(){
		List<StudyDocumentsEntity> entities = new ArrayList<StudyDocumentsEntity>();
		try {
			entities = entityManager.createQuery("select d from StudyDocumentsEntity d",StudyDocumentsEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	public List<StudyDocumentsEntity> findByStudyId(Integer studyId){
		List<StudyDocumentsEntity> entities = new ArrayList<StudyDocumentsEntity>();
		try {
			entities = entityManager.createQuery("select d from StudyDocumentsEntity d where d.studyId=:studyId",StudyDocumentsEntity.class)
					.setParameter("studyId", studyId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
	public List<StudyDocumentsEntity> findByStudyIdAndTitle(Integer studyId, String title){
		List<StudyDocumentsEntity> entities = new ArrayList<StudyDocumentsEntity>();
		try {
			entities = entityManager.createQuery("select d from StudyDocumentsEntity d where d.studyId=:studyId and d.title=:title",StudyDocumentsEntity.class)
					.setParameter("studyId", studyId).setParameter("title", title).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
}
