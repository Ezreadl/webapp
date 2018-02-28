package com.qm.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.qm.core.base.GridListData;
import com.qm.core.base.IBaseDao;
import com.qm.core.base.IQuery;
import com.qm.core.base.PageInfo;
import com.qm.core.base.QmQuery;
import com.qm.sys.domain.SysRole;

public interface ISysRoleDao extends IBaseDao<SysRole, Integer> {

	@QmQuery(value="select r.* from SysRole r {#& r.roleName like %:roleName%}",nativeQuery=true)
	GridListData findDataList1(IQuery query, PageInfo pageInfo) throws Exception;
	
	@Query(value="select distinct sr.* from UserRoleRelation urr "
			+ "left join SysUser ur on urr.userId=ur.oid "
			+ "left join sysrole sr on urr.roleId= sr.oid "
			+ "where ur.oid=?",nativeQuery=true)
	List<SysRole> findRoleByID(int oid);

	@Query(value="select r.* from SysRole r where r.oid>=?1 ",nativeQuery=true)
	List<SysRole> findLowRole(int oid);
}
