package com.clinivapps.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.clinivapps.dao.StudyDAO;
import com.clinivapps.dao.StudyPatientsDAO;
import com.clinivapps.dao.StudyVisitDAO;
import com.clinivapps.dao.AdverseEventDAO;
import com.clinivapps.dao.CRFQuestionDAO;
import com.clinivapps.dao.CRFSectionDAO;
import com.clinivapps.dao.CommonFormDAO;
import com.clinivapps.dao.DispositionDAO;
import com.clinivapps.dao.PatientLabReportDAO;
import com.clinivapps.dao.PatientVisitsDAO;
import com.clinivapps.dao.StudyDocumentsDAO;
import com.clinivapps.dao.ProductDAO;
import com.clinivapps.dao.QueryDAO;
import com.clinivapps.dao.QuestionDAO;
import com.clinivapps.dao.SectionDAO;
import com.clinivapps.dao.TherapeuticDAO;
import com.clinivapps.dao.TrailSiteDAO;
import com.clinivapps.dao.UserDAO;
import com.clinivapps.dao.WithdrawlDAO;
import com.clinivapps.entity.StudyEntity;
import com.clinivapps.entity.StudyPatientsEntity;
import com.clinivapps.entity.StudyVisitEntity;
import com.clinivapps.entity.TrailSiteEntity;
import com.clinivapps.entity.AdverseEventsEntity;
import com.clinivapps.entity.CRFQuestionsEntity;
import com.clinivapps.entity.CRFSectionsEntity;
import com.clinivapps.entity.CommonFormEntity;
import com.clinivapps.entity.DispositionEntity;
import com.clinivapps.entity.PatientLabReportsEntity;
import com.clinivapps.entity.PatientVisitEntity;
import com.clinivapps.entity.QueriesEntity;
import com.clinivapps.entity.QuestionsEntity;
import com.clinivapps.entity.SectionEntity;
import com.clinivapps.entity.UserEntity;
import com.clinivapps.entity.WithdrawlEntity;
import com.clinivapps.model.CRFQuestionModel;
import com.clinivapps.model.LabReportModel;
import com.clinivapps.model.PatientProfileModel;
import com.clinivapps.model.PatientStudyVisitModel;
import com.clinivapps.model.PatientVisitModel;
import com.clinivapps.model.SectionModel;
import com.clinivapps.model.SiteReportModel;
import com.clinivapps.model.StudyMobilePatientModel;
import com.clinivapps.model.StudyPatientModel;
import com.clinivapps.model.VisitQuestionModel;
import com.clinivapps.model.VisitReportModel;
import com.clinivapps.model.VisitSectionModel;
import com.clinivapps.util.FileUtil;
import com.clinivapps.util.ClinIVProperty;

@Service("studyPatientsService")
@Transactional
public class StudyPatientsService {

	@Autowired
	StudyDAO studyDAO;

	@Autowired
	ProductDAO productDAO;

	@Autowired
	StudyDocumentsDAO ethicsDAO;

	@Autowired
	TrailSiteDAO trailSiteDAO;

	@Autowired
	TherapeuticDAO therapeuticDAO;

	@Autowired
	StudyPatientsDAO studyPatientsDAO;

	@Autowired
	PatientVisitsDAO patientVisitsDAO;

	@Autowired
	SectionDAO sectionDAO;

	@Autowired
	QuestionDAO questionDAO;

	@Autowired
	UserDAO userDAO;

	@Autowired
	StudyVisitDAO studyVisitDAO;

	@Autowired
	CRFQuestionDAO crfQuestionDAO;

	@Autowired
	WithdrawlDAO withdrawDAO;

	@Autowired
	AdverseEventDAO advDAO;

	@Autowired
	DispositionDAO dispoDAO;

	@Autowired
	QueryDAO queryDAO;

	@Autowired
	PatientLabReportDAO labReportDAO;
	
	@Autowired
	CRFSectionDAO crfSectionDAO;
	
