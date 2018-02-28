package com.qm.sys.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qm.core.base.IBaseDao;
import com.qm.sys.domain.Test;

public interface ITestDao extends IBaseDao<Test, Integer> {
	@Query(value="from Test t where t.name like :name")
	public Test findTestByName(@Param("name")String name) throws Exception;
}
