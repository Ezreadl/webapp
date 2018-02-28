package com.qm.sys.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.qm.core.base.IBaseDao;
import com.qm.core.base.QmQuery;
import com.qm.sys.domain.LoginSolution;

@Repository
public interface ILoginSolutionDao extends IBaseDao<LoginSolution,Integer>{
	
	public List<LoginSolution> findByDelFlg(int delFlg,Sort sort);
	
	@QmQuery(value="from LoginSolution where solutionName=? and oid!=?",nativeQuery=true)
	public long findDataCount1(Object[] params) throws Exception;

}
