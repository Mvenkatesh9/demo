package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.clinivapps.entity.TherapeuticAreaEntity;

@Repository
public class TherapeuticDAO extends ClinIVDAO<TherapeuticAreaEntity>{
	
	public List<TherapeuticAreaEntity> findAll(){
		List<TherapeuticAreaEntity> entities = new ArrayList<TherapeuticAreaEntity>();
		try {
			entities = entityManager.createQuery("select d from TherapeuticAreaEntity d",TherapeuticAreaEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	public TherapeuticAreaEntity findByThId(Integer therapeuticId){
		TherapeuticAreaEntity entities = new TherapeuticAreaEntity();
		try {
			entities = entityManager.createQuery("select d from TherapeuticAreaEntity d where d.therapeuticId=:therapeuticId",TherapeuticAreaEntity.class).setParameter("therapeuticId", therapeuticId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
}
