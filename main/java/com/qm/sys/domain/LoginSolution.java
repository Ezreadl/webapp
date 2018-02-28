package com.qm.sys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.qm.core.base.BaseDomCfg;

/**
 * 设备登录方案
 * @author (wangchong)
 */

@Entity
@SuppressWarnings("serial")
public class LoginSolution extends BaseDomCfg{
	/**
	 * 方案名称
	 */
	@Column
	private String solutionName;
	/**
	 * 预处理命令，多条命令直接使用\;分隔命令
	 */
	@Column
	private String preMaintainExcuteCmd;
	/**
	 * 维护账号后置处理命令，多条命令直接使用\;分隔命令
	 */
	@Column
	private String endMaintainExcuteCmd;
	/**
	 * 预处理命令，多条命令直接使用\;分隔命令
	 */
	@Column
	private String preManagerExcuteCmd;
	/**
	 * 管理账号后置处理命令，多条命令直接使用\;分隔命令
	 */
	@Column
	private String endManagerExcuteCmd;
	/**
	 * 连接错误提示多个用,号分隔
	 */
	@Column
	private String linkError;
	/**
	 * 登录错误提示(最后一行)
	 */
	@Column
    private String loginEndError;
    /**
     * 登录错误(包含错误),多个用,号分隔
     */
	@Column
    private String loginContainError;
    /**
	 * 登录成功提示(最后一行)
	 */
	@Column
    private String loginEndSuccess;
    /**
     * 登录成功(包含成功),多个用,号分隔
     */
	@Column
    private String loginContainSuccess;
    /**
     * 如果未捕获到结果0返回错误，1返回正常
     */
	@Column
 	private int noCatchRet=1;
    /**
	 * 超时等待
	 */
	@Column
	private int waittime;
	/**
	 * 允许加入库特征0不允许，1允许
	 */
	@Column
	private int allowAddStore=0;
	
	public String getSolutionName() {
		return solutionName;
	}

	public void setSolutionName(String solutionName) {
		this.solutionName = solutionName;
	}

	public String getPreMaintainExcuteCmd() {
		return preMaintainExcuteCmd;
	}

	public void setPreMaintainExcuteCmd(String preMaintainExcuteCmd) {
		this.preMaintainExcuteCmd = preMaintainExcuteCmd;
	}

	public String getEndMaintainExcuteCmd() {
		return endMaintainExcuteCmd;
	}

	public void setEndMaintainExcuteCmd(String endMaintainExcuteCmd) {
		this.endMaintainExcuteCmd = endMaintainExcuteCmd;
	}

	public String getPreManagerExcuteCmd() {
		return preManagerExcuteCmd;
	}

	public void setPreManagerExcuteCmd(String preManagerExcuteCmd) {
		this.preManagerExcuteCmd = preManagerExcuteCmd;
	}

	public String getEndManagerExcuteCmd() {
		return endManagerExcuteCmd;
	}

	public void setEndManagerExcuteCmd(String endManagerExcuteCmd) {
		this.endManagerExcuteCmd = endManagerExcuteCmd;
	}

	public String getLinkError() {
		return linkError;
	}

	public void setLinkError(String linkError) {
		this.linkError = linkError;
	}

	public String getLoginEndError() {
		return loginEndError;
	}

	public void setLoginEndError(String loginEndError) {
		this.loginEndError = loginEndError;
	}

	public String getLoginContainError() {
		return loginContainError;
	}

	public void setLoginContainError(String loginContainError) {
		this.loginContainError = loginContainError;
	}

	public String getLoginEndSuccess() {
		return loginEndSuccess;
	}

	public void setLoginEndSuccess(String loginEndSuccess) {
		this.loginEndSuccess = loginEndSuccess;
	}

	public String getLoginContainSuccess() {
		return loginContainSuccess;
	}

	public void setLoginContainSuccess(String loginContainSuccess) {
		this.loginContainSuccess = loginContainSuccess;
	}

	public int getNoCatchRet() {
		return noCatchRet;
	}

	public void setNoCatchRet(int noCatchRet) {
		this.noCatchRet = noCatchRet;
	}

	public int getWaittime() {
		return waittime;
	}

	public void setWaittime(int waittime) {
		this.waittime = waittime;
	}

	public int getAllowAddStore() {
		return allowAddStore;
	}

	public void setAllowAddStore(int allowAddStore) {
		this.allowAddStore = allowAddStore;
	}

	public String[] getLinkErrorArr(){
		if(this.linkError==null || "".equals(this.linkError)){
			return new String[0];
		}
		return this.linkError.split("[，,]+");
	}
	
	public String[] getLoginEndErrorArr(){
		if(this.loginEndError==null || "".equals(this.loginEndError)){
			return new String[0];
		}
		return this.loginEndError.split("[，,]+");
	}
	
	public String[] getLoginContainErrorArr(){
		if(this.loginContainError==null || "".equals(this.loginContainError)){
			return new String[0];
		}
		return this.loginContainError.split("[，,]+");
	}
	
	public String[] getLoginEndSuccessArr(){
		if(this.loginEndSuccess==null || "".equals(this.loginEndSuccess)){
			return new String[0];
		}
		return this.loginEndSuccess.split("[，,]+");
	}
	
	public String[] getLoginContainSuccessArr(){
		if(this.loginContainSuccess==null || "".equals(this.loginContainSuccess)){
			return new String[0];
		}
		return this.loginContainSuccess.split("[，,]+");
	}

	@Override
	public String toString() {
		return "LoginSolution [solutionName=" + solutionName + ", preMaintainExcuteCmd=" + preMaintainExcuteCmd
				+ ", endMaintainExcuteCmd=" + endMaintainExcuteCmd + ", preManagerExcuteCmd=" + preManagerExcuteCmd
				+ ", endManagerExcuteCmd=" + endManagerExcuteCmd + ", linkError=" + linkError + ", loginEndError="
				+ loginEndError + ", loginContainError=" + loginContainError + ", loginEndSuccess=" + loginEndSuccess
				+ ", loginContainSuccess=" + loginContainSuccess + ", noCatchRet=" + noCatchRet + ", waittime="
				+ waittime + ", allowAddStore=" + allowAddStore + ", oid=" + oid + ", optUserName=" + optUserName
				+ ", addUserName=" + addUserName + ", optDateTime=" + optDateTime + ", addDateTime=" + addDateTime
				+ ", delFlg=" + delFlg + "]";
	}

}
