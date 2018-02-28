package com.qm.sys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.qm.core.base.BaseDomCfg;

/**
 * 系统菜单表，控制菜单是否显示
 * @author (wangchong)
 */

@Entity
@SuppressWarnings("serial")
public class SysMenu extends BaseDomCfg{
	
	/**
	 * 菜单的名称
	 */
	@Column
	private String menuName="";
	/**
	 * tabId号
	 */
	@Column
	private String tabId;
	/**
	 * 动作类型，0为js，1为url地址
	 */
	@Column
	private int actionType=0;
	/**
	 * 点击菜单时执行的js动作或链接URL地址
	 */
	@Column
	private String actionClass="";
	/**
	 * 菜单左边的图标icon css样式类型
	 */
	@Column
	private String iconCls="";
	/**
	 * 同一级别菜单的排列顺序
	 */
	@Column
	private int orderNumber=1;
	/**
	 * 参照的父菜单ID号
	 */
	@Column
	private int pMenuId;
	/**
	 * 菜单是否展开0否，1是
	 */
	@Column
	private int expanded=1;
	
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getTabId() {
		return tabId;
	}

	public void setTabId(String tabId) {
		this.tabId = tabId;
	}

	public int getActionType() {
		return actionType;
	}

	public void setActionType(int actionType) {
		this.actionType = actionType;
	}

	public String getActionClass() {
		return actionClass;
	}

	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getpMenuId() {
		return pMenuId;
	}

	public void setpMenuId(int pMenuId) {
		this.pMenuId = pMenuId;
	}

	public int getExpanded() {
		return expanded;
	}

	public void setExpanded(int expanded) {
		this.expanded = expanded;
	}

	@Override
	public String toString() {
		return "SysMenu [menuName=" + menuName + ", tabId=" + tabId + ", actionType=" + actionType + ", actionClass="
				+ actionClass + ", iconCls=" + iconCls + ", orderNumber=" + orderNumber + ", pMenuId=" + pMenuId
				+ ", expanded=" + expanded + ", oid=" + oid + ", optUserName=" + optUserName + ", addUserName="
				+ addUserName + ", optDateTime=" + optDateTime + ", addDateTime=" + addDateTime + ", delFlg=" + delFlg
				+ "]";
	}
	
	public static void main(String[] args) {
		System.out.println(new SysMenu().toString());
	}
}
