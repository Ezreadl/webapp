package com.qm.nms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;

import com.qm.core.base.GridListData;
import com.qm.core.base.IBaseDao;
import com.qm.core.base.IQuery;
import com.qm.core.base.PageInfo;
import com.qm.core.base.QmQuery;
import com.qm.nms.domain.Schedule;

public interface IScheduleDao extends IBaseDao<Schedule, Integer> {
	
	@QmQuery(value="select org1.organName region,org.organName school,org2.organName labroom,appt.oid,s.Clazz,"
			+ "s.term,s.course,s.weekNo,s.weekDay,s.lessonNo,s.teacher,appt.cdate,appt.lessonstat,appt.content "
			+ "from Schedule s "
			+ "left join Apptrecord appt on appt.scheduleID=s.oid "
			+ "left join Organization org on s.schoolID= org.oid "
			+ "left join Organization org1 on s.regionID= org1.oid "
			+ "left join Organization org2 on appt.labroomID= org2.oid "
			+ "{ #& weekNo=:weekNo #& checkStat>:checkStat #& ( org.oid=:areaId  or org1.oid=:areaId ) #& teacher=:teacher }",nativeQuery=true)
	public GridListData findDataList1(IQuery query,PageInfo pageInfo) throws Exception;

	@QmQuery(value="select appt.oid,appt.picUrl,appt.viewfilename,org1.locationUrl regionUrl,org2.locationUrl schoolUrl,video.chanNo,video.channel from Apptrecord appt "+
			"left join Schedule s on appt.scheduleID=s.oid "+
			"left join organization org1 on s.regionID = org1.oid "+
			"left join organization org2 on s.schoolID = org2.oid "+
			"left join videosite video on appt.labroomID = video.organid "+
			"{ #& appt.oid =:oid }",nativeQuery=true)
	public GridListData findDataList2(IQuery query,PageInfo pageInfo) throws Exception;
	
	@Query(value="select count(*)count from Schedule where schoolID=?1",nativeQuery=true)
	public int findSchoolGroupCount(int oid);

	@Query(value="select count(*)count from Schedule ",nativeQuery=true)
	public int findGroupCount();
	
	@QmQuery(value="select org.organName school,count(*)testnum from Schedule s left join "
			+ "Organization org on s.schoolID=org.oid group by schoolID",nativeQuery=true)
	public List<Map<String,Object>> findDataList1(Object[] params,PageInfo pageInfo);

	@QmQuery(value="select Clazz from Schedule s where teacher = ? group by clazz ",nativeQuery=true)
	public List<Map<String,Object>> findDataList4(Object[] params,PageInfo pageInfo);	
	
	@QmQuery(value="select s.* from Schedule s { #& weekNo=:weekNo #& weekDay=:weekDay #& lessonNo=:lessonNo }",nativeQuery=true)
	public List<Map<String,Object>> findDataList3(Object[] params,PageInfo pageInfo);
	
	@Query(value="select * from Schedule s where s.teacher = ?1",nativeQuery=true)
	public List<Schedule> findByUserID(String teachserID);

	@Query(value="select * from Schedule s where s.weekNo = ?1 and s.oid not in (select scheduleid from apptrecord)",nativeQuery=true)
	public List<Schedule> findNextWeek(String weekNo);	
	
	@QmQuery(value="select s.school,s.labroom,count(oid)num from Schedule s group by s.labroom",nativeQuery=true)
	public List<Map<String,Object>> findDataList2(Object[] params,PageInfo pageInfo);
	
	@QmQuery(value = "select sch.Clazz,sch.course,sch.teacher,count(*)schedulenum from Schedule sch "
			+ "where sch.schoolID=?1 group by sch.schoolID,sch.Clazz,sch.course,sch.teacher ", nativeQuery = true)
	public List<Map<String,Object>> findDataList5(Object[] params,PageInfo pageInfo);		

}
