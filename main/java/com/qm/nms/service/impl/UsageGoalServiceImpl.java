package com.qm.nms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qm.nms.dao.IUsageGoalDao;
import com.qm.nms.domain.UsageGoal;
import com.qm.nms.service.IUsageGoalService;
import com.qm.sys.domain.SysUser;
@Service
public class UsageGoalServiceImpl implements IUsageGoalService{
	@Autowired
	public IUsageGoalDao usageGoalDao;
	@Override
	public List<UsageGoal> findUsageGoal() throws Exception{
		List<UsageGoal> goal = usageGoalDao.findAll();
		return goal;
	}
	@Override
	public UsageGoal findByType( int schoolType) throws Exception{
		UsageGoal goal = usageGoalDao.findByType(schoolType);
		return goal;
	}
	@Override
	public UsageGoal findByID( int oid) throws Exception{
		UsageGoal goal = usageGoalDao.findByID(oid);
		return goal;
	}
	
	@Override	
	public void updateGoal(SysUser user,UsageGoal goal){
		int oid = goal.getOid();
		int uid = user.getOid();
		UsageGoal old = usageGoalDao.findOne(oid);
		if (null == old){
			goal.setAddUserName(user.getUserName());
			goal.setAddDateTime(new Date());
			usageGoalDao.save(goal);
		}else {
			goal.setAddUserName(old.getAddUserName());
			goal.setAddDateTime(old.getAddDateTime());
			usageGoalDao.update(goal);
		}
		usageGoalDao.save(goal);
	}


}
