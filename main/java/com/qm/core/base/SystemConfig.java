package com.qm.core.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

public class SystemConfig {
	private static Properties sysProperties;
	static{
		Reader in=null;
		try{
			sysProperties=new Properties();
			String path=SystemConfig.class.getResource("/").getPath()+"sys_config.properties";
			path = path.replaceAll("%20", " ");
			File cfgFile=new File(path);
			if(!cfgFile.exists()){
				path=SystemConfig.class.getResource("").getPath()+"sys_config.properties";
				cfgFile=new File(path);
			}
			in=new InputStreamReader(new FileInputStream(cfgFile),"utf8");
			sysProperties.load(in);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String getString(String key){
		return getString(key,"");
	}
	public static String getString(String key,String defaultValue){
		try {
			synchronized (sysProperties) {
				String value=sysProperties.getProperty(key);
				if(value==null||"".equals(value)){
					value=sysProperties.getProperty(key);
					if(value==null||"".equals(value)){
						return defaultValue;
					}else{
						return value.trim();
					}
				}else{
					return value.trim();
				}
			}
		} catch (Exception e) {
			return defaultValue;
		}
	}
	public static String getPath(String key){
		String path=getString(key);
		try {
			if("".equals(path)){
				return path;
			}
			if(!path.endsWith("/")){
				path=path+"/";
			}
			File dir=new File(path);
			if(!dir.exists()){
				dir.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}
	public static boolean getBoolean(String key){
		return getBoolean(key,false);
	}
	public static boolean getBoolean(String key,boolean defaultValue){
		String value=getString(key);
		if(value==null || "".equals(value.trim())){
			return defaultValue;
		}
		if(value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("true")){
			return true;
		}else{
			return false;
		}
	}
	public static int getInt(String key){
		return getInt(key,0);
	}
	public static int getInt(String key,int defaultValue){
		try {
			String value=getString(key);
			if(value.equals("")){
				return defaultValue;
			}else{
				return Integer.parseInt(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultValue;
	}
	public static long getLong(String key){
		return getLong(key,0L);
	}
	public static long getLong(String key,long defaultValue){
		try {
			String value=getString(key);
			if(value.equals("")){
				return defaultValue;
			}else{
				return Long.parseLong(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultValue;
	}
	public static String[] getStringArr(String key){
		return getStringArr(key,new String[0]);
	}
	public static String[] getStringArr(String key,String[] defaultValue){
		String value=getString(key);
		if(value==null || "".equals(value.trim())){
			return defaultValue;
		}
		return value.split(",");
	}
}
