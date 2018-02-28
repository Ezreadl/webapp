package com.qm.nms.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qm.core.base.IBaseDao;
import com.qm.nms.domain.UsageGoal;
import com.qm.nms.domainEnum.TermEnum;

@Repository
public interface IUsageGoalDao extends IBaseDao<UsageGoal,Integer>{

	@Query(value="select ug.* from UsageGoal ug where ug.schoolType=?1 and ug.delFlg =0 ",nativeQuery=true)	
	public UsageGoal findByType(int type) throws Exception;
	
	@Query(value="select ug.* from UsageGoal ug where ug.oid=?1 and ug.delFlg =0 ",nativeQuery=true)	
	public UsageGoal findByID(int oid) throws Exception;
	
	@Modifying
	@Query(value="update UsageGoal ug set ug.openRate=?1,ug.groupRate=?2,ug.demoRate=?3 where ug.oid=?1 and ug.delFlg =0 ",nativeQuery=true)
	public void updateGoal(float openRate,float groupRate,float demoRate);		
}
