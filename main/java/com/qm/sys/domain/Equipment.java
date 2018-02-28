package com.qm.sys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.qm.core.base.BaseDomCfg;
/**
 * 设备信息，包括基础信息，登录信息等。
 * @author (wangchong)
 */

@Entity
@SuppressWarnings("serial")
public class Equipment extends BaseDomCfg{
	
	/**
	 * 设备名称(通常为telnet登录后的提示字符串)
	 */
	@Column
	private String equipName;
	/**
	 * 设备中文名称说明
	 */
	@Column
	private String chineseName;
	/**
	 * 别名
	 */
	@Column
	private String otherName;
	/**
	 * 通过中间设备登录的接口ID
	 */
	@Column
	private String loginNeId;
	/**
	 * 设备登录的ip地址
	 */
	@Column
	private String ipAddress;
	/**
	 * 设备分级，在cmnet对设备分层级关系，（1级设备是省干 2级设备连接1级设备 2级设备一般指地市或RT 3级设备连接2级设备）
	 */
	@Column
	private int equipLevel=1;
	/**
	 * 设备品牌名称
	 */
	@Column
	private String brandName;
	/**
	 * 设备类型下的型号名称
	 */
	@Column
	private String typeName;
	/**
	 * 软件版本
	 */
	@Column 
	private String softVersion;
	/**
	 * 设备类型名称
	 */
	@Column
	private String styleName;
	/**
	 * 设备所属安全域名称
	 */
	@Column
	private String netAreaName;
	/**
	 * 所属地市
	 */
	@Column
	private String areaName;
	/**
	 * 所在机房
	 */
	@Column
	private String machineHome;
	/**
	 * 备注信息
	 */
	@Column
	private String commentInfo1;
	/**
	 * 备注信息
	 */
	@Column
	private String commentInfo2;
	/**
	 * 登录设备的连接类型，0直接登录，1通过中间设备登录，2不能登录的虚拟设备(仅用于界面展示时分组使用)
	 */
	@Column
	private int loginLinkType=0;
	//----------------------telnet登录信息开始--------------------------------
	/**
	 * 由维护账号切换到管理员账号的切换命令（如：en或su）
	 */
	@Column
	private String enableCmd;
	/**
	 * telnet或ssh端口号(telnet默认为23，ssh默认为22,该处用字符串表示，有的设备在跳转登录时不能指定端口，也就是该处允许端口为空)
	 */
	@Column
	private String telnetPort="23";
	/**
	 * 设备终端类型，一般情况下linux VT100,windows VT200或ASNI
	 * 大多数设备用的VT100类型
	 */
	@Column
	private String termType="VT100";
	/**
	 * telnet协议类型（ telnet, ssh1, ssh2）
	 */
	@Column
	private String telnetProtocol="telnet";
	/**
	 * 设备终端telnet指令返回的编码格式，默认为utf-8
	 */
	@Column
	private String telnetEncoding="utf8";
	/**
	 * 指令下发特征指示(0为\n 1为\r\n)，一般情况VT100为\n,VT200和ASNI为\r\n
	 */
	@Column
	private int telnetWriteFea=1;
	/**
	 * 在该设备终端telnet跳转到其他设备上shell指令格式，
	 * Telnet方式默认为telnet (ip) (port)
	 * SSH方式默认为ssh -l (username) (ip) -p (port)
	 */
	@Column
	private String telnetJumpCmd="telnet (ip) (port)";
	/**
	 * 设备telnet维护账号
	 */
	@Column
	private String maintainUser="";
	/**
	 * 设备telnet维护密码
	 */
	@Column
	private String maintainPwd="";
	/**
	 * 设备telnet管理员账号
	 */
	@Column
	private String managerUser="";
	/**
	 * 设备telnet管理员密码
	 */
	@Column
	private String managerPwd="";
	/**
	 * 退出命令多条指令用\;分隔
	 */
	@Column
	private String logoutCmd="quit";
	/**
	 * 退出more结尾指令
	 */
	@Column
	private String exitMoreCmd="c";
	/**
	 * telnet最大连接数限制，默认为2
	 */
	@Column
	private int maxLink=2;
	/**
	 * telnet跳转设备ID号(通过某台设备跳转登录，可以多级跳转)
	 */
	@Column
	private int jumpEquipId;
	/**
	 * telnet跳转设备别名
	 */
	@Column
	private String jumpEquipName;
	/**
	 * 指示该设备跳转时需要使用的账号类型，0维护账号，1管理账号,默认为1
	 */
	@Column
	private int jumpAccount=1;
	/**
	 * 设备telnet登录方案ID
	 */
	@Column
	private int loginSolutionId;
	/**
	 * 设备telnet登录方案Name
	 */
	@Column
	private String loginSolutionName;
	/**
	 * 指令返回报文特征库 ID
	 */
	@Column
	private int cmdFeaStoreId;
	/**
	 * 指令返回报文特征库 名称
	 */
	@Column
	private String cmdFeaStoreName;
	
