package com.clinivapps.dao;

import com.clinivapps.entity.*;
import org.springframework.stereotype.*;
import org.apache.commons.logging.*;
import java.util.*;

@Repository
public class OTPDAO extends ClinIVDAO<OTPEntity>
{
    private static final Log log;
    
    static {
        log = LogFactory.getLog(OTPDAO.class);
    }
    
    public OTPEntity findByVerificationId(final String verificationId) {
        OTPDAO.log.info((Object)("getting OTP instance with verification Id: " + verificationId));
        OTPEntity instance = new OTPEntity();
        try {
            instance = (OTPEntity)this.entityManager.createQuery("select s from OTPEntity s Where s.verificationId = :verificationId", OTPEntity.class).setParameter("verificationId", (Object)verificationId).getSingleResult();
            OTPDAO.log.info((Object)"get successful");
        }
        catch (RuntimeException re) {
            OTPDAO.log.error((Object)"get failed", (Throwable)re);
        }
        return instance;
    }
    
    public List<OTPEntity> findBySecurityId(final Integer securityId) {
        OTPDAO.log.info((Object)("getting OTP instance with security Id: " + securityId));
        List<OTPEntity> instances = new ArrayList<OTPEntity>();
        try {
            instances = (List<OTPEntity>)this.entityManager.createQuery("select s from OTPEntity s Where s.securityId = :securityId", OTPEntity.class).setParameter("securityId", (Object)securityId).getResultList();
            OTPDAO.log.info((Object)"get successful");
        }
        catch (RuntimeException re) {
            OTPDAO.log.error((Object)"get failed", (Throwable)re);
        }
        return instances;
    }
}