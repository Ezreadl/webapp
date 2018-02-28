package com.qm.sys.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.core.util.CipherUtil;
import com.qm.core.util.DataListUtil;
import com.qm.core.util.DateUtil;
import com.qm.core.util.IpAddress;
import com.qm.nms.dao.IOrganDao;
import com.qm.nms.domain.Organization;
import com.qm.sys.dao.ISysUserDao;
import com.qm.sys.domain.SysUser;
import com.qm.sys.query.SysUserQuery;
import com.qm.sys.service.ISysUserService;

@Service
public class SysUserServiceImpl implements ISysUserService {

	@Autowired
	private ISysUserDao sysUserDao;
	
	@Autowired
	private IOrganDao organDao;	
	
	@Override
	public SysUser findUser(String userName,String userPassword) throws Exception {
		 SysUser user = sysUserDao.findUserByUserName(userName);                   
		if(user != null && user.getUserPassword().equals(userPassword)){
			return user;
		}
		return null; 
	}

	@SuppressWarnings("unchecked")
	@Override
	public GridListData findUserInfo(SysUser sysUser,SysUserQuery query, PageInfo pageInfo) throws Exception {
		if(!sysUser.isAdmin()){
			query.setDelFlg(0);
		}
		query.setAreaid(sysUser.getAreaid());
		query.setOid(sysUser.getOid());
		GridListData gridListData = new GridListData();
		gridListData = sysUserDao.findDataList2(query, pageInfo);
		DataListUtil.formatDateList(gridListData.getRows(),new String[]{"addDateTime"});
		return gridListData;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public GridListData findUserList(SysUser sysUser,SysUserQuery query, PageInfo pageInfo) throws Exception {
		if(!sysUser.isAdmin()){
			query.setDelFlg(0);
		}
		pageInfo.setSort("oid");
		pageInfo.setDir("desc");
		GridListData gridListData = new GridListData();
		System.out.println("IMMIIJMMM::::::::::::::::::"+sysUser.getAreaid());
		if(sysUser.getUserType()==4){
			query.setAreaid(sysUser.getAreaid());
		}
		gridListData = sysUserDao.findDataList3(query, pageInfo);
		DataListUtil.formatDateList(gridListData.getRows(),new String[]{"addDateTime"});
		return gridListData;
	}	
	
	@Override
	public SysUser findSysUserById(int oid) {
		return sysUserDao.findOne(oid);
	}
	
	@Override
	public List<SysUser> findSysUserById(List<Integer> oidList) throws Exception{
		return sysUserDao.findAll(oidList);
	}
	public static void main(String[] args) {
		String pwd = CipherUtil.baseDecrypt("q4Q7ohKiJ/xcI9+++51hiQ==");
		System.out.println(pwd);
	}
	@Override
	public void saveSysUser(SysUser loginUser, SysUser sysUser) {
		int oid=sysUser.getOid();
		SysUser old=sysUserDao.findOne(oid);
		sysUser.updateOptInfo(old, loginUser.getUserName());
		sysUser.setOptDateTime(DateUtil.getDate());
		System.out.println("sysUser.getAreaid():J::::::::::"+sysUser.getAreaid());
		System.out.println("sysUser.getUserType():J:::::::::::"+sysUser.getUserType());
		if(old==null){
			
			String pwd = CipherUtil.baseEncrypt(sysUser.getUserName());
			if(sysUser.getAreaid()==0){
				sysUser.setAreaid(loginUser.getAreaid());
			}
			sysUser.setUserPassword(pwd);
			sysUserDao.save(sysUser);
			sysUserDao.saveUserAndRoleRelation(sysUser.getUserType(),sysUser.getOid());
			sysUserDao.saveUserAndOrganRelation(sysUser.getOid(),sysUser.getAreaid());
		}else{
			old.setUserNick(sysUser.getUserNick());
			old.setTelephone(sysUser.getTelephone());
			old.setEmailAddress(sysUser.getEmailAddress());
			old.setOtherInfo(sysUser.getOtherInfo());
			old.setOptDateTime(DateUtil.getDate());
			old.setOptUserName(loginUser.getUserName());
			old.setUserType(sysUser.getUserType());
			old.setAreaid(sysUser.getAreaid());
			sysUserDao.update(old);
			sysUserDao.deleteUserAndRoleRelation(old.getOid());
			sysUserDao.saveUserAndRoleRelation(old.getUserType(),old.getOid());
		}
	}
	public List<SysUser> findUserByType(SysUser user,int type) throws Exception{
		int areaid = user.getAreaid();
		List<SysUser> lusr = sysUserDao.findUserByOrg(areaid);
		return lusr;
	}
	@Override
	public void changeDelFlg(SysUser sysUser, List<Integer> oidList, int delFlg) {
		List<SysUser> userList=sysUserDao.findAll(oidList);
		for(SysUser user : userList){
			user.setOptUserName(sysUser.getUserName());
			user.setOptDateTime(DateUtil.getDate());
			user.setDelFlg(delFlg);
			sysUserDao.update(user);
		}
	}

	@Override
	public void saveUserOptInfo(SysUser user,HttpServletRequest request) {
		user.setLastLoginTime(user.getCurLoginTime());
		user.setLastLoginIp(user.getCurLoginIp());
		user.setCurLoginIp(IpAddress.getIpAddress(request));
		user.setCurLoginTime(DateUtil.getYmdHms());
		sysUserDao.update(user);
	}

	@Override
	public boolean changePassword(SysUserQuery query, SysUser sysUser) {
		if(query.getOldPassword().equals(query.getNewPassword())){
			return false;
		}else{
			if(sysUser.getUserPassword().equals(query.getOldPassword())){
				sysUser.setUserPassword(query.getNewPassword());
				sysUser.setOptUserName(sysUser.getUserName());
				sysUser.setOptDateTime(DateUtil.getDate());
				sysUserDao.update(sysUser);
				return true;
			}else{
				return false;
			}
		}
	}

	@Override
	@Transactional
	public void resetPassword(int[] oids) {
		for (int oid : oids) {
			SysUser sysUser = sysUserDao.findOne(oid);
			if(sysUser != null){
				sysUser.setUserPassword(sysUser.getUserName()+"!@1234");
				sysUserDao.update(sysUser);
			}
		}	
	}

	

}
