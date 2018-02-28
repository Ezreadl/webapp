package com.qm.nms.query;

import com.qm.core.base.IQuery;

public class ScheduleQuery implements IQuery {

	private Integer areaId;
	
	private String Grade;
	
	private String Clazz;

	private String teacher;	
	
	private Integer indexNum;
	
	private Integer term;
	
	private String weekNo;		

	public Integer getIndexNum() {
		return indexNum;
	}

	public void setIndexNum(Integer indexNum) {
		this.indexNum = indexNum;
	}

	public Integer getTerm() {
		return term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	public String getGrade() {
		return Grade;
	}

	public String getClazz() {
		return Clazz;
	}

	public void setGrade(String grade) {
		Grade = grade;
	}

	public void setClazz(String clazz) {
		Clazz = clazz;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getWeekNo() {
		return weekNo;
	}

	public void setWeekNo(String weekNo) {
		this.weekNo = weekNo;
	}
	
}
