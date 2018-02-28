package com.qm.nms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.nms.dao.IMsgRcdDao;
import com.qm.nms.domain.MsgRecord;
import com.qm.nms.query.MsgRecordQuery;
import com.qm.nms.service.IMsgRcdService;
import com.qm.sys.domain.SysUser;
@Service
public class MsgRcdServiceImpl implements IMsgRcdService{
	@Autowired
	public IMsgRcdDao msgRcdDao;

	@Override
	public GridListData findDataList1(MsgRecordQuery query, PageInfo pageInfo) throws Exception {
		GridListData gld = new GridListData();
		gld = msgRcdDao.findDataList1(query, pageInfo);
		return gld;
	}
	/**
	 * 添加消息记录
	 */
	public void saveMsgRcd(SysUser user,MsgRecord msgRcd) throws Exception{
		int oid =  msgRcd.getOid();
		MsgRecord old = msgRcdDao.findOne(oid);
		if(null == old)
			msgRcdDao.save(msgRcd);
		else 
			msgRcdDao.update(msgRcd);
	}
	/**
	 * 删除消息记录
	 */
	public void changeDelFlg(SysUser user,List<Integer> oidList,int delFlg) throws Exception {
		List<MsgRecord> lor = msgRcdDao.findAll(oidList);
		for (MsgRecord old : lor) {
			old.setDelFlg(delFlg);
			msgRcdDao.delete(old);
		}
	}	
}
