package com.qm.nms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.nms.dao.ILabLessonPlanDao;
import com.qm.nms.dao.ILabLessonPlanInfoDao;
import com.qm.nms.dao.ILabSchoolLessonDao;
import com.qm.nms.dao.IOrganDao;
import com.qm.nms.domain.LabLessonPlan;
import com.qm.nms.domain.LabLessonPlanInfo;
import com.qm.nms.domain.LabSchoolLesson;
import com.qm.nms.domain.LabType;
import com.qm.nms.domain.Organization;
import com.qm.nms.domainEnum.CommonStatusEnum;
import com.qm.nms.domainEnum.LessonPlanEnum;
import com.qm.nms.domainEnum.RequiredEnum;
import com.qm.nms.domainEnum.TeachVersionEnum;
import com.qm.nms.domainEnum.TermEnum;
import com.qm.nms.query.LabLessonPlanQuery;
import com.qm.nms.service.ILabLessonPlanService;
import com.qm.sys.domain.SysUser;

import exception.BizException;

@Service
public class LabLessonPlanServiceImpl implements ILabLessonPlanService {
	@Autowired
	ILabLessonPlanDao planDao;
	@Autowired
	ILabLessonPlanInfoDao infoDao;
	@Autowired
	ILabSchoolLessonDao schoollessonDao;
	@Autowired
	IOrganDao organDao;

	public GridListData findLessonPlan(LabLessonPlanQuery query, PageInfo pageInfo) throws Exception {
		GridListData lpan = planDao.findDataList1(query, pageInfo);
		return lpan;
	}

