package com.qm.sys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.qm.core.base.BaseDomCfg;

/**
 * 系统用户账号信息表
 * @author (wangchong)
 */
@Entity
@SuppressWarnings("serial")
public class SysUser extends BaseDomCfg{
	
	/**
	 * 用户名称，用于登录系统使用的用户账号
	 */
	@Column
	private String userName;
	/**
	 * 用户昵称，表示使用系统的用户的真实名称，用于界面提示
	 */
	@Column
	private String userNick;
	/**
	 * 用户密码，用户登录系统使用的密码(初始密码为userName+!@#000)
	 */
	@Column
	private String userPassword;
	/**
	 * 用户手机电话号码，用于接收告警短信和登录验证码，可以用分号,分隔多个号码
	 */
	@Column
	private String telephone;
	/**
	 * 用户email邮箱地址，用于用户接收告警信息
	 */
	@Column
	private String emailAddress;
	/**
	 * 用户组织id
	 */
	@Column
	private int areaid;
	/**
	 * 用户界面的列表的分页大小(默认为20条记录)
	 */
	@Column
	private int pagingSize=20;
	/**
	 * 默认的登录验证码，用于登录系统的验证码
	 */
	@Column
	private String loginCode;
	/**
	 * 用户皮肤
	 */
	@Column
	private String skinName;
	/**
	 * 用户类型标志，用于控制账号权限管理，
	 * 1表示普通用户，2表示管理员，3表示超级管理员
	 * 权限大小依次为超级管理员>管理员>普通用户降低，
	 * 低等级权限不能操作高级或同级权限账号，
	 * 例如普通用户不能新增、修改、删除管理员账号和普通用户账号，
	 * 但管理员账号可以新增、修改、删除普通用户账号，
	 * (默认为普通用户)
	 */
	@Column
	private int userType=1;
	/**
	 * 其他信息
	 */
	@Column
	private String otherInfo;
	/**
	 * 记录上一次登录系统的时间
	 */
	@Column
	private String lastLoginTime;
	/**
	 * 记录上一次登录时的IP地址
	 */
	@Column
	private String lastLoginIp;
	/**
	 * 记录当前用户登录时间
	 */
	@Column
	private String curLoginTime;
	/**
	 * 用户当前登录系统的IP地址
	 */
	@Column
	private String curLoginIp;
	/**
	 * 该字段不保存
	 */
	@Transient
	private long responseTime = System.currentTimeMillis();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getPagingSize() {
		return pagingSize;
	}

	public void setPagingSize(int pagingSize) {
		this.pagingSize = pagingSize;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public String getSkinName() {
		return skinName;
	}

	public void setSkinName(String skinName) {
		this.skinName = skinName;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getCurLoginTime() {
		return curLoginTime;
	}

	public void setCurLoginTime(String curLoginTime) {
		this.curLoginTime = curLoginTime;
	}

	public String getCurLoginIp() {
		return curLoginIp;
	}

	public void setCurLoginIp(String curLoginIp) {
		this.curLoginIp = curLoginIp;
	}

	public long getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}

	public boolean isAdmin(){
		if(userType>=2){
			return true;
		}else{
			return false;
		}
	}
	
	public SysUser clone(){
		SysUser user = null;
		try {
			user = (SysUser) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public String toString() {
		return "SysUser [userName=" + userName + ", userNick=" + userNick + ", userPassword=" + userPassword
				+ ", telephone=" + telephone + ", emailAddress=" + emailAddress + ", pagingSize=" + pagingSize
				+ ", loginCode=" + loginCode + ", skinName=" + skinName + ", userType=" + userType + ", lastLoginTime="
				+ lastLoginTime + ", lastLoginIp=" + lastLoginIp + ", curLoginTime=" + curLoginTime + ", curLoginIp="
				+ curLoginIp + ", responseTime=" + responseTime + ", oid=" + oid + ", optUserName=" + optUserName
				+ ", addUserName=" + addUserName + ", optDateTime=" + optDateTime + ", addDateTime=" + addDateTime
				+ ", delFlg=" + delFlg + "]";
	}

	public int getAreaid() {
		return areaid;
	}

	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}

}
