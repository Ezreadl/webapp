package com.qm.nms.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.qm.core.base.GlobalCfg;
import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.core.base.WebStatus;
import com.qm.core.base.WebStatus4P;
import com.qm.core.util.DateUtil;
import com.qm.nms.domain.ApptRecord;
import com.qm.nms.domain.Organization;
import com.qm.nms.domain.Schedule;
import com.qm.nms.query.ApptRcdQuery;
import com.qm.nms.service.IApptRcdService;
import com.qm.nms.service.IOrganService;
import com.qm.nms.service.IScheduleService;
import com.qm.sys.domain.SysUser;

@Controller
@RequestMapping("/appt")
public class ApptRcdController {
	
	@Autowired
	private IApptRcdService apptRcdService;
	
	@Autowired
	private IScheduleService scheduleService;
	
	@Autowired
	private IOrganService organService;
	public static void main(String[] args) {
		long dayNum = 0l;
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		Date date = new Date();
		Date last_term_start = DateUtil.parseYmd("" + (year - 1) + "-09-01");
		Date last_term_end = DateUtil.parseYmd("" + (year) + "-01-21");

		Date next_term_start = DateUtil.parseYmd("" + (year) + "-02-20");
		Date next_term_end = DateUtil.parseYmd("" + (year) + "-06-31");

		if (date.getTime() > last_term_start.getTime() && date.getTime() < last_term_end.getTime()) {
			dayNum = (date.getTime() - last_term_start.getTime()) / 1000 / 60 / 60 / 24  + 1;
		} else if (date.getTime() > next_term_start.getTime() && date.getTime() < next_term_end.getTime()) {
			dayNum = (date.getTime() - next_term_start.getTime()) / 1000 / 60 / 60 / 24 + 1;
		} else
			dayNum = 138;
	}
	private long findDaysAfterTerm(){
		long dayNum = 0l;
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		Date date = new Date();
		Date last_term_start = DateUtil.parseYmd("" + (year - 1) + "-09-01");
		Date last_term_end = DateUtil.parseYmd("" + (year) + "-01-31");

		Date next_term_start = DateUtil.parseYmd("" + (year) + "-03-04");
		Date next_term_end = DateUtil.parseYmd("" + (year) + "-07-10");

		if (date.getTime() > last_term_start.getTime() && date.getTime() < last_term_end.getTime()) {
			// Date dt = DateUtil.parseYmd(""+(year-1)+"-09-01");
			dayNum = (date.getTime() - last_term_start.getTime()) / 1000 / 60 / 60 / 24  + 1;
		} else if (date.getTime() > next_term_start.getTime() && date.getTime() < next_term_end.getTime()) {
			// Date dt = DateUtil.parseYmd(""+year+"-02-20");
			dayNum = (date.getTime() - next_term_start.getTime()) / 1000 / 60 / 60 / 24 + 1;
		} else
			dayNum = 138;
		return dayNum;
	}
	