	@Transactional
	public void importLessonPlan(List<Map<String, Object>> list, SysUser user, LabLessonPlan labLessonPlan)
			throws Exception {
		if (null == (list)) {
			throw new BizException("导入文件内容不能为空");
		}
		List<LabType> grades = planDao.findByName("grade");
		List<LabType> subjects = planDao.findByName("subject");

		LabLessonPlanInfo[] labLessonPlanInfos = new LabLessonPlanInfo[10];
		Map<String, Object> title = (Map) list.get(0);
		int gradeIndex = 0;
		int gradeYearIndex = 0;
		for (int i = 1; i <= title.size(); i++) {
			if (title.get("value" + i).toString().indexOf("年纪") >= 0) {
				gradeIndex = i;
			}
			if (title.get("value" + i).toString().indexOf("学期") >= 0) {
				gradeYearIndex = i;
			}
		}
		LabLessonPlanInfo lessonLevel = null;
		for (int i = 2; i < list.size(); i++) {
			Map<String, Object> map = (Map) list.get(i);
			int valueSize = map.size();
			int titleSize = valueSize - 6;
			for (int valueIndex = 1; valueIndex <= titleSize; valueIndex++) {
				if (valueIndex == gradeIndex) {
					if (null != (map.get("value" + valueIndex))) {
						for (LabType labType : grades) {
							if (map.get("value" + valueIndex).toString().indexOf(labType.getTypeValue()) >= 0) {
								labLessonPlanInfos[(valueIndex - 1)].setGrade(labType);
								break;
							}
						}
					}
					labLessonPlanInfos[valueIndex] = labLessonPlanInfos[(valueIndex - 1)];
				} else if (valueIndex == gradeYearIndex) {
					if (null != (map.get("value" + valueIndex))) {
						if (map.get("value" + valueIndex).toString().indexOf(TermEnum.LAST_TERM.getDesc()) >= 0) {
							labLessonPlanInfos[(valueIndex - 1)].setGradeYear(TermEnum.LAST_TERM);
						} else if (map.get("value" + valueIndex).toString()
								.indexOf(TermEnum.NEXT_TERM.getDesc()) >= 0) {
							labLessonPlanInfos[(valueIndex - 1)].setGradeYear(TermEnum.NEXT_TERM);
						} else {
							labLessonPlanInfos[(valueIndex - 1)].setGradeYear(TermEnum.ALL_TERM);
						}
					}
					labLessonPlanInfos[valueIndex] = labLessonPlanInfos[(valueIndex - 1)];
				} else if (null != (map.get("value" + valueIndex))) {
					labLessonPlanInfos[valueIndex] = new LabLessonPlanInfo();
					labLessonPlanInfos[valueIndex].setTitle(map.get("value" + valueIndex).toString());
					labLessonPlanInfos[valueIndex].setLevel(getLessPlanLevel(valueIndex));
					labLessonPlanInfos[valueIndex].setPlan(labLessonPlan);
					if (valueIndex == 1) {
						for (LabType labType : subjects) {
							if (map.get("value" + valueIndex).toString().indexOf(labType.getTypeValue()) >= 0) {
								labLessonPlanInfos[valueIndex].setSubject(labType);
								break;
							}
						}
					}
					if (valueIndex > 1) {
						labLessonPlanInfos[valueIndex].setNodePath(labLessonPlanInfos[(valueIndex - 1)].getNodePath()
								+ "/" + labLessonPlanInfos[(valueIndex - 1)].getId());
						labLessonPlanInfos[valueIndex].setParent(labLessonPlanInfos[(valueIndex - 1)]);
						labLessonPlanInfos[valueIndex].setSubject(labLessonPlanInfos[(valueIndex - 1)].getSubject());
						labLessonPlanInfos[valueIndex].setGrade(labLessonPlanInfos[(valueIndex - 1)].getGrade());
						labLessonPlanInfos[valueIndex]
								.setGradeYear(labLessonPlanInfos[(valueIndex - 1)].getGradeYear());
						labLessonPlanInfos[valueIndex].setRequired(labLessonPlanInfos[(valueIndex - 1)].getRequired());
					} else {
						labLessonPlanInfos[valueIndex].setNodePath("#");
					}
					if (valueIndex == titleSize) {
						labLessonPlanInfos[valueIndex].setLevel(LessonPlanEnum.LESSON_LEVEL);
						lessonLevel = labLessonPlanInfos[valueIndex];
					}
					if (valueIndex == 2) {
						if (map.get("value" + valueIndex).toString().indexOf("选修") >= 0) {
							labLessonPlanInfos[valueIndex].setRequired(RequiredEnum.ELECTIVE);
						} else {
							labLessonPlanInfos[valueIndex].setRequired(RequiredEnum.REQUIRED);
						}
					}
					infoDao.save(labLessonPlanInfos[valueIndex]);
				}
			}
			int courseIndex = titleSize + 1;
			if (null != (map.get("value" + courseIndex))) {
				LabLessonPlanInfo labLessonPlanInfo = new LabLessonPlanInfo();
				labLessonPlanInfo
						.setSerialNumber(Integer.valueOf(Integer.parseInt(map.get("value" + courseIndex).toString())));
				courseIndex++;
				labLessonPlanInfo.setTitle(map.get("value" + courseIndex).toString());
				courseIndex++;
				if (map.get("value" + courseIndex).toString().length() > 0) {
					labLessonPlanInfo.setDemonstration(LessonPlanEnum.NEED);
				} else {
					labLessonPlanInfo.setDemonstration(LessonPlanEnum.UNWANTED);
				}
				courseIndex++;
				labLessonPlanInfo.setScene(LessonPlanEnum.NULL_CLASS);
				if (map.get("value" + courseIndex).toString().length() > 0) {
					labLessonPlanInfo.setScene(LessonPlanEnum.IN_CLASS);
				}
				courseIndex++;
				if (map.get("value" + courseIndex).toString().length() > 0) {
					labLessonPlanInfo.setScene(LessonPlanEnum.OUT_CLASS);
				}
				courseIndex++;
				if (map.get("value" + courseIndex).toString().length() > 0) {
					labLessonPlanInfo.setRemark(LessonPlanEnum.STAR_COURSE);
				} else {
					labLessonPlanInfo.setRemark(LessonPlanEnum.ORDINARY_CLASS);
				}
				labLessonPlanInfo.setLevel(LessonPlanEnum.COURSE_LEVEL);
				if (null != (lessonLevel)) {
					labLessonPlanInfo.setParent(lessonLevel);
				}
				labLessonPlanInfo.setSubject(lessonLevel.getSubject());
				labLessonPlanInfo.setPlan(labLessonPlan);
				labLessonPlanInfo.setGrade(lessonLevel.getGrade());
				labLessonPlanInfo.setGradeYear(lessonLevel.getGradeYear());
				labLessonPlanInfo.setRequired(lessonLevel.getRequired());
				if (labLessonPlanInfo.getRemark().equals(LessonPlanEnum.STAR_COURSE)) {
					lessonLevel.setRemark(labLessonPlanInfo.getRemark());
				}
				if (labLessonPlanInfo.getDemonstration().equals(LessonPlanEnum.NEED)) {
					lessonLevel.setDemonstration(labLessonPlanInfo.getDemonstration());
				}
				if (labLessonPlanInfo.getScene().equals(LessonPlanEnum.IN_CLASS)) {
					lessonLevel.setScene(labLessonPlanInfo.getScene());
				}
				labLessonPlanInfo.setNodePath(lessonLevel.getNodePath() + "/" + lessonLevel.getId());
				infoDao.update(lessonLevel);
				infoDao.save(labLessonPlanInfo);
			}
		}
	}

