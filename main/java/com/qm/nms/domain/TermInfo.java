package com.qm.nms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.qm.core.base.BaseDomCfg;
/**
 * 学期信息
 * @author lenovo
 *
 */
@SuppressWarnings("serial")
@Entity
public class TermInfo extends BaseDomCfg{
	/**
	 * 学期
	 */
    @Column private String termName;
    @Column private String startTime;
    @Column private String endTime;
	public String getTermName() {
		return termName;
	}
	public String getStartTime() {
		return startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
