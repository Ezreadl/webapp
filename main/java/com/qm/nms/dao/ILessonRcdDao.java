package com.qm.nms.dao;

import org.springframework.stereotype.Repository;

import com.qm.core.base.GridListData;
import com.qm.core.base.IBaseDao;
import com.qm.core.base.IQuery;
import com.qm.core.base.PageInfo;
import com.qm.core.base.QmQuery;
import com.qm.nms.domain.LessonRecord;

@Repository
public interface ILessonRcdDao extends IBaseDao<LessonRecord,Integer>{
	@QmQuery(value="select par.* from LessonRecord par {  #& par.oid=:oid #& par.course=:course #& par.lesson=:lesson #& par.lessonInfo=:lessonInfo #& par.lessonType=:lessonType #& par.region=:region #& par.school=:school #& par.grade=:grade #& par.className=:className #& par.teacherID=:teacherID #& par.teacherName=:teacherName #& par.lessonHour=:lessonHour #& par.lessonTime=:lessonTime #& par.lessonEquip=:lessonEquip #& par.groupNo=:groupNo #& par.lessonStatus=:lessonStatus #& par.ifCommend=:ifCommend #& par.delFlg=:delFlg #& par.apptNumber=:apptNumber #& par.apptStat=:apptStat #& par.apptComt=:apptComt #& par.apptMan=:apptMan #& par.checkMan=:checkMan #& par.checkRes=:checkRes #& par.checkOut=:checkOut #& par.needappt=:needappt #& par.isChange=:isChange #& par.changeTime=:changeTime }",nativeQuery=true)	
	public GridListData findDataList1(IQuery query,PageInfo pageInfo) throws Exception;

}
