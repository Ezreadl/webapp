package com.qm.nms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qm.nms.dao.IWorkRestDao;
import com.qm.nms.domain.WorkRest;
import com.qm.nms.service.IWorkRestService;
import com.qm.sys.domain.SysUser;
@Service
public class WorkRestServiceImpl implements IWorkRestService{

	@Autowired
	private IWorkRestDao workDao;
	@Override
	public List<WorkRest> findSchoolWorkOfUser(SysUser user) throws Exception {
		// TODO Auto-generated method stub
		int schoolID = user.getAreaid();
		List<WorkRest> lwork = workDao.findByOrgan(schoolID);
		return lwork;
	}
	
}