	@Autowired
	CommonFormDAO commonFormDAO;

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat dateYearFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	public List<StudyMobilePatientModel> findByStudyAndDoctor(Integer trialSiteId, Integer studyId){
		List<StudyMobilePatientModel> patientsList = new ArrayList<StudyMobilePatientModel>();
		try {
			List<StudyPatientsEntity> entities = studyPatientsDAO.findBySiteAndStudyId(trialSiteId, studyId);
			if(entities != null)
				for(StudyPatientsEntity e : entities) {
					StudyMobilePatientModel model = new StudyMobilePatientModel();
					model.setEmailId(e.getEmailId());
					model.setFullName(e.getFullName());
					model.setMobileNumber(e.getMobileNumber());
					model.setStatus(e.getStatus());
					model.setPatientId(e.getPatientId());
					model.setRegisteredDate(dateYearFormat.format(e.getRegisteredDate()));

					if (e.getCompletedDate() != null) 
						model.setCompletedDate(dateYearFormat.format(e.getCompletedDate()));

					if (e.getScreenedDate() != null) 
						model.setScreenedDate(dateYearFormat.format(e.getScreenedDate()));

					if (e.getTreatmentStartDate() != null) 
						model.setTreatmentStartDate(dateYearFormat.format(e.getTreatmentStartDate()));

					model.setGender(e.getGender());
					model.setIcfSignType(e.getIcfSignType());
					model.setIcfSignURL(e.getIcfSignURL());
					patientsList.add(model);
				}
		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return patientsList;
	} 
	public List<StudyMobilePatientModel> findByStudy(Integer studyId){
		List<StudyMobilePatientModel> patientsList = new ArrayList<StudyMobilePatientModel>();
		try {
			List<StudyPatientsEntity> entities = studyPatientsDAO.findByStudyId(studyId);
			if(entities != null)
				for(StudyPatientsEntity e : entities) {
					StudyMobilePatientModel model = new StudyMobilePatientModel();
					model.setEmailId(e.getEmailId());
					model.setAge(e.getAge());
					model.setDob(dateFormat.format(e.getDateOfBirth()));
					model.setMobileNumber(e.getMobileNumber());
					model.setStatus(e.getStatus());
					model.setPatientId(e.getPatientId());
					TrailSiteEntity siteEnt = trailSiteDAO.findBySiteId(e.getTrailSiteId());
					model.setSiteId(siteEnt.getId());
					model.setSiteName(siteEnt.getSiteName());
					model.setRegisteredDate(dateYearFormat.format(e.getRegisteredDate()));

					if (e.getCompletedDate() != null) 
						model.setCompletedDate(dateYearFormat.format(e.getCompletedDate()));

					if (e.getScreenedDate() != null) 
						model.setScreenedDate(dateYearFormat.format(e.getScreenedDate()));

					if (e.getTreatmentStartDate() != null) 
						model.setTreatmentStartDate(dateYearFormat.format(e.getTreatmentStartDate()));
					model.setGender(e.getGender());
					model.setIcfSignType(e.getIcfSignType());
					model.setIcfSignURL(e.getIcfSignURL());
					patientsList.add(model);
				}
		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return patientsList;
	} 
	public List<StudyMobilePatientModel> findBySite(Integer siteId){
		List<StudyMobilePatientModel> patientsList = new ArrayList<StudyMobilePatientModel>();
		try {
			List<StudyPatientsEntity> entities = studyPatientsDAO.findBySiteId(siteId);
			if(entities != null)
				for(StudyPatientsEntity e : entities) {
					StudyMobilePatientModel model = new StudyMobilePatientModel();
					model.setEmailId(e.getEmailId());
					model.setFullName(e.getFullName());
					model.setAge(e.getAge());
					model.setDob(dateFormat.format(e.getDateOfBirth()));
					model.setMobileNumber(e.getMobileNumber());
					model.setStatus(e.getStatus());
					model.setPatientId(e.getPatientId());
					model.setRegisteredDate(dateYearFormat.format(e.getRegisteredDate()));

					if (e.getCompletedDate() != null) 
						model.setCompletedDate(dateYearFormat.format(e.getCompletedDate()));

					if (e.getScreenedDate() != null) 
						model.setScreenedDate(dateYearFormat.format(e.getScreenedDate()));

					if (e.getTreatmentStartDate() != null) 
						model.setTreatmentStartDate(dateYearFormat.format(e.getTreatmentStartDate()));
					model.setGender(e.getGender());
					TrailSiteEntity siteEnt = trailSiteDAO.findBySiteId(e.getTrailSiteId());
					model.setSiteId(siteEnt.getId());
					model.setSiteName(siteEnt.getSiteName());
					model.setIcfSignType(e.getIcfSignType());
					model.setIcfSignURL(e.getIcfSignURL());
					patientsList.add(model);
				}
		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return patientsList;
	} 
	public List<StudyPatientModel> findByStudyAndNurse(Integer enrolledUserId, Integer studyId){
		List<StudyPatientModel> patientsList = new ArrayList<StudyPatientModel>();
		try {
			List<StudyPatientsEntity> entities = studyPatientsDAO.findByNurseAndStudyId(enrolledUserId, studyId);
			if(entities != null)
				for(StudyPatientsEntity e : entities) {
					StudyPatientModel model = new StudyPatientModel();
					model.setEmailId(e.getEmailId());
					model.setFullName(e.getFullName());
					model.setFullName(e.getFullName());
					model.setAge(e.getAge());
					model.setDob(dateFormat.format(e.getDateOfBirth()));
					model.setMobileNumber(e.getMobileNumber());
					model.setStatus(e.getStatus());
					model.setPatientId(e.getPatientId());
					model.setRegisteredDate(dateYearFormat.format(e.getRegisteredDate()));

					if (e.getCompletedDate() != null) 
						model.setCompletedDate(dateYearFormat.format(e.getCompletedDate()));

					if (e.getScreenedDate() != null) 
						model.setScreenedDate(dateYearFormat.format(e.getScreenedDate()));

					if (e.getTreatmentStartDate() != null) 
						model.setTreatmentStartDate(dateYearFormat.format(e.getTreatmentStartDate()));
					model.setGender(e.getGender());
					model.setIcfSignType(e.getIcfSignType());
					model.setIcfSignURL(e.getIcfSignURL());
					patientsList.add(model);
				}
		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return patientsList;
	} 
	public List<StudyPatientModel> findByStudyAndStatus(String status, Integer studyId){
		List<StudyPatientModel> patientsList = new ArrayList<StudyPatientModel>();
		try {
			List<StudyPatientsEntity> entities = studyPatientsDAO.findByStatus(status);
			if(entities != null)
				for(StudyPatientsEntity e : entities) {
					StudyPatientModel model = new StudyPatientModel();
					model.setEmailId(e.getEmailId());
					model.setFullName(e.getFullName());
					model.setMobileNumber(e.getMobileNumber());
					model.setStatus(e.getStatus());
					model.setRemarks(e.getRemarks());
					model.setPatientId(e.getPatientId());
					model.setRegisteredDate(dateYearFormat.format(e.getRegisteredDate()));

					if (e.getCompletedDate() != null) 
						model.setCompletedDate(dateYearFormat.format(e.getCompletedDate()));

					if (e.getScreenedDate() != null) 
						model.setScreenedDate(dateYearFormat.format(e.getScreenedDate()));

					if (e.getTreatmentStartDate() != null) 
						model.setTreatmentStartDate(dateYearFormat.format(e.getTreatmentStartDate()));
					model.setGender(e.getGender());
					model.setIcfSignType(e.getIcfSignType());
					model.setIcfSignURL(e.getIcfSignURL());
					patientsList.add(model);
				}
		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return patientsList;
	}
	public StudyPatientModel findByPatientAndDoctor(StudyPatientModel studyPatientModel){
		StudyPatientModel model = new StudyPatientModel();
		try {
			StudyPatientsEntity e = studyPatientsDAO.findByPatientAndStudyIds(studyPatientModel.getMobileNumber(), studyPatientModel.getStudyId());
			if(e.getEnrolledUserId() != null)
				model.setStudyId(e.getStudyId());
			model.setTrailSiteId(e.getTrailSiteId());
			model.setFullName(e.getFullName());
			model.setStatus(e.getStatus());
			model.setEnrolledUserId(e.getEnrolledUserId());
			model.setPatientId(e.getPatientId());
			model.setMobileNumber(e.getMobileNumber());
			model.setStatus(e.getStatus());
			model.setRegisteredDate(dateYearFormat.format(e.getRegisteredDate()));
			
			if (e.getCompletedDate() != null) 
				model.setCompletedDate(dateYearFormat.format(e.getCompletedDate()));
			
			if (e.getScreenedDate() != null) 
				model.setScreenedDate(dateYearFormat.format(e.getScreenedDate()));
			
			if (e.getTreatmentStartDate() != null) 
				model.setTreatmentStartDate(dateYearFormat.format(e.getTreatmentStartDate()));
		}catch(Exception e){
			e.printStackTrace();	
		}
		return model;
	}
	public StudyPatientModel findByPatientMobileNumberAndPatientId(String mobileNumber, String patientId){
		StudyPatientModel model = new StudyPatientModel();
		try {
			StudyPatientsEntity e = studyPatientsDAO.findByMobileNumberAndPatientId(mobileNumber, patientId);
			if(e.getEnrolledUserId() != null)
				model.setStudyId(e.getStudyId());
			model.setTrailSiteId(e.getTrailSiteId());
			model.setPatientId(e.getPatientId());
			model.setFullName(e.getFullName());
			model.setEnrolledUserId(e.getEnrolledUserId());
			model.setStatus(e.getStatus());
			model.setMobileNumber(e.getMobileNumber());
			model.setStatus(e.getStatus());
			StudyEntity studyEntity = studyDAO.findByStudyId(e.getStudyId());
			model.setStudyStatus(studyEntity.getStudyStatus());
			model.setStudyName(studyEntity.getStudyName());
			model.setStudyBannerURL(studyEntity.getStudyBannerURL());
			model.setStartDate(dateFormat.format(studyEntity.getStartDate()));
			model.setEndDate(dateFormat.format(studyEntity.getEndDate()));
			model.setTotalVisits(studyEntity.getVisitCount());
			model.setPatientDuration(studyEntity.getPatientDuration());
			model.setProtocolId(studyEntity.getProtocolNumber());
			model.setRegisteredDate(dateYearFormat.format(e.getRegisteredDate()));
			
			if (e.getCompletedDate() != null) 
				model.setCompletedDate(dateYearFormat.format(e.getCompletedDate()));
			
			if (e.getScreenedDate() != null) 
				model.setScreenedDate(dateYearFormat.format(e.getScreenedDate()));
			
			if (e.getTreatmentStartDate() != null) 
				model.setTreatmentStartDate(dateYearFormat.format(e.getTreatmentStartDate()));

		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return model;
	}
	public PatientProfileModel findByPatientId(String patientId){
		PatientProfileModel model = new PatientProfileModel();
		try {
			StudyPatientsEntity e = studyPatientsDAO.findByPatientId(patientId);
			if(e.getEnrolledUserId() != null)
				model.setPatientId(e.getId());
			model.setMobileNumber(e.getMobileNumber());
			model.setIcfImageUrl(e.getIcfSignURL());
			model.setIcfType(e.getIcfSignType());
			UserEntity user = userDAO.findByMobileNoAndRole(e.getMobileNumber(), 9);
			model.setFullName(user.getFirstName() + " " + user.getLastName());
			model.setEmailId(user.getEmailId());

		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return model;
	}
	public StudyPatientModel findByPatientMobileNumberAndStudyId(String mobileNumber, Integer studyId){
		StudyPatientModel model = new StudyPatientModel();
		try {
			StudyPatientsEntity e = studyPatientsDAO.findByPatientAndStudyIds(mobileNumber, studyId);
			if(e.getEnrolledUserId() != null)
				model.setStudyId(e.getStudyId());
			model.setTrailSiteId(e.getTrailSiteId());
			model.setFullName(e.getFullName());
			model.setPatientId(e.getPatientId());
			model.setStatus(e.getStatus());
			model.setMobileNumber(e.getMobileNumber());
			model.setStatus(e.getStatus());
			model.setRegisteredDate(dateYearFormat.format(e.getRegisteredDate()));
			
			if (e.getCompletedDate() != null) 
				model.setCompletedDate(dateYearFormat.format(e.getCompletedDate()));
			
			if (e.getScreenedDate() != null) 
				model.setScreenedDate(dateYearFormat.format(e.getScreenedDate()));
			
			if (e.getTreatmentStartDate() != null) 
				model.setTreatmentStartDate(dateYearFormat.format(e.getTreatmentStartDate()));
		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return model;
	}	

	public boolean approvePatientLabData(LabReportModel labReportModel){
		boolean status = false;
		try {
			StudyPatientsEntity entity = studyPatientsDAO.findByPatientAndStudyIds(labReportModel.getMobileNumber(), labReportModel.getStudyId());
			if(entity.getEnrolledUserId() != null) {
				entity.setStatus("SCREENING-INPROGRESS");
				studyPatientsDAO.merge(entity);
				PatientLabReportsEntity labEnt = new PatientLabReportsEntity();
				labEnt.setCreatedDate(Calendar.getInstance().getTime());
				String picStr = ClinIVProperty.getInstance().getProperties("cliniv.home") + "/images/desrem/reports/";
				if (labReportModel.getFileURL().length()>0) {
					picStr += FileUtil.copyBinaryData(labReportModel.getFileURL().getBytes(),ClinIVProperty.getInstance().getProperties("images.loc")+File.separator+"reports",labReportModel.getMobileNumber()+".jpg");
				}
				labEnt.setFileURL(picStr);
				labEnt.setUserId(labReportModel.getUserId());
				labEnt.setPatientId(labReportModel.getPatientId());
				labEnt.setTestName(labReportModel.getTestName());
				labEnt.setValue(labReportModel.getTestValue());
				labEnt.setStudyId(labReportModel.getStudyId());
				labReportDAO.persist(labEnt);
				status = true;
			}
		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return status;
	}
	public boolean approvePatientICF(LabReportModel labReportModel){
		boolean status = false;
		try {
			StudyPatientsEntity entity = studyPatientsDAO.findByPatientAndStudyIds(labReportModel.getMobileNumber(), labReportModel.getStudyId());
			if(entity.getEnrolledUserId() != null) {
				entity.setStatus("SCREENING-PASSED");
				entity.setScreenedDate(Calendar.getInstance().getTime());
				studyPatientsDAO.merge(entity);
				List<StudyVisitEntity>visitEntities = studyVisitDAO.findByStudyId(labReportModel.getStudyId());
				if (visitEntities != null) {
					for (StudyVisitEntity e: visitEntities) {
						PatientVisitEntity visit = new PatientVisitEntity();
						visit.setVisitId(e.getVisitId());
						visit.setStatus("OPEN");
						visit.setSiteId(entity.getTrailSiteId());
						visit.setStudyNurseUserId(entity.getEnrolledUserId());
						visit.setApprovalStatus("OPEN");
						visit.setSiteId(entity.getTrailSiteId());
						visit.setCreatedDate(Calendar.getInstance().getTime());
						visit.setPatientId(labReportModel.getPatientId());
						visit.setStudyId(labReportModel.getStudyId());
						patientVisitsDAO.persist(visit);
					}
				}
				status = true;
			}

		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return status;
	}
	public boolean updateLabReport(LabReportModel labReportModel){
		boolean status = false;
		try {
			PatientLabReportsEntity labEnt = new PatientLabReportsEntity();
			labEnt.setCreatedDate(Calendar.getInstance().getTime());
			String picStr = ClinIVProperty.getInstance().getProperties("cliniv.home") + "/images/desrem/reports/";
			if (labReportModel.getFileURL().length()>0) {
				picStr += FileUtil.copyBinaryData(labReportModel.getFileURL().getBytes(),ClinIVProperty.getInstance().getProperties("images.loc")+File.separator+"reports",labReportModel.getMobileNumber()+".jpg");
			}
			labEnt.setFileURL(picStr);
			labEnt.setStudyId(labReportModel.getStudyId());
			labEnt.setUserId(labReportModel.getUserId());
			labEnt.setPatientId(labReportModel.getPatientId());
			labEnt.setTestName(labReportModel.getTestName());
			labEnt.setValue(labReportModel.getTestValue());
			labReportDAO.persist(labEnt);
			status = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	public void enrollNewPatient(StudyPatientModel patientModel) throws IOException{
		try {
			StudyPatientsEntity patientEntity = new StudyPatientsEntity();
			patientEntity.setTrailSiteId(patientModel.getTrailSiteId());
			patientEntity.setEmailId(patientModel.getEmailId());
			patientEntity.setRegisteredDate(Calendar.getInstance().getTime());
			patientEntity.setGender(patientModel.getGender());
			patientEntity.setMobileNumber(patientModel.getMobileNumber());
			patientEntity.setStatus("SCREENING-PENDING");
			patientEntity.setIcfSignType(patientModel.getIcfSignType());
			patientEntity.setPatientId(patientModel.getPatientId());
			patientEntity.setStudyId(patientModel.getStudyId());
			patientEntity.setDateOfBirth(dateFormat.parse(patientModel.getDob()));
			patientEntity.setAge(patientModel.getAge());
			patientEntity.setFullName(patientModel.getFullName());
			patientEntity.setTrailSiteId(patientModel.getTrailSiteId());
			patientEntity.setEnrolledUserId(patientModel.getEnrolledUserId());
			String picStr = ClinIVProperty.getInstance().getProperties("cliniv.home") + "/images/desrem/icf/";
			if (patientModel.getIcfSignURL().length()>0) {
				picStr += FileUtil.copyBinaryData(patientModel.getIcfSignURL().getBytes(),ClinIVProperty.getInstance().getProperties("images.loc")+File.separator+"icf",patientModel.getMobileNumber()+".jpg");
			}
			String proofStr = ClinIVProperty.getInstance().getProperties("cliniv.home") + "/images/desrem/icf/";
			if (patientModel.getProofImageUrl().length()>0) {
				proofStr += FileUtil.copyBinaryData(patientModel.getProofImageUrl().getBytes(),ClinIVProperty.getInstance().getProperties("images.loc")+File.separator+"icf",patientModel.getMobileNumber().trim()+".jpg");
			}
			patientEntity.setIcfSignURL(picStr);
			patientEntity.setIdProofUrl(proofStr);
			studyPatientsDAO.persist(patientEntity);
		}

		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public VisitSectionModel findVisitsByPatientAndSection(Integer sectionId, String patientId, Integer visitId){
		VisitSectionModel model = new VisitSectionModel();
		try {
			List<VisitQuestionModel> questionmodels = new ArrayList<VisitQuestionModel>();	
				List<QuestionsEntity> questions = questionDAO.findBySectionId(sectionId);
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
							CRFQuestionsEntity crfQuestionsEntity = crfQuestionDAO.findBySectionQuestionAndSection(patientId, question.getQuestionId(), sectionId);
							questionModel.setAnswer(crfQuestionsEntity.getAnswer());
							questionModel.setRemarks(crfQuestionsEntity.getRemarks());
							questionmodels.add(questionModel);
						}
				}
				model.setQuestions(questionmodels);

		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return model;
	}
	public VisitSectionModel findWebVisitsByPatientAndStudyId(Integer studyId, String patientId, Integer sectionId,  Integer categoryId){
		VisitSectionModel model = new VisitSectionModel();
		try {
			SectionEntity secEntity = sectionDAO.findBySectionId(sectionId);
			model.setTitle(secEntity.getTitle());
			model.setDescription(secEntity.getDescription());
			if (secEntity != null) {
				List<VisitQuestionModel> questionmodels = new ArrayList<VisitQuestionModel>();	
				List<QuestionsEntity> questions = questionDAO.findBySectionId(secEntity.getSectionId());
					if (questions != null) {
						for(QuestionsEntity question : questions) {
							VisitQuestionModel questionModel = new VisitQuestionModel();
							questionModel.setQuestionId(question.getQuestionId());
							questionModel.setQuestionTitle(question.getQuestionTitle());
							questionModel.setLength(question.getLength());
							questionModel.setQuestionType(question.getQuestionType());
							questionModel.setQuestionComments(question.getQuestionComments());
							questionModel.setSectionId(question.getSectionId());
							if (question.getSectionId() == 5) {
								CommonFormEntity commonForm = commonFormDAO.findByPatientAndQuestionCategory(patientId, categoryId, question.getQuestionId());
								if (commonForm != null) {
									questionModel.setAnswer(commonForm.getAnswer());
									questionModel.setRemarks(commonForm.getRemarks());
								}
								else {
									questionModel.setAnswer("");
									questionModel.setRemarks("");
								}
							}
							else if (question.getSectionId() == 6) {
							AdverseEventsEntity advEntity = advDAO.findByPatientAndQuestionCategory(patientId, categoryId, question.getQuestionId());
							if (advEntity != null) {
								questionModel.setAnswer(advEntity.getAnswer());
								questionModel.setRemarks(advEntity.getRemarks());
							}
							else {
								questionModel.setAnswer("");
								questionModel.setRemarks("");
							}
							}
							else {
								CRFQuestionsEntity crfQuestionEntity = crfQuestionDAO.findBySectionQuestionAndCategory(patientId, question.getQuestionId(), categoryId);
								if (crfQuestionEntity != null) {
									questionModel.setAnswer(crfQuestionEntity.getAnswer());
									questionModel.setRemarks(crfQuestionEntity.getRemarks());
								}
								else {
									questionModel.setAnswer("");
									questionModel.setRemarks("");
								}
								}
							QueriesEntity queryEnt = queryDAO.findByPatientVisitQuestionId(categoryId,question.getQuestionId(),patientId,"CLOSED");
							if (queryEnt.getAssigneduserId() != null) {
								questionModel.setOptions("Query Raised");
							}
							else
								questionModel.setOptions("No Queries");
							questionmodels.add(questionModel);
						}
					}
				
				model.setQuestions(questionmodels);
			}

		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return model;
	}
	public List<PatientStudyVisitModel> findVisitDataByPatientAndStudyId(Integer studyId, String patientId){
		List<PatientStudyVisitModel> models = new ArrayList<PatientStudyVisitModel>();
		try {
			List<PatientVisitEntity> visitEntities = patientVisitsDAO.findByStudyPatientStudy(studyId, patientId);
			for(PatientVisitEntity e : visitEntities) {
				PatientStudyVisitModel model = new PatientStudyVisitModel();
				model.setStudyId(e.getStudyId());
				model.setPatientId(e.getPatientId());
				model.setVisitId(e.getVisitId());
				model.setUserStatus(e.getStatus());
				model.setDoctorStatus(e.getApprovalStatus());
				model.setCreatedDate(dateYearFormat.format(e.getCreatedDate()));
				StudyVisitEntity visitEntity = studyVisitDAO.findByVisitId(e.getVisitId());
				model.setVisitName(visitEntity.getTitle());
				model.setSectionId(visitEntity.getSectionId());
				model.setFromDay(visitEntity.getFromDay());
				model.setToDay(visitEntity.getToDay());
				models.add(model);
			}					
		}
		catch(Exception e){
			e.printStackTrace();	
		}
		return models;
	}
	
	public List<PatientStudyVisitModel> findWebPatientVisit(Integer studyId, String patientId){
		List<PatientStudyVisitModel> models = new ArrayList<PatientStudyVisitModel>();
		try {
			List<CRFSectionsEntity> visitEntities = crfSectionDAO.findByPatientId(patientId);
			for(CRFSectionsEntity e : visitEntities) {
				PatientStudyVisitModel model = new PatientStudyVisitModel();
				model.setPatientId(e.getPatientId());
				model.setCategoryId(e.getSectionId());
				model.setSectionId(e.getStudySectionId());
				SectionEntity secEnt = sectionDAO.findBySectionId(e.getStudySectionId());
				model.setVisitName(secEnt.getTitle());
				model.setCreatedDate(dateYearFormat.format(e.getCreatedDate()));
				QueriesEntity queryEnt = queryDAO.findByPatientCategory(e.getSectionId(),patientId,"CLOSED");
				if (queryEnt.getAssigneduserId() != null) {
					model.setDoctorStatus("Query Raised");
				}
				else
					model.setDoctorStatus("No Queries");
				models.add(model);
			}					
		}
		catch(Exception e){
			e.printStackTrace();	
		}
		return models;
	}

	public boolean approvePatientVisit(PatientVisitModel patientModel) throws IOException{
		boolean status = false;
		try {
			PatientVisitEntity patientEntity = patientVisitsDAO.findByStudyPatientVisitId(patientModel.getStudyId(), patientModel.getPatientId(), patientModel.getVisitId());
			if (patientEntity != null) {
				patientEntity.setApprovalStatus("APPROVED");
				patientEntity.setDoctorRemarks(patientModel.getDoctorRemarks());
				patientVisitsDAO.merge(patientEntity);
				status = true;
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public VisitReportModel getStudyVisitReportsData(Integer studyId) throws IOException{
		VisitReportModel reportData = new VisitReportModel();		
		try {
			Integer openCount = 0;
			Integer ongoingCount = 0;
			Integer completedCount = 0;
			Integer maxCount = 0;
			ArrayList<String> visitsStr = new ArrayList<String>();
			ArrayList<String> openCounttr = new ArrayList<String>();
			ArrayList<String> ongoingCounttr = new ArrayList<String>();
			ArrayList<String> completedCounttr = new ArrayList<String>();
			List<StudyVisitEntity> visitEntities = studyVisitDAO.findByStudyId(studyId);
			if (visitEntities != null) {
				System.out.println(visitEntities.size() + "Stud" + studyId);
				for(int i = 0; i<visitEntities.size(); i++) {
					visitsStr.add(visitEntities.get(i).getTitle());
					int opCount = 0;
					int onCount = 0;
					int cCount = 0;
					List<PatientVisitEntity> patEntities = patientVisitsDAO.findByStudyPatientVisit(studyId, visitEntities.get(i).getVisitId());
					for(PatientVisitEntity e: patEntities) {
						if (e.getStatus().equals("OPEN")) {
							openCount = openCount + 1;
							opCount = opCount + 1;
						}
						else {
							if (e.getApprovalStatus().equals("APPROVED")) {
								completedCount = completedCount + 1;
								cCount = cCount + 1;
							}
							else {
								ongoingCount = ongoingCount + 1;
								onCount = onCount + 1;
							}
						}

					}
					openCounttr.add(String.valueOf(opCount));
					ongoingCounttr.add(String.valueOf(onCount));
					completedCounttr.add(String.valueOf(cCount));
				}
			}
			int values[] = {openCount, ongoingCount, completedCount};
			Arrays.sort(values);
			Integer maxValue = values[values.length-1];

			maxCount = maxValue + 10;

			String vsnames[]=visitsStr.toArray(new String[visitsStr.size()]);
			String ocArr[]=openCounttr.toArray(new String[openCounttr.size()]);
			String onArr[]=ongoingCounttr.toArray(new String[ongoingCounttr.size()]);
			String coArr[]=completedCounttr.toArray(new String[completedCounttr.size()]);

			reportData.setCompletedCount(completedCount);
			reportData.setMaxCount(maxCount);
			reportData.setOngoingCount(ongoingCount);
			reportData.setOpenCount(openCount);
			reportData.setVisitNames(vsnames);
			reportData.setOpenCountValues(ocArr);
			reportData.setOngoingCountValues(onArr);
			reportData.setCompletedCountValues(coArr);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return reportData;
	}

	public boolean terminatePatient(List<CRFQuestionModel> crfModels){
		boolean status = false;
		try {
			StudyPatientsEntity e = studyPatientsDAO.findByPatientId(crfModels.get(0).getPatientId());
			if(e.getEnrolledUserId() != null) {
				e.setStatus("DROP_OUT");
				e.setRemarks(crfModels.get(0).getRemarks());
				studyPatientsDAO.merge(e);
				for (int i = 0; i < crfModels.size(); i++) {
					CRFQuestionModel crfModel = crfModels.get(i);
					WithdrawlEntity ent = new WithdrawlEntity();
					ent.setAnswer(crfModel.getAnswer());
					ent.setCreatedDate(Calendar.getInstance().getTime());
					ent.setPatientId(crfModel.getPatientId());
					ent.setQuestionId(crfModel.getQuestionId());
					ent.setRemarks(crfModel.getRemarks());
					ent.setUserId(crfModel.getUserId());
					withdrawDAO.persist(ent);
				}
				status = true;
			}

		}catch(Exception e){
			e.printStackTrace();	
		}
		return status;
	}
	public boolean disposePatient(List<CRFQuestionModel> crfModels){
		boolean status = false;
		try {
			StudyPatientsEntity e = studyPatientsDAO.findByPatientId(crfModels.get(0).getPatientId());
			if(e.getEnrolledUserId() != null) {
				e.setStatus("DISCONTINUED");
				studyPatientsDAO.merge(e);
				for (int i = 0; i < crfModels.size(); i++) {
					CRFQuestionModel crfModel = crfModels.get(i);
					DispositionEntity ent = new DispositionEntity();
					ent.setAnswer(crfModel.getAnswer());
					ent.setCreatedDate(Calendar.getInstance().getTime());
					ent.setPatientId(crfModel.getPatientId());
					ent.setQuestionId(crfModel.getQuestionId());
					ent.setRemarks(crfModel.getRemarks());
					ent.setUserId(crfModel.getUserId());
					dispoDAO.persist(ent);
				}
				status = true;
			}

		}catch(Exception e){
			e.printStackTrace();	
		}
		return status;
	}
	public List<LabReportModel> getAllLabReports(){
		List<LabReportModel> labReports = new ArrayList<LabReportModel>();
		try {
			List<PatientLabReportsEntity> reports = labReportDAO.findAll();
			for(PatientLabReportsEntity ent: reports) {
				LabReportModel model = new LabReportModel();
				model.setFileURL(ent.getFileURL());
				model.setTestName(ent.getTestName());
				model.setTestValue(ent.getValue());
				model.setPatientId(ent.getPatientId());
				model.setCreatedDate(dateYearFormat.format(ent.getCreatedDate()));
				labReports.add(model);
			}


		}catch(Exception e){
			e.printStackTrace();	
		}
		return labReports;
	}
	public List<LabReportModel> getLabReportsByPatient(String patientId){
		List<LabReportModel> labReports = new ArrayList<LabReportModel>();
		try {
			List<PatientLabReportsEntity> reports = labReportDAO.findByPatientId(patientId);
			for(PatientLabReportsEntity ent: reports) {
				LabReportModel model = new LabReportModel();
				model.setFileURL(ent.getFileURL());
				model.setTestName(ent.getTestName());
				model.setCreatedDate(dateYearFormat.format(ent.getCreatedDate()));
				model.setTestValue(ent.getValue());
				model.setPatientId(ent.getPatientId());
				labReports.add(model);
			}
		}catch(Exception e){
			e.printStackTrace();	
		}
		return labReports;
	}

	public List<SectionModel> getPatientDairyDataBydates(String patientId, String fromDate, String toDate) {
		List<SectionModel> patientDairyList = new ArrayList<SectionModel>();
		try {
			SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");

			List<CRFSectionsEntity> entities = crfSectionDAO.findByPatientAndDate(patientId, dmyFormat.parse(fromDate), dmyFormat.parse(toDate));
			if (entities != null) {
				for (CRFSectionsEntity e : entities) {
					if (e.getStudySectionId() == 5 || e.getStudySectionId() == 6 || e.getStudySectionId() == 7) {
						SectionModel model = new SectionModel();
						model.setCreatedDate(dateYearFormat.format(e.getCreatedDate()));
						model.setSectionId(e.getStudySectionId());
						model.setTitle(e.getTitle());
						patientDairyList.add(model);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return patientDairyList;
	}
	public SiteReportModel findAllSiteReport(){
		SiteReportModel model = new SiteReportModel();
		try {

			int totalPatCount = 0;
			int enrolledPatCount = 0;
			int completedPatCount = 0;
			int droppedPatCount = 0;


			completedPatCount = studyPatientsDAO.findByStatus("SCREENING-PENDING").size();
			completedPatCount = completedPatCount + studyPatientsDAO.findByStatus("SCREENING-PASSED").size();
			completedPatCount = completedPatCount + studyPatientsDAO.findByStatus("SCREENING-INPROGRESS").size();
			droppedPatCount = studyPatientsDAO.findByStatus("DROPPED").size();
			enrolledPatCount = studyPatientsDAO.findByStatus("IN-TREATMENT").size();
			totalPatCount = completedPatCount + enrolledPatCount + droppedPatCount;
			model.setTotalPatCount(totalPatCount);
			model.setCompletedPatCount(completedPatCount);
			model.setEnrolledPatCount(enrolledPatCount);
			model.setDroppedPatCount(droppedPatCount);

		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return model;
	}
	public SiteReportModel findAllSiteReportBySite(Integer siteId){
		SiteReportModel model = new SiteReportModel();
		try {
			int totalPatCount = 0;
			int completedPatCount = 0;
			int droppedPatCount = 0;
			int enrolledPatCount = 0;
		

			completedPatCount = studyPatientsDAO.findBySiteAndStatus(siteId,"SCREENING-PENDING").size();
			completedPatCount = completedPatCount + studyPatientsDAO.findBySiteAndStatus(siteId,"SCREENING-PASSED").size();
			completedPatCount = completedPatCount + studyPatientsDAO.findBySiteAndStatus(siteId,"SCREENING-INPROGRESS").size();
			droppedPatCount = studyPatientsDAO.findBySiteAndStatus(siteId,"DROPPED").size();
			enrolledPatCount = studyPatientsDAO.findBySiteAndStatus(siteId,"IN-TREATMENT").size();
			totalPatCount = enrolledPatCount + completedPatCount + droppedPatCount;
			
			model.setTotalPatCount(totalPatCount);
			model.setCompletedPatCount(completedPatCount);
			model.setEnrolledPatCount(enrolledPatCount);
			model.setDroppedPatCount(droppedPatCount);

		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return model;
	}
}
