package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.clinivapps.entity.QueriesEntity;

@Repository
public class QueryDAO extends ClinIVDAO<QueriesEntity>{
	
	public List<QueriesEntity> findAll(){
		List<QueriesEntity> entities = new ArrayList<QueriesEntity>();
		try {
			entities = entityManager.createQuery("select d from QueriesEntity d order by d.createdDate desc",QueriesEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
	public List<QueriesEntity> findByVisitId(Integer visitId){
		List<QueriesEntity> entities = new ArrayList<QueriesEntity>();
		try {
			entities = entityManager.createQuery("select d from QueriesEntity d where d.visitId=:visitId order by d.createdDate desc",QueriesEntity.class)
					.setParameter("visitId", visitId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<QueriesEntity> findByPatientQuestionId(Integer questionId, String patientId){
		List<QueriesEntity> entities = new ArrayList<QueriesEntity>();
		try {
			entities = entityManager.createQuery("select d from QueriesEntity d where d.questionId=:questionId and d.patientId=:patientId order by d.createdDate desc",QueriesEntity.class)
					.setParameter("questionId", questionId).setParameter("patientId", patientId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public QueriesEntity findByQueryId(Integer id){
		QueriesEntity entities = new QueriesEntity();
		try {
			entities = entityManager.createQuery("select d from QueriesEntity d where d.id=:id order by d.createdDate desc",QueriesEntity.class)
					.setParameter("id", id).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<QueriesEntity> findByAssignedId(Integer assigneduserId){
		List<QueriesEntity> entities = new ArrayList<QueriesEntity>();
		try {
			entities = entityManager.createQuery("select d from QueriesEntity d where d.assigneduserId=:assigneduserId order by d.createdDate desc",QueriesEntity.class)
					.setParameter("assigneduserId", assigneduserId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<QueriesEntity> findByAssignedIdStatus(Integer assigneduserId, String status){
		List<QueriesEntity> entities = new ArrayList<QueriesEntity>();
		try {
			entities = entityManager.createQuery("select d from QueriesEntity d where d.assigneduserId=:assigneduserId and d.status=:status order by d.createdDate desc",QueriesEntity.class)
					.setParameter("assigneduserId", assigneduserId).setParameter("status", status).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public QueriesEntity findByPatientVisitQuestionId(Integer categoryId,Integer questionId,String patientId, String status){
		QueriesEntity entities = new QueriesEntity();
		try {
			entities = entityManager.createQuery("select d from QueriesEntity d where d.categoryId=:categoryId and d.questionId=:questionId "
					+ "and d.patientId=:patientId and d.status !=:status order by d.createdDate desc",QueriesEntity.class)
					.setParameter("categoryId", categoryId).setParameter("questionId", questionId).setParameter("patientId", patientId).setParameter("status", status).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public QueriesEntity findByPatientCategory(Integer categoryId,String patientId, String status){
		QueriesEntity entities = new QueriesEntity();
		try {
			entities = entityManager.createQuery("select d from QueriesEntity d where d.categoryId=:categoryId "
					+ "and d.patientId=:patientId and d.status !=:status order by d.createdDate desc",QueriesEntity.class)
					.setParameter("categoryId", categoryId).setParameter("patientId", patientId).setParameter("status", status).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
}

