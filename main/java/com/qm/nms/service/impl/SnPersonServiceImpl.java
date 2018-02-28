package com.qm.nms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qm.core.util.CipherUtil;
import com.qm.core.util.CommonUtils;
import com.qm.nms.dao.ISnPersonDao;
import com.qm.nms.domain.SnPerson;
import com.qm.nms.service.ISnPersonService;

import exception.BizException;

@Service
public class SnPersonServiceImpl
  implements ISnPersonService
{
  @Autowired
  ISnPersonDao snPersonDao;
  
  public SnPerson snLogin(String persUsername, String persPwd)
    throws BizException
  {
    String hql = "from SnPerson where persUsername = ?";
    SnPerson snPerson = snPersonDao.findByName(persUsername);
    if (CommonUtils.isEmpty(snPerson)) {
      throw new BizException("综合平台用户不存在");
    }
    
    if (!snPerson.getPersPwd().equals(CipherUtil.md5(persPwd))) {
      throw new BizException("密码错误");
    }
    return snPerson;
  }
  
  public SnPerson getSnPersonByUserName(String userName)
  {
    String hql = "from SnPerson where persUsername = ?";
    SnPerson snPerson = snPersonDao.findByName(userName);
    return snPerson;
  }
}