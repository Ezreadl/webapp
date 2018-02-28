package com.qm.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class  MapListUtil {
	private static Map<String,String> map = new HashMap<String, String>();
	static{
		map.put("netAreaName","netAreaId");
		map.put("brandName", "equipBrandId");
		map.put("styleName", "equipStyleId");
		map.put("typeName","equipTypeId");
	}
	public  static Map<String,String> groupFiledMap(){
		return map;
	}
	public static <K,V> void putInList(Map<K,List<V>> map,K key,V value){
		List<V> list=map.get(key);
		if(list==null){
			list=new ArrayList<V>();
			map.put(key, list);
		}
		list.add(value);
	}
	
	public static <K,V> void putInSet(Map<K,Set<V>> map,K key,V value){
		Set<V> set=map.get(key);
		if(set==null){
			set=new HashSet<V>();
			map.put(key, set);
		}
		set.add(value);
	}
	
	public static <K,V> void putInListNotRepeat(Map<K,List<V>> map,K key,V value){
		List<V> list=map.get(key);
		if(list==null){
			list=new ArrayList<V>();
			map.put(key, list);
		}
		if(! list.contains(value)){
			list.add(value);
		}
	}
	
	public static <K,V> List<V> getIfList(Map<K,List<V>> map,K key,List<V> defaultList){
		List<V> list=map.get(key);
		if(list==null || list.size()==0){
			return defaultList;
		}else{
			return list;
		}
	}
	
	//将List<Map<String,Object>>结构数据转换成Map<T,Map<String,Object>>结构数据
	@SuppressWarnings("unchecked")
	public static <K> Map<K,Map<String,Object>> convertList2Map(List<Map<String,Object>> list,String key,Class<K> clazz){
		Map<K,Map<String,Object>> resultMap=new HashMap<K,Map<String,Object>>();
		for(Map<String,Object> rowMap:list){
			K value=(K)ConvertUtil.castFromObject(rowMap.get(key),clazz);
			resultMap.put(value, rowMap);
		}
		return resultMap;
	}
	
	//将List<Map<String,Object>>结构数据转换成Map<T,Map<String,Object>>结构数据
	public static Map<String,Map<String,Object>> convertList2Map(List<Map<String,Object>> list,String[] keyArr){
		Map<String,Map<String,Object>> resultMap=new HashMap<String,Map<String,Object>>();
		for(Map<String,Object> rowMap:list){
			String value=null;
			for(int i=0;i<keyArr.length;i++){
				String t=rowMap.get(keyArr[i]).toString();
				if(i==0){
					value=t;
				}else if(i>=1){
					value=value+","+t;
				}
			}
			resultMap.put(value, rowMap);
		}
		return resultMap;
	}
	
	//将List<Map<String,Object>>结构数据转换成Map<Map<T,Map<String,Object>>>结构数据
	@SuppressWarnings("unchecked")
	public static <K,V,T> Map<K,Map<V,Map<String,Object>>> convertList2DoubleMap(List<Map<String,Object>> list,String outerKey,Class<K> outerClazz,String innerKey,Class<T> innerClazz){
		Map<K,Map<V,Map<String,Object>>> resultMap=new HashMap<K,Map<V,Map<String,Object>>>();
		for(Map<String,Object> rowMap:list){
			K outerV=(K)ConvertUtil.castFromObject(rowMap.get(outerKey),outerClazz);
			V innerV=(V)ConvertUtil.castFromObject(rowMap.get(innerKey),innerClazz);
			Map<V,Map<String,Object>> innerMap=resultMap.get(outerV);
			if(innerMap==null){
				innerMap=new HashMap<V,Map<String,Object>>();
				resultMap.put(outerV, innerMap);
			}
			innerMap.put(innerV,rowMap);
		}
		return resultMap;
	}
	
	public static void main(String[] args) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map1=new HashMap<String,Object>();
		map1.put("equipId",1);
		map1.put("itemId",11);
		map1.put("value","value1");
		Map<String,Object> map2=new HashMap<String,Object>();
		map2.put("equipId",2);
		map2.put("itemId",22);
		map2.put("value","value2");
		list.add(map1);
		list.add(map2);
		Map<Long,Map<Long,Map<String,Object>>> resultMap=MapListUtil.convertList2DoubleMap(list,"equipId",Long.class,"itemId",Long.class);
		System.out.println(resultMap);
	}
	
	public static Map<String,Object> mapCompare(Map<String,Object> map){
		Map<String,Object> treeMap = new TreeMap<String,Object>(map);
		List<Map.Entry<String, Object>> list = new ArrayList<Map.Entry<String, Object>>(treeMap.entrySet()); 
		Collections.sort(list, new Comparator<Map.Entry<String, Object>>() {  
		    @Override  
		    public int compare(Entry<String, Object> o1, Entry<String, Object> o2) {  
//		         return o2.getValue().compareTo(o1.getValue());
		    	return -1;
		    }  
		});  
		return map;
	}
}
