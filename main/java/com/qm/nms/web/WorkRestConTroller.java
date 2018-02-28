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
import com.qm.core.base.WebStatus4P;
import com.qm.nms.domain.UsageGoal;
import com.qm.nms.domain.WorkRest;
import com.qm.nms.service.IUsageGoalService;
import com.qm.nms.service.IWorkRestService;
import com.qm.sys.domain.SysUser;

@Controller
@RequestMapping("/work")
public class WorkRestConTroller {

	@Autowired
	IWorkRestService workRestService;

	@RequestMapping("/find")
	@ResponseBody
	public WebStatus4P findUsageGoal(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY)SysUser user) throws Exception {
		List<String> lstr = new ArrayList<String>(); 
		StringBuilder sb = new StringBuilder();
		List<WorkRest> lwork = workRestService.findSchoolWorkOfUser(user);
		for (WorkRest work : lwork) {
			sb = new StringBuilder();
			sb.append(work.getName());
			sb.append(" ["+work.getStartTime());
			sb.append("~" + work.getEndTime()+"]");
			lstr.add(sb.toString());
		}
		return new WebStatus4P(lstr);
	}
}
