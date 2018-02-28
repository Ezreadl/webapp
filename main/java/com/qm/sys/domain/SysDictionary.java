package com.qm.sys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.qm.core.base.BaseDomCfg;

/**
 * 系统字典库，保存系统使用到的基础信息，包括作业项目
 * @author wangchong
 *
 */
@Entity
@SuppressWarnings("serial")
public class SysDictionary extends BaseDomCfg{

	/**
	 * 字典库名称
	 */
	@Column
	private String dicName;
	
	/**
	 * 父字典Id指向，用于字典数据层级关联，(如：设备型号关联上级品牌)
	 */
	@Column
	private int dicPid;
	
	/**
	 * 词典类型
	 * 1:设备类型，2：设备品牌、3：设备型号、4：安全域、5：所在地市、6：作业项目类型
	 * oid= 6001日常重要，6002日常次要，6003syslog巡检,6005接口，6006链路，6008配置变更
	 */
	@Column
	private int dicType;
	/**
	 * 附加属性,在作业类型中，保存对应的执行任务类
	 */
	@Column
	private String additional;

	public String getDicName() {
		return dicName;
	}

	public void setDicName(String dicName) {
		this.dicName = dicName;
	}

	public int getDicPid() {
		return dicPid;
	}

	public void setDicPid(int dicPid) {
		this.dicPid = dicPid;
	}

	public int getDicType() {
		return dicType;
	}

	public void setDicType(int dicType) {
		this.dicType = dicType;
	}

	public String getAdditional() {
		return additional;
	}

	public void setAdditional(String additional) {
		this.additional = additional;
	}

	@Override
	public String toString() {
		return "SysDictionary [dicName=" + dicName + ", dicPid=" + dicPid + ", dicType=" + dicType + ", oid=" + oid
				+ ", optUserName=" + optUserName + ", addUserName=" + addUserName + ", optDateTime=" + optDateTime
				+ ", addDateTime=" + addDateTime + ", delFlg=" + delFlg + "]";
	}
	
}
