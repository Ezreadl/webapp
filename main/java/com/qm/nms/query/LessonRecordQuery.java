package com.qm.nms.query;

import javax.persistence.Column;

import com.qm.core.base.IQuery;

/**
 * 上课记录
 * 
 * @author lenovo
 *
 */
public class LessonRecordQuery implements IQuery {

	private Integer oid;
	/**
	 * 学科
	 */
	private String course;
	/**
	 * 课程
	 */
	private String lesson;
	/**
	 * 课程内容
	 */
	private String lessonInfo;
	/**
	 * 课程类型
	 */
	private Integer lessonType;
	/**
	 * 区/县
	 */
	private String region;
	private String school;
	private String grade;
	private String className;
	private Integer teacherID;
	private String teacherName;
	/**
	 * 课时
	 */
	private Float lessonHour;
	/**
	 * 上课时间
	 */
	private String lessonTime;
	/**
	 * 实验器材
	 */
	private Integer apptNumber;
	private Integer apptStat;
	private String apptComt;
	private String apptMan;
	private String checkMan;
	private String checkRes;
	private String checkOut;	
	private Integer needappt;
	private Integer isChange;
	private String changeTime;
	private String lessonEquip;
	/**
	 * 实验组数
	 */
	private Integer groupNo;
	/**
	 * lessonStatus 上课状态
	 */
	private String lessonStatus;
	/**
	 * ifCommend 是否推荐
	 */
	private String ifCommend;
	private Integer delFlg;

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getLesson() {
		return lesson;
	}

	public void setLesson(String lesson) {
		this.lesson = lesson;
	}

	public String getLessonInfo() {
		return lessonInfo;
	}

	public void setLessonInfo(String lessonInfo) {
		this.lessonInfo = lessonInfo;
	}

	public Integer getLessonType() {
		return lessonType;
	}

	public void setLessonType(Integer lessonType) {
		this.lessonType = lessonType;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(Integer teacherID) {
		this.teacherID = teacherID;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Float getLessonHour() {
		return lessonHour;
	}

	public void setLessonHour(Float lessonHour) {
		this.lessonHour = lessonHour;
	}

	public String getLessonTime() {
		return lessonTime;
	}

	public void setLessonTime(String lessonTime) {
		this.lessonTime = lessonTime;
	}

	public String getLessonEquip() {
		return lessonEquip;
	}

	public void setLessonEquip(String lessonEquip) {
		this.lessonEquip = lessonEquip;
	}

	public Integer getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(Integer groupNo) {
		this.groupNo = groupNo;
	}

	public String getLessonStatus() {
		return lessonStatus;
	}

	public void setLessonStatus(String lessonStatus) {
		this.lessonStatus = lessonStatus;
	}

	public String getIfCommend() {
		return ifCommend;
	}

	public void setIfCommend(String ifCommend) {
		this.ifCommend = ifCommend;
	}

	public Integer getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(Integer delFlg) {
		this.delFlg = delFlg;
	}

	public Integer getApptNumber() {
		return apptNumber;
	}

	public Integer getApptStat() {
		return apptStat;
	}

	public String getApptComt() {
		return apptComt;
	}

	public String getApptMan() {
		return apptMan;
	}

	public Integer getNeedappt() {
		return needappt;
	}

	public Integer getIsChange() {
		return isChange;
	}

	public String getChangeTime() {
		return changeTime;
	}

	public void setApptNumber(Integer apptNumber) {
		this.apptNumber = apptNumber;
	}

	public void setApptStat(Integer apptStat) {
		this.apptStat = apptStat;
	}

	public void setApptComt(String apptComt) {
		this.apptComt = apptComt;
	}

	public void setApptMan(String apptMan) {
		this.apptMan = apptMan;
	}

	public void setNeedappt(Integer needappt) {
		this.needappt = needappt;
	}

	public void setIsChange(Integer isChange) {
		this.isChange = isChange;
	}

	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}

	public String getCheckMan() {
		return checkMan;
	}

	public String getCheckRes() {
		return checkRes;
	}

	public String getCheckOut() {
		return checkOut;
	}

	public void setCheckMan(String checkMan) {
		this.checkMan = checkMan;
	}

	public void setCheckRes(String checkRes) {
		this.checkRes = checkRes;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

	@Override
	public String toString() {
		return "{\"oid\":\"" + oid + "\",\"course\":\"" + course + "\",\"lesson\":\"" + lesson + "\",\"lessonInfo\":\""
				+ lessonInfo + "\",\"lessonType\":\"" + lessonType + "\",\"region\":\"" + region + "\",\"school\":\""
				+ school + "\",\"grade\":\"" + grade + "\",\"className\":\"" + className + "\",\"teacherID\":\""
				+ teacherID + "\",\"teacherName\":\"" + teacherName + "\",\"lessonHour\":\"" + lessonHour
				+ "\",\"lessonTime\":\"" + lessonTime + "\",\"apptNumber\":\"" + apptNumber + "\",\"apptStat\":\""
				+ apptStat + "\",\"apptComt\":\"" + apptComt + "\",\"apptMan\":\"" + apptMan + "\",\"checkMan\":\""
				+ checkMan + "\",\"checkRes\":\"" + checkRes + "\",\"checkOut\":\"" + checkOut + "\",\"needappt\":\""
				+ needappt + "\",\"isChange\":\"" + isChange + "\",\"changeTime\":\"" + changeTime
				+ "\",\"lessonEquip\":\"" + lessonEquip + "\",\"groupNo\":\"" + groupNo + "\",\"lessonStatus\":\""
				+ lessonStatus + "\",\"ifCommend\":\"" + ifCommend + "\",\"delFlg\":\"" + delFlg + "\"} ";
	}
	
}
