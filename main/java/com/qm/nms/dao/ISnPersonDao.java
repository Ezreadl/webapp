package com.qm.nms.dao;

import org.springframework.data.jpa.repository.Query;

import com.qm.core.base.IBaseDao;
import com.qm.nms.domain.SnPerson;

public interface ISnPersonDao  extends IBaseDao<SnPerson,Integer>{
	@Query(value="select sp.* from SnPerson sp where sp.persUsername = ?1",nativeQuery=true)	
	public SnPerson findByName(String persUsername);
}
