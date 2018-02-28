package com.qm.nms.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qm.core.base.PageInfo;
import com.qm.core.base.WebStatus4P;
import com.qm.nms.domain.UsageGoal;
import com.qm.nms.domain.WorkRest;
import com.qm.sys.domain.SysUser;

@Repository
public interface IWorkRestService {

	public List<WorkRest> findSchoolWorkOfUser(SysUser user) throws Exception;
	
}
