package com.clinivapps.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.clinivapps.dao.StudyDAO;
import com.clinivapps.dao.StudyPatientsDAO;
import com.clinivapps.dao.StudyVisitDAO;
import com.clinivapps.dao.CRFQuestionDAO;
import com.clinivapps.dao.CRFSectionDAO;
import com.clinivapps.dao.CommonFormDAO;
import com.clinivapps.dao.LogReportDAO;
import com.clinivapps.dao.PatientVisitsDAO;
import com.clinivapps.dao.QueryDAO;
import com.clinivapps.dao.QueryDetailsDAO;
import com.clinivapps.dao.QuestionDAO;
import com.clinivapps.dao.RoleDAO;
import com.clinivapps.dao.SectionDAO;
import com.clinivapps.dao.SiteUserDAO;
import com.clinivapps.dao.TrailSiteDAO;
import com.clinivapps.dao.UserDAO;
import com.clinivapps.entity.CRFQuestionsEntity;
import com.clinivapps.entity.CommonFormEntity;
import com.clinivapps.entity.LogReportEntity;
import com.clinivapps.entity.QueriesEntity;
import com.clinivapps.entity.QueryDetailsEntity;
import com.clinivapps.entity.QuestionsEntity;
import com.clinivapps.entity.RoleEntity;
import com.clinivapps.entity.SectionEntity;
import com.clinivapps.entity.StudyPatientsEntity;
import com.clinivapps.model.QueryDetailsModel;
import com.clinivapps.model.QueryModel;

@Service("queryService")
@Transactional
public class QueryService {


	@Autowired
	TrailSiteDAO trailSiteDAO;

	@Autowired
	StudyVisitDAO studyVisitDAO;

	@Autowired
	StudyPatientsDAO studyPatientsDAO;

	@Autowired
	UserDAO userDAO;

	@Autowired
	PatientVisitsDAO patientVisitDAO;

	@Autowired
	SiteUserDAO siteUserDAO;

	@Autowired
	QueryDAO queryDAO;

	@Autowired
	QuestionDAO questionDAO;

	@Autowired
	SectionDAO sectionDAO;

	@Autowired
	CRFSectionDAO crfSectionDAO;

	@Autowired
	QueryDetailsDAO queryDetailsDAO;

	@Autowired
	CRFQuestionDAO crfQuestionDAO;
	
	@Autowired
	CommonFormDAO commonFormDAO;

	@Autowired
	RoleDAO roleDAO;

	@Autowired
	LogReportDAO logReportDAO;

