package com.qm.nms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
public class WorkRest {
	/**
	 * 数据库保存的主键，自增长类型
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long oid ;
	
	@Column
	private String name;
	
	@Column
	private String startTime;
	
	@Column
	private String endTime;
	
	@Column
	private int schoolID;

	public long getOid() {
		return oid;
	}

	public String getName() {
		return name;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public int getSchoolID() {
		return schoolID;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setSchoolID(int schoolID) {
		this.schoolID = schoolID;
	}
	
}
