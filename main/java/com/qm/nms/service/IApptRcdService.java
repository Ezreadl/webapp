package com.qm.nms.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.SessionAttribute;

import com.qm.core.base.GlobalCfg;
import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.nms.domain.ApptRecord;
import com.qm.nms.domain.Schedule;
import com.qm.nms.query.ApptRcdQuery;
import com.qm.sys.domain.SysUser;

public interface IApptRcdService {
	
	public GridListData findRcdList(SysUser user,ApptRcdQuery query,PageInfo pageInfo) throws Exception;

	public GridListData findMyAppt(SysUser user,ApptRcdQuery query, PageInfo pageInfo) throws Exception;	
	
	public ApptRecord findOne(int oid);
	
	public List<SysUser> findLabMan(SysUser user) throws Exception;
	
	public void saveAppt(SysUser user,ApptRecord rcd) throws Exception;

	public void insertAppt(List<Schedule> list) throws Exception;
	
	public int findApptCount(SysUser user, int stat) throws Exception;
	
	public Map<String,Object> findByClassCourse(SysUser user, PageInfo pageInfo) throws Exception;	
	
	public List<Map<String, Object>> findApptDay(int stat,PageInfo pageInfo) throws Exception;
	
	public List<Map<String,Object>> findFGroup(SysUser user,PageInfo pageInfo) throws Exception;

	public GridListData findApptList(ApptRcdQuery query,PageInfo pageInfo) throws Exception;
	
	public void changeDelFlg(SysUser user,List<Integer> oidList,int delFlg) throws Exception;

}
