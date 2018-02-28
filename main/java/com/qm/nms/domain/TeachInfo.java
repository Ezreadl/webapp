package com.qm.nms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.qm.core.base.BaseDomCfg;
/**
 * 任课信息
 * @author lenovo
 *
 */
@SuppressWarnings("serial")
@Entity public class TeachInfo extends BaseDomCfg {
	/**
	 * 区/县
	 */
	@Column
	private String region;
	/**
	 * 学校
	 */
	@Column
	private String school;
	@Column
	private String grade;
	@Column
	private String className;
	@Column
	private String teacherName;
	@Column
	private String courseName;
	public String getRegion() {
		return region;
	}
	public String getSchool() {
		return school;
	}
	public String getGrade() {
		return grade;
	}
	public String getClassName() {
		return className;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}	
}
