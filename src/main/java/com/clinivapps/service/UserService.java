package com.clinivapps.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.clinivapps.dao.DeviceTokenDAO;
import com.clinivapps.dao.DisplayPictureDAO;
import com.clinivapps.dao.SiteUserDAO;
import com.clinivapps.dao.StudyPatientsDAO;
import com.clinivapps.dao.UserDAO;
import com.clinivapps.dao.UserSecurityDAO;
import com.clinivapps.entity.UserEntity;
import com.clinivapps.entity.UserSecurityEntity;
import com.clinivapps.model.Role;
import com.clinivapps.model.User;
import com.clinivapps.model.UserSecurityContext;
import com.clinivapps.util.PasswordProtector;
import com.clinivapps.util.Util;
import com.clinivapps.entity.DeviceTokenEntity;
import com.clinivapps.entity.DisplayPictureEntity;
import com.clinivapps.entity.SiteUsersEntity;
import com.clinivapps.model.ForgotPassword;
import com.clinivapps.model.LoginRequestModel;
import com.clinivapps.model.LoginResponseModel;

@Service("userService")
@Transactional
public class UserService implements UserDetailsService{

	@Autowired
	UserSecurityDAO dao;

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	DisplayPictureDAO displayPictureDAO;
	
	@Autowired
	DeviceTokenDAO deviceTokenDAO;

	@Autowired
	StudyPatientsDAO studyPatientsDAO;
	