	public LessonPlanEnum getLessPlanLevel(int level) {
		switch (level) {
		case 1:
			return LessonPlanEnum.FIRST_LEVEL;
		case 2:
			return LessonPlanEnum.SECOND_LEVEL;
		case 3:
			return LessonPlanEnum.THIRD_LEVEL;
		case 4:
			return LessonPlanEnum.FOURTH_LEVEL;
		case 5:
			return LessonPlanEnum.FIFTH_LEVEL;
		case 6:
			return LessonPlanEnum.SIXTH_LEVEL;
		case 7:
			return LessonPlanEnum.SEVENTH_LEVEL;
		case 0:
			return LessonPlanEnum.COURSE_LEVEL;
		case -1:
			return LessonPlanEnum.LESSON_LEVEL;
		}
		return null;
	}

	@Transactional
	public List getLessonPlanList(Integer start, Integer length) {
		start = Integer.valueOf(start.intValue() / length.intValue() + 1);
		String hql = "from LabLessonPlan where status = ?";
		// planDao.findByStatus("可用");
		List<LabLessonPlan> labLessonPlans = planDao.findByStatus(CommonStatusEnum.AVAILABLE);
		// planDao.pageQuery(hql, start, length, new Object[] {
		// CommonStatusEnum.AVAILABLE });
		List<Map<String, Object>> list = new ArrayList();
		for (LabLessonPlan labLessonPlan : labLessonPlans) {
			Map<String, Object> map = new HashMap();
			map.put("id", Long.valueOf(labLessonPlan.getId()));
			map.put("name", labLessonPlan.getName());
			map.put("version", labLessonPlan.getVersion().getDesc());
			map.put("schoolTypeName", labLessonPlan.getSchoolType().getTypeValue());
			map.put("schoolType", Long.valueOf(labLessonPlan.getSchoolType().getId()));
			list.add(map);
		}
		return list;
	}

	@Transactional
	public Long getLessonPlanCount() {
		return planDao.findDataCount1(new Object[] { CommonStatusEnum.AVAILABLE });
	}

	@Transactional
	public void delLessonPlan(Long planId) throws BizException {
		// LabLessonPlan labLessonPlan =
		// (LabLessonPlan)planDao.getById(LabLessonPlan.class, planId);
		LabLessonPlan labLessonPlan = planDao.findByID(planId);
		if (null == (labLessonPlan)) {
			throw new BizException("教学计划不存在");
		}
		labLessonPlan.setStatus(CommonStatusEnum.DELETED);
	}

