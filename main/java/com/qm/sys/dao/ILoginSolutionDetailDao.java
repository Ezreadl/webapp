package com.qm.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qm.core.base.GridListData;
import com.qm.core.base.IBaseDao;
import com.qm.core.base.IQuery;
import com.qm.core.base.PageInfo;
import com.qm.core.base.QmQuery;
import com.qm.sys.domain.LoginSolutionDetail;

@Repository
public interface ILoginSolutionDetailDao extends IBaseDao<LoginSolutionDetail,Integer>{
	
	@QmQuery(value="select d.* from LoginSolutionDetail d where e.delFlg=? and d.userType=? and d.solutionId=? ",nativeQuery=true)
	public GridListData findDataList1(IQuery query,PageInfo pageInfo) throws Exception;
	
	public void deleteBySolutionId(int solutionId);
	
	public List<LoginSolutionDetail> findBySolutionIdAndUserType(int solutionId,int userType);
}
