package com.qm.core.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class DataListUtil {
	public static List<Map<String,Object>> formatDateList(List<Map<String,Object>> valueList,String[] dateNameArr) throws Exception{
		return formatDateList(valueList,dateNameArr,null);
	}
	//格式化日期时间和耗时
	public static List<Map<String,Object>> formatDateList(List<Map<String,Object>> valueList,String[] dateNameArr,String[] useTimeArr) throws Exception{
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(Map<String,Object> valueMap:valueList){
			for(String dateName:dateNameArr){
				Object dateValue=valueMap.get(dateName);
				if(dateValue!=null){
					String date=dateFormat.format(dateValue);
					valueMap.put(dateName,date);
				}
			}
			if(useTimeArr!=null){
				for(String useTimeName:useTimeArr){
					Object useTimeValue=valueMap.get(useTimeName);
					if(useTimeValue!=null){
						long useTime=Long.parseLong(useTimeValue.toString());
						String date=DateUtil.getTimeDiff(useTime);
						valueMap.put(useTimeName,date);
					}
				}
			}
		}
		return valueList;
	}
	//将listMap转换成按keyArr的顺序的List<String[]>
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
	//截短数据
	public static List<Map<String,Object>> formatTruncateList(List<Map<String,Object>> valueList,String[] fieldNameArr,int length) throws Exception{
		for(Map<String,Object> valueMap:valueList){
			for(String name:fieldNameArr){
				String value=(String)valueMap.get(name);
				if(value!=null){
					int end=Math.min(value.length(),length);
					value=value.substring(0,end);
					if(end<value.length()){
						value=value+"...";
					}
					valueMap.put(name,value);
				}
			}
		}
		return valueList;
	}
	//对list按sort进行dir排序,如果需要对结果进行限制，则限制结果
	public static List<Map<String,Object>> orderLimitList(List<Map<String,Object>> list,String sort,String dir,int start,int limit){
		//排序
		if(StringUtil.isNotBlank(sort)){
			Collections.sort(list,createMapComparator(sort));
			//倒序
			if("desc".equalsIgnoreCase(dir)){
				Collections.reverse(list);
			}
		}
		//限制长度
		if(start==0 && limit==list.size()){
			return list;
		}else if(limit>0 && start>=0 && limit+start<=list.size()){
			return list.subList(start,limit+start);
		}else{
			return new ArrayList<Map<String,Object>>();
		}
	}
	
	private static Comparator<Map<String,Object>> createMapComparator(final String columnName){
		Comparator<Map<String,Object>> c= new Comparator<Map<String,Object>>() {
			@Override
			public int compare(Map<String, Object> o1,Map<String, Object> o2) {
				Object v1=o1.get(columnName);
				Object v2=o2.get(columnName);
				return CompareUtil.compareObject(v1,v2);
			}
		};
		return c;
	}
	
}
