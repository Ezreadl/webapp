package com.qm.nms.service;

import org.springframework.stereotype.Repository;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.nms.query.CourseQuery;

@Repository
public interface ICourseService {

	public GridListData findDataList1(CourseQuery query,PageInfo pageInfo) throws Exception;

}
