package com.qm.core.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.qm.core.base.SystemConfig;

import aiismg.jcmppapi.CMPPAPI;
import sample.SMArgument;

public class ShortMsgUtil {
	
	private static boolean short_Messge_Open=SystemConfig.getBoolean("Short_Messge_Open");
	
	private static String short_Message_Interface=SystemConfig.getString("Short_Message_Interface"); 
	
	public static void sendShortMessage(String message,String phones) throws Exception{
		if(short_Messge_Open){
			if("xizhang".equalsIgnoreCase(short_Message_Interface)){
				sendShortMessageXZ(message, phones);
				Thread.sleep(2000);//暂停2秒
			}else if("sichuan".equalsIgnoreCase(short_Message_Interface)){
				String[] msgArr=StringUtil.splitEncodeLength(message,"gbk",130);
				for(String msg:msgArr){
					sendShortMessageSC(msg, phones);
				}
			}else{
				String[] msgArr=StringUtil.splitEncodeLength(message,"gbk",130);
				for(String msg:msgArr){
					sendShortMessageSC(msg, phones);
				}
			}
		}
	}
	
	/*public static void sendShortMessageXZ(String message,String phones) throws Exception{
		String configFile=null;
		try {
			configFile = ShortMessageUtil.class.getClassLoader().getResource("/javacmppc.ini").getPath();
		} catch (Exception e) {
			configFile = ShortMessageUtil.class.getClassLoader().getResource("javacmppc.ini").getPath();
		}
		if(configFile==null){
			throw new Exception("short message get config error:");
		}
		byte[] sMsgID = new byte[21];
		SMArgument pArgs = new SMArgument();
		pArgs.nNeedReply = (byte)1;
		pArgs.nMsgLevel = (byte)2;
		pArgs.sServiceID = "158".getBytes();
		pArgs.nMsgFormat = (byte)15;
		pArgs.sFeeType = "01".getBytes();
		pArgs.sFeeCode = "0010".getBytes();
		pArgs.sSrcTermID = "106573000002".getBytes();
		pArgs.sMsgCon = message.getBytes("gbk");
		int length = pArgs.sMsgCon.length;
		CMPPAPI pCMPPAPI = new CMPPAPI();
		if( pCMPPAPI.InitCMPPAPI(configFile) == 0 ){
			for(String phone:phones.split("[,，;；]+")){
				if(phone==null || "".equals(phone.trim())){
					continue;
				}
				pArgs.sDestTermID=phone.getBytes();
				int result=pCMPPAPI.CMPPSendSingle( pArgs.nNeedReply, pArgs.nMsgLevel,
						pArgs.sServiceID, pArgs.nMsgFormat,
						pArgs.sFeeType, pArgs.sFeeCode,
						pArgs.sValidTime, pArgs.sAtTime,
						pArgs.sSrcTermID, pArgs.sDestTermID,
						length, pArgs.sMsgCon,
						sMsgID, (byte)0, null, (byte)0, (byte)0 );
				if(result!=0){
					throw new Exception(result+" short message send error:"+pCMPPAPI.GetErrCode());
				}
			}
		}else{
			throw new Exception("short message init error!");
		}
	}*/
	
	/**
	 * 西藏发送短信接口
	 * @param message
	 * @param phones
	 * @throws Exception
	 */
	public static void sendShortMessageXZ(String message,String phones) throws Exception{
		String configFile=null;
		try {
			configFile = ShortMsgUtil.class.getClassLoader().getResource("/javacmppc.ini").getPath();
		} catch (Exception e) {
			configFile = ShortMsgUtil.class.getClassLoader().getResource("javacmppc.ini").getPath();
		}
		if(configFile==null){
			throw new Exception("short message get config error:");
		}
		byte[] sMsgID = new byte[21];
		SMArgument pArgs = new SMArgument();
		pArgs.nNeedReply = (byte)0;
		pArgs.nMsgLevel = (byte)2;
		pArgs.sServiceID = "158".getBytes();
		pArgs.nMsgFormat = (byte)8;//使用USC2编码
		pArgs.sFeeType = "01".getBytes();
		pArgs.sFeeCode = "0010".getBytes();
		pArgs.sSrcTermID = "106573000002".getBytes();
//		pArgs.sMsgCon = message.getBytes("gbk");
//		int length = pArgs.sMsgCon.length;
		CMPPAPI pCMPPAPI = new CMPPAPI();
		if( pCMPPAPI.InitCMPPAPI(configFile) == 0 ){
			for(String phone:phones.split("[,，;；]+")){
				if(phone==null || "".equals(phone.trim())){
					continue;
				}
				pArgs.sDestTermID=phone.getBytes();
				List<byte[]> msgByteList=getUSC2ContentByteList(message);
				byte cTpUdhi=msgByteList.size()>1?(byte)1:(byte)0;//长短信为1，短短信为0
				for(byte[] msgByte:msgByteList){
					int result=pCMPPAPI.CMPPSendSingle( pArgs.nNeedReply, pArgs.nMsgLevel,
							pArgs.sServiceID, pArgs.nMsgFormat,
							pArgs.sFeeType, pArgs.sFeeCode,
							pArgs.sValidTime, pArgs.sAtTime,
							pArgs.sSrcTermID, pArgs.sDestTermID,
							msgByte.length, msgByte,
							sMsgID, (byte)0, null, (byte)0, cTpUdhi );
					if(result!=0){
						throw new Exception(result+" short message send error:"+pCMPPAPI.GetErrCode());
					}
				}
			}
		}else{
			throw new Exception("short message init error!");
		}
	}
	
