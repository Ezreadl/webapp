package com.qm.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.core.util.DataListUtil;
import com.qm.core.util.DateUtil;
import com.qm.sys.dao.ISysRoleDao;
import com.qm.sys.domain.SysRole;
import com.qm.sys.domain.SysUser;
import com.qm.sys.query.SysRoleQuery;
import com.qm.sys.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {
	
	@Autowired
	private ISysRoleDao sysRoleDao;

	@SuppressWarnings("unchecked")
	@Override
	public GridListData findAllRole(SysRoleQuery query, PageInfo pageInfo) throws Exception {
		GridListData gridListData = sysRoleDao.findDataList1(query,pageInfo);
		DataListUtil.formatDateList(gridListData.getRows(), new String[]{"optDateTime","addDateTime"});
		return gridListData;
	}

	@Override
	public SysRole findRoleById(int oid) {
		return sysRoleDao.findOne(oid);
	}
	
	@Override
	public List<SysRole> findRoleById(List<Integer> oidList) {
		return sysRoleDao.findAll(oidList);
	}
	
	@Override
	public List<SysRole> findLowRole(int oid) {
		return sysRoleDao.findLowRole(oid);
	}

	
	@Override
	public void changeDelFlg(SysUser sysUser, List<Integer> oidList, int delFlg) {
		List<SysRole> roleList=sysRoleDao.findAll(oidList);
		for(SysRole role : roleList){
			role.setOptUserName(sysUser.getUserName());
			role.setOptDateTime(DateUtil.getDate());
			role.setDelFlg(delFlg);
			sysRoleDao.update(role);
		}
	}

	@Override
	public void saveRole(SysRole role,SysUser loginUser) {
		String optUserName = loginUser.getUserName();
		Date optDateTime = new Date();
		int oid = role.getOid();
		role.setOptUserName(optUserName);
		role.setOptDateTime(optDateTime); 
		SysRole old = sysRoleDao.findOne(oid);
		if(old==null){
			role.setAddDateTime(optDateTime);
			role.setAddUserName(optUserName);
			sysRoleDao.save(role);
		}else{
			sysRoleDao.update(role);
		}
	}	
	
	@Override
	public String findPage(SysUser user) {
		// TODO Auto-generated method stub
		List<SysRole> lrole = sysRoleDao.findRoleByID(user.getOid());
		int roid = 100;
		String roleName = "";		
		for (SysRole role : lrole) {
			if(role.getOid()<roid){
				roid = role.getOid();
				roleName = role.getRoleName();
			}
		}
		if("教师".equals(roleName)){
			return "teacher/myCourse";
		}else if("实验员".equals(roleName)){
			return "labPeople/orderList";
		}else if("学校管理员".equals(roleName)){
			return "schoolAdmin/homePage";
		}
		return "disEduBur/homePage";
	}
}
