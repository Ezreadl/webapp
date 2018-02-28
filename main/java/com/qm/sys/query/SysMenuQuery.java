package com.qm.sys.query;

import com.qm.core.base.IQuery;

public class SysMenuQuery implements IQuery {
	
	private Integer oid;

	private Integer pMenuId;
	
	private String menuName;

	public Integer getpMenuId() {
		return pMenuId;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public void setpMenuId(Integer pMenuId) {
		this.pMenuId = pMenuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	
}
