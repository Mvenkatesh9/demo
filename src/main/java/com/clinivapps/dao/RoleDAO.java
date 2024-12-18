package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.clinivapps.entity.RoleEntity;


@Repository
public class RoleDAO extends ClinIVDAO<RoleEntity>{

	private static final Log log = LogFactory.getLog(RoleDAO.class);

	public List<RoleEntity> findAll() {
		log.info("getting All role instances");
		List<RoleEntity> instances = new ArrayList<RoleEntity>();
		try {
			instances = entityManager.createQuery("select r from RoleEntity r",RoleEntity.class).getResultList();
			log.info("get successful");			
		} catch (RuntimeException re) {
			log.error("get failed", re);
		}
		return instances;
	}
	public RoleEntity findByRoleId(Integer roleId) {
		RoleEntity roleEntity =  new RoleEntity();
		try
		{
			roleEntity = entityManager.createQuery("select r from RoleEntity r Where r.roleId = :roleId",RoleEntity.class).setParameter("roleId", roleId).getSingleResult();
		}
		catch(Exception e)
		{
			log.error("Failed : "+e);
		}
		return roleEntity;
	}
}
