package com.clinivapps.service;

import java.io.File;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.clinivapps.dao.DisplayPictureDAO;
import com.clinivapps.dao.UserDAO;
import com.clinivapps.entity.DisplayPictureEntity;
import com.clinivapps.entity.UserEntity;
import com.clinivapps.model.DisplayPicture;
import com.clinivapps.util.FileUtil;
import com.clinivapps.util.ClinIVProperty;
import com.clinivapps.util.ClinIVException;

@Service("dpService")
@Transactional
public class DisplayPictureService {

	@Autowired
	DisplayPictureDAO displayPictureDAO;

	@Autowired
	UserDAO userDAO;

	public boolean uploadDisplayPicture(Model model) throws ClinIVException
	{
		boolean flag = false;
		try
		{
			DisplayPicture dp=(DisplayPicture) model.asMap().get("displayPicture");


			if(dp==null || dp.getLoginId()==null || "null".equalsIgnoreCase(dp.getLoginId().trim()) || "".equalsIgnoreCase(dp.getLoginId().trim())){
				flag=false;
			}else{
				UserEntity userEntity = userDAO.findByEmailId(dp.getLoginId().trim());

				if(userEntity != null && userEntity.getUserId() != null)
				{
					DisplayPictureEntity dpEntity = new DisplayPictureEntity();
					if(dp.getImgData()!=null){
						String _displayPic = ClinIVProperty.getInstance().getProperties("cliniv.home") + "static/images/displaypictures/";
						_displayPic += FileUtil.copyBinaryData(dp.getImgData().getBytes(),ClinIVProperty.getInstance().getProperties("images.loc")+File.separator+"displaypictures",dp.getFileName());
						dpEntity.setImageUrl(_displayPic);
						dpEntity=new DisplayPictureEntity();
						dpEntity.setImageUrl(_displayPic);
						model.addAttribute("url",_displayPic);
				}
					displayPictureDAO.persist(dpEntity);
					userEntity.setDisplayPicture(dpEntity);
					userDAO.merge(userEntity);
					flag = true;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new ClinIVException(e);
		}
		return flag;
	}

	public boolean upDateDpwithMultipartFile(Model model) throws ClinIVException
	{
		boolean flag = false;
		try
		{
			DisplayPicture dp=(DisplayPicture) model.asMap().get("displayPicture");


			if(dp==null || dp.getLoginId()==null || "null".equalsIgnoreCase(dp.getLoginId().trim()) || "".equalsIgnoreCase(dp.getLoginId().trim())){
				flag=false;
			}else{
				UserEntity userEntity = userDAO.findByEmailId(dp.getLoginId().trim());

				if(userEntity != null && userEntity.getUserId() != null)
				{
					DisplayPictureEntity dpEntity = new DisplayPictureEntity();
				    MultipartFile idPic=(MultipartFile) model.asMap().get("ipimage");

					if(idPic !=null){
						String _displayPic = ClinIVProperty.getInstance().getProperties("cliniv.home") + "static/images/displaypictures/";
						_displayPic += FileUtil.copyImage(idPic,ClinIVProperty.getInstance().getProperties("images.loc")+File.separator+"displaypictures");
						dpEntity.setImageUrl(_displayPic);
						dpEntity=new DisplayPictureEntity();
						dpEntity.setImageUrl(_displayPic);
						model.addAttribute("url",_displayPic);
				}
					displayPictureDAO.persist(dpEntity);
					userEntity.setDisplayPicture(dpEntity);
					userDAO.merge(userEntity);
					flag = true;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new ClinIVException(e);
		}
		return flag;
	}
	public DisplayPicture getDisplayPicture(DisplayPicture dp) throws ClinIVException
	{
		try
		{
			UserEntity userEntity = userDAO.findByEmailId(dp.getLoginId());
			DisplayPictureEntity dpEntity = userEntity.getDisplayPicture();
			dp.setdPicture(dpEntity.getImageUrl());

		}
		catch(Exception e)
		{
			throw new ClinIVException(e);
		}

		return dp;

	}

	public DisplayPicture getUserDisplayPicture(Integer userId)
	{
		DisplayPicture displayPicture = null;
		UserEntity userEntity = userDAO.findBySecurityId(userId);
		if(userEntity != null)
		{
			DisplayPictureEntity displayPictureEntity = userEntity.getDisplayPicture();
			if(displayPictureEntity != null)
			{
				displayPicture = new DisplayPicture();
				displayPicture.setdPicture(displayPictureEntity.getImageUrl());
				displayPicture.setDpId(displayPictureEntity.getDpId());
			}
		}

		return displayPicture;

	}

}
