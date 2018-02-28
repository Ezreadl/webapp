package com.qm.protocols;

import com.qm.protocols.impl.FTPSConsoleImpl;
import com.qm.protocols.impl.FtpConsoleImpl;
import com.qm.protocols.impl.SFTPConsoleImpl;
import com.qm.sys.domain.Equipment;

public class FtpConsoleFactory {
	
	public static FtpConsole createFtpConsole(Equipment equipment) throws Exception{
		if("ftp".equalsIgnoreCase(equipment.getFtpProtocol())){
			return new FtpConsoleImpl(equipment);
		}else if("sftp".equalsIgnoreCase(equipment.getFtpProtocol())){
			return new SFTPConsoleImpl(equipment);
		}else if("ftps".equalsIgnoreCase(equipment.getFtpProtocol())){
			return new FTPSConsoleImpl(equipment);
		}
		return null;
	}
	
	public static FtpConsole createFtpConsole(Equipment equipment,int loginRetryCount,int loginRetryDelay) throws Exception{
		if("ftp".equalsIgnoreCase(equipment.getFtpProtocol())){
			return new FtpConsoleImpl(equipment, loginRetryCount, loginRetryDelay);
		}else if("sftp".equalsIgnoreCase(equipment.getFtpProtocol())){
			return new SFTPConsoleImpl(equipment,loginRetryCount,loginRetryDelay);
		}else if("ftps".equalsIgnoreCase(equipment.getFtpProtocol())){
			return new FTPSConsoleImpl(equipment,loginRetryCount,loginRetryDelay);
		}
		return null;
	}
	
}
