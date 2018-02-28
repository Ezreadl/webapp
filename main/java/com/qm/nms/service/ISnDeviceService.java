package com.qm.nms.service;

import com.qm.nms.domain.SnDevice;
import com.qm.nms.domain.SnPerson;
import com.qm.nms.domain.SnVideoDvr;

import exception.BizException;

public interface ISnDeviceService {

	public SnPerson snLogin(String paramString1, String paramString2) throws BizException;

	public SnPerson getSnPersonByUserName(String paramString);

	public SnDevice getParentSnDevice(SnDevice snDevice);

	public SnVideoDvr getSnVideoDvrByDevId(SnDevice snDevice);

}
