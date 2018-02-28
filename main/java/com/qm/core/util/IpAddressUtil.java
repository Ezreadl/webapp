package com.qm.core.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.net.util.SubnetUtils;
import org.apache.commons.net.util.SubnetUtils.SubnetInfo;


public class IpAddressUtil {
	
	public static List<String> getSubNetListByAuto(List<String> subNetList){
		List<String> retList=new ArrayList<String>(1024);
		for(String subNet:subNetList){
			retList.addAll(getSubNetListByAuto(subNet));
		}
		return retList;
	}
	
	public static List<String> getSubNetListByAuto(String subNet){
		String[] ipRangeArr=subNet.split("[-~～]+");
		if(ipRangeArr.length>=2){
			//补位
			String[] arr=ipRangeArr[1].split("\\.");
			if(arr.length<4){
				//补位
				String[] arr2=ipRangeArr[1].split("\\.");
				if(arr2.length<4){
					String[] arr1=ipRangeArr[0].split("\\.");
					String s="";
					for(int i=0;i<arr1.length-arr2.length;i++){
						if(i==0){
							s=arr1[i];
						}else{
							s+="."+arr1[i];
						}
					}
					ipRangeArr[1]=s+"."+ipRangeArr[1];
				}
			}
			return getIpRangeList(ipRangeArr[0],ipRangeArr[1]);
		}
		String[] ipArr=subNet.split("[ \t]+");
		if(ipArr.length==1 || ipArr[0].contains("/")){
			return getSubNetList(ipArr[0]);
		}else if(ipArr.length==2){
			return getSubNetList(ipArr[0],ipArr[1]);
		}else{
			System.err.println(subNet+" ip地址解析错误");
			return new ArrayList<String>();
		}
	}
	
	//子网所有ip
	public static List<String> getSubNetList(String subNet){
		List<String> retList=new ArrayList<String>();
//		System.out.println("subNet="+subNet);
		if(!subNet.contains("/")){
			retList.add(subNet);
			return retList;
		}
//		System.out.println("subNet2="+subNet);
		SubnetUtils utils = new SubnetUtils(subNet);
		utils.setInclusiveHostCount(true);
		SubnetInfo info = utils.getInfo();
		String[] ipArr=info.getAllAddresses();
		for(String ip:ipArr){
			retList.add(ip);
		}
		/*if( ! retList.contains(info.getNetworkAddress())){
			retList.add(0,info.getNetworkAddress());
		}
		if( ! retList.contains(info.getBroadcastAddress())){
			retList.add(info.getBroadcastAddress());
		}*/
        return retList;
	}
	public static List<String> getSubNetList(String ipAddress,String marsk) {
		List<String> retList=new ArrayList<String>();
		if(!isIpAddress(marsk)){
			if(!"0".equals(marsk)){
				System.err.println(ipAddress+" "+marsk+"未处理过的格式");
			}
			retList.add(ipAddress);
			return retList;
		}
		if(marsk.startsWith("0.")){
			marsk=getReverseMarsk(marsk);
		}
		SubnetUtils utils = new SubnetUtils(ipAddress,marsk);
		utils.setInclusiveHostCount(true);
		SubnetInfo info = utils.getInfo();
		String[] ipArr=info.getAllAddresses();
		for(String ip:ipArr){
			retList.add(ip);
		}
		/*if( ! retList.contains(info.getNetworkAddress())){
			retList.add(0,info.getNetworkAddress());
		}
		if( ! retList.contains(info.getBroadcastAddress())){
			retList.add(info.getBroadcastAddress());
		}*/
        return retList;
	}
	
