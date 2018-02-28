package com.qm.nms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qm.core.base.BaseController;
import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.core.base.WebStatus4P;
import com.qm.nms.query.LessonRecordQuery;
import com.qm.nms.service.ILessonRcdService;

@Controller
@RequestMapping("/lesson")
public class LessonRcdController {
	@Autowired
	public ILessonRcdService lessonRcdService;

	@RequestMapping(value="/findRcd",produces = {"application/json;chrset=UTF-8"})
	@ResponseBody
	public WebStatus4P findDataList1(LessonRecordQuery query, PageInfo pageInfo) throws Exception {
		GridListData gld = lessonRcdService.findDataList1(query, pageInfo);
		System.out.println(query.toString());
		System.out.println(gld.toString());
		return new WebStatus4P(gld);
	}

}