	//----------------telnet登录信息结束，ftp登录信息开始----------------------------
	/**
	 * ftp协议类型， ftp, ftps, sftp
	 */
	@Column
	private String ftpProtocol="ftp";
	/**
	 * ftp协议端口号，默认为21
	 */
	@Column
	private int ftpPort=21;
	/**
	 * 指示ftp数据传输模式，"passive"被动模式，"port"主动模式，默认为"passive"
	 */
	@Column
	private String ftpMode="passive";
	/**
	 * 指示ftps使用隐式或显示连接，"implicit"隐式，"explicit"显示，默认为显示"explicit"
	 */
	@Column
	private String ftpImplicit="explicit";
	/**
	 * ftp账号
	 */
	@Column
	private String ftpUserName="";
	/**
	 * ftp密码
	 */
	@Column
	private String ftpPassword="";
	//-----------ftp登录信息结束，snmp信息开始-----------------------
	/**
	 * 该设备snmp协议使用的共同体名称，默认为public
	 */
	@Column
	private String community="public";
	/**
	 * 该设备snmp的版本号1版本1;2版本2c;3版本3，默认为2
	 */
	@Column
	private int snmpVersion=2;
	/**
	 * snmp的端口号
	 */
	@Column
	private int snmpPort=161;
	//--------------snmp信息结束----------------------------------
	/**
	 * 暂停标志0:正常，1：暂停
	 */
	@Column
	private int stateFlg=0;
	/**
	 * 当前telnet到设备的连接线程数(不保存)
	 */
	@Transient
	private int linkNumber=0;
	/**
	 * 指示是否解密(不保存)
	 */
	@Transient
	private boolean hasDeDecrypt=false;
	/**
	 * 连续超时次数(不保存)
	 */
	@Transient
	private long lastTimeOutTime=0;
	
