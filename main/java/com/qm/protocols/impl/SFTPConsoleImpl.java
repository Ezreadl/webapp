package com.qm.protocols.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;

import com.mindbright.nio.NetworkConnection;
import com.mindbright.ssh2.SSH2SFTP;
import com.mindbright.ssh2.SSH2SFTP.FileHandle;
import com.mindbright.ssh2.SSH2SFTPClient;
import com.mindbright.ssh2.SSH2SimpleClient;
import com.mindbright.ssh2.SSH2Transport;
import com.mindbright.util.RandomSeed;
import com.mindbright.util.SecureRandomAndPad;
import com.qm.protocols.FtpConsole;
import com.qm.protocols.FtpFile;
import com.qm.sys.domain.Equipment;

public class SFTPConsoleImpl extends FtpConsole{
	
	private NetworkConnection socket=null;
	private SSH2Transport transport =null;
	private SSH2SFTPClient sftpClient=null;
	
	private FileHandle fileHandle=null;
	
	public SFTPConsoleImpl(Equipment equipment){
		super(equipment);
	}
	
	public SFTPConsoleImpl(Equipment equipment,int loginRetryCount,int loginRetryDelay){
		super(equipment, loginRetryCount, loginRetryDelay);
	}
	
	@Override
	protected boolean ftpLogin() throws Exception{
		socket= NetworkConnection.open(equipment.getIpAddress(),equipment.getFtpPort());
		socket.setSoTimeout(soTimeOut);
		transport = new SSH2Transport(socket,createSecureRandom());
		SSH2SimpleClient client = new SSH2SimpleClient(transport,equipment.getFtpUserName(),equipment.getFtpPassword());
		sftpClient = new SSH2SFTPClient(client.getConnection(),false, 16 * 1024);
		fileHandle=sftpClient.opendir("./");
		return true;
	}
	
	@Override
	public void downLoadFile(String downPath, String downFileName,File saveToFile) throws Exception {
		String srcFile=downFileName;
		if(downPath!=null && !"".equals(downPath)){
			if(downPath.endsWith("/")||downPath.endsWith("\\")){
				srcFile=downPath+downFileName;
			}else{
				srcFile=downPath+"/"+downFileName;
			}
		}
        FileHandle handle = sftpClient.open(srcFile,
		                SSH2SFTP.SSH_FXF_READ,
		                new SSH2SFTP.FileAttributes());
		FileOutputStream fout = new FileOutputStream(saveToFile);
		sftpClient.readFully(handle, fout);
		fout.close();
	}

	@Override
	public void downLoadFile(String downFileName, File saveToFile) throws Exception {
		downLoadFile(null,downFileName,saveToFile);
	}

	@Override
	public void upLoadFile(String upLoadToPath, String upLoadToFileName,File upLoadFile) throws Exception {
		String dstFile=upLoadToFileName;
		if(upLoadToPath!=null && !"".equals(upLoadToPath)){
			if(upLoadToPath.endsWith("/")||upLoadToPath.endsWith("\\")){
				dstFile=upLoadToPath+upLoadToFileName;
			}else{
				dstFile=upLoadToPath+"/"+upLoadToFileName;
			}
		}
		FileHandle handle = sftpClient.open(dstFile, SSH2SFTP.SSH_FXF_WRITE| SSH2SFTP.SSH_FXF_CREAT | SSH2SFTP.SSH_FXF_TRUNC,new SSH2SFTP.FileAttributes());
		FileInputStream fin = new FileInputStream(upLoadFile);
		sftpClient.writeFully(handle, fin);
		fin.close();
	}

	@Override
	public void upLoadFile(String upLoadToFileName, File upLoadFile) throws Exception {
		upLoadFile(null,upLoadToFileName,upLoadFile);
	}

	@Override
	public void logout() {
		try {
			transport.normalDisconnect("User disconnects");
			socket.close();
		} catch (Exception e) {
			//logger.error(equipment.getIpAddress()+"SFTP退出",e);
		}
	}
	
	private static SecureRandomAndPad createSecureRandom() {
        byte[] seed;
        File devRandom = new File("/dev/urandom");
        if (devRandom.exists()) {
            RandomSeed rs = new RandomSeed("/dev/urandom", "/dev/urandom");
            seed = rs.getBytesBlocking(20);
        } else {
            seed = RandomSeed.getSystemStateHash();
        }
        return new SecureRandomAndPad(new SecureRandom(seed));
    }

	@Override
	public FtpFile[] listFile() throws Exception {
		SSH2SFTP.FileAttributes[] sftpArr=sftpClient.readdir(fileHandle);
		FtpFile[] ftpFileArr=new FtpFile[sftpArr.length];
		for(int i=0;i<sftpArr.length;i++){
			ftpFileArr[i]=new FtpFile();
           ftpFileArr[i].setName(sftpArr[i].name);
           ftpFileArr[i].setSize(sftpArr[i].size);
           Calendar calendar=Calendar.getInstance();
           Date date=new Date();
           date.setTime(sftpArr[i].mtime);
           calendar.setTime(date);
           ftpFileArr[i].setmTime(calendar);
           ftpFileArr[i].setFile(sftpArr[i].isFile());
		}
		return ftpFileArr;
	}

	@Override
	public void changeDir(String pathName) throws Exception {
		fileHandle=sftpClient.opendir(pathName);
		//sftpClient.realpath(pathName);
		//sftpClient.readlink(pathName);
	}
}
