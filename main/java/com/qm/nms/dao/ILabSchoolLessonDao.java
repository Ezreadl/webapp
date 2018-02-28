package com.qm.nms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qm.core.base.IBaseDao;
import com.qm.core.base.QmQuery;
import com.qm.nms.domain.LabLessonPlan;
import com.qm.nms.domain.LabSchoolLesson;
import com.qm.nms.domain.LabType;
import com.qm.nms.domain.Organization;
import com.qm.nms.domainEnum.CommonStatusEnum;

@Repository
public interface ILabSchoolLessonDao extends IBaseDao<LabSchoolLesson, Integer> {

	@Query(value = "select * from  LabSchoolLesson par where par.id = ?1", nativeQuery = true)
	public LabSchoolLesson findByID(long id);

	@Query(value = "select * from  LabSchoolLesson par where par.title = ?1", nativeQuery = true)
	public List<LabSchoolLesson> findByTitle(String title);

	@Query(value = "from  LabSchoolLesson par where par.school = ?1 and par.status = ?2")
	public LabSchoolLesson findBySchoolStatus(Organization school,CommonStatusEnum status);

	@QmQuery(value = "select count(id) from LabSchoolLesson par where par.status = ?1", nativeQuery = true)
	public long findDataCount1(Object[] params);
	
	@Query(value = "select * from LabSchoolLesson par par.school_type = ?1 and par.status = ?2", nativeQuery = true)
	public List<LabSchoolLesson> findByType(LabType schoolType, CommonStatusEnum status);

	@Query(value = "select * from LabType par where par.type_name = ?1", nativeQuery = true)
	public List<LabType> findByName(String typeName);

	@Query(value = "select * from LabType par where par.id = ?1 and type_name= 'grade' ", nativeQuery = true)
	public LabType findGradeById(long id);	
	
	@Query(value = "select * from LabType par where par.id = ?1", nativeQuery = true)
	public LabType findTypeByID(long id);


}
