package com.qm.sys.domain;

import java.util.Date;

/**
 * 登录错误的用户信息，用于记录用户联系错误登录次数，
 * 该对象数据缓存在内存中，不持久化到数据库
 * @author wangchong
 *
 */
public class LoginErrorTempUser {
	
	private String userName;
	
	private String password;
	
	private String netCode;
	
	private String loginIp;
	
	private Date lastLoginTime;
	
	private Date curLoginTime;
	
	private int loginErrorCount;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNetCode() {
		return netCode;
	}

	public void setNetCode(String netCode) {
		this.netCode = netCode;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getCurLoginTime() {
		return curLoginTime;
	}

	public void setCurLoginTime(Date curLoginTime) {
		this.curLoginTime = curLoginTime;
	}

	public int getLoginErrorCount() {
		return loginErrorCount;
	}

	public void setLoginErrorCount(int loginErrorCount) {
		this.loginErrorCount = loginErrorCount;
	}

	@Override
	public String toString() {
		return "LoginErrorTempUser [userName=" + userName + ", password=" + password + ", netCode=" + netCode
				+ ", loginIp=" + loginIp + ", lastLoginTime=" + lastLoginTime + ", curLoginTime=" + curLoginTime
				+ ", loginErrorCount=" + loginErrorCount + "]";
	}
}
