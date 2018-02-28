package com.qm.nms.service;

import java.util.List;
import java.util.Map;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.core.base.WebStatus4P;
import com.qm.nms.domain.Schedule;
import com.qm.nms.query.ApptRcdQuery;
import com.qm.nms.query.ScheduleQuery;
import com.qm.sys.domain.SysUser;

public interface IScheduleService {

	Map<String, Object> findAllSchedule(ScheduleQuery query, PageInfo pageinfo) throws Exception;

	public WebStatus4P findMySchedule(SysUser user,ScheduleQuery query, PageInfo pageinfo) throws Exception;
	
	public WebStatus4P findSchoolSchedule(SysUser user,ScheduleQuery query, PageInfo pageinfo) throws Exception;	
	
	public int findGroupCount(SysUser user, PageInfo pageInfo) throws Exception;	
	
	public List<Map<String, Object>> findGroup(SysUser user, PageInfo pageInfo) throws Exception;
	
	public Map<String, Object> groupScheduleByCourse(SysUser user) throws Exception;
	
	public List<Map<String, Object>> findMyClass(SysUser user,PageInfo pageInfo) throws Exception;

	public Map<String,Object> findByClassCourse(SysUser user, PageInfo pageInfo) throws Exception;
	
	public List<Map<String, Object>> findScheduleNow(int uid) throws Exception;
	
	public GridListData findOneAppt(ApptRcdQuery query,PageInfo pageInfo) throws Exception;	
	
	public List<Schedule> findNextWeek(SysUser user)throws Exception;	
	
	public void saveSchedule(SysUser user,Schedule scd) throws Exception;
	
	public void insertSchedule(SysUser user, List<StringBuilder> param) throws Exception;
	
	public void delSchedule(SysUser user,Schedule scd) throws Exception;
	
}
