package com.qm.nms.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.nms.query.LessonRecordQuery;

@Repository
public interface ILessonRcdService {

	public GridListData findDataList1(LessonRecordQuery query, PageInfo pageInfo) throws Exception;
	
	public List<Map<String, Object>> findData(LessonRecordQuery query) throws Exception;
	
}
