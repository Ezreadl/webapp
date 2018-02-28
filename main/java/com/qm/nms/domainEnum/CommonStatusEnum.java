package com.qm.nms.domainEnum;

public enum CommonStatusEnum
{
  DELETED("已删除"),  AVAILABLE("可用");
  
  private String desc;
  
  private CommonStatusEnum(String desc)
  {
    this.desc = desc;
  }
  
  public String getDesc()
  {
    return this.desc;
  }
}