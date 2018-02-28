package com.qm.sys.domain;

import javax.persistence.Entity;

import com.qm.core.base.BaseDomPure;

/**
 * 设备登录方案明细
 * @author (wangchong)
 */

@Entity
@SuppressWarnings("serial")
public class LoginSolutionDetail extends BaseDomPure{
	
	/**
	 * 方案id
	 */
	private int solutionId;
	/**
	 * 输入提示符号
	 */
	private String promt="";
	/**
	 * 输入参数
	 */
	private String inputparam="";
	/**
	 * 用户类别0维护，1管理员
	 */
	private int userType=0;
	/**
	 * 输入步骤
	 */
	private int stepnum=1;
	
	public int getSolutionId() {
		return solutionId;
	}
	public void setSolutionId(int solutionId) {
		this.solutionId = solutionId;
	}
	public String getPromt() {
		return promt;
	}
	public void setPromt(String promt) {
		this.promt = promt;
	}
	public String getInputparam() {
		return inputparam;
	}
	public void setInputparam(String inputparam) {
		this.inputparam = inputparam;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public int getStepnum() {
		return stepnum;
	}
	public void setStepnum(int stepnum) {
		this.stepnum = stepnum;
	}
	@Override
	public String toString() {
		return "LoginSolutionDetail [solutionId=" + solutionId + ", promt=" + promt + ", inputparam=" + inputparam
				+ ", userType=" + userType + ", stepnum=" + stepnum + ", oid=" + oid + "]";
	}
	
}
