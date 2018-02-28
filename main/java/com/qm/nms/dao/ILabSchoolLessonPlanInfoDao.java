package com.qm.nms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qm.core.base.IBaseDao;
import com.qm.core.base.QmQuery;
import com.qm.nms.domain.LabSchoolLesson;
import com.qm.nms.domain.LabSchoolLessonPlanInfo;
import com.qm.nms.domain.LabType;
import com.qm.nms.domainEnum.TermEnum;

@Repository
public interface ILabSchoolLessonPlanInfoDao extends IBaseDao<LabSchoolLessonPlanInfo, Integer> {

	@Query(value = "select * from  LabSchoolLessonPlanInfo par where par.id = ?1", nativeQuery = true)
	public LabSchoolLessonPlanInfo findByID(long id);

	@Query(value = "select * from LabSchoolLessonPlanInfo where lessonId = ?1", nativeQuery = true)
	public List<LabSchoolLessonPlanInfo> findByLessoonID(Long lessonid);		
	
	@Query(value = "select * from  LabSchoolLessonPlanInfo par where par.title = ?1", nativeQuery = true)
	public List<LabSchoolLessonPlanInfo> findByTitle(String title);

	@Query(value = "from LabSchoolLessonPlanInfo where parent = ?1 order by title")
	public List<LabSchoolLessonPlanInfo> findByLesson(LabSchoolLessonPlanInfo parent);		
	
	@Modifying
	@Query(value="update LabSchoolLessonPlanInfo set grade = ?1,gradeYear = ?2 where nodePath like ?3")
	public void updatePlan(LabType grade,TermEnum gradeYear,String nodePath);		
	
	@Query(value = "from LabSchoolLessonPlanInfo where labSchoolLesson = ?1 and level = ?2 order by title")
	public List<LabSchoolLessonPlanInfo> findByLessonLevel(LabSchoolLesson labSchoolLesson,String level);	
	
	@Query(value = "from LabSchoolLesson where school = ?1 and status = ?2 ")
	public List<LabSchoolLessonPlanInfo> findBySchoolStatus(Object school,String status);
	
	@Query(value = "select * from  LabSchoolLessonPlanInfo par where par.status = ?1", nativeQuery = true)
	public List<LabSchoolLessonPlanInfo> findByStatus(String status);

	@QmQuery(value = "select count(id) from LabSchoolLessonPlanInfo par where par.status = ?1", nativeQuery = true)
	public long findDataCount1(Object[] params);
	
	@Query(value = "select * from LabSchoolLessonPlanInfo par par.school_type = ?1 and par.status = ?2", nativeQuery = true)
	public List<LabSchoolLessonPlanInfo> findByType(LabType schoolType, String status);

	@Query(value = "select * from LabType par where par.type_name = ?1", nativeQuery = true)
	public List<LabType> findByName(String typeName);

	@Query(value = "select * from LabType par where par.id = ?1 and type_name= 'grade' ", nativeQuery = true)
	public LabType findGradeById(long id);	
	
	@Query(value = "select * from LabType par where par.id = ?1", nativeQuery = true)
	public LabType findTypeByID(long id);


}
