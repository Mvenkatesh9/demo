package com.clinivapps.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.clinivapps.dao.StudyDAO;
import com.clinivapps.dao.StudyFaqsDAO;
import com.clinivapps.dao.StudyPatientsDAO;
import com.clinivapps.dao.StudyVisitDAO;
import com.clinivapps.dao.StudyDocumentsDAO;
import com.clinivapps.dao.CRFSectionDAO;
import com.clinivapps.dao.ExclusionDAO;
import com.clinivapps.dao.InclusionDAO;
import com.clinivapps.dao.PatientDairyDAO;
import com.clinivapps.dao.PatientVisitsDAO;
import com.clinivapps.dao.ProductDAO;
import com.clinivapps.dao.SiteUserDAO;
import com.clinivapps.dao.TherapeuticDAO;
import com.clinivapps.dao.TrailSiteDAO;
import com.clinivapps.dao.UserDAO;
import com.clinivapps.entity.StudyEntity;
import com.clinivapps.entity.StudyFaqsEntity;
import com.clinivapps.entity.StudyPatientsEntity;
import com.clinivapps.entity.StudyVisitEntity;
import com.clinivapps.entity.StudyDocumentsEntity;
import com.clinivapps.entity.ExclusionEntity;
import com.clinivapps.entity.InclusionEntity;
import com.clinivapps.entity.CRFSectionsEntity;
import com.clinivapps.entity.PatientVisitEntity;
import com.clinivapps.entity.SiteUsersEntity;
import com.clinivapps.entity.TherapeuticAreaEntity;
import com.clinivapps.entity.TrailSiteEntity;
import com.clinivapps.entity.UserEntity;
import com.clinivapps.model.StudyModel;
import com.clinivapps.model.StudyReportModel;
import com.clinivapps.model.StudyVisitModel;
import com.clinivapps.model.TrialSiteModel;
import com.clinivapps.model.InclusionExcusionModel;
import com.clinivapps.model.PatientDairyModel;
import com.clinivapps.model.ReportDoctorModel;
import com.clinivapps.model.ReportPatientModel;
import com.clinivapps.model.SectionModel;
import com.clinivapps.model.StudyDocumentModel;
import com.clinivapps.model.StudyFaqsModel;
import com.clinivapps.util.FileUtil;
import com.clinivapps.util.ClinIVProperty;

@Service("studyService")
@Transactional
public class StudyService {

	@Autowired
	StudyDAO studyDAO;

	@Autowired
	ProductDAO productDAO;

	@Autowired
	StudyDocumentsDAO ethicsDAO;

	@Autowired
	TrailSiteDAO trailSiteDAO;

	@Autowired
	InclusionDAO inclusionDAO;

	@Autowired
	ExclusionDAO exclusionDAO;

	@Autowired
	TherapeuticDAO therapeuticDAO;

	@Autowired
	PatientDairyDAO patientDairyDAO;

	@Autowired
	StudyFaqsDAO studyFaqsDAO;