	@Autowired
	SiteUserDAO siteUserDAO;

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserSecurityEntity securityEntity = dao.findByLoginId(userName);
		UserSecurityContext context = null;
		if(securityEntity != null && securityEntity.getLoginId() != null){
			context = new UserSecurityContext();
			context.setPassword(securityEntity.getPassword());
			context.setUsername(securityEntity.getLoginId());
			context.setUserSecurityId(securityEntity.getUserSecId());
			context.setLoginTime(new Date());
			if("ADMIN".equals(securityEntity.getType())){
				if (securityEntity.getStatus().equalsIgnoreCase("Active")) {
					Role appRole = new Role();
					appRole.setName("ROLE_ADMIN");
					appRole.setDescription("Application Admin User");
					context.getAuthorities().add(appRole);
				}
				else{
					throw new UsernameNotFoundException("Invalid loginId");
				}
			}
			else if("SPONSOR_ADMIN".equals(securityEntity.getType())){
				if (securityEntity.getStatus().equalsIgnoreCase("Active")) {
				Role appRole = new Role();
				appRole.setName("ROLE_SPONSOR_ADMIN");
				appRole.setDescription("Sponsor Admin User");
				context.getAuthorities().add(appRole);
				}
				else{
					throw new UsernameNotFoundException("Invalid loginId");
				}
			}
			else if("DATA_MANAGER".equals(securityEntity.getType())){
				if (securityEntity.getStatus().equalsIgnoreCase("Active")) {
				Role appRole = new Role();
				appRole.setName("ROLE_DATA_MANAGER");
				appRole.setDescription("Data Manager User");
				context.getAuthorities().add(appRole);
				}
				else{
					throw new UsernameNotFoundException("Invalid loginId");
				}
			}
			securityEntity.setLastLoginDate(new Date());
		    dao.merge(securityEntity);
		}
		else{
			throw new UsernameNotFoundException("Invalid loginId");
		}
		return context;
	}
	public User findUserByUserSecurityId(Integer id){
		User user = null;
		UserEntity userEntity = userDAO.findBySecurityId(id);
		if(userEntity != null){
			user = new User();
			user.setEmailId(userEntity.getEmailId());
			user.setFirstName("Dr. " + userEntity.getFirstName());
			user.setUserId(userEntity.getUserId());
			user.setLastName(userEntity.getLastName());
			user.setMobileNo(userEntity.getMobileNo());
			user.setTitle(userEntity.getTitle());
			if (userEntity.getDisplayPicture() != null) {
	        	user.setDisplayPicture(userEntity.getDisplayPicture().getImageUrl());
			}
		}
		return user;
	}
	public String fetchUserNameByUserSecurityId(Integer id){
		String userName="";
		UserEntity userEntity = userDAO.findBySecurityId(id);
		if(userEntity != null){
			userName =Util.getTitle(userEntity.getTitle())+ userEntity.getFirstName();
			userName += " "+userEntity.getLastName();
		}
		return userName;
	}

	public void updateRegistrationToken(String regToken, String username){
		userDAO.updateRegistrationToken(regToken, username);
	}

	public void updatePassword(String email, String password){
		dao.updatePassword(email, PasswordProtector.encrypt(password));
	}

	public LoginResponseModel isValid(final LoginRequestModel model) {
        LoginResponseModel loginResponse = new LoginResponseModel();
        final UserEntity entity = userDAO.findByMobileNoAndRole(model.getUsername(), Integer.parseInt(model.getRoleId()));
        System.out.println(PasswordProtector.encrypt(model.getPassword()));
        if (entity.getSecurity().getPassword().equals(PasswordProtector.encrypt(model.getPassword()))) {
        	if (entity.getUserId() != null && model != null) {
                final DeviceTokenEntity e = new DeviceTokenEntity();
                e.setDeviceToken(model.getDeviceToken());
                e.setUserId(entity.getUserId());
                e.setLoginId(model.getUsername());
                e.setCreatedDate(Calendar.getInstance().getTime());
                e.setPlatform(model.getPlatform());
                deviceTokenDAO.persist(e);
            }
            if (entity.getDisplayPicture().getDpId() != null) {
                final DisplayPictureEntity dpEntity = displayPictureDAO.findDPImage(entity.getDisplayPicture().getDpId());
                loginResponse.setDisplayPictureUrl(dpEntity.getImageUrl());
            }
            else {
                loginResponse.setDisplayPictureUrl("");
            }
            loginResponse.setUserId(entity.getUserId());
            loginResponse.setStatus(entity.getSecurity().getStatus());
            loginResponse.setRoleId(entity.getRole());
            if (entity.getRole().equals(3) || entity.getRole().equals(4) || entity.getRole().equals(5)) {
            	SiteUsersEntity docEntity = siteUserDAO.findByUserId(entity.getUserId());
            	loginResponse.setOtherId(docEntity.getSiteId());
    		}
            loginResponse.setDisplayName(String.valueOf(entity.getFirstName()) + " " + entity.getLastName());
            if (entity.getSecurity().getStatus().equals("Active")) {
                loginResponse = loginResponse;
                entity.getSecurity().setLastLoginDate(Calendar.getInstance().getTime());
                userDAO.merge(entity);
            }
            else {
                loginResponse = null;
            }
		}
        else {
            loginResponse = null;
        }
        return loginResponse;
    }
    
    public User findByLoginId(final String loginid) {
        User user = null;
        final UserEntity userEntity = userDAO.findByEmailId(loginid);
        if (userEntity != null) {
            user = new User();
            user.setEmailId(userEntity.getEmailId());
            user.setFirstName(String.valueOf(Util.getTitle(userEntity.getTitle())) + userEntity.getFirstName());
            user.setUserSecurityId(userEntity.getSecurity().getUserSecId());
            user.setLastName(userEntity.getLastName());
            user.setMobileNo(userEntity.getMobileNo());
            user.setTitle(userEntity.getTitle());
        }
        return user;
    }
    public User findByUserId(final Integer userId) {
        User user = null;
        final UserEntity userEntity = userDAO.findByUserId(userId);
        if (userEntity != null) {
            user = new User();
            user.setEmailId(userEntity.getEmailId());
            user.setFirstName(userEntity.getFirstName());
            user.setLastName(userEntity.getLastName());
            user.setUserSecurityId(userEntity.getSecurity().getUserSecId());
            user.setLastName(userEntity.getLastName());
            user.setMobileNo(userEntity.getMobileNo());
            user.setRoleId(userEntity.getRole());
            user.setTitle(userEntity.getTitle());
            user.setStatus(userEntity.getStatus());
        }
        return user;
    }
    
    public User findByMobileNumber(final String mobileNumber, Integer role) {
        User user = null;
        final UserEntity userEntity = userDAO.findByMobileNoAndRole(mobileNumber, role);
        if (userEntity.getMobileNo() != null) {
            user = new User();
            user.setEmailId(userEntity.getEmailId());
            user.setFirstName(String.valueOf(Util.getTitle(userEntity.getTitle())) + userEntity.getFirstName());
            user.setUserSecurityId(userEntity.getSecurity().getUserSecId());
            user.setLastName(userEntity.getLastName());
            user.setMobileNo(userEntity.getMobileNo());
            user.setTitle(userEntity.getTitle());

        }
        return user;
    }
    public User findByMobileNumberAndRoleId(String mobileNumber, Integer role) {
        User user = null;
        final UserEntity userEntity = userDAO.findByMobileNoAndRole(mobileNumber, role);
        if (userEntity.getMobileNo() != null) {
            user = new User();
            user.setEmailId(userEntity.getEmailId());
            user.setFirstName(String.valueOf(Util.getTitle(userEntity.getTitle())) + userEntity.getFirstName());
            user.setUserSecurityId(userEntity.getSecurity().getUserSecId());
            user.setLastName(userEntity.getLastName());
            user.setMobileNo(userEntity.getMobileNo());
            user.setTitle(userEntity.getTitle());
            user.setStatus(userEntity.getStatus());
            user.setUserId(userEntity.getUserId());
            user.setPassword(userEntity.getSecurity().getPassword());
            user.setCreatedDate(userEntity.getSecurity().getCreatedDate().toString());
        }
        return user;
    }
    public void updatePassword(final ForgotPassword forgotPassword, Integer secId) {
        final UserSecurityEntity security = dao.findBySecurityId(secId);
        security.setPassword(PasswordProtector.encrypt(forgotPassword.getNewPassword()));
        dao.merge(security);
    }
    
    
    public String changePassword(final ForgotPassword model) {
        String response = null;
        final UserEntity entity = userDAO.findByUserId(model.getUserId());
        if (entity != null) {
        	
            if (!entity.getSecurity().getPassword().equals(PasswordProtector.encrypt(model.getOldPassword()))) {
                response = "Worng Password";
            }
            else {
                entity.getSecurity().setPassword(PasswordProtector.encrypt(model.getNewPassword()));
                userDAO.merge(entity);
                response = "Success";
            }
        }
        if (entity == null) {
            response = "No Data";
        }
        return response;
    }
    public void createUser(final User userModel) throws IOException, ParseException {
        try {
            final UserSecurityEntity securityEntity = new UserSecurityEntity();
            securityEntity.setStatus("Active");
            securityEntity.setLoginId(userModel.getMobileNo());
            securityEntity.setPassword(PasswordProtector.encrypt(userModel.getMobileNo()));
            securityEntity.setType(userModel.getType());
            securityEntity.setCreatedDate(Calendar.getInstance().getTime());
            final UserEntity userEntity = new UserEntity();
            userEntity.setSecurity(securityEntity);
            userEntity.setFirstName(userModel.getFirstName());
            userEntity.setLastName(userModel.getLastName());
            userEntity.setEmailId(userModel.getEmailId());
            userEntity.setMobileNo(userModel.getMobileNo());
            userEntity.setTitle(userModel.getTitle());
            final DisplayPictureEntity displayPictureEntity = displayPictureDAO.findDPImage(1);
            userEntity.setDisplayPicture(displayPictureEntity);
            userEntity.setStatus("Active");
            userEntity.setRole(userModel.getRoleId());
            userDAO.persist(userEntity);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    
}

