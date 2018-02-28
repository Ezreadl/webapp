package com.qm.nms.dao;

import org.springframework.stereotype.Repository;

import com.qm.core.base.GridListData;
import com.qm.core.base.IBaseDao;
import com.qm.core.base.IQuery;
import com.qm.core.base.PageInfo;
import com.qm.core.base.QmQuery;
import com.qm.nms.domain.RadioRecord;

@Repository
public interface IRadioRcdDao extends IBaseDao<RadioRecord,Integer>{
	@QmQuery(value="select rcd.* from RadioRecord rcd { #& rcd.radioName=:radioName #& rcd.region=:region #& rcd.school=:school #& rcd.room=:room #& rcd.startTime=:startTime }",nativeQuery=true)	
	public GridListData findDataList1(IQuery query,PageInfo pageInfo) throws Exception;

}
