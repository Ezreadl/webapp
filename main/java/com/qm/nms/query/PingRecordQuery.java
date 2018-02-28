package com.qm.nms.query;

import com.qm.core.base.IQuery;

public class PingRecordQuery implements IQuery {
	//查询时间，开始段
	private String timeStart;
	//查询时间，结束段
	private String timeEnd;
	//设备名称
	private String equipName;
	//设备地址
	private String ipAddress;
	//链路名称
	private String linkName;
	//链路目标
	private String targetIp;
	//目标类型
	private String targetType;
	//告警类型,1掉包告警，2延时告警,10全部告警
	private Integer alertType;
	//掉包，开始
	private Float lostPackStart;
	//掉包，结束
	private Float lostPackEnd;
	//延时，开始
	private Integer delayStart;
	//延时，结束
	private Integer delayEnd;
	//设备Id
	private Integer equipId;
	//链路Id
	private Integer linkId;
	//告警处置状态
	private Integer dealState;
	
	public String getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	public String getEquipName() {
		return equipName;
	}
	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
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
	public String getTargetType() {
		return targetType;
	}
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	public Integer getAlertType() {
		return alertType;
	}
	public void setAlertType(Integer alertType) {
		this.alertType = alertType;
	}
	public Float getLostPackStart() {
		return lostPackStart;
	}
	public void setLostPackStart(Float lostPackStart) {
		this.lostPackStart = lostPackStart;
	}
	public Float getLostPackEnd() {
		return lostPackEnd;
	}
	public void setLostPackEnd(Float lostPackEnd) {
		this.lostPackEnd = lostPackEnd;
	}
	public Integer getDelayStart() {
		return delayStart;
	}
	public void setDelayStart(Integer delayStart) {
		this.delayStart = delayStart;
	}
	public Integer getDelayEnd() {
		return delayEnd;
	}
	public void setDelayEnd(Integer delayEnd) {
		this.delayEnd = delayEnd;
	}
	public Integer getEquipId() {
		return equipId;
	}
	public void setEquipId(Integer equipId) {
		this.equipId = equipId;
	}
	public Integer getLinkId() {
		return linkId;
	}
	public void setLinkId(Integer linkId) {
		this.linkId = linkId;
	}
	public Integer getDealState() {
		return dealState;
	}
	public void setDealState(Integer dealState) {
		this.dealState = dealState;
	}

}