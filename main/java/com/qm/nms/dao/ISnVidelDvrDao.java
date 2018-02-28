package com.qm.nms.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qm.core.base.IBaseDao;
import com.qm.nms.domain.SnVideoDvr;

@Repository
public interface ISnVidelDvrDao extends IBaseDao<SnVideoDvr,Integer>{
	@Query(value="select sp.* from SnVideoDvr sp where sp.dvrId = ?1 ",nativeQuery=true)	
	public SnVideoDvr findByType(String dvrId);

}
