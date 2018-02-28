package com.qm.nms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.qm.core.base.GlobalCfg;
import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.core.util.DateUtil;
import com.qm.nms.dao.IApptRcdDao;
import com.qm.nms.dao.IOrganDao;
import com.qm.nms.domain.ApptRecord;
import com.qm.nms.domain.Organization;
import com.qm.nms.domain.Schedule;
import com.qm.nms.query.ApptRcdQuery;
import com.qm.nms.service.IApptRcdService;
import com.qm.sys.dao.ISysRoleDao;
import com.qm.sys.dao.ISysUserDao;
import com.qm.sys.domain.SysUser;

@Service
public class ApptRcdServiceImpl implements IApptRcdService {

	@Autowired
	public IApptRcdDao apptRcdDao;
	@Autowired
	public IOrganDao organDao;
	@Autowired
	public ISysRoleDao sysRoleDao;
	@Autowired
	public ISysUserDao sysUserDao;

	@Override
	public GridListData findRcdList(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser user,
			ApptRcdQuery query, PageInfo pageInfo) throws Exception {
		Organization org = organDao.findOwnByUserID(user.getOid());
			// "教师"
		if (user.getUserType() == 5) {
			query.setApptMan(user.getUserName());
			// "实验员",实验监督员,学校管理员
		} else if (user.getUserType() == 7 || user.getUserType() == 6 || user.getUserType() == 4) {
			query.setSchoolID(org.getOid());
			// 只查询调课
			query.setApptType(2);
			// 区管理员,区巡查员
		} else if (user.getUserType() == 2 || user.getUserType() == 3) {
			query.setRegionID(org.getOid());
			query.setApptType(2);
		}
		pageInfo.setSort("checkStat");
		pageInfo.setDir("desc");
		GridListData apt = apptRcdDao.findDataList1(query, pageInfo);
		return apt;
	}

	public int findApptCount(SysUser user, int stat) throws Exception {
		int count = 0;
		int schoolid = user.getAreaid();
		if (user.getUserType() == 2)
			count = apptRcdDao.findApptCount(stat);
		else
			count = apptRcdDao.findApptSchoolCount(stat, schoolid);
		return count;
	}

	public List<Map<String, Object>> findApptDay(int stat, PageInfo pageInfo) throws Exception {
		List<Map<String, Object>> list = apptRcdDao.findDataList2(new Integer[] { stat }, pageInfo);
		return list;
	}

	@Override
	public GridListData findMyAppt(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser user,
			ApptRcdQuery query, PageInfo pageInfo) throws Exception {
		// TODO Auto-generated method stub
		pageInfo.setSort("cdate");
		pageInfo.setDir("desc");
		query.setApptMan(user.getUserNick());
		GridListData apt = apptRcdDao.findDataList1(query, pageInfo);
		return apt;
	}

	// 查找本校实验员
	public List<SysUser> findLabMan(SysUser user) throws Exception {
		int area = user.getAreaid();
		List<SysUser> lusr = sysUserDao.findUserByOrg(area);
		return lusr;
	}

	// 查找本校实验室
	public List<SysUser> findLabRoom(SysUser user) throws Exception {
		int area = user.getAreaid();
		List<SysUser> lusr = sysUserDao.findUserByOrg(area);
		return lusr;
	}

	public void saveAppt(SysUser user, ApptRecord rcd) throws Exception {
		Integer oid = rcd.getOid();
		ApptRecord old = apptRcdDao.findOne(oid);
		// 老师
		if (user.getUserType() == 5) {
			rcd.setCheckStat("1");
			// 实验员
		} else if (user.getUserType() == 7) {
			// 調課預約
			if (old.getApptType() == 2) {
				rcd.setCheckStat("2");
			} else {
				rcd.setCheckStat("4");
			}
			rcd.setCheckMan(user.getOid());
			// 学校管理员
		} else if (user.getUserType() == 4) {
			rcd.setCheckStat("3");
			rcd.setCheckMan(user.getOid());
			// 区管理员
		} else if (user.getUserType() == 2) {
			rcd.setCheckStat("4");
			rcd.setCheckMan(user.getOid());
		}
		if (null == old) {
			apptRcdDao.save(rcd);
		} else {
			// 老师，只能预约
			if (user.getUserType() == 5) {
				old.setLessonStat("1");
				old.setGroupNum(rcd.getGroupNum());
				old.setContent(rcd.getContent());
				old.setCdate(rcd.getCdate());
				old.setCheckStat(rcd.getCheckStat());
				old.setStudentNum(rcd.getStudentNum());
				old.setLabEquip(rcd.getLabEquip());
				old.setAskMan(rcd.getAskMan());
				old.setApptComt(rcd.getApptComt());
				old.setApptDate(rcd.getCdate());
				old.setApptStart(rcd.getApptStart());
				old.setApptEnd(rcd.getApptEnd());
				old.setApptType(rcd.getApptType());
				// 审批
			} else {
				old.setLessonStat("2");
				old.setCheckComt(rcd.getCheckComt());
				old.setCheckAnswer(rcd.getCheckAnswer());
				old.setCheckStat(rcd.getCheckStat());
				old.setCheckMan(rcd.getCheckMan());
			}
			apptRcdDao.update(old);
		}
	}

