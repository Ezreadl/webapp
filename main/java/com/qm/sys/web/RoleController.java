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
import com.qm.sys.domain.SysRole;
import com.qm.sys.domain.SysUser;
import com.qm.sys.query.SysUserQuery;
import com.qm.sys.service.IRoleService;
import com.qm.sys.service.ISysUserService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private IRoleService roleService;

	@RequestMapping("/find")
	@ResponseBody
	public WebStatus4P gridList(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser usr) throws Exception {
		List<Map<String,Object>> lmap = new ArrayList<Map<String,Object>>();
		int roleid = usr.getUserType();
		List<SysRole> lrole = roleService.findLowRole(roleid);
		for (SysRole role : lrole) {
			Map<String,Object> mp = new HashMap<String,Object>();
			mp.put(role.getRoleName(), role.getOid());
			lmap.add(mp);
		}
		return new WebStatus4P(lmap);
	}

	@RequestMapping("/save")
	@ResponseBody
	public WebStatus save(SysRole role,@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser loginUser,
			int organid) throws Exception {
		roleService.saveRole(role,loginUser);
		return new WebStatus();
	}

	@RequestMapping("/delete")
	@ResponseBody
	public WebStatus changeDelFlg(String oids, @SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser loginUser)
			throws Exception {
		List<Integer> oidList = ConvertUtil.convertStr2IntegerList(oids);
		int delFlg = 1;
		roleService.changeDelFlg(loginUser, oidList, delFlg);
		return new WebStatus();
	}

}
