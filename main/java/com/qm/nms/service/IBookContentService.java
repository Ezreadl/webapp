package com.qm.nms.service;

import org.springframework.stereotype.Repository;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.nms.domain.BookContent;
import com.qm.nms.query.BookContentQuery;

@Repository
public interface IBookContentService {
	
	public GridListData findBookContList(BookContentQuery query, PageInfo pageInfo) throws Exception;
	
	public BookContent findOne(int oid);

}
