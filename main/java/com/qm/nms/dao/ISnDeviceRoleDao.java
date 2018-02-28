package com.qm.nms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qm.core.base.IBaseDao;
import com.qm.nms.domain.SnDeviceRole;

@Repository
public interface ISnDeviceRoleDao extends IBaseDao<SnDeviceRole, Integer> {
	@Query(value="select par.* from SnDeviceRole par where par.roleId in ( :roleIds ) and par.nodeType= :nodeType and par.checkState= :checkState ", nativeQuery = true)
	public List<SnDeviceRole> findByIds(List<String> roleIds, String nodeType, int checkState);
}
