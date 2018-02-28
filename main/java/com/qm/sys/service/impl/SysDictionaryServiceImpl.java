package com.qm.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.core.util.DateUtil;
import com.qm.sys.dao.ISysDictionaryDao;
import com.qm.sys.domain.SysDictionary;
import com.qm.sys.domain.SysUser;
import com.qm.sys.query.SysDictionaryQuery;
import com.qm.sys.service.ISysDictionaryService;

@Service("sysDictionaryService")
public class SysDictionaryServiceImpl implements ISysDictionaryService{

	@Autowired
	private ISysDictionaryDao sysDictionaryDao;

	@Override
	public SysDictionary findSysDictionaryById(int oid) throws Exception {
		return sysDictionaryDao.findOne(oid);
	}

	@Override
	public List<SysDictionary> findSysDictionaryById(List<Integer> oids) throws Exception {
		return sysDictionaryDao.findAll(oids);
	}

	@Override
	public GridListData findSysDictionaryList(SysUser sysUser,SysDictionaryQuery query,PageInfo pageInfo) throws Exception{
		if(!sysUser.isAdmin()){
			query.setDelFlg(0);
		}
		GridListData gridData=sysDictionaryDao.findDataList1(query, pageInfo);
		return gridData;
	}

	@Override
	public void saveSysDictionary(SysUser sysUser,SysDictionary record) throws Exception {
		int oid=record.getOid();
		SysDictionary old=sysDictionaryDao.findOne(oid);
		record.updateOptInfo(old, sysUser.getUserName());
		if(old==null){
			sysDictionaryDao.save(record);
		}else{
		sysDictionaryDao.update(record);
		}
	}

	@Override
	public void changeDelFlg(SysUser sysUser,List<Integer> oidList,int delFlg) throws Exception {
		List<SysDictionary> recordList=sysDictionaryDao.findAll(oidList);
		for(SysDictionary record : recordList){
			record.setOptUserName(sysUser.getUserName());
			record.setOptDateTime(DateUtil.getDate());
			record.setDelFlg(delFlg);
			sysDictionaryDao.update(record);
		}
	}

}