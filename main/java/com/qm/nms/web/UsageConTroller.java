package com.qm.nms.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.qm.core.base.GlobalCfg;
import com.qm.core.base.PageInfo;
import com.qm.core.base.WebStatus;
import com.qm.core.base.WebStatus4P;
import com.qm.nms.query.ScheduleQuery;
import com.qm.nms.service.IScheduleService;
import com.qm.nms.service.IUsageService;
import com.qm.sys.domain.SysUser;

@Controller
@RequestMapping("/usage")
public class UsageConTroller {

	@Autowired IUsageService usageService;
	@Autowired IScheduleService scheduleService;
	
	@RequestMapping("/find/ID/{oid}")
	@ResponseBody
	public WebStatus4P findUsageByID(@PathVariable("oid") int oid,PageInfo pageInfo) throws Exception{
		List<Map<String, Object>> luse = usageService.findByType(oid,pageInfo);
		return new WebStatus4P(luse);
	}
	@RequestMapping("/findAllSchedule")
	@ResponseBody
	public Map<String,Object> findAllSchedule(ScheduleQuery query,PageInfo pageinfo) throws Exception{
		return scheduleService.findAllSchedule(query,pageinfo);
	}
	@RequestMapping("/find/{type}")
	@ResponseBody
	public WebStatus4P findUsageByType(@PathVariable("type") int type,PageInfo pageInfo) throws Exception{
		List<Map<String, Object>> luse   = usageService.findByType(type,pageInfo);
		return new WebStatus4P(luse);
	}	
	@RequestMapping("/findMy")
	@ResponseBody
	public WebStatus4P findLastTopUsage(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY)SysUser user,PageInfo pageInfo) throws Exception{
		pageInfo.setLimit(10);
		pageInfo.setDir("desc");
//		List<Map<String,List<Map<String, Object>>>> myList  = new ArrayList<Map<String,List<Map<String, Object>>>>();
		Map<String,List<Map<String, Object>>> mp = new HashMap<String,List<Map<String, Object>>>();
		List<Map<String, Object>> one = usageService.findMyLabUsage(user,pageInfo);
		List<Map<String, Object>> two  = usageService.findRateRank(user,pageInfo);
		pageInfo.setDir("asc");
		List<Map<String, Object>> three = usageService.findRateRank(user,pageInfo);
		mp.put("first", one);
		mp.put("second", two);
		mp.put("third", three);
//		myList.add(mp);
		return new WebStatus4P(mp);
	}

}
