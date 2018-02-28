package com.qm.sys.service;

import java.util.List;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.sys.domain.SysRole;
import com.qm.sys.domain.SysUser;
import com.qm.sys.query.SysRoleQuery;

public interface IRoleService {

	GridListData findAllRole(SysRoleQuery query, PageInfo pageInfo) throws Exception;

	SysRole findRoleById(int oid);

	void saveRole(SysRole role,SysUser loginUser);
	
	void changeDelFlg(SysUser sysUser, List<Integer> oidList, int delFlg);

	List<SysRole> findRoleById(List<Integer> oidList); 

	List<SysRole> findLowRole(int oid);
	
	String findPage(SysUser user);

}
