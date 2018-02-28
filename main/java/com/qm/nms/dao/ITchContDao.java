package com.qm.nms.dao;

import org.springframework.stereotype.Repository;

import com.qm.core.base.GridListData;
import com.qm.core.base.IBaseDao;
import com.qm.core.base.IQuery;
import com.qm.core.base.PageInfo;
import com.qm.core.base.QmQuery;
import com.qm.nms.domain.TeachContent;

@Repository
public interface ITchContDao extends IBaseDao<TeachContent,Integer>{
	@QmQuery(value="select tc.* from TeachContent tc { #& tc.contName=:contName  }",nativeQuery=true)	
	public GridListData findDataList1(IQuery query,PageInfo pageInfo) throws Exception;

}
