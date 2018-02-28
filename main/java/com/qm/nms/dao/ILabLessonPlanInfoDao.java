package com.qm.nms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qm.core.base.IBaseDao;
import com.qm.core.base.PageInfo;
import com.qm.core.base.QmQuery;
import com.qm.nms.domain.LabLessonPlanInfo;
import com.qm.nms.domain.LabType;
import com.qm.nms.domainEnum.TermEnum;

@Repository
public interface ILabLessonPlanInfoDao  extends IBaseDao<LabLessonPlanInfo,Integer>{
	
	@Query(value="select * from  LabLessonPlanInfo par where par.id = ?1",nativeQuery=true)
	public LabLessonPlanInfo findByID(long id);		
	
	@Query(value="select * from  LabLessonPlanInfo par where par.plan_id = ?1 group by plan_id ",nativeQuery=true)
	public LabLessonPlanInfo findByPlanID(long id);	
	
	@Query(value="select * from  LabLessonPlanInfo par where par.title = ?1",nativeQuery=true)
	public List<LabLessonPlanInfo> findByTitle(String title);
	
	@QmQuery(value="select * from  LabLessonPlanInfo par where plan_id=?1 group by subject",nativeQuery=true)
	public List<Map<String,Object>> findDataList1(Object[] params,PageInfo pageInfo);	
	
	@QmQuery(value="select * from  LabLessonPlanInfo par where plan_id=?1 and level=?2 and subject=?3 ",nativeQuery=true)
	public List<Map<String,Object>> findDataList2(Object[] params,PageInfo pageInfo);
	
	@QmQuery(value="select * from  LabLessonPlanInfo par where title=?1 and level=?2 and subject=?3 ",nativeQuery=true)
	public List<Map<String,Object>> findDataList3(Object[] params,PageInfo pageInfo);
	
	@Query(value="from LabLessonPlanInfo where parent = ?")
	public List<LabLessonPlanInfo> findByParent(LabLessonPlanInfo parent);
	
	@Query(value="select * from LabType par where par.typeName = ?1",nativeQuery=true)
	public List<LabType> findByName(String title);	
	
	@Query(value="select * from LabLessonPlan where status = ?1",nativeQuery=true)
	public List<LabType> findByStatus(String title);
	
	@Query(value="select * from LabLessonPlanInfo where plan_id = ?1 and level = ?2",nativeQuery=true)
	public List<LabLessonPlanInfo> findByPlanAndLevel(long planid,int level);
	
	@Modifying
	@Query(value="update LabLessonPlanInfo set grade = ?1,gradeYear = ?2 where nodePath like ?3",nativeQuery=true)
	public void updateInfo(Long grade,TermEnum gradeYear,String nodePath);	
	
	@Modifying
	@Query(value = "delete from LabLessonPlanInfo par where par.id = ?1", nativeQuery = true)
	public void deleteByID(long id);
	
}
