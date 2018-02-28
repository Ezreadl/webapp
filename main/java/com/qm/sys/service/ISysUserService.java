package com.qm.sys.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.sys.domain.Equipment;
import com.qm.sys.domain.SysUser;
import com.qm.sys.query.SysUserQuery;

public interface ISysUserService {


	public SysUser findUser(String userPassword, String userName) throws Exception;

	public GridListData findUserInfo(SysUser sysUser,SysUserQuery query, PageInfo pageInfo) throws Exception;

	public GridListData findUserList(SysUser sysUser, SysUserQuery query, PageInfo pageInfo) throws Exception;
	
	public SysUser findSysUserById(int oid);
	
	public List<SysUser> findUserByType(SysUser user,int type) throws Exception;	
	
	public List<SysUser> findSysUserById(List<Integer> oidList) throws Exception ;

	public void saveSysUser(SysUser sysUser, SysUser user);

	public void changeDelFlg(SysUser sysUser, List<Integer> oidList, int delFlg);

	public void saveUserOptInfo(SysUser user,HttpServletRequest request);

	public boolean changePassword(SysUserQuery query, SysUser sysUser);

	public void resetPassword(int[] oids);

}
