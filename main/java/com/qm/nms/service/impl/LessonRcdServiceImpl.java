package com.qm.nms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.nms.dao.ILessonRcdDao;
import com.qm.nms.query.LessonRecordQuery;
import com.qm.nms.service.ILessonRcdService;
@Service
public class LessonRcdServiceImpl implements ILessonRcdService{
	@Autowired
	public ILessonRcdDao lessonRcdDao;

	@Override
	public GridListData findDataList1(LessonRecordQuery query, PageInfo pageInfo) throws Exception {
		GridListData gld = lessonRcdDao.findDataList1(query, pageInfo);
		return gld;
	}
	public List<Map<String, Object>> findData(LessonRecordQuery query)  throws Exception{
		String sql = "select * from lessonRecord ";
		List<Object> params = new ArrayList<Object>();
		List<Map<String, Object>> list = lessonRcdDao.findDataList(sql, params.toArray(), null, true);
		System.out.println();
		return list;
	}
}