	public static String getReverseMarsk(String marsk){
		String[] arr=marsk.split("\\.");
		Integer[] retArr=new Integer[4];
		for(int i=0;i<arr.length;i++){
			retArr[i]=255-(0xFF & Integer.parseInt(arr[i]));
		}
		return ArraysUtil.join(retArr,'.');
	}
	public static List<String> getIpRangeList(String startIp,String endIp) {
		List<String> retList=new ArrayList<String>();
		int start=toInteger(startIp);
		int end=toInteger(endIp);
		if(start>end){
			throw new RuntimeException("startIp is big than endIp");
		}
		for(int ip=start;ip<=end;ip++){
			retList.add(format(toArray(ip)));
		}
		return retList;
	}
	public static long[] getIpRange(String ipSubNet) {
//		System.out.println(ipSubNet);
		long[] retArr=new long[2];
		String[] ipRangeArr=ipSubNet.split("[-~～]+");
		if(ipRangeArr.length>=2){
			retArr[0]=toLong(ipRangeArr[0]);
			retArr[1]=toLong(ipRangeArr[1]);
			return retArr;
		}
		String[] ipArr=ipSubNet.split("[ \t]+");
		if(ipArr.length==1 || ipArr[0].contains("/")){
			if(!ipArr[0].contains("/")){
				retArr[0]=toLong(ipArr[0]);
				retArr[1]=retArr[0];
				return retArr;
			}
			SubnetUtils utils = new SubnetUtils(ipArr[0]);
			utils.setInclusiveHostCount(true);
			SubnetInfo info = utils.getInfo();
			retArr[0]=toLong(info.getLowAddress());
			retArr[1]=toLong(info.getHighAddress());
		}else if(ipArr.length==2){
			if(!isIpAddress(ipArr[1])){
				if(!"0".equals(ipArr[1])){
					System.err.println(ipSubNet+"未处理过的格式");
				}
				retArr[0]=toLong(ipArr[0]);
				retArr[1]=retArr[0];
				return retArr;
			}
			if(ipArr[1].startsWith("0.")){
				ipArr[1]=getReverseMarsk(ipArr[1]);
			}
			SubnetUtils utils = new SubnetUtils(ipArr[0],ipArr[1]);
			utils.setInclusiveHostCount(true);
			SubnetInfo info = utils.getInfo();
			retArr[0]=toLong(info.getLowAddress());
			retArr[1]=toLong(info.getHighAddress());
		}else{
			System.err.println(ipSubNet+" ip范围解析错误");
			return null;
		}
		return retArr;
	}
	
	public static long toLong(String ip){
		if(ip==null || "".equals(ip.trim())){
			return 0;
		}
		String[] ipArr=ip.split("\\.");
		long addr=0;
		for (int i = 0; i <= 3; ++i) {
            long n =Long.parseLong(ipArr[i]);
            addr |= ((n & 0xff) << 8*(3-i));
        }
		return addr;
	}
	
	public static int toInteger(String ip){
		if(ip==null || "".equals(ip.trim())){
			return 0;
		}
		String[] ipArr=ip.split("\\.");
		int addr=0;
		for (int i = 0; i <= 3; ++i) {
            int n =Integer.parseInt(ipArr[i]);
            addr |= ((n & 0xff) << 8*(3-i));
        }
		return addr;
	}
	
	public static String toStr(long ip){
		int ret[] = new int[4];
        for (int j = 3; j >= 0; --j) {
        	ret[j] |= ((ip >> 8*(3-j)) & (0xff));
        }
        StringBuilder sb=new StringBuilder();
        for(int j=0;j<=3;j++){
        	if(j==3){
        		sb.append(ret[j]);
        	}else{
        		sb.append(ret[j]+".");
        	}
        }
        return sb.toString();
	}
	
	//获取掩码位数
	public static int getMarskLen(long startIp,long endIp){
		if(startIp==endIp){
			return 32;
		}
		String s=Long.toBinaryString(startIp);
		String e=Long.toBinaryString(endIp);
		if(s.length()<32){
			s=StringUtil.getLPadding(s,'0',32);
		}
		if(e.length()<32){
			e=StringUtil.getLPadding(e,'0',32);
		}
		int sub=0;
		while(sub<32){
			if(s.charAt(sub)!=e.charAt(sub)){
				break;
			}
			sub++;
		}
		return sub;
	}
	
	public static int[] toArray(int val) {
        int ret[] = new int[4];
        for (int j = 3; j >= 0; --j) {
            ret[j] |= ((val >>> 8*(3-j)) & (0xff));
        }
        return ret;
    }
	
