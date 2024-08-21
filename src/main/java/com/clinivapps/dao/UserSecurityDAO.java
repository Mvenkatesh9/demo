package com.clinivapps.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.clinivapps.entity.UserSecurityEntity;


@Repository
public class UserSecurityDAO extends ClinIVDAO<UserSecurityEntity>{

	private static final Log log = LogFactory.getLog(UserSecurityDAO.class);

	public UserSecurityEntity findByLoginId(String userSecLoginId) {
		log.info("User Sec Login Id:"+userSecLoginId);
		UserSecurityEntity securityEntity =  new UserSecurityEntity();
		try
		{
			securityEntity = entityManager.createQuery("select s from UserSecurityEntity s Where s.loginId = :loginId",UserSecurityEntity.class).setParameter("loginId", userSecLoginId).getSingleResult();
		}
		catch(Exception e)
		{
			log.error("Failed:"+e);
		}
		return securityEntity;
	}
	
	public UserSecurityEntity findBySecurityId(final Integer userSecId) {
        UserSecurityEntity securityEntity = new UserSecurityEntity();
        try {
            securityEntity = (UserSecurityEntity)this.entityManager.createQuery("select s from UserSecurityEntity s Where s.userSecId = :userSecId", UserSecurityEntity.class)
            		.setParameter("userSecId", userSecId).getSingleResult();
        }
        catch (Exception e) {
            UserSecurityDAO.log.error((Object)("Failed:" + e));
        }
        return securityEntity;
    }
	public void updatePassword(String email, String password) {
		try {
			entityManager.createNativeQuery("UPDATE c_user_security SET PASSWORD = '" + password + "' WHERE LOGIN_ID ='" + email+"'" ).executeUpdate();
			log.info("get successful");

		} catch (RuntimeException re)
		{
			log.error("get failed", re);
		}
	}

}

