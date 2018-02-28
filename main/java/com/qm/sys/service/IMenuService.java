package com.qm.sys.service;

import java.util.List;
import java.util.Map;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.sys.domain.SysMenu;
import com.qm.sys.domain.SysRole;
import com.qm.sys.domain.SysUser;
import com.qm.sys.query.SysMenuQuery;
import com.qm.sys.query.SysRoleQuery;

public interface IMenuService {

	List<Object> findAllMenuTree(Integer oid);

	List<Object> findUserMenu(int oid, Integer menuId) throws Exception;

	GridListData findMenu(SysMenuQuery query, PageInfo pageInfo) throws Exception;

	void changeDelFlg(SysUser sysUser, List<Integer> oidList, int delFlg);

	SysMenu findMenuById(int oid);
	
	List<SysMenu> findMenuById(List<Integer> oidList) throws Exception;

	void saveMenu(SysUser sysUser, SysMenu menu);

	void saveRoleMenu(List<Integer> menuIdList, SysRole sysRole, SysUser sysUser);


}
