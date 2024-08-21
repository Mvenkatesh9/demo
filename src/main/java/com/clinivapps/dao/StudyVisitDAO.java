package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.clinivapps.entity.StudyVisitEntity;

@Repository
public class StudyVisitDAO extends ClinIVDAO<StudyVisitEntity>{
	
	public List<StudyVisitEntity> findAll(){
		List<StudyVisitEntity> entities = new ArrayList<StudyVisitEntity>();
		try {
			entities = entityManager.createQuery("select d from StudyVisitEntity d",StudyVisitEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	public StudyVisitEntity findByVisitId(Integer visitId){
		StudyVisitEntity entities = new StudyVisitEntity();
		try {
			entities = entityManager.createQuery("select d from StudyVisitEntity d where d.visitId=:visitId",StudyVisitEntity.class)
					.setParameter("visitId", visitId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<StudyVisitEntity> findByStudyId(Integer studyId){
		List<StudyVisitEntity> entities = new ArrayList<StudyVisitEntity>();
		try {
			entities = entityManager.createQuery("select d from StudyVisitEntity d where d.studyId=:studyId",StudyVisitEntity.class).setParameter("studyId", studyId)
					.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public StudyVisitEntity findByDoctorAStudyId(Integer studyId){
		StudyVisitEntity entities = new StudyVisitEntity();
		try {
			entities = entityManager.createQuery("select d from StudyVisitEntity d where d.studyId=:studyId",StudyVisitEntity.class).setParameter("studyId", studyId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
}
