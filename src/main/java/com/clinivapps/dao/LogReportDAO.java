package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.clinivapps.entity.LogReportEntity;

@Repository
public class LogReportDAO extends ClinIVDAO<LogReportEntity>{
	
	public List<LogReportEntity> findAll(){
		List<LogReportEntity> entities = new ArrayList<LogReportEntity>();
		try {
			entities = entityManager.createQuery("select d from LogReportEntity d",LogReportEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	public List<LogReportEntity> findByDate(Date startDate, Date endDate){
		List<LogReportEntity> entities = new ArrayList<LogReportEntity>();
		try {
			entities = entityManager.createQuery("select d from LogReportEntity d where DATE(d.createdDate) BETWEEN :startDate and :endDate order by d.createdDate desc",LogReportEntity.class)
					.setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	
}