	SimpleDateFormat dateYearFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public List<QueryModel> findAll(){
		List<QueryModel> models = new ArrayList<QueryModel>();
		try {
			List<QueriesEntity> queryEntities = queryDAO.findAll();
			for(QueriesEntity e : queryEntities) {
				QueryModel model = new QueryModel();
				model.setAssignedUserId(e.getAssigneduserId());
				model.setAssignedRole(e.getAssignedRole());
				model.setCreatedDate(dateYearFormat.format(e.getCreatedDate()));
				model.setPatientId(e.getPatientId());
				model.setQueryId(e.getId());
				model.setStatus(e.getStatus());
				model.setRaisedUserId(e.getRaiseduserId());
				model.setCategoryId(e.getCategoryId());
				model.setSectionId(e.getSectionId());
				SectionEntity visitEnt = sectionDAO.findBySectionId(e.getSectionId());
				model.setVisitName(visitEnt.getTitle());
				QuestionsEntity quesEnt = questionDAO.findByQuestionId(e.getQuestionId());
				model.setQuestionName(quesEnt.getQuestionTitle());
				models.add(model);
			}					
		}
		catch(Exception e){
			e.printStackTrace();	
		}
		return models;
	}
	public List<QueryModel> findByPaientQuestionId(String patientId, Integer questionId){
		List<QueryModel> models = new ArrayList<QueryModel>();
		try {
			List<QueriesEntity> queryEntities = queryDAO.findByPatientQuestionId(questionId,patientId);
			for(QueriesEntity e : queryEntities) {
				QueryModel model = new QueryModel();
				model.setAssignedUserId(e.getAssigneduserId());
				model.setAssignedRole(e.getAssignedRole());
				model.setCreatedDate(dateYearFormat.format(e.getCreatedDate()));
				model.setPatientId(e.getPatientId());
				model.setQueryId(e.getId());
				model.setStatus(e.getStatus());
				model.setRaisedUserId(e.getRaiseduserId());
				model.setCategoryId(e.getCategoryId());
				model.setSectionId(e.getSectionId());
				SectionEntity visitEnt = sectionDAO.findBySectionId(e.getSectionId());
				model.setVisitName(visitEnt.getTitle());
				QuestionsEntity quesEnt = questionDAO.findByQuestionId(e.getQuestionId());
				model.setQuestionName(quesEnt.getQuestionTitle());
				models.add(model);
			}					
		}
		catch(Exception e){
			e.printStackTrace();	
		}
		return models;
	}
	public List<QueryModel> findByAssignedId(Integer assignedId){
		List<QueryModel> models = new ArrayList<QueryModel>();
		try {
			List<QueriesEntity> queryEntities = queryDAO.findByAssignedId(assignedId);
			for(QueriesEntity e : queryEntities) {
				QueryModel model = new QueryModel();
				model.setAssignedUserId(e.getAssigneduserId());
				model.setAssignedRole(e.getAssignedRole());
				model.setCreatedDate(dateYearFormat.format(e.getCreatedDate()));
				model.setPatientId(e.getPatientId());
				model.setQueryId(e.getId());
				model.setQuestionId(e.getQuestionId());
				model.setStatus(e.getStatus());
				model.setRaisedUserId(e.getRaiseduserId());
				model.setCategoryId(e.getCategoryId());
				model.setSectionId(e.getSectionId());
				SectionEntity visitEnt = sectionDAO.findBySectionId(e.getSectionId());
				model.setVisitName(visitEnt.getTitle());
				QuestionsEntity quesEnt = questionDAO.findByQuestionId(e.getQuestionId());
				model.setQuestionName(quesEnt.getQuestionTitle());
				models.add(model);
			}					
		}
		catch(Exception e){
			e.printStackTrace();	
		}
		return models;
	}
	public List<QueryModel> findByAssignedIdAndStatus(Integer assignedId, String status){
		List<QueryModel> models = new ArrayList<QueryModel>();
		try {
			List<QueriesEntity> queryEntities = queryDAO.findByAssignedIdStatus(assignedId, status);
			for(QueriesEntity e : queryEntities) {
				QueryModel model = new QueryModel();
				model.setAssignedUserId(e.getAssigneduserId());
				model.setAssignedRole(e.getAssignedRole());
				model.setCreatedDate(dateYearFormat.format(e.getCreatedDate()));
				model.setPatientId(e.getPatientId());
				model.setQueryId(e.getId());
				model.setQuestionId(e.getQuestionId());
				model.setStatus(e.getStatus());
				model.setRaisedUserId(e.getRaiseduserId());
				model.setCategoryId(e.getCategoryId());
				model.setSectionId(e.getSectionId());
				SectionEntity visitEnt = sectionDAO.findBySectionId(e.getSectionId());
				model.setVisitName(visitEnt.getTitle());
				QuestionsEntity quesEnt = questionDAO.findByQuestionId(e.getQuestionId());
				model.setQuestionName(quesEnt.getQuestionTitle());
				models.add(model);
			}					
		}
		catch(Exception e){
			e.printStackTrace();	
		}
		return models;
	}

