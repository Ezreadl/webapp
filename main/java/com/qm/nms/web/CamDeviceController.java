package com.qm.nms.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qm.core.base.BaseController;
import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.core.base.WebStatus;
import com.qm.nms.domain.CamDevice;
import com.qm.nms.query.CourseQuery;
import com.qm.nms.service.ICamDeviceService;
import com.qm.nms.service.ICourseService;

@Controller
@RequestMapping("/camera")
public class CamDeviceController{
	
	@Autowired
	public ICamDeviceService camDeviceService;

	@RequestMapping(value="/find")
	@ResponseBody
	public WebStatus findCam() {
		List<CamDevice> lst = new ArrayList<CamDevice>();
		lst = camDeviceService.findAll();
		return new WebStatus(lst);
	}
	
	@RequestMapping(value="/find/organ/{organid}")
	@ResponseBody
	public WebStatus findByOrganID(@PathVariable("organid") long organid) {
		List<CamDevice> lst = new ArrayList<CamDevice>();
		lst = camDeviceService.findByOrganID(organid);
		return new WebStatus(lst);
	}
	
//	@RequestMapping(value="/look/organ/{organid}")
//	public String getUrlByOrganID(@PathVariable("organid") long organid) {
//		List<CamDevice> lst = new ArrayList<CamDevice>();
//		lst = camDeviceService.findByOrganID(organid);
//		StringBuffer url = new StringBuffer();
//		System.getProperty(key, def);
//		url.append("rtsp://");
//		for (CamDevice camDevice : lst) {
//			
//			url.append(camDevice.getPortnum());
//			url.append(camDevice.getAccount());
//			url.append(camDevice.getPasswd());
//			url.append(camDevice.getPasswd());
//		}
//		return n;
//	}

}