	@Transactional
	public List<Map<String, Object>> getPlanInfoTree(Long planId) throws Exception {
		LabLessonPlanInfo myPlan = infoDao.findByPlanID(planId);
		Long parentId = myPlan.getId();
		LabLessonPlanInfo parent = infoDao.findByID(parentId);
		List<LabLessonPlanInfo> labLessonPlanInfos = infoDao.findByParent(parent);
		List<Map<String, Object>> firstPlanInfos = new ArrayList<Map<String, Object>>();
		Set<Long> set = new HashSet<Long>();
		set.add(planId);
		set.add(parent.getId());
		for (LabLessonPlanInfo labLessonPlanInfo : labLessonPlanInfos) {
			List<LabLessonPlanInfo> secondPlanInfos = infoDao.findByParent(labLessonPlanInfo);
			set.add(labLessonPlanInfo.getId());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", labLessonPlanInfo.getId() + "");
			map.put("text", labLessonPlanInfo.getTitle());
			map.put("lessonLevel", labLessonPlanInfo.getLevel());
			map.put("remark", labLessonPlanInfo.getRemark());
			map.put("demonstration", labLessonPlanInfo.getDemonstration());
			map.put("scene", labLessonPlanInfo.getScene());
			if (null != labLessonPlanInfo.getGrade())
				map.put("grade", Long.valueOf(labLessonPlanInfo.getGrade().getId()));
			if (null != labLessonPlanInfo.getGradeYear())
				map.put("gradeYear", labLessonPlanInfo.getGradeYear());
			map.put("children", secondJson(set, secondPlanInfos));
			firstPlanInfos.add(map);
		}
		return firstPlanInfos;
	}

	@Transactional
	private List<Object> secondJson(Set<Long> set, List<LabLessonPlanInfo> secondPlanInfos) throws Exception {
		List<Object> secondaryList = new ArrayList<>();
		if (secondPlanInfos == null || secondPlanInfos.size() == 0) {
			return null;
		}
		for (LabLessonPlanInfo labLessonPlanInfo : secondPlanInfos) {
			if (set.contains(labLessonPlanInfo.getId())) {
				continue;
			}
			System.out.println("set=" + set);
			set.add(labLessonPlanInfo.getId());
			List<LabLessonPlanInfo> thirdPlanInfos = infoDao.findByParent(labLessonPlanInfo);
			Map<String, Object> map = new HashMap<>();
			map.put("id", labLessonPlanInfo.getId() + "");
			map.put("text", labLessonPlanInfo.getTitle());
			map.put("lessonLevel", labLessonPlanInfo.getLevel());
			map.put("remark", labLessonPlanInfo.getRemark());
			map.put("demonstration", labLessonPlanInfo.getDemonstration());
			map.put("scene", labLessonPlanInfo.getScene());
			if (null != labLessonPlanInfo.getGrade())
				map.put("grade", Long.valueOf(labLessonPlanInfo.getGrade().getId()));
			if (null != labLessonPlanInfo.getGradeYear())
				map.put("gradeYear", labLessonPlanInfo.getGradeYear());
			map.put("children", secondJson(set, thirdPlanInfos));
			secondaryList.add(map);
		}
		return secondaryList;
	}

