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
import com.qm.core.base.WebStatus;
import com.qm.nms.domain.UsageGoal;
import com.qm.nms.service.IUsageGoalService;
import com.qm.sys.domain.SysUser;

@Controller
@RequestMapping("/usegoal")
public class UsageGoalConTroller {

	@Autowired
	IUsageGoalService usageGoalService;

	@RequestMapping("/find")
	@ResponseBody
	public WebStatus findUsageGoal() throws Exception {
		List<Map<String, Object>> lmap = new ArrayList<Map<String, Object>>();
		List<UsageGoal> lgoal = usageGoalService.findUsageGoal();
//		String schoolType = "";
		for (UsageGoal usageGoal : lgoal) {
			Map<String, Object> mp = new HashMap<String, Object>();
//			if (usageGoal.getSchoolType() == 1) {
//				schoolType = "小学";
//			} else if (usageGoal.getSchoolType() == 2) {
//				schoolType = "初中";
//			} else if (usageGoal.getSchoolType() == 3) {
//				schoolType = "高中";
//			} else if (usageGoal.getSchoolType() == 4) {
//				schoolType = "九年一贯制";
//			} else if (usageGoal.getSchoolType() == 5) {
//				schoolType = "完全中学";
//			} else if (usageGoal.getSchoolType() == 6) {
//				schoolType = "十二年一贯制";
//			}
			mp.put("schoolType", usageGoal.getSchoolType());
			mp.put("groupRate", usageGoal.getGroupRate());
			mp.put("openRate", usageGoal.getOpenRate());
			mp.put("demoRate", usageGoal.getDemoRate());
			lmap.add(mp);
		}
		return new WebStatus(lmap);
	}

	@RequestMapping("/find/{type}")
	@ResponseBody
	public WebStatus findUsageGoalByType(@PathVariable("type") int type) throws Exception {
		List<Map<String, Object>> lmap = new ArrayList<Map<String, Object>>();
		UsageGoal goal = usageGoalService.findByType(type);
//		String schoolType = "";
		Map<String, Object> mp = new HashMap<String, Object>();
//		if (goal.getSchoolType() == 1) {
//			schoolType = "小学";
//		} else if (goal.getSchoolType() == 2) {
//			schoolType = "初中";
//		} else if (goal.getSchoolType() == 3) {
//			schoolType = "高中";
//		} else if (goal.getSchoolType() == 4) {
//			schoolType = "九年一贯制";
//		} else if (goal.getSchoolType() == 5) {
//			schoolType = "完全中学";
//		} else if (goal.getSchoolType() == 6) {
//			schoolType = "十二年一贯制";
//		}
		mp.put("schoolType", goal.getSchoolType());
		mp.put("groupRate", goal.getGroupRate());
		mp.put("openRate", goal.getOpenRate());
		mp.put("demoRate", goal.getDemoRate());
		lmap.add(mp);
		return new WebStatus(lmap);
	}

	@RequestMapping("/save")
	@ResponseBody
	public WebStatus saveUsageGoal(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser user, UsageGoal goal)
			throws Exception {
		try {
			usageGoalService.updateGoal(user, goal);
			return new WebStatus(goal);
		} catch (Exception e) {
			return new WebStatus(e).failure();
		}
	}

	@RequestMapping("/delete/{oid}")
	@ResponseBody
	public WebStatus delUsageGoal(@PathVariable("oid") int oid) throws Exception {
		UsageGoal goal = usageGoalService.findByID(oid);
		return new WebStatus(goal);
	}
}
