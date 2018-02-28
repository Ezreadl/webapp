package com.qm.nms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 课程规划表
 * @author lenovo
 *
 */
@Entity public class LessonRecord {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int oid;
	 /**
	  * 学科
	  */
	 @Column private String course;
	 /**
	  * 课程
	  */
	 @Column private String lesson;
	 /**
	  * 课程内容
	  */	 
	 @Column private String lessonInfo;
	 /**
	  * 课程类型
	  */	 
	 @Column private int lessonType;
	 /**
	  * 区/县
	  */
	 @Column private String region;
	 @Column private String school;
	 @Column private String grade;
	 @Column private String className;
	 @Column private int teacherID;
	 @Column private String teacherName;
	 /**
	  * 课时
	  */ 
	 @Column private float lessonHour;
	 /**
	  * 上课时间
	  */ 	 
	 @Column private String lessonTime;
	 /**
	  * 实验器材
	  */ 	  
	 @Column private String lessonEquip;
	 /**
	  * 实验组数
	  */  
	 @Column private int groupNo;
	 /**
	  * lessonStatus	上课状态
	  */
	 @Column private String lessonStatus;
	/**
	 * ifCommend	是否推荐	 
	 */
	 @Column private String ifCommend;
	 @Column private int delFlg;
	 
	 @Column private int apptNumber;
	 @Column private int apptStat;
	 @Column private String apptComt;
	 @Column private String apptMan;
	 @Column private String checkMan;
	 @Column private String checkRes;
	 @Column private String checkOut;		 
	 @Column private int needappt;
	 @Column private int isChange;
	 @Column private String changeTime;
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
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
	public int getLessonType() {
		return lessonType;
	}
	public void setLessonType(int lessonType) {
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
	public int getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public float getLessonHour() {
		return lessonHour;
	}
	public void setLessonHour(float lessonHour) {
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
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
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
	public int getDelFlg() {
		return delFlg;
	}
	public void setDelFlg(int delFlg) {
		this.delFlg = delFlg;
	}
	public int getApptNumber() {
		return apptNumber;
	}
	public int getApptStat() {
		return apptStat;
	}
	public String getApptComt() {
		return apptComt;
	}
	public String getApptMan() {
		return apptMan;
	}
	public int getNeedappt() {
		return needappt;
	}
	public int getIsChange() {
		return isChange;
	}
	public String getChangeTime() {
		return changeTime;
	}
	public void setApptNumber(int apptNumber) {
		this.apptNumber = apptNumber;
	}
	public void setApptStat(int apptStat) {
		this.apptStat = apptStat;
	}
	public void setApptComt(String apptComt) {
		this.apptComt = apptComt;
	}
	public void setApptMan(String apptMan) {
		this.apptMan = apptMan;
	}
	public void setNeedappt(int needappt) {
		this.needappt = needappt;
	}
	public void setIsChange(int isChange) {
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
}
