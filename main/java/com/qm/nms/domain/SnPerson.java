package com.qm.nms.domain;

import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sn_person", schema="", catalog="snms")
public class SnPerson
{
  private String persId;
  private String persUsername;
  private String persPwd;
  private String persName;
  private String persSex;
  private String persDuty;
  private String persPosition;
  private String persDepartment;
  private String persTele;
  private String persMobile;
  private String persMemo;
  private String persCard;
  private String persEmail;
  private Integer persEnable;
  private Integer persStatus;
  private Timestamp persRegisterTime;
  private Timestamp persLoginTime;
  private Integer persLevel;
  private String verifyIp;
  private Timestamp verifyTime;
  private Timestamp updateTime;
  private String gbId;
  private Integer viewVideoTime;
  private Integer ifpush;
  
  @Id
  @Column(name="pers_id")
  public String getPersId()
  {
    return this.persId;
  }
  
  public void setPersId(String persId)
  {
    this.persId = persId;
  }
  
  @Basic
  @Column(name="pers_username")
  public String getPersUsername()
  {
    return this.persUsername;
  }
  
  public void setPersUsername(String persUsername)
  {
    this.persUsername = persUsername;
  }
  
  @Basic
  @Column(name="pers_pwd")
  public String getPersPwd()
  {
    return this.persPwd;
  }
  
  public void setPersPwd(String persPwd)
  {
    this.persPwd = persPwd;
  }
  
  @Basic
  @Column(name="pers_name")
  public String getPersName()
  {
    return this.persName;
  }
  
  public void setPersName(String persName)
  {
    this.persName = persName;
  }
  
  @Basic
  @Column(name="pers_sex")
  public String getPersSex()
  {
    return this.persSex;
  }
  
  public void setPersSex(String persSex)
  {
    this.persSex = persSex;
  }
  
  @Basic
  @Column(name="pers_duty")
  public String getPersDuty()
  {
    return this.persDuty;
  }
  
  public void setPersDuty(String persDuty)
  {
    this.persDuty = persDuty;
  }
  
  @Basic
  @Column(name="pers_position")
  public String getPersPosition()
  {
    return this.persPosition;
  }
  
  public void setPersPosition(String persPosition)
  {
    this.persPosition = persPosition;
  }
  
  @Basic
  @Column(name="pers_department")
  public String getPersDepartment()
  {
    return this.persDepartment;
  }
  
  public void setPersDepartment(String persDepartment)
  {
    this.persDepartment = persDepartment;
  }
  
  @Basic
  @Column(name="pers_tele")
  public String getPersTele()
  {
    return this.persTele;
  }
  
  public void setPersTele(String persTele)
  {
    this.persTele = persTele;
  }
  
  @Basic
  @Column(name="pers_mobile")
  public String getPersMobile()
  {
    return this.persMobile;
  }
  
  public void setPersMobile(String persMobile)
  {
    this.persMobile = persMobile;
  }
  
  @Basic
  @Column(name="pers_memo")
  public String getPersMemo()
  {
    return this.persMemo;
  }
  
  public void setPersMemo(String persMemo)
  {
    this.persMemo = persMemo;
  }
  
  @Basic
  @Column(name="pers_card")
  public String getPersCard()
  {
    return this.persCard;
  }
  
  public void setPersCard(String persCard)
  {
    this.persCard = persCard;
  }
  
  @Basic
  @Column(name="pers_email")
  public String getPersEmail()
  {
    return this.persEmail;
  }
  
  public void setPersEmail(String persEmail)
  {
    this.persEmail = persEmail;
  }
  
  @Basic
  @Column(name="pers_enable")
  public Integer getPersEnable()
  {
    return this.persEnable;
  }
  
  public void setPersEnable(Integer persEnable)
  {
    this.persEnable = persEnable;
  }
  
  @Basic
  @Column(name="pers_status")
  public Integer getPersStatus()
  {
    return this.persStatus;
  }
  
  public void setPersStatus(Integer persStatus)
  {
    this.persStatus = persStatus;
  }
  
