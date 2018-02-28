package com.qm.nms.domainEnum;

public enum RequiredEnum
{
  REQUIRED("必修"),  ELECTIVE("选修"),  ELECTIVE_REQUIRED("选修必上");
  
  private String desc;
  
  private RequiredEnum(String desc)
  {
    this.desc = desc;
  }
  
  public String getDesc()
  {
    return this.desc;
  }
}

