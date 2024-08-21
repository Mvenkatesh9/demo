package com.clinivapps.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.clinivapps.entity.DeviceTokenEntity;
import com.clinivapps.util.Util;


@Repository
public class DeviceTokenDAO extends ClinIVDAO<DeviceTokenEntity>{
	private static final Log log = LogFactory.getLog(DeviceTokenDAO.class);
	public List<DeviceTokenEntity> findBySingleUserId(int userId) {
		List<DeviceTokenEntity> instance = null;
		try {
			instance = entityManager.createQuery("select d from DeviceTokenEntity d where d.userId=:userId order by d.createdDate desc ", DeviceTokenEntity.class).setParameter("userId", userId).getResultList();
			log.info("get successful");
			System.out.println(instance.size()+"DAO");
		} catch (RuntimeException re) {
			log.error("get failed", re);
		}
		return instance;
	}
	public void deleteById(String canonicalId) {
		DeviceTokenEntity d=findById(DeviceTokenEntity.class, canonicalId);
		if(!Util.isEmpty(d))
			remove(d);


		// TODO Auto-generated method stub

	}

}
