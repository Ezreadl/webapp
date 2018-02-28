package com.qm.nms.service;

import org.springframework.stereotype.Repository;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.nms.query.MsgRecordQuery;

@Repository
public interface IMsgRcdService {
	public GridListData findDataList1(MsgRecordQuery query, PageInfo pageInfo) throws Exception;
}