	/**
	 * 非苏教版教学目录(分学期、年级)
	 * 
	 */
	public List<Map<String, Object>> getLessonPlanInfoTree(Long planId, Long parentId) {
		List<Map<String, Object>> list = new ArrayList();
		if (!planId.equals(Long.valueOf(0L))) {
			LabLessonPlan labLessonPlan = planDao.findByID(planId);
			List<LabLessonPlanInfo> labLessonPlanInfos = infoDao.findByPlanAndLevel(planId, 0);
			for (LabLessonPlanInfo labLessonPlanInfo : labLessonPlanInfos) {
				Map<String, Object> map = new HashMap();
				map.put("id", labLessonPlanInfo.getId() + "");
				map.put("text", labLessonPlanInfo.getTitle());
				if (null != (labLessonPlanInfo.getLabLessonPlanInfos())) {
					map.put("children", Boolean.valueOf(true));
				}
				map.put("lessonLevel", labLessonPlanInfo.getLevel());
				if (null != (labLessonPlanInfo.getParent())) {
					map.put("parent", Long.valueOf(labLessonPlanInfo.getParent().getId()));
				} else {
					map.put("parent", "#");
				}
				if (labLessonPlanInfo.getLevel().equals(LessonPlanEnum.COURSE_LEVEL)) {
					map.put("remark", labLessonPlanInfo.getRemark());
					map.put("demonstration", labLessonPlanInfo.getDemonstration());
					map.put("scene", labLessonPlanInfo.getScene());
				}
				if (labLessonPlanInfo.getLevel().equals(LessonPlanEnum.THIRD_LEVEL)) {
					map.put("grade", Long.valueOf(labLessonPlanInfo.getGrade().getId()));
					map.put("gradeYear", labLessonPlanInfo.getGradeYear());
				}
				list.add(map);
			}
		} else {
			LabLessonPlanInfo parent = infoDao.findByID(parentId);
			List<LabLessonPlanInfo> labLessonPlanInfos = infoDao.findByParent(parent);
			for (LabLessonPlanInfo labLessonPlanInfo : labLessonPlanInfos) {
				Map<String, Object> map = new HashMap();
				map.put("id", labLessonPlanInfo.getId() + "");
				map.put("text", labLessonPlanInfo.getTitle());
				if (null != (labLessonPlanInfo.getLabLessonPlanInfos())) {
					map.put("children", Boolean.valueOf(true));
				}
				map.put("lessonLevel", labLessonPlanInfo.getLevel());
				if (null != (labLessonPlanInfo.getParent())) {
					map.put("parent", Long.valueOf(labLessonPlanInfo.getParent().getId()));
				} else {
					map.put("parent", "#");
				}
				if (labLessonPlanInfo.getLevel().equals(LessonPlanEnum.COURSE_LEVEL)) {
					map.put("remark", labLessonPlanInfo.getRemark());
					map.put("demonstration", labLessonPlanInfo.getDemonstration());
					map.put("scene", labLessonPlanInfo.getScene());
				}
				if (((labLessonPlanInfo.getLevel().equals(LessonPlanEnum.THIRD_LEVEL))
						|| (labLessonPlanInfo.getLevel().equals(LessonPlanEnum.LESSON_LEVEL))
						|| (labLessonPlanInfo.getLevel().equals(LessonPlanEnum.COURSE_LEVEL)))
						&& (null != (labLessonPlanInfo.getGrade()))) {
					map.put("grade", Long.valueOf(labLessonPlanInfo.getGrade().getId()));
					map.put("gradeYear", labLessonPlanInfo.getGradeYear());
				}
				if ((labLessonPlanInfo.getLevel().equals(LessonPlanEnum.FOURTH_LEVEL))
						&& (null != (labLessonPlanInfo.getGrade()))) {
					map.put("grade", Long.valueOf(labLessonPlanInfo.getGrade().getId()));
					map.put("gradeYear", labLessonPlanInfo.getGradeYear());
				}
				list.add(map);
			}
		}
		return list;
	}

