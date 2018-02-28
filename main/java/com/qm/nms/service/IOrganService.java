package com.qm.nms.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.nms.domain.Organization;
import com.qm.nms.query.OrganizationQuery;
import com.qm.sys.domain.SysUser;

@Repository
public interface IOrganService {
	
	public GridListData findOrgan(OrganizationQuery query, PageInfo pageInfo) throws Exception;

	public List<Organization> findByUserAndType(int uid,int type,PageInfo page) throws Exception;
	
	public List<Map<String, Object>> findTeacherNum(SysUser user, int typeVal, PageInfo pageInfo) throws Exception;
	
	public List<Organization> findByIDUserAndType(int oid,int uid,int type,PageInfo page) throws Exception;
	
	public Map<String,Object> findMyOrgan(int oid,int typeVal,PageInfo page) throws Exception ;
	
	public List<Organization> findLib(SysUser usr, int typeVal, PageInfo page) throws Exception;
	
	public List<Map<String,Object>> findMyLib(SysUser user,int typeVal,PageInfo page) throws Exception;
	
	public List<Map<String, Object>> findMyLibGroup(SysUser user, int typeVal, PageInfo page) throws Exception;
	
	public int findMyLibCount(SysUser user, int typeVal, PageInfo page) throws Exception;
	
	public List<Map<String, Object>> findTypeCount(SysUser user, int typeVal, PageInfo pageInfo) throws Exception;	
	
	public Organization findByID(int oid) throws Exception;
	
	public void saveRegion(SysUser user,Organization organ) throws Exception;	
	
	public void changeDelFlg(SysUser user,List<Integer> oidList,int delFlg) throws Exception;

	public List<Map<String, Object>> findByUser(SysUser user, PageInfo pageInfo) throws Exception;
	
}