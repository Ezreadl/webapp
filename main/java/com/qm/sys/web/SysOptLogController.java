package com.qm.sys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.core.util.DateUtil;
import com.qm.core.util.StringUtil;
import com.qm.sys.query.SysOptLogQuery;
import com.qm.sys.service.ISysOptLogService;

/**
 * 
 * 
 * @author xiaoze
 * @date 
 */
@Controller
@RequestMapping("/SysOptLog")
public class SysOptLogController {
	
	@Autowired
	private ISysOptLogService sysOptLogService;
	
	/*
	 * 查系统访问日志
	 */
	@RequestMapping("/findSysOptLogs")
	@ResponseBody
	public GridListData findSysOptLogs(SysOptLogQuery query,PageInfo pageInfo) throws Exception{
		if (StringUtil.isBlank(query.getTimeEnd())) {
			query.setTimeEnd(DateUtil.getYmd() + " 23:59:59");
		}
		return sysOptLogService.findSysOptLogs(query,pageInfo);
	}
}
