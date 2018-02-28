package com.qm.nms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.core.util.DateUtil;
import com.qm.nms.dao.ICourseDao;
import com.qm.nms.domain.Course;
import com.qm.nms.query.CourseQuery;
import com.qm.nms.service.ICourseService;
import com.qm.sys.domain.SysUser;
@Service
public class CourseServiceImpl implements ICourseService{
	@Autowired
	public ICourseDao courseDao;

	@Transactional
	@Override
	public GridListData findDataList1(CourseQuery query, PageInfo pageInfo) throws Exception {
		GridListData gld = new GridListData();
		gld = courseDao.findDataList1(query, pageInfo);
		return gld;
	}
	/**
	 * 添加课程
	 */
	@Transactional	
	public void saveCourse(SysUser user,Course Course) throws Exception{
		int oid =  Course.getOid();
		Course old = courseDao.findOne(oid);
		Course.updateOptInfo(old, user.getUserName());
		if(null == old)
			courseDao.save(Course);
		else 
			courseDao.update(Course);
	}
	/**
	 * 删除课程
	 */
	@Transactional	
	public void changeDelFlg(SysUser user,List<Integer> oidList,int delFlg) throws Exception {
		List<Course> lor = courseDao.findAll(oidList);
		for (Course old : lor) {
			old.setOptUserName(user.getUserName());
			old.setOptDateTime(DateUtil.getDate());
			old.setDelFlg(delFlg);
			courseDao.delete(old);
		}
	}	
}
