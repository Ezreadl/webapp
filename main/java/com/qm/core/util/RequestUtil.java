package com.qm.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	//key区分大小写Map<className,Map<fileName,Method>>
	private static Map<String,Map<String,Method>> setMethodMap=new HashMap<String,Map<String,Method>>();
	//key区分大小写
	private static Map<String,Map<String,Method>> getMethodMap=new HashMap<String,Map<String,Method>>();
	
	private static void initClassGetSetMethod(Class<?> objClazz){
		Map<String,String> fieldNameMap=new HashMap<String,String>();
		for(Field field:objClazz.getDeclaredFields()){
			fieldNameMap.put(field.getName().toLowerCase(),field.getName());
		}
		Method[] methodArr=objClazz.getDeclaredMethods();
		Map<String,Method> setMap=new HashMap<String,Method>();
		Map<String,Method> getMap=new HashMap<String,Method>();
		for(Method method:methodArr){
			String methodName=method.getName();
			if(methodName.startsWith("set")){
				String fieldName=fieldNameMap.get(methodName.toLowerCase().substring(3));
				if(fieldName!=null){
					setMap.put(fieldName,method);
				}
			}else if(methodName.startsWith("get")){
				String fieldName=fieldNameMap.get(methodName.toLowerCase().substring(3));
				if(fieldName!=null){
					getMap.put(fieldName,method);
				}
			}else if(methodName.startsWith("is")){
				String fieldName=fieldNameMap.get(methodName.toLowerCase().substring(2));
				if(fieldName!=null){
					getMap.put(fieldName,method);
				}
			}
		}
		setMethodMap.put(objClazz.getName(), setMap);
		getMethodMap.put(objClazz.getName(), getMap);
	}
	
	public static Map<String,Method> classGetMethodMap(Class<?> objClazz){
		Map<String,Method> getMap=getMethodMap.get(objClazz.getName());
		if(getMap==null){
			initClassGetSetMethod(objClazz);
			getMap=getMethodMap.get(objClazz.getName());
		}
		return getMap;
	}
	
	public static Map<String,Method> classSetMethodMap(Class<?> objClazz){
		Map<String,Method> setMap=setMethodMap.get(objClazz.getName());
		if(setMap==null){
			initClassGetSetMethod(objClazz);
			setMap=setMethodMap.get(objClazz.getName());
		}
		return setMap;
	}
	
	
	public static Object createObjectByMap(Class<?> objClazz,Map<String,Object> dataMap) throws Exception{
		Object obj=objClazz.newInstance();
		Map<String,Method> setMethodMap=classSetMethodMap(objClazz);
		for(String fieldName:setMethodMap.keySet()){
			Object value=dataMap.get(fieldName);
			if(value!=null){
				Method method=setMethodMap.get(fieldName);
				Object v=ConvertUtil.castFromObject(value,method.getParameterTypes()[0]);
				if(v!=null){
					method.invoke(obj,v);
				}
			}
		}
		/*Object obj=objClazz.newInstance();
		Field[] fieldArr=objClazz.getDeclaredFields();
		for(Field field:fieldArr){
			String fieldName=field.getName();
			Object value=dataMap.get(fieldName);
			if(value==null){
				continue;
			}else{
				Map<String,Method> setMap=classSetMethodMap(objClazz);
				Method method=setMap.get(fieldName.toLowerCase());
				Object v=ConvertUtil.castFromObject(value,field.getType());
				if(v!=null){
					method.invoke(obj,v);
				}
			}
		}*/
		return obj;
	}
	
	@SuppressWarnings({"rawtypes"})
	public static List createObjectListByCollection(Class<?> objClazz,Collection<Map<String,Object>> collection) throws Exception{
		List<Object> retList=new ArrayList<Object>();
		Iterator<Map<String,Object>> interator=collection.iterator();
		while(interator.hasNext()){
			Map<String,Object> valueMap=interator.next();
			Object obj=createObjectByMap(objClazz,valueMap);
			retList.add(obj);
		}
		return retList;
	}
	public static Object createObjectByRequest(Class<?> objClazz,HttpServletRequest request) throws Exception{
		Object obj=objClazz.newInstance();
		Map<String,Method> setMethodMap=classSetMethodMap(objClazz);
		for(String fieldName:setMethodMap.keySet()){
			Object value=request.getParameter(fieldName);
			if(value!=null){
				Method method=setMethodMap.get(fieldName);
				Object v=ConvertUtil.castFromObject(value,method.getParameterTypes()[0]);
				if(v!=null){
//					if("java.lang.String".equals(v.getClass().getName())){
//						v = (Object)java.net.URLDecoder.decode(v.toString(),"UTF-8");
//					}
					method.invoke(obj,v);
				}
			}
		}
		return obj;
	}
	
	public static Object getObjectFieldValue(Object obj,String fieldName) throws Exception{
		Map<String,Method> getMethodMap=RequestUtil.classGetMethodMap(obj.getClass());
		Method getMethod=getMethodMap.get(fieldName);
		Object value=getMethod.invoke(obj);
		return value;
	}
	
	public static Long getParamLong(HttpServletRequest request,String paramName,Long defaultValue){
		String value=request.getParameter(paramName);
		if(StringUtil.isBlank(value) || "null".equals(value)){
			return defaultValue;
		}else{
			return Long.parseLong(value);
		}
	}
	
	public static int getParamInt(HttpServletRequest request,String paramName,int defaultValue){
		String value=request.getParameter(paramName);
		if(StringUtil.isBlank(value) || "null".equals(value)){
			return defaultValue;
		}else{
			return Integer.parseInt(value);
		}
	}
	public static String getParamString(HttpServletRequest request,String paramName,String defaultValue){
		String value=request.getParameter(paramName);
		if(StringUtil.isBlank(value) || "null".equals(value)){
			return defaultValue;
		}else{
			return value;
		}
	}
	
	public static List<Long> getParamLongList(HttpServletRequest request,String paramName,List<Long> defaultValue){
		String[] values=request.getParameterValues(paramName);
		if(values==null || values.length==0){
			return defaultValue;
		}else{
			List<Long> list=new ArrayList<Long>();
			for(String value:values){
				String[] arr=value.split(",");
				for(int i=0;i<arr.length;i++){
					if(StringUtil.isNotBlank(arr[i])){
						list.add(Long.parseLong(arr[i]));
					}
				}
			}
			return list;
		}
	}
	
	public static List<Integer> getParamIntList(HttpServletRequest request,String paramName,List<Integer> defaultValue){
		String[] values=request.getParameterValues(paramName);
		if(values==null || values.length==0){
			return defaultValue;
		}else{
			List<Integer> list=new ArrayList<Integer>();
			for(String value:values){
				String[] arr=value.split(",");
				for(int i=0;i<arr.length;i++){
					if(StringUtil.isNotBlank(arr[i])){
						list.add(Integer.parseInt(arr[i]));
					}
				}
			}
			return list;
		}
	}
	
	public static Set<Long> getParamLongSet(HttpServletRequest request,String paramName,Set<Long> defaultValue){
		String[] values=request.getParameterValues(paramName);
		if(values==null || values.length==0){
			return defaultValue;
		}else{
			Set<Long> set=new HashSet<Long>();
			for(String value:values){
				String[] arr=value.split(",");
				for(int i=0;i<arr.length;i++){
					if(StringUtil.isNotBlank(arr[i])){
						set.add(Long.parseLong(arr[i]));
					}
				}
			}
			return set;
		}
	}
	
	public static String getParamDate(HttpServletRequest request,String paramName,String defaultValue){
		String time=request.getParameter(paramName);
		return DateUtil.getYmd(time,defaultValue);
	}
	
	public static String getParamDayStart(HttpServletRequest request,String paramName,String defaultValue){
		String time=request.getParameter(paramName);
		return DateUtil.getYmdHmsStrStart(time, defaultValue);
	}
	
	public static String getParamDayEnd(HttpServletRequest request,String paramName,String defaultValue){
		String time=request.getParameter(paramName);
		return DateUtil.getYmdHmsStrEnd(time, defaultValue);
	}
	
	public static Date getParamDateStart(HttpServletRequest request,String paramName,Date defaultValue){
		String time=request.getParameter(paramName);
		return DateUtil.getYmdHmsDateStart(time, defaultValue);
	}
	
	public static Date getParamDateEnd(HttpServletRequest request,String paramName,Date defaultValue){
		String time=request.getParameter(paramName);
		return DateUtil.getYmdHmsDateEnd(time, defaultValue);
	}
	
	public static Boolean getParamBoolean(HttpServletRequest request,String paramName,Boolean defaultValue){
		String value=request.getParameter(paramName);
		if(StringUtil.isBlank(value)){
			return defaultValue;
		}else{
			value=value.toLowerCase().trim();
			if("true".equalsIgnoreCase(value)){
				return true;
			}else if("false".equalsIgnoreCase(value) || "0".equals(value)){
				return false;
			}else{
				return true;
			}
		}
	}
	
	public static Map<String,Object> convertObject2Map(Object obj,String ... excludeFiled) throws Exception{
		Map<String,Object> retMap=new HashMap<String,Object>();
		Map<String,Method> getMethodMap=classGetMethodMap(obj.getClass());
		for(Field f:obj.getClass().getDeclaredFields()){
			if(excludeFiled!=null && excludeFiled.length>0){
				if(StringArraysUtil.indexOf(excludeFiled, f.getName())>=0){
					continue;
				}
			}
			Method method=getMethodMap.get(f.getName());
			Object value=method.invoke(obj);
			retMap.put(f.getName(),value);
		}
		return retMap;
	}
	
	public static void main(String[] args) throws Exception{
//		EquipBrand brand1=new EquipBrand();
//		brand1.setOid(1L);
//		brand1.setBrandName("brandName");
//		brand1.setAddUserId(1L);
//		brand1.setOptUserId(1L);
//		brand1.setAddUserNick("超级");
//		brand1.setOptUserNick("超级");
//		brand1.setAddDateTime(new Date());
//		brand1.setOptDateTime(new Date());
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("brandName","华为");
		map.put("oid","2");
		map.put("optDateTime",new Date());
	}
}
