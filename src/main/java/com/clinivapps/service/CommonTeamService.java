package com.clinivapps.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clinivapps.dao.SponsorUserDAO;
import com.clinivapps.dao.UserDAO;
import com.clinivapps.entity.SponsorUsersEntity;
import com.clinivapps.model.CommonTeamsModel;


@Service("commonTeamService")
@Transactional

public class CommonTeamService {

	@Autowired
	SponsorUserDAO sponsorUserDAO;

	@Autowired
	UserDAO userDAO;


	public List<CommonTeamsModel> findAllTeam(){
		List<CommonTeamsModel> companyList = new ArrayList<CommonTeamsModel>();
		try {
			List<SponsorUsersEntity> entities = sponsorUserDAO.findAll();
			if(entities != null)
				for(SponsorUsersEntity e : entities) {
					CommonTeamsModel model = new CommonTeamsModel();
					model.setId(e.getId());
					model.setUser(e.getUser());
					model.setFullName(e.getUser().getFirstName() + " "  + e.getUser().getLastName());
					model.setMobileNumber(e.getUser().getMobileNo());
					model.setEmailId(e.getUser().getEmailId());
					model.setLocation(e.getLocation());
					model.setStatus(e.getUser().getStatus());
					model.setDesignation(e.getDesignation());
					model.setCreatedDate(e.getCreatedDate());
					companyList.add(model);
				}


		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return companyList;
	} 


}

