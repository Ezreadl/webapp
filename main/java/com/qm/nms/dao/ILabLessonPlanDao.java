package com.qm.nms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qm.core.base.GridListData;
import com.qm.core.base.IBaseDao;
import com.qm.core.base.IQuery;
import com.qm.core.base.PageInfo;
import com.qm.core.base.QmQuery;
import com.qm.nms.domain.LabLessonPlan;
import com.qm.nms.domain.LabType;
import com.qm.nms.domainEnum.CommonStatusEnum;

@Repository
public interface ILabLessonPlanDao extends IBaseDao<LabLessonPlan, Integer> {

	@Query(value = "select * from  LabLessonPlan par where par.id = ?1", nativeQuery = true)
	public LabLessonPlan findByID(long id);

	@QmQuery(value = "select id,name from LabLessonPlan par", nativeQuery = true)
	public GridListData findDataList1(IQuery query,PageInfo pageInfo) throws Exception;
	
	@Query(value = "select * from  LabLessonPlan par where par.title = ?1", nativeQuery = true)
	public List<LabLessonPlan> findByTitle(String title);

	@Query(value = "select * from  LabLessonPlan par where par.status = ?1", nativeQuery = true)
	public List<LabLessonPlan> findByStatus(CommonStatusEnum status);

	@QmQuery(value = "select count(id) from LabLessonPlan par where par.status = ?1", nativeQuery = true)
	public long findDataCount1(Object[] params);
	
	@Query(value = "select * from LabLessonPlan par par.school_type = ?1 and par.status = ?2", nativeQuery = true)
	public List<LabLessonPlan> findByType(LabType schoolType, CommonStatusEnum status);

	@Query(value = "select * from LabType par where par.type_name = ?1", nativeQuery = true)
	public List<LabType> findByName(String typeName);

	@Query(value = "select * from LabType par where par.id = ?1 and type_name= 'grade' ", nativeQuery = true)
	public LabType findGradeById(long id);	
	
	@Query(value = "select * from LabType par where par.id = ?1", nativeQuery = true)
	public LabType findTypeByID(long id);


}
