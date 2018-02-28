package com.qm.nms.query;

import com.qm.core.base.IQuery;

/**
 * 教学目录
 * 
 * @author lenovo
 *
 */
public class TeachContentQuery implements IQuery {
	/**
	 * 教学目录名称
	 */
	private String contName;
	/**
	 * 目录版本
	 */
	private String contVersion;
	/**
	 * 适用学校类型
	 */
	private String fitType;

	public String getContName() {
		return contName;
	}

	public String getContVersion() {
		return contVersion;
	}

	public String getFitType() {
		return fitType;
	}

	public void setContName(String contName) {
		this.contName = contName;
	}

	public void setContVersion(String contVersion) {
		this.contVersion = contVersion;
	}

	public void setFitType(String fitType) {
		this.fitType = fitType;
	}
}
