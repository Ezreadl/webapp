package com.qm.nms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qm.core.base.BaseController;
import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.core.base.WebStatus4P;
import com.qm.nms.domain.BookContent;
import com.qm.nms.query.BookContentQuery;
import com.qm.nms.service.IBookContentService;

@Controller
@RequestMapping("/book")
public class BookContentController extends BaseController{
	@Autowired
	private IBookContentService bookContentService;
	@RequestMapping("/find/{oid}")
	public String findContent(@PathVariable("oid") int oid) throws Exception{
		BookContent apt = bookContentService.findOne(oid);
		return "/bookcontent";	
	}
	@RequestMapping(value="/findName",produces ={"application/json;chrset=UTF-8" })
	@ResponseBody	
	public WebStatus4P findApptList(BookContentQuery query,PageInfo pageInfo) throws Exception{
		GridListData apt = bookContentService.findBookContList(query, pageInfo);
		System.out.println(apt.toString());
		return new WebStatus4P(apt);
	}		
}
