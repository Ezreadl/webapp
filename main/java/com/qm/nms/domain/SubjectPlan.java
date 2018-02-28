package com.qm.nms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.qm.core.base.BaseDomCfg;

/**
 * 课表
 * 
 * @author lenovo
 *
 */
@SuppressWarnings("serial")
@Entity public class SubjectPlan extends BaseDomCfg {
	/**
	 * 区县
	 */
	@Column private String region;
	@Column private String school;
	@Column private String grade;
	/**
	 * 班级
	 */
	@Column private String className;
	@Column private int teacherID;
	@Column private String teacherName;
	/**
	 * 课次
	 */
	@Column
	private int lessonNo;
	/**
	 * 科目
	 */
	@Column
	private String course;
	@Column
	private float lessonHour;
	/**
	 * 上课时间
	 */
	@Column
	private String lessonTime;

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

	public int getTeacherID() {
		return teacherID;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public int getLessonNo() {
		return lessonNo;
	}

	public String getCourse() {
		return course;
	}

	public float getLessonHour() {
		return lessonHour;
	}

	public String getLessonTime() {
		return lessonTime;
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

	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public void setLessonNo(int lessonNo) {
		this.lessonNo = lessonNo;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public void setLessonHour(float lessonHour) {
		this.lessonHour = lessonHour;
	}

	public void setLessonTime(String lessonTime) {
		this.lessonTime = lessonTime;
	}
}
