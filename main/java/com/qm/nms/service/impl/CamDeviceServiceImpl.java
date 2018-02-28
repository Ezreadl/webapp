package com.qm.nms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qm.core.util.DateUtil;
import com.qm.nms.dao.ICamDeviceDao;
import com.qm.nms.domain.CamDevice;
import com.qm.nms.service.ICamDeviceService;
import com.qm.sys.domain.SysUser;

@Service
public class CamDeviceServiceImpl implements ICamDeviceService {

	@Autowired
	public ICamDeviceDao camDeviceDao;


	@Override
	public List<CamDevice> findAll() {
		// TODO Auto-generated method stub
		List<CamDevice> list = new ArrayList<CamDevice>();
		list = camDeviceDao.findAll();
		return list;
	}	
	
	@Override
	public List<CamDevice> findByOrganID(long organid) {
		// TODO Auto-generated method stub
		List<CamDevice> list = new ArrayList<CamDevice>();
		list = camDeviceDao.findByOrganID(organid);
		return list;
	}

	/**
	 * 添加课程
	 */
	public void saveCourse(SysUser user, CamDevice camDevice) throws Exception {
		int oid = camDevice.getOid();
		CamDevice old = camDeviceDao.findOne(oid);
		camDevice.updateOptInfo(old, user.getUserName());
		if (null == old)
			camDeviceDao.save(camDevice);
		else
			camDeviceDao.update(camDevice);
	}

	/**
	 * 删除课程
	 */
	public void changeDelFlg(SysUser user, List<Integer> oidList, int delFlg) throws Exception {
		List<CamDevice> lor = camDeviceDao.findAll(oidList);
		for (CamDevice old : lor) {
			old.setOptUserName(user.getUserName());
			old.setOptDateTime(DateUtil.getDate());
			old.setDelFlg(delFlg);
			camDeviceDao.delete(old);
		}
	}

}
