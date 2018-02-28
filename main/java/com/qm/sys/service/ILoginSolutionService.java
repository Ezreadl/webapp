package com.qm.sys.service;

import java.util.List;
import java.util.Map;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.sys.domain.LoginSolution;
import com.qm.sys.domain.LoginSolutionDetail;
import com.qm.sys.domain.SysUser;
import com.qm.sys.query.LoginSolutionQuery;

public interface ILoginSolutionService {
	
	public LoginSolution findLoginSolutionById(int oid) throws Exception;

	public List<LoginSolution> findLoginSolutionAll() throws Exception;
	
	public List<LoginSolutionDetail> findLoginSolutionDetailAll() throws Exception;

	public Map<Integer,LoginSolution> findLoginSolutionMap() throws Exception;

	public GridListData findLoginSolutionList(SysUser sysUser,LoginSolutionQuery query,PageInfo pageInfo) throws Exception ;
	
	public GridListData findLoginSolutionDetailList(SysUser sysUser,int solutionId,PageInfo pageInfo) throws Exception;

	public void saveLoginSolution(SysUser sysUser,LoginSolution loginSolution,List<LoginSolutionDetail> maintainGridList,List<LoginSolutionDetail> managerGridList) throws Exception;

	public void changeDelFlg(SysUser sysUser,List<Integer> oidList,int delFlg) throws Exception ;
	
	public boolean isUnique(LoginSolution solution) throws Exception;
	
	public List<LoginSolutionDetail> findLoginSolutionDetailById(int solutionId,int userType) throws Exception;
}
