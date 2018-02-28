package com.qm.nms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qm.core.util.CommonUtils;
import com.qm.nms.dao.ISnSysCfgDao;
import com.qm.nms.domain.SnSystemConfig;
import com.qm.nms.service.ISnSystemConfigService;

@Service
public class SnSystemConfigServiceImpl
  implements ISnSystemConfigService
{
  @Autowired
  ISnSysCfgDao snSysCfgDao;
  
  public boolean checkSystemConfig()
  {
    SnSystemConfig snSystemConfig = snSysCfgDao.findByName("appLabFlag");
    if (CommonUtils.isEmpty(snSystemConfig)) {
      return false;
    }
    if (snSystemConfig.getParamValue().equals("1")) {
      return true;
    }
    return false;
  }
}