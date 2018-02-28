package com.qm.nms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qm.core.util.CommonUtils;
import com.qm.nms.dao.ILabLessonPlanDao;
import com.qm.nms.dao.ILabSchoolLessonDao;
import com.qm.nms.dao.ILabSchoolLessonPlanInfoDao;
import com.qm.nms.domain.LabLessonPlan;
import com.qm.nms.domain.LabLessonPlanInfo;
import com.qm.nms.domain.LabSchoolLesson;
import com.qm.nms.domain.LabSchoolLessonPlanInfo;
import com.qm.nms.domain.LabType;
import com.qm.nms.domain.Organization;
import com.qm.nms.domainEnum.CommonStatusEnum;
import com.qm.nms.domainEnum.LessonPlanEnum;
import com.qm.nms.domainEnum.RequiredEnum;
import com.qm.nms.domainEnum.TermEnum;
import com.qm.nms.service.ILabSchoolLessonService;

import exception.BizException;

@Service
public class LabSchoolLessonServiceImpl implements ILabSchoolLessonService {

	@Autowired
	ILabLessonPlanDao planDao;
	@Autowired
	ILabSchoolLessonDao lessonDao;
	@Autowired
	ILabSchoolLessonPlanInfoDao infoDao;

	@Override
	public LabLessonPlan getLabLessonPlanBySchool(Organization school) {
		// TODO Auto-generated method stub
		// LabSchoolLesson labSchoolLesson =
		// (LabSchoolLesson)this.baseDao.getUnique(hql, new Object[] {
		// Organization, CommonStatusEnum.AVAILABLE });
		LabSchoolLesson labSchoolLesson = lessonDao.findBySchoolStatus(school, "可用");
		return CommonUtils.isEmpty(labSchoolLesson) ? null : labSchoolLesson.getLabLessonPlan();
	}

