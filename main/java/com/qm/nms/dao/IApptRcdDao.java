package com.qm.nms.dao;

import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.qm.core.base.GridListData;
import com.qm.core.base.IBaseDao;
import com.qm.core.base.IQuery;
import com.qm.core.base.PageInfo;
import com.qm.core.base.QmQuery;
import com.qm.nms.domain.ApptRecord;

@Repository
public interface IApptRcdDao extends IBaseDao<ApptRecord, Integer> {

	@QmQuery(value = "select distinct par.oid,sch.oid as scheduleID,sch.teacher,sch.Clazz,par.labroomID,par.apptType,par.checkAnswer,par.supervise,par.superMan,par.apptComt,par.checkMan,usr.userNick askManName,par.askMan, "
			+ "par.cdate,par.planLessonNo,par.checkStat,par.checkComt,par.lessonStat,par.bgtime,par.viewfilename,par.picUrl,par.studentNum,par.groupNum, "
			+ "par.labEquip,sch.course,sch.weekNo,sch.weekDay,sch.lessonNo,par.content,org.organName school,org1.organName labroom,par.visitRcd "
			+ "from schedule sch "
			+ "left join apptrecord par on par.scheduleID = sch.oid "
			+ "left join organization org on sch.schoolID = org.oid "
			+ "left join organization org1 on par.labroomID = org1.oid "
			+ "left join WorkRest wr on sch.schoolID= wr.schoolID "
			+ "left join SysUser usr on par.askMan = usr.oid "
			+ "{ #& sch.teacher =:teacher #& sch.schoolID=:schoolID #& par.checkStat>=:checkStat #& par.oid=:oid } ", nativeQuery = true)
	public GridListData findDataList1(IQuery query, PageInfo pageInfo) throws Exception;
	
	@QmQuery(value = "select distinct par.oid,sch.oid as scheduleID,sch.teacher,sch.Clazz,par.labroomID,par.apptType,par.checkAnswer,par.supervise,par.superMan,par.apptComt,par.checkMan,usr.userNick askManName,par.askMan, "
			+ "par.cdate,par.planLessonNo,par.checkStat,par.checkComt,par.lessonStat,par.bgtime,par.viewfilename,par.picUrl,par.studentNum,par.groupNum, "
			+ "par.labEquip,sch.course,sch.weekNo,sch.weekDay,sch.lessonNo,par.content,org.organName school,org1.organName labroom,par.visitRcd "
			+ "from schedule sch "
			+ "left join apptrecord par on par.scheduleID = sch.oid "
			+ "left join organization org on sch.schoolID = org.oid "
			+ "left join organization org1 on par.labroomID = org1.oid "
			+ "left join SysUser usr on par.askMan = usr.oid "
			+ "{ #& sch.teacher =:teacher #& sch.schoolID=:schoolID #& par.checkStat is null #& par.oid=:oid } ", nativeQuery = true)

	public GridListData findDataList2(IQuery query, PageInfo pageInfo) throws Exception;
	
	@QmQuery(value = "select distinct par.oid,sch.oid as scheduleID,sch.teacher,sch.Clazz,par.labroomID,par.apptType,par.checkAnswer,par.supervise,par.superMan,par.apptComt,par.checkMan,usr.userNick askManName,par.askMan,  "
			+ "par.cdate,par.planLessonNo,par.checkStat,par.checkComt,par.lessonStat,par.bgtime,par.viewfilename,par.picUrl,par.studentNum,par.groupNum, "
			+ "par.labEquip,sch.course,sch.weekNo,sch.weekDay,sch.lessonNo,par.content,org.organName school,org1.organName labroom,par.visitRcd "
			+ "from schedule sch "
			+ "left join apptrecord par on par.scheduleID = sch.oid "
			+ "left join organization org on sch.schoolID = org.oid "
			+ "left join organization org1 on par.labroomID = org1.oid "
			+ "left join SysUser usr on par.askMan = usr.oid "
			+ "{ #& par.teacher =:teacher #& sch.schoolID=:schoolID #& par.checkStat=:checkState #& par.oid=:oid } ", nativeQuery = true)
	public GridListData findDataList3(IQuery query, PageInfo pageInfo) throws Exception;
	
	@Query(value="select count(*) from ApptRecord where lessonStat >= ?1",nativeQuery=true)
	public int findApptCount(int lessonStat) throws Exception;
	
	@Query(value="select count(*) from ApptRecord appt left join Schedule sch on appt.scheduleID = sch.oid "
			+ "where appt.lessonStat >= ?1 and sch.schoolID= ?2",nativeQuery=true)
	public int findApptSchoolCount(int lessonStat, int schoolid) throws Exception;
	
	@QmQuery(value="select cdate,count(*)count from ApptRecord where lessonStat >= ?1 group by cdate",nativeQuery=true)
	public List<Map<String,Object>> findDataList2(Object[] params,PageInfo pageInfo) throws Exception;
	
	@Query(value = "select par.* from ApptRecord par where scheduleID = ?1 ", nativeQuery = true)
	public List<ApptRecord> findBySchID(int schid);
	
	@QmQuery(value = "select org.organName school,count(*)schdulenum from ApptRecord par left join Schedule sch on par.scheduleID = sch.oid "
			+ "left join Organization org on org.oid=sch.schoolID where lessonstat>0 group by sch.schoolID ", nativeQuery = true)
	public List<Map<String,Object>> findDataList1(Object[] params,PageInfo pageInfo);
	
	@QmQuery(value = "select sch.Clazz,sch.course,sch.teacher,count(*) apptnum from ApptRecord par left join Schedule sch on par.scheduleID = sch.oid "
			+ "left join Organization org on org.oid=sch.schoolID where lessonstat>0 and schoolID=?1 group by sch.schoolID,sch.clazz,sch.course,sch.teacher ", nativeQuery = true)
	public List<Map<String,Object>> findDataList3(Object[] params,PageInfo pageInfo);

}
