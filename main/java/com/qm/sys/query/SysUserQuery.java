package com.qm.sys.query;

import com.qm.core.base.IQuery;

public class SysUserQuery implements IQuery{
	
	private String oids;

	private Integer areaid;	
	
	private Integer roleid;		
	
	private String userName;
	
	private String oldPassword;
	
	private String newPassword;
	
	private String userNick;
	
	private String telephone;
	
	private String emailAddress;
	
	private Integer userType;
	
	private Integer delFlg=0;
	
	private String timeStart;
	
	private String timeEnd;
	
	private Integer oid;

	public String getOids() {
		return oids;
	}


	public void setOids(String oids) {
		this.oids = oids;
	}


	public String getOldPassword() {
		return oldPassword;
	}


	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}


	public String getNewPassword() {
		return newPassword;
	}


	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


	public String getUserNick() {
		return userNick;
	}


	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	public Integer getUserType() {
		return userType;
	}


	public void setUserType(Integer userType) {
		this.userType = userType;
	}


	public Integer getDelFlg() {
		return delFlg;
	}


	public void setDelFlg(Integer delFlg) {
		this.delFlg = delFlg;
	}


	public String getTimeStart() {
		return timeStart;
	}


	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}


	public String getTimeEnd() {
		return timeEnd;
	}


	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Integer getOid() {
		return oid;
	}


	public void setOid(Integer oid) {
		this.oid = oid;
	}


	public Integer getAreaid() {
		return areaid;
	}


	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}


	public Integer getRoleid() {
		return roleid;
	}


	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

}