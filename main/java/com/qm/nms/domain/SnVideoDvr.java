package com.qm.nms.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sn_video_dvr", schema="", catalog="snms")
public class SnVideoDvr
{
  private String id;
  private String dvrId;
  private Integer mode;
  private String mac;
  private String netMask;
  private String netGateway;
  private Integer if3G;
  private Integer webPort;
  private Integer videoPort;
  private Integer mobilePort;
  private Integer nvrConnType;
  
  @Id
  @Column(name="id")
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  @Basic
  @Column(name="dvr_id")
  public String getDvrId()
  {
    return this.dvrId;
  }
  
  public void setDvrId(String dvrId)
  {
    this.dvrId = dvrId;
  }
  
  @Basic
  @Column(name="mode")
  public Integer getMode()
  {
    return this.mode;
  }
  
  public void setMode(Integer mode)
  {
    this.mode = mode;
  }
  
  @Basic
  @Column(name="mac")
  public String getMac()
  {
    return this.mac;
  }
  
  public void setMac(String mac)
  {
    this.mac = mac;
  }
  
  @Basic
  @Column(name="net_mask")
  public String getNetMask()
  {
    return this.netMask;
  }
  
  public void setNetMask(String netMask)
  {
    this.netMask = netMask;
  }
  
  @Basic
  @Column(name="net_gateway")
  public String getNetGateway()
  {
    return this.netGateway;
  }
  
  public void setNetGateway(String netGateway)
  {
    this.netGateway = netGateway;
  }
  
  @Basic
  @Column(name="if_3g")
  public Integer getIf3G()
  {
    return this.if3G;
  }
  
  public void setIf3G(Integer if3G)
  {
    this.if3G = if3G;
  }
  
  @Basic
  @Column(name="web_port")
  public Integer getWebPort()
  {
    return this.webPort;
  }
  
  public void setWebPort(Integer webPort)
  {
    this.webPort = webPort;
  }
  
  @Basic
  @Column(name="video_port")
  public Integer getVideoPort()
  {
    return this.videoPort;
  }
  
  public void setVideoPort(Integer videoPort)
  {
    this.videoPort = videoPort;
  }
  
  @Basic
  @Column(name="mobile_port")
  public Integer getMobilePort()
  {
    return this.mobilePort;
  }
  
  public void setMobilePort(Integer mobilePort)
  {
    this.mobilePort = mobilePort;
  }
  
  @Basic
  @Column(name="nvr_conn_type")
  public Integer getNvrConnType()
  {
    return this.nvrConnType;
  }
  
  public void setNvrConnType(Integer nvrConnType)
  {
    this.nvrConnType = nvrConnType;
  }
  
  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if ((o == null) || (getClass() != o.getClass())) {
      return false;
    }
    SnVideoDvr that = (SnVideoDvr)o;
    if (this.dvrId != null ? !this.dvrId.equals(that.dvrId) : that.dvrId != null) {
      return false;
    }
    if (this.id != null ? !this.id.equals(that.id) : that.id != null) {
      return false;
    }
    if (this.if3G != null ? !this.if3G.equals(that.if3G) : that.if3G != null) {
      return false;
    }
    if (this.mac != null ? !this.mac.equals(that.mac) : that.mac != null) {
      return false;
    }
    if (this.mobilePort != null ? !this.mobilePort.equals(that.mobilePort) : that.mobilePort != null) {
      return false;
    }
    if (this.mode != null ? !this.mode.equals(that.mode) : that.mode != null) {
      return false;
    }
    if (this.netGateway != null ? !this.netGateway.equals(that.netGateway) : that.netGateway != null) {
      return false;
    }
    if (this.netMask != null ? !this.netMask.equals(that.netMask) : that.netMask != null) {
      return false;
    }
    if (this.nvrConnType != null ? !this.nvrConnType.equals(that.nvrConnType) : that.nvrConnType != null) {
      return false;
    }
    if (this.videoPort != null ? !this.videoPort.equals(that.videoPort) : that.videoPort != null) {
      return false;
    }
    if (this.webPort != null ? !this.webPort.equals(that.webPort) : that.webPort != null) {
      return false;
    }
    return true;
  }
  
  public int hashCode()
  {
    int result = this.id != null ? this.id.hashCode() : 0;
    result = 31 * result + (this.dvrId != null ? this.dvrId.hashCode() : 0);
    result = 31 * result + (this.mode != null ? this.mode.hashCode() : 0);
    result = 31 * result + (this.mac != null ? this.mac.hashCode() : 0);
    result = 31 * result + (this.netMask != null ? this.netMask.hashCode() : 0);
    result = 31 * result + (this.netGateway != null ? this.netGateway.hashCode() : 0);
    result = 31 * result + (this.if3G != null ? this.if3G.hashCode() : 0);
    result = 31 * result + (this.webPort != null ? this.webPort.hashCode() : 0);
    result = 31 * result + (this.videoPort != null ? this.videoPort.hashCode() : 0);
    result = 31 * result + (this.mobilePort != null ? this.mobilePort.hashCode() : 0);
    result = 31 * result + (this.nvrConnType != null ? this.nvrConnType.hashCode() : 0);
    return result;
  }
}
