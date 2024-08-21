package com.clinivapps.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import com.clinivapps.dao.StudyDAO;
import com.clinivapps.dao.StudyPatientsDAO;
import com.clinivapps.dao.AdverseEventDAO;
import com.clinivapps.dao.CRFQuestionDAO;
import com.clinivapps.dao.CRFSectionDAO;
import com.clinivapps.dao.CommonFormDAO;
import com.clinivapps.dao.LogReportDAO;
import com.clinivapps.dao.PatientVisitsDAO;
import com.clinivapps.dao.QuestionDAO;
import com.clinivapps.dao.SectionDAO;
import com.clinivapps.entity.StudyEntity;
import com.clinivapps.entity.StudyPatientsEntity;
import com.clinivapps.entity.AdverseEventsEntity;
import com.clinivapps.entity.CRFQuestionsEntity;
import com.clinivapps.entity.CRFSectionsEntity;
import com.clinivapps.entity.CommonFormEntity;
import com.clinivapps.entity.LogReportEntity;
import com.clinivapps.entity.PatientVisitEntity;
import com.clinivapps.entity.QuestionsEntity;
import com.clinivapps.entity.SectionEntity;
import com.clinivapps.model.CRFQuestionModel;
import com.clinivapps.model.LogReportModel;
import com.clinivapps.model.SectionModel;
import com.clinivapps.model.UpdateCRFQuestionModel;
import com.clinivapps.model.VisitQuestionModel;
import com.clinivapps.model.VisitSectionModel;

@Service("sectionService")
@Transactional
public class SectionService {

	@Autowired
	SectionDAO sectionDAO;

	@Autowired
	StudyDAO studyDAO;

	@Autowired
	CRFQuestionDAO crfQuestionDAO;

	@Autowired
	CRFSectionDAO crfSectionDAO;

	@Autowired
	AdverseEventDAO adverseEventsDAO;

	@Autowired
	QuestionDAO questionDAO;

	@Autowired
	PatientVisitsDAO patientVisitsDAO;

	@Autowired
	CommonFormDAO commonFormDAO;

	@Autowired
	LogReportDAO logReportDAO;

	@Autowired
	AdverseEventDAO advDAO;

	@Autowired
	StudyPatientsDAO studyPatientDAO;


	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	DateFormat dyformatter = new SimpleDateFormat("yyyy-MM-dd");

