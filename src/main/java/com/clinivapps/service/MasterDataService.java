package com.clinivapps.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.clinivapps.entity.LogReportEntity;
import com.clinivapps.entity.QuestionsEntity;
import com.clinivapps.entity.RoleEntity;
import com.clinivapps.entity.SiteUsersEntity;
import com.clinivapps.entity.StudyPatientsEntity;
import com.clinivapps.entity.UserEntity;
import com.clinivapps.model.LogReportModel;
import com.clinivapps.dao.LogReportDAO;
import com.clinivapps.dao.QuestionDAO;
import com.clinivapps.dao.SiteUserDAO;
import com.clinivapps.dao.StudyPatientsDAO;
import com.clinivapps.dao.DisplayPictureDAO;


import com.clinivapps.dao.RoleDAO;

import com.clinivapps.dao.UserDAO;
import com.clinivapps.model.Role;
import com.twilio.Twilio;
import com.twilio.rest.video.v1.Room;

@Service("masterDataService")
@Transactional
public class MasterDataService {

	@Autowired
	RoleDAO roleDAO;

	@Autowired
	DisplayPictureDAO displayPictureDAO;

	@Autowired
	UserDAO userDAO;

	@Autowired
	LogReportDAO logReportDAO;

	@Autowired
	StudyPatientsDAO studyPatientsDAO;

	@Autowired
	SiteUserDAO siteUserDAO;

	@Autowired
	QuestionDAO questionDAO;

	private static final Log log = LogFactory.getLog(MasterDataService.class);
	public static final String ACCOUNT_SID = "ACa972fb48bea9545d357d2ee6288049b3";
	public static final String AUTH_TOKEN = "e23967a54beb2eab116a028e9e2bf614";

	public List<Role> getAllRoles(){
		List<Role> roles = new ArrayList<Role>();
		try
		{
			for(com.clinivapps.entity.RoleEntity roleEntity : roleDAO.findAll())
			{
				Role role = new Role();
				role.setRoleId(roleEntity.getRoleId());
				role.setName(roleEntity.getRoleName());
				role.setDescription(roleEntity.getRoleDesc());
				roles.add(role);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return roles;
	}

	public void createVideo() {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Room room = Room.creator().setUniqueName("V202020").create();

		System.out.println(room.getSid());
	}
	public Map<String, List<LogReportModel>> getAllLogReportsDownload(String startDate, String endDate) {
		Map<String, List<LogReportModel>> reportDataHMap = new HashMap<String, List<LogReportModel>>();
		try {

			List<LogReportModel> logReports = new ArrayList<LogReportModel>();
			SimpleDateFormat dateTimeFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
			Date strDate = dateTimeFormat.parse(startDate + " 00:00:00");
			Date endate = dateTimeFormat.parse(endDate + " 23:59:59");
			List<LogReportEntity> entities = logReportDAO.findByDate(strDate, endate);
			if(entities != null) {
				for(LogReportEntity e : entities) {
					LogReportModel model = new LogReportModel();
					model.setActivity(e.getActivity());
					model.setDescription(e.getDescription());
					if (e.getQuestionId() != null) {
						QuestionsEntity questEnt = questionDAO.findByQuestionId(e.getQuestionId());
						model.setFieldName(questEnt.getQuestionTitle());
					}
					model.setOriginalAnswer(e.getOriginalAnswer());
					model.setModifiedAnswer(e.getModifiedAnswer());
					model.setTrailParticipant(e.getTrialParticipant());
					model.setSiteId("1");
					if (e.getUserId() != null) {
						UserEntity user = userDAO.findByUserId(e.getUserId());
						if (user.getRole() != null) {
							RoleEntity role = roleDAO.findByRoleId(user.getRole());
							model.setRoleName(role.getRoleName());
							if (role.getRoleId() == 5) {
								SiteUsersEntity siteUser = siteUserDAO.findByUserId(user.getUserId());
								model.setSiteId(siteUser.getSiteId().toString());
							}
						}
						else {
							model.setRoleName("ROLE_PATIENT");
							StudyPatientsEntity studyPat = studyPatientsDAO.findByPatientId(e.getTrialParticipant());
							model.setSiteId(studyPat.getTrailSiteId().toString());
						}
					}
					else {
						model.setRoleName("ROLE_PATIENT");
						StudyPatientsEntity studyPat = studyPatientsDAO.findByPatientId(e.getTrialParticipant());
						model.setSiteId(studyPat.getTrailSiteId().toString());
					}
					model.setCreatedDate(dateTimeFormat.parse(e.getCreatedDate().toString()));
					logReports.add(model);
				}
				reportDataHMap.put("LogReports", logReports);
			}
			else {
				reportDataHMap = null;
			}
		}
		catch (Exception e2) {
			e2.printStackTrace();
		}
//		System.out.println(reportDataHMap.get("LogReports").get(0).getActivity());
		return reportDataHMap;
	}

	public void createLogReport(LogReportModel model) {
		LogReportEntity entity = new LogReportEntity();
		entity.setActivity(model.getActivity());
		entity.setDescription(model.getDescription());
		entity.setUserId(model.getUserId());
		entity.setCreatedDate(model.getCreatedDate());
		entity.setTrialParticipant(model.getTrailParticipant());
		logReportDAO.persist(entity);
	}


}
