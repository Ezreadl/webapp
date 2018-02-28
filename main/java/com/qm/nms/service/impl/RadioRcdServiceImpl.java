package com.qm.nms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.nms.dao.IRadioRcdDao;
import com.qm.nms.domain.RadioRecord;
import com.qm.nms.query.RadioRecordQuery;
import com.qm.nms.service.IRadioRcdService;
import com.qm.sys.domain.SysUser;
@Service
public class RadioRcdServiceImpl implements IRadioRcdService{
	@Autowired
	public IRadioRcdDao radioRcdDao;

	@Override
	public GridListData findDataList1(RadioRecordQuery query, PageInfo pageInfo) throws Exception {
		GridListData gld = new GridListData();
		gld = radioRcdDao.findDataList1(query, pageInfo);
		return gld;
	}
	/**
	 * 添加记录
	 */
	public void saveRadioRcd(SysUser user,RadioRecord radioRcd) throws Exception{
		int oid =  radioRcd.getOid();
		RadioRecord old = radioRcdDao.findOne(oid);
		if(null == old)
			radioRcdDao.save(radioRcd);
		else 
			radioRcdDao.update(radioRcd);
	}
	/**
	 * 删除记录
	 */
	public void changeDelFlg(SysUser user,List<Integer> oidList,int delFlg) throws Exception {
		List<RadioRecord> lor = radioRcdDao.findAll(oidList);
		for (RadioRecord old : lor) {
			radioRcdDao.delete(old);
		}
	}	
}
