package com.qm.nms.domainEnum;


public enum TeachVersionEnum
{
  PEOPLE_EDITION("人教版"),  SU_EDITION("苏教版");
  
  private String desc;
  
  private TeachVersionEnum(String desc)
  {
    this.desc = desc;
  }
  
  public String getDesc()
  {
    return this.desc;
  }
}
