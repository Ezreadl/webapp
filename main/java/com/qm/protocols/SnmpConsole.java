package com.qm.protocols;

import com.qm.sys.domain.Equipment;

public abstract class SnmpConsole {
	
	protected Equipment equipment;
	
	public SnmpConsole(Equipment equipment){
		this.equipment=equipment;
	}
	
	public abstract void startListen();
	
	public abstract String getRequest(String oid);
}
