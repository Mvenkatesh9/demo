package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.clinivapps.entity.SponsorUsersEntity;

@Repository
public class SponsorUserDAO extends ClinIVDAO<SponsorUsersEntity>{
	
	public List<SponsorUsersEntity> findAll(){
		List<SponsorUsersEntity> entities = new ArrayList<SponsorUsersEntity>();
		try {
			entities = entityManager.createQuery("select d from SponsorUsersEntity d",SponsorUsersEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<SponsorUsersEntity> findByCompanyId(Integer companyId){
		List<SponsorUsersEntity> entities = new ArrayList<SponsorUsersEntity>();
		try {
			entities = entityManager.createQuery("select d from SponsorUsersEntity d where d.companyId=:companyId",SponsorUsersEntity.class).setParameter("companyId", companyId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public SponsorUsersEntity findByManagerId(Integer id){
		SponsorUsersEntity entities = new SponsorUsersEntity();
		try {
			entities = entityManager.createQuery("select d from SponsorUsersEntity d where d.id=:id",SponsorUsersEntity.class).setParameter("id", id).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
}
