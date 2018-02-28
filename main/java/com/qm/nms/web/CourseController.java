package com.qm.nms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qm.core.base.BaseController;
import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.nms.query.CourseQuery;
import com.qm.nms.service.ICourseService;

@Controller
@RequestMapping("/course")
public class CourseController {
	@Autowired
	public ICourseService courseService;

	@RequestMapping("/find")
	@ResponseBody
	public GridListData findDataList1(CourseQuery query, PageInfo pageInfo) throws Exception {
		GridListData gld = new GridListData();
		gld = courseService.findDataList1(query, pageInfo);
		return gld;
	}

}
