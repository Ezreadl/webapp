package com.qm.protocols;

import com.qm.sys.domain.Equipment;

public class ConsolAdapter implements ConsoleListener{

	@Override
	public void onReadBack(Equipment equipment, String readBack,
			boolean isLoginStep) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSendWrite(Equipment equipment, String sendWrite,
			boolean isLoginStep) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoginInfo(Equipment equipment, String loginMsg,
			boolean readBack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoginException(Equipment equipment, String loginMsg,
			Exception e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoginout(Equipment equipment, String loginMsg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReadBackException(Equipment equipment, String readMsg,
			Exception e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReadBackTimeOut(Equipment equipment, String readMsg) {
		// TODO Auto-generated method stub
		
	}


}
