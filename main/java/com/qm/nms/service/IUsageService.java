package com.qm.nms.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qm.core.base.PageInfo;
import com.qm.core.base.WebStatus4P;
import com.qm.nms.domain.UsageGoal;
import com.qm.sys.domain.SysUser;

@Repository
public interface IUsageService {

	public List<Map<String,Object>> findByType(int schoolType,PageInfo pageInfo) throws Exception;
	
	public List<Map<String, Object>> findMyLabUsage(SysUser user,PageInfo pageInfo) throws Exception;	
	
	public List<Map<String, Object>> findRateRank(SysUser user, PageInfo pageInfo) throws Exception;
	
	public List<Map<String,Object>> findUsageGoal() throws Exception;	
	
	public void updateGoal(SysUser user,UsageGoal goal);
	
}