	public List<QueryDetailsModel> findDetailsByQueryId(Integer queryId){
		List<QueryDetailsModel> models = new ArrayList<QueryDetailsModel>();
		try {
			List<QueryDetailsEntity> queryEntities = queryDetailsDAO.findByQueryId(queryId);
			for(QueryDetailsEntity e : queryEntities) {
				QueryDetailsModel model = new QueryDetailsModel();
				model.setCreatedDate(dateYearFormat.format(e.getCreatedDate()));
				model.setDetailId(e.getId());
				model.setQueryId(queryId);
				model.setStatus(e.getStatus());
				model.setOriginalAnswer(e.getOriginalAnswer());
				model.setModifiedAnswer(e.getModifiedAnswer());
				model.setUpdatedFrom(e.getUpdatedFrom());
				model.setComments(e.getComments());
				model.setPatientId(e.getQueryEnt().getPatientId());
				QuestionsEntity quesEnt = questionDAO.findByQuestionId(e.getQuestionId());
				model.setQuestionName(quesEnt.getQuestionTitle());
				model.setQuestionType(quesEnt.getQuestionType());
				model.setQuestionOptions(quesEnt.getOptions());
				model.setQuestionComments(quesEnt.getQuestionComments());
				models.add(model);
			}					
		}
		catch(Exception e){
			e.printStackTrace();	
		}
		return models;
	}