	@RequestMapping("/firstPart")
	@ResponseBody
	public WebStatus4P firstPart(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY)SysUser user,PageInfo pageInfo) throws Exception{
		int uid = user.getOid();
		String school = "";
		String teachernum = "";
		String roomnum = "";
		String testnum = "";
		String schdulenum = "";
		List<Map<String, Object>> ldetail = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> lrank = new ArrayList<Map<String, Object>>();

		int labNumTotal = organService.findMyLibCount(user, 6, pageInfo);
		int grounpCourseTimeTotal = scheduleService.findGroupCount(user, pageInfo);
		Map<String, Object> mp0 = new HashMap<String, Object>();
		Map<String, Object> mp1 = new HashMap<String, Object>();
		mp1.put("labNumTotal", labNumTotal);
		mp1.put("grounpCourseTimeTotal", grounpCourseTimeTotal);
		long day = findDaysAfterTerm();
		DateUtil.getYm();
		int apptCount = apptRcdService.findApptCount(user,1);
		int appDay = 0;
		List<Map<String,Object>> apptDay = apptRcdService.findApptDay(1, pageInfo);
		System.out.println(apptDay.toString()+".......................................appt");
		for (Map<String, Object> mapd : apptDay) {
			if(null!=mapd.get("count").toString()){
				appDay = Integer.parseInt(mapd.get("count").toString());
				mapd.put("count", new BigDecimal((float)appDay/(day*8)).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue()*100);
			}
		}
		if(labNumTotal*day!=0)
			mp1.put("thisTerm", new BigDecimal((float)apptCount/(labNumTotal*day*8)).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue()*100);
		else
			mp1.put("thisTerm", 0);
		if(grounpCourseTimeTotal!=0)
			mp1.put("courseFinish", new BigDecimal((float)apptCount/grounpCourseTimeTotal).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue()*100);
		else 
			mp1.put("courseFinish", 0);
		mp1.put("thisMonth", apptDay);
		mp0.put("totalNum", mp1);
		System.out.println("After appt **************************************userType"+user.getUserType());
		//区管理员
		if(user.getUserType()==2){
			List<Organization> lorgan = organService.findByUserAndType(uid, 3, pageInfo);
			//教师总数
			List<Map<String, Object>> lteacher = organService.findTeacherNum(user, 5, pageInfo);
			Map<String, Object> mpcher = new HashMap<String, Object>();
			System.out.println(lteacher.toString()+".....................................tcher");
			for (Map<String, Object> map : lteacher) {
				school = map.get("school").toString();
				teachernum = map.get("teachernum").toString();
				mpcher.put(school, teachernum);
			}
			//实验室
			List<Map<String, Object>> llabroom = organService.findTypeCount(user, 6, pageInfo);
			Map<String, Object> mproom = new HashMap<String, Object>();
			for (Map<String, Object> map : llabroom) {
				school = map.get("school").toString();
				roomnum = map.get("roomnum").toString();
				mproom.put(school, roomnum);
			}
			//班级数
			List<Map<String, Object>> lclazz = organService.findTypeCount(user, 6, pageInfo);
			Map<String, Object> mpclazz = new HashMap<String, Object>();
			for (Map<String, Object> map : lclazz) {
				school = map.get("school").toString();
				roomnum = map.get("roomnum").toString();
				mpclazz.put(school, roomnum);
			}
			//分组实验组
			List<Map<String, Object>> lstudygroup = scheduleService.findGroup(user, pageInfo);
			Map<String, Object> mpgroup = new HashMap<String, Object>();
	
			for (Map<String, Object> map : lstudygroup) {
				school = map.get("school").toString();
				testnum = map.get("testnum").toString();
				mpgroup.put(school, testnum);
			}
			//已开分组实验
			List<Map<String, Object>> lfstudygroup = apptRcdService.findFGroup(user,pageInfo);
			Map<String, Object> mpfgroup = new HashMap<String, Object>();
	
			for (Map<String, Object> map : lfstudygroup) {
				school = map.get("school").toString();
				schdulenum = map.get("schdulenum").toString();
				mpfgroup.put(school, schdulenum);
			}
			for (Organization org : lorgan) {
				Map<String, Object> mpch = new HashMap<String, Object>();		
				if(mpcher!=null && null!=mpcher.get(org.getOrganName())){
					mpch.put("teacherTotal", mpcher.get(org.getOrganName()).toString());
				}else
					mpch.put("teacherTotal", "");
				if(mpclazz!=null && null!=mpclazz.get(org.getOrganName())){
					mpch.put("clazzTotal", mpclazz.get(org.getOrganName()).toString());
				}else
					mpch.put("clazzTotal", "");
				if(mproom!=null && null!=mproom.get(org.getOrganName()))
					mpch.put("labTotal", mproom.get(org.getOrganName()).toString());
				else{
					mpch.put("labTotal", "");
					mpch.put("userate", 0);
				}
				if(mpfgroup!=null && null!=mpfgroup.get(org.getOrganName()))
					mpch.put("finishCourse", mpfgroup.get(org.getOrganName()).toString());
				else{
					mpch.put("finishCourse", "");
					mpch.put("userate", 0);
				}
				if(mpgroup!=null && null!=mpgroup.get(org.getOrganName()))
					mpch.put("grounpCourseTotal", mpgroup.get(org.getOrganName()).toString());
				else
					mpch.put("grounpCourseTotal", "");
				mpch.put("school", org.getOrganName());
				if(mpfgroup!=null && mproom!=null && null!=mpfgroup.get(org.getOrganName()) && null!=mpgroup.get(org.getOrganName())){
					int mtotal = Integer.valueOf(mpgroup.get(org.getOrganName()).toString());
					int mfinish = Integer.valueOf(mpfgroup.get(org.getOrganName()).toString());
					mpch.put("userate",new BigDecimal((float)mfinish/mtotal).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue()*100);
				}
				lrank.add(mpch);
			}
			mp0.put("rankdata", lrank);
			ldetail.add(mp0);
		//学校管理员
		}else if(user.getUserType()==4){
			Map<String, Object> mschcourse = new HashMap<String, Object>(); 
			Map<String, Object> mappcourse = new HashMap<String, Object>(); 
			mschcourse = scheduleService.findByClassCourse(user,pageInfo);
			mappcourse = apptRcdService.findByClassCourse(user,pageInfo);
			for ( Entry<String, Object> entry : mschcourse.entrySet()) {
				String key = entry.getKey();
				Map<String, Object> mps = (HashMap)entry.getValue();
				for ( Entry<String, Object> ent : mappcourse.entrySet()) {
					if(ent.getKey().equals(key)){
						Map<String, Object> mp = (HashMap)ent.getValue();
						String apptnum = mp.get("apptnum").toString();
						mps.put("apptnum", apptnum);
						entry.setValue(mps);
					}else{
						mps.put("apptnum", 0);
						entry.setValue(mps);
					}
						
				}
				lrank.add((HashMap)entry.getValue());
			}
			mp0.put("rankdata", lrank);
			ldetail.add(mp0);
		}

		return new WebStatus4P(ldetail);
	}
	@RequestMapping("/firstWindow")
	@ResponseBody
	public WebStatus4P firstWindow(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY)SysUser user,PageInfo pageInfo) throws Exception{
		List<Map<String, Object>> llabtotal = organService.findMyLibGroup(user, 6, pageInfo);
		return new WebStatus4P(llabtotal);
	}
	@RequestMapping("/find/ID/{oid}")
	public String findApptRcd(@PathVariable("oid") int oid) throws Exception{
		ApptRecord apt = apptRcdService.findOne(oid);
		return "/aptrcd";
		//		return new WebStatus(apt);		
	}
	@RequestMapping("/findLabMan")
	public WebStatus4P findLabMan(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY)SysUser user) throws Exception{
		List<SysUser> lusr = apptRcdService.findLabMan(user);
		return new WebStatus4P(lusr);
	}
	@RequestMapping("/apply")
	@ResponseBody
	public WebStatus applyApptRcd(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY)SysUser user,ApptRecord apt) throws Exception{
		apptRcdService.saveAppt(user, apt);
		return new WebStatus().success();
		//		return new WebStatus(apt);
	}
	//申请任务中心	
	@RequestMapping("/findAppt")
	@ResponseBody
	public WebStatus4P findApptList(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY)SysUser user,ApptRcdQuery query,PageInfo pageInfo){
		try {
			GridListData gld = new GridListData();
			pageInfo.setSort("checkStat desc,");
			pageInfo.setDir("checkComt asc");
			int areaid = user.getAreaid();
			//老師
			if(user.getUserType()==5){
				//查找自己未预约的
				List<Schedule> list = scheduleService.findNextWeek(user);
				if(list==null){
				//加入动态课表
				apptRcdService.insertAppt(list);
				}
				query.setTeacher(user.getUserNick());
			//区管理员
			}else if(user.getUserType()==2){
			//学校管理员,显示本校所有已预约的
				query.setCheckStat(1);
			}else if(user.getUserType()==4){
				Organization school = organService.findByID(areaid);
				query.setSchoolID(school.getOid());
				query.setCheckStat(1);
			}//实验员
			else if(user.getUserType()==7){
				Organization school = organService.findByID(areaid);
				query.setSchoolID(school.getOid());
				query.setCheckStat(1);
			}
//			pageInfo.setLimit(100);
			gld = apptRcdService.findApptList(query,pageInfo);
			return new WebStatus4P(gld);
		} catch (Exception e) {
			e.printStackTrace();
			return new WebStatus4P().failure();
		}
	
	}

	public WebStatus4P findCheckList(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY)SysUser user,ApptRcdQuery query,PageInfo pageInfo){
		GridListData apt;
		try {
			apt = apptRcdService.findRcdList(user,query, pageInfo);
			return new WebStatus4P(apt);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new WebStatus4P().failure();
		}
	
	}	
	@RequestMapping("/check")
	@ResponseBody
	public WebStatus checkApptRcd(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY)SysUser user,ApptRecord apt) throws Exception{
		apptRcdService.saveAppt(user, apt);
		return new WebStatus().success();
	}
	
	@RequestMapping(value="/find",produces ={"application/json;chrset=UTF-8" })
	@ResponseBody	
	public WebStatus4P findApptByName(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY)SysUser user,ApptRcdQuery query,PageInfo pageInfo) throws Exception{
		GridListData apt = apptRcdService.findRcdList(user,query, pageInfo);
		return new WebStatus4P(apt);		
	}
	
	@RequestMapping(value="/findMy",produces ={"application/json;chrset=UTF-8" })
	@ResponseBody	
	public WebStatus4P findMyAppt(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY)SysUser user,ApptRcdQuery query,PageInfo pageInfo) throws Exception{
		GridListData apt = apptRcdService.findMyAppt(user,query, pageInfo);
		return new WebStatus4P(apt);		
	}	
	
}
