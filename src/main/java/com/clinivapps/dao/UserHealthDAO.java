package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.clinivapps.entity.UserHealthEntity;

@Repository
public class UserHealthDAO extends ClinIVDAO<UserHealthEntity>{
	
	public List<UserHealthEntity> findAll(){
		List<UserHealthEntity> entities = new ArrayList<UserHealthEntity>();
		try {
			entities = entityManager.createQuery("select d from UserHealthEntity d",UserHealthEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
	public List<UserHealthEntity> findByUserAndDates(String patientId, Date startDate, Date endDate){
		List<UserHealthEntity> entities = new ArrayList<UserHealthEntity>();
		try {
			entities = entityManager.createQuery("select d from UserHealthEntity d where d.patientId=:patientId and DATE(d.createdDate) BETWEEN :startDate and :endDate",UserHealthEntity.class)
					.setParameter("patientId", patientId).setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
	
}