	public void updateLessPlanInfo(Long id, String title, LessonPlanEnum remark, LessonPlanEnum scene,
			LessonPlanEnum demonstration, Long grade, TermEnum gradeYear) throws BizException {
		LabLessonPlanInfo labLessonPlanInfo = infoDao.findByID(id);
		if (null == (labLessonPlanInfo)) {
			throw new BizException("目录信息不存在");
		}
		if (null == (title)) {
			throw new BizException("标题目录不能为空");
		}
		if (null != gradeYear) {
			labLessonPlanInfo.setGradeYear(gradeYear);
		}
		labLessonPlanInfo.setTitle(title);
		// LabType labType = (LabType)planDao.getById(LabType.class, grade);
		LabType labType = planDao.findGradeById(grade);
		if (null != (labType)) {
			labLessonPlanInfo.setGrade(labType);
		}
		if (null != (remark)) {
			labLessonPlanInfo.setRemark(remark);
		}
		if (null != (scene)) {
			labLessonPlanInfo.setScene(scene);
		}
		if (null != (demonstration)) {
			labLessonPlanInfo.setDemonstration(demonstration);
		}
		// String hql = "update LabLessonPlanInfo set grade = ?,gradeYear = ?
		// where nodePath like ?";
		// infoDao.updateInfo(hql, new Object[] { labType, gradeYear, "%" +
		// labLessonPlanInfo.getId() + "%" });
		infoDao.updateInfo(grade, gradeYear, "%" + labLessonPlanInfo.getId() + "%");
		LabLessonPlanInfo parent = labLessonPlanInfo.getParent();
		if (null != (parent)) {
			Set<LabLessonPlanInfo> labLessonPlanInfos = parent.getLabLessonPlanInfos();
			parent.setScene(null);
			parent.setRemark(null);
			parent.setDemonstration(null);
			Iterator<LabLessonPlanInfo> infoIterator = labLessonPlanInfos.iterator();
			while (infoIterator.hasNext()) {
				LabLessonPlanInfo info = (LabLessonPlanInfo) infoIterator.next();
				if ((null != (info.getRemark())) && (info.getRemark().equals(LessonPlanEnum.STAR_COURSE))) {
					parent.setRemark(info.getRemark());
				}
				if ((null != (info.getDemonstration())) && (info.getDemonstration().equals(LessonPlanEnum.NEED))) {
					parent.setDemonstration(info.getDemonstration());
				}
				if ((null != (info.getScene())) && (info.getScene().equals(LessonPlanEnum.IN_CLASS))) {
					parent.setScene(info.getScene());
				}
			}
		}
	}

	public void deleteLessonPlanInfo(Long id) {
		// LabLessonPlanInfo labLessonPlanInfo =
		// (LabLessonPlanInfo)infoDao.getById(LabLessonPlanInfo.class, id);
		LabLessonPlanInfo labLessonPlanInfo = infoDao.findByID(id);
		LabLessonPlanInfo parent = labLessonPlanInfo.getParent();
		infoDao.deleteByID(id);
		if (null != (parent)) {
			Set<LabLessonPlanInfo> labLessonPlanInfos = parent.getLabLessonPlanInfos();

			parent.setScene(null);
			parent.setRemark(null);
			parent.setDemonstration(null);
			Iterator<LabLessonPlanInfo> infoIterator = labLessonPlanInfos.iterator();
			while (infoIterator.hasNext()) {
				LabLessonPlanInfo info = (LabLessonPlanInfo) infoIterator.next();
				if ((null != (info.getRemark())) && (info.getRemark().equals(LessonPlanEnum.STAR_COURSE))) {
					parent.setRemark(info.getRemark());
				}
				if ((null != (info.getDemonstration())) && (info.getDemonstration().equals(LessonPlanEnum.NEED))) {
					parent.setDemonstration(info.getDemonstration());
				}
				if ((null != (info.getScene())) && (info.getScene().equals(LessonPlanEnum.IN_CLASS))) {
					parent.setScene(info.getScene());
				}
			}
		}
	}

