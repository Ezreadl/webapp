package com.qm.nms.service;

import org.springframework.stereotype.Repository;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.nms.query.TeachContentQuery;

@Repository
public interface ITchContService {
	public GridListData findDataList1(TeachContentQuery query,PageInfo pageInfo) throws Exception;
}
