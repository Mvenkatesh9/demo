package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.clinivapps.entity.PatientVisitEntity;

@Repository
public class PatientVisitsDAO extends ClinIVDAO<PatientVisitEntity>{
	
	public List<PatientVisitEntity> findAll(){
		List<PatientVisitEntity> entities = new ArrayList<PatientVisitEntity>();
		try {
			entities = entityManager.createQuery("select d from PatientVisitEntity d",PatientVisitEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	public List<PatientVisitEntity> findByCompanyId(Integer companyId){
		List<PatientVisitEntity> entities = new ArrayList<PatientVisitEntity>();
		try {
			entities = entityManager.createQuery("select d from PatientVisitEntity d where d.companyId=:companyId",PatientVisitEntity.class).setParameter("companyId", companyId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<PatientVisitEntity> findByStudyId(Integer studyId){
		List<PatientVisitEntity> entities = new ArrayList<PatientVisitEntity>();
		try {
			entities = entityManager.createQuery("select d from PatientVisitEntity d where d.studyId=:studyId",PatientVisitEntity.class).setParameter("studyId", studyId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<PatientVisitEntity> findByDoctorPatientStudyId(Integer studyId, Integer patientId, Integer doctorId){
		List<PatientVisitEntity> entities = new ArrayList<PatientVisitEntity>();
		try {
			entities = entityManager.createQuery("select d from PatientVisitEntity d where d.studyId=:studyId and d.patientId=:patientId and d.doctorId=:doctorId",PatientVisitEntity.class)
					.setParameter("studyId", studyId).setParameter("patientId", patientId).setParameter("doctorId", doctorId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
	public PatientVisitEntity findByStudyPatientVisitId(Integer studyId, String patientId, Integer visitId){
		PatientVisitEntity entities = new PatientVisitEntity();
		try {
			entities = entityManager.createQuery("select d from PatientVisitEntity d where d.studyId=:studyId and d.patientId=:patientId and d.visitId=:visitId",PatientVisitEntity.class)
					.setParameter("studyId", studyId).setParameter("patientId", patientId).setParameter("visitId", visitId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<PatientVisitEntity> findByStudyPatientStudy(Integer studyId, String patientId){
		List<PatientVisitEntity> entities = new ArrayList<PatientVisitEntity>();
		try {
			entities = entityManager.createQuery("select d from PatientVisitEntity d where d.studyId=:studyId and d.patientId=:patientId",PatientVisitEntity.class)
					.setParameter("studyId", studyId).setParameter("patientId", patientId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<PatientVisitEntity> findByPatientId(String patientId){
		List<PatientVisitEntity> entities = new ArrayList<PatientVisitEntity>();
		try {
			entities = entityManager.createQuery("select d from PatientVisitEntity d where d.patientId=:patientId",PatientVisitEntity.class)
					.setParameter("patientId", patientId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<PatientVisitEntity> findByStudyPatientVisit(Integer studyId, Integer visitId){
		List<PatientVisitEntity> entities = new ArrayList<PatientVisitEntity>();
		try {
			entities = entityManager.createQuery("select d from PatientVisitEntity d where d.studyId=:studyId and d.visitId=:visitId",PatientVisitEntity.class)
					.setParameter("studyId", studyId).setParameter("visitId", visitId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
	
	public PatientVisitEntity findByStudyPatientDoctorVisitId(Integer studyId, String patientId, Integer visitId, Integer siteId){
		PatientVisitEntity entities = new PatientVisitEntity();
		try {
			entities = entityManager.createQuery("select d from PatientVisitEntity d where d.studyId=:studyId and d.patientId=:patientId and d.visitId=:visitId and d.siteId=:siteId",PatientVisitEntity.class)
					.setParameter("studyId", studyId).setParameter("patientId", patientId).setParameter("visitId", visitId).setParameter("siteId", siteId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
}
