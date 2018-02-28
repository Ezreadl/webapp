package com.qm.nms.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qm.core.base.GridListData;
import com.qm.core.base.IBaseDao;
import com.qm.core.base.IQuery;
import com.qm.core.base.PageInfo;
import com.qm.core.base.QmQuery;
import com.qm.nms.domain.SnDevice;
import com.qm.nms.domain.SnSystemConfig;

@Repository
public interface ISnSysCfgDao  extends IBaseDao<SnSystemConfig, Integer> {
	@QmQuery(value="select * from SnSystemConfig par { #& par.paramName=:paramName #& par.paramValue=:paramValue #& par.paramDesc=:paramDesc }", nativeQuery = true)
	public GridListData findDataList1(IQuery query,PageInfo pageInfo) throws Exception;
    @Query(value="select *from SnSystemConfig sn where sn.paramName = ? ", nativeQuery = true)
    public SnSystemConfig findByName(String name);
}
