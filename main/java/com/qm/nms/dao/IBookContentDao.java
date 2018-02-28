package com.qm.nms.dao;

import org.springframework.stereotype.Repository;

import com.qm.core.base.GridListData;
import com.qm.core.base.IBaseDao;
import com.qm.core.base.IQuery;
import com.qm.core.base.PageInfo;
import com.qm.core.base.QmQuery;
import com.qm.nms.domain.BookContent;

@Repository
public interface IBookContentDao extends IBaseDao<BookContent,Integer>{
	
	@QmQuery(value="select par.* from BookContent par { #& par.bookName=:bookName #& par.bookCont=:bookCont #& par.pid=:pid #& par.grade=:grade #& par.classType=:classType #& par.term=:term }",nativeQuery=true)
	public GridListData findDataList1(IQuery query,PageInfo pageInfo) throws Exception;

}