	@Override
	public void saveSchoolLessonPlan(Long paramLong, Organization school) throws BizException {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveSchoolLessonInfo(List<LabLessonPlanInfo> paramList,
			LabSchoolLessonPlanInfo paramLabSchoolLessonPlanInfo, LabSchoolLesson paramLabSchoolLesson,
			String paramString) {
		// TODO Auto-generated method stub

	}

	@Override
	public List getLessonPlanInfoTree(Organization school, Long parentId) {
		LabSchoolLesson labSchoolLesson = lessonDao.findBySchoolStatus(school, "可用");
		List<Map<String, Object>> list = new ArrayList();
		Iterator localIterator;
		LabSchoolLessonPlanInfo labSchoolLessonPlanInfo;
		if (parentId.equals(Long.valueOf(0L))) {
			List<LabSchoolLessonPlanInfo> labSchoolLessonPlanInfos = infoDao.findByLessonLevel(labSchoolLesson, "1");
			for (localIterator = labSchoolLessonPlanInfos.iterator(); localIterator.hasNext();) {
				labSchoolLessonPlanInfo = (LabSchoolLessonPlanInfo) localIterator.next();
				Map<String, Object> map = new HashMap();

				map.put("id", labSchoolLessonPlanInfo.getId() + "");
				map.put("text", labSchoolLessonPlanInfo.getTitle());
				if (!CommonUtils.isEmpty(labSchoolLessonPlanInfo.getLabSchoolLessonPlanInfos())) {
					map.put("children", Boolean.valueOf(true));
				}
				map.put("lessonLevel", labSchoolLessonPlanInfo.getLevel());
				if (!CommonUtils.isEmpty(labSchoolLessonPlanInfo.getParent())) {
					map.put("parent", Long.valueOf(labSchoolLessonPlanInfo.getParent().getId()));
				} else {
					map.put("parent", "#");
				}
				if (labSchoolLessonPlanInfo.getLevel().equals(LessonPlanEnum.COURSE_LEVEL)) {
					map.put("remark", labSchoolLessonPlanInfo.getRemark());
					map.put("demonstration", labSchoolLessonPlanInfo.getDemonstration());
					map.put("scene", labSchoolLessonPlanInfo.getScene());
				}
				if (labSchoolLessonPlanInfo.getLevel().equals(LessonPlanEnum.THIRD_LEVEL)) {
					map.put("grade", Long.valueOf(labSchoolLessonPlanInfo.getGrade().getId()));
					map.put("gradeYear", labSchoolLessonPlanInfo.getGradeYear());
				}
				list.add(map);
			}
		} else {
			LabSchoolLessonPlanInfo parent = infoDao.findByID(parentId);
			List<LabSchoolLessonPlanInfo> labSchoolLessonPlanInfos = infoDao.findByLesson(parent);
			for (LabSchoolLessonPlanInfo labLessonPlanInfo : labSchoolLessonPlanInfos) {
				Map<String, Object> map = new HashMap();
				map.put("id", labLessonPlanInfo.getId() + "");
				map.put("text", labLessonPlanInfo.getTitle());
				Map<String, Object> state = new HashMap();
				if (!CommonUtils.isEmpty(labLessonPlanInfo.getRequired())) {
					map.put("required", labLessonPlanInfo.getRequired());
					if ((labLessonPlanInfo.getRequired().equals(RequiredEnum.REQUIRED))
							|| (labLessonPlanInfo.getRequired().equals(RequiredEnum.ELECTIVE_REQUIRED))) {
						state.put("selected", Boolean.valueOf(true));
					}
				}
				map.put("state", state);
				if (!CommonUtils.isEmpty(labLessonPlanInfo.getLabSchoolLessonPlanInfos())) {
					map.put("children", Boolean.valueOf(true));
				}
				map.put("lessonLevel", labLessonPlanInfo.getLevel());
				if (!CommonUtils.isEmpty(labLessonPlanInfo.getParent())) {
					map.put("parent", Long.valueOf(labLessonPlanInfo.getParent().getId()));
				} else {
					map.put("parent", "#");
				}
				if (labLessonPlanInfo.getLevel().equals(LessonPlanEnum.COURSE_LEVEL)) {
					map.put("remark", labLessonPlanInfo.getRemark());
					map.put("demonstration", labLessonPlanInfo.getDemonstration());
					map.put("scene", labLessonPlanInfo.getScene());
				}
				if (!CommonUtils.isEmpty(labLessonPlanInfo.getGrade())) {
					map.put("grade", Long.valueOf(labLessonPlanInfo.getGrade().getId()));
					map.put("gradeYear", labLessonPlanInfo.getGradeYear());
				}
				list.add(map);
			}
		}
		return list;
	}

	@Override
	public void updateLessPlanInfo(Long id, Long grade, TermEnum gradeYear) throws BizException {
		LabSchoolLessonPlanInfo labSchoolLessonPlanInfo = infoDao.findByID(id);
		LabType labType = planDao.findGradeById(grade);
		if (CommonUtils.isEmpty(labSchoolLessonPlanInfo)) {
			throw new BizException("目录信息不存在");
		}
		if (CommonUtils.isEmpty(labType)) {
			throw new BizException("年级信息不存在");
		}
		labSchoolLessonPlanInfo.setGrade(labType);
		if (!CommonUtils.isEmpty(gradeYear)) {
			labSchoolLessonPlanInfo.setGradeYear(gradeYear);
		}
		infoDao.updatePlan(labType, gradeYear, "%" + labSchoolLessonPlanInfo.getId() + "%");
	}

	@Override
	public List<Map<String, Object>> getSchoolLessonInfoList(Integer start, Integer length, Organization school,
			Long gradeId, Long subjectId, Long termId) {
//		start = Integer.valueOf(start.intValue() / length.intValue() + 1);
//		String hql = "from LabSchoolLesson where school = ? and status = ?";
//		LabSchoolLesson labSchoolLesson = (LabSchoolLesson) this.baseDao.getUnique(hql,
//				new Object[] { school, CommonStatusEnum.AVAILABLE });
//		hql = "from LabSchoolLessonPlanInfo where labSchoolLesson = ? and level = ? order by title ";
//		List<LabSchoolLessonPlanInfo> labSchoolLessonPlanInfos = this.baseDao.pageQuery(hql, start, length,
//				new Object[] { labSchoolLesson, LessonPlanEnum.LESSON_LEVEL });
//
//		String sql1 = "call proc_getTermTypeById(?)";
//
//		Query query1 = this.baseDao.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql1);
//		query1.setParameter(0, termId);
//		String termTypeStr = query1.list().toString();
//		String[] strArray = termTypeStr.split("\\[|\\]");
//		String termType = strArray[1];
//
//		List<LabSchoolLessonPlanInfo> labSchoolLessonPlanInfosFilterList = new ArrayList();
//		for (LabSchoolLessonPlanInfo labSchoolLessonPlanInfo : labSchoolLessonPlanInfos) {
//			if ((!CommonUtils.isEmpty(labSchoolLessonPlanInfo.getGrade()))
//					&& (!CommonUtils.isEmpty(labSchoolLessonPlanInfo.getSubject()))) {
//				Long mGradeId = Long.valueOf(labSchoolLessonPlanInfo.getGrade().getId());
//				Long mSubjectId = Long.valueOf(labSchoolLessonPlanInfo.getSubject().getId());
//				mTermType = labSchoolLessonPlanInfo.getGradeYear().name();
//				if ((mGradeId.equals(gradeId)) && (mSubjectId.equals(subjectId)) && (mTermType.equals(termType))) {
//					labSchoolLessonPlanInfosFilterList.add(labSchoolLessonPlanInfo);
//				}
//			}
//		}
//		String mTermType;
//		String hql2 = "from LabSchoolLesson where school = ? and status = ?";
//
//		LabSchoolLesson labSchoolLesson2 = (LabSchoolLesson) this.baseDao.getUnique(hql2,
//				new Object[] { school, CommonStatusEnum.AVAILABLE });
//		hql2 = "from LabSchoolLessonPlanInfo where labSchoolLesson = ? and level = ?";
//		List<LabSchoolLessonPlanInfo> labSchoolLessonPlanInfos2 = this.baseDao.pageQuery(hql2, start, length,
//				new Object[] { labSchoolLesson2, LessonPlanEnum.COURSE_LEVEL });
//
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		for (LabSchoolLessonPlanInfo labSchoolLessonPlanInfo : labSchoolLessonPlanInfosFilterList) {
//			Map<String, Object> map = new HashMap();
//
//			map.put("grade", labSchoolLessonPlanInfo.getGrade().getTypeValue());
//
//			map.put("gradeYear", labSchoolLessonPlanInfo.getGradeYear().getDesc());
//
//			map.put("subjectName", labSchoolLessonPlanInfo.getParent().getParent().getParent().getTitle());
//
//			map.put("parentTitle", labSchoolLessonPlanInfo.getParent().getParent().getTitle());
//
//			map.put("title", labSchoolLessonPlanInfo.getParent().getTitle());
//
//			map.put("content", labSchoolLessonPlanInfo.getTitle());
//			if (!CommonUtils.isEmpty(labSchoolLessonPlanInfo.getDemonstration())) {
//				map.put("demonstration", labSchoolLessonPlanInfo.getDemonstration().name());
//			} else {
//				map.put("demonstration", " ");
//			}
//			if (!CommonUtils.isEmpty(labSchoolLessonPlanInfo.getScene())) {
//				map.put("inClass", labSchoolLessonPlanInfo.getScene().name());
//				map.put("outClass", labSchoolLessonPlanInfo.getScene().name());
//			} else {
//				map.put("inClass", " ");
//				map.put("outClass", " ");
//			}
//			if (!CommonUtils.isEmpty(labSchoolLessonPlanInfo.getRemark())) {
//				map.put("starCourse", labSchoolLessonPlanInfo.getRemark().name());
//			} else {
//				map.put("starCourse", " ");
//			}
//			List<Map<String, Object>> list2 = new ArrayList();
//			for (LabSchoolLessonPlanInfo labSchoolLessonPlanInfo2 : labSchoolLessonPlanInfos2) {
//				Map<String, Object> map2 = new HashMap();
//				if ((!CommonUtils.isEmpty(Long.valueOf(labSchoolLessonPlanInfo2.getParent().getId())))
//						&& (labSchoolLessonPlanInfo.getId() == labSchoolLessonPlanInfo2.getParent().getId())) {
//					map2.put("labCourseName", labSchoolLessonPlanInfo2.getTitle());
//					if ((!CommonUtils.isEmpty(labSchoolLessonPlanInfo2.getDemonstration()))
//							&& (labSchoolLessonPlanInfo2.getDemonstration().name().equals("NEED"))) {
//						map2.put("labCourseDemonstration", "√ ");
//					} else {
//						map2.put("labCourseDemonstration", " ");
//					}
//					if (!CommonUtils.isEmpty(labSchoolLessonPlanInfo2.getScene())) {
//						if (labSchoolLessonPlanInfo2.getScene().name().equals("IN_CLASS")) {
//							map2.put("labCourseInClass", "√ ");
//							map2.put("labCourseOutClass", " ");
//						} else if (labSchoolLessonPlanInfo2.getScene().name().equals("OUT_CLASS")) {
//							map2.put("labCourseInClass", " ");
//							map2.put("labCourseOutClass", "√ ");
//						} else {
//							map2.put("labCourseInClass", " ");
//							map2.put("labCourseOutClass", " ");
//						}
//					} else {
//						map2.put("labCourseInClass", " ");
//						map2.put("labCourseOutClass", " ");
//					}
//					if ((!CommonUtils.isEmpty(labSchoolLessonPlanInfo2.getRemark()))
//							&& (labSchoolLessonPlanInfo2.getRemark().name().equals("STAR_COURSE"))) {
//						map2.put("labCourseStarCourse", "√ ");
//					} else {
//						map2.put("labCourseStarCourse", " ");
//					}
//					list2.add(map2);
//				}
//			}
//			map.put("detail", list2);
//
//			list.add(map);
//		}
		return null;
//		return list;
	}
	
	@Override
	public void setSchoolRequiredCourse(Long lessonId, RequiredEnum requiredEnum) throws BizException {
//		LabSchoolLessonPlanInfo labSchoolLessonPlanInfo = (LabSchoolLessonPlanInfo) this.baseDao
//				.getById(LabSchoolLessonPlanInfo.class, lessonId);
		LabSchoolLessonPlanInfo labSchoolLessonPlanInfo = infoDao.findByID(lessonId);
		if ((!CommonUtils.isEmpty(labSchoolLessonPlanInfo.getRequired()))
				&& ((labSchoolLessonPlanInfo.getRequired().equals(RequiredEnum.ELECTIVE))
						|| (labSchoolLessonPlanInfo.getRequired().equals(RequiredEnum.ELECTIVE_REQUIRED)))) {
			labSchoolLessonPlanInfo.setRequired(requiredEnum);
			setSchoolRequiredChildrenCourse(labSchoolLessonPlanInfo, requiredEnum);
		}
	}

	public void setSchoolRequiredChildrenCourse(LabSchoolLessonPlanInfo labSchoolLessonPlanInfo,
			RequiredEnum requiredEnum) {
		if (!CommonUtils.isEmpty(labSchoolLessonPlanInfo.getLabSchoolLessonPlanInfos())) {
			Iterator<LabSchoolLessonPlanInfo> infoIterator = labSchoolLessonPlanInfo.getLabSchoolLessonPlanInfos()
					.iterator();
			while (infoIterator.hasNext()) {
				LabSchoolLessonPlanInfo lessonInfo = (LabSchoolLessonPlanInfo) infoIterator.next();
				lessonInfo.setRequired(requiredEnum);
				if (!CommonUtils.isEmpty(lessonInfo.getLabSchoolLessonPlanInfos())) {
					setSchoolRequiredChildrenCourse(lessonInfo, requiredEnum);
				}
			}
		}
	}

}
