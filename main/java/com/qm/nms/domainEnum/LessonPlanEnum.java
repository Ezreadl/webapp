package com.qm.nms.domainEnum;

public enum LessonPlanEnum
{
  NEED("演示"),  UNWANTED("不演示"),  IN_CLASS("课内"),  OUT_CLASS("课外"),  NULL_CLASS("无"),  STAR_COURSE("必做"),  ORDINARY_CLASS("普通课程"),  FIRST_LEVEL("1"),  SECOND_LEVEL("2"),  THIRD_LEVEL("3"),  FOURTH_LEVEL("4"),  FIFTH_LEVEL("5"),  SIXTH_LEVEL("6"),  SEVENTH_LEVEL("7"),  LESSON_LEVEL("8"),  COURSE_LEVEL("9"),  GRADE_LEVEL("10");
  
  private String desc;
  
  private LessonPlanEnum(String desc)
  {
    this.desc = desc;
  }
  
  public String getDesc()
  {
    return this.desc;
  }
}
