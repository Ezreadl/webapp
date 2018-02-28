package com.qm.nms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.qm.core.base.BaseDomPure;

@Entity
@SuppressWarnings("serial")
public class UserOrganRelation extends BaseDomPure{
	/**
	 * 用户外键Id
	 */
	@Column
	private int userId;
	/**
	 * 角色外键Id
	 */
	@Column
	private int organId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getOrganId() {
		return organId;
	}
	public void setOrganId(int organId) {
		this.organId = organId;
	}
	
	@Override
	public String toString() {
		return "UserRoleRelation [userId=" + userId + ", organId=" + organId + ", oid=" + oid + "]";
	}
	
}
