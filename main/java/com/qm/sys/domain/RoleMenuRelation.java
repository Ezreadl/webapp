package com.qm.sys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.qm.core.base.BaseDomPure;
/**
 * 角色与菜单的多对多关系表
 * @author (wangchong)
 */

@Entity
@SuppressWarnings("serial")
public class RoleMenuRelation extends BaseDomPure{
	/**
	 * 角色外键Id
	 */
	@Column
	private int roleId;
	/**
	 * 菜单外键Id
	 */
	@Column
	private int menuId;
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	
	@Override
	public String toString() {
		return "RoleMenuRelation [oid=" + oid + ", roleId=" + roleId + ", menuId=" + menuId + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}
