package com.clinivapps.dao;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.clinivapps.entity.DisplayPictureEntity;


@Repository
public class DisplayPictureDAO extends ClinIVDAO<DisplayPictureEntity>{

	private static final Log log = LogFactory.getLog(DisplayPictureDAO.class);
public DisplayPictureEntity findDPImage(Integer id) {
	log.info("Getting Entity Instance with ID: " + id);
	DisplayPictureEntity instance = null;
	try {
		instance = entityManager.createQuery("select d from DisplayPictureEntity d where d.dpId = :dpId ",DisplayPictureEntity.class).setParameter("dpId", id).getSingleResult();
		log.info("get successful");
		return instance;
	} catch (RuntimeException re) {
		log.error("get failed", re);
	}
	return instance;	
	
}
}
