package com.qm.nms.query;

import javax.persistence.Entity;

import com.qm.core.base.IQuery;

/**
 * 视频记录
 * @author lenovo
 *
 */
@Entity public class RadioRecordQuery implements IQuery {
	private Integer oid;
	/**
	 * 视频名称
	 */

	private String radioName;
	/**
	 * 录制时长 
	 */
	private Float radioHour;
	/**
	 * 区/县
	 */
	private String region;
	private String school;
	private String room;
	/**
	 * 	开始时间
	 */
	private String startTime;
	private String endTime;

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getRadioName() {
		return radioName;
	}

	public void setRadioName(String radioName) {
		this.radioName = radioName;
	}

	public Float getRadioHour() {
		return radioHour;
	}

	public void setRadioHour(Float radioHour) {
		this.radioHour = radioHour;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
