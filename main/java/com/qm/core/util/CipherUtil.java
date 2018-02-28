package com.qm.core.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class CipherUtil {
	public static String md5(String s){
		try {
			if(s==null){
				return null;
			}
			if("".equals(s)){
				return "";
			}
			MessageDigest md=MessageDigest.getInstance("md5");
			byte[] b=md.digest(s.getBytes());
			BASE64Encoder encoder=new BASE64Encoder();
			return encoder.encode(b);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return s;
	}
	public static String baseEncrypt(String s){
		if(s==null){
			return null;
		}
		if("".equals(s)){
			return "";
		}
		byte[] b=s.getBytes();
		BASE64Encoder encoder=new BASE64Encoder();
		return encoder.encode(b);
	}
	public static String baseDecrypt(String s){
		if(s==null){
			return null;
		}
		if("".equals(s)){
			return "";
		}
		String t=s;
		byte[] b;
		try {
			BASE64Decoder decoder=new BASE64Decoder();
			b = decoder.decodeBuffer(s);
			t=new String(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return t;
	}
	public static void main(String[] args) {
//		System.out.println(md5("000000"));
		
		//String s="login:||_||menglin||_||assword:||_||1qaz@WSX";
//		String s127="login:,user:,userName:|administrator\r|password:,pass:|wangchong\r";
//		String s127="login:,user:,userName:|menglin|password:,pass:|!QAZ2wsx";
//		String[] arr=s127.split("\\|");
//		for(String s:arr){
//			System.out.println(baseEncrypt(s));
//		}
		System.out.println(md5("wjm@123"));
		System.out.println(baseEncrypt(baseEncrypt("MlKj#yxgs0852")));
//		System.out.println("---------------------");
		System.out.println(baseDecrypt(baseDecrypt("")));
		System.out.println(baseDecrypt(baseDecrypt("SVZGQldqSjNjM2c9")));
//		System.out.println("aaaaaaa");
//		String t="bG9naW46LHVzZXI6LHVzZXJOYW1lOg==|YWRtaW5pc3RyYXRvcg==|cGFzc3dvcmQ6LHBhc3M6|d2FuZ2Nob25n";
//		for(String s:t.split("\\|")){
//			System.out.println(baseDecrypt(s));
//		}
//		System.out.println(baseDecrypt("VGhRLw=="));
//		System.out.println(baseDecrypt(baseDecrypt("VTBOWlJFQjVZVzExTURJeA==")));
//		System.out.println(baseDecrypt(baseDecrypt(baseDecrypt("VGhRLw=="))));
	}
}
