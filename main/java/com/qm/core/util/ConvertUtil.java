package com.qm.core.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qm.core.base.GridListData;

/**
 * 类型转换工具，将给定对象转换为指定类型对象
 * 如：将String "true"转换为boolean true
 * @author wangchong
 *
 */

public class ConvertUtil {
	
	/**
	 * 验证是否为一个或以,分割的多个数字，如1,2,3
	 * @param number
	 * @return
	 */
	public static boolean isNumber(String number){
		if(StringUtil.isBlank(number)){
			return false;
		}else{
			return number.trim().matches("[\\d,]+");
		}
	}
	
	/**
	 * 将1,2,3格式的ids转换成List<Long>结构
	 * @param ids
	 * @return
	 */
	public static List<Long> convertStr2LongList(String ids){
		List<Long> idList=new ArrayList<Long>();
		if(StringUtil.isBlank(ids)){
			return idList;
		}
		String[] idsArr=ids.split(",");
		for(int i=0;i<idsArr.length;i++){
			idList.add(Long.parseLong(idsArr[i]));
		}
		return idList;
	}
	/**
	 * 将1,2,3格式的ids转换成List-Integer结构
	 * @param ids
	 * @return
	 */
	public static List<Integer> convertStr2IntegerList(String ids){
		List<Integer> idList=new ArrayList<Integer>();
		if(StringUtil.isBlank(ids)){
			return idList;
		}
		String[] idsArr=ids.split(",");
		for(int i=0;i<idsArr.length;i++){
			idList.add(Integer.parseInt(idsArr[i]));
		}
		return idList;
	}
	
	public static GridListData convertToComboData(List<?> list,String valueName) throws Exception{

		return convertToComboData(list,"oid",valueName);
	}
	
	//将对象列表，将oidName字段值转换为value,valueName字段值转换为text,形成combo需要数据
	public static GridListData convertToComboData(List<?> list,String oidName,String valueName) throws Exception{
		List<Map<String,Object>> retList=new ArrayList<Map<String,Object>>();
		for(Object t:list){
			Map<String,Object> map=new HashMap<String,Object>();
			Method oidMethod=t.getClass().getMethod("get"+StringUtil.toUpperFirst(oidName));
			Method valueMethod=t.getClass().getMethod("get"+StringUtil.toUpperFirst(valueName));
			map.put("value",oidMethod.invoke(t));
			map.put("text",valueMethod.invoke(t));
			retList.add(map);
		}
		return new GridListData(retList.size(),retList);
	}
	
	/**
	 * 将listMap转换成按keyArr的顺序的List<String[]>
	 * @param listMap
	 * @param keyArr
	 * @return
	 */
	public static List<String[]> convertListMap2ListStringArr(List<Map<String,Object>> listMap,String[] keyArr){
		List<String[]> listArr=new ArrayList<String[]>(1024);
		
		for(Map<String,Object> map:listMap){
			String[] valueArr=new String[keyArr.length];
			
			for(int i=0;i<keyArr.length;i++){
				
				Object value=map.get(keyArr[i]);
				if(value==null){
					valueArr[i]="";
				}else{
					if("delFlg".equalsIgnoreCase(keyArr[i])){
						valueArr[i]=value.toString().equals("1")?"删除":"正常";
					}else if("stateFlg".equalsIgnoreCase(keyArr[i])){
						valueArr[i]=value.toString().equals("1")?"停用":"启用";
					}else{
						valueArr[i]=value.toString();
					}
				}
			}
			listArr.add(valueArr);
		}
		return listArr;
	}
	
	
	/**
	 * 将obj对象转化成destType类型
	 * @author wangchong
	 *
	 */
    public static Object castFromObject(Object obj, Class<?> destType){
        if (obj == null){
            return null;
        }else if (obj.getClass().equals(destType)){
            return obj;
        }
        String typeName = obj.getClass().getCanonicalName();
        if (typeName.equals("java.lang.String")){
            return castFromString((String)obj, destType);
        }
        else if (typeName.equals("boolean") || typeName.equals("java.lang.Boolean")){
            return castFromString(Boolean.toString((Boolean)obj), destType);
        }
        else if (typeName.equals("int") || typeName.equals("java.lang.Integer")){
            return castFromString(Integer.toString((Integer)obj), destType);
        }
        else if (typeName.equals("short") || typeName.equals("java.lang.Short")){
            return castFromString(Short.toString((Short)obj), destType);
        }
        else if (typeName.equals("long") || typeName.equals("java.lang.Long")){
            return castFromString(Long.toString((Long)obj), destType);
        }
        else if (typeName.equals("double") || typeName.equals("java.lang.Double")){
            return castFromString(Double.toString((Double)obj), destType);
        }
        else if (typeName.equals("float") || typeName.equals("java.lang.Float")){
            return castFromString(Float.toString((Float)obj), destType);
        }
        else if (typeName.equals("java.math.BigInteger")){
            return castFromString(((java.math.BigInteger)obj).toString(), destType);
        }
        else if (typeName.equals("java.math.BigDecimal")){
            return castFromString(((java.math.BigDecimal)obj).toString(), destType);
        }
        else if (typeName.equals("java.sql.Date")){
            return castFromString(((java.sql.Date)obj).toString(), destType);
        }
        else if (typeName.equals("java.sql.Time")){
            return castFromString(((java.sql.Time)obj).toString(), destType);
        }
        else if (typeName.equals("java.sql.Timestamp")){
            return castFromString(((java.sql.Timestamp)obj).toString(), destType);
        }
        else{
            return obj;
        }
    }
    
    /**
     * 把字符串转换成指定的数据类型
     * @param val 字符串
     * @param destType 目标数据类型
     * @return 转换后的数值
     */
    private static Object castFromString(String val, Class<?> destType){
        String typeName = destType.getCanonicalName();
        if (typeName.equals("java.lang.String")){
            return val;
        }
        
        if (val.equals("") || val.equals("null") || val.equals("undefined")){
            return null;
        }
        else if (typeName.equals("boolean") || typeName.equals("java.lang.Boolean")){
            return Boolean.parseBoolean(val);
        }
        else if (typeName.equals("int") || typeName.equals("java.lang.Integer")){
            return Integer.parseInt(val);
        }
        else if (typeName.equals("short") || typeName.equals("java.lang.Short")){
            return Short.parseShort(val);
        }
        else if (typeName.equals("long") || typeName.equals("java.lang.Long")){
            return Long.parseLong(val);
        }
        else if (typeName.equals("double") || typeName.equals("java.lang.Double")){
            return Double.parseDouble(val);
        }
        else if (typeName.equals("float") || typeName.equals("java.lang.Float")){
            return Float.parseFloat(val);
        }
        else if (typeName.equals("java.math.BigInteger")){
            return new java.math.BigInteger(val);
        }
        else if (typeName.equals("java.math.BigDecimal")){
            return new java.math.BigDecimal(val);
        }
        else if (typeName.equals("java.sql.Date")){
            return java.sql.Date.valueOf(val.substring(0, 10)); //yyyy-mm-dd
        }
        else if (typeName.equals("java.sql.Time")){
            return java.sql.Time.valueOf(val);
        }
        else if (typeName.equals("java.sql.Timestamp")){
                if (val.trim().length() == 10) {
                        return java.sql.Timestamp.valueOf(val.trim() + " 00:00:00");
                }
            return java.sql.Timestamp.valueOf(val);
        }
        return null;
    }
}