	public String getEquipName() {
		if(equipName==null){
			return "";
		}
		return equipName;
	}
	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}
	public String getChineseName() {
		if(chineseName==null){
			return "";
		}
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	public String getIpAddress() {
		if(ipAddress==null){
			return "";
		}
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getEnableCmd() {
		if(enableCmd==null){
			return "";
		}
		return enableCmd;
	}
	public void setEnableCmd(String enableCmd) {
		this.enableCmd = enableCmd;
	}
	public String getMaintainUser() {
		if(maintainUser==null){
			return "";
		}
		return maintainUser;
	}
	public void setMaintainUser(String maintainUser) {
		this.maintainUser = maintainUser;
	}
	public String getMaintainPwd() {
		if(maintainPwd==null){
			return "";
		}
		return maintainPwd;
	}
	public void setMaintainPwd(String maintainPwd) {
		this.maintainPwd=maintainPwd;
	}
	public String getFtpUserName() {
		if(ftpUserName==null){
			return "";
		}
		return ftpUserName;
	}
	public void setFtpUserName(String ftpUserName) {
		this.ftpUserName = ftpUserName;
	}
	public String getFtpPassword() {
		if(ftpPassword==null){
			return "";
		}
		return ftpPassword;
	}
	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword=ftpPassword;
	}
	public String getManagerUser() {
		if(managerUser==null){
			return "";
		}
		return managerUser;
	}
	public void setManagerUser(String managerUser) {
		this.managerUser = managerUser;
	}
	public String getManagerPwd() {
		if(managerPwd==null){
			return "";
		}
		return managerPwd;
	}
	public void setManagerPwd(String managerPwd) {
		this.managerPwd=managerPwd;
	}
	public String getTelnetPort() {
		if(telnetPort==null){
			return "";
		}
		return telnetPort;
	}
	public void setTelnetPort(String telnetPort) {
		this.telnetPort = telnetPort;
	}
	public String getTelnetProtocol() {
		if(telnetProtocol==null){
			return "";
		}
		return telnetProtocol;
	}
	public void setTelnetProtocol(String telnetProtocol) {
		this.telnetProtocol = telnetProtocol;
	}
	public String getFtpProtocol() {
		if(ftpProtocol==null){
			return "";
		}
		return ftpProtocol;
	}
	public void setFtpProtocol(String ftpProtocol) {
		this.ftpProtocol = ftpProtocol;
	}
	public String getLoginNeId() {
		if(loginNeId==null){
			return "";
		}
		return loginNeId;
	}
	public void setLoginNeId(String loginNeId) {
		this.loginNeId = loginNeId;
	}
	public int getCmdFeaStoreId() {
		return cmdFeaStoreId;
	}
	public void setCmdFeaStoreName(String cmdFeaStoreName) {
		this.cmdFeaStoreName = cmdFeaStoreName;
	}
	public int getFtpPort() {
		return ftpPort;
	}
	public void setFtpPort(int ftpPort) {
		this.ftpPort = ftpPort;
	}
	public int getMaxLink() {
		return maxLink;
	}
	public void setMaxLink(int maxLink) {
		this.maxLink = maxLink;
	}
	public String getFtpMode() {
		if(ftpMode==null){
			return "";
		}
		return ftpMode;
	}
	public void setFtpMode(String ftpMode) {
		this.ftpMode = ftpMode;
	}
	public String getFtpImplicit() {
		if(ftpImplicit==null){
			return "";
		}
		return ftpImplicit;
	}
	public void setFtpImplicit(String ftpImplicit) {
		this.ftpImplicit = ftpImplicit;
	}
	public String getLogoutCmd() {
		if(logoutCmd==null){
			return "";
		}
		return logoutCmd;
	}
	public void setLogoutCmd(String logoutCmd) {
		this.logoutCmd = logoutCmd;
	}
	public String getExitMoreCmd() {
		if(exitMoreCmd==null){
			return "";
		}
		return exitMoreCmd;
	}
	public void setExitMoreCmd(String exitMoreCmd) {
		this.exitMoreCmd = exitMoreCmd;
	}
	public void setJumpAccount(int jumpAccount) {
		this.jumpAccount = jumpAccount;
	}
	public int getLoginLinkType() {
		return loginLinkType;
	}
	public void setLoginLinkType(int loginLinkType) {
		this.loginLinkType = loginLinkType;
	}
	public String getCommunity() {
		if(community==null){
			return "";
		}
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}
	public String getTermType() {
		if(termType==null){
			return "";
		}
		return termType;
	}
	public void setTermType(String termType) {
		this.termType = termType;
	}
	public String getTelnetEncoding() {
		if(telnetEncoding==null){
			return "";
		}
		return telnetEncoding;
	}
	public void setTelnetEncoding(String telnetEncoding) {
		this.telnetEncoding = telnetEncoding;
	}
	public int getSnmpVersion() {
		return snmpVersion;
	}
	public void setSnmpVersion(int snmpVersion) {
		this.snmpVersion = snmpVersion;
	}
	public int getSnmpPort() {
		return snmpPort;
	}
	public String getNetAreaName() {
		if(netAreaName==null){
			return "";
		}
		return netAreaName;
	}
	public void setNetAreaName(String netAreaName) {
		this.netAreaName = netAreaName;
	}
	public String getBrandName() {
		if(brandName==null){
			return "";
		}
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getStyleName() {
		if(styleName==null){
			return "";
		}
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	
	public String getTypeName() {
		if(typeName==null){
			return "";
		}
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public void setSnmpPort(int snmpPort) {
		this.snmpPort = snmpPort;
	}
	public String getJumpEquipName() {
		return jumpEquipName;
	}
	public void setJumpEquipName(String jumpEquipName) {
		this.jumpEquipName = jumpEquipName;
	}
	public String getLoginSolutionName() {
		return loginSolutionName;
	}
	public void setLoginSolutionName(String loginSolutionName) {
		this.loginSolutionName = loginSolutionName;
	}
	public String getTelnetJumpCmd() {
		if(telnetJumpCmd==null){
			return "";
		}
		return telnetJumpCmd;
	}
	public void setTelnetJumpCmd(String telnetJumpCmd) {
		this.telnetJumpCmd = telnetJumpCmd;
	}
	public int getTelnetWriteFea() {
		return telnetWriteFea;
	}
	public void setTelnetWriteFea(int telnetWriteFea) {
		this.telnetWriteFea = telnetWriteFea;
	}
	public int getStateFlg() {
		return stateFlg;
	}
	public void setStateFlg(int stateFlg) {
		this.stateFlg = stateFlg;
	}
	public synchronized void addLinkNumber(){
		linkNumber++;
	}
	public synchronized void minusLinkNumber(){
		if(linkNumber>0){
			linkNumber--;
		}else{
			linkNumber=0;
		}
	}
	public long getLastTimeOutTime() {
		return lastTimeOutTime;
	}
	public void setLastTimeOutTime(long lastTimeOutTime) {
		this.lastTimeOutTime = lastTimeOutTime;
	}
	public int getLinkNumber() {
		return linkNumber;
	}
	public void setLinkNumber(int linkNumber) {
		this.linkNumber = linkNumber;
	}
	public boolean isHasDeDecrypt() {
		return hasDeDecrypt;
	}
	public String getMachineHome() {
		return machineHome;
	}
	public void setMachineHome(String machineHome) {
		this.machineHome = machineHome;
	}
	public String getSoftVersion() {
		return softVersion;
	}
	public void setSoftVersion(String softVersion) {
		this.softVersion = softVersion;
	}
	public String getCommentInfo1() {
		return commentInfo1;
	}
	public void setCommentInfo1(String commentInfo1) {
		this.commentInfo1 = commentInfo1;
	}
	public String getCommentInfo2() {
		return commentInfo2;
	}
	public void setCommentInfo2(String commentInfo2) {
		this.commentInfo2 = commentInfo2;
	}
	public void setHasDeDecrypt(boolean hasDeDecrypt) {
		this.hasDeDecrypt = hasDeDecrypt;
	}
	public String getAreaName() {
		if(areaName==null){
			return "";
		}
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getOtherName() {
		return otherName;
	}
	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}
	public int getJumpEquipId() {
		return jumpEquipId;
	}
	public void setJumpEquipId(int jumpEquipId) {
		this.jumpEquipId = jumpEquipId;
	}
	public int getLoginSolutionId() {
		return loginSolutionId;
	}
	public int getEquipLevel() {
		return equipLevel;
	}
	public void setEquipLevel(int equipLevel) {
		this.equipLevel = equipLevel;
	}
	public void setLoginSolutionId(int loginSolutionId) {
		this.loginSolutionId = loginSolutionId;
	}
	public int getJumpAccount() {
		return jumpAccount;
	}
	public String getCmdFeaStoreName() {
		return cmdFeaStoreName;
	}
	public void setCmdFeaStoreId(int cmdFeaStoreId) {
		this.cmdFeaStoreId = cmdFeaStoreId;
	}
	public String getUniqueFlg(){
		if(this.loginLinkType==0){
			return this.ipAddress;
		}else if(this.loginLinkType==1){
			return this.loginNeId;
		}else{
			return "";
		}
	}
	@Override
	public String toString() {
		return "Equipment [equipName=" + equipName + ", chineseName=" + chineseName + ", otherName=" + otherName
				+ ", loginNeId=" + loginNeId + ", ipAddress=" + ipAddress + ", equipLevel=" + equipLevel
				+ ", netAreaName=" + netAreaName + ", brandName=" + brandName + ", styleName=" + styleName
				+ ", typeName=" + typeName + ", areaName=" + areaName + ", machineHome=" + machineHome
				+ ", commentInfo1=" + commentInfo1 + ", commentInfo2=" + commentInfo2 + ", loginLinkType="
				+ loginLinkType + ", enableCmd=" + enableCmd + ", telnetPort=" + telnetPort + ", termType=" + termType
				+ ", telnetProtocol=" + telnetProtocol + ", telnetEncoding=" + telnetEncoding + ", telnetWriteFea="
				+ telnetWriteFea + ", telnetJumpCmd=" + telnetJumpCmd + ", maintainUser=" + maintainUser
				+ ", maintainPwd=" + maintainPwd + ", managerUser=" + managerUser + ", managerPwd=" + managerPwd
				+ ", logoutCmd=" + logoutCmd + ", exitMoreCmd=" + exitMoreCmd + ", maxLink=" + maxLink
				+ ", jumpEquipId=" + jumpEquipId + ", jumpEquipName=" + jumpEquipName + ", jumpAccount=" + jumpAccount
				+ ", loginSolutionId=" + loginSolutionId + ", loginSolutionName=" + loginSolutionName
				+ ", cmdFeaStoreId=" + cmdFeaStoreId + ", cmdFeaStoreName=" + cmdFeaStoreName + ", ftpProtocol="
				+ ftpProtocol + ", ftpPort=" + ftpPort + ", ftpMode=" + ftpMode + ", ftpImplicit=" + ftpImplicit
				+ ", ftpUserName=" + ftpUserName + ", ftpPassword=" + ftpPassword + ", community=" + community
				+ ", snmpVersion=" + snmpVersion + ", snmpPort=" + snmpPort + ", stateFlg=" + stateFlg + ", linkNumber="
				+ linkNumber + ", hasDeDecrypt=" + hasDeDecrypt + ", lastTimeOutTime=" + lastTimeOutTime + ", oid="
				+ oid + ", optUserName=" + optUserName + ", addUserName=" + addUserName + ", optDateTime=" + optDateTime
				+ ", addDateTime=" + addDateTime + ", delFlg=" + delFlg + "]";
	}
	
}
