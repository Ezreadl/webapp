package com.qm.nms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.core.util.DateUtil;
import com.qm.nms.dao.IBookContentDao;
import com.qm.nms.domain.BookContent;
import com.qm.nms.query.BookContentQuery;
import com.qm.nms.service.IBookContentService;
import com.qm.sys.domain.SysUser;
@Service
public class BookContentServiceImpl implements IBookContentService{
	@Autowired
	public IBookContentDao bookContentDao;

	@Override
	public GridListData findBookContList(BookContentQuery query, PageInfo pageInfo) throws Exception {
		// TODO Auto-generated method stub
		GridListData gld = new GridListData();
		gld = bookContentDao.findDataList1(query, pageInfo);
		return gld;
	}

	@Override
	public BookContent findOne(int oid) {
		BookContent bc = new BookContent();
		bc = bookContentDao.findOne(oid);
		return bc;
	}
	/**
	 * 添加教学目录
	 */
	public void saveBookContent(SysUser user,BookContent book) throws Exception{
		int oid =  book.getOid();
		BookContent old = bookContentDao.findOne(oid);
		book.updateOptInfo(old, user.getUserName());
		if(null == old)
			bookContentDao.save(book);
		else 
			bookContentDao.update(book);
	}
	/**
	 * 删除教学目录
	 */
	public void changeDelFlg(SysUser user,List<Integer> oidList,int delFlg) throws Exception {
		List<BookContent> lor = bookContentDao.findAll(oidList);
		for (BookContent old : lor) {
			old.setOptUserName(user.getUserName());
			old.setOptDateTime(DateUtil.getDate());
			old.setDelFlg(delFlg);
			bookContentDao.delete(old);
		}
	}		
}
