package com.qm.protocols.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;

import com.qm.protocols.FtpConsole;
import com.qm.protocols.FtpFile;
import com.qm.sys.domain.Equipment;

public class FTPSConsoleImpl extends FtpConsole{
	
	protected FTPSClient ftpClient=null;
	
	public FTPSConsoleImpl(Equipment equipment){
		super(equipment);
	}
	
	public FTPSConsoleImpl(Equipment equipment,int loginRetryCount,int loginRetryDelay){
		super(equipment, loginRetryCount, loginRetryDelay);
	}
	@Override
	protected boolean ftpLogin() throws Exception{
		boolean implicit="implicit".equalsIgnoreCase(equipment.getFtpImplicit())?true:false;
		ftpClient = new FTPSClient(implicit);
		ftpClient.setSoTimeout(soTimeOut);
//		ftpClient.setControlKeepAliveTimeout(dataTimeOut);
//		ftpClient.setControlKeepAliveReplyTimeout(soTimeOut);
		ftpClient.connect(equipment.getIpAddress(), equipment.getFtpPort());
		int reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)){
             return false;
        }
		boolean login=ftpClient.login(equipment.getFtpUserName(), equipment.getFtpPassword());
		if(!login){
			return false;
		}
		//主动或被动模式
		if("port".equalsIgnoreCase(equipment.getFtpMode())){
			ftpClient.enterLocalActiveMode();
		}else{
			ftpClient.enterLocalPassiveMode();
		}
		ftpClient.setDataTimeout(dataTimeOut);
		//使用2进制传输
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE); 
		ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE); 
		return true;
	}
	
	@Override
	public void downLoadFile(String downPath, String downFileName,File saveToFile) throws Exception {
		if(downPath!=null && !"".equals(downPath)){
			ftpClient.changeWorkingDirectory(downPath);
		}
		if(!saveToFile.getParentFile().exists()){
			saveToFile.getParentFile().mkdirs();
		}
		FileOutputStream fout=new FileOutputStream(saveToFile);
		ftpClient.retrieveFile(downFileName,fout);
		fout.close();
	}
	
	@Override
	public void downLoadFile(String downFileName, File saveToFile) throws Exception{
		downLoadFile(null,downFileName,saveToFile);
	}
	
	@Override
	public void upLoadFile(String upLoadToPath, String upLoadToFileName,File upLoadFile) throws Exception {
		if(upLoadToPath!=null && !"".equals(upLoadToPath)){
			ftpClient.changeWorkingDirectory(upLoadToPath);
		}
		FileInputStream finput=new FileInputStream(upLoadFile);
		ftpClient.storeFile(upLoadToFileName,finput);
		finput.close();
	}
	
	@Override
	public void upLoadFile(String upLoadToFileName, File upLoadFile) throws Exception {
		upLoadFile(null,upLoadToFileName,upLoadFile);
	}
	
	@Override
	public FtpFile[] listFile() throws Exception {
		FTPFile[] ftpArr=ftpClient.listFiles();
		FtpFile[] ftpFileArr=new FtpFile[ftpArr.length];
		for (int i=0;i<ftpArr.length;i++) {
           ftpFileArr[i]=new FtpFile();
           ftpFileArr[i].setName(ftpArr[i].getName());
           ftpFileArr[i].setSize(ftpArr[i].getSize());
           ftpFileArr[i].setmTime(ftpArr[i].getTimestamp());
           ftpFileArr[i].setFile(ftpArr[i].isFile());
        }
		return ftpFileArr;
	}

	@Override
	public void changeDir(String pathName) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void logout() {
		try {
			if(ftpClient!=null){
				ftpClient.logout();
				ftpClient.disconnect();
			}
		} catch (Exception e) {
			//logger.error(equipment.getIpAddress()+"FTP退出",e);
		}
	}

	
}
