package com.qm.nms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.nms.dao.ITchContDao;
import com.qm.nms.query.TeachContentQuery;
import com.qm.nms.service.ITchContService;
@Service
public class TchContentServiceImpl implements ITchContService{
	@Autowired
	public ITchContDao tchContDao;

	@Override
	public GridListData findDataList1(TeachContentQuery query, PageInfo pageInfo) throws Exception {
		GridListData gld = new GridListData();
		gld = tchContDao.findDataList1(query, pageInfo);
		return gld;
	}

}