  @Basic
  @Column(name="pers_register_time")
  public Timestamp getPersRegisterTime()
  {
    return this.persRegisterTime;
  }
  
  public void setPersRegisterTime(Timestamp persRegisterTime)
  {
    this.persRegisterTime = persRegisterTime;
  }
  
  @Basic
  @Column(name="pers_login_time")
  public Timestamp getPersLoginTime()
  {
    return this.persLoginTime;
  }
  
  public void setPersLoginTime(Timestamp persLoginTime)
  {
    this.persLoginTime = persLoginTime;
  }
  
  @Basic
  @Column(name="pers_level")
  public Integer getPersLevel()
  {
    return this.persLevel;
  }
  
  public void setPersLevel(Integer persLevel)
  {
    this.persLevel = persLevel;
  }
  
  @Basic
  @Column(name="verifyIp")
  public String getVerifyIp()
  {
    return this.verifyIp;
  }
  
  public void setVerifyIp(String verifyIp)
  {
    this.verifyIp = verifyIp;
  }
  
  @Basic
  @Column(name="verifyTime")
  public Timestamp getVerifyTime()
  {
    return this.verifyTime;
  }
  
  public void setVerifyTime(Timestamp verifyTime)
  {
    this.verifyTime = verifyTime;
  }
  
  @Basic
  @Column(name="updateTime")
  public Timestamp getUpdateTime()
  {
    return this.updateTime;
  }
  
  public void setUpdateTime(Timestamp updateTime)
  {
    this.updateTime = updateTime;
  }
  
  @Basic
  @Column(name="gb_id")
  public String getGbId()
  {
    return this.gbId;
  }
  
  public void setGbId(String gbId)
  {
    this.gbId = gbId;
  }
  
  @Basic
  @Column(name="view_video_time")
  public Integer getViewVideoTime()
  {
    return this.viewVideoTime;
  }
  
  public void setViewVideoTime(Integer viewVideoTime)
  {
    this.viewVideoTime = viewVideoTime;
  }
  
  @Basic
  @Column(name="ifpush")
  public Integer getIfpush()
  {
    return this.ifpush;
  }
  
