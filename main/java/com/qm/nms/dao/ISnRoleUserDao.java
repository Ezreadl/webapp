package com.qm.nms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qm.core.base.IBaseDao;
import com.qm.nms.domain.SnRoleUser;

@Repository
public interface ISnRoleUserDao extends IBaseDao<SnRoleUser,Integer>{
	
	@Query(value="select *from SnRoleUser sn where sn.persId = ?", nativeQuery = true)
	public List<SnRoleUser> findByPersID(String persId);	

}
