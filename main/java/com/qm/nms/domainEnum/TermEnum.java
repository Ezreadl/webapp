package com.qm.nms.domainEnum;

public enum TermEnum
{
  LAST_TERM("上学期"),  NEXT_TERM("下学期"),  ALL_TERM("全学年");
  
  private String desc;
  
  private TermEnum(String desc)
  {
    this.desc = desc;
  }
  
  public String getDesc()
  {
    return this.desc;
  }
}