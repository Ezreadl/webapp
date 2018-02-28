package com.qm.sys.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qm.core.base.GridListData;
import com.qm.core.base.IBaseDao;
import com.qm.core.base.IQuery;
import com.qm.core.base.PageInfo;
import com.qm.core.base.QmQuery;
import com.qm.sys.domain.SysMenu;

public interface ISysMenuDao extends IBaseDao<SysMenu, Integer> {
	
	@Query(value="select m.* from SysMenu m",nativeQuery=true)
	List<SysMenu> findAllMenuList();
	
	@Query(value="select m.* from RoleMenuRelation rm left join SysMenu m on(rm.menuId = m.oid) where rm.roleId = ?1",nativeQuery=true)
	List<SysMenu> findRoleAllMenuId(Integer roleId);

	@Modifying
	@Query(value="delete from RoleMenuRelation where roleId = :roleId",nativeQuery=true)
	int deleteAllRoleMenuRelation(@Param("roleId")int roleId);
 
	@Modifying
	@Query(value="insert into RoleMenuRelation(roleId,menuId) values(:roleId,:menuId)",nativeQuery=true)
	void saveRoleMenuRelation(@Param("menuId")int i,@Param("roleId") int roleId);

	@Query(value="select distinct m.* from RoleMenuRelation rm LEFT JOIN SysMenu m on m.oid = rm.menuId where rm.roleid=:roleid and m.pMenuId = :pMenuId and m.delFlg != 1",nativeQuery=true)
	List<SysMenu> findUserMenuList(@Param("roleid") int roleid,@Param("pMenuId")Integer pmenuId) throws Exception;

	@Query(value="select m.* from SysMenu m where m.pMenuId = :oid",nativeQuery=true)
	List<SysMenu> findMenuByPMenuId(@Param("oid")int oid);

	@QmQuery(value="select m.* from SysMenu m {#& m.pMenuId = :oid #& m.menuName like %:menuName%}",nativeQuery=true)
	GridListData findDataList1(IQuery query,PageInfo pageInfp);
	
}
