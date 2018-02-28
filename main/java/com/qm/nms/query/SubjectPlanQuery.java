package com.qm.nms.query;

import com.qm.core.base.IQuery;

/**
 * 课表
 * 
 * @author lenovo
 *
 */
public class SubjectPlanQuery implements IQuery {
	/**
	 * 区县
	 */
	private String region;
	private String school;
	private String grade;
	/**
	 * 班级
	 */
	private String className;
	private Integer teacherID;
	private String teacherName;
	/**
	 * 课次
	 */

	private Integer lessonNo;
	/**
	 * 科目
	 */

	private String course;

	private Float lessonHour;
	/**
	 * 上课时间
	 */

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

	public Integer getTeacherID() {
		return teacherID;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public Integer getLessonNo() {
		return lessonNo;
	}

	public String getCourse() {
		return course;
	}

	public Float getLessonHour() {
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

	public void setTeacherID(Integer teacherID) {
		this.teacherID = teacherID;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public void setLessonNo(Integer lessonNo) {
		this.lessonNo = lessonNo;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public void setLessonHour(Float lessonHour) {
		this.lessonHour = lessonHour;
	}

	public void setLessonTime(String lessonTime) {
		this.lessonTime = lessonTime;
	}
}