	@Autowired
	StudyDocumentsDAO studyDocumentsDAO;

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


	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat dateYearFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public List<StudyModel> findAll() {
		List<StudyModel> drugList = new ArrayList<StudyModel>();
		try {
			List<StudyEntity> entities = studyDAO.findAll();
			if (entities != null)
				for (StudyEntity e : entities) {
					StudyModel model = new StudyModel();
					model.setStudyId(e.getStudyId());
					model.setStudyName(e.getStudyName());
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
					model.setStartDate(formatter.format(e.getStartDate()));
					model.setEndDate(formatter.format(e.getEndDate()));
					model.setTherapeuticIds(e.getTherapeuticIds());
					model.setCreatedDate(e.getCreatedDate());
					model.setProtocolNumber(e.getProtocolNumber());
					model.setTotalVisits(e.getVisitCount());
					model.setPatientDuration(e.getPatientDuration());
					model.setStudyStatus(e.getStudyStatus());
					String therapeuticName = "";
					if (e.getTherapeuticIds().contains(",")) {
						String thrIDs[] = e.getTherapeuticIds().split(",");
						for (int i = 0; i < thrIDs.length; i++) {
							TherapeuticAreaEntity thrEntity = new TherapeuticAreaEntity();
							thrEntity = therapeuticDAO.findByThId(Integer.parseInt(thrIDs[i]));
							if (i + 1 < thrIDs.length) {
								therapeuticName = therapeuticName + thrEntity.getTherapeuticName() + ", ";
							} else {
								therapeuticName = therapeuticName + thrEntity.getTherapeuticName();
							}
						}
						model.setTherapeuticNames(therapeuticName);
					} else {
						TherapeuticAreaEntity thrEntity = new TherapeuticAreaEntity();
						thrEntity = therapeuticDAO.findByThId(Integer.parseInt(e.getTherapeuticIds()));
						model.setTherapeuticNames(thrEntity.getTherapeuticName());
					}

					List<TrialSiteModel> trModels = new ArrayList<TrialSiteModel>();
					List<TrailSiteEntity> trailSiteList = trailSiteDAO.findByStudyId(e.getStudyId());
					if (trailSiteList != null) {
						for (TrailSiteEntity en : trailSiteList) {
							TrialSiteModel trModel = new TrialSiteModel();
							trModel.setSiteName(en.getSiteName());
							trModel.setContactName(en.getContactName());
							trModel.setContactNumber(en.getContactNumber());
							trModel.setLocation(en.getLocation());
							trModels.add(trModel);
						}
						model.setTrailSites(trModels);
					}

					drugList.add(model);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return drugList;
	}

	public StudyReportModel getStudyDashboardReportsData(){
		StudyReportModel reportData = new StudyReportModel();
		try {
			
			List<TrailSiteEntity> entities = trailSiteDAO.findAll();
			List<StudyPatientsEntity> pentities = studyPatientsDAO.findAll();

			reportData.setPatCount(pentities.size());
			reportData.setSiteCount(entities.size());	
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return reportData;
	}

	public StudyModel findByStudyId(Integer studyId) {
		StudyModel model = new StudyModel();
		try {
			StudyEntity e = studyDAO.findByStudyId(studyId);
			if (e != null)
				model.setStudyId(e.getStudyId());
			model.setStudyName(e.getStudyName());
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			model.setStartDate(formatter.format(e.getStartDate()));
			model.setEndDate(formatter.format(e.getEndDate()));
			model.setTherapeuticIds(e.getTherapeuticIds());
			model.setCreatedDate(e.getCreatedDate());
			model.setProtocolNumber(e.getProtocolNumber());
			model.setStudyStatus(e.getStudyStatus());
			model.setStudyBannerURL(e.getStudyBannerURL());
			model.setTotalVisits(e.getVisitCount());
			model.setPatientDuration(e.getPatientDuration());
			model.setDoseDuration(e.getDoseDuration());
			model.setContactEmail(e.getContactEmail());
			model.setContactName(e.getContactName());
			model.setContactMobile(e.getContactNumber());
			model.setIcfUrl(e.getIcfUrl());
			model.setPisUrl(e.getPisUrl());
			String therapeuticName = "";
			if (e.getTherapeuticIds().contains(",")) {
				String thrIDs[] = e.getTherapeuticIds().split(",");
				for (int i = 0; i < thrIDs.length; i++) {
					TherapeuticAreaEntity thrEntity = new TherapeuticAreaEntity();
					thrEntity = therapeuticDAO.findByThId(Integer.parseInt(thrIDs[i]));
					if (i + 1 < thrIDs.length) {
						therapeuticName = therapeuticName + thrEntity.getTherapeuticName() + ", ";
					} else {
						therapeuticName = therapeuticName + thrEntity.getTherapeuticName();
					}
				}
				model.setTherapeuticNames(therapeuticName);
			} else {
				TherapeuticAreaEntity thrEntity = new TherapeuticAreaEntity();
				thrEntity = therapeuticDAO.findByThId(Integer.parseInt(e.getTherapeuticIds()));
				model.setTherapeuticNames(thrEntity.getTherapeuticName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	public void createStudy(Model model) throws IOException {
		StudyModel studyModel = (StudyModel) model.asMap().get("study");
		try {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			StudyEntity studyEntity = new StudyEntity();
			studyEntity.setStudyName(studyModel.getStudyName());
			studyEntity.setStartDate(formatter.parse(studyModel.getStartDate()));
			studyEntity.setEndDate(formatter.parse(studyModel.getEndDate()));
			studyEntity.setTherapeuticIds(studyModel.getTherapeuticIds());
			studyEntity.setCreatedDate(Calendar.getInstance().getTime());
			studyEntity.setPrincipalInvestogator(studyModel.getPrincipalInvestigator());
			studyEntity.setCoPrincipalInvestogators(studyModel.getCoPrincipalInvestigator());
			studyEntity.setStudyShortName(studyModel.getStudyShortName());
			studyEntity.setProtocolNumber(studyModel.getProtocolNumber());
			studyEntity.setVisitCount(studyModel.getTotalVisits());
			studyEntity.setPatientDuration(studyModel.getPatientDuration());
			studyEntity.setContactNumber(studyModel.getContactMobile());
			studyEntity.setContactEmail(studyModel.getContactEmail());
			MultipartFile studyImage = (MultipartFile) model.asMap().get("studyImage");
			if (studyImage != null) {
				String _displayPic = ClinIVProperty.getInstance().getProperties("cliniv.home") + "/images/study/";
				_displayPic += FileUtil.copyImage(studyImage,
						ClinIVProperty.getInstance().getProperties("images.loc") + File.separator + "study");
				studyEntity.setStudyBannerURL(_displayPic);
			}
//		    MultipartFile studyICF=(MultipartFile) model.asMap().get("icfDoc");
//		    if (studyICF != null) {
//				String _displayPic = ClinivProperty.getInstance().getProperties("cliniv.home") + "/files/study/icf/";
//				_displayPic += FileUtil.copyImage(studyICF,ClinivProperty.getInstance().getProperties("images.loc") + File.separator + "study"+ File.separator + "icf");
//				studyEntity.setStudyBannerURL(_displayPic);
//		    }
			studyDAO.persist(studyEntity);

		} catch (Exception e) {
			System.out.println(e);

		}

	}

	public void createStudyDocument(Model model) throws IOException {
		StudyDocumentModel studyModel = (StudyDocumentModel) model.asMap().get("study");
		try {

			StudyDocumentsEntity studyDocEntity = new StudyDocumentsEntity();
			studyDocEntity.setTitle(studyModel.getTitle());
			studyDocEntity.setFileType(studyModel.getFileType());
			studyDocEntity.setStudyId(studyModel.getStudyId());
			studyDocEntity.setCreatedDate(Calendar.getInstance().getTime());
			MultipartFile studyImage = (MultipartFile) model.asMap().get("file");
			if (studyImage != null) {
				String _displayPic = ClinIVProperty.getInstance().getProperties("cliniv.home") + "/images/study/";
				_displayPic += FileUtil.copyImage(studyImage,
						ClinIVProperty.getInstance().getProperties("images.loc") + File.separator + "study");
				studyDocEntity.setFileUrl(_displayPic);
			}
			studyDocumentsDAO.persist(studyDocEntity);

		} catch (Exception e) {
			System.out.println(e);

		}

	}


	public void createTrailSite(Model model) throws IOException {
		TrialSiteModel trailSiteModel = (TrialSiteModel) model.asMap().get("trailSite");
		try {
			TrailSiteEntity trailSiteEntity = new TrailSiteEntity();
			trailSiteEntity.setLocation(trailSiteModel.getLocation());
			trailSiteEntity.setSiteName(trailSiteModel.getSiteName());
			trailSiteEntity.setCreatedDate(Calendar.getInstance().getTime());
			trailSiteDAO.persist(trailSiteEntity);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public List<InclusionExcusionModel> getInclusionData(Integer studyId) throws IOException {
		List<InclusionExcusionModel> inclusionList = new ArrayList<InclusionExcusionModel>();
		try {
			List<InclusionEntity> entities = inclusionDAO.findByStudyId(studyId);
			if (entities != null) {
				for (InclusionEntity e : entities) {
					InclusionExcusionModel model = new InclusionExcusionModel();
					model.setStudyId(e.getStudyId());
					model.setTitle(e.getTitle());
					model.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(e.getCreatedDate()));
					model.setId(e.getId());
					model.setValidityPoint(e.getValidityPoint());
					inclusionList.add(model);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return inclusionList;
	}

	public List<InclusionExcusionModel> getExclusionData(Integer studyId) throws IOException {
		List<InclusionExcusionModel> exclusionList = new ArrayList<InclusionExcusionModel>();
		try {
			List<ExclusionEntity> entities = exclusionDAO.findByStudyId(studyId);
			if (entities != null) {
				for (ExclusionEntity e : entities) {
					InclusionExcusionModel model = new InclusionExcusionModel();
					model.setStudyId(e.getStudyId());
					model.setTitle(e.getTitle());
					model.setValidityPoint(e.getValidityPoint());
					model.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(e.getCreatedDate()));
					model.setId(e.getId());
					exclusionList.add(model);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return exclusionList;
	}

	
	public List<StudyFaqsModel> getFaqsData(Integer studyId) throws IOException {
		List<StudyFaqsModel> faqsList = new ArrayList<StudyFaqsModel>();
		try {
			List<StudyFaqsEntity> entities = studyFaqsDAO.findByStudyId(studyId);
			if (entities != null) {
				for (StudyFaqsEntity e : entities) {
					StudyFaqsModel model = new StudyFaqsModel();
					model.setStudyId(e.getStudyId());
					model.setCreatedDate(e.getCreatedDate());
					model.setQuestion(e.getQuestion());
					model.setAnswer(e.getAnswer());
					model.setFaqId(e.getFaqId());
					faqsList.add(model);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return faqsList;
	}

	public List<StudyDocumentModel> getStudyDocumentData(Integer studyId, String type) throws IOException {
		List<StudyDocumentModel> docsList = new ArrayList<StudyDocumentModel>();
		try {
			if (type.equals("ALL")) {
				List<StudyDocumentsEntity> entities = studyDocumentsDAO.findByStudyId(studyId);
				if (entities != null) {
					for (StudyDocumentsEntity e : entities) {
						StudyDocumentModel model = new StudyDocumentModel();
						model.setStudyId(e.getStudyId());
						model.setCreatedDate(e.getCreatedDate());
						model.setTitle(e.getTitle());
						model.setFileUrl(e.getFileUrl());
						model.setFileType(e.getFileType());
						docsList.add(model);
					}
				}
			} else {
				List<StudyDocumentsEntity> entities = studyDocumentsDAO.findByStudyIdAndTitle(studyId, "PROTOCOL");
				if (entities != null) {
					for (StudyDocumentsEntity e : entities) {
						StudyDocumentModel model = new StudyDocumentModel();
						model.setStudyId(e.getStudyId());
						model.setCreatedDate(e.getCreatedDate());
						model.setTitle(e.getTitle());
						model.setFileUrl(e.getFileUrl());
						model.setFileType(e.getFileType());
						docsList.add(model);
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return docsList;
	}

	public List<StudyVisitModel> getStudyVisitsData(Integer studyId) throws IOException {
		List<StudyVisitModel> visitList = new ArrayList<StudyVisitModel>();
		try {
			List<StudyVisitEntity> entities = studyVisitDAO.findByStudyId(studyId);
			if (entities != null) {
				for (StudyVisitEntity e : entities) {
					StudyVisitModel model = new StudyVisitModel();
					model.setStudyId(e.getStudyId());
					model.setVisitId(e.getVisitId());
					model.setVisitName(e.getTitle());
					model.setFromDay(e.getFromDay());
					model.setToDay(e.getToDay());
					visitList.add(model);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return visitList;
	}

	public StudyReportModel getStudyReportsData(){
		StudyReportModel reportData = new StudyReportModel();
		try {
			List<TrailSiteEntity> entities = trailSiteDAO.findAll();
			List<StudyPatientsEntity> pentities = studyPatientsDAO.findAll();
			reportData.setPatCount(pentities.size());
			reportData.setSiteCount(entities.size());			

		} catch (Exception e) {
			System.out.println(e);
		}
		return reportData;
	}

	public StudyReportModel getStudyDoctorReportsData(Integer studyId, String type) throws IOException {
		StudyReportModel reportData = new StudyReportModel();
		try {
			SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			List<SiteUsersEntity> entities = siteUserDAO.findByStudyId(studyId);

			List<ReportDoctorModel> docModels = new ArrayList<ReportDoctorModel>();

			if (entities != null) {
				for (SiteUsersEntity e : entities) {
					if (e.getUserId() != null) {
						if (type.equals("DOC-ENR") || type.equals("DOC-ALL")) {
							ReportDoctorModel model = new ReportDoctorModel();
							List<StudyPatientsEntity> patentitiesDoc = studyPatientsDAO
									.findBySiteAndStudyId(e.getSiteId(), studyId);
							model.setTotalPatients(patentitiesDoc.size());
							docModels.add(model);
						}

					} else {
						if (type.equals("DOC-ALL")) {
							ReportDoctorModel model = new ReportDoctorModel();
							List<StudyPatientsEntity> patentitiesDoc = studyPatientsDAO
									.findBySiteAndStudyId(e.getSiteId(), studyId);
							model.setTotalPatients(patentitiesDoc.size());
							docModels.add(model);
						}
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return reportData;
	}
	public StudyReportModel getStudyPatientReportsData(Integer studyId, String type) throws IOException {
		StudyReportModel reportData = new StudyReportModel();
		try {
			SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			List<StudyPatientsEntity> patentities = studyPatientsDAO.findByStudyId(studyId);

			List<ReportPatientModel> patModels = new ArrayList<ReportPatientModel>();

			if (patentities != null) {
				for (StudyPatientsEntity e : patentities) {
						ReportPatientModel model = new ReportPatientModel();
						model.setPatientId(e.getId());
						UserEntity user = userDAO.findByMobileNoAndRole(e.getMobileNumber(), 9);
						model.setFullName(user.getFirstName() + " " + user.getLastName());
						model.setMobileNumber(e.getMobileNumber());
						model.setEmailId(e.getEmailId());
						model.setHospital("ClinIV Hospital");
						model.setLocation("Hyderabad");
						model.setJoinedDate(dmyFormat.format(e.getRegisteredDate()));
						List<PatientVisitEntity> visitEntity = patientVisitDAO.findByStudyPatientStudy(studyId,
								e.getPatientId());
						model.setTotalVisits(visitEntity.size());
						Integer visitCompleted = 0;
						for (PatientVisitEntity entit : visitEntity) {
							if (!entit.getStatus().equals("OPEN")) {
								visitCompleted = visitCompleted + 1;
							}
						}
						model.setCompletedVisits(visitCompleted);
						patModels.add(model);
					}
			 }
			reportData.setPatientList(patModels);
		} catch (Exception e) {
			System.out.println(e);
		}
		return reportData;
	}
	public List<TrialSiteModel> getAllSites() {
		List<TrialSiteModel> siteList = new ArrayList<TrialSiteModel>();
		try {
			List<TrailSiteEntity> entities = trailSiteDAO.findAll();
			if (entities != null) {
				for (TrailSiteEntity e : entities) {
					TrialSiteModel model = new TrialSiteModel();
					model.setId(e.getId());
					model.setCreatedDate(dateYearFormat.format(e.getCreatedDate()));
					model.setContactName(e.getContactName());
					model.setContactNumber(e.getContactNumber());
					model.setLocation(e.getLocation());
					model.setSiteName(e.getSiteName());
					siteList.add(model);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return siteList;
	}
	
	
}
