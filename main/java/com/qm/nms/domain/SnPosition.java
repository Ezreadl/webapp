package com.qm.nms.domain;

import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sn_position", schema="", catalog="snms")
public class SnPosition
{
  private String posiId;
  private String parentPosiId;
  private String posiName;
  private String posiAddr;
  private Integer posiLevel;
  private int posiPriority;
  private Timestamp mapTime;
  private Integer mapVer;
  private String serverId;
  private String manager;
  private String managerPhone;
  private String duty;
  private String dutyPhone;
  private Integer isDel;
  private String domain;
  private Timestamp addTime;
  private Timestamp updateTime;
  private String gbId;
  private int isOutland;
  private String domainId;
  private String mcpsId;
  private String mapIconType;
  private String mapLng;
  private String mapLat;
  private String mapCustomName;
  private String mapInfo;
  private String mapId;
  private Integer posiType;
  
  @Id
  @Column(name="posi_id")
  public String getPosiId()
  {
    return this.posiId;
  }
  
  public void setPosiId(String posiId)
  {
    this.posiId = posiId;
  }
  
  @Basic
  @Column(name="parent_posi_id")
  public String getParentPosiId()
  {
    return this.parentPosiId;
  }
  
  public void setParentPosiId(String parentPosiId)
  {
    this.parentPosiId = parentPosiId;
  }
  
  @Basic
  @Column(name="posi_name")
  public String getPosiName()
  {
    return this.posiName;
  }
  
  public void setPosiName(String posiName)
  {
    this.posiName = posiName;
  }
  
  @Basic
  @Column(name="posi_addr")
  public String getPosiAddr()
  {
    return this.posiAddr;
  }
  
  public void setPosiAddr(String posiAddr)
  {
    this.posiAddr = posiAddr;
  }
  
  @Basic
  @Column(name="posi_level")
  public Integer getPosiLevel()
  {
    return this.posiLevel;
  }
  
  public void setPosiLevel(Integer posiLevel)
  {
    this.posiLevel = posiLevel;
  }
  
  @Basic
  @Column(name="posi_priority")
  public int getPosiPriority()
  {
    return this.posiPriority;
  }
  
  public void setPosiPriority(int posiPriority)
  {
    this.posiPriority = posiPriority;
  }
  
  @Basic
  @Column(name="map_time")
  public Timestamp getMapTime()
  {
    return this.mapTime;
  }
  
  public void setMapTime(Timestamp mapTime)
  {
    this.mapTime = mapTime;
  }
  
  @Basic
  @Column(name="map_ver")
  public Integer getMapVer()
  {
    return this.mapVer;
  }
  
  public void setMapVer(Integer mapVer)
  {
    this.mapVer = mapVer;
  }
  
  @Basic
  @Column(name="server_id")
  public String getServerId()
  {
    return this.serverId;
  }
  
  public void setServerId(String serverId)
  {
    this.serverId = serverId;
  }
  
  @Basic
  @Column(name="manager")
  public String getManager()
  {
    return this.manager;
  }
  
  public void setManager(String manager)
  {
    this.manager = manager;
  }
  
  @Basic
  @Column(name="managerPhone")
  public String getManagerPhone()
  {
    return this.managerPhone;
  }
  
  public void setManagerPhone(String managerPhone)
  {
    this.managerPhone = managerPhone;
  }
  
  @Basic
  @Column(name="duty")
  public String getDuty()
  {
    return this.duty;
  }
  
  public void setDuty(String duty)
  {
    this.duty = duty;
  }
  
  @Basic
  @Column(name="dutyPhone")
  public String getDutyPhone()
  {
    return this.dutyPhone;
  }
  
  public void setDutyPhone(String dutyPhone)
  {
    this.dutyPhone = dutyPhone;
  }
  
  @Basic
  @Column(name="isDel")
  public Integer getIsDel()
  {
    return this.isDel;
  }
  
  public void setIsDel(Integer isDel)
  {
    this.isDel = isDel;
  }
  
  @Basic
  @Column(name="domain")
  public String getDomain()
  {
    return this.domain;
  }
  
  public void setDomain(String domain)
  {
    this.domain = domain;
  }
  
  @Basic
  @Column(name="addTime")
  public Timestamp getAddTime()
  {
    return this.addTime;
  }
  
