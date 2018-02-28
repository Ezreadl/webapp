package com.qm.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringUtil {
	
	public static boolean isBlank(String str){
		if(str==null || "".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isDouble(String s){
		if(s!=null){
			return s.matches("(\\d+\\.?\\d*)|(\\d*\\.?\\d+)");
		}else{
			return false;
		}
	}
	
	public static boolean isInteger(String s){
		if(s!=null){
			return s.matches("\\d+");
		}else{
			return false;
		}
	}
	
	public static boolean isNotBlank(String str){
		if(str!=null && !"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	public static String trim(String sourceStr,String trimStr){
		if(trimStr==null || trimStr.equals("")){
			return sourceStr;
		}
		sourceStr=trimLeft(sourceStr, trimStr);
		sourceStr=trimRight(sourceStr, trimStr);
		return sourceStr;
	}
	
	public static String trimLeft(String sourceStr,String trimStr){
		if(trimStr==null || trimStr.equals("")){
			return sourceStr;
		}
		while(sourceStr.startsWith(trimStr)){
			sourceStr=sourceStr.substring(trimStr.length());
		}
		return sourceStr;
	}
	public static String trimRight(String sourceStr,String trimStr){
		if(trimStr==null || trimStr.equals("")){
			return sourceStr;
		}
		while(sourceStr.endsWith(trimStr)){
			sourceStr=sourceStr.substring(0,sourceStr.length()-trimStr.length());
		}
		return sourceStr;
	}
	
	public static String getLPadding(String value,char defalut,int length){
		String ret=value;
		for(int i=0;i<length-value.length();i++){
			ret=defalut+ret;
		}
		return ret;
	}
	
	public static String getRPadding(String value,char defalut,int length){
		String ret=value;
		for(int i=0;i<length-value.length();i++){
			ret=ret+defalut;
		}
		return ret;
	}
	
	public static int getEndWidthCount(String sourceStr,String end){
		if(end==null || end.equals("")){
			return 0;
		}
		int count=0;
		while(sourceStr.endsWith(end)){
			sourceStr=sourceStr.substring(0,sourceStr.length()-end.length());
			count++;
		}
		return count;
	}
	
	public static int getStartWidthCount(String sourceStr,String start){
		if(start==null || start.equals("")){
			return 0;
		}
		int count=0;
		while(sourceStr.startsWith(start)){
			sourceStr=sourceStr.substring(start.length());
			count++;
		}
		return count;
	}
	
	public static boolean startWidthAny(String source,String[] subArr,boolean lowercase){
		String[] subTempArr=subArr;
		if(lowercase){
			source=source.toLowerCase();
			subTempArr=new String[subArr.length];
			for(int i=0;i<subArr.length;i++){
				subTempArr[i]=subArr[i]!=null?subArr[i].toLowerCase():subArr[i];
			}
		}
		for(String s:subTempArr){
			if(s!=null && source.startsWith(s)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean endWidthAny(String source,String[] subArr,boolean lowercase){
		String[] subTempArr=subArr;
		if(lowercase){
			source=source.toLowerCase();
			subTempArr=new String[subArr.length];
			for(int i=0;i<subArr.length;i++){
				subTempArr[i]=subArr[i]!=null?subArr[i].toLowerCase():subArr[i];
			}
		}
		for(String s:subTempArr){
			if(s!=null && source.endsWith(s)){
				return true;
			}
		}
		return false;
	}
	
	public static List<String> getSplitArgumentsList(String str){
		return getSplitArgumentsList(str,true);
	}
	
	/**
	 * 以传入参数字符串形式返回参数列表,removeQuote是否移除"号
	 * 如传入aa bb "cc  dd e" ff
	 * 返回[aa,bb,cc dd e,ff]
	 * @param str
	 * @return
	 */
	public static List<String> getSplitArgumentsList(String str,boolean removeQuote){
		List<String> retList=new ArrayList<String>();
		StringBuilder sb=new StringBuilder();
		boolean isPreSpace=false;
		boolean isQuoteStart=false;
		boolean isBegin=false;
		char[] charArr=str.toCharArray();
		for(int i=0;i<charArr.length;i++){
			char c=charArr[i];
			//去掉开头空格
			if(!isBegin && (c ==' ' || c =='\t')){
				continue;
			}else{
				isBegin=true;
			}
			if(c=='"'){
				isQuoteStart=! isQuoteStart;
				isPreSpace=false;
				if(!removeQuote){
					sb.append(c);
				}
			}else if(c==' ' || c=='\t'){
				if(isQuoteStart){
					sb.append(c);
				}else if(!isPreSpace){
					retList.add(sb.toString());
					sb.delete(0, sb.length());
					isPreSpace=true;
				}
			}else{
				sb.append(c);
				isPreSpace=false;
			}
		}
		if(sb.length()>0){
			retList.add(sb.toString());
		}
		return retList;
	}
	
	public static String fillRepeatString(String repeatStr,int repeatCount){
		String s=repeatStr;
		for(int i=0;i<repeatCount;i++){
			s=s+repeatStr;
		}
		return s;
	}
	
	//找到任意一个最小位置
	public static int firstIndexOfAny(String line,String[] subStr){
		int min=-1;
		for(int i=0;i<subStr.length;i++){
			if(subStr[i]!=null && !"".equals(subStr[i])){
				int index=line.indexOf(subStr[i]);
				if(index>=0){
					min=(min==-1?index:Math.min(index,min));
				}
			}
		}
		return min;
	}
	
	//从找到任意一个最小位置
	public static int firstIndexOfAny(String line,String[] subStr,int startIndex){
		if(line.length()>startIndex && startIndex>0){
			line=line.substring(startIndex);
		}
		int min=-1;
		for(int i=0;i<subStr.length;i++){
			if(subStr[i]!=null && !"".equals(subStr[i])){
				int index=line.indexOf(subStr[i]);
				if(index>=0){
					min=(min==-1?index:Math.min(index,min));
				}
			}
		}
		return min;
	}
	public static int lastIndexOfAny(String line,String[] subStr){
		int max=-1;
		for(int i=0;i<subStr.length;i++){
			if(subStr[i]!=null && !"".equals(subStr[i])){
				int index=line.lastIndexOf(subStr[i]);
				if(index>=0){
					max=(max==-1?index:Math.max(index,max));
				}
			}
		}
		return max;
	}
	
	public static int lastIndexOfAny(String line,String[] subStr,int startIndex){
		if(line.length()>startIndex && startIndex>0){
			line=line.substring(startIndex);
		}
		int max=-1;
		for(int i=0;i<subStr.length;i++){
			if(subStr[i]!=null && !"".equals(subStr[i])){
				int index=line.lastIndexOf(subStr[i]);
				if(index>=0){
					max=(max==-1?index:Math.max(index,max));
				}
			}
		}
		return max;
	}
	//从后往前找到任意一个返回位置
	public static int lastIndexOf(String line,String[] subStr){
		for(int i=0;i<subStr.length;i++){
			if(subStr[i]!=null && !"".equals(subStr[i])){
				int index=line.lastIndexOf(subStr[i]);
				if(index>=0){
					return index;
				}
			}
		}
		return -1;
	}
	
	//从startIndex开始，从后往前找到任意一个返回位置
	public static int lastIndexOf(String line,String[] subStr,int startIndex){
		if(line.length()>startIndex && startIndex>0){
			line=line.substring(startIndex);
		}
		for(int i=0;i<subStr.length;i++){
			if(subStr[i]!=null && !"".equals(subStr[i])){
				int index=line.lastIndexOf(subStr[i]);
				if(index>=0){
					return index;
				}
			}
		}
		return -1;
	}
	//找到任意一个返回位置
	public static int indexOf(String line,String[] subStr){
		for(int i=0;i<subStr.length;i++){
			if(subStr[i]!=null && !"".equals(subStr[i])){
				int index=line.indexOf(subStr[i]);
				if(index>=0){
					return index;
				}
			}
		}
		return -1;
	}
	
	//从startIndex开始，找到任意一个返回位置
	public static int indexOf(String line,String[] subStr,int startIndex){
		if(line.length()>startIndex && startIndex>0){
			line=line.substring(startIndex);
		}
		for(int i=0;i<subStr.length;i++){
			if(subStr[i]!=null && !"".equals(subStr[i])){
				int index=line.indexOf(subStr[i]);
				if(index>=0){
					return index;
				}
			}
		}
		return -1;
	}
	
	public static String findSubString(String line,String[] subStr,int startIndex){
		if(startIndex>0 && line.length()>startIndex){
			line=line.substring(startIndex);
		}
		for(int i=0;i<subStr.length;i++){
			if(subStr[i]!=null && !"".equals(subStr[i])){
				int index=line.indexOf(subStr[i]);
				if(index>=0){
					return subStr[i];
				}
			}
		}
		return null;
	}
	
	public static boolean equalsIgnoreCase(String obj1,String obj2){
		if(obj1==obj2){
			return true;
		}
		if(obj1!=null && obj1.equalsIgnoreCase(obj2)){
			return true;
		}
		return false;
	}
	public static boolean equals(Object obj1,Object obj2){
		if(obj1==obj2){
			return true;
		}
		if(obj1!=null && obj1.equals(obj2)){
			return true;
		}
		return false;
	}
	
	//对给定的字符串以encode编码方式按最大长度maxLength切割成多个字符串
	public static String[] splitEncodeLength(String str,String encode,int maxLength) throws Exception{
		if(str==null){
			return new String[0];
		}
		byte[] byteArr=str.getBytes(encode);
		if(maxLength<=0 || byteArr.length<=maxLength){
			return new String[]{str};
		}else{
			String s=str;
			List<String> list=new ArrayList<String>();
			while(s.getBytes(encode).length>maxLength){
				int index=Math.min(s.length()-1,maxLength);
				for(int i=index;i>0;i--){
					String sub=s.substring(0,i);
					if(sub.getBytes(encode).length<=maxLength){
						list.add(sub);
						s=s.substring(i);
						break;
					}
				}
			}
			if(s.length()>0){
				list.add(s);
			}
			return list.toArray(new String[0]);
		}
	}
	
	public static String convertHtmlSign(String str){
		str = str.replace(">", "&gt;");
		str = str.replace("<", "&lt;");
		str = str.replace(" ", " &nbsp;");
		str = str.replace("\"", "&quot;");
		str = str.replace("\'", "&#39;");
		str = str.replace("\r\n", "<br/> ");
		return str;
	}
	
	//用,划分，合并重复项
	public static String mergeRepeatSplit(String... items){
		Set<String> set=new HashSet<String>();
		if(items!=null){
			for(int i=0;i<items.length;i++){
				if(items[i]!=null){
					for(String s:items[i].split(",")){
						if(!"".equals(s.trim())){
							set.add(s);
						}
					}
				}
			}
		}
		StringBuilder sb=new StringBuilder();
		for(String s:set){
			sb.append(s+",");
		}
		sb.delete(sb.length()-1, sb.length());
		return sb.toString();
	}
	
	 /**
	  *  判断某个字符串是否存在于数组中
	  *  @param stringArray 原数组
	  *  @param source 查找的字符串
	  *  @return 是否找到
	  */
	 public static boolean contains(String[] stringArray, String source) {
	  // 转换为list
	  List<String> tempList = Arrays.asList(stringArray);
	  
	  // 利用list的包含方法,进行判断
	  if(tempList.contains(source))
	  {
	   return true;
	  } else {
	   return false;
	  }
	 } 
	
	 /**
	  * 删除结束标志
	  * @param src
	  * @return
	  */
	 public static String deleteReturn(String src)
	  {
	    if ((src == null) || (src.trim().length() == 0))
	      return null;

	    src = src.replaceAll("\r", "");
	    src = src.replaceAll("\n", "");
	    src = src.replaceAll("\t", "");
	    return src;
	  }
	 
	 /**
	  * 将第一字母转换为大写
	  */
	 public static String toUpperFirst(String str){
		 if(str!=null && str.length()>=1){
			 return str.substring(0,1).toUpperCase()+str.substring(1);
		 }
		 return str;
	 }
	 
	 /**
	  * 将第一个字母转换为小写
	  */
	 public static String toLowerFirst(String str){
		 if(str!=null && str.length()>=1){
			 return str.substring(0,1).toLowerCase()+str.substring(1);
		 }
		 return str;
	 }
	 
	 public static void main(String[] args) {
		 String[] hwACTCEquips = {"YIBGS09","YIBGS02","ZIYGS06","XICGS03","MESGS1","LUZGS03","GAZGS04","GAZGS03","PZHGS02","NEJGS22","MESGS02","YAAGS02","MESGS1"};
		 System.out.println(StringUtil.contains(hwACTCEquips, "MESGS1"));
	 }
}
