package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.clinivapps.entity.StudyEntity;

@Repository
public class StudyDAO extends ClinIVDAO<StudyEntity>{
	
	public List<StudyEntity> findAll(){
		List<StudyEntity> entities = new ArrayList<StudyEntity>();
		try {
			entities = entityManager.createQuery("select d from StudyEntity d",StudyEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	public List<StudyEntity> findByCompanyId(Integer companyId){
		List<StudyEntity> entities = new ArrayList<StudyEntity>();
		try {
			entities = entityManager.createQuery("select d from StudyEntity d where d.companyId=:companyId",StudyEntity.class).setParameter("companyId", companyId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public StudyEntity findByStudyId(Integer studyId){
		StudyEntity entities = new StudyEntity();
		try {
			entities = entityManager.createQuery("select d from StudyEntity d where d.studyId=:studyId",StudyEntity.class).setParameter("studyId", studyId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public StudyEntity findByDoctorAStudyId(Integer studyId){
		StudyEntity entities = new StudyEntity();
		try {
			entities = entityManager.createQuery("select d from StudyEntity d where d.studyId=:studyId",StudyEntity.class).setParameter("studyId", studyId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
}
