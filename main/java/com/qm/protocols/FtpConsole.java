package com.qm.protocols;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qm.sys.domain.Equipment;

public abstract class FtpConsole {
	protected Logger logger=LoggerFactory.getLogger(FtpConsole.class);
	//要ftp的设备
	protected Equipment equipment=null;
	//登录失败，重试次数
	protected int loginRetryCount=2;
	//登录失败，重试延迟时间(秒)
	protected int loginRetryDelay=30;
	//设置socket超时时间（单位秒）默认30分钟
	protected int soTimeOut=30*60;
	//数据传输超时时间
	protected int dataTimeOut=50000;
	
	public FtpConsole(Equipment equipment){
		this.equipment=equipment;
	}
	
	public FtpConsole(Equipment equipment,int loginRetryCount,int loginRetryDelay){
		this.equipment=equipment;
		this.loginRetryCount=loginRetryCount;
		this.loginRetryDelay=loginRetryDelay;
	}
	
	public boolean login() {
		try {
			for(int i=0;i<loginRetryCount;i++){
				try {
					boolean loginSuccess=ftpLogin();
					if(loginSuccess){
						return true;
					}else{
						logout();
						Thread.sleep(loginRetryDelay*1000);
					}
				} catch (Exception e) {
					if(i==loginRetryCount-1){
						throw e;
					}
					Thread.sleep(loginRetryDelay*1000);
				}
			}
			logger.info("登陆设备ID"+equipment.getOid()+equipment.getChineseName()+"("+equipment.getIpAddress()+")FTP成功");
			return true;
		} catch (Exception e) {
			logger.error("登陆设备ID"+equipment.getOid()+equipment.getChineseName()+"("+equipment.getIpAddress()+")FTP错误",e);
		}
		return false;
	}
	//登陆设备
	protected abstract boolean ftpLogin() throws Exception;
	//下载文件
	public abstract void downLoadFile(String downPath,String downFileName,File saveToFile) throws Exception;
	//下载文件
	public abstract void downLoadFile(String downFileName,File saveToFile) throws Exception;
	//上传文件
	public abstract void upLoadFile(String upLoadToPath,String upLoadToFileName,File upLoadFile) throws Exception;
	//上传文件
	public abstract void upLoadFile(String upLoadToFileName,File upLoadFile) throws Exception;
	//目录列表
	public abstract FtpFile[] listFile() throws Exception;
	//改变当前目录
	public abstract void changeDir(String pathName) throws Exception; 
	//退出设备
	public abstract void logout();

	public int getLoginRetryCount() {
		return loginRetryCount;
	}

	public void setLoginRetryCount(int loginRetryCount) {
		this.loginRetryCount = loginRetryCount;
	}

	public int getLoginRetryDelay() {
		return loginRetryDelay;
	}

	public void setLoginRetryDelay(int loginRetryDelay) {
		this.loginRetryDelay = loginRetryDelay;
	}

	public int getSoTimeOut() {
		return soTimeOut;
	}

	public void setSoTimeOut(int soTimeOut) {
		this.soTimeOut = soTimeOut;
	}

	public int getDataTimeOut() {
		return dataTimeOut;
	}

	public void setDataTimeOut(int dataTimeOut) {
		this.dataTimeOut = dataTimeOut;
	}
	
}
