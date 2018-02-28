package com.qm.sys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.qm.core.base.BaseDomCfg;

/**
 * 系统角色表
 * @author (wangchong)
 */

@Entity
@SuppressWarnings("serial")
public class SysRole extends BaseDomCfg{
	
	/**
	 * 角色名称
	 */
	@Column
	private String roleName;
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "SysRole [roleName=" + roleName + ", oid=" + oid + ", optUserName=" + optUserName + ", addUserName="
				+ addUserName + ", optDateTime=" + optDateTime + ", addDateTime=" + addDateTime + ", delFlg=" + delFlg
				+ "]";
	}
	
}
