package com.qm.nms.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.qm.core.base.GlobalCfg;
import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.core.base.WebStatus;
import com.qm.core.base.WebStatus4P;
import com.qm.nms.domain.LabLessonPlan;
import com.qm.nms.domain.LabLessonPlanInfo;
import com.qm.nms.domainEnum.LessonPlanEnum;
import com.qm.nms.domainEnum.TermEnum;
import com.qm.nms.query.LabLessonPlanQuery;
import com.qm.nms.query.ScheduleQuery;
import com.qm.nms.service.ILabLessonPlanService;
import com.qm.nms.service.ILabSchoolLessonService;
import com.qm.sys.domain.SysUser;

import exception.BizException;

@Controller
@RequestMapping("/plan")
public class LabLessonPlanController {

	@Autowired
	private ILabLessonPlanService labLessonPlanService;
	
	@Autowired
	private ILabSchoolLessonService labSchoolLessonService;
	
	@RequestMapping(value = "/find")
	@ResponseBody
	public WebStatus4P findLessonPlan(LabLessonPlanQuery query,PageInfo pageInfo) throws Exception{
		GridListData lpan = labLessonPlanService.findLessonPlan(query,pageInfo);
		return new WebStatus4P(lpan);
	}
	@RequestMapping(value = "/findInfo")
	@ResponseBody
	public WebStatus4P findLesson(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY)SysUser user) throws Exception{
		List<String> list = labLessonPlanService.findLesson(user);
		return new WebStatus4P(list);
	}
	/**
	 * 教学目录树
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tree")
	@ResponseBody
	public List<Map<String, Object>> findTree(long id) throws Exception {
		List<Map<String, Object>> list = labLessonPlanService.getPlanInfoTree(id);
		return list;
	}

	@RequestMapping(value = "/count")
	@ResponseBody
	public WebStatus findCount() {
		long count = labLessonPlanService.getLessonPlanCount();
		return new WebStatus(count);
	}

	@ResponseBody
	@RequestMapping(value="/update",method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json; charset=UTF-8"})
	public WebStatus updateLessonPlanInfo(@RequestParam(value = "id", required = true) Long id,String title,
			@RequestParam(value="remark", required=false) LessonPlanEnum remark, 
			@RequestParam(value="scene", required=false) LessonPlanEnum scene,
			@RequestParam(value="demonstration", required=false) LessonPlanEnum demonstration,
			@RequestParam(value = "grade", required = false, defaultValue = "0") Long grade,
			@RequestParam(value = "gradeYear", required = false) TermEnum gradeYear) {
		try {
			this.labLessonPlanService.updateLessPlanInfo(id, title, remark, scene, demonstration, grade, gradeYear);
			return new WebStatus().success();
		} catch (BizException e) {
			return new WebStatus(e);
		}
	}

	/**
	 * 学校教学目录树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/plantree")
	@ResponseBody
	public WebStatus findPlanTree() {
		// List<Map<String, Object>> list =
		// labSchoolLessonService.getLessonPlanInfoTree(paramSchool, paramLong);
		// return new WebStatus(list);
		return new WebStatus();
	}
	
	@RequestMapping("/findLabLesson")
	@ResponseBody
	public Map<String,Object> findLabLesson(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY)SysUser user,ScheduleQuery query,PageInfo pageinfo) throws Exception{
		Map<String,Object> mp = labLessonPlanService.findLabLesson(140);
		return mp;
	}	
}
