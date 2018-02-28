package com.qm.nms.query;

import com.qm.core.base.IQuery;

/**
 * 学科
 * @author lenovo
 *
 */
	public class CourseQuery implements IQuery{
	  
	/**
	 * 学科名称
	 */
	private Integer delFlg;
	private String courseName;
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(Integer delFlg) {
		this.delFlg = delFlg;
	}
}
