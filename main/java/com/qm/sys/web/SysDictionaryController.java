package com.qm.sys.web;

import java.util.List;
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
import com.qm.core.util.ConvertUtil;
import com.qm.core.util.DateUtil;
import com.qm.core.util.ExcelFileOprator;
import com.qm.sys.domain.SysDictionary;
import com.qm.sys.domain.SysUser;
import com.qm.sys.query.SysDictionaryQuery;
import com.qm.sys.service.ISysDictionaryService;

@Controller
@RequestMapping("/SysDictionary")
public class SysDictionaryController {

	@Autowired
	ISysDictionaryService sysDictionaryService;

	@RequestMapping("/gridList")
	@ResponseBody
	public GridListData gridList(SysDictionaryQuery query,PageInfo pageInfo,
			@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser sysUser) throws Exception{
		return sysDictionaryService.findSysDictionaryList(sysUser, query, pageInfo);
	}

	@RequestMapping("/findById")
	@ResponseBody
	public SysDictionary findById(int oid) throws Exception{
		return sysDictionaryService.findSysDictionaryById(oid);
	}

	@RequestMapping("/save")
	@ResponseBody
	public WebStatus save(SysDictionary sysDictionary,
			@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser sysUser) throws Exception{
		sysDictionaryService.saveSysDictionary(sysUser, sysDictionary);
		return new WebStatus();
	}

	@RequestMapping("/changeDelFlg")
	@ResponseBody
	public WebStatus changeDelFlg(String oids,int delFlg,
			@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser sysUser) throws Exception{
		List<Integer> oidList=ConvertUtil.convertStr2IntegerList(oids);
		sysDictionaryService.changeDelFlg(sysUser, oidList, delFlg);
		return new WebStatus();
	}

}