package com.qm.core.util;

import javax.servlet.http.HttpServletRequest;

public class IpAddress {
	
	private SplitUtil splitUtil=new SplitUtil();
	
	public static void main(String[] args) {
		long a=10000*10000*10000;
		System.out.println(a);
		int n=0;
		while(a>=2){
			System.out.println("a="+a);
			a=a/2;
			n++;
		}
		System.out.println(n);
	}
	
	//空ip返回-2，ipv6返回-1
	public long parseIpv4ToLong(String ipAddress){
		if(ipAddress==null || "".equals(ipAddress)){
			return -2;
		}
		long addr=0;
		String[] ipArr=splitUtil.splitText(ipAddress,".");
		if(splitUtil.getLength()==4){//ipv4
			for (int i = 0; i <= 3; ++i) {
	            long n =Long.parseLong(ipArr[i]);
	            addr |= ((n & 0xff) << 8*(3-i));
	        }
			return addr;
		}
		return -1;
	}
	//将ipv6地址转换为long数组格式0高位，1低位
	public long[] parseIpv6ToLongArr(String ipAddress){
		long[] addr={0L,0L};
		String[] aArr=splitUtil.splitText(ipAddress,"::");
		String ip2=splitUtil.getLength()>=2?aArr[1]:null;
		long n=0;
		if(aArr[0]!=null && !"".equals(aArr[0])){
			String[] ipg1=splitUtil.splitText(aArr[0],":");
			int len1=splitUtil.getLength();
			for(int i=0;i<len1;i++){
				n = Long.parseLong(ipg1[i],16);
				if(i<4){
					addr[0] |= ((n & 0xffff) << 16*(3-i));
				}else{
					addr[1] |= ((n & 0xffff) << 16*(7-i));
				}
			}
		}
		if(ip2!=null && !"".equals(ip2)){
			String[] ipg2=splitUtil.splitText(ip2,":");
			int len2=splitUtil.getLength();
			for(int i=len2-1;i>=0;i--){
				n = Long.parseLong(ipg2[i],16);
				if(len2-i-1<4){
					addr[1] |= ((n & 0xffff) << 16*(len2-i-1));
				}else{
					addr[0] |= ((n & 0xffff) << 16*(len2-i-5));
				}
			}
		}
		return addr;
	}
	
	/**
	 * 获取用户客户端真实IP
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) { 
	    String ip = request.getHeader("x-forwarded-for"); 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_CLIENT_IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getRemoteAddr(); 
	    } 
	    return ip; 
	  } 
	
	/*private String toCharString(long ip,int len){
		String s=Long.toBinaryString(ip);
		List<String> list=new ArrayList<String>();
		for(int i=s.length();i>=0;i=i-len){
			int start=i-len;
			if(start<0){
				start=0;
			}
			if(i==start){
				continue;
			}
			list.add(Long.toString(Long.parseLong(s.substring(start,i),2)));
		}
		Collections.reverse(list);
		return list.toString();
	}*/
}
