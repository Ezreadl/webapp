package com.qm.nms.service;

import java.util.Map;

import com.qm.nms.domain.SnServer;

import exception.BizException;

public interface ISnServerService
{
	  public SnServer getSnServerByServertypeId(String paramString);
	  
	  public Map<String, Object> getIAS()
	    throws BizException;
	  
	  public Map<String, Object> getDMS()
	    throws BizException;
	}
