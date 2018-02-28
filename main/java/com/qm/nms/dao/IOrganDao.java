package com.qm.nms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qm.core.base.IBaseDao;
import com.qm.core.base.PageInfo;
import com.qm.core.base.QmQuery;
import com.qm.nms.domain.Organization;

@Repository
public interface IOrganDao extends IBaseDao<Organization, Integer> {
	@Query(value = "select distinct og.* from userorganrelation uo "+
			"left join sysuser su on uo.userId=su.oid "+
			"left join Organization og on og.oid=uo.organId "+
			"where su.oid = ?1 and og.typeVal = "+
			"(select min(og.typeVal) from userorganrelation uo " +
			"left join sysuser su on uo.userId=su.oid "+
			"left join Organization og on og.oid=uo.organId " +
			"where su.oid = ?1);", nativeQuery = true)
	public Organization findOwnByUserID(int oid);

	@Query(value = "select distinct og.* from userorganrelation uo "+
			"left join sysuser su on uo.userId=su.oid "+
			"left join Organization og on og.oid=uo.organId " +
			"where su.oid=?1",nativeQuery=true)	
	public List<Organization> findByUserID(int oid);

	@Query(value = "select * from Organization par where par.organPreID = ?1 and typeVal=?2 ",nativeQuery=true)	
	public List<Organization> findSonByPreID(int oid,int typeVal);	
	
	@Query(value = "select count(*)count from Organization par where par.organPreID = ?1 and typeVal=?2",nativeQuery=true)	
	public int findCountSonByPreID(int oid,int typeVal);	
	
	@Query(value = "select * from Organization par where par.oid = ?1",nativeQuery=true)	
	public Organization findByID(int oid);
	
	@Query(value = "select * from Organization par where par.organPreID in ( select uo.organId from userorganrelation uo "
			+ "left join sysuser su on uo.userId=su.oid where su.oid=?1 ) and par.typeVal != ?2", nativeQuery = true)
	public List<Organization> findPreByUserID(int oid,int typeVal);
	
	@Query(value = "select distinct par.* from userorganrelation uo "
			+ "left join sysuser su on uo.userId=su.oid "
			+ "left join Organization par on uo.organId=par.organPreID "
			+ "where par.oid = ?1 su.oid=?2 and typeVal=?3", nativeQuery = true)
	public List<Organization> findByIDUserAndType(int oid,int uid,int type);
	
	@QmQuery(value="select distinct org.organName school,count(*)roomnum from Organization org "
			+ "left join Organization org1 on org1.organPreID=org.oid where org1.typeVal = ?1 group by school",nativeQuery=true)
	public List<Map<String,Object>> findDataList1(Object[] params,PageInfo pageInfo);
	
	@QmQuery(value="select org.organName school,count(*)teachernum from sysuser user "
			+ "left join Organization org on user.areaId = org.oid where userType = ?1 group by areaId", nativeQuery=true)
	public List<Map<String,Object>> findDataList2(Object[] params,PageInfo pageInfo);
	
	@QmQuery(value = "select userName,userNick,areaId,org.organName from SysUser user "
			+ "left join Organization org on user.areaId=org.oid where user.oid = ?1 ", nativeQuery = true)
	public List<Map<String,Object>> findDataList3(Object[] params,PageInfo pageInfo);
	
	@QmQuery(value = "select userName,userNick,org.oid areaId,org.organName from SysUser user "
			+ "left join Organization org on user.areaId=org.organPreID where user.oid = ?1 ", nativeQuery = true)
	public List<Map<String,Object>> findDataList4(Object[] params,PageInfo pageInfo);
	
	@Query(value = "select distinct par.* from userorganrelation uo "
			+ "left join sysuser su on uo.userId=su.oid "
			+ "left join Organization par on uo.organId=par.organPreID "
			+ "where su.oid=?1 and typeVal=?2", nativeQuery = true)
	public List<Organization> findByUserAndType(int uid,int type);
	
	@Query(value = "select * from Organization par where par.typeVal=?1 and oid=?2 and delFlg!=1", nativeQuery = true)
	public List<Organization> findLabCount(int typeVal,int oid);
	
	@Query(value = "select * from Organization par where par.typeVal=?1 and delFlg!=1", nativeQuery = true)
	public List<Organization> findLabCount(int typeVal);	
	
	@Query(value = "select distinct par.* from userorganrelation uo "
			+ "left join sysuser su on uo.userId=su.oid "
			+ "left join Organization par on uo.organId=par.oid "
			+ "where su.oid=?1 ", nativeQuery = true)
	public Organization findOrganByID(int oid);
	
	@Query(value = "select * from Organization par where par.organPreID =?1 and par.typeVal!=?2 and delFlg!=1", nativeQuery = true)
	public List<Organization> findPreByID(int oid,int typeVal);
	
	@Modifying
	@Query(value="insert into userorganrelation(userId,organId) values(:userId,:organId)",nativeQuery=true)
	void saveorganRelation(@Param("userId")int i,@Param("organId") int roleId);	
	
	@Modifying
	@Query(value="delete from userorganrelation where organId= ?1",nativeQuery=true)
	void delorganRelation(@Param("organId") int organId);		
}