  public void setAddTime(Timestamp addTime)
  {
    this.addTime = addTime;
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
  @Column(name="is_outland")
  public int getIsOutland()
  {
    return this.isOutland;
  }
  
  public void setIsOutland(int isOutland)
  {
    this.isOutland = isOutland;
  }
  
  @Basic
  @Column(name="domain_id")
  public String getDomainId()
  {
    return this.domainId;
  }
  
  public void setDomainId(String domainId)
  {
    this.domainId = domainId;
  }
  
  @Basic
  @Column(name="mcps_id")
  public String getMcpsId()
  {
    return this.mcpsId;
  }
  
  public void setMcpsId(String mcpsId)
  {
    this.mcpsId = mcpsId;
  }
  
  @Basic
  @Column(name="map_icon_type")
  public String getMapIconType()
  {
    return this.mapIconType;
  }
  
  public void setMapIconType(String mapIconType)
  {
    this.mapIconType = mapIconType;
  }
  
  @Basic
  @Column(name="map_lng")
  public String getMapLng()
  {
    return this.mapLng;
  }
  
  public void setMapLng(String mapLng)
  {
    this.mapLng = mapLng;
  }
  
  @Basic
  @Column(name="map_lat")
  public String getMapLat()
  {
    return this.mapLat;
  }
  
  public void setMapLat(String mapLat)
  {
    this.mapLat = mapLat;
  }
  
  @Basic
  @Column(name="map_custom_name")
  public String getMapCustomName()
  {
    return this.mapCustomName;
  }
  
  public void setMapCustomName(String mapCustomName)
  {
    this.mapCustomName = mapCustomName;
  }
  
  @Basic
  @Column(name="map_info")
  public String getMapInfo()
  {
    return this.mapInfo;
  }
  
  public void setMapInfo(String mapInfo)
  {
    this.mapInfo = mapInfo;
  }
  
  @Basic
  @Column(name="map_id")
  public String getMapId()
  {
    return this.mapId;
  }
  
  public void setMapId(String mapId)
  {
    this.mapId = mapId;
  }
  
  @Basic
  @Column(name="posiType")
  public Integer getPosiType()
  {
    return this.posiType;
  }
  
  public void setPosiType(Integer posiType)
  {
    this.posiType = posiType;
  }
  
  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if ((o == null) || (getClass() != o.getClass())) {
      return false;
    }
    SnPosition that = (SnPosition)o;
    if (this.isOutland != that.isOutland) {
      return false;
    }
    if (this.posiPriority != that.posiPriority) {
      return false;
    }
    if (this.addTime != null ? !this.addTime.equals(that.addTime) : that.addTime != null) {
      return false;
    }
    if (this.domain != null ? !this.domain.equals(that.domain) : that.domain != null) {
      return false;
    }
    if (this.domainId != null ? !this.domainId.equals(that.domainId) : that.domainId != null) {
      return false;
    }
    if (this.duty != null ? !this.duty.equals(that.duty) : that.duty != null) {
      return false;
    }
    if (this.dutyPhone != null ? !this.dutyPhone.equals(that.dutyPhone) : that.dutyPhone != null) {
      return false;
    }
    if (this.gbId != null ? !this.gbId.equals(that.gbId) : that.gbId != null) {
      return false;
    }
    if (this.isDel != null ? !this.isDel.equals(that.isDel) : that.isDel != null) {
      return false;
    }
    if (this.manager != null ? !this.manager.equals(that.manager) : that.manager != null) {
      return false;
    }
    if (this.managerPhone != null ? !this.managerPhone.equals(that.managerPhone) : that.managerPhone != null) {
      return false;
    }
    if (this.mapCustomName != null ? !this.mapCustomName.equals(that.mapCustomName) : that.mapCustomName != null) {
      return false;
    }
    if (this.mapIconType != null ? !this.mapIconType.equals(that.mapIconType) : that.mapIconType != null) {
      return false;
    }
    if (this.mapId != null ? !this.mapId.equals(that.mapId) : that.mapId != null) {
      return false;
    }
    if (this.mapInfo != null ? !this.mapInfo.equals(that.mapInfo) : that.mapInfo != null) {
      return false;
    }
    if (this.mapLat != null ? !this.mapLat.equals(that.mapLat) : that.mapLat != null) {
      return false;
    }
    if (this.mapLng != null ? !this.mapLng.equals(that.mapLng) : that.mapLng != null) {
      return false;
    }
    if (this.mapTime != null ? !this.mapTime.equals(that.mapTime) : that.mapTime != null) {
      return false;
    }
    if (this.mapVer != null ? !this.mapVer.equals(that.mapVer) : that.mapVer != null) {
      return false;
    }
    if (this.mcpsId != null ? !this.mcpsId.equals(that.mcpsId) : that.mcpsId != null) {
      return false;
    }
    if (this.parentPosiId != null ? !this.parentPosiId.equals(that.parentPosiId) : that.parentPosiId != null) {
      return false;
    }
    if (this.posiAddr != null ? !this.posiAddr.equals(that.posiAddr) : that.posiAddr != null) {
      return false;
    }
    if (this.posiId != null ? !this.posiId.equals(that.posiId) : that.posiId != null) {
      return false;
    }
    if (this.posiLevel != null ? !this.posiLevel.equals(that.posiLevel) : that.posiLevel != null) {
      return false;
    }
    if (this.posiName != null ? !this.posiName.equals(that.posiName) : that.posiName != null) {
      return false;
    }
    if (this.posiType != null ? !this.posiType.equals(that.posiType) : that.posiType != null) {
      return false;
    }
    if (this.serverId != null ? !this.serverId.equals(that.serverId) : that.serverId != null) {
      return false;
    }
    if (this.updateTime != null ? !this.updateTime.equals(that.updateTime) : that.updateTime != null) {
      return false;
    }
    return true;
  }
  
