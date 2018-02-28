package com.qm.nms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.qm.core.base.BaseDomCfg;

/**
 * 点位信息
 * @author lenovo
 *
 */
@SuppressWarnings("serial")
@Entity
public class CamDevice extends BaseDomCfg{
	/**
	 * 点位位置
	 */
	@Column
	private Long organid;
	/**
	 * 点位名
	 */
	@Column
	private String describtion;
	/**
	 * 端口
	 */
	@Column
	private String portnum;	
	/**
	 * 用户名
	 */
	@Column
	private String account;
	/**
	 * 密码
	 */
	@Column
	private String passwd;
	
	@Column
	private String channel1;

	@Column
	private String channel2;

	public Long getOrganid() {
		return organid;
	}

	public String getDescribtion() {
		return describtion;
	}

	public String getAccount() {
		return account;
	}

	public String getChannel1() {
		return channel1;
	}

	public String getChannel2() {
		return channel2;
	}

	public void setOrganid(Long organid) {
		this.organid = organid;
	}

	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setChannel1(String channel1) {
		this.channel1 = channel1;
	}

	public void setChannel2(String channel2) {
		this.channel2 = channel2;
	}

	public String getPortnum() {
		return portnum;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPortnum(String portnum) {
		this.portnum = portnum;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
}
