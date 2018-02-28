package com.qm.nms.service;

import com.qm.nms.domain.SnPerson;

import exception.BizException;

public interface ISnPersonService {
	public SnPerson snLogin(String paramString1, String paramString2) throws BizException;

	public SnPerson getSnPersonByUserName(String paramString);
}
