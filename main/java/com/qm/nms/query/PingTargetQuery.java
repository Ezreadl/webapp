package com.qm.nms.query;

import com.qm.core.base.IQuery;

public class PingTargetQuery implements IQuery{
	
	private String oids;
	
	private String linkName;
	
	private String targetIp;
	
	private  Integer stateFlg;

	private Integer delFlg;

	public String getOids() {
		return oids;
	}

	public void setOids(String oids) {
		this.oids = oids;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getTargetIp() {
		return targetIp;
	}

	public void setTargetIp(String targetIp) {
		this.targetIp = targetIp;
	}

	public Integer getStateFlg() {
		return stateFlg;
	}

	public void setStateFlg(Integer stateFlg) {
		this.stateFlg = stateFlg;
	}

	public Integer getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(Integer delFlg) {
		this.delFlg = delFlg;
	}

}