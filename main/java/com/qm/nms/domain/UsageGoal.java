package com.qm.nms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.qm.core.base.BaseDomCfg;

/**
 * 教学目标
 * @author lenovo
 *
 */
@SuppressWarnings("serial")
@Entity
public class UsageGoal extends BaseDomCfg{
	@Column private String schoolType;
	@Column private float openRate;
	@Column private float groupRate;
	@Column private float demoRate;
	public String getSchoolType() {
		return schoolType;
	}
	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}
	public float getOpenRate() {
		return openRate;
	}
	public float getGroupRate() {
		return groupRate;
	}
	public float getDemoRate() {
		return demoRate;
	}

	public void setOpenRate(float openRate) {
		this.openRate = openRate;
	}
	public void setGroupRate(float groupRate) {
		this.groupRate = groupRate;
	}
	public void setDemoRate(float demoRate) {
		this.demoRate = demoRate;
	}
}
