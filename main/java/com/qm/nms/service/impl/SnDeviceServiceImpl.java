package com.qm.nms.service.impl;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qm.core.base.PageInfo;
import com.qm.core.util.CommonUtils;
import com.qm.nms.dao.ISnDeviceDao;
import com.qm.nms.dao.ISnDeviceRoleDao;
import com.qm.nms.dao.ISnPositionDao;
import com.qm.nms.dao.ISnRoleUserDao;
import com.qm.nms.dao.ISnVidelDvrDao;
import com.qm.nms.domain.SnDevice;
import com.qm.nms.domain.SnDeviceRole;
import com.qm.nms.domain.SnPerson;
import com.qm.nms.domain.SnPosition;
import com.qm.nms.domain.SnRoleUser;
import com.qm.nms.domain.SnVideoDvr;
import com.qm.nms.service.ISnDeviceService;

import exception.BizException;

@Service
public class SnDeviceServiceImpl implements ISnDeviceService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SnDeviceServiceImpl.class);
	@Autowired
	ISnDeviceDao snDeviceDao;
	@Autowired
	ISnRoleUserDao snRoleUserDao;
	@Autowired
	ISnDeviceRoleDao snDeviceRoleDao;
	@Autowired
	ISnPositionDao snPositionDao;
	@Autowired	
	ISnVidelDvrDao snVideoDvrDao;
	public List getSnChannelBySnPerson(SnPerson snPerson,PageInfo pageInfo) {
		//用户的角色
		List<SnRoleUser> snRoleUsers = snRoleUserDao.findByPersID(snPerson.getPersId());
		List<String> roleIds = new ArrayList<String>();
		for (SnRoleUser snRoleUser : snRoleUsers) {
			roleIds.add(snRoleUser.getRoleId());
		}
		//角色的设备
		List<SnDeviceRole> snDeviceRoles = snDeviceRoleDao.findByIds(roleIds,"SnPosition",1);
		List<String> posiIds = new ArrayList<String>();
		for (SnDeviceRole snDeviceRole : snDeviceRoles) {
			posiIds.add(snDeviceRole.getNodeId());
		}
		List<SnPosition> snPositions = new ArrayList<SnPosition>();
		if (posiIds.size() > 0) {
			snPositions = snPositionDao.findByIds();
		}
		posiIds.clear();
		List<String> resPosiIds = new ArrayList<String>();
		for (SnPosition snPosition : snPositions) {
			if (!snPosition.getParentPosiId().equals("0")) {
				posiIds.add(snPosition.getPosiId());
			} else {
				resPosiIds.add(snPosition.getPosiId());
			}
		}
		SnPosition snPosition;
		getChildPosi(posiIds, resPosiIds);
		snDeviceRoles = snDeviceRoleDao.findByIds(roleIds,"SnDevice",1);
		List<String> devIds = new ArrayList<String>();
		for (SnDeviceRole snDeviceRole : snDeviceRoles) {
			devIds.add(snDeviceRole.getNodeId());
		}
		devIds.add("1");
		List<SnDevice> snDevices = new ArrayList<SnDevice>();
		if (posiIds.size() == 0) {
			snDevices = snDeviceDao.findByDev(devIds, "5");
		}else
			snDevices = snDeviceDao.findByPos(roleIds, devIds, "5");
		LOGGER.debug("导入点位:当前用户所有点位列表{}", snDevices.toString());
		return snDevices;
	}

	public SnDevice getParentSnDevice(SnDevice snDevice) {
		return snDeviceDao.findByID(snDevice.getParentDevId());
	}

	public SnDevice getSnDeviceById(String devId) {
		return snDeviceDao.findByID(devId);
	}
	public SnVideoDvr getSnVideoDvrByDevId(SnDevice snDevice) {
		return snVideoDvrDao.findByType(snDevice.getDevId());
	}

	public Map<String, Object> getFlashPlayerPlayInfo(String devId) throws BizException {
		SnDevice snDevice = getSnDeviceById(devId);
		if (CommonUtils.isEmpty(snDevice)) {
			throw new BizException("无法从安防平台获取设备");
		}
		SnDevice parent = getParentSnDevice(snDevice);
		if (CommonUtils.isEmpty(parent)) {
			throw new BizException("安防平台设置有问题");
		}
		Map<String, Object> ans = new HashMap();
		ans.put("IP", parent.getDevIp());
		ans.put("port", parent.getDevPort());
		ans.put("username", parent.getDevUsername());
		ans.put("password", parent.getDevPassword());
		ans.put("proxyclass", parent.getDevFirm());
		ans.put("devclass", "");
		ans.put("channel", snDevice.getDevNo());
		ans.put("source", "0");
		ans.put("flag", "0");
		return ans;
	}

	public Map<String, Object> getFlashPlayerBackInfo(String devId, Long start, Long end) throws BizException {
		SnDevice snDevice = getSnDeviceById(devId);
		if (CommonUtils.isEmpty(snDevice)) {
			throw new BizException("无法从安防平台获取设备");
		}
		SnDevice parent = getParentSnDevice(snDevice);
		if (CommonUtils.isEmpty(parent)) {
			throw new BizException("安防平台设置有问题");
		}
		Timestamp startTime = new Timestamp(start.longValue());
		String startString = startTime.toString().replace("-", "").replace(" ", "").replace(":", "").replace(".", "");
		startString = startString.substring(0, startString.length() - 3);
		Timestamp endTime = new Timestamp(end.longValue());
		String endString = endTime.toString().replace("-", "").replace(" ", "").replace(":", "").replace(".", "");
		endString = endString.substring(0, endString.length() - 3);
		Map<String, Object> ans = new HashMap();
		ans.put("IP", parent.getDevIp());
		ans.put("port", parent.getDevPort());
		ans.put("username", parent.getDevUsername());
		ans.put("password", parent.getDevPassword());
		ans.put("proxyclass", parent.getDevFirm());
		ans.put("devclass", "");
		ans.put("channel", snDevice.getDevNo());
		ans.put("source", "1");
		ans.put("flag", "1");
		ans.put("timeRange", startString + "_" + endString);
		return ans;
	}

	public void getChildPosi(List<String> posiIds, List<String> resPosiIds) {
		resPosiIds.addAll(posiIds);
		if (posiIds.size() == 0) {
			return;
		}
		List<SnPosition> snPositions = snPositionDao.findByIds(resPosiIds);
		if (CommonUtils.isEmpty(snPositions)) {
			return;
		}
		posiIds.clear();
		for (SnPosition snPosition : snPositions) {
			posiIds.add(snPosition.getPosiId());
		}
		getChildPosi(posiIds, resPosiIds);
	}

	@Override
	public SnPerson snLogin(String paramString1, String paramString2) throws BizException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SnPerson getSnPersonByUserName(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}
}
