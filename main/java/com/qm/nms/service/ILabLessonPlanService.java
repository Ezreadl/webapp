package com.qm.nms.service;

import java.util.List;
import java.util.Map;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.nms.domain.LabLessonPlan;
import com.qm.nms.domain.LabLessonPlanInfo;
import com.qm.nms.domain.LabType;
import com.qm.nms.domainEnum.LessonPlanEnum;
import com.qm.nms.domainEnum.TeachVersionEnum;
import com.qm.nms.domainEnum.TermEnum;
import com.qm.nms.query.LabLessonPlanQuery;
import com.qm.sys.domain.SysUser;

import exception.BizException;

public abstract interface ILabLessonPlanService {
	public abstract LabLessonPlan addLabLessonPlan(String paramString, Long paramLong,
			TeachVersionEnum paramTeachVersionEnum) throws BizException;

	public abstract GridListData findLessonPlan(LabLessonPlanQuery query, PageInfo pageInfo) throws Exception;

	public abstract void importLessonPlan(List<Map<String, Object>> paramList, SysUser paramUser,
			LabLessonPlan paramLabLessonPlan) throws Exception;

	public abstract List<Map<String, Object>> getLessonPlanList(Integer paramInteger1, Integer paramInteger2);

	public abstract Long getLessonPlanCount();

	public List<String> findLesson(SysUser user) throws Exception;

	public Map<String, Object> findLabLesson(int planid) throws Exception;

	public abstract void delLessonPlan(Long paramLong) throws BizException;

	public abstract List<Map<String, Object>> getPlanInfoTree(Long planId) throws Exception;

	public abstract List<Map<String, Object>> getLessonPlanInfoTree(Long paramLong1, Long paramLong2);

	public abstract void updateLessPlanInfo(Long paramLong1, String paramString, LessonPlanEnum paramLessonPlanEnum1,
			LessonPlanEnum paramLessonPlanEnum2, LessonPlanEnum paramLessonPlanEnum3, Long paramLong2,
			TermEnum paramTermEnum) throws BizException;

	public abstract void deleteLessonPlanInfo(Long paramLong);

	public abstract void addLessPlanInfo(Long paramLong1, String paramString, LessonPlanEnum paramLessonPlanEnum1,
			LessonPlanEnum paramLessonPlanEnum2, LessonPlanEnum paramLessonPlanEnum3, Long paramLong2,
			TermEnum paramTermEnum) throws BizException;

	public abstract List<Map<String, Object>> getLessonPlanListBySchoolType(LabType paramLabType);
}
