package com.qm.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.core.util.DataListUtil;
import com.qm.sys.dao.ISysOptLogDao;
import com.qm.sys.domain.SysOptLog;
import com.qm.sys.domain.SysUser;
import com.qm.sys.query.SysOptLogQuery;
import com.qm.sys.service.ISysOptLogService;

@Service
public class SysOptLogServiceImpl implements ISysOptLogService {
	
	@Autowired
	private ISysOptLogDao sysOptLogDao;

	@SuppressWarnings("unchecked")
	@Override
	public GridListData findSysOptLogs(SysOptLogQuery query, PageInfo pageInfo) throws Exception {
			GridListData gridListData = sysOptLogDao.findDataList1(query, pageInfo);
			DataListUtil.formatDateList(gridListData.getRows(), new String[]{"optTime"});
			return gridListData;
	}

	@Override
	@Transactional
	public void saveSysOptLog(SysUser sysUser,SysOptLogQuery query){
		if(query.getOptType() != null){
			SysOptLog sysOptLog = new SysOptLog();
			int optType = query.getOptType();
			if(optType == 1){//等于1为登陆  2为修改密码
				sysOptLog.setIpAddress(sysUser.getCurLoginIp());
				sysOptLog.setOptDesc("用户登录");
				sysOptLog.setOptUserId(sysUser.getOid());
				sysOptLog.setOptUserNick(sysUser.getUserNick());
			}else if(optType == 2){
				sysOptLog.setIpAddress(sysUser.getCurLoginIp());
				sysOptLog.setOptDesc("用户修改密码");
				sysOptLog.setOptUserId(sysUser.getOid());
				sysOptLog.setOptUserNick(sysUser.getUserNick());
			}
			sysOptLogDao.save(sysOptLog);
		}
	}
}