	private static List<byte[]> getUSC2ContentByteList(String message) {
		List<byte[]> list = new ArrayList<byte[]>();
		try {
			byte[] msgUCS2 = message.getBytes("UnicodeBigUnmarked");//UCS2编码
			int maxMsgLen = 140;//单条短信允许的最大长度
			int headLen=6;//长短信协议头长度
			if (msgUCS2.length > maxMsgLen) {// 长短信发送
				// int tpUdhi = 1;
				// 长消息是1.短消息是0
				// int msgFmt = 0x08;//长消息不能用GBK，使用UCS2编码
				int maxConLen=maxMsgLen - headLen;//短信内容长度
				int msgUCS2Count = msgUCS2.length/maxConLen;//短信条数
				int modContLen=msgUCS2.length % maxConLen;
				if(modContLen>0){
					msgUCS2Count++;
				}
				// 长短信分为多少条发送
				byte[] tp_udhiHead = new byte[headLen];
				tp_udhiHead[0] = 0x05;
				tp_udhiHead[1] = 0x00;
				tp_udhiHead[2] = 0x03;
				tp_udhiHead[3] = 0x0A;
				tp_udhiHead[4] = (byte) msgUCS2Count;
				tp_udhiHead[5] = 0x01;// 默认为第一条
				int sendNumber=0;
				for(;sendNumber<msgUCS2.length/maxConLen;sendNumber++){
					tp_udhiHead[5] = (byte) (sendNumber + 1);//第几条短信
					byte[] msgContent=new byte[headLen+maxConLen];
					System.arraycopy(tp_udhiHead,0,msgContent,0,headLen);//拷贝协议头
					System.arraycopy(msgUCS2,sendNumber*maxConLen,msgContent,headLen,maxConLen);//拷贝发送内容
					list.add(msgContent);
				}
				if(modContLen>0){
					tp_udhiHead[5] = (byte) (sendNumber + 1);//第几条短信
					byte[] msgContent=new byte[headLen+modContLen];
					System.arraycopy(tp_udhiHead,0,msgContent,0,headLen);
					System.arraycopy(msgUCS2,sendNumber*maxConLen,msgContent,headLen,modContLen);
					list.add(msgContent);
				}
			}else{
				list.add(msgUCS2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 四川移动短信接口
	 * @param args
	 * @throws Exception
	 */
	public static void sendShortMessageSC(String message,String phones) {
		if (phones!=null && !"".equals(phones)) {
			if( message.getBytes().length > 130 )
	        {
	            throw new RuntimeException( "短信内容太长,已超过130个字符!" );
	        }
//			System.out.println("给"+phones+"发送短信"+message);
			String[] ps = phones.split(",");
			for (int i=0;i<ps.length;i++) {
				try {
					if(ps[i]==null || "".equals(ps[i])){
						continue;
					}
					String return_value = "";
					URL url = new URL("http://10.101.50.100:8080/terminal/terminalSend");
					HttpURLConnection conn = ( HttpURLConnection )url.openConnection();
					conn.setRequestMethod( "POST" );
					conn.setDoOutput( true );
					conn.setDoInput( true );
					PrintWriter writer = new PrintWriter( conn.getOutputStream() );
					writer.print("user=kg&pass=kg&phone="+ps[i]+"&psw="+URLEncoder.encode(message,"gbk"));
					writer.flush();
			     	writer.close();
			     	String tempStr = "";
			     	BufferedReader read = new BufferedReader( new InputStreamReader( conn.getInputStream() ) );
			     	while( ( tempStr = read.readLine() ) != null )
			     	{
			     		return_value = return_value + tempStr;
			     	}
			     	read.close();
			     	conn.disconnect();
			     	if( !"0".equals( return_value ) )
		            {
		                return_value = "1";
		            }
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
	}
	public static void main(String[] args) throws Exception{
//		String msg="2014-05-20 08:01:01财务集中化内部核心区防火墙备(10.233.86.50)Ethernet0/0/2进口0出口0状态down告警级别2";
//		String[] msgArr=StringUtil.splitEncodeLength(msg,"gbk",130);
//		for(String s:msgArr){
//			System.out.println(s);
//		}
//		System.out.println(msg.length());
//		System.out.println(msg.getBytes("gbk").length);
		String s="2014-05-20 08:01:01财务集中化内部核心区防火墙备(10.233.86.50)Ethernet0/0/2进口0出口0状态down告警级别2";
		byte[] us2Arr=s.getBytes("UnicodeBigUnmarked");
		System.out.println(Arrays.toString(us2Arr));
		List<byte[]> list=getUSC2ContentByteList(s);
		for(byte[] arr:list){
			System.out.println(Arrays.toString(arr));
		}
	}
}
