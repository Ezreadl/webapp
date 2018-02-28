package com.qm.nms.dao;

import org.springframework.stereotype.Repository;

import com.qm.core.base.GridListData;
import com.qm.core.base.IBaseDao;
import com.qm.core.base.IQuery;
import com.qm.core.base.PageInfo;
import com.qm.core.base.QmQuery;
import com.qm.nms.domain.MsgRecord;

@Repository
public interface IMsgRcdDao extends IBaseDao<MsgRecord,Integer>{
	@QmQuery(value="select rcd.* from MsgRecord rcd {#& rcd.msgName=:msgName #& rcd.startTime=:startTime #& rcd.towho=:towho #& rcd.fomwho=:fromwho } ",nativeQuery=true)	
	public GridListData findDataList1(IQuery query,PageInfo pageInfo) throws Exception;

}
