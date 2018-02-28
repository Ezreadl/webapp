package com.qm.protocols.impl;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.ScopedPDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.UserTarget;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.MPv3;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.AuthMD5;
import org.snmp4j.security.PrivDES;
import org.snmp4j.security.SecurityLevel;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import com.qm.protocols.SnmpConsole;
import com.qm.sys.domain.Equipment;

public class SnmpConsoleImpl extends SnmpConsole{
	
	protected Snmp snmp;
	
	protected Address address;
	
	public SnmpConsoleImpl(Equipment equipment){
		super(equipment);
	}
	
	public void startListen(){
		try {
			//生成目标地址
			address=GenericAddress.parse(equipment.getIpAddress().trim()+"/"+equipment.getSnmpPort());
			TransportMapping transport=new DefaultUdpTransportMapping();
			snmp=new Snmp(transport);
			if(equipment.getSnmpVersion()==3){
				//设置安全模式
				USM usm=new USM(SecurityProtocols.getInstance(),new OctetString(MPv3.createLocalEngineID()),0);
				SecurityModels.getInstance().addSecurityModel(usm);
			}
			//开始监听
			transport.listen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getRequest(String oid){
		try {
			Target target=null;
			if(equipment.getSnmpVersion()==3){
				//添加用户
				snmp.getUSM().addUser(new OctetString("MD5DES"),new UsmUser(new OctetString("MD5DES"),AuthMD5.ID,new OctetString("MD5DESUserAuthPassword"),PrivDES.ID,new OctetString("MD5DESUserPrivPassword")));
				target=new UserTarget();
				//设置安全级别
				((UserTarget)target).setSecurityLevel(SecurityLevel.AUTH_PRIV);
				((UserTarget)target).setSecurityName(new OctetString("MD5DES"));
				target.setVersion(SnmpConstants.version3);
			}else{
				target=new CommunityTarget();
				if(equipment.getSnmpVersion()==1){
					target.setVersion(SnmpConstants.version1);
					((CommunityTarget)target).setCommunity(new OctetString(equipment.getCommunity()));
				}else{
					target.setVersion(SnmpConstants.version2c);
					((CommunityTarget)target).setCommunity(new OctetString(equipment.getCommunity()));
				}
			}
			//目标对象相关设置
			target.setAddress(address);
			//错误重试2次
			target.setRetries(2);
			//5秒超时
			target.setTimeout(5000);
			PDU pdu=null;
			if(equipment.getSnmpVersion()==3){
				pdu=new ScopedPDU();
				((ScopedPDU)pdu).setContextName(new OctetString("priv"));
			}else{
				pdu=new PDU();
			}
			OID oidObj=new OID(oid);
			pdu.add(new VariableBinding(oidObj));
			pdu.setType(PDU.GET);
			ResponseEvent response=snmp.send(pdu,target);
			VariableBinding vb=(VariableBinding)response.getResponse().getVariableBindings().get(0);
			return vb.getVariable().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println("---------------");
		Equipment equip=new Equipment();
		equip.setIpAddress("127.0.0.1");
		equip.setSnmpPort(161);
		equip.setCommunity("public");
		equip.setSnmpVersion(2);
		SnmpConsoleImpl snmp=new SnmpConsoleImpl(equip);
		snmp.startListen();
		try {
			String result=snmp.getRequest("1.3.6.1.2.1.2.2.1.10.4");
			if(result!=null||!"".equals(result)){
				//System.out.println("result="+Double.parseDouble(result));
//				System.out.println(result);
			}else{
//				System.out.println("result is null");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
