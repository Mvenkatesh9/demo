package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.clinivapps.entity.SiteUsersEntity;

@Repository
public class SiteUserDAO extends ClinIVDAO<SiteUsersEntity>{	
	public List<SiteUsersEntity> findAll(){
		List<SiteUsersEntity> entities = new ArrayList<SiteUsersEntity>();
		try {
			entities = entityManager.createQuery("select d from SiteUsersEntity d",SiteUsersEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	public List<SiteUsersEntity> findByCompanyId(Integer companyId){
		List<SiteUsersEntity> entities = new ArrayList<SiteUsersEntity>();
		try {
			entities = entityManager.createQuery("select d from SiteUsersEntity d where d.companyId=:companyId",SiteUsersEntity.class).setParameter("companyId", companyId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<SiteUsersEntity> findByDoctorId(Integer doctorId){
		List<SiteUsersEntity> entities = new ArrayList<SiteUsersEntity>();
		try {
			entities = entityManager.createQuery("select d from SiteUsersEntity d where d.doctorId=:doctorId",SiteUsersEntity.class)
					.setParameter("doctorId", doctorId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
	public List<SiteUsersEntity> findByStudyId(Integer studyId){
		List<SiteUsersEntity> entities = new ArrayList<SiteUsersEntity>();
		try {
			entities = entityManager.createQuery("select d from SiteUsersEntity d where d.studyId=:studyId",SiteUsersEntity.class).setParameter("studyId", studyId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public SiteUsersEntity findByStudyAndDoctorId(Integer studyId, Integer doctorId){
		SiteUsersEntity entities = new SiteUsersEntity();
		try {
			entities = entityManager.createQuery("select d from SiteUsersEntity d where d.studyId=:studyId and d.doctorId=:doctorId",SiteUsersEntity.class)
					.setParameter("studyId", studyId).setParameter("doctorId", doctorId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public SiteUsersEntity findByUserId(Integer userId){
		SiteUsersEntity entities = new SiteUsersEntity();
		try {
			entities = entityManager.createQuery("select d from SiteUsersEntity d where d.userId=:userId",SiteUsersEntity.class)
					.setParameter("userId", userId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
}
