package com.qm.sys.query;

import com.qm.core.base.IQuery;

public class SysRoleQuery implements IQuery {
	
	private Integer oid;
	
	private String roleName;

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
