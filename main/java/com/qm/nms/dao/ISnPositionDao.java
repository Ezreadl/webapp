package com.qm.nms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.qm.core.base.IBaseDao;
import com.qm.nms.domain.SnPosition;

public interface ISnPositionDao  extends IBaseDao<SnPosition, Integer> {
	
	@Query(value="select *from SnPosition sp where sp.parentPosiId in ( :posiIds )", nativeQuery = true)
	public List<SnPosition> findByIds(List<String> posiIds);
}
