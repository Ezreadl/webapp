package com.qm.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringArraysUtil {
	
	public static boolean isAllNotBlank(String[] arr){
		if(arr==null){
			return false;
		}
		for(String s:arr){
			if(StringUtil.isBlank(s)){
				return false;
			}
		}
		return true;
	}
	
	public static boolean isAllBlank(String[] arr){
		if(arr==null){
			return true;
		}
		for(String s:arr){
			if(StringUtil.isNotBlank(s)){
				return false;
			}
		}
		return true;
	}
	
	public static boolean isAnyNotBlank(String[] arr){
		if(arr==null){
			return false;
		}
		for(String s:arr){
			if(StringUtil.isNotBlank(s)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isAnyBlank(String[] arr){
		if(arr==null){
			return true;
		}
		for(String s:arr){
			if(StringUtil.isBlank(s)){
				return true;
			}
		}
		return false;
	}
	
	public static <T> String join(T [] arr){
		return join(arr,null,",");
	}
	
	public static <T> String join(T [] arr,String split){
		return join(arr,null,split);
	}
	
	public static <T> String join(T [] arr,int[] indexArr,String split){
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
	
	public static int indexOfIgnoreCase(String[] arr,String value){
		return indexOfIgnoreCase(arr,value,0);
	}
	
	public static int indexOfIgnoreCase(String[] arr,String value,int startIndex){
		for(int i=startIndex;i<arr.length;i++){
			if(arr[i]!=null && arr[i].equalsIgnoreCase(value)){
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
	
	public static int lastIndexOfIgnoreCase(String[] arr,String value){
		return lastIndexOfIgnoreCase(arr,value,arr.length);
	}
	
	public static  int lastIndexOfIgnoreCase(String[] arr,String value,int endIndex){
		for(int i=endIndex;i>=0;i++){
			if(arr[i]!=null && arr[i].equalsIgnoreCase(value)){
				return i;
			}
		}
		return -1;
	}
	
	public static <T> int indexOfAny(T[] arr,T[] valueArr){
		return indexOfAny(arr,valueArr,0);
	}
	
	public static int indexOfAnyIgnoreCase(String[] arr,String[] valueArr){
		return indexOfAnyIgnoreCase(arr,valueArr,0);
	}
	
	public static <T> int lastIndexOfAny(T[] arr,T[] valueArr){
		return lastIndexOfAny(arr,valueArr,arr.length-1);
	}
	
	public static int lastIndexOfAnyIgnoreCase(String[] arr,String[] valueArr){
		return lastIndexOfAnyIgnoreCase(arr,valueArr,arr.length-1);
	}
	
	public static <T> T[] mergeArrays(T[] arr1,T[] arr2){
		List<T> list=new ArrayList<T>(arr1.length+arr2.length);
		for(T a1:arr1){
			list.add(a1);
		}
		for(T a2:arr2){
			list.add(a2);
		}
		return list.toArray(arr1);
	}
	
	public static <T> T[] mergeArrays(T[] arr1,T[] arr2,T[] arr3){
		List<T> list=new ArrayList<T>(arr1.length+arr2.length);
		for(T a1:arr1){
			list.add(a1);
		}
		for(T a2:arr2){
			list.add(a2);
		}
		for(T a3:arr3){
			list.add(a3);
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
	
	public static <T> int indexOfAnyIgnoreCase(String[] arr,String[] valueArr,int startIndex){
		for(int i=startIndex;i<arr.length;i++){
			for(int j=0;j<valueArr.length;j++){
				if(arr[i]!=null && arr[i].equalsIgnoreCase(valueArr[j])){
					return i;
				}
			}
		}
		return -1;
	}
	
	public static boolean containsOfAll(String[] dataArr,String[] valueArr,boolean regExp){
		return containsOfAll(dataArr, valueArr, 0, regExp,false);
	}
	
	public static boolean containsOfAll(String[] dataArr,String[] valueArr,int startIndex,boolean regExp,boolean sensitive){
		if(dataArr==null || valueArr==null || dataArr.length<=0 || valueArr.length<=0){
			return false;
		}
		if(!sensitive && !regExp){
			for(int i=0;i<dataArr.length;i++){
				dataArr[i]=dataArr[i].toLowerCase();
			}
			for(int i=0;i<valueArr.length;i++){
				valueArr[i]=valueArr[i].toLowerCase();
			}
		}
		boolean findAll=true;
		for(String value:valueArr){
			Pattern p=null;
			if(regExp){
				if(!sensitive){
					p=Pattern.compile(value,Pattern.CASE_INSENSITIVE);
				}else{
					p=Pattern.compile(value);
				}
			}
			boolean find=false;
			for(int i=startIndex;i<dataArr.length;i++){
				if(regExp){
					Matcher m=p.matcher(dataArr[i]);
					find=m.find();
				}else{
					find=dataArr[i].indexOf(value)>=0;
				}
				if(find){
					break;
				}
			}
			if(!find){
				findAll=false;
				break;
			}
		}
		return findAll;
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
	public static  int lastIndexOfAnyIgnoreCase(String[] arr,String[] valueArr,int endIndex){
		for(int i=endIndex;i>=0;i--){
			for(int j=0;j<valueArr.length;j++){
				if(arr[i]!=null && arr[i].equalsIgnoreCase(valueArr[j])){
					return i;
				}
			}
		}
		return -1;
	}
	
	public static void toAllLowerCase(String[] arr){
		for(int i=0;i<arr.length;i++){
			arr[i]=arr[i].toLowerCase();
		}
	}
	public static void toAllLowerCaseTrim(String[] arr){
		for(int i=0;i<arr.length;i++){
			arr[i]=arr[i].trim().replaceAll("^\\xa0+|\\xa0+$","").trim().toLowerCase();
		}
	}
	public static void toAllUpperCase(String[] arr){
		for(int i=0;i<arr.length;i++){
			arr[i]=arr[i].toUpperCase();
		}
	}
	public static void toAllUpperCaseTrim(String[] arr){
		for(int i=0;i<arr.length;i++){
			arr[i]=arr[i].trim().toUpperCase();
		}
	}
	public static String[] toNewAllLowerCase(String[] arr){
		String[] retArr=new String[arr.length];
		for(int i=0;i<arr.length;i++){
			retArr[i]=arr[i].toLowerCase();
		}
		return retArr;
	}
	public static String[] toNewAllLowerCaseTrim(String[] arr){
		String[] retArr=new String[arr.length];
		for(int i=0;i<arr.length;i++){
			retArr[i]=arr[i].trim().toLowerCase();
		}
		return retArr;
	}
	public static String[] toNewAllUpperCase(String[] arr){
		String[] retArr=new String[arr.length];
		for(int i=0;i<arr.length;i++){
			retArr[i]=arr[i].toUpperCase();
		}
		return retArr;
	}
	public static String[] toNewAllUpperCaseTrim(String[] arr){
		String[] retArr=new String[arr.length];
		for(int i=0;i<arr.length;i++){
			retArr[i]=arr[i].trim().toUpperCase();
		}
		return retArr;
	}
}
