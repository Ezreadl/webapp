package com.qm.nms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qm.core.base.IBaseDao;
import com.qm.nms.domain.WorkRest;

@Repository
public interface IWorkRestDao extends IBaseDao<WorkRest,Integer>{

	@Query(value="select * from WorkRest where schoolID=?1 and name like '%第%' ",nativeQuery = true)
	public List<WorkRest> findByOrgan(int schoolID);
}
