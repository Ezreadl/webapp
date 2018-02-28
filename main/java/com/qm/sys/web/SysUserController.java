package com.qm.sys.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.qm.core.base.GlobalCfg;
import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.core.base.WebStatus;
import com.qm.core.base.WebStatus4P;
import com.qm.core.util.ConvertUtil;
import com.qm.core.util.DateUtil;
import com.qm.core.util.ExcelFileOprator;
import com.qm.core.util.StringUtil;
import com.qm.sys.domain.SysUser;
import com.qm.sys.query.SysUserQuery;
import com.qm.sys.service.ISysUserService;

@Controller
@RequestMapping("/user")
public class SysUserController {

	@Autowired
	private ISysUserService userService;

	@RequestMapping("/find")
	@ResponseBody
	public WebStatus4P findUserInfo(SysUserQuery query, PageInfo pageInfo,
			@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser sysUser) throws Exception {
		GridListData gld = new GridListData();
		gld = userService.findUserList(sysUser, query, pageInfo);
		return new WebStatus4P(gld);
	}
	@RequestMapping("/findAll")
	@ResponseBody
	public WebStatus4P findUserList(SysUserQuery query, PageInfo pageInfo,
			@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser sysUser) throws Exception {
		GridListData gld = new GridListData();
		gld = userService.findUserList(sysUser, query, pageInfo);
		return new WebStatus4P(gld);
	}	
	
	//找实验员
	@RequestMapping("/findLabMan")
	@ResponseBody
	public WebStatus4P findUserByType(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser sysUser) throws Exception{
		List<Map<String,Object>> lmap = new ArrayList<Map<String,Object>>();
		List<SysUser> lusr = userService.findUserByType(sysUser, 7);
		for (SysUser usr : lusr) {
			Map<String,Object> mp = new HashMap<String,Object>();
			mp.put(usr.getUserNick(), usr.getOid());
			lmap.add(mp);
		}
		return new WebStatus4P(lmap);
	}
	@RequestMapping("/findById")
	@ResponseBody
	public WebStatus findById(int oid, int isCopy) throws Exception {
		SysUser sysUser = userService.findSysUserById(oid);
		if (sysUser == null) {
			sysUser = new SysUser();// 新增
			sysUser.setOid(0);
		}
		if (isCopy == 1) {
			sysUser.setOid(0);// 复制
		}
		return new WebStatus(sysUser);
	}

	@RequestMapping("/save")
	@ResponseBody
	public WebStatus save(SysUser sysUser, @SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser loginUser) throws Exception {
		userService.saveSysUser(loginUser, sysUser);
		return new WebStatus();
	}

	@RequestMapping("/delete")
	@ResponseBody
	public WebStatus changeDelFlg(String oids, @SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser loginUser)
			throws Exception {
		List<Integer> oidList = ConvertUtil.convertStr2IntegerList(oids);
		int delFlg = 1;
		System.out.println("delete:::::::::::::::::"+delFlg+":::::list:::::"+oidList.toString());
		try{
			userService.changeDelFlg(loginUser, oidList, delFlg);
		}catch(Exception e){
			e.printStackTrace();
			return new WebStatus(e);
		}
		return new WebStatus().success();
	}

	@RequestMapping("/resetPassword")
	@ResponseBody
	public WebStatus resetPassword(int[] oids) {
		userService.resetPassword(oids);
		return new WebStatus();
	}

	@RequestMapping("/export")
	public void exportDataList(boolean isIE, SysUserQuery query, HttpServletResponse response) throws Exception {

		String[] titleArr = { "ID", "用户账号", "用户昵称", "联系电话", "邮箱地址", "用户类型", "添加时间", "删除状态" };
		String[] keyNameArr = { "oid", "userName", "userNick", "telephone", "emailAddress", "userType", "addDateTime",
				"delFlg" };
		if (StringUtil.isNotBlank(query.getOids())) {
			List<Integer> oids = ConvertUtil.convertStr2IntegerList(query.getOids());
			List<SysUser> sysUsers = userService.findSysUserById(oids);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (SysUser sysUser : sysUsers) {
				Map<String, Object> map = new HashMap<>();
				map.put("oid", sysUser.getOid());
				map.put("userName", sysUser.getUserName());
				map.put("userNick", sysUser.getUserNick());
				map.put("telephone", sysUser.getTelephone());
				map.put("emailAddress", sysUser.getEmailAddress());
				map.put("userType",
						sysUser.getUserType() == 1 ? "普通用户"
								: sysUser.getUserType() == 2 ? "管理员"
										: sysUser.getUserType() == 3 ? "超级管理员" : sysUser.getUserType());
				map.put("addDateTime", sysUser.getAddDateTime());
				map.put("delFlg", sysUser.getDelFlg());
				list.add(map);
			}
			List<String[]> dataList = ConvertUtil.convertListMap2ListStringArr(list, keyNameArr);
			String downName = "用户数据导出" + DateUtil.getyyyyMMddHHmmssDate() + ".xlsx";
			ServletOutputStream out = response.getOutputStream();
			response.reset();
			response.setContentType("application/x-msdownload");
			if (isIE) {
				downName = java.net.URLEncoder.encode(downName, "UTF-8");
			}
			response.setHeader("Content-Disposition",
					"attachment;filename=\"" + new String(downName.getBytes("UTF-8"), "ISO8859-1") + "\"");
			ExcelFileOprator excel = new ExcelFileOprator();
			excel.writeData(titleArr, dataList, 0);
			excel.transmit(out);
			out.flush();
			out.close();
		}
	}
}
