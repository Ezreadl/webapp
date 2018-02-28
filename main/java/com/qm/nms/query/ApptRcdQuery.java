package com.qm.nms.query;

import com.qm.core.base.IQuery;

public class ApptRcdQuery implements IQuery {
	private Integer oid;
	// 预约课次
	private Integer apptNo;
	// 区/县
	private Integer regionID;
	// 学校
	private Integer schoolID;
	
	private String grade;
	
	private String className;
	
	private String teacher;
	// 课时
	private Float lessonHour;
	// 上课
	private String lessonTime;
	
	private String cdate;
	
	private String lessonNo;
	
	private String groupNum;
	//計劃課時
	private String planLessonNo;
	
	private String labEquip;
	
	private Integer askMan;
	
	private Integer studentNum;
	//调课
	private String changeTime;		
	// 预约状态
	private Integer apptType;
	//审批状态
	private Integer checkStat;
	//审批状态下拉
	private Integer checkState;
	// 审批人
	private String apptMan;
	//教学内容
	private String content;
	//是否有实验员审批
	private Integer needappt;
	
	private String supervise;
	
	private Integer delFlg;

	private Integer checkComt;
	
	public Integer getApptNo() {
		return apptNo;
	}

	public String getGrade() {
		return grade;
	}

	public String getClassName() {
		return className;
	}

	public Float getLessonHour() {
		return lessonHour;
	}

	public String getLessonTime() {
		return lessonTime;
	}

	public Integer getStudentNum() {
		return studentNum;
	}

	public String getApptMan() {
		return apptMan;
	}

	public Integer getNeedappt() {
		return needappt;
	}

	public Integer getDelFlg() {
		return delFlg;
	}

	public void setApptNo(Integer apptNo) {
		this.apptNo = apptNo;
	}

	public void setGride(String grade) {
		this.grade = grade;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setLessonHour(Float lessonHour) {
		this.lessonHour = lessonHour;
	}

	public void setLessonTime(String lessonTime) {
		this.lessonTime = lessonTime;
	}

	public void setStudentNum(Integer studentNum) {
		this.studentNum = studentNum;
	}

	public void setApptMan(String apptMan) {
		this.apptMan = apptMan;
	}

	public void setNeedappt(Integer needappt) {
		this.needappt = needappt;
	}

	public void setDelFlg(Integer delFlg) {
		this.delFlg = delFlg;
	}

	public String getChangeTime() {
		return changeTime;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Integer getApptType() {
		return apptType;
	}

	public Integer getCheckStat() {
		return checkStat;
	}

	public void setApptType(Integer apptType) {
		this.apptType = apptType;
	}

	public void setCheckStat(Integer checkStat) {
		this.checkStat = checkStat;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public Integer getCheckComt() {
		return checkComt;
	}

	public void setCheckComt(Integer checkComt) {
		this.checkComt = checkComt;
	}

	public String getSupervise() {
		return supervise;
	}

	public void setSupervise(String supervise) {
		this.supervise = supervise;
	}

	public Integer getSchoolID() {
		return schoolID;
	}

	public void setSchoolID(Integer schoolID) {
		this.schoolID = schoolID;
	}

	public Integer getRegionID() {
		return regionID;
	}

	public void setRegionID(Integer regionID) {
		this.regionID = regionID;
	}

	public String getCdate() {
		return cdate;
	}

	public String getLessonNo() {
		return lessonNo;
	}

	public String getGroupNum() {
		return groupNum;
	}

	public String getLabEquip() {
		return labEquip;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public void setLessonNo(String lessonNo) {
		this.lessonNo = lessonNo;
	}

	public void setGroupNum(String groupNum) {
		this.groupNum = groupNum;
	}

	public void setLabEquip(String labEquip) {
		this.labEquip = labEquip;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPlanLessonNo() {
		return planLessonNo;
	}

	public void setPlanLessonNo(String planLessonNo) {
		this.planLessonNo = planLessonNo;
	}

	public Integer getAskMan() {
		return askMan;
	}

	public Integer getCheckState() {
		return checkState;
	}

	public void setAskMan(Integer askMan) {
		this.askMan = askMan;
	}

	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
	}
}
