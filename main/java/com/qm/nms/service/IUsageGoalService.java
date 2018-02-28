package com.qm.nms.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qm.nms.domain.UsageGoal;
import com.qm.sys.domain.SysUser;

@Repository
public interface IUsageGoalService {

	public UsageGoal findByType( int schoolType) throws Exception;
	
	public UsageGoal findByID( int oid) throws Exception;
	
	public List<UsageGoal> findUsageGoal() throws Exception;	
	
	public void updateGoal(SysUser user,UsageGoal goal);
	
}
