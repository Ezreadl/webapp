package com.qm.core.base;

public class RequestResult
{
  private Boolean mIsSuccess = Boolean.valueOf(false);
  private String mResult;
  
  public String getResult()
  {
    return this.mResult;
  }
  
  public Boolean isSuccess()
  {
    return this.mIsSuccess;
  }
  
  public void setResult(String paramString)
  {
    this.mResult = paramString;
  }
  
  protected void setSuccess(Boolean paramBoolean)
  {
    this.mIsSuccess = paramBoolean;
  }
}
