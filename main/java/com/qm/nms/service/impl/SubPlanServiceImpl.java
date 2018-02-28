package com.qm.nms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.nms.dao.ISubPlanDao;
import com.qm.nms.query.SubjectPlanQuery;
import com.qm.nms.service.ISubPlanService;
@Service
public class SubPlanServiceImpl implements ISubPlanService{
	@Autowired
	public ISubPlanDao subPlanDao;

	@Override
	public GridListData findDataList1(SubjectPlanQuery query, PageInfo pageInfo) throws Exception {
		GridListData gld = new GridListData();
		gld = subPlanDao.findDataList1(query, pageInfo);
		return gld;
	}

}
