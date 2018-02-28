package com.qm.nms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="sn_role_user", schema="", catalog="snms")
@IdClass(SnRoleUserPK.class)
public class SnRoleUser
{
  private String roleId;
  private String persId;
  
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
  @Column(name="pers_id")
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
    SnRoleUser that = (SnRoleUser)o;
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
