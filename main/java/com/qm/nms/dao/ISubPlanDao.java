package com.qm.nms.dao;

import org.springframework.stereotype.Repository;

import com.qm.core.base.GridListData;
import com.qm.core.base.IBaseDao;
import com.qm.core.base.IQuery;
import com.qm.core.base.PageInfo;
import com.qm.core.base.QmQuery;
import com.qm.nms.domain.SubjectPlan;

@Repository
public interface ISubPlanDao extends IBaseDao<SubjectPlan,Integer>{
	@QmQuery(value="select sp.* from SubjectPlan sp {#& sp.course=:course }",nativeQuery=true)	
	public GridListData findDataList1(IQuery query,PageInfo pageInfo) throws Exception;

}
