package com.qm.sys.service;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.sys.domain.SysUser;
import com.qm.sys.query.SysOptLogQuery;

public interface ISysOptLogService {

	GridListData findSysOptLogs(SysOptLogQuery query, PageInfo pageInfo) throws Exception;

	void saveSysOptLog(SysUser sysUser, SysOptLogQuery query);

}
