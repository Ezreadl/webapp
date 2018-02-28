package com.qm.nms.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.core.base.WebStatus4P;
import com.qm.core.util.DateUtil;
import com.qm.nms.dao.IOrganDao;
import com.qm.nms.dao.IScheduleDao;
import com.qm.nms.dao.IVideoSiteDao;
import com.qm.nms.domain.Organization;
import com.qm.nms.domain.Schedule;
import com.qm.nms.domain.VideoSite;
import com.qm.nms.query.ApptRcdQuery;
import com.qm.nms.query.ScheduleQuery;
import com.qm.nms.service.IScheduleService;
import com.qm.sys.domain.SysUser;

@Service
public class ScheduleServiceImpl implements IScheduleService {

	@Autowired
	private IScheduleDao scheduleDao;

	@Autowired
	public IOrganDao organDao;

	@Autowired
	public IVideoSiteDao videoDao;

	public Map<String, Object> groupScheduleByCourse(SysUser user) throws Exception {
		List<Schedule> schduleList = scheduleDao.findByUserID(user.getUserNick());
		Map<String, Object> schMap = new HashMap<String, Object>();

		String name = "";
		List<String> items = new ArrayList<String>();
		// "name":
		// "items":
		try {
			for (Schedule scd : schduleList) {
				name = scd.getCourse();
				items.add(scd.getTeacher());
			}
			schMap.put(name, items);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return schMap;
	}

	/**
	 * 对list进行分组
	 * 
	 * @param schduleList
	 * @return
	 * @throws Exception
	 */
	private Map<String, List<Map<String, Object>>> groupScheduleBySchool(List<Map<String, Object>> schduleList)
			throws Exception {
		Map<String, List<Map<String, Object>>> resultMap = new HashMap<String, List<Map<String, Object>>>();
		try {
			for (Map<String, Object> scd : schduleList) {
				if (resultMap.containsKey(scd.get("school"))) {
					// map中学校已存在，将该数据存放到同一个key（key存放的是学校）的map中
					resultMap.get(scd.get("school")).add(scd);
				} else {// map中不存在，新建key，用来存放数据
					List<Map<String, Object>> tmpList = new ArrayList<Map<String, Object>>();
					tmpList.add(scd);
					resultMap.put(scd.get("school").toString(), tmpList);
				}
			}
			// for (Entry<String, List<Map<String, Object>>> entr :
			// resultMap.entrySet()) {
			// System.out.println(entr.getKey());
			// System.out.println(entr.getValue());
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultMap;
	}

	public int getWeekNum(int month) {
		if (month > 9 && month < 3) {
			// DateUtil.
		} else {

		}
		return month;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(DateUtil.getFirstDayOfThisWeek(new Date()));
		System.out.println(DateUtil.getWeeksAgo(new Date(), 1));
		Date dateWeek = DateUtil.getWeeksAgo(new Date(), -1);
		System.out.println(DateUtil.getYmd(dateWeek)+"....");
		// Calendar cal = Calendar.getInstance();
		// Map<String, Object> mp = new HashMap<String, Object>();
		// int year = cal.get(Calendar.YEAR);
		// Date dt = DateUtil.parseYmd("" + (year - 1) + "-09-01");
		// String classYear = "";
		// String term = "";
		// // 上学期
		// String weekNum = "";
		// String dayNum = "";
		// int month = cal.get(Calendar.MONTH) + 1;
		// Date date = new Date();
		// Date last_term_start = DateUtil.parseYmd("" + (year - 1) + "-09-01");
		// Date last_term_end = DateUtil.parseYmd("" + (year) + "-01-21");
		//
		// Date next_term_start = DateUtil.parseYmd("" + (year) + "-02-20");
		// Date next_term_end = DateUtil.parseYmd("" + (year) + "-06-31");
		//
		// if (date.getTime() > last_term_start.getTime() && date.getTime() <
		// last_term_end.getTime()) {
		// classYear = year + 1 + "年";
		// term = "上学期";
		// } else if (date.getTime() > next_term_start.getTime() &&
		// date.getTime() < next_term_end.getTime()) {
		// classYear = year + "年";
		// term = "下学期";
		// } else {
		// classYear = "放假了";
		// }
		// System.out.println(classYear + term);
	}

	/**
	 * 巡查屏幕
	 */
	@Override
	public List<Map<String, Object>> findScheduleNow(int uid) throws Exception {
		// 第几周
		String weekNum = "";
		// 星期
		String weekDay = "";
		// 第几节课
		String lessonNo = "";
		Object[] params = new Object[] { weekNum, weekDay, lessonNo };
		PageInfo page = new PageInfo();
		page.setLimit(9);
		List<Map<String, Object>> la = new ArrayList<Map<String, Object>>();
		List<Organization> lorgan = organDao.findByUserID(uid);
		Organization region = lorgan.get(0);
		StringBuilder videoUrl = new StringBuilder();
		// 区县
		// String sql = "select dev_ip,dev_no,audio_enable from sn_device where
		// dev_name=?";
		// List<Map<String, Object>> lregion =
		// DBConnectionPool.executeQuery(sql, new Object[] {
		// region.getOrganName() });
		String schoolUrl = "";
		String regionUrl = "";
		String labUrl = "";
		// if (lregion.size() > 0) {
		// regionUrl = "rtsp://" + lregion.get(0).get("dev_ip") + ":554/";
		// } else {
		// regionUrl = "rtsp://" + region.getLocationUrl() + ":554/";
		// }
		regionUrl = "rtsp://" + region.getLocationUrl() + ":554/";
		videoUrl.append(regionUrl);
		// 学校
		List<Organization> lschool = organDao.findSonByPreID(region.getOid(), 3);
		for (Organization school : lschool) {
			// List<Map<String, Object>> lsch =
			// DBConnectionPool.executeQuery(sql, new Object[] {
			// school.getOrganName() });
			// schoolUrl = lsch.get(0).get("dev_ip") + ":554/";
			schoolUrl = school.getLocationUrl() + ":554/";
			videoUrl.append(schoolUrl);
			List<Organization> llab = organDao.findSonByPreID(school.getOid(), 6);
			for (Organization lab : llab) {
				List<Map<String, Object>> lvd = new ArrayList<Map<String, Object>>();
				// 实验室
				// List<Map<String, Object>> lvdio =
				// DBConnectionPool.executeQuery(sql,
				// new Object[] { reg.getOrganName() });
				List<VideoSite> lvdio = videoDao.findByOrgan(lab.getOid());

				StringBuilder videoName = new StringBuilder();
				videoName.append(videoUrl);
				for (VideoSite videoSite : lvdio) {
					Map<String, Object> mp = new HashMap<String, Object>();
					videoName.append("ch" + videoSite.getChanNo() + "/0");
					mp.put("chanNo", videoName.toString());
					mp.put("channel", videoSite.getChannel());
					lvd.add(mp);
				}
				Map<String, Object> mp = new HashMap<String, Object>();
				mp.put("videoUrl", lvd);
				mp.put("organType", lab.getOrganType());
				mp.put("organName", lab.getOrganName());
				la.add(mp);
				videoUrl = new StringBuilder();
				videoUrl.append(regionUrl);
			}
		}
		return la;
	}

	@SuppressWarnings("unchecked")
	@Override
	public WebStatus4P findMySchedule(SysUser user, ScheduleQuery query, PageInfo pageinfo) throws Exception {
		query.setTeacher(user.getUserNick());
		List<Map<String, Object>> schedules = scheduleDao.findDataList1(query, pageinfo).getRows();
		for (Map<String, Object> map : schedules) {
			if ("0".equals(map.get("checkStat").toString())) {
				map.put("status", "未预约");
			} else if ("1".equals(map.get("checkStat").toString())) {
				map.put("status", "预约成功");
			} else if ("2".equals(map.get("checkStat").toString())) {
				map.put("status", "预约失败");
			} else if ("3".equals(map.get("checkStat").toString())) {
				map.put("status", "回看");
			} else if ("4".equals(map.get("checkStat").toString())) {
				map.put("status", "审批中");
			}
			map.put("className", map.get("Grade").toString() + "(" + map.get("Clazz").toString() + ")");
		}
		return new WebStatus4P(schedules).success();
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findMyClass(SysUser user, PageInfo pageInfo) throws Exception {
		List<Map<String, Object>> list = scheduleDao.findDataList4(new Object[] { user.getOid() }, pageInfo);
		return list;
	}

	public int findGroupCount(SysUser user, PageInfo pageInfo) throws Exception {
		int areaid = user.getAreaid();
		int count = 0;
		if (user.getUserType() == 2) {
			count = scheduleDao.findGroupCount();
		} else if (user.getUserType() == 4) {
			count = scheduleDao.findSchoolGroupCount(areaid);
		}
		return count;

	}

	public Map<String, Object> findByClassCourse(SysUser user, PageInfo pageInfo) throws Exception {
		int schoolid = user.getAreaid();
		List<Map<String, Object>> list = scheduleDao.findDataList5(new Integer[] { schoolid }, pageInfo);
		Map<String, Object> mp = new HashMap<String, Object>();
		for (Map<String, Object> map : list) {
			mp.put(map.get("Clazz").toString() + map.get("course").toString() + map.get("teacher").toString(), map);
		}
		return mp;
	}

	// 分组实验课
	public List<Map<String, Object>> findGroup(SysUser user, PageInfo pageInfo) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 学校查school
		// 区县查region
		int orgid = user.getAreaid();
		list = scheduleDao.findDataList1(new Integer[] {}, pageInfo);
		return list;
	}

	@Override
	public WebStatus4P findSchoolSchedule(SysUser user, ScheduleQuery query, PageInfo pageinfo) throws Exception {
		// query.setTeacherID(user.getOid());
		pageinfo.setSort("weekNo");
		pageinfo.setDir("desc");
		List<Map<String, Object>> schedules = scheduleDao.findDataList1(query, pageinfo).getRows();
		for (Map<String, Object> map : schedules) {
			if ("0".equals(map.get("checkStat").toString())) {
				map.put("status", "未预约");
			} else if ("1".equals(map.get("checkStat").toString())) {
				map.put("status", "预约成功");
			} else if ("2".equals(map.get("checkStat").toString())) {
				map.put("status", "预约失败");
			} else if ("3".equals(map.get("checkStat").toString())) {
				map.put("status", "回看");
			} else if ("4".equals(map.get("checkStat").toString())) {
				map.put("status", "审批中");
			}
			String picUrl = "";
			List<String> lpic = new ArrayList<String>();
			if (null != map.get("picUrl")) {
				picUrl = map.get("picUrl").toString();
				String[] pic = picUrl.split(",");
				for (int i = 0; i < pic.length; i++) {
					lpic.add(pic[i]);
				}
			}
			map.put("appurl", "rtsp://125.68.76.148:504/192.160.1.101:504/move.mvo");
			map.put("picture", lpic);
			map.put("className", map.get("Grade").toString() + "(" + map.get("Clazz").toString() + ")");
		}
		return new WebStatus4P(schedules).success();
	}
	//点课
	public GridListData findOneAppt(ApptRcdQuery query,PageInfo pageInfo) throws Exception{
		GridListData gld = scheduleDao.findDataList2(query, pageInfo);
		return gld;
	}
	//巡课
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findAllSchedule(ScheduleQuery query, PageInfo pageinfo) throws Exception {
		int indexNum = 0;
		Date dateWeek = new Date();
		if (null != query.getIndexNum())
			indexNum = query.getIndexNum();
		long weekNum = 0l;
		pageinfo.setLimit(10000);
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		Date date = new Date();
		Date last_term_start = DateUtil.parseYmd("" + (year - 1) + "-09-01");
		Date last_term_end = DateUtil.parseYmd("" + (year) + "-01-21");

		Date next_term_start = DateUtil.parseYmd("" + (year) + "-02-20");
		Date next_term_end = DateUtil.parseYmd("" + (year) + "-06-31");

		if (date.getTime() > last_term_start.getTime() && date.getTime() < last_term_end.getTime()) {
			// Date dt = DateUtil.parseYmd(""+(year-1)+"-09-01");
			weekNum = (date.getTime() - last_term_start.getTime()) / 1000 / 60 / 60 / 24 / 7 + 1;
			System.out.println((date.getTime() - last_term_start.getTime()) / 1000 / 60 / 60 / 24 / 7 + 1);
			query.setTerm(0);
		} else if (date.getTime() > next_term_start.getTime() && date.getTime() < next_term_end.getTime()) {
			// Date dt = DateUtil.parseYmd(""+year+"-02-20");
			weekNum = (date.getTime() - next_term_start.getTime()) / 1000 / 60 / 60 / 24 / 7 + 1;
			System.out.println((date.getTime() - next_term_start.getTime()) / 1000 / 60 / 60 / 24 / 7 + 1);
			query.setTerm(1);
		} else if (date.getTime() > last_term_end.getTime() && date.getTime() < next_term_start.getTime()) {
			// Date dt = DateUtil.parseYmd(""+(year-1)+"-09-01");
			weekNum = (date.getTime() - last_term_start.getTime()) / 1000 / 60 / 60 / 24 / 7 + 1;
			System.out.println((date.getTime() - last_term_start.getTime()) / 1000 / 60 / 60 / 24 / 7 + 1);
			query.setTerm(0);
		} else {
			weekNum = (date.getTime() - next_term_start.getTime()) / 1000 / 60 / 60 / 24 / 7 + 1;
			System.out.println((date.getTime() - next_term_start.getTime()) / 1000 / 60 / 60 / 24 / 7 + 1);
			query.setTerm(1);
		}
		query.setWeekNo("第"+(weekNum + indexNum)+"周");
		dateWeek = DateUtil.getWeeksAgo(new Date(), (int)indexNum*-1);
		String startDate = DateUtil.getFirstDayOfThisWeek(dateWeek);
		String endDate = DateUtil.getLastDayOfThisWeek(dateWeek);
		StringBuilder startDay = new StringBuilder();
		StringBuilder endDay = new StringBuilder();
		startDay.append(startDate.split("-")[1]+"/");
		startDay.append(startDate.split("-")[2]);
		endDay.append(endDate.split("-")[1]+"/");
		endDay.append(endDate.split("-")[2]);
		List<Map<String, Object>> schedules = scheduleDao.findDataList1(query, pageinfo).getRows();
//		for (Map<String, Object> map : schedules) {
//			System.out.println(map.get("lessonstat"));
//			if(map.get("oid")==null){
//				map.put("lessonstat", 0);
//			}
//		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> datas0 = new ArrayList<Map<String, Object>>();
		Map<String, List<Map<String, Object>>> map0 = groupScheduleBySchool(schedules);
		for (Entry<String, List<Map<String, Object>>> entry : map0.entrySet()) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("schoolName", entry.getKey());
			map1.put("data", returnData(entry.getValue(), datas0));
			// System.out.println("schoolName:" + entry.getKey());
			// System.out.println("data:" + entry.getValue().toString());
			// System.out.println("*********************************");
			datas.add(map1);
		}
		// for (Map<String, Object> map2 : datas) {
		// for (Entry<String, Object> entr : map2.entrySet()) {
		// System.out.println(entr.getKey());
		// System.out.println(entr.getValue());
		// System.out.println("-----------------------------");
		// }
		// }
		map.put("code", 0);
		map.put("msg", "第"+(weekNum + indexNum)+"周("+startDay.toString()+"~"+endDay.toString()+")");
		map.put("count", datas.size());
		map.put("data", datas);
		return map;
	}

	private List<Map<String, Object>> returnData(List<Map<String, Object>> schedules, List<Map<String, Object>> datas) {
		datas = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> count1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> count2 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> count3 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> count4 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> count5 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> count6 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> count7 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> count8 = new ArrayList<Map<String, Object>>();
		count1 = returnValue(schedules, count1, "lessonNo", "第1节");
		datas.add(returnMap(count1, "第1节"));
		count2 = returnValue(schedules, count2, "lessonNo", "第2节");
		datas.add(returnMap(count2, "第2节"));
		count3 = returnValue(schedules, count3, "lessonNo", "第3节");
		datas.add(returnMap(count3, "第3节"));
		count4 = returnValue(schedules, count4, "lessonNo", "第4节");
		datas.add(returnMap(count4, "第4节"));
		count5 = returnValue(schedules, count5, "lessonNo", "第5节");
		datas.add(returnMap(count5, "第5节"));
		count6 = returnValue(schedules, count6, "lessonNo", "第6节");
		datas.add(returnMap(count6, "第6节"));
		count7 = returnValue(schedules, count7, "lessonNo", "第7节");
		datas.add(returnMap(count7, "第7节"));
		count8 = returnValue(schedules, count8, "lessonNo", "第8节");
		datas.add(returnMap(count8, "第8节"));
		return datas;
	}

	private Map<String, Object> returnMap(List<Map<String, Object>> returnValue, String lessonNo) {
		Map<String, Object> map = new HashMap<>();
		String labroom = "";
		String content = "";
		String lessonstat = "0";
		String cdate = "";
		map.put("dayNo", "");
		map.put("week1", "");
		map.put("week2", "");
		map.put("week3", "");
		map.put("week4", "");
		map.put("week5", "");
		if (returnValue != null) {
			if (returnValue.size() == 1) {
				// List<Map<String, Object>> list = new ArrayList<Map<String,
				// Object>>();
				Map<String, Object> num = new HashMap<>();
				List<String> lv = new ArrayList<String>();
				Map<String, Object> schedule = returnValue.get(0);

				if (null != schedule.get("labroom")) {
					labroom = schedule.get("labroom").toString();
				}
				if (null != schedule.get("content")) {
					content = schedule.get("content").toString();
				}
				if (null != schedule.get("lessonstat")) {
					lessonstat = schedule.get("lessonstat").toString();
				}
				if (null == schedule.get("oid")) {
					lessonstat = "0";
				}
				if (null != schedule.get("cdate")) {
					cdate = schedule.get("cdate").toString();
				}
				String value = "" + schedule.get("oid") + "," + lessonstat + "," + cdate + "," + labroom + ","
						+ schedule.get("teacher") + "," + schedule.get("Clazz") + "," + schedule.get("course") + ","
						+ content + "";
				lv.add(value);
				// num.put("num1", value);
				// list.add(num);
				// map.put("week" + schedule.get("weekDay"), list);
				int day = 0;
				if (schedule.get("weekDay").equals("星期一")) {
					day = 1;
				} else if (schedule.get("weekDay").equals("星期二")) {
					day = 2;
				} else if (schedule.get("weekDay").equals("星期三")) {
					day = 3;
				} else if (schedule.get("weekDay").equals("星期四")) {
					day = 4;
				} else if (schedule.get("weekDay").equals("星期五")) {
					day = 5;
				}
				map.put("week" + day, lv);
			} else {
				List<Map<String, Object>> count1 = new ArrayList<Map<String, Object>>();
				List<Map<String, Object>> count2 = new ArrayList<Map<String, Object>>();
				List<Map<String, Object>> count3 = new ArrayList<Map<String, Object>>();
				List<Map<String, Object>> count4 = new ArrayList<Map<String, Object>>();
				List<Map<String, Object>> count5 = new ArrayList<Map<String, Object>>();
				int week1 = 0;
				int week2 = 0;
				int week3 = 0;
				int week4 = 0;
				int week5 = 0;
				for (Map<String, Object> schedule : returnValue) {
					// int flag =
					// Integer.parseInt(schedule.get("weekDay").toString());
					String flag = schedule.get("weekDay").toString();
					if ("星期一".equals(flag)) {
						week1++;
						count1.add(schedule);
					} else if ("星期二".equals(flag)) {
						week2++;
						count2.add(schedule);
					} else if ("星期三".equals(flag)) {
						week3++;
						count3.add(schedule);
					} else if ("星期四".equals(flag)) {
						week4++;
						count4.add(schedule);
					} else if ("星期五".equals(flag)) {
						week5++;
						count5.add(schedule);
					}
				}
				map = returnNums(week1, count1, map);
				map = returnNums(week2, count2, map);
				map = returnNums(week3, count3, map);
				map = returnNums(week4, count4, map);
				map = returnNums(week5, count5, map);
			}
			map.put("dayNo", lessonNo);
		}
		return map;
	}

	private Map<String, Object> returnNums(int week, List<Map<String, Object>> count, Map<String, Object> map) {
		List<String> lv = new ArrayList<String>();
		String labroom = "";
		String content = "";
		String lessonstat = "0";
		String cdate = "";
		if (week > 1) {
			// List<Map<String, Object>> list = new ArrayList<Map<String,
			// Object>>();
			for (int i = 0; i < count.size(); i++) {
				int day = 0;
				Map<String, Object> num = new HashMap<>();
				Map<String, Object> schedule = count.get(i);
				if (null != schedule.get("labroom")) {
					labroom = schedule.get("labroom").toString();
				}
				if (null != schedule.get("content")) {
					content = schedule.get("content").toString();
				}
				if (null != schedule.get("lessonstat")) {
					lessonstat = schedule.get("lessonstat").toString();
				}
				if (null == schedule.get("oid")) {
					lessonstat = "0";
				}
				if (null != schedule.get("cdate")) {
					cdate = schedule.get("cdate").toString();
				}
				String value = "" + schedule.get("oid") + "," + lessonstat + "," + cdate + "," + labroom + ","
						+ schedule.get("teacher") + "," + schedule.get("Clazz") + "," + schedule.get("course") + ","
						+ content + "";
				lv.add(value);

				if (schedule.get("weekDay").equals("星期一")) {
					day = 1;
				} else if (schedule.get("weekDay").equals("星期二")) {
					day = 2;
				} else if (schedule.get("weekDay").equals("星期三")) {
					day = 3;
				} else if (schedule.get("weekDay").equals("星期四")) {
					day = 4;
				} else if (schedule.get("weekDay").equals("星期五")) {
					day = 5;
				}
				map.put("week" + day, lv);
			}
			// map.put("week1", list);
		} else if (week == 1) {
			// List<Map<String, Object>> list = new ArrayList<Map<String,
			// Object>>();
			Map<String, Object> num = new HashMap<>();
			Map<String, Object> schedule = count.get(0);
			if (null != schedule.get("labroom")) {
				labroom = schedule.get("labroom").toString();
			}
			if (null != schedule.get("content")) {
				content = schedule.get("content").toString();
			}
			if (null != schedule.get("lessonstat")) {
				lessonstat = schedule.get("lessonstat").toString();
			}
			if (null == schedule.get("oid")) {
				lessonstat = "0";
			}
			if (null != schedule.get("cdate")) {
				cdate = schedule.get("cdate").toString();
			}
			String value = "" + schedule.get("oid") + "," + lessonstat + "," + cdate + "," + labroom + ","
					+ schedule.get("teacher") + "," + schedule.get("Clazz") + "," + schedule.get("course") + ","
					+ content + "";
			lv.add(value);

			int day = 0;
			if (schedule.get("weekDay").equals("星期一")) {
				day = 1;
			} else if (schedule.get("weekDay").equals("星期二")) {
				day = 2;
			} else if (schedule.get("weekDay").equals("星期三")) {
				day = 3;
			} else if (schedule.get("weekDay").equals("星期四")) {
				day = 4;
			} else if (schedule.get("weekDay").equals("星期五")) {
				day = 5;
			}
			map.put("week" + day, lv);
		}
		return map;
	}

	private List<Map<String, Object>> returnValue(List<Map<String, Object>> schedules, List<Map<String, Object>> counts,
			String flag, String lessonNo) {
		for (Map<String, Object> schedule : schedules) {
			if (schedule.get(flag).equals(lessonNo)) {
				counts.add(schedule);
			}
		}
		return counts;
	}

	public List<Schedule> findNextWeek(SysUser user) {
		List<Schedule> lsch = new ArrayList<Schedule>();
		String weekNo = "";
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		// 上学期
		System.out.println("" + (year - 1) + "-09-01" + "~" + (year) + "-02-20");
		// 下学期
		System.out.println("" + (year) + "-02-21" + "~" + (year) + "-06-31");
		System.out.println(DateUtil.getYmd());
		DateUtil.getYYYYMMDD();
		Date date = new Date();
		Date last_term_start = DateUtil.parseYmd("" + (year - 1) + "-09-01");
		Date last_term_end = DateUtil.parseYmd("" + (year) + "-01-21");

		Date next_term_start = DateUtil.parseYmd("" + (year) + "-02-20");
		Date next_term_end = DateUtil.parseYmd("" + (year) + "-06-31");

		if (date.getTime() > last_term_start.getTime() && date.getTime() < last_term_end.getTime()) {
			weekNo = "第" + (date.getTime() - last_term_start.getTime()) / 1000 / 60 / 60 / 24 / 7 + 1 + "周";
		} else if (date.getTime() > next_term_start.getTime() && date.getTime() < next_term_end.getTime()) {
			weekNo = "第" + (date.getTime() - next_term_start.getTime()) / 1000 / 60 / 60 / 24 / 7 + 1 + "周";
		} else {
			weekNo = "第2周";
		}
		lsch = scheduleDao.findNextWeek(weekNo);
		return lsch;
	}

	@Override
	public void insertSchedule(SysUser user, List<StringBuilder> params) throws Exception {
		String sql = "";
		String dql = "";
		Calendar cal = Calendar.getInstance();
		Map<String, Object> mp = new HashMap<String, Object>();
		int year = cal.get(Calendar.YEAR);
		String classYear = "";
		String term = "";
		Date date = new Date();
		Date last_term_start = DateUtil.parseYmd("" + (year - 1) + "-09-01");
		Date last_term_end = DateUtil.parseYmd("" + (year) + "-01-21");

		Date next_term_start = DateUtil.parseYmd("" + (year) + "-02-20");
		Date next_term_end = DateUtil.parseYmd("" + (year) + "-06-31");

		if (date.getTime() > last_term_start.getTime() && date.getTime() < last_term_end.getTime()) {
			classYear = year + 1 + "年";
			term = "上学期";
		} else if (date.getTime() > next_term_start.getTime() && date.getTime() < next_term_end.getTime()) {
			classYear = year + "年";
			term = "下学期";
		} else {
			classYear = "放假了";
		}
		int schoolid = user.getAreaid();
		Organization org = organDao.findByID(schoolid);
		dql = "delete from schedule where regionID= " + schoolid + " and ClassYear= " + classYear + " and term = "
				+ term;
		scheduleDao.execute(dql, null, true);
		int regionid = org.getOrganPreID();
		for (StringBuilder sb : params) {
			try {
				sql = "insert into schedule (regionID,schoolID,weekNo,weekDay,lessonNo,teacher,clazz,course,term) ";
				sql += "values (" + "'" + regionid + "'," + "'" + schoolid + "'," + sb.toString() + ") ";
				scheduleDao.execute(sql, null, true);
				System.out.println(sql);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void saveSchedule(SysUser user, Schedule scd) throws Exception {
		int oid = scd.getOid();
		Schedule old = scheduleDao.findOne(oid);
		if (null == old) {
			scheduleDao.save(scd);
		} else {
			scheduleDao.update(old);
		}
	}

	@Override
	public void delSchedule(SysUser user, Schedule scd) throws Exception {

	}

}
