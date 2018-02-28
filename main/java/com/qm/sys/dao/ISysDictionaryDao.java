package com.qm.sys.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.qm.core.base.GridListData;
import com.qm.core.base.IBaseDao;
import com.qm.core.base.IQuery;
import com.qm.core.base.PageInfo;
import com.qm.core.base.QmQuery;
import com.qm.sys.domain.SysDictionary;

@Repository
public interface ISysDictionaryDao extends IBaseDao<SysDictionary,Integer>{

	@QmQuery(value="select a.* from SysDictionary a ",nativeQuery=true)
	public GridListData findDataList1(IQuery query,PageInfo pageInfo) throws Exception;

}
