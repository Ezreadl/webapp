package com.qm.nms.domain;

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
import javax.persistence.Table;

import com.qm.nms.domainEnum.CommonStatusEnum;
import com.qm.nms.domainEnum.TeachVersionEnum;

@Entity
public class LabLessonPlan
{
  private long id;
  private String name;
  private LabType schoolType;
  private CommonStatusEnum status;
  private TeachVersionEnum version;
  
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
  
  @Basic
  @Column(name="name")
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  @ManyToOne(targetEntity=LabType.class, fetch=FetchType.LAZY)
  @JoinColumn(name="school_type")
  public LabType getSchoolType()
  {
    return this.schoolType;
  }
  
  public void setSchoolType(LabType schoolType)
  {
    this.schoolType = schoolType;
  }
  
  @Basic
  @Enumerated(EnumType.STRING)
  @Column(name="status")
  public CommonStatusEnum getStatus()
  {
    return this.status;
  }
  
  public void setStatus(CommonStatusEnum status)
  {
    this.status = status;
  }
  
  @Basic
  @Enumerated(EnumType.STRING)
  @Column(name="version")
  public TeachVersionEnum getVersion()
  {
    return this.version;
  }
  
  public void setVersion(TeachVersionEnum version)
  {
    this.version = version;
  }
}
