package com.qm.nms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 视频记录
 * @author lenovo
 *
 */
@Entity public class RadioRecord {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int oid;
	/**
	 * 视频名称
	 */
	@Column private String radioName;
	/**
	 * 录制时长 
	 */
	@Column private float radioHour;
	/**
	 * 区/县
	 */
	@Column private String region;
	@Column private String school;
	@Column private String room;
	/**
	 * 	开始时间
	 */
	@Column private String startTime;
	@Column private String endTime;

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getRadioName() {
		return radioName;
	}

	public void setRadioName(String radioName) {
		this.radioName = radioName;
	}

	public float getRadioHour() {
		return radioHour;
	}

	public void setRadioHour(float radioHour) {
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
