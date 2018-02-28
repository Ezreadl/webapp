package com.qm.nms.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="sn_device_role", schema="", catalog="snms")
@IdClass(SnDeviceRolePK.class)
public class SnDeviceRole
{
  private String roleId;
  private String nodeId;
  private String permission;
  private String nodeType;
  private Integer checkState;
  
  @Id
  @Column(name="role_id")
  public String getRoleId()
  {
    return this.roleId;
  }
  
  public void setRoleId(String roleId)
  {
    this.roleId = roleId;
  }
  
  @Id
  @Column(name="nodeId")
  public String getNodeId()
  {
    return this.nodeId;
  }
  
  public void setNodeId(String nodeId)
  {
    this.nodeId = nodeId;
  }
  
  @Basic
  @Column(name="permission")
  public String getPermission()
  {
    return this.permission;
  }
  
  public void setPermission(String permission)
  {
    this.permission = permission;
  }
  
  @Basic
  @Column(name="nodeType")
  public String getNodeType()
  {
    return this.nodeType;
  }
  
  public void setNodeType(String nodeType)
  {
    this.nodeType = nodeType;
  }
  
  @Basic
  @Column(name="checkState")
  public Integer getCheckState()
  {
    return this.checkState;
  }
  
  public void setCheckState(Integer checkState)
  {
    this.checkState = checkState;
  }
  
  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if ((o == null) || (getClass() != o.getClass())) {
      return false;
    }
    SnDeviceRole that = (SnDeviceRole)o;
    if (this.checkState != null ? !this.checkState.equals(that.checkState) : that.checkState != null) {
      return false;
    }
    if (this.nodeId != null ? !this.nodeId.equals(that.nodeId) : that.nodeId != null) {
      return false;
    }
    if (this.nodeType != null ? !this.nodeType.equals(that.nodeType) : that.nodeType != null) {
      return false;
    }
    if (this.permission != null ? !this.permission.equals(that.permission) : that.permission != null) {
      return false;
    }
    if (this.roleId != null ? !this.roleId.equals(that.roleId) : that.roleId != null) {
      return false;
    }
    return true;
  }
  
  public int hashCode()
  {
    int result = this.roleId != null ? this.roleId.hashCode() : 0;
    result = 31 * result + (this.nodeId != null ? this.nodeId.hashCode() : 0);
    result = 31 * result + (this.permission != null ? this.permission.hashCode() : 0);
    result = 31 * result + (this.nodeType != null ? this.nodeType.hashCode() : 0);
    result = 31 * result + (this.checkState != null ? this.checkState.hashCode() : 0);
    return result;
  }
}
