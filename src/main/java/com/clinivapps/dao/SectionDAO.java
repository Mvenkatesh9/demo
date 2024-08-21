package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.clinivapps.entity.SectionEntity;

@Repository
public class SectionDAO extends ClinIVDAO<SectionEntity>{
	
	public List<SectionEntity> findAll(){
		List<SectionEntity> entities = new ArrayList<SectionEntity>();
		try {
			entities = entityManager.createQuery("select d from SectionEntity d order by d.createdDate desc",SectionEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	public SectionEntity findBySectionId(Integer sectionId){
		SectionEntity entities = new SectionEntity();
		try {
			entities = entityManager.createQuery("select d from SectionEntity d where d.sectionId=:sectionId",SectionEntity.class).setParameter("sectionId", sectionId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
	public List<SectionEntity> findByStudyId(Integer studyId){
		List<SectionEntity> entities = new ArrayList<SectionEntity>();
		try {
			entities = entityManager.createQuery("select d from SectionEntity d where d.studyId=:studyId",SectionEntity.class).setParameter("studyId", studyId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> findByStudyAndVisitId(Integer studyId, Integer visitIdInt){
		String visitId = visitIdInt.toString();
		List<Object[]> entitiesObj = new ArrayList<Object[]>();
		try {
//			entities = entityManager.createQuery("select d from SectionEntity d where d.studyId=:studyId and find_in_set(:visitId, d.visitId)",SectionEntity.class)
//					.setParameter("studyId", studyId).setParameter("visitId", visitId).getResultList();
//			
			entitiesObj = entityManager.createNativeQuery("SELECT * FROM c_section WHERE STUDY_ID = :studyId AND FIND_IN_SET(:visitId, VISIT_ID)")
					.setParameter("studyId", studyId).setParameter("visitId", visitId).getResultList();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entitiesObj;
	}
	public List<SectionEntity> findByStudyIdAndDays(Integer studyId, Integer displayDay){
		List<SectionEntity> entities = new ArrayList<SectionEntity>();
		try {
			entities = entityManager.createQuery("select d from SectionEntity d where d.studyId=:studyId and d.displayDay >= :displayDay",SectionEntity.class)
					.setParameter("studyId", studyId).setParameter("displayDay", displayDay).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	

}

