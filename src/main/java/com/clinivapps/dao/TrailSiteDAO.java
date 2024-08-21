package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.clinivapps.entity.TrailSiteEntity;

@Repository
public class TrailSiteDAO extends ClinIVDAO<TrailSiteEntity>{
	
	public List<TrailSiteEntity> findAll(){
		List<TrailSiteEntity> entities = new ArrayList<TrailSiteEntity>();
		try {
			entities = entityManager.createQuery("select d from TrailSiteEntity d",TrailSiteEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	public List<TrailSiteEntity> findByStudyId(Integer studyId){
		List<TrailSiteEntity> entities = new ArrayList<TrailSiteEntity>();
		try {
			entities = entityManager.createQuery("select d from TrailSiteEntity d where d.studyId=:studyId",TrailSiteEntity.class).setParameter("studyId", studyId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	public TrailSiteEntity findBySiteId(Integer id){
		TrailSiteEntity entities = new TrailSiteEntity();
		try {
			entities = entityManager.createQuery("select d from TrailSiteEntity d where d.id=:id",TrailSiteEntity.class).setParameter("id", id).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
}
