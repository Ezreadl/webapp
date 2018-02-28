package com.qm.nms.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qm.core.util.CommonUtils;
import com.qm.core.util.Constant;
import com.qm.nms.dao.ISnServerDao;
import com.qm.nms.domain.SnServer;
import com.qm.nms.service.ISnServerService;

import exception.BizException;

@Service
public class SnServerServiceImpl implements ISnServerService {
	@Autowired
	ISnServerDao snServerDao;

	public SnServer getSnServerByServertypeId(String serverTypeId) {
		return (SnServer) snServerDao.findByType(serverTypeId);
	}

	public Map<String, Object> getIAS() throws BizException {
		Map<String, Object> ans = new HashMap();
		SnServer IAS = getSnServerByServertypeId("21");
		if (CommonUtils.isEmpty(IAS)) {
			throw new BizException("无智能分析接入服务器");
		}
		String IP = null;
		for (String ip : IAS.getServerIp().split(",")) {
			if (CommonUtils.ping(ip, 2, 2000)) {
				IP = ip;
				break;
			}
		}
		if (CommonUtils.isEmpty(IP)) {
			throw new BizException("无可用的智能分析接入服务器");
		}
		ans.put("IP", IP);
		ans.put("port", Constant.getPropertie("IASPORT"));
		return ans;
	}

	public Map<String, Object> getDMS() throws BizException {
		Map<String, Object> ans = new HashMap();
		SnServer DMS = getSnServerByServertypeId("3");
		if (CommonUtils.isEmpty(DMS)) {
			throw new BizException("无设备管理服务器");
		}
		String IP = null;
		for (String ip : DMS.getServerIp().split(",")) {
			if (CommonUtils.ping(ip, 2, 2000)) {
				IP = ip;
				break;
			}
		}
		if (CommonUtils.isEmpty(IP)) {
			throw new BizException("无可用的设备管理服务器");
		}
		ans.put("IP", IP);
		ans.put("port", DMS.getServerPort());
		return ans;
	}
}