	public void addLessPlanInfo(Long parentId, String title, LessonPlanEnum remark, LessonPlanEnum scene,
			LessonPlanEnum demonstration, Long grade, TermEnum gradeYear) throws BizException {
		// LabLessonPlanInfo labLessonPlanInfo =
		// (LabLessonPlanInfo)planDao.getById(LabLessonPlanInfo.class,
		// parentId);
		LabLessonPlanInfo labLessonPlanInfo = infoDao.findByID(parentId);
		// LabType labType = (LabType)planDao.getById(LabType.class, grade);
		LabType labType = planDao.findGradeById(grade);
		if (null == (labLessonPlanInfo)) {
			throw new BizException("父目录不存在");
		}
		if (null == title) {
			throw new BizException("目录标题不能为空");
		}
		if ((!grade.equals(Long.valueOf(0L))) && (null == labType)) {
			throw new BizException("年级信息不存在");
		}
		LabLessonPlanInfo newNode = new LabLessonPlanInfo();
		newNode.setTitle(title);
		if (null != (remark)) {
			newNode.setRemark(remark);
			if (newNode.getRemark().equals(LessonPlanEnum.STAR_COURSE)) {
				labLessonPlanInfo.setRemark(newNode.getRemark());
			}
		}
		if (null != (scene)) {
			newNode.setScene(scene);
			if (newNode.getScene().equals(LessonPlanEnum.IN_CLASS)) {
				labLessonPlanInfo.setScene(newNode.getScene());
			}
		}
		if (null != (demonstration)) {
			newNode.setDemonstration(demonstration);
			if (newNode.getDemonstration().equals(LessonPlanEnum.NEED)) {
				labLessonPlanInfo.setDemonstration(newNode.getDemonstration());
			}
		}
		if (null != (labType)) {
			newNode.setGrade(labType);
		}
		if (null != (gradeYear)) {
			newNode.setGradeYear(gradeYear);
		}
		if (labLessonPlanInfo.getLevel().equals(LessonPlanEnum.FIRST_LEVEL)) {
			newNode.setLevel(LessonPlanEnum.SECOND_LEVEL);
		}
		if (labLessonPlanInfo.getLevel().equals(LessonPlanEnum.SECOND_LEVEL)) {
			newNode.setLevel(LessonPlanEnum.THIRD_LEVEL);
		}
		if (labLessonPlanInfo.getLevel().equals(LessonPlanEnum.THIRD_LEVEL)) {
			newNode.setLevel(LessonPlanEnum.LESSON_LEVEL);
		}
		if (labLessonPlanInfo.getLevel().equals(LessonPlanEnum.LESSON_LEVEL)) {
			newNode.setLevel(LessonPlanEnum.COURSE_LEVEL);
		}
		if (null != (labLessonPlanInfo.getGrade())) {
			newNode.setGrade(labLessonPlanInfo.getGrade());
		}
		if (null != (labLessonPlanInfo.getGradeYear())) {
			newNode.setGradeYear(labLessonPlanInfo.getGradeYear());
		}
		newNode.setParent(labLessonPlanInfo);
		infoDao.save(newNode);
	}

	public List getLessonPlanListBySchoolType(LabType schoolType) {
		String hql = "from LabLessonPlan where schoolType = ? and status = '可用'";
		// List<LabLessonPlan> labLessonPlans = planDao.queryForList(hql, new
		// Object[] { schoolType, CommonStatusEnum.AVAILABLE });
		List<LabLessonPlan> labLessonPlans = planDao.findByType(schoolType, CommonStatusEnum.AVAILABLE);
		List<Map<String, Object>> list = new ArrayList();
		for (LabLessonPlan labLessonPlan : labLessonPlans) {
			Map<String, Object> map = new HashMap();
			map.put("id", Long.valueOf(labLessonPlan.getId()));
			map.put("text", labLessonPlan.getName());
			list.add(map);
		}
		return list;
	}

