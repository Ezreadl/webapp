package com.qm.nms.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.qm.nms.domainEnum.LessonPlanEnum;
import com.qm.nms.domainEnum.RequiredEnum;
import com.qm.nms.domainEnum.TermEnum;

@Entity
public class LabSchoolLessonPlanInfo
{
  private long id;
  private LabSchoolLessonPlanInfo parent;
  private String title;
  private Integer serialNumber;
  private LessonPlanEnum demonstration;
  private LessonPlanEnum scene;
  private LessonPlanEnum remark;
  private LabSchoolLesson labSchoolLesson;
  private LessonPlanEnum level;
  private LabType subject;
  private TermEnum gradeYear;
  private LabType grade;
  private RequiredEnum required;
  private String nodePath;
  private Set<LabSchoolLessonPlanInfo> labSchoolLessonPlanInfos = new HashSet();
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="id")
  public long getId()
  {
    return this.id;
  }
  
  public void setId(long id)
  {
    this.id = id;
  }
  
  @ManyToOne(targetEntity=LabSchoolLessonPlanInfo.class, fetch=FetchType.LAZY)
  @JoinColumn(name="parent_id")
  public LabSchoolLessonPlanInfo getParent()
  {
    return this.parent;
  }
  
  public void setParent(LabSchoolLessonPlanInfo parent)
  {
    this.parent = parent;
  }
  
  @Basic
  @Column(name="title")
  public String getTitle()
  {
    return this.title;
  }
  
  public void setTitle(String title)
  {
    this.title = title;
  }
  
  @Basic
  @Column(name="serial_number")
  public Integer getSerialNumber()
  {
    return this.serialNumber;
  }
  
  public void setSerialNumber(Integer serialNumber)
  {
    this.serialNumber = serialNumber;
  }
  
  @Basic
  @Enumerated(EnumType.STRING)
  @Column(name="demonstration")
  public LessonPlanEnum getDemonstration()
  {
    return this.demonstration;
  }
  
  public void setDemonstration(LessonPlanEnum demonstration)
  {
    this.demonstration = demonstration;
  }
  
  @Basic
  @Enumerated(EnumType.STRING)
  @Column(name="scene")
  public LessonPlanEnum getScene()
  {
    return this.scene;
  }
  
  public void setScene(LessonPlanEnum scene)
  {
    this.scene = scene;
  }
  
  @Basic
  @Enumerated(EnumType.STRING)
  @Column(name="remark")
  public LessonPlanEnum getRemark()
  {
    return this.remark;
  }
  
  public void setRemark(LessonPlanEnum remark)
  {
    this.remark = remark;
  }
  
  @ManyToOne(targetEntity=LabSchoolLesson.class, fetch=FetchType.LAZY)
  @JoinColumn(name="school_lesson_id")
  public LabSchoolLesson getLabSchoolLesson()
  {
    return this.labSchoolLesson;
  }
  
  public void setLabSchoolLesson(LabSchoolLesson labSchoolLesson)
  {
    this.labSchoolLesson = labSchoolLesson;
  }
  
  @Basic
  @Enumerated(EnumType.STRING)
  @Column(name="level")
  public LessonPlanEnum getLevel()
  {
    return this.level;
  }
  
  public void setLevel(LessonPlanEnum level)
  {
    this.level = level;
  }
  
  @ManyToOne(targetEntity=LabType.class, fetch=FetchType.LAZY)
  @JoinColumn(name="subject")
  public LabType getSubject()
  {
    return this.subject;
  }
  
  public void setSubject(LabType subject)
  {
    this.subject = subject;
  }
  
  @Basic
  @Enumerated(EnumType.STRING)
  @Column(name="grade_year")
  public TermEnum getGradeYear()
  {
    return this.gradeYear;
  }
  
  public void setGradeYear(TermEnum gradeYear)
  {
    this.gradeYear = gradeYear;
  }
  
  @ManyToOne(targetEntity=LabType.class, fetch=FetchType.LAZY)
  @JoinColumn(name="grade")
  public LabType getGrade()
  {
    return this.grade;
  }
  
  public void setGrade(LabType grade)
  {
    this.grade = grade;
  }
  
  @Basic
  @Enumerated(EnumType.STRING)
  @Column(name="required")
  public RequiredEnum getRequired()
  {
    return this.required;
  }
  
  public void setRequired(RequiredEnum required)
  {
    this.required = required;
  }
  
  @Basic
  @Column(name="node_path")
  public String getNodePath()
  {
    return this.nodePath;
  }
  
  public void setNodePath(String nodePath)
  {
    this.nodePath = nodePath;
  }
  
  @OneToMany(mappedBy="parent", cascade={javax.persistence.CascadeType.REMOVE}, fetch=FetchType.LAZY)
  public Set<LabSchoolLessonPlanInfo> getLabSchoolLessonPlanInfos()
  {
    return this.labSchoolLessonPlanInfos;
  }
  
  public void setLabSchoolLessonPlanInfos(Set<LabSchoolLessonPlanInfo> labSchoolLessonPlanInfos)
  {
    this.labSchoolLessonPlanInfos = labSchoolLessonPlanInfos;
  }
}
