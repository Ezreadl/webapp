package com.qm.nms.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.qm.core.base.GlobalCfg;
import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.core.base.SystemConfig;
import com.qm.core.base.WebStatus;
import com.qm.core.base.WebStatus4P;
import com.qm.core.util.DateUtil;
import com.qm.core.util.ExcelFileOprator;
import com.qm.core.util.FileUtil;
import com.qm.core.util.StringUtil;
import com.qm.core.util.TextFileUtil;
import com.qm.core.util.UUIDUtil;
import com.qm.nms.domain.Schedule;
import com.qm.nms.query.ApptRcdQuery;
import com.qm.nms.query.ScheduleQuery;
import com.qm.nms.service.IScheduleService;
import com.qm.sys.domain.SysUser;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	private IScheduleService scheduleService;

	@RequestMapping("/findAll")
	@ResponseBody
	public Map<String, Object> findAllSchedule(ScheduleQuery query, PageInfo pageinfo) throws Exception {
		return scheduleService.findAllSchedule(query, pageinfo);
	}

	@RequestMapping("/menu")
	@ResponseBody
	public Map<String, Object> groupScheduleByCourse(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser user)
			throws Exception {
		return scheduleService.groupScheduleByCourse(user);
	}

	@RequestMapping("/screen")
	@ResponseBody
	public WebStatus4P findScheduleNow(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser user)
			throws Exception {
		List<Map<String, Object>> lsch = scheduleService.findScheduleNow(user.getOid());
		return new WebStatus4P(lsch);
	}
	
	@RequestMapping("/clickOne")
	@ResponseBody
	public WebStatus4P findOneAppt(ApptRcdQuery query,PageInfo pageInfo)throws Exception {
		GridListData grid = scheduleService.findOneAppt(query, pageInfo);
		return new WebStatus4P(grid);
	}
	// 我上的班級的课表
	@RequestMapping("/findMy")
	@ResponseBody
	public WebStatus4P findMySchedule(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser user,
			ScheduleQuery query, PageInfo pageinfo) throws Exception {
		return scheduleService.findMySchedule(user, query, pageinfo);
	}

	// @RequestMapping("/findMyClass")
	// @ResponseBody
	// public WebStatus4P
	// findMyClass(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY)SysUser
	// user,ScheduleQuery query,PageInfo pageinfo) throws Exception{
	// return scheduleService.findMySchedule(user,query,pageinfo);
	// }
	// 查找我上课的班级
	@RequestMapping("/findMyClass")
	@ResponseBody
	public WebStatus4P findLabClass(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser user,
			PageInfo pageInfo) throws Exception {
		pageInfo.setLimit(10);
		List<Map<String, Object>> list = scheduleService.findMyClass(user, pageInfo);
		return new WebStatus4P(list);
	}

	@RequestMapping("/findMySchool")
	@ResponseBody
	public WebStatus4P findSchoolSchedule(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser user,
			ScheduleQuery query, PageInfo pageinfo) throws Exception {
		return scheduleService.findSchoolSchedule(user, query, pageinfo);
	}

	//分组实验课
	@RequestMapping("/findGroup")
	@ResponseBody
	public WebStatus4P findGroupNum(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser user,
			ScheduleQuery query, PageInfo pageinfo) throws Exception {
		List<Map<String, Object>> gld = scheduleService.findGroup(user,pageinfo);
		return new WebStatus4P(gld);
	}

	@RequestMapping("/save")
	@ResponseBody
	public WebStatus saveSchedule(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser user, Schedule scd)
			throws Exception {
		try {
			scheduleService.saveSchedule(user, scd);
		} catch (Exception e) {
			return new WebStatus(e);
		}
		return new WebStatus().success();

	}

	@RequestMapping("/delete")
	@ResponseBody
	public WebStatus delSchedule(SysUser user, Schedule scd) throws Exception {
		scheduleService.delSchedule(user, scd);
		return new WebStatus().success();
	}

	@RequestMapping("/time")
	@ResponseBody
	public Map<String, Object> findTime(String rtime) throws Exception {
		String msg = "成功";
		Calendar cal = Calendar.getInstance();
		Map<String, Object> mp = new HashMap<String, Object>();
		int year = cal.get(Calendar.YEAR);
		Date dt = DateUtil.parseYmd("" + (year - 1) + "-09-01");
		// 上学期
		String weekNum = "";
		String dayNum = "";
		int month = cal.get(Calendar.MONTH) + 1;
		Date date = DateUtil.parseYmd(rtime);
		Date last_term_start = DateUtil.parseYmd("" + (year - 1) + "-09-01");
		Date last_term_end = DateUtil.parseYmd("" + (year) + "-01-21");

		Date next_term_start = DateUtil.parseYmd("" + (year) + "-02-20");
		Date next_term_end = DateUtil.parseYmd("" + (year) + "-06-31");

		if (date.getTime() > last_term_start.getTime() && date.getTime() < last_term_end.getTime()) {
			weekNum = (date.getTime() - last_term_start.getTime()) / 1000 / 60 / 60 / 24 / 7 + 1 + "";
		} else if (date.getTime() > next_term_start.getTime() && date.getTime() < next_term_end.getTime()) {
			weekNum = (date.getTime() - next_term_start.getTime()) / 1000 / 60 / 60 / 24 / 7 + 1 + "";
		} else {
			weekNum = "";
			msg = "请选择当前本学年的时间";
		}
		dayNum = DateUtil.getTodayWeekDayNum(rtime);
		mp.put("rtime", rtime);
		mp.put("weekNum", weekNum);
		mp.put("dayNum", dayNum);
		mp.put("msg", msg);
		return mp;
	}

	/**
	 * 上传课表保存到数据库
	 * 
	 * @param upfile
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/fileUpload")
	public String fileUpload(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser user, @RequestParam(value = "upfile") MultipartFile upfile,
			HttpServletResponse response) throws Exception {
		try {
			String fileName = upfile.getName();
			System.out.println(fileName);
			int[] nu = { 0, 1, 2, 3, 4, 5, 6 };
			String path = SystemConfig.getPath("");// 文件存放///////////
			File targetFile = new File(path, "up" + UUIDUtil.getTimeId() + "."
					+ FileUtil.getFileNameNoSuffix(upfile.getOriginalFilename()) + ".xlsx");
			upfile.transferTo(targetFile);
			if (upfile.getOriginalFilename().toLowerCase().endsWith(".txt")) {
				List<String> lf = TextFileUtil.readData(targetFile, "gbk");
				for (String string : lf) {
					System.out.println(string);
				}
			} else if (upfile.getOriginalFilename().toLowerCase().endsWith(".xls")
					|| upfile.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
				ExcelFileOprator excel = new ExcelFileOprator(targetFile);
				List<String[]> list = excel.readData(0, 1, nu);
				List<StringBuilder> lsb = new ArrayList<StringBuilder>();
				for (String[] str : list) {
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < str.length; i++) {
						if (StringUtil.isNotBlank(str[i]))
							sb.append(str[i] + ",");
					}
					// System.out.println(sb.toString());
					int num = sb.toString().split(",").length;
					// System.out.println(sb.toString().split(",").length);
					StringBuilder sb0 = new StringBuilder();
					for (int i = 0; i < num / 3; i++) {
						StringBuilder sb1 = new StringBuilder();
						StringBuilder sb2 = new StringBuilder();
						StringBuilder sb3 = new StringBuilder();
						StringBuilder sb4 = new StringBuilder();
						StringBuilder sb5 = new StringBuilder();
						if (i == 1) {
							sb1 = sb1.append(sb0+",");
							sb1 = sb1.append("'"+sb.toString().split(",")[i * 3]+"'").append(","+"'"+sb.toString().split(",")[i * 3 + 1]+"'")
									.append(","+"'"+sb.toString().split(",")[i * 3 + 2]+"'");
							lsb.add(sb1);
//							System.out.println(sb1.toString());
						}
						if (i == 2) {
							sb2 = sb2.append(sb0+",");
							sb2 = sb2.append("'"+sb.toString().split(",")[i * 3]+"'").append(","+"'"+sb.toString().split(",")[i * 3 + 1]+"'")
									.append(","+"'"+sb.toString().split(",")[i * 3 + 2]+"'");
							lsb.add(sb2);
//							System.out.println(sb2.toString());
						}
						if (i == 3) {
							sb3 = sb3.append(sb0+",");
							sb3 = sb3.append("'"+sb.toString().split(",")[i * 3]+"'").append(","+"'"+sb.toString().split(",")[i * 3 + 1]+"'")
									.append(","+"'"+sb.toString().split(",")[i * 3 + 2]+"'");
							lsb.add(sb3);
//							System.out.println(sb3.toString());
						}
						if (i == 4) {
							sb4 = sb4.append(sb0+",");
							sb4 = sb4.append("'"+sb.toString().split(",")[i * 3]+"'").append(","+"'"+sb.toString().split(",")[i * 3 + 1]+"'")
									.append(","+"'"+sb.toString().split(",")[i * 3 + 2]+"'");
							lsb.add(sb4);
//							System.out.println(sb4.toString());
						}
						if (i == 5) {
							sb5 = sb5.append(sb0+",");
							sb5 = sb5.append("'"+sb.toString().split(",")[i * 3]+"'").append(","+"'"+sb.toString().split(",")[i * 3 + 1])
									.append(","+"'"+sb.toString().split(",")[i * 3 + 2]+"'");
							lsb.add(sb5);
//							System.out.println(sb2.toString());
						}
						if (i == 0) {
							sb0 = new StringBuilder();
							sb0 = sb0.append("'"+sb.toString().split(",")[i * 3]+"'")
									.append("," +"'"+ sb.toString().split(",")[i * 3 + 1]+"'")
									.append("," +"'"+ sb.toString().split(",")[i * 3 + 2]+"'");
						}
					}
				}
				scheduleService.insertSchedule(user,lsb);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
