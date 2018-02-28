package com.qm.nms.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.qm.nms.domainEnum.CommonStatusEnum;

@Entity
public class LabSchoolLesson {
	private long id;
	private Organization school;
	private LabLessonPlan labLessonPlan;
	private CommonStatusEnum status;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(targetEntity = Organization.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "school_id")
	public Organization getSchool() {
		return this.school;
	}

	public void setSchool(Organization school) {
		this.school = school;
	}

	@ManyToOne(targetEntity = LabLessonPlan.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "lesson_plan_id")
	public LabLessonPlan getLabLessonPlan() {
		return this.labLessonPlan;
	}

	public void setLabLessonPlan(LabLessonPlan labLessonPlan) {
		this.labLessonPlan = labLessonPlan;
	}

	@Basic
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	public CommonStatusEnum getStatus() {
		return this.status;
	}

	public void setStatus(CommonStatusEnum status) {
		this.status = status;
	}
}
