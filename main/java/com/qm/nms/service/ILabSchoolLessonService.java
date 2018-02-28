package com.qm.nms.service;

import java.util.List;
import java.util.Map;

import com.qm.nms.domain.LabLessonPlan;
import com.qm.nms.domain.LabLessonPlanInfo;
import com.qm.nms.domain.LabSchoolLesson;
import com.qm.nms.domain.LabSchoolLessonPlanInfo;
import com.qm.nms.domain.Organization;
import com.qm.nms.domainEnum.RequiredEnum;
import com.qm.nms.domainEnum.TermEnum;

import exception.BizException;

public abstract interface ILabSchoolLessonService {
	public abstract LabLessonPlan getLabLessonPlanBySchool(Organization paramSchool);

	public abstract void saveSchoolLessonPlan(Long paramLong, Organization paramSchool) throws BizException;

	public abstract void saveSchoolLessonInfo(List<LabLessonPlanInfo> paramList,
			LabSchoolLessonPlanInfo paramLabSchoolLessonPlanInfo, LabSchoolLesson paramLabSchoolLesson,
			String paramString);

	public abstract List getLessonPlanInfoTree(Organization paramSchool, Long paramLong);

	public abstract void updateLessPlanInfo(Long paramLong1, Long paramLong2, TermEnum paramTermEnum)
			throws BizException;

	public abstract List getSchoolLessonInfoList(Integer paramInteger1, Integer paramInteger2, Organization paramSchool,
			Long paramLong1, Long paramLong2, Long paramLong3);

	public abstract void setSchoolRequiredCourse(Long paramLong, RequiredEnum paramRequiredEnum) throws BizException;
}
