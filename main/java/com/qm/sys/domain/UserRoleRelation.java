package com.qm.sys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.qm.core.base.BaseDomPure;

@Entity
@SuppressWarnings("serial")
public class UserRoleRelation extends BaseDomPure{
	/**
	 * 用户外键Id
	 */
	@Column
	private int userId;
	/**
	 * 角色外键Id
	 */
	@Column
	private int roleId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	@Override
	public String toString() {
		return "UserRoleRelation [userId=" + userId + ", roleId=" + roleId + ", oid=" + oid + "]";
	}
	
}
