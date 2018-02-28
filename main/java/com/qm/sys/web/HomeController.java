package com.qm.sys.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.qm.core.base.GlobalCfg;
import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.core.base.WebStatus;
import com.qm.core.util.ConvertUtil;
import com.qm.core.util.DateUtil;
import com.qm.core.util.ExcelFileOprator;
import com.qm.core.util.StringUtil;
import com.qm.nms.query.PingTargetQuery;
import com.qm.sys.domain.SysMenu;
import com.qm.sys.domain.SysRole;
import com.qm.sys.domain.SysUser;
import com.qm.sys.query.SysMenuQuery;
import com.qm.sys.query.SysRoleQuery;
import com.qm.sys.service.IMenuService;
import com.qm.sys.service.IRoleService;

/**
 * 
 * @author xiaoze 2017年8月25日下午8:22:34
 */

@Controller
@RequestMapping("/Manage")
public class HomeController {

	@Autowired
	private IMenuService menuService;

	@Autowired
	private IRoleService roleService;

	/**
	 * 查找用户所有权限树
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findUserMenu")
	@ResponseBody
	public GridListData findUserMenu(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY)SysUser user) throws Exception {
		GridListData gridListData = new GridListData();
		List<Object> list = menuService.findUserMenu(user.getUserType(), 0);
		System.out.println(list.toString());
		gridListData.setRows(list);
		gridListData.setTotal(list.size());
		return gridListData;
	}

	/*
	 * 返回用户名字
	 */
	@RequestMapping("/findUserNick")
	@ResponseBody
	public String findUserNick(HttpSession session) throws Exception {
		SysUser user = (SysUser) session.getAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY);
		return "{\"name\":\"" + user.getUserNick() + "\",\"oid\":"+user.getOid()+"}";
	}

	/**
	 * 查找所有菜单树
	 * 
	 * @return
	 */
	@RequestMapping("/findAllMenuTree")
	@ResponseBody
	public List<Object> findAllMenuTree() {
		return menuService.findAllMenuTree(null);
	}

	/**
	 * 按条件查找菜单
	 * 
	 * @param query
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findMenu")
	@ResponseBody
	public GridListData findMenu(SysMenuQuery query, PageInfo pageInfo) throws Exception {
		return menuService.findMenu(query, pageInfo);
	}

	/**
	 * 查找角色的所有权限ID
	 * 
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/findRoleAllMenuId")
	@ResponseBody
	public List<Object> findRoleAllMenuId(Integer oid) {
		return menuService.findAllMenuTree(oid);
	}

	/**
	 * 查找所有角色
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findAllRole")
	@ResponseBody
	public GridListData findAllRole(SysRoleQuery query, PageInfo pageInfo) throws Exception {
		return roleService.findAllRole(query, pageInfo);
	}

	/**
	 * 根据id查找角色
	 * 
	 * @param oid
	 * @param isCopy
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findRoleById")
	@ResponseBody
	public WebStatus findRoleById(int oid, int isCopy) throws Exception {
		SysRole sysRole = roleService.findRoleById(oid);
		if (sysRole == null) {
			sysRole = new SysRole();// 新增
			sysRole.setOid(0);
		}
		if (isCopy == 1) {
			sysRole.setOid(0);// 复制
		}
		return new WebStatus(sysRole);
	}

	/**
	 * 保存角色与权限的关系
	 * 
	 * @param menuId
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/saveRoleMenu")
	@ResponseBody
	public WebStatus saveRoleMenu(String menuIds, SysRole sysRole,
			@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser sysUser) {
		List<Integer> menuIdList = ConvertUtil.convertStr2IntegerList(menuIds);
		menuService.saveRoleMenu(menuIdList, sysRole, sysUser);
		return new WebStatus();
	}

	@RequestMapping("/changeRoleDelFlg")
	@ResponseBody
	public WebStatus changeRoleDelFlg(String oids, int delFlg,
			@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser sysUser) throws Exception {
		List<Integer> oidList = ConvertUtil.convertStr2IntegerList(oids);
		roleService.changeDelFlg(sysUser, oidList, delFlg);
		return new WebStatus();
	}

	/**
	 * 修改菜单状态
	 * 
	 * @param oids
	 * @param delFlg
	 * @param sysUser
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/changeMenuDelFlg")
	@ResponseBody
	public WebStatus changeMenuDelFlg(String oids, int delFlg,
			@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser sysUser) throws Exception {
		List<Integer> oidList = ConvertUtil.convertStr2IntegerList(oids);
		menuService.changeDelFlg(sysUser, oidList, delFlg);
		return new WebStatus();
	}

	/**
	 * 根据ID查菜单
	 * 
	 * @param oid
	 * @param isCopy
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findMenuById")
	@ResponseBody
	public WebStatus findMenuById(int oid, int isCopy) throws Exception {
		SysMenu sysMenu = menuService.findMenuById(oid);
		if (sysMenu == null) {
			sysMenu = new SysMenu();// 新增
			sysMenu.setOid(0);
		}
		if (isCopy == 1) {
			sysMenu.setOid(0);// 复制
		}
		return new WebStatus(sysMenu);
	}

	/**
	 * 保存菜单
	 * 
	 * @param menu
	 * @param sysUser
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveMenu")
	@ResponseBody
	public WebStatus saveMenu(SysMenu sysMenu, @SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser sysUser)
			throws Exception {
		menuService.saveMenu(sysUser, sysMenu);
		return new WebStatus();
	}

	@RequestMapping("/exportMenuDataList")
	public void exportMenuDataList(boolean isIE,PingTargetQuery query,
			HttpServletResponse response) throws Exception{
		
		String[] titleArr={"ID","菜单名称","动作类型","js动作/URL链接","图标icon","父菜单ID","是否展开","添加人员","操作人员","添加时间","操作时间","删除状态"};
		String[] keyNameArr={"oid","menuName","actionType","actionClass","iconCls","pMenuId","expanded","addUserName","optUserName","addDateTime","optDateTime","delFlg"};
		if(StringUtil.isNotBlank(query.getOids())){
			List<Integer> oids = ConvertUtil.convertStr2IntegerList(query.getOids());
			List<SysMenu> sysMenus = menuService.findMenuById(oids);
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			for (SysMenu sysMenu : sysMenus) {
				Map<String,Object> map = new HashMap<>();
				map.put("oid", sysMenu.getOid());
				map.put("menuName", sysMenu.getMenuName());
				map.put("actionType", sysMenu.getActionType()==0?"JS动作":"URL地址");
				map.put("actionClass", sysMenu.getActionClass());
				map.put("iconCls", sysMenu.getIconCls());
				map.put("pMenuId", sysMenu.getpMenuId());
				map.put("expanded", sysMenu.getExpanded()==0?"否":"是");
				map.put("addUserName", sysMenu.getAddUserName());
				map.put("optUserName", sysMenu.getOptUserName());
				map.put("addDateTime", sysMenu.getAddDateTime());
				map.put("optDateTime", sysMenu.getOptDateTime());
				map.put("delFlg", sysMenu.getDelFlg());
				list.add(map);
			}
			List<String[]> dataList = ConvertUtil.convertListMap2ListStringArr(list, keyNameArr);
			String downName = "菜单数据导出" + DateUtil.getyyyyMMddHHmmssDate()+ ".xlsx";
			ServletOutputStream out = response.getOutputStream();
			response.reset();
			response.setContentType("application/x-msdownload");
			if (isIE) {
				downName = java.net.URLEncoder.encode(downName, "UTF-8");
			}
			response.setHeader("Content-Disposition", "attachment;filename=\""+ new String(downName.getBytes("UTF-8"), "ISO8859-1") + "\"");
			ExcelFileOprator excel = new ExcelFileOprator();
			excel.writeData(titleArr, dataList, 0);
			excel.transmit(out);
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping("/exportRoleDataList")
	public void exportRoleDataList(boolean isIE,PingTargetQuery query,
			HttpServletResponse response) throws Exception{
		
		String[] titleArr={"ID","角色名称","操作人员","添加人员","操作时间","添加时间","删除状态"};
		String[] keyNameArr={"oid","roleName","optUserName","addUserName","optDateTime","addDateTime","delFlg"};
		if(StringUtil.isNotBlank(query.getOids())){
			List<Integer> oids = ConvertUtil.convertStr2IntegerList(query.getOids());
			List<SysRole> sysRoles = roleService.findRoleById(oids);
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			for (SysRole sysRole : sysRoles) {
				Map<String,Object> map = new HashMap<>();
				map.put("oid", sysRole.getOid());
				map.put("roleName", sysRole.getRoleName());
				map.put("addUserName", sysRole.getAddUserName());
				map.put("optUserName", sysRole.getOptUserName());
				map.put("addDateTime", sysRole.getAddDateTime());
				map.put("optDateTime", sysRole.getOptDateTime());
				map.put("delFlg", sysRole.getDelFlg());
				list.add(map);
			}
			List<String[]> dataList = ConvertUtil.convertListMap2ListStringArr(list, keyNameArr);
			String downName = "角色数据导出" + DateUtil.getyyyyMMddHHmmssDate()+ ".xlsx";
			ServletOutputStream out = response.getOutputStream();
			response.reset();
			response.setContentType("application/x-msdownload");
			if (isIE) {
				downName = java.net.URLEncoder.encode(downName, "UTF-8");
			}
			response.setHeader("Content-Disposition", "attachment;filename=\""+ new String(downName.getBytes("UTF-8"), "ISO8859-1") + "\"");
			ExcelFileOprator excel = new ExcelFileOprator();
			excel.writeData(titleArr, dataList, 0);
			excel.transmit(out);
			out.flush();
			out.close();
		}
	}
	@RequestMapping("/goHome")
	public String goHomePage(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser user) throws Exception {	
		return roleService.findPage(user);

	}
}
