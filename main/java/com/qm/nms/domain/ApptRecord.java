package com.qm.nms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.qm.core.base.BaseDomCfg;

/**
 * 预约审批记录
 * 
 * @author lenovo
 */
@Entity
public class ApptRecord {
	/**
	 * 数据库保存的主键，自增长类型
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer oid;
	
	@Column
	private Integer scheduleID;
	
	@Column
	private Integer labroomID;
	
	@Column
	private String apptDate;
	
	@Column
	private String apptStart;
	
	@Column
	private String apptEnd;
	//實驗器材
	@Column
	private String labEquip;
	//分組組數
	@Column
	private String groupNum;
	//學生數
	@Column
	private String studentNum;
	
	@Column
	private String needappt;
	//巡查人
	@Column
	private String superMan;
	//巡查结果
	@Column
	private String supervise;
	
	@Column
	private String cdate;
	
	@Column
	private String bgtime;
	
	@Column
	private String endtime;
	/**
	 * 預約上課節次
	 */
	@Column
	private String planLessonNo; 
	
	@Column
	private String content;
	
	@Column
	private String viewfilename;
	
	@Column
	private String picUrl;
	/**
	 * 预约状态
	 */
	@Column
	private Integer apptType;
	/**
	 * 预约审批人
	 */
	@Column
	private Integer askMan;
	/**
	 * 预约备注
	 */
	@Column
	private String apptComt;
	/**
	 * 预约状态
	 */
	@Column
	private String checkStat;
	/**
	 * 审批结果
	 */
	@Column
	private String checkAnswer;
	/**
	 * 预约审批人
	 */
	@Column
	private Integer checkMan;
	
	/**
	 * 上课状态
	 */
	@Column
	private String lessonStat;
	
	/**
	 * 预约备注
	 */
	@Column
	private String checkComt;

	public Integer getOid() {
		return oid;
	}

	public Integer getScheduleID() {
		return scheduleID;
	}

	public Integer getLabroomID() {
		return labroomID;
	}

	public String getApptDate() {
		return apptDate;
	}

	public String getApptStart() {
		return apptStart;
	}

	public String getApptEnd() {
		return apptEnd;
	}

	public String getLabEquip() {
		return labEquip;
	}

	public String getStudentNum() {
		return studentNum;
	}

	public String getNeedappt() {
		return needappt;
	}

	public String getCdate() {
		return cdate;
	}

	public String getBgtime() {
		return bgtime;
	}

	public String getEndtime() {
		return endtime;
	}

	public String getViewfilename() {
		return viewfilename;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public String getApptComt() {
		return apptComt;
	}

	public String getCheckStat() {
		return checkStat;
	}

	public String getCheckComt() {
		return checkComt;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public void setScheduleID(Integer scheduleID) {
		this.scheduleID = scheduleID;
	}

	public void setLabroomID(Integer labroomID) {
		this.labroomID = labroomID;
	}

	public void setApptDate(String apptDate) {
		this.apptDate = apptDate;
	}

	public void setApptStart(String apptStart) {
		this.apptStart = apptStart;
	}

	public void setApptEnd(String apptEnd) {
		this.apptEnd = apptEnd;
	}

	public void setLabEquip(String labEquip) {
		this.labEquip = labEquip;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	public void setNeedappt(String needappt) {
		this.needappt = needappt;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public void setBgtime(String bgtime) {
		this.bgtime = bgtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public void setViewfilename(String viewfilename) {
		this.viewfilename = viewfilename;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public void setApptComt(String apptComt) {
		this.apptComt = apptComt;
	}

	public void setCheckStat(String checkStat) {
		this.checkStat = checkStat;
	}

	public void setCheckComt(String checkComt) {
		this.checkComt = checkComt;
	}

	public Integer getApptType() {
		return apptType;
	}

	public void setApptType(Integer apptType) {
		this.apptType = apptType;
	}

	public String getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(String groupNum) {
		this.groupNum = groupNum;
	}

	public String getSupervise() {
		return supervise;
	}

	public void setSupervise(String supervise) {
		this.supervise = supervise;
	}

	public String getCheckAnswer() {
		return checkAnswer;
	}

	public void setCheckAnswer(String checkAnswer) {
		this.checkAnswer = checkAnswer;
	}

	public String getSuperMan() {
		return superMan;
	}

	public void setSuperMan(String superMan) {
		this.superMan = superMan;
	}

	public String getLessonStat() {
		return lessonStat;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setLessonStat(String lessonStat) {
		this.lessonStat = lessonStat;
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

	public void setAskMan(Integer askMan) {
		this.askMan = askMan;
	}

	public Integer getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(Integer checkMan) {
		this.checkMan = checkMan;
	}

}
