package com.qm.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;

import com.qm.core.base.SystemConfig;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
* RSA安全编码组件 
*   
* @version 1.0 
* @since 1.0 
*/ 
public abstract class RSACoder{   
    public static final String KEY_ALGORITHM = "RSA";   
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";   
    private static final String PUBLIC_KEY = "RSAPublicKey";   
    private static final String PRIVATE_KEY = "RSAPrivateKey";   
	private static String path=SystemConfig.getPath("license_path");
    //缓存密钥，无需每次都读取文件
    private static Map<String,String> keyMap=new HashMap<String,String>();
    
    /**
     * 解密用私钥解密 
     *   
     * @param data 
     * @param key 
     * @return 
     * @throws Exception 
     */ 
    public static String decryptByPrivateKey(String data, String key) throws Exception{   
    		key = getPrivateKey(key);
			if(data==null||"".equals(data)||key==null||"".equals(key)){
				return data;
			}
			// 对密钥解密   
			byte[] keyBytes = decryptBASE64(key);   

			// 取得私钥   
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);   
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);   
			Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);   

			// 对数据解密   
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());   
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] temp=cipher.doFinal(decryptBASE64(data));
			return new String(temp,"utf-8");
    }   

    /**
     * 加密
     * 用公钥加密 
     *   
     * @param data 
     * @param key 
     * @return 
     * @throws Exception 
     */ 
    public static String encryptByPublicKey(String data, String key){ 
    	try {
    		key = getPublicKey(key);
    		if(data==null||"".equals(data)||key==null||"".equals(key)){
				return data;
			}
			// 对公钥解密   
			byte[] keyBytes = decryptBASE64(key);   

			// 取得公钥   
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);   
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);   
			Key publicKey = keyFactory.generatePublic(x509KeySpec);   

			// 对数据加密   
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());   
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);   
			byte[] temp=cipher.doFinal(data.getBytes("utf-8"));
			return encryptBASE64(temp);
		} catch (Exception e) {
			e.printStackTrace();
			return data;
		} 
    }   

    /**
     * 取得私钥 
     *   
     * @param keyMap 
     * @return 
     * @throws Exception 
     */ 
    public static String getPrivateKey(String keyName) {   
		try {
			String keyValue=keyMap.get(keyName+"PrivateKey");
			if(keyValue==null||"".equals(keyValue)){
				keyValue=readKey(getPath(),keyName+"PrivateKey.security");
				keyMap.put(keyName+"PrivateKey", keyValue);
			}
			return keyValue;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }   

    /**
     * 取得公钥 
     *   
     * @param keyMap 
     * @return 
     * @throws Exception 
     */ 
    public static String getPublicKey(String keyName){   
    	try {
			String keyValue=keyMap.get(keyName+"PublicKey");
			if(keyValue==null||"".equals(keyValue)){
				keyValue=readKey(getPath(),keyName+"PublicKey.security");
				keyMap.put(keyName+"PublicKey", keyValue);
			}
			return keyValue;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }   

    /**
     * 初始化密钥 
     *   
     * @return 
     * @throws Exception 
     */ 
    private static void initKey(String keyName) throws Exception {   
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);   
        keyPairGen.initialize(1024);   
        KeyPair keyPair = keyPairGen.generateKeyPair();   
        // 公钥   
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();   
        String publicKeyStr=encryptBASE64(publicKey.getEncoded());
        writeKey(getPath()+keyName+"PublicKey.security",publicKeyStr);
        // 私钥   
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); 
        String privateKeyStr=encryptBASE64(privateKey.getEncoded());
        writeKey(getPath()+keyName+"PrivateKey.security",privateKeyStr);
    }   
    //写文件
    private static void writeKey(String file,String content) throws IOException{
    	OutputStream out=null;
    	try {
			out=new FileOutputStream(file);
			out.write(content.getBytes("iso8859-1"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(out!=null){
					out.flush();
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
    //读文件
    private static String readKey(String path,String fileName) throws Exception{
    	File file=new File(path+fileName);
    	byte[] data=new byte[(int)file.length()];
    	InputStream in=null;
    	try {
    		in=new FileInputStream(path+fileName);
			in.read(data);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		}finally{
			try {
				if(in!=null) in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    	return new String(data,"iso8859-1");
    }
    private static String getPath(){
//    	String path=RSACoder.class.getClassLoader().getResource("").getPath();
    	try {
			path=URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
//		if(path.indexOf("WEB-INF") > 0){
//			path=path.substring(0, path.indexOf("WEB-INF")+7)+"/security/";
//		}
		return path;
    }
    private static byte[] decryptBASE64(String s) throws IOException{
    	BASE64Decoder decoder=new BASE64Decoder();
    	return decoder.decodeBuffer(s);
    }
    private static String encryptBASE64(byte[] b){
    	BASE64Encoder encoder=new BASE64Encoder();
    	return encoder.encode(b);
    }
    //我们产生服务器license数据
    public static String getLicense() throws Exception{
		StringBuilder strb = new StringBuilder();
		File f = new File(path+"/customer.txt");
		List<String> ll = TextFileUtil.readData(f);
		for (String str : ll) {
			if(str.split(":")[0].equals("versionNumber")){
				strb.append(str.split(":")[1]);
			}else if(str.split(":")[0].equals("Time")){
				strb.append(str.split(":")[1].split("~")[0]+",");
				strb.append(str.split(":")[1].split("~")[1]+",");
			}else{
				strb.append(str.split(":")[1]+",");
			}
		}
		String Data = strb.toString();
		System.out.println("加密Data="+Data);
		String keyData = encryptByPublicKey(Data,"license");
    	File fl = new File(getPath()+"/License.sec");
    	TextFileUtil.writeToFile(fl,keyData,"utf-8");
		return keyData;
    }
    public static String getLocalData() throws Exception{
		String Data = "BC-EC-89-90-FA,200,100,200,v5.1";
    	return Data;
    }
    /*解密后的数据*/
    public static String[] getLicenseData () throws Exception{
    	File fl = new File(getPath()+"/License.sec");
    	List<String> ll = TextFileUtil.readData(fl, "utf-8");
    	StringBuilder sbd = new StringBuilder();
    	for (String object : ll) {
			sbd.append(object);
		}
    	String dtt = sbd.toString();
    	System.out.println(dtt);
    	String lsdata = decryptByPrivateKey(dtt,"license");
    	String []arr = lsdata.split(",");
    	for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
    	return arr ;
    }
    public static String[] getLicenseData (File fl) throws Exception{
    	List<String> ll = TextFileUtil.readData(fl, "utf-8");
    	StringBuilder sbd = new StringBuilder();
    	for (String object : ll) {
			sbd.append(object);
		}
    	String dtt = sbd.toString();
    	System.out.println(dtt);
    	System.out.println("-----------11------begin----------------------------");
    	String lsdata = decryptByPrivateKey(dtt,"license");
    	System.out.println(lsdata);
    	String []arr = lsdata.split(",");
    	System.out.println("end----------------------------");
    	return arr ;
    }
    public static void main(String[] args) throws Exception {
    	//同一个客户只产生一次密钥
//    	initKey("license");
    	getLicense();
//    	File file = new File(getPath()+"/License.sec");
//    	getLicenseData(file);
    	getLicenseData();
//    	validateData(dst);
	}
} 