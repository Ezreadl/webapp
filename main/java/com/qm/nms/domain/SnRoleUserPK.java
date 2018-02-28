package com.qm.nms.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class SnRoleUserPK
  implements Serializable
{
  private String roleId;
  private String persId;
  
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
  
  @Column(name="pers_id")
  @Id
  public String getPersId()
  {
    return this.persId;
  }
  
  public void setPersId(String persId)
  {
    this.persId = persId;
  }
  
  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if ((o == null) || (getClass() != o.getClass())) {
      return false;
    }
    SnRoleUserPK that = (SnRoleUserPK)o;
    if (this.persId != null ? !this.persId.equals(that.persId) : that.persId != null) {
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
    result = 31 * result + (this.persId != null ? this.persId.hashCode() : 0);
    return result;
  }
}
