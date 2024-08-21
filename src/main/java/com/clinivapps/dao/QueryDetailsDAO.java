package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.clinivapps.entity.QueryDetailsEntity;

@Repository
public class QueryDetailsDAO extends ClinIVDAO<QueryDetailsEntity>{
	
	public List<QueryDetailsEntity> findAll(){
		List<QueryDetailsEntity> entities = new ArrayList<QueryDetailsEntity>();
		try {
			entities = entityManager.createQuery("select d from QueryDetailsEntity d order by d.createdDate desc",QueryDetailsEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
	public List<QueryDetailsEntity> findByQueryId(Integer queryId){
		List<QueryDetailsEntity> entities = new ArrayList<QueryDetailsEntity>();
		try {
			entities = entityManager.createQuery("select d from QueryDetailsEntity d where d.queryEnt.id=:queryId order by d.createdDate desc",QueryDetailsEntity.class)
					.setParameter("queryId", queryId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public QueryDetailsEntity findByDetailId(Integer id){
		QueryDetailsEntity entities = new QueryDetailsEntity();
		try {
			entities = entityManager.createQuery("select d from QueryDetailsEntity d where d.id=:id order by d.createdDate desc",QueryDetailsEntity.class)
					.setParameter("id", id).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
}

