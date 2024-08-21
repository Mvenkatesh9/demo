package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import com.clinivapps.entity.UserEntity;
import com.clinivapps.dao.UserDAO;


@Repository
public class UserDAO extends ClinIVDAO<UserEntity>{

	private static final Log log = LogFactory.getLog(UserDAO.class);

	public UserEntity findByEmailId(String emailId) {
		log.info("Getting UserEntity Instance with emailId: " + emailId);
		UserEntity userEntity =  new UserEntity();
		try{
			userEntity = entityManager.createQuery("select s from UserEntity s Where s.security.loginId = :emailId",UserEntity.class).setParameter("emailId", emailId).getSingleResult();
			log.info("get successfull");
		}
		catch(Exception e){
			log.error("Failed : "+e);
		}
		return userEntity;
	}
	public UserEntity findByMobileNo(String mobileNo) {
		UserEntity userEntity =  new UserEntity();
		try{
			userEntity = entityManager.createQuery("select s from UserEntity s Where s.mobileNo = :mobileNo",UserEntity.class).setParameter("mobileNo", mobileNo).getSingleResult();
			log.info("get successfull");
		}
		catch(Exception e){
			log.error("Failed : "+e);
		}
		return userEntity;
	}
	public UserEntity findByMobileNoAndRole(String mobileNo, Integer role) {
		UserEntity userEntity = new UserEntity();
		try {
			userEntity = entityManager.createQuery("select s from UserEntity s Where s.mobileNo = :mobileNo and s.role = :role", UserEntity.class)
					.setParameter("mobileNo", mobileNo).setParameter("role", role).getSingleResult();
			UserDAO.log.info("get successfull");
		}
		catch (Exception e) {
			UserDAO.log.error(("Failed : " + e));
		}
		return userEntity;
	}
	public UserEntity findBySecurityId(Integer id) {
		UserEntity userEntity =  new UserEntity();
		log.info("Security Id:"+id);
		try{
			userEntity = entityManager.createQuery("select s from UserEntity s Where s.security.userSecId = :userSecId",UserEntity.class).setParameter("userSecId", id).getSingleResult();
		}
		catch(Exception e)
		{
			log.error("Failed : "+e);
		}
		return userEntity;
	}

	public List<UserEntity> findByStatus(String status) {
		log.info("Getting UserEntity based on status : " +status);
		List<UserEntity> userEntities =  new ArrayList<UserEntity>();
		try{
			userEntities = entityManager.createQuery("select s from UserEntity s Where s.security.status = :status",UserEntity.class).setParameter("status", status).getResultList();
		}
		catch(Exception e)
		{
			log.error("Failed : "+e);
		}
		return userEntities;
	}
	public void updateRegistrationToken(String regToken, String username) {
		try {
			entityManager.createNativeQuery("UPDATE T_USER SET REG_TOKEN = '"+regToken+ "' WHERE EMAIL_ID ='" + username+"'" ).executeUpdate();
			log.info("get successful");

		} catch (RuntimeException re)
		{
			log.error("get failed", re);
		}
	}

	public UserEntity findByUserId(Integer id) {
		UserEntity userEntity =  new UserEntity();
		try{
			userEntity = entityManager.createQuery("select s from UserEntity s Where s.userId = :userId",UserEntity.class).setParameter("userId", id).getSingleResult();
		}
		catch(Exception e)
		{
			log.error("Failed : "+e);
		}
		return userEntity;
	}
	public List<UserEntity> rolesByUserId(Integer id) {
		List<UserEntity> userEntity =  new ArrayList<UserEntity>();
		int roleId=2;
		try{
			userEntity = entityManager.createQuery("select s from UserEntity s Where s.userId = :userId and s.role.roleId = :roleId",UserEntity.class).setParameter("userId", id).setParameter("roleId", roleId).getResultList();
		}
		catch(Exception e)
		{
			log.error("Failed : "+e);
		}
		return userEntity;
	}

	public List<UserEntity> getUserDetails(String userName) {
		List<UserEntity> userEntity =  new ArrayList<UserEntity>();
		log.info("user name="+userName);
		try{
			userEntity =  entityManager.createQuery("select s from UserEntity s Where s.emailId = :emailId",UserEntity.class).setParameter("emailId", userName).getResultList();
		}
		catch(Exception e){
			log.error("Failed : "+e);
		}
		return userEntity;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getLocationDetails(Integer userId) {
		List<Object[]> locationEntity =  new ArrayList<Object[]>();
		log.info("user name="+userId);
		try{
			locationEntity =  entityManager.createNativeQuery("SELECT LOCATION_ID, ADDRESS1,ADDRESS2, CITY, STATE, COUNTRY, ZIPCODE, TYPE, USER_ID FROM T_LOCATION WHERE  USER_ID=:userId").setParameter("userId", userId).getResultList();
		}
		catch(Exception e){
			log.error("Failed : "+e);
		}
		return locationEntity;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getUserId(String loginId) {
		List<Object[]> userId =  new ArrayList<Object[]>();
		log.info("user name="+loginId);
		try{
			userId =  entityManager.createNativeQuery("SELECT usr.USER_ID, usr.SECURITY_ID, usr.ROLE_ID, usr.FIRST_NAME, usr.MIDDLE_NAME, usr.LAST_NAME, usr.ALIAS, usr.TITLE, usr.PHONE_NO, usr.MOBILE_NO, usr.EMAIL_ID, usr.ALTERNATE_EMAIL_ID, usr.DP_ID, usr.STATUS, usr.REG_TOKEN FROM T_USER usr,T_USER_SECURITY sec WHERE sec.LOGIN_ID=:loginId and sec.USER_SEC_ID=usr.SECURITY_ID").setParameter("loginId", loginId).getResultList();
		}
		catch(Exception e){
			log.error("Failed : "+e);
		}
		return userId;
	}


	@SuppressWarnings("unchecked")
	public List<Object[]> getUserValues(int doctId) {
		List<Object[]> contactDetails =  new ArrayList<Object[]>();
		try{
			contactDetails =  entityManager.createNativeQuery("SELECT USER_ID FROM T_USER WHERE USER_ID= :doctId").setParameter("doctId", doctId).getResultList();
		}
		catch(Exception e){
			log.error("Failed : "+e);
		}
		return contactDetails;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getUserNames(Integer userId1) {
		List<Object[]> userId =  new ArrayList<Object[]>();
		log.info("user name="+userId1);
		try{
			userId =  entityManager.createNativeQuery("SELECT USER_ID, SECURITY_ID, ROLE_ID, FIRST_NAME, MIDDLE_NAME, LAST_NAME, ALIAS, TITLE, PHONE_NO, MOBILE_NO, EMAIL_ID, ALTERNATE_EMAIL_ID, DP_ID, STATUS, REG_TOKEN FROM T_USER WHERE USER_ID=:userId1").setParameter("userId1", userId1).getResultList();
		}
		catch(Exception e){
			log.error("Failed : "+e);
		}
		return userId;
	}
	public List<UserEntity> findAllManagers(){
		List<UserEntity> entites = null;
		Integer roleId = 10;
		try {
			entites = entityManager.createQuery("SELECT d FROM UserEntity d WHERE d.user.role=:roleId", UserEntity.class).setParameter("roleId", roleId).getResultList();

		}catch(Exception e) {
			e.printStackTrace();
		}
		return entites;
	}
	public UserEntity findByUserId(String rhcId){
		UserEntity entites = null;
		try {
			entites = entityManager.createQuery("select d from UserEntity d where d.security.loginId = :rhcId", UserEntity.class).setParameter("rhcId", rhcId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entites;
	}
}

