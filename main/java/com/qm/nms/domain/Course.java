package com.qm.nms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.qm.core.base.BaseDomCfg;

/**
 * 学科
 * @author lenovo
 *
 */
@SuppressWarnings("serial")

@Entity public class Course extends BaseDomCfg{
	  
	/**
	 * 学科名称
	 */
	@Column private String courseName;
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}