	public void createQuery(Model model, Integer roleId) throws IOException{
		QueryModel queryModel=(QueryModel) model.asMap().get("query");
		try {
			QueriesEntity queryEntity = new QueriesEntity();
			queryEntity.setAssignedRole(queryModel.getAssignedRole());
			if (queryModel.getAssignedRole().equals("SITE")) {
				StudyPatientsEntity patientEnt = studyPatientsDAO.findByPatientId(queryModel.getPatientId());
				queryEntity.setAssigneduserId(patientEnt.getTrailSiteId());
			}
			queryEntity.setCreatedDate(Calendar.getInstance().getTime());
			queryEntity.setPatientId(queryModel.getPatientId());
			queryEntity.setQuestionId(queryModel.getQuestionId());
			queryEntity.setRaiseduserId(queryModel.getRaisedUserId());
			queryEntity.setStatus("OPEN");
			queryEntity.setCategoryId(queryModel.getCategoryId());
			queryEntity.setSectionId(queryModel.getSectionId());
			QueryDetailsEntity detailEnt = new QueryDetailsEntity();
			detailEnt.setCreatedDate(Calendar.getInstance().getTime());
			detailEnt.setComments(queryModel.getComments());
			detailEnt.setStatus("OPEN");
			detailEnt.setQuestionId(queryModel.getQuestionId());
			detailEnt.setUpdatedUserId(queryModel.getRaisedUserId());
			if (queryModel.getSectionId() == 5) {
				CommonFormEntity commonForm = commonFormDAO.findByPatientAndQuestionCategory(queryModel.getPatientId(),queryModel.getCategoryId(),queryModel.getQuestionId());
				detailEnt.setOriginalAnswer(commonForm.getAnswer());
			}
			else {
				CRFQuestionsEntity crfQuestionsEntity = crfQuestionDAO.findBySectionQuestionAndCategory( queryModel.getPatientId(),queryModel.getQuestionId(), queryModel.getCategoryId());
				detailEnt.setOriginalAnswer(crfQuestionsEntity.getAnswer());
			}
		
			RoleEntity roleEnt = roleDAO.findByRoleId(roleId);
			detailEnt.setUpdatedFrom(roleEnt.getRoleName());
			detailEnt.setQueryEnt(queryEntity);
			queryDetailsDAO.persist(detailEnt);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public void updateQuery(QueryDetailsModel queryModel, Integer roleId) throws IOException{
		try {
			String oldAnswer = "";
			QueryDetailsEntity queryDetailEntity = queryDetailsDAO.findByDetailId(queryModel.getDetailId());

			QueryDetailsEntity newEnt = new QueryDetailsEntity();
			newEnt.setCreatedDate(Calendar.getInstance().getTime());
			newEnt.setComments(queryModel.getComments());
			newEnt.setQuestionId(queryDetailEntity.getQuestionId());
			newEnt.setUpdatedUserId(queryModel.getUpdatedUserId());
			RoleEntity roleEnt = roleDAO.findByRoleId(roleId);
			newEnt.setUpdatedFrom(roleEnt.getRoleName());
			QueriesEntity queryEnt = queryDAO.findByQueryId(queryModel.getQueryId());
			newEnt.setQueryEnt(queryEnt);
			newEnt.setOriginalAnswer(queryDetailEntity.getOriginalAnswer());
			newEnt.setModifiedAnswer(queryModel.getModifiedAnswer());
			if (queryEnt.getSectionId() == 5) {
				CommonFormEntity commonForm = commonFormDAO.findByPatientAndQuestionCategory(queryModel.getPatientId(),queryEnt.getCategoryId(),queryModel.getQuestionId());
				oldAnswer = commonForm.getAnswer();
				commonForm.setAnswer(queryModel.getModifiedAnswer());
				commonFormDAO.merge(commonForm);
			}
			else {
				CRFQuestionsEntity crfQuestionEnt = crfQuestionDAO.findBySectionQuestionAndCategory(queryEnt.getPatientId(),queryEnt.getQuestionId(), queryEnt.getCategoryId());
				oldAnswer = crfQuestionEnt.getAnswer();
				crfQuestionEnt.setAnswer(queryModel.getModifiedAnswer());
				crfQuestionDAO.merge(crfQuestionEnt);
			}


			queryDetailEntity.setStatus("CLOSED");
			queryDetailsDAO.merge(queryDetailEntity);

			newEnt.setStatus("OPEN");
			queryDetailsDAO.persist(newEnt);

			queryEnt.setStatus("ANSWERED");
			queryDAO.merge(queryEnt);

			QuestionsEntity questionEnt = questionDAO.findByQuestionId(queryEnt.getQuestionId());
			LogReportEntity entity = new LogReportEntity();
			entity.setActivity("FIELD UPDATED - " + questionEnt.getQuestionTitle());
			entity.setDescription("Update Value From " + oldAnswer + " - To " + queryModel.getModifiedAnswer());
			entity.setUserId(queryModel.getUpdatedUserId());
			entity.setCreatedDate(Calendar.getInstance().getTime());
			entity.setTrialParticipant(queryEnt.getPatientId());
			entity.setQuestionId(queryEnt.getQuestionId());
			entity.setOriginalAnswer(oldAnswer);
			entity.setModifiedAnswer(queryModel.getModifiedAnswer());
			logReportDAO.persist(entity);

		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public void updateQueryStatus(QueryDetailsModel queryModel, Integer roleId, String status, Integer userId) throws IOException{
		try {
			QueryDetailsEntity queryDetailEntity = queryDetailsDAO.findByDetailId(queryModel.getDetailId());
			QueriesEntity queryEnt = queryDAO.findByQueryId(queryModel.getQueryId());

			// New Query If Status is OPEN
			QueryDetailsEntity newEnt = new QueryDetailsEntity();
			newEnt.setCreatedDate(Calendar.getInstance().getTime());
			newEnt.setComments("Query Not Closed!");
			newEnt.setQuestionId(queryDetailEntity.getQuestionId());
			newEnt.setUpdatedUserId(userId);
			RoleEntity roleEnt = roleDAO.findByRoleId(roleId);
			newEnt.setUpdatedFrom(roleEnt.getRoleName());
			newEnt.setQueryEnt(queryEnt);
			newEnt.setOriginalAnswer(queryDetailEntity.getOriginalAnswer());

			if (!status.equals("CLOSED")) {
				queryEnt.setStatus("REQUERY");
				queryDAO.merge(queryEnt);
				newEnt.setStatus("OPEN");
				queryDetailsDAO.persist(newEnt);
				queryDetailEntity.setStatus("CLOSED");
				queryDetailEntity.setComments("New Query Raised");
				queryDetailsDAO.merge(queryDetailEntity);
			}
			else {
				queryDetailEntity.setStatus(status);
				queryDetailEntity.setComments(queryModel.getComments());
				queryDetailsDAO.merge(queryDetailEntity);
				queryEnt.setStatus("CLOSED");
				queryDAO.merge(queryEnt);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