	// 已完成分组课
	@Override
	public List<Map<String, Object>> findFGroup(SysUser user, PageInfo pageInfo) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int uid = user.getOid();
		list = apptRcdDao.findDataList1(new Integer[] {}, pageInfo);
		return list;
	}

	public Map<String, Object> findByClassCourse(SysUser user, PageInfo pageInfo) throws Exception {
		int schoolid = user.getAreaid();
		List<Map<String, Object>> list = apptRcdDao.findDataList3(new Integer[] { schoolid }, pageInfo);
		Map<String, Object> mp = new HashMap<String, Object>();
		for (Map<String, Object> map : list) {
			mp.put(map.get("Clazz").toString() + map.get("course").toString() + map.get("teacher").toString(), map);
		}
		return mp;
	}

	@SuppressWarnings("unchecked")
	public GridListData findApptList(ApptRcdQuery query, PageInfo pageInfo) throws Exception {
		GridListData gld = new GridListData();
		String result = "";
		// 选择查询
		if (null != query.getCheckState()) {
			// 未预约
			if (query.getCheckState() == 0) {
				gld = apptRcdDao.findDataList2(query, pageInfo);
				// 预约
			} else {
				query.setCheckStat(query.getCheckState());
				gld = apptRcdDao.findDataList3(query, pageInfo);
			}
		} else {
			gld = apptRcdDao.findDataList1(query, pageInfo);
		}
		List<Map<String, Object>> list = gld.getRows();
		for (Map<String, Object> mp : list) {
			if (null != mp.get("checkStat")) {
				if ("0".equals(mp.get("checkStat").toString())) {
					result = "未预约";
				} else if ("1".equals(mp.get("checkStat").toString())) {
					result = "实验员待审批";
				} else if ("2".equals(mp.get("checkStat").toString())) {
					result = "学校管理员待审批";
				} else if ("3".equals(mp.get("checkStat").toString())) {
					result = "区管理员待审批";
				} else if ("4".equals(mp.get("checkStat").toString())) {
					if (null != (mp.get("lessonStat"))) {
						if ("3".equals(mp.get("lessonStat").toString())) {
							result = "回看";
						} else if ("4".equals(mp.get("lessonStat").toString())) {
							result = "调课";
						} else if ("-1".equals(mp.get("lessonStat").toString())) {
							result = "缺课";
						} else if ("0".equals(mp.get("lessonStat").toString())) {
							result = "未预约";
						} else
							result = "已审批";
					}
				} else
					result = "未预约";
				mp.put("checkStat", result);
			}
		}
		return gld;
	}

	public void insertAppt(List<Schedule> list) throws Exception {
		for (Schedule sch : list) {
			System.out.println(sch.getOid());
			String sql = "";
			List<ApptRecord> appt = new ArrayList<ApptRecord>();
			appt = apptRcdDao.findBySchID(sch.getOid());
			if (appt == null) {
				sql = "insert into apptrecord (scheduleID) ";
				sql += "values (" + "'" + sch.getOid() + "') ";
				apptRcdDao.execute(sql, null, true);
			}
		}
	}

	public void changeDelFlg(SysUser user, List<Integer> oidList, int delFlg) throws Exception {
		List<ApptRecord> lor = apptRcdDao.findAll(oidList);
		for (ApptRecord old : lor) {
			apptRcdDao.delete(old);
		}
	}

	public ApptRecord findOne(int oid) {
		ApptRecord ar = apptRcdDao.findOne(oid);
		return ar;
	}
}
