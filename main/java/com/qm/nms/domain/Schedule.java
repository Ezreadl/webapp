package com.qm.nms.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.qm.core.base.BaseDomCfg;

/**
 * 课程表
 * @author xiaoze
 * 2017年12月14日上午10:59:45
 */
@SuppressWarnings("serial")
@Entity
public class Schedule {
	/**
	 * 数据库保存的主键，自增长类型
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int oid;
	//地市代码
	@Column
	private int areaID;
	
	//区县代码
	@Column
	private int regionID;
	
	//学校代码
	@Column
	private int schoolID;
	
	//班级
	@Column
	private String Clazz;
	
	//学期
	@Column
	private String term;
	
	//学科
	@Column
	private String course;
	
	//任课教师
	@Column
	private String teacher;
	
	//第几周
	@Column
	private String weekNo;
	
	//星期几
	@Column
	private String weekDay;
	
	//每天的第几课时
	@Column
	private String lessonNo;
	
	@Column 
	private String optUser;
	
	@Column
	private String optTime;
	
	@Column
	private String Classyear;

	public String getLessonNo() {
		return lessonNo;
	}

	public void setLessonNo(String lessonNo) {
		this.lessonNo = lessonNo;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public int getAreaID() {
		return areaID;
	}

	public int getRegionID() {
		return regionID;
	}

	public int getSchoolID() {
		return schoolID;
	}

	public void setAreaID(int areaID) {
		this.areaID = areaID;
	}

	public void setRegionID(int regionID) {
		this.regionID = regionID;
	}

	public void setSchoolID(int schoolID) {
		this.schoolID = schoolID;
	}

	public String getCourse() {
		return course;
	}

	public String getTeacher() {
		return teacher;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public int getOid() {
		return oid;
	}

	public String getOptUser() {
		return optUser;
	}

	public String getOptTime() {
		return optTime;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public void setOptUser(String optUser) {
		this.optUser = optUser;
	}

	public void setOptTime(String optTime) {
		this.optTime = optTime;
	}

	public String getClazz() {
		return Clazz;
	}

	public void setClazz(String Clazz) {
		this.Clazz = Clazz;
	}

	public String getWeekNo() {
		return weekNo;
	}

	public void setWeekNo(String weekNo) {
		this.weekNo = weekNo;
	}

	public String getClassyear() {
		return Classyear;
	}

	public void setClassyear(String classyear) {
		Classyear = classyear;
	}

}
