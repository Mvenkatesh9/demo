package com.clinivapps.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import com.clinivapps.dao.StudyDAO;
import com.clinivapps.dao.QuestionDAO;
import com.clinivapps.dao.SectionDAO;
import com.clinivapps.entity.QuestionsEntity;
import com.clinivapps.model.QuestionModel;

@Service("questionService")
@Transactional
public class QuestionService {
		
	@Autowired
	SectionDAO sectionDAO;
	
	@Autowired
	QuestionDAO questionDAO;
	
	@Autowired
	StudyDAO studyDAO;
	
	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public List<QuestionModel> findAll(){
		List<QuestionModel> sectionList = new ArrayList<QuestionModel>();
		try {
			List<QuestionsEntity> entities = questionDAO.findAll();
			if(entities != null)
				for(QuestionsEntity e : entities) {
					QuestionModel model = new QuestionModel();
					model.setSectionId(e.getSectionId());
					model.setQuestionId(e.getQuestionId());
					model.setQuestionTitle(e.getQuestionTitle());
					model.setQuestionComments(e.getQuestionComments());
					model.setQuestionType(e.getQuestionType());
					model.setOptions(e.getOptions());
					model.setLength(e.getLength());
					model.setSdtm(e.getSdtm());
					model.setCdash(e.getCdash());
					model.setSectionId(e.getSectionId());
					model.setCreatedDate(formatter.format(e.getCreatedDate()));
					sectionList.add(model);
				}
		}catch(Exception e)
		{
		e.printStackTrace();	
		}
		return sectionList;
	} 
	
	public List<QuestionModel> findBySectionId(Integer sectionId){
		List<QuestionModel> questionList = new ArrayList<QuestionModel>();
		try {
			List<QuestionsEntity> entities = questionDAO.findBySectionId(sectionId);
			if(entities != null)
				for(QuestionsEntity e : entities) {
					QuestionModel model = new QuestionModel();
					model.setSectionId(e.getSectionId());
					model.setQuestionId(e.getQuestionId());
					model.setQuestionTitle(e.getQuestionTitle());
					model.setQuestionComments(e.getQuestionComments());
					model.setQuestionType(e.getQuestionType());
					model.setOptions(e.getOptions());
					model.setLength(e.getLength());
					model.setRequired(e.getRequired());
					model.setSdtm(e.getSdtm());
					model.setCdash(e.getCdash());
					model.setSectionId(e.getSectionId());
					model.setCreatedDate(formatter.format(e.getCreatedDate()));
					questionList.add(model);
				}
		}catch(Exception e)
		{
		e.printStackTrace();	
		}
		return questionList;
	} 
	public QuestionModel findByQuestionId(Integer questionId){
		QuestionModel model = new QuestionModel();
		try {
			QuestionsEntity e = questionDAO.findByQuestionId(questionId);
			if(e != null) {
					model.setSectionId(e.getSectionId());
					model.setQuestionId(e.getQuestionId());
					model.setQuestionTitle(e.getQuestionTitle());
					model.setQuestionComments(e.getQuestionComments());
					model.setQuestionType(e.getQuestionType());
					model.setOptions(e.getOptions());
					model.setLength(e.getLength());
					model.setRequired(e.getRequired());
					model.setSdtm(e.getSdtm());
					model.setCdash(e.getCdash());
					model.setSectionId(e.getSectionId());
					model.setCreatedDate(formatter.format(e.getCreatedDate()));
				}
		}catch(Exception e)
		{
		e.printStackTrace();	
		}
		return model;
	} 
	public void createQuestion(Model model) throws IOException{
		QuestionModel questionModel=(QuestionModel) model.asMap().get("question");
		  try {
			  QuestionsEntity questionEntity = new QuestionsEntity();
			  questionEntity.setQuestionTitle(questionModel.getQuestionTitle());
			  questionEntity.setQuestionType(questionModel.getQuestionType());
			  questionEntity.setOptions(questionModel.getOptions());
			  questionEntity.setSectionId(questionModel.getSectionId());
			  questionEntity.setCreatedDate(Calendar.getInstance().getTime());
			  questionDAO.persist(questionEntity);
		   }
		   catch(Exception e) {
			  System.out.println(e);
	 	 		
		   }

	}
	
}


