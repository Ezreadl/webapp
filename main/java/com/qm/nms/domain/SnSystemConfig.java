package com.qm.nms.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sn_system_config", schema="", catalog="snms")
public class SnSystemConfig
{
  private String paramName;
  private String paramValue;
  private String paramDesc;
  
  @Id
  @Column(name="param_name")
  public String getParamName()
  {
    return this.paramName;
  }
  
  public void setParamName(String paramName)
  {
    this.paramName = paramName;
  }
  
  @Basic
  @Column(name="param_value")
  public String getParamValue()
  {
    return this.paramValue;
  }
  
  public void setParamValue(String paramValue)
  {
    this.paramValue = paramValue;
  }
  
  @Basic
  @Column(name="param_desc")
  public String getParamDesc()
  {
    return this.paramDesc;
  }
  
  public void setParamDesc(String paramDesc)
  {
    this.paramDesc = paramDesc;
  }
  
  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if ((o == null) || (getClass() != o.getClass())) {
      return false;
    }
    SnSystemConfig that = (SnSystemConfig)o;
    if (this.paramDesc != null ? !this.paramDesc.equals(that.paramDesc) : that.paramDesc != null) {
      return false;
    }
    if (this.paramName != null ? !this.paramName.equals(that.paramName) : that.paramName != null) {
      return false;
    }
    if (this.paramValue != null ? !this.paramValue.equals(that.paramValue) : that.paramValue != null) {
      return false;
    }
    return true;
  }
  
  public int hashCode()
  {
    int result = this.paramName != null ? this.paramName.hashCode() : 0;
    result = 31 * result + (this.paramValue != null ? this.paramValue.hashCode() : 0);
    result = 31 * result + (this.paramDesc != null ? this.paramDesc.hashCode() : 0);
    return result;
  }
}
