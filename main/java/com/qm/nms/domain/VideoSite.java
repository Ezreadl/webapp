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
@Entity public class VideoSite extends BaseDomCfg{
	/**
	 * 实验室id
	 */
	@Column
	private long organid;	
	/**
	 * 通道名
	 */
	@Column
	private String channel;

	/**
	 * 通道号
	 */
	@Column
	private String chanNo;
	
	public long getOrganid() {
		return organid;
	}

	public String getChannel() {
		return channel;
	}

	public void setOrganid(long organid) {
		this.organid = organid;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getChanNo() {
		return chanNo;
	}

	public void setChanNo(String chanNo) {
		this.chanNo = chanNo;
	}
	
}
