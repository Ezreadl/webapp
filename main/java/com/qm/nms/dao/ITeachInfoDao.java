package com.qm.nms.dao;

import org.springframework.stereotype.Repository;

import com.qm.core.base.GridListData;
import com.qm.core.base.IBaseDao;
import com.qm.core.base.IQuery;
import com.qm.core.base.PageInfo;
import com.qm.core.base.QmQuery;
import com.qm.nms.domain.TeachInfo;

@Repository
public interface ITeachInfoDao extends IBaseDao<TeachInfo,Integer>{

	@QmQuery(value="select * from  TeachInfo par #& par.region=:regionselect * from  TeachInfo par #& par.school=:schoolselect * from  TeachInfo par #& par.grade=:gradeselect * from  TeachInfo par #& par.className=:classNameselect * from  TeachInfo par #& par.teacherName=:teacherNameselect * from  TeachInfo par #& par.courseName=:courseName }",nativeQuery=true)	
	public GridListData findDataList1(IQuery query,PageInfo pageInfo) throws Exception;

}
