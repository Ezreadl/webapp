package com.qm.protocols;

import com.qm.sys.domain.Equipment;

public interface ConsoleListener {
	
	public void onReadBack(Equipment equipment,String readBack,boolean isLoginStep);
	
	public void onSendWrite(Equipment equipment,String sendWrite,boolean isLoginStep);
	
	public void onLoginInfo(Equipment equipment, String loginMsg, boolean readBack);
	
	public void onLoginException(Equipment equipment,String loginMsg,Exception e);
	
	public void onLoginout(Equipment equipment,String loginMsg);
	
	public void onReadBackException(Equipment equipment,String readMsg,Exception e);
	
	public void onReadBackTimeOut(Equipment equipment,String readMsg);
	
}
