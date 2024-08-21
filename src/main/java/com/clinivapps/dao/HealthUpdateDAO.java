package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.clinivapps.entity.HealthUpdateEntity;

@Repository
public class HealthUpdateDAO extends ClinIVDAO<HealthUpdateEntity>{
	
	public List<HealthUpdateEntity> findAll(){
		List<HealthUpdateEntity> entities = new ArrayList<HealthUpdateEntity>();
		try {
			entities = entityManager.createQuery("select d from HealthUpdateEntity d",HealthUpdateEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public HealthUpdateEntity findByHealthId(Integer healthId){
		HealthUpdateEntity entities = new HealthUpdateEntity();
		try {
			entities = entityManager.createQuery("select d from HealthUpdateEntity d where d.healthId=:healthId",HealthUpdateEntity.class)
					.setParameter("healthId", healthId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	
}
