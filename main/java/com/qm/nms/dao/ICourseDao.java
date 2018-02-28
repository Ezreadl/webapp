package com.qm.nms.dao;

import org.springframework.stereotype.Repository;

import com.qm.core.base.GridListData;
import com.qm.core.base.IBaseDao;
import com.qm.core.base.IQuery;
import com.qm.core.base.PageInfo;
import com.qm.core.base.QmQuery;
import com.qm.nms.domain.Course;

@Repository
public interface ICourseDao extends IBaseDao<Course,Integer>{

	@QmQuery(value="select cr.* from Course cr { #& cr.courseName=:courseName #& cr.delFlg =:delFlg }",nativeQuery=true)	
	public GridListData findDataList1(IQuery query,PageInfo pageInfo) throws Exception;

}
