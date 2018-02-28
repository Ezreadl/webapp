package com.qm.core.util;

import java.util.ArrayList;
import java.util.List;


public class ArraysUtil {
	
	public static <T> String join(T [] arr){
		return join(arr,null,',');
	}
	
	public static <T> String join(T [] arr,char split){
		return join(arr,null,split);
	}
	
	public static <T> String join(T [] arr,int[] indexArr,char split){
		if(arr==null){
			return "";
		}
		StringBuilder sb=new StringBuilder();
		if(indexArr !=null){
			for(int i=0;i<indexArr.length;i++){
				int index=indexArr[i];
				if(index<arr.length){
					T value=arr[index];
					sb.append(value+""+split);
				}
			}
		}else{
			for(int i=0;i<arr.length;i++){
				sb.append(arr[i]+""+split);
			}
		}
		if(sb.length()>0){
			sb.delete(sb.length()-1,sb.length());
		}
		return sb.toString();
	}
	
	public static <T> int indexOf(T[] arr,T value){
		return indexOf(arr,value,0);
	}
	
	public static <T> int indexOf(T[] arr,T value,int startIndex){
		for(int i=startIndex;i<arr.length;i++){
			if(arr[i]!=null && arr[i].equals(value)){
				return i;
			}
		}
		return -1;
	}
	
	public static <T> int lastIndexOf(T[] arr,T value){
		return lastIndexOf(arr,value,arr.length);
	}
	
	public static <T> int lastIndexOf(T[] arr,T value,int endIndex){
		for(int i=endIndex;i>=0;i++){
			if(arr[i]!=null && arr[i].equals(value)){
				return i;
			}
		}
		return -1;
	}

	public static <T> int indexOfAny(T[] arr,T[] valueArr){
		return indexOfAny(arr,valueArr,0);
	}

	public static <T> int lastIndexOfAny(T[] arr,T[] valueArr){
		return lastIndexOfAny(arr,valueArr,arr.length-1);
	}

	public static <T> T[] mergeArrays(T[] arr1,T[]... arr2){
		List<T> list=new ArrayList<T>();
		for(T a1:arr1){
			list.add(a1);
		}
		for(int i=0;i<arr2.length;i++){
			if(arr2[i]!=null){
				for(T a2:arr2[i]){
					list.add(a2);
				}
			}
		}
		return list.toArray(arr1);
	}

	public static <T> int indexOfAny(T[] arr,T[] valueArr,int startIndex){
		for(int i=startIndex;i<arr.length;i++){
			for(int j=0;j<valueArr.length;j++){
				if(arr[i]!=null && arr[i].equals(valueArr[j])){
					return i;
				}
			}
		}
		return -1;
	}
	
	/**
	 * 从后往前找，满足valueArr任意值则返回arr位置
	 * @param arr
	 * @param valueArr
	 * @param endIndex
	 * @return
	 */
	public static <T> int lastIndexOfAny(T[] arr,T[] valueArr,int endIndex){
		for(int i=endIndex;i>=0;i--){
			for(int j=0;j<valueArr.length;j++){
				if(arr[i]!=null && arr[i].equals(valueArr[j])){
					return i;
				}
			}
		}
		return -1;
	}
	
}