	public List<SectionModel> findAll(){
		List<SectionModel> sectionList = new ArrayList<SectionModel>();
		try {
			List<SectionEntity> entities = sectionDAO.findAll();
			if(entities != null)
				for(SectionEntity e : entities) {
					SectionModel model = new SectionModel();
					model.setSectionId(e.getSectionId());
					model.setTitle(e.getTitle());
					model.setStudyId(e.getStudyId());
					model.setDescription(e.getDescription());
					model.setCreatedDate(formatter.format(e.getCreatedDate()));
					sectionList.add(model);
				}
		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return sectionList;
	} 

	public List<SectionModel> findByStudyId(Integer crfId){
		List<SectionModel> sectionList = new ArrayList<SectionModel>();
		try {
			List<SectionEntity> entities = sectionDAO.findByStudyId(crfId);
			if(entities != null)
				for(SectionEntity e : entities) {
					SectionModel model = new SectionModel();
					model.setSectionId(e.getSectionId());
					model.setTitle(e.getTitle());
					model.setStudyId(e.getStudyId());
					model.setDescription(e.getDescription());
					model.setCreatedDate(formatter.format(e.getCreatedDate()));
					sectionList.add(model);
				}
		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return sectionList;
	} 
	public List<SectionModel> findByStudyIdAndDate(Integer studyId, String joinedDate){
		List<SectionModel> sectionList = new ArrayList<SectionModel>();
		try {
			Integer displayDays = 0;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String presentDate= dateFormat.format(Calendar.getInstance().getTime());
			Date jndDate = dateFormat.parse(joinedDate);
			joinedDate = dateFormat.format(jndDate);
			displayDays = daysBetweenDates(joinedDate, presentDate);
			List<SectionEntity> entities = sectionDAO.findByStudyIdAndDays(studyId, displayDays);
			if(entities != null)
				for(SectionEntity e : entities) {
					SectionModel model = new SectionModel();
					model.setSectionId(e.getSectionId());
					model.setTitle(e.getTitle());
					model.setStudyId(e.getStudyId());
					model.setDescription(e.getDescription());
					StudyEntity crfEntity = studyDAO.findByStudyId(e.getStudyId());
					model.setStudyName(crfEntity.getStudyName());
					model.setCreatedDate(formatter.format(e.getCreatedDate()));
					sectionList.add(model);
				}
		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return sectionList;
	} 
	public int daysBetweenDates(String date1, String date2) {
		LocalDate dt1 = LocalDate.parse(date1);
		LocalDate dt2= LocalDate.parse(date2);

		long diffDays = ChronoUnit.DAYS.between(dt1, dt2);

		return Math.abs((int)diffDays);
	}
	public void createSection(Model model) throws IOException{
		SectionModel sectionModel=(SectionModel) model.asMap().get("section");
		try {

			SectionEntity sectionEntity = new SectionEntity();
			sectionEntity.setStudyId(sectionModel.getStudyId());
			sectionEntity.setDescription(sectionModel.getDescription());
			sectionEntity.setTitle(sectionModel.getTitle());
			sectionEntity.setCreatedDate(Calendar.getInstance().getTime());

			sectionDAO.persist(sectionEntity);
		}
		catch(Exception e) {
			System.out.println(e);

		}
	}
	public VisitSectionModel findVisitsByPatientAndCategory(Integer categoryId, String patientId){
		VisitSectionModel model = new VisitSectionModel();
		try {
			CRFSectionsEntity secEntity = crfSectionDAO.findByCategoryId(categoryId);
			List<VisitQuestionModel> questionmodels = new ArrayList<VisitQuestionModel>();	
			if (secEntity != null) {
				List<QuestionsEntity> questions = questionDAO.findBySectionId(secEntity.getStudySectionId());
				if (questions != null) {
					for(QuestionsEntity question : questions) {
						VisitQuestionModel questionModel = new VisitQuestionModel();
						questionModel.setQuestionId(question.getQuestionId());
						questionModel.setQuestionTitle(question.getQuestionTitle());
						questionModel.setQuestionType(question.getQuestionType());
						questionModel.setOptions(question.getOptions());
						questionModel.setRequired(question.getRequired());
						questionModel.setSdtm(question.getSdtm());
						questionModel.setLength(question.getLength());
						questionModel.setCdash(question.getCdash());
						questionModel.setQuestionComments(question.getQuestionComments());
						questionModel.setSectionId(question.getSectionId());
						if (question.getSectionId() == 1 || question.getSectionId() == 2 || question.getSectionId() == 3 || question.getSectionId() == 4) {
							CRFQuestionsEntity crfQuestionsEntity = crfQuestionDAO.findBySectionQuestionAndCategory(patientId, question.getQuestionId(), categoryId);
							questionModel.setAnswer(crfQuestionsEntity.getAnswer());
						}
						else if (question.getSectionId() == 6) {
							AdverseEventsEntity adveEntity = advDAO.findByPatientAndQuestionCategory(patientId, categoryId, question.getQuestionId());
							questionModel.setAnswer(adveEntity.getAnswer());
						}
						else {
							CommonFormEntity formEntity = commonFormDAO.findByPatientAndQuestionCategory(patientId, categoryId, question.getQuestionId());
							questionModel.setAnswer(formEntity.getAnswer());
						}
						questionmodels.add(questionModel);
					}
				}
			}
			model.setQuestions(questionmodels);

		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return model;
	}
	public void submitQuestions(List<CRFQuestionModel> crfQuestionList) throws IOException{
		try {
			PatientVisitEntity visitEntity = patientVisitsDAO.findByStudyPatientVisitId(crfQuestionList.get(0).getStudyId(), crfQuestionList.get(0).getPatientId(), crfQuestionList.get(0).getVisitId());
			if (visitEntity.getId() != null) {
				SectionEntity secEnt = sectionDAO.findBySectionId(crfQuestionList.get(0).getSectionId());
				CRFSectionsEntity sectionEntity = new CRFSectionsEntity();
				sectionEntity.setCreatedDate(Calendar.getInstance().getTime());
				sectionEntity.setUserId(crfQuestionList.get(0).getUserId());
				sectionEntity.setStudySectionId(crfQuestionList.get(0).getSectionId());
				sectionEntity.setPatientId(crfQuestionList.get(0).getPatientId());
				sectionEntity.setTitle(secEnt.getTitle());
				crfSectionDAO.persist(sectionEntity);

				for (int i = 0; i < crfQuestionList.size(); i++) {
					CRFQuestionModel questionModel = crfQuestionList.get(i);
					CRFQuestionsEntity questionEntity = new CRFQuestionsEntity();
					questionEntity.setQuestionId(questionModel.getQuestionId());
					questionEntity.setCategoryId(sectionEntity.getSectionId());
					questionEntity.setAnswer(questionModel.getAnswer());
					questionEntity.setCreatedDate(Calendar.getInstance().getTime());
					questionEntity.setRemarks(questionModel.getRemarks());
					questionEntity.setStudyId(questionModel.getStudyId());
					questionEntity.setSectionId(questionModel.getSectionId());
					questionEntity.setUserId(questionModel.getUserId());
					questionEntity.setPatientId(questionModel.getPatientId());
					crfQuestionDAO.persist(questionEntity);
				}
				if (visitEntity.getStatus().equals("OPEN")) {
					visitEntity.setStatus("SUBMITTED");
					patientVisitsDAO.merge(visitEntity);
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public void submitAEQuestions(List<CRFQuestionModel> crfQuestionList) throws IOException{
		try {
			CRFSectionsEntity sectionEntity = new CRFSectionsEntity();
			sectionEntity.setCreatedDate(formatter.parse(crfQuestionList.get(0).getCreatedDate()));
			sectionEntity.setUserId(crfQuestionList.get(0).getUserId());
			sectionEntity.setStudySectionId(crfQuestionList.get(0).getSectionId());
			sectionEntity.setPatientId(crfQuestionList.get(0).getPatientId());
			SectionEntity secEnt = sectionDAO.findBySectionId(crfQuestionList.get(0).getSectionId());
			sectionEntity.setTitle(secEnt.getTitle());
			crfSectionDAO.persist(sectionEntity);
			for (int i = 0; i < crfQuestionList.size(); i++) {
				CRFQuestionModel questionModel = crfQuestionList.get(i);
				AdverseEventsEntity questionEntity = new AdverseEventsEntity();
				questionEntity.setQuestionId(questionModel.getQuestionId());
				questionEntity.setAnswer(questionModel.getAnswer());
				questionEntity.setCategoryId(sectionEntity.getSectionId());
				questionEntity.setCreatedDate(formatter.parse(questionModel.getCreatedDate()));
				questionEntity.setRemarks(questionModel.getRemarks());
				questionEntity.setUserId(questionModel.getUserId());
				questionEntity.setPatientId(questionModel.getPatientId());
				adverseEventsDAO.persist(questionEntity);
			}
		}
		catch(Exception e) {
			System.out.println(e);

		}
	}
	public void submitStatusQuestions(List<CRFQuestionModel> crfQuestionList) throws IOException{
		try {
			CRFSectionsEntity sectionEntity = new CRFSectionsEntity();
			sectionEntity.setCreatedDate(formatter.parse(crfQuestionList.get(0).getCreatedDate()));
			sectionEntity.setUserId(crfQuestionList.get(0).getUserId());
			sectionEntity.setStudySectionId(crfQuestionList.get(0).getSectionId());
			sectionEntity.setPatientId(crfQuestionList.get(0).getPatientId());
			SectionEntity secEnt = sectionDAO.findBySectionId(crfQuestionList.get(0).getSectionId());
			sectionEntity.setTitle(secEnt.getTitle());
			crfSectionDAO.persist(sectionEntity);
			for (int i = 0; i < crfQuestionList.size(); i++) {
				CRFQuestionModel questionModel = crfQuestionList.get(i);
				CommonFormEntity questionEntity = new CommonFormEntity();
				questionEntity.setQuestionId(questionModel.getQuestionId());
				questionEntity.setAnswer(questionModel.getAnswer());
				questionEntity.setSectionId(questionModel.getSectionId());
				questionEntity.setCategoryId(sectionEntity.getSectionId());
				questionEntity.setCreatedDate(formatter.parse(questionModel.getCreatedDate()));
				questionEntity.setRemarks(questionModel.getRemarks());
				questionEntity.setUserId(questionModel.getUserId());
				questionEntity.setPatientId(questionModel.getPatientId());
				commonFormDAO.persist(questionEntity);
			}
			StudyPatientsEntity patEnt = studyPatientDAO.findByPatientId(crfQuestionList.get(0).getPatientId());
			if (patEnt.getStatus().equals("IN-TREATMENT") || patEnt.getStatus().equals("SCREENING-PASSED")) {
				if (!crfQuestionList.get(1).getAnswer().equals("COMPLETED")) {
					patEnt.setStatus("DIPOSED");
					patEnt.setRemarks(crfQuestionList.get(1).getAnswer());
				}
				else
					patEnt.setStatus("COMPLETED");

				String completedDateStr = crfQuestionList.get(0).getAnswer();
				String hourTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
				completedDateStr = completedDateStr + " " + hourTime;
				patEnt.setCompletedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(completedDateStr));
				studyPatientDAO.merge(patEnt);
			}
		}
		catch(Exception e) {
			System.out.println(e);

		}
	}
	public void submitCommonQuestions(List<CRFQuestionModel> crfQuestionList) throws IOException{
		try {
			CRFSectionsEntity sectionEntity = new CRFSectionsEntity();
			sectionEntity.setCreatedDate(formatter.parse(crfQuestionList.get(0).getCreatedDate()));
			sectionEntity.setUserId(crfQuestionList.get(0).getUserId());
			sectionEntity.setStudySectionId(crfQuestionList.get(0).getSectionId());
			sectionEntity.setPatientId(crfQuestionList.get(0).getPatientId());
			SectionEntity secEnt = sectionDAO.findBySectionId(crfQuestionList.get(0).getSectionId());
			sectionEntity.setTitle(secEnt.getTitle());
			crfSectionDAO.persist(sectionEntity);
			System.out.println(sectionEntity.getSectionId());
			for (int i = 0; i < crfQuestionList.size(); i++) {
				CRFQuestionModel questionModel = crfQuestionList.get(i);
				CommonFormEntity questionEntity = new CommonFormEntity();
				questionEntity.setQuestionId(questionModel.getQuestionId());
				questionEntity.setAnswer(questionModel.getAnswer());
				questionEntity.setSectionId(questionModel.getSectionId());
				questionEntity.setCategoryId(sectionEntity.getSectionId());
				questionEntity.setCreatedDate(formatter.parse(questionModel.getCreatedDate()));
				questionEntity.setRemarks(questionModel.getRemarks());
				questionEntity.setUserId(questionModel.getUserId());
				questionEntity.setPatientId(questionModel.getPatientId());
				commonFormDAO.persist(questionEntity);
			}
			if (crfQuestionList.get(0).getSectionId() == 5) {
				StudyPatientsEntity patEnt = studyPatientDAO.findByPatientId(crfQuestionList.get(0).getPatientId());
				if (patEnt.getStatus().equals("SCREENING-PASSED")) {
					patEnt.setStatus("IN-TREATMENT");
					patEnt.setTreatmentStartDate(Calendar.getInstance().getTime());
					studyPatientDAO.merge(patEnt);
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);

		}
	}
	public List<SectionModel> findByAllAdv(Integer sectionId){
		List<SectionModel> sectionList = new ArrayList<SectionModel>();
		try {
			List<CRFSectionsEntity> entities = crfSectionDAO.findByStudySectionId(sectionId);
			if(entities != null)
				for(CRFSectionsEntity e : entities) {
					SectionModel model = new SectionModel();
					model.setSectionId(e.getSectionId());
					model.setTitle(e.getPatientId());
					model.setCreatedDate(formatter.format(e.getCreatedDate()));
					sectionList.add(model);
				}
		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return sectionList;
	} 
	public List<CRFQuestionModel> findByAllByCategoryId(Integer categoryId, String patientId){
		List<CRFQuestionModel> sectionList = new ArrayList<CRFQuestionModel>();
		try {
			List<AdverseEventsEntity> entities = adverseEventsDAO.findByPatientAndCategory(patientId, categoryId);
			if(entities != null) {
				for(AdverseEventsEntity e : entities) {
					CRFQuestionModel model = new CRFQuestionModel();
					model.setPatientId(e.getPatientId());
					QuestionsEntity quesEnt = questionDAO.findByQuestionId(e.getQuestionId());
					model.setRemarks(quesEnt.getQuestionTitle());
					model.setAnswer(e.getAnswer());
					model.setCreatedDate(formatter.format(e.getCreatedDate()));
					sectionList.add(model);
				}
			}

		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return sectionList;
	} 

	public List<SectionModel> findCategoryFromByPatientSection(String patientId, Integer sectionId){
		List<SectionModel> sectionList = new ArrayList<SectionModel>();
		try {
			List<CRFSectionsEntity> entities = crfSectionDAO.findBySectionAndPatient(sectionId,patientId);
			if(entities != null)
				for(CRFSectionsEntity e : entities) {
					SectionModel model = new SectionModel();
					model.setSectionId(e.getSectionId());
					model.setTitle(e.getTitle());
					model.setCreatedDate(formatter.format(e.getCreatedDate()));
					sectionList.add(model);
				}
		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return sectionList;
	} 
	public List<SectionModel> findCategoryFromByPatientSectionDate(String patientId, Integer sectionId, String fromDate, String toDate){
		List<SectionModel> sectionList = new ArrayList<SectionModel>();
		try {
			List<CRFSectionsEntity> entities = crfSectionDAO.findBySectionAndPatientDate(sectionId,patientId, dyformatter.parse(fromDate), dyformatter.parse(toDate));
			if(entities != null)
				for(CRFSectionsEntity e : entities) {
					SectionModel model = new SectionModel();
					model.setSectionId(e.getSectionId());
					model.setTitle(e.getTitle());
					model.setCreatedDate(formatter.format(e.getCreatedDate()));
					sectionList.add(model);
				}
		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return sectionList;
	} 
	public void updateForm(List<UpdateCRFQuestionModel> crfQuestionList) throws IOException{
		String oldAnswer = "";
		try {
			CRFSectionsEntity sectionEntity = new CRFSectionsEntity();
			if (crfQuestionList.get(0).getSectionId() != 1) {			
				sectionEntity = crfSectionDAO.findByCategoryId(crfQuestionList.get(0).getCategoryId());
			}
			else {
				sectionEntity = crfSectionDAO.findBySectionAndPatient(crfQuestionList.get(0).getSectionId(), crfQuestionList.get(0).getPatientId()).get(0);
			}
			if (sectionEntity != null) {
				for (int i = 0; i < crfQuestionList.size(); i++) {  // For Medical History || Prior Medication || Non Drug Therapies
					UpdateCRFQuestionModel questionModel = crfQuestionList.get(i);
					if (questionModel.getSectionId() == 2 || questionModel.getSectionId() == 3 || questionModel.getSectionId() == 4) {
						CRFQuestionsEntity questionEntity = crfQuestionDAO.findBySectionQuestionAndCategory(questionModel.getPatientId(), questionModel.getQuestionId(),
								questionModel.getCategoryId());	
						oldAnswer = questionEntity.getAnswer();
						if (!oldAnswer.equals(questionModel.getAnswer())) {
							questionEntity.setQuestionId(questionModel.getQuestionId());
							questionEntity.setCategoryId(sectionEntity.getSectionId());
							questionEntity.setRemarks(questionModel.getRemarks());
							questionEntity.setSectionId(questionModel.getSectionId());
							questionEntity.setUserId(questionModel.getUserId());
							questionEntity.setPatientId(questionModel.getPatientId());
							questionEntity.setAnswer(questionModel.getAnswer());
							crfQuestionDAO.merge(questionEntity);
							updateLogReportForm(questionModel, oldAnswer);
						}
					}
					else if (questionModel.getSectionId() == 1) { // For Demographic Data
						CRFQuestionsEntity questionEntity = crfQuestionDAO.findBySectionQuestionAndSection(questionModel.getPatientId(), questionModel.getQuestionId(),
								questionModel.getSectionId());	
						oldAnswer = questionEntity.getAnswer();
						if (!oldAnswer.equals(questionModel.getAnswer())) {
							questionEntity.setQuestionId(questionModel.getQuestionId());
							questionEntity.setCategoryId(sectionEntity.getSectionId());
							questionEntity.setRemarks(questionModel.getRemarks());
							questionEntity.setSectionId(questionModel.getSectionId());
							questionEntity.setUserId(questionModel.getUserId());
							questionEntity.setPatientId(questionModel.getPatientId());
							questionEntity.setAnswer(questionModel.getAnswer());
							crfQuestionDAO.merge(questionEntity);
							updateLogReportForm(questionModel, oldAnswer);
						}
					}
					else if (questionModel.getSectionId() == 6) { // Adverse Events
						AdverseEventsEntity adveEntity = adverseEventsDAO.findByPatientAndQuestionCategory(questionModel.getPatientId(), questionModel.getCategoryId(), questionModel.getQuestionId());
						oldAnswer = adveEntity.getAnswer();
						System.out.print(oldAnswer + "-" + questionModel.getAnswer());
						if (!oldAnswer.equals(questionModel.getAnswer())) {
							adveEntity.setQuestionId(questionModel.getQuestionId());
							adveEntity.setCategoryId(sectionEntity.getSectionId());
							adveEntity.setRemarks(questionModel.getRemarks());
							adveEntity.setUserId(questionModel.getUserId());
							adveEntity.setPatientId(questionModel.getPatientId());
							adveEntity.setAnswer(questionModel.getAnswer());
							adverseEventsDAO.merge(adveEntity);
							updateLogReportForm(questionModel, oldAnswer);
						}
					}
					else {  // Study Treatment || Concomitant Medication
						CommonFormEntity formEntity = commonFormDAO.findByPatientAndQuestionCategory(questionModel.getPatientId(), questionModel.getCategoryId(), questionModel.getQuestionId());
						oldAnswer = formEntity.getAnswer();
						if (!oldAnswer.equals(questionModel.getAnswer())) {
							formEntity.setQuestionId(questionModel.getQuestionId());
							formEntity.setCategoryId(sectionEntity.getSectionId());
							formEntity.setRemarks(questionModel.getRemarks());
							formEntity.setSectionId(questionModel.getSectionId());
							formEntity.setUserId(questionModel.getUserId());
							formEntity.setPatientId(questionModel.getPatientId());
							formEntity.setAnswer(questionModel.getAnswer());
							commonFormDAO.merge(formEntity);
							updateLogReportForm(questionModel, oldAnswer);
						}
					}

				}
			}

		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	public void updateLogReportForm(UpdateCRFQuestionModel questionModel, String oldAnswer) {
		LogReportEntity entity = new LogReportEntity();
		entity.setActivity("FIELD UPDATED - " + questionModel.getQuestionTitle());
		entity.setDescription("Update Value From " + oldAnswer + " - To " + questionModel.getAnswer());
		entity.setUserId(questionModel.getUserId());
		entity.setCreatedDate(Calendar.getInstance().getTime());
		entity.setTrialParticipant(questionModel.getPatientId());
		entity.setQuestionId(questionModel.getQuestionId());
		entity.setOriginalAnswer(oldAnswer);
		entity.setModifiedAnswer(questionModel.getAnswer());
		logReportDAO.persist(entity);
	}
}



