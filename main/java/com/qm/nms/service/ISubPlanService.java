package com.qm.nms.service;

import org.springframework.stereotype.Repository;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.nms.query.SubjectPlanQuery;

@Repository
public interface ISubPlanService {
	public GridListData findDataList1(SubjectPlanQuery query,PageInfo pageInfo) throws Exception;
}
