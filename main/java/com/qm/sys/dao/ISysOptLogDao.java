package com.qm.sys.dao;



import org.springframework.stereotype.Repository;

import com.qm.core.base.GridListData;
import com.qm.core.base.IBaseDao;
import com.qm.core.base.IQuery;
import com.qm.core.base.PageInfo;
import com.qm.core.base.QmQuery;
import com.qm.sys.domain.SysOptLog;

@Repository
public interface ISysOptLogDao extends IBaseDao<SysOptLog, Integer>{
	
	@QmQuery(value="select sol.* from SysOptLog sol {#& sol.optUserNick like %:userNick% #& sol.ipAddress like %:ipAdress% "
			+ "#& sol.optDesc like %:optDesc% #& sol.optTime >= :timeStart #& sol.optTime <= :timeEnd }",nativeQuery=true)
	public GridListData findDataList1(IQuery query,PageInfo pageInfo);
	
}
