package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.clinivapps.entity.StudyPatientsEntity;

@Repository
public class StudyPatientsDAO extends ClinIVDAO<StudyPatientsEntity>{	
	public List<StudyPatientsEntity> findAll(){
		List<StudyPatientsEntity> entities = new ArrayList<StudyPatientsEntity>();
		try {
			entities = entityManager.createQuery("select d from StudyPatientsEntity d",StudyPatientsEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	public List<StudyPatientsEntity> findByCompanyId(Integer companyId){
		List<StudyPatientsEntity> entities = new ArrayList<StudyPatientsEntity>();
		try {
			entities = entityManager.createQuery("select d from StudyPatientsEntity d where d.companyId=:companyId",StudyPatientsEntity.class).setParameter("companyId", companyId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<StudyPatientsEntity> findByNurseAndStudyId(Integer enrolledUserId, Integer studyId){
		List<StudyPatientsEntity> entities = new ArrayList<StudyPatientsEntity>();
		try {
			entities = entityManager.createQuery("select d from StudyPatientsEntity d where d.enrolledUserId=:enrolledUserId and d.studyId=:studyId",StudyPatientsEntity.class)
					.setParameter("enrolledUserId", enrolledUserId).setParameter("studyId", studyId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<StudyPatientsEntity> findBySiteAndStudyId(Integer trailSiteId, Integer studyId){
		List<StudyPatientsEntity> entities = new ArrayList<StudyPatientsEntity>();
		try {
			entities = entityManager.createQuery("select d from StudyPatientsEntity d where d.trailSiteId=:trailSiteId and d.studyId=:studyId",StudyPatientsEntity.class)
					.setParameter("trailSiteId", trailSiteId).setParameter("studyId", studyId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<StudyPatientsEntity> findBySiteAndStatus(Integer trailSiteId, String status){
		List<StudyPatientsEntity> entities = new ArrayList<StudyPatientsEntity>();
		try {
			entities = entityManager.createQuery("select d from StudyPatientsEntity d where d.trailSiteId=:trailSiteId and d.status=:status",StudyPatientsEntity.class)
					.setParameter("trailSiteId", trailSiteId).setParameter("status", status).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<StudyPatientsEntity> findByStatus(String status){
		List<StudyPatientsEntity> entities = new ArrayList<StudyPatientsEntity>();
		try {
			entities = entityManager.createQuery("select d from StudyPatientsEntity d where d.status=:status",StudyPatientsEntity.class)
					.setParameter("status", status).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public StudyPatientsEntity findByPatientAndSiteStudyIds(Integer trailSiteId, String mobileNumber, Integer studyId){
		StudyPatientsEntity entities = new StudyPatientsEntity();
		try {
			entities = entityManager.createQuery("select d from StudyPatientsEntity d where d.trailSiteId=:trailSiteId and d.mobileNumber=:mobileNumber and d.studyId=:studyId",StudyPatientsEntity.class)
					.setParameter("trailSiteId", trailSiteId).setParameter("mobileNumber", mobileNumber).setParameter("studyId", studyId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<StudyPatientsEntity> findBySiteId(Integer trailSiteId){
		List<StudyPatientsEntity> entities = new ArrayList<StudyPatientsEntity>();
		try {
			entities = entityManager.createQuery("select d from StudyPatientsEntity d where d.trailSiteId=:trailSiteId",StudyPatientsEntity.class)
					.setParameter("trailSiteId", trailSiteId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<StudyPatientsEntity> findByStudyId(Integer studyId){
		List<StudyPatientsEntity> entities = new ArrayList<StudyPatientsEntity>();
		try {
			entities = entityManager.createQuery("select d from StudyPatientsEntity d where d.studyId=:studyId",StudyPatientsEntity.class)
					.setParameter("studyId", studyId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public StudyPatientsEntity findByMobileNumber(String mobileNumber){
		StudyPatientsEntity entities = new StudyPatientsEntity();
		try {
			entities = entityManager.createQuery("select d from StudyPatientsEntity d where d.mobileNumber=:mobileNumber",StudyPatientsEntity.class)
					.setParameter("mobileNumber", mobileNumber).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public StudyPatientsEntity findByMobileNumberAndPatientId(String mobileNumber, String patientId){
		StudyPatientsEntity entities = new StudyPatientsEntity();
		try {
			entities = entityManager.createQuery("select d from StudyPatientsEntity d where d.mobileNumber=:mobileNumber and d.patientId=:patientId",StudyPatientsEntity.class)
					.setParameter("mobileNumber", mobileNumber).setParameter("patientId", patientId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public StudyPatientsEntity findByPatientId(String patientId){
		StudyPatientsEntity entities = new StudyPatientsEntity();
		try {
			entities = entityManager.createQuery("select d from StudyPatientsEntity d where d.patientId=:patientId",StudyPatientsEntity.class)
					.setParameter("patientId", patientId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public StudyPatientsEntity findByPatientAndStudyIds(String mobileNumber, Integer studyId){
		StudyPatientsEntity entities = new StudyPatientsEntity();
		try {
			entities = entityManager.createQuery("select d from StudyPatientsEntity d where d.mobileNumber=:mobileNumber and d.studyId=:studyId",StudyPatientsEntity.class)
					.setParameter("mobileNumber", mobileNumber).setParameter("studyId", studyId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
}