	public List<Map<String, Object>> findLesson(int planid) throws Exception {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setLimit(1000);
		List<Map<String, Object>> list = infoDao.findDataList1(new Object[] { planid }, pageInfo);
		String subject1 = "";
		String subject2 = "";
		Map<String, List<Map<String, Object>>> mp = new HashMap<String, List<Map<String, Object>>>();
		Map<String, Object> mpd = new HashMap<String, Object>();
		List<Map<String, Object>> list0 = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			if (null != map.get("title"))
				subject1 = map.get("title").toString();
			subject2 = map.get("subject").toString();
			List<Map<String, Object>> list1 = infoDao.findDataList2(new Object[] { planid, "LESSON_LEVEL", subject2 },
					pageInfo);
			for (Map<String, Object> map2 : list1) {
				String group = "";
				if (null != map2.get("demonstration")) {
					if (group.equals("NEED")) {
						list0.add(map2);
					}
				}
			}
		}
		return list0;
	}

	public List<String> findLesson(SysUser user) throws Exception {
		int areaid = user.getAreaid();
		Organization school = organDao.findByID(areaid);
		LabSchoolLesson schoolLesson = schoollessonDao.findBySchoolStatus(school, CommonStatusEnum.AVAILABLE);
		int planid = (int) schoolLesson.getLabLessonPlan().getId();
		PageInfo pageInfo = new PageInfo();
		pageInfo.setLimit(1000);
		List<Map<String, Object>> list = infoDao.findDataList1(new Object[] { planid }, pageInfo);
		String subject2 = "";
		Map<String, List<Map<String, Object>>> mp = new HashMap<String, List<Map<String, Object>>>();
		Map<String, Object> mpd = new HashMap<String, Object>();
		List<String> list0 = new ArrayList<String>();
		for (Map<String, Object> map : list) {
			if (null != map.get("title"))
				subject2 = map.get("subject").toString();
			List<Map<String, Object>> list1 = infoDao.findDataList2(new Object[] { planid, "LESSON_LEVEL", subject2 },
					pageInfo);
			for (Map<String, Object> map2 : list1) {
				String group = "";
				if (null != map2.get("demonstration")) {
					group = map2.get("demonstration").toString();
					if (group.equals("NEED")) {
						if (null != map2.get("title"))
							list0.add(map2.get("title").toString());
						else
							list0.add("");
					}
				}
			}
		}
		return list0;
	}

	public Map<String, Object> findLabLesson(int planid) throws Exception {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setLimit(1000);
		LabLessonPlanInfo planinfo = new LabLessonPlanInfo();
		List<Map<String, Object>> list = infoDao.findDataList1(new Object[] { planid }, pageInfo);
		String subject1 = "";
		String subject2 = "";
		Map<String, Object> mp = new HashMap<String, Object>();
		Map<String, Object> mpd = new HashMap<String, Object>();
		for (Map<String, Object> map : list) {
			if (null != map.get("title"))
				subject1 = map.get("title").toString();
			subject2 = map.get("subject").toString();
			List<Map<String, Object>> list1 = infoDao.findDataList2(new Object[] { planid, "LESSON_LEVEL", subject2 },
					pageInfo);
			Map<String, Object> mp0 = new HashMap<String, Object>();
			Map<String, Object> mpp = new HashMap<String, Object>();
			Map<String, Object> mp11 = new HashMap<String, Object>();
			Map<String, Object> mp12 = new HashMap<String, Object>();
			Map<String, Object> mp21 = new HashMap<String, Object>();
			Map<String, Object> mp22 = new HashMap<String, Object>();
			for (Map<String, Object> map2 : list1) {
				String group = "";
				if (null != map2.get("demonstration")) {
					group = map2.get("demonstration").toString();
					if (group.equals("NEED")) {
						group = "分组";
						mp11.put(map2.get("title").toString(), map2.get("title").toString());
						mp12.put("items", mp11);
					} else {
						mp21.put(map2.get("title").toString(), map2.get("title").toString());
						mp22.put("items", mp21);
						group = "演示";
					}
				}
				// mp1.put(map2.get("title").toString(),
				// map2.get("title").toString());
				// mp1.put("items", mp1);
				// mp1.put(group, mp1);
				// mp0.put("items", mp1);
			}
			mp0.put("分组", mp12);
			mp0.put("演示", mp22);
			mpp.put("items", mp0);
			mpd.put(subject1, mpp);
		}
		mp.put("threeSelectData", mpd);
		return mp;
	}

	public LabLessonPlan addLabLessonPlan(String name, Long schoolTypeId, TeachVersionEnum teachVersion)
			throws BizException {
		if (null == (name)) {
			throw new BizException("教学目录名称不能为空");
		}
		if (null == (teachVersion)) {
			throw new BizException("教学目录版本不能为空");
		}
		LabType schoolType = planDao.findTypeByID(schoolTypeId);
		// LabType schoolType = (LabType)planDao.getById(LabType.class,
		// schoolTypeId);
		if (null == (schoolType)) {
			throw new BizException("适应学校类型不能为空");
		}
		LabLessonPlan labLessonPlan = new LabLessonPlan();
		labLessonPlan.setVersion(teachVersion);
		labLessonPlan.setName(name);
		labLessonPlan.setSchoolType(schoolType);
		labLessonPlan.setStatus(CommonStatusEnum.AVAILABLE);
		planDao.save(labLessonPlan);
		return labLessonPlan;
	}

}