  public void setIfpush(Integer ifpush)
  {
    this.ifpush = ifpush;
  }
  
  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if ((o == null) || (getClass() != o.getClass())) {
      return false;
    }
    SnPerson snPerson = (SnPerson)o;
    if (this.gbId != null ? !this.gbId.equals(snPerson.gbId) : snPerson.gbId != null) {
      return false;
    }
    if (this.ifpush != null ? !this.ifpush.equals(snPerson.ifpush) : snPerson.ifpush != null) {
      return false;
    }
    if (this.persCard != null ? !this.persCard.equals(snPerson.persCard) : snPerson.persCard != null) {
      return false;
    }
    if (this.persDepartment != null ? !this.persDepartment.equals(snPerson.persDepartment) : snPerson.persDepartment != null) {
      return false;
    }
    if (this.persDuty != null ? !this.persDuty.equals(snPerson.persDuty) : snPerson.persDuty != null) {
      return false;
    }
    if (this.persEmail != null ? !this.persEmail.equals(snPerson.persEmail) : snPerson.persEmail != null) {
      return false;
    }
    if (this.persEnable != null ? !this.persEnable.equals(snPerson.persEnable) : snPerson.persEnable != null) {
      return false;
    }
    if (this.persId != null ? !this.persId.equals(snPerson.persId) : snPerson.persId != null) {
      return false;
    }
    if (this.persLevel != null ? !this.persLevel.equals(snPerson.persLevel) : snPerson.persLevel != null) {
      return false;
    }
    if (this.persLoginTime != null ? !this.persLoginTime.equals(snPerson.persLoginTime) : snPerson.persLoginTime != null) {
      return false;
    }
    if (this.persMemo != null ? !this.persMemo.equals(snPerson.persMemo) : snPerson.persMemo != null) {
      return false;
    }
    if (this.persMobile != null ? !this.persMobile.equals(snPerson.persMobile) : snPerson.persMobile != null) {
      return false;
    }
    if (this.persName != null ? !this.persName.equals(snPerson.persName) : snPerson.persName != null) {
      return false;
    }
    if (this.persPosition != null ? !this.persPosition.equals(snPerson.persPosition) : snPerson.persPosition != null) {
      return false;
    }
    if (this.persPwd != null ? !this.persPwd.equals(snPerson.persPwd) : snPerson.persPwd != null) {
      return false;
    }
    if (this.persRegisterTime != null ? !this.persRegisterTime.equals(snPerson.persRegisterTime) : snPerson.persRegisterTime != null) {
      return false;
    }
    if (this.persSex != null ? !this.persSex.equals(snPerson.persSex) : snPerson.persSex != null) {
      return false;
    }
    if (this.persStatus != null ? !this.persStatus.equals(snPerson.persStatus) : snPerson.persStatus != null) {
      return false;
    }
    if (this.persTele != null ? !this.persTele.equals(snPerson.persTele) : snPerson.persTele != null) {
      return false;
    }
    if (this.persUsername != null ? !this.persUsername.equals(snPerson.persUsername) : snPerson.persUsername != null) {
      return false;
    }
    if (this.updateTime != null ? !this.updateTime.equals(snPerson.updateTime) : snPerson.updateTime != null) {
      return false;
    }
    if (this.verifyIp != null ? !this.verifyIp.equals(snPerson.verifyIp) : snPerson.verifyIp != null) {
      return false;
    }
    if (this.verifyTime != null ? !this.verifyTime.equals(snPerson.verifyTime) : snPerson.verifyTime != null) {
      return false;
    }
    if (this.viewVideoTime != null ? !this.viewVideoTime.equals(snPerson.viewVideoTime) : snPerson.viewVideoTime != null) {
      return false;
    }
    return true;
  }
  
  public int hashCode()
  {
    int result = this.persId != null ? this.persId.hashCode() : 0;
    result = 31 * result + (this.persUsername != null ? this.persUsername.hashCode() : 0);
    result = 31 * result + (this.persPwd != null ? this.persPwd.hashCode() : 0);
    result = 31 * result + (this.persName != null ? this.persName.hashCode() : 0);
    result = 31 * result + (this.persSex != null ? this.persSex.hashCode() : 0);
    result = 31 * result + (this.persDuty != null ? this.persDuty.hashCode() : 0);
    result = 31 * result + (this.persPosition != null ? this.persPosition.hashCode() : 0);
    result = 31 * result + (this.persDepartment != null ? this.persDepartment.hashCode() : 0);
    result = 31 * result + (this.persTele != null ? this.persTele.hashCode() : 0);
    result = 31 * result + (this.persMobile != null ? this.persMobile.hashCode() : 0);
    result = 31 * result + (this.persMemo != null ? this.persMemo.hashCode() : 0);
    result = 31 * result + (this.persCard != null ? this.persCard.hashCode() : 0);
    result = 31 * result + (this.persEmail != null ? this.persEmail.hashCode() : 0);
    result = 31 * result + (this.persEnable != null ? this.persEnable.hashCode() : 0);
    result = 31 * result + (this.persStatus != null ? this.persStatus.hashCode() : 0);
    result = 31 * result + (this.persRegisterTime != null ? this.persRegisterTime.hashCode() : 0);
    result = 31 * result + (this.persLoginTime != null ? this.persLoginTime.hashCode() : 0);
    result = 31 * result + (this.persLevel != null ? this.persLevel.hashCode() : 0);
    result = 31 * result + (this.verifyIp != null ? this.verifyIp.hashCode() : 0);
    result = 31 * result + (this.verifyTime != null ? this.verifyTime.hashCode() : 0);
    result = 31 * result + (this.updateTime != null ? this.updateTime.hashCode() : 0);
    result = 31 * result + (this.gbId != null ? this.gbId.hashCode() : 0);
    result = 31 * result + (this.viewVideoTime != null ? this.viewVideoTime.hashCode() : 0);
    result = 31 * result + (this.ifpush != null ? this.ifpush.hashCode() : 0);
    return result;
  }
}
