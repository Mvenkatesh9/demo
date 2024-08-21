package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.clinivapps.entity.WithdrawlEntity;

@Repository
public class WithdrawlDAO extends ClinIVDAO<WithdrawlEntity>{
	
	public List<WithdrawlEntity> findAll(){
		List<WithdrawlEntity> entities = new ArrayList<WithdrawlEntity>();
		try {
			entities = entityManager.createQuery("select d from WithdrawlEntity d",WithdrawlEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	public List<WithdrawlEntity> findByPatientId(String patientId){
		List<WithdrawlEntity> entities = new ArrayList<WithdrawlEntity>();
		try {
			entities = entityManager.createQuery("select d from WithdrawlEntity d where d.patientId=:patientId",WithdrawlEntity.class)
					.setParameter("patientId", patientId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
}