	public static String format(int[] octets) {
        StringBuilder str = new StringBuilder();
        for (int i =0; i < octets.length; ++i){
            str.append(octets[i]);
            if (i != octets.length - 1) {
                str.append(".");
            }
        }
        return str.toString();
    }
	public static boolean isIpAddress(String name){
		if(name==null || "".equals(name)){
			return false;
		}
		if(name.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")){
			return true;
		}else{
			return false;
		}
	}
	//将ipList整理成ipStart-ipEnd格式
	public static List<String> formatIpRange(List<String> ipList){
		List<String> retList=new ArrayList<String>();
		if(ipList==null || ipList.size()==0){
			System.err.println("格式化范围的iPList为空");
			return retList;
		}else if(ipList.size()==1){
			retList.add(ipList.get(0)+"-"+ipList.get(0));
			return retList;
		}
		//排序
		List<Integer> ipIntList=new ArrayList<Integer>();
		for(String ip:ipList){
			ipIntList.add(toInteger(ip));
		}
		Collections.sort(ipIntList);
		int firstIp=ipIntList.get(0);
		int preIp=firstIp;
		for(int i=1;i<ipIntList.size();i++){
			int curIp=ipIntList.get(i);
			if(curIp-1>preIp){
				String startIp=format(toArray(firstIp));
				String endIp=format(toArray(preIp));
				retList.add(startIp+"-"+endIp);
				firstIp=curIp;
			}
			preIp=curIp;
		}
		String startIp=format(toArray(firstIp));
		String endIp=format(toArray(ipIntList.get(ipIntList.size()-1)));
		retList.add(startIp+"-"+endIp);
		return retList;
	}
	
	//===========================================2016-05-29 zhanglei 爬虫Ip所用 =========================================
	/*
	 * 验证IP是否属于某个IP段
	 * ipSection IP段（以'-'分隔）
	 * ip 所验证的IP号码
	 */
	public static boolean ipExistsInRange(String ip, String ipSection) {
		ipSection = ipSection.trim();
		ip = ip.trim();
		int idx = ipSection.indexOf('-');
		String beginIP = ipSection.substring(0, idx);
		String endIP = ipSection.substring(idx + 1);
		return getIp2long(beginIP) <= getIp2long(ip)&& getIp2long(ip) <= getIp2long(endIP);

	}
	public static long getIp2long(String ip) {
		ip = ip.trim();
		String[] ips = ip.split("\\.");
		long ip2long = 0L;
		for (int i = 0; i < 4; ++i) {
			ip2long = ip2long << 8 | Integer.parseInt(ips[i]);
		}
		return ip2long;
	}
	public static long getIp2long2(String ip) {
		ip = ip.trim();
		String[] ips = ip.split("\\.");
		long ip1 = Integer.parseInt(ips[0]);
		long ip2 = Integer.parseInt(ips[1]);
		long ip3 = Integer.parseInt(ips[2]);
		long ip4 = Integer.parseInt(ips[3]);
		long ip2long = 1L * ip1 * 256 * 256 * 256 + ip2 * 256 * 256 + ip3 * 256 + ip4;
		return ip2long;
	}
	
	//===========================================end==============================================================
	
	
	public static void main(String[] args) throws Exception{
		/*String ipStrs = "103.37.176.0/22@117.139.208.0/27@117.139.208.32/27@223.86.0.224/27@223.87.85.160/28";
		 String[] arr = ipStrs.split("@");
		 List<String> iplist = java.util.Arrays.asList(arr);
	    List<String> standardDataList=IpAddressUtil.getSubNetListByAuto(iplist);//拆分数据
		Set<String> standardDataSet=new HashSet<String>(1024);
		standardDataSet.addAll(standardDataList);
		 for(String ip:standardDataSet){
			// System.out.println(IpAddressUtil.toLong(ip));
			 System.out.println(IpAddressUtil.toStr(IpAddressUtil.toLong(ip)));
		 }
		// System.out.println(IpAddressUtil.toStr(IpAddressUtil.toLong("117.172.20.34")));
*/		 
		//10.10.10.116 是否属于固定格式的IP段10.10.1.00-10.10.255.255

        
        
//        long ip = 1863137060l;
//        System.out.println(IpAddressUtil.toStr(ip));
//        System.out.println(IpAddressUtil.toLong("10.25.96.33"));
		System.out.println(IpAddressUtil.getSubNetListByAuto("111.9.117.32/27"));
		System.out.println(IpAddressUtil.getSubNetListByAuto("111.9.117.64/28"));
		System.out.println(IpAddressUtil.getSubNetListByAuto("117.177.105.1/25"));
	}

}
