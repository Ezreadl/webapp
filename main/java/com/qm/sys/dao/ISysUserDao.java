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
import com.qm.sys.domain.SysUser;

public interface ISysUserDao extends IBaseDao<SysUser, Integer> {
	
	@Query(value="from SysUser u where u.userName =?1 and u.delFlg = 0")
	SysUser findUserByUserName(@Param("userName")String userName) throws Exception;

	@Query(value="from SysUser u where u.areaid =?1 and u.userType=7 and u.delFlg = 0")
	List<SysUser> findUserByOrg(int areaid) throws Exception;	
	
	@QmQuery(value="select DISTINCT usr.*,org.organName,role.roleName from userorganrelation uorg"
			+ "left join organization org on uorg.organid=org.oid " 
			+ "left join sysuser usr on uorg.userid=usr.oid "
			+ "{ #& org.organName=:organName #& usr.areaid=:areaid #& usr.oid=:oid #& usr.delFlg=:delFlg }",nativeQuery=true)
	GridListData findDataList1(IQuery query,PageInfo pageInfo) throws Exception;
	
	@QmQuery(value="select group_concat(role.roleName)as roleName,urr.roleid,usr.* from userrolerelation urr " 
			+ "left join sysuser usr on urr.userId=usr.oid "
			+ "left join SysRole role on urr.roleId = role.oid{ #& usr.areaid=:areaid #& usr.delFlg=:delFlg "
			+ "#& urr.roleid = :roleid #& urr.userId = :oid }",nativeQuery=true)
	GridListData findDataList2(IQuery query,PageInfo pageInfo) throws Exception;
	
	@QmQuery(value="select org.organName,role.roleName,urr.roleid,usr.* from userrolerelation urr " 
			+ "left join sysuser usr on urr.userId=usr.oid "
			+ "left join SysRole role on urr.roleId = role.oid "
			+ "left join organization org on usr.areaid=org.oid "
			+ "{ #& usr.areaid =:areaid #& usr.delFlg= :delFlg "
			+ "#& urr.roleid = :roleid #& urr.userId = :oid }",nativeQuery=true)
	GridListData findDataList3(IQuery query,PageInfo pageInfo) throws Exception;
	
	@Modifying
	@Query(value="insert into UserRoleRelation(roleId,userId) values(:roleId,:userId)",nativeQuery=true)
	void saveUserAndRoleRelation(@Param("roleId")int userType,@Param("userId") int oid);
	
	@Modifying
	@Query(value="insert into userorganrelation(userId,organId) values(:userId,:organId)",nativeQuery=true)
	void saveUserAndOrganRelation(@Param("userId")int userId,@Param("organId") int organId);
	
	@Modifying
	@Query(value="delete from UserRoleRelation where userId = :userId",nativeQuery=true)
	void deleteUserAndRoleRelation(@Param("userId")int oid);

}
