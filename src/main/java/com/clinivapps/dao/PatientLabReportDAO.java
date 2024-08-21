package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.clinivapps.entity.PatientLabReportsEntity;

@Repository
public class PatientLabReportDAO extends ClinIVDAO<PatientLabReportsEntity>{
	
	public List<PatientLabReportsEntity> findAll(){
		List<PatientLabReportsEntity> entities = new ArrayList<PatientLabReportsEntity>();
		try {
			entities = entityManager.createQuery("select d from PatientLabReportsEntity d",PatientLabReportsEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	public List<PatientLabReportsEntity> findByPatientId(String patientId){
		List<PatientLabReportsEntity> entities = new ArrayList<PatientLabReportsEntity>();
		try {
			entities = entityManager.createQuery("select d from PatientLabReportsEntity d where d.patientId=:patientId",PatientLabReportsEntity.class)
					.setParameter("patientId", patientId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<PatientLabReportsEntity> findByPatientAndStudyIdAndDates(String patientId, Integer studyId, Date startDate, Date endDate){
		List<PatientLabReportsEntity> entities = new ArrayList<PatientLabReportsEntity>();
		try {
			entities = entityManager.createQuery("select d from PatientLabReportsEntity d where d.patientId=:patientId and d.studyId=:studyId and DATE(d.createdDate) BETWEEN :startDate and :endDate",PatientLabReportsEntity.class)
					.setParameter("patientId", patientId).setParameter("studyId", studyId).setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<PatientLabReportsEntity> findByPatientAndStudyIdAndMonth(Integer patientId, Integer studyId){
		List<PatientLabReportsEntity> entities = new ArrayList<PatientLabReportsEntity>();
		try {
			entities = entityManager.createQuery("select d from PatientLabReportsEntity d where d.patientId=:patientId and d.studyId=:studyId",PatientLabReportsEntity.class)
					.setParameter("patientId", patientId).setParameter("studyId", studyId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
}