  public int hashCode()
  {
    int result = this.posiId != null ? this.posiId.hashCode() : 0;
    result = 31 * result + (this.parentPosiId != null ? this.parentPosiId.hashCode() : 0);
    result = 31 * result + (this.posiName != null ? this.posiName.hashCode() : 0);
    result = 31 * result + (this.posiAddr != null ? this.posiAddr.hashCode() : 0);
    result = 31 * result + (this.posiLevel != null ? this.posiLevel.hashCode() : 0);
    result = 31 * result + this.posiPriority;
    result = 31 * result + (this.mapTime != null ? this.mapTime.hashCode() : 0);
    result = 31 * result + (this.mapVer != null ? this.mapVer.hashCode() : 0);
    result = 31 * result + (this.serverId != null ? this.serverId.hashCode() : 0);
    result = 31 * result + (this.manager != null ? this.manager.hashCode() : 0);
    result = 31 * result + (this.managerPhone != null ? this.managerPhone.hashCode() : 0);
    result = 31 * result + (this.duty != null ? this.duty.hashCode() : 0);
    result = 31 * result + (this.dutyPhone != null ? this.dutyPhone.hashCode() : 0);
    result = 31 * result + (this.isDel != null ? this.isDel.hashCode() : 0);
    result = 31 * result + (this.domain != null ? this.domain.hashCode() : 0);
    result = 31 * result + (this.addTime != null ? this.addTime.hashCode() : 0);
    result = 31 * result + (this.updateTime != null ? this.updateTime.hashCode() : 0);
    result = 31 * result + (this.gbId != null ? this.gbId.hashCode() : 0);
    result = 31 * result + this.isOutland;
    result = 31 * result + (this.domainId != null ? this.domainId.hashCode() : 0);
    result = 31 * result + (this.mcpsId != null ? this.mcpsId.hashCode() : 0);
    result = 31 * result + (this.mapIconType != null ? this.mapIconType.hashCode() : 0);
    result = 31 * result + (this.mapLng != null ? this.mapLng.hashCode() : 0);
    result = 31 * result + (this.mapLat != null ? this.mapLat.hashCode() : 0);
    result = 31 * result + (this.mapCustomName != null ? this.mapCustomName.hashCode() : 0);
    result = 31 * result + (this.mapInfo != null ? this.mapInfo.hashCode() : 0);
    result = 31 * result + (this.mapId != null ? this.mapId.hashCode() : 0);
    result = 31 * result + (this.posiType != null ? this.posiType.hashCode() : 0);
    return result;
  }
}
