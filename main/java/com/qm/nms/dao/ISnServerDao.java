package com.qm.nms.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qm.core.base.IBaseDao;
import com.qm.nms.domain.SnServer;
import com.qm.nms.domain.SubjectPlan;

@Repository
public interface ISnServerDao extends IBaseDao<SnServer,Integer>{
	@Query(value="select sp.* from SnServer sp {#& sp.serverTypeId= ?1 #& sp.serverState='1' order by sp.serverUpdateTime desc }",nativeQuery=true)	
	public SnServer findByType(String serverTypeId);

}
