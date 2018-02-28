package com.qm.nms.service;

import org.springframework.stereotype.Repository;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.nms.query.RadioRecordQuery;

@Repository
public interface IRadioRcdService {
	public GridListData findDataList1(RadioRecordQuery query,PageInfo pageInfo) throws Exception;
}
