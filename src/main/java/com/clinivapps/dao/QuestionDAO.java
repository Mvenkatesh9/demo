package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.clinivapps.entity.QuestionsEntity;

@Repository
public class QuestionDAO extends ClinIVDAO<QuestionsEntity>{
	
	public List<QuestionsEntity> findAll(){
		List<QuestionsEntity> entities = new ArrayList<QuestionsEntity>();
		try {
			entities = entityManager.createQuery("select d from QuestionsEntity d order by d.createdDate desc",QuestionsEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
	public List<QuestionsEntity> findBySectionId(Integer sectionId){
		System.out.print(sectionId + "-");

		List<QuestionsEntity> entities = new ArrayList<QuestionsEntity>();
		try {
			entities = entityManager.createQuery("select d from QuestionsEntity d where d.sectionId=:sectionId",QuestionsEntity.class)
					.setParameter("sectionId", sectionId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
	public QuestionsEntity findByQuestionId(Integer questionId){
		QuestionsEntity entities = new QuestionsEntity();
		try {
			entities = entityManager.createQuery("select d from QuestionsEntity d where d.questionId=:questionId",QuestionsEntity.class)
					.setParameter("questionId", questionId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
}

