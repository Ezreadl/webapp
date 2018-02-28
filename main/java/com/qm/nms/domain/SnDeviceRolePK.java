package com.qm.nms.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;

public class SnDeviceRolePK
  implements Serializable
{
  private String roleId;
  private String nodeId;
  
  @Column(name="role_id")
  @Id
  public String getRoleId()
  {
    return this.roleId;
  }
  
  public void setRoleId(String roleId)
  {
    this.roleId = roleId;
  }
  
  @Column(name="nodeId")
  @Id
  public String getNodeId()
  {
    return this.nodeId;
  }
  
  public void setNodeId(String nodeId)
  {
    this.nodeId = nodeId;
  }
  
  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if ((o == null) || (getClass() != o.getClass())) {
      return false;
    }
    SnDeviceRolePK that = (SnDeviceRolePK)o;
    if (this.nodeId != null ? !this.nodeId.equals(that.nodeId) : that.nodeId != null) {
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
    return result;
  }
}
