package com.qm.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.core.util.DataListUtil;
import com.qm.core.util.DateUtil;
import com.qm.sys.dao.ISysMenuDao;
import com.qm.sys.dao.ISysRoleDao;
import com.qm.sys.domain.SysMenu;
import com.qm.sys.domain.SysRole;
import com.qm.sys.domain.SysUser;
import com.qm.sys.query.SysMenuQuery;
import com.qm.sys.query.SysRoleQuery;
import com.qm.sys.service.IMenuService;

@Service
public class MenuServiceImpl implements IMenuService {
	
	@Autowired
	private ISysMenuDao sysMenuDao;
	
	@Autowired
	private ISysRoleDao sysRoleDao;

	@Override
	public List<Object> findAllMenuTree(Integer roleId) {
		List<SysMenu> roleAllMenus = sysMenuDao.findRoleAllMenuId(roleId);
		List<SysMenu> menuList = sysMenuDao.findAllMenuList();
		List<Object> list = new ArrayList<>();
		Map<String,Object> firstMap = new HashMap<>();
		list.add(firstMap);
		firstMap.put("text", "菜单树");
		firstMap.put("oid", 0);
		List<Object> secList = new ArrayList<>();
		firstMap.put("expanded", 0);
		firstMap.put("children", secList);
		for (SysMenu sysMenu : menuList) {
			if(sysMenu.getpMenuId() == 0){
				Map<String,Object> secondMap = new HashMap<>();
				secondMap.put("oid", sysMenu.getOid());
				secondMap.put("text", sysMenu.getMenuName());
				secondMap.put("status", sysMenu.getDelFlg());
				secondMap.put("expanded", sysMenu.getExpanded());
				if(roleAllMenus != null && roleAllMenus.contains(sysMenu)){
					secondMap.put("ischecked", true);
				}
				List<Object> findSubMenu = findSubMenu(sysMenu.getOid(),roleAllMenus);
				if(findSubMenu != null && findSubMenu.size() > 0){
					secondMap.put("children", findSubMenu);
				}
				secList.add(secondMap);
			}
		}	
		return list; 
	}

	private List<Object> findSubMenu(int oid, List<SysMenu> roleAllMenus) {
		List<Object> list = new ArrayList<>();
		List<SysMenu> menuList = sysMenuDao.findMenuByPMenuId(oid);
		for (SysMenu sysMenu : menuList) {
			Map<String,Object> thridMap = new HashMap<>();
			thridMap.put("oid", sysMenu.getOid());
			thridMap.put("text", sysMenu.getMenuName());
			thridMap.put("status", sysMenu.getDelFlg());
			thridMap.put("expanded", sysMenu.getExpanded());
			if(roleAllMenus != null && roleAllMenus.contains(sysMenu)){
				thridMap.put("ischecked", true);
			}
			List<Object> findSubMenu = findSubMenu(sysMenu.getOid(),roleAllMenus);
			if(findSubMenu != null && findSubMenu.size() > 0){
				thridMap.put("children", findSubMenu);
			}
			list.add(thridMap);
		}
		return list;
	}

	@Override
	@Transactional
	public void saveRoleMenu(List<Integer> menuIdList, SysRole sysRole,SysUser sysUser) {
		int oid = sysRole.getOid();
		SysRole old=sysRoleDao.findOne(oid);
		sysRole.updateOptInfo(old, sysUser.getUserName());
		sysRole.setOptDateTime(DateUtil.getDate());
		if(old==null){
			sysRole = sysRoleDao.save(sysRole);
		}else{
			sysRoleDao.update(sysRole);
			sysMenuDao.deleteAllRoleMenuRelation(sysRole.getOid());
		}
		for (Integer i : menuIdList) {
			sysMenuDao.saveRoleMenuRelation(i.intValue(),sysRole.getOid());
		}
	}

	@Override
	@Transactional
	public List<Object> findUserMenu(int oid, Integer menuId) throws Exception {
		List<SysMenu> firstList = sysMenuDao.findUserMenuList(oid,menuId);
		List<Object> firstMenus = new ArrayList<>();
		for (SysMenu menu : firstList) {
			List<SysMenu> secondaryMenus = sysMenuDao.findUserMenuList(oid, menu.getOid());
			Map<String,Object> map = new HashMap<>();
			map.put("menuName", menu.getMenuName());
			map.put("menuID", menu.getOid());
			map.put("tabId", menu.getTabId());
			map.put("actionType", menu.getActionType());
			map.put("url", menu.getActionClass());
			map.put("icon", menu.getIconCls());
			map.put("childrenMenu", secondJson(oid,secondaryMenus));
			firstMenus.add(map);
		}
		return firstMenus;
	}
	
	@Transactional
	private List<Object> secondJson(int oid,List<SysMenu> secondaryMenus) throws Exception{
		List<Object> secondaryList = new ArrayList<>();
		if(secondaryMenus==null || secondaryMenus.size()==0){
			return null;
		}		
		for (SysMenu menu : secondaryMenus) {
			List<SysMenu> tertiaryMenus = sysMenuDao.findUserMenuList(oid, menu.getOid());
			Map<String,Object> map = new HashMap<>();
			map.put("menuName", menu.getMenuName());
			map.put("menuID", menu.getOid());
			map.put("tabId", menu.getTabId());
			map.put("actionType", menu.getActionType());
			map.put("url", menu.getActionClass());
			map.put("icon", menu.getIconCls());
			map.put("childrenMenu", secondJson(oid, tertiaryMenus));
			secondaryList.add(map);
		}
		return secondaryList;
	}
	
//	private List<Object> tertiaryJson(List<SysMenu> tertiaryMenus){
//		List<Object> tertiaryList = new ArrayList<>();
//		for (SysMenu menu : tertiaryMenus) {
//			Map<String,Object> map = new HashMap<>();
//			map.put("menuName", menu.getMenuName());
//			map.put("menuId", menu.getOid());
//			map.put("tabId", menu.getTabId());
//			map.put("actionType", menu.getActionType());
//			map.put("actionClass", menu.getActionClass());
//			map.put("iconCls", menu.getIconCls());
//			tertiaryList.add(map);
//		}
//		return tertiaryList;
//	}

	@SuppressWarnings("unchecked")
	@Override
	public GridListData findMenu(SysMenuQuery query, PageInfo pageInfo) throws Exception {
		GridListData gridListData = sysMenuDao.findDataList1(query, pageInfo);
		DataListUtil.formatDateList(gridListData.getRows(), new String[]{"addDateTime","optDateTime"});
		return gridListData;
	}

	@Override
	public void changeDelFlg(SysUser sysUser, List<Integer> oidList, int delFlg) {
		List<SysMenu> menuList=sysMenuDao.findAll(oidList);
		for(SysMenu menu : menuList){
			menu.setOptUserName(sysUser.getUserName());
			menu.setOptDateTime(DateUtil.getDate());
			menu.setDelFlg(delFlg);
			sysMenuDao.update(menu);
		}	
	}

	@Override
	public SysMenu findMenuById(int oid) {
		return sysMenuDao.findOne(oid);
	}
	
	@Override
	public List<SysMenu> findMenuById(List<Integer> oidList) {
		return sysMenuDao.findAll(oidList);
	}

	@Override
	public void saveMenu(SysUser sysUser, SysMenu SysMenu) {
		int oid=SysMenu.getOid();
		SysMenu old=sysMenuDao.findOne(oid);
		SysMenu.updateOptInfo(old, sysUser.getUserName());
		if(old==null){
			sysMenuDao.save(SysMenu);
		}else{
			sysMenuDao.update(SysMenu);
		}
	}


}
