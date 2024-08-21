
package com.clinivapps.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.clinivapps.model.AppResponse;
import com.clinivapps.service.MasterDataService;

@Controller
@RequestMapping("/masterdata")
public class MasterDataController {
	public static final String CREATE_VIDEO_ROOM = "/createRoom";



	@Autowired
	MasterDataService masterDataService;


	@RequestMapping(value = MasterDataController.CREATE_VIDEO_ROOM, method = { RequestMethod.POST })
	@ResponseBody
	public AppResponse createVideoRoom() {
		final AppResponse response = new AppResponse();
		try {
			
			masterDataService.createVideo();
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error occured getting data");
			response.setStatus("Error");
		}
		return response;
	}
}
