package com.qm.sys.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qm.core.base.BaseDomPure;
import com.qm.core.base.DateTimeSerializer;

@Entity
@SuppressWarnings("serial")
public class SysOptLog extends BaseDomPure{
	
	/**
	 * 操作描述
	 */
	@Column
	private String optDesc;
	
	/**
	 * 操作用户Id
	 */
	@Column
	private int optUserId;
	
	/**
	 * 操作用户昵称
	 */
	@Column
	private String optUserNick;
	
	/**
	 * 操作日志记录时间
	 */
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using=DateTimeSerializer.class)
	private Date optTime;
	
	/**
	 * 用户操作的IP地址
	 */
	@Column
	private String ipAddress;

	public String getOptDesc() {
		return optDesc;
	}

	public void setOptDesc(String optDesc) {
		this.optDesc = optDesc;
	}

	public int getOptUserId() {
		return optUserId;
	}

	public void setOptUserId(int optUserId) {
		this.optUserId = optUserId;
	}

	public String getOptUserNick() {
		return optUserNick;
	}

	public void setOptUserNick(String optUserNick) {
		this.optUserNick = optUserNick;
	}

	public Date getOptTime() {
		return optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
}
