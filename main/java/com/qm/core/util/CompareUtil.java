package com.qm.core.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CompareUtil {
	
	//比较2个对象的大小
	public static int compareObject(Object o1,Object o2){
		if(o1==null){
			return -1;
		}
		if(o2==null){
			return 1;
		}
		if(o1 instanceof Number && o2 instanceof Number ){
			Double d1=Double.parseDouble(o1.toString());
			Double d2=Double.parseDouble(o2.toString());
			return d1.compareTo(d2);
		}else{
			return o1.toString().compareTo(o2.toString());
		}
	}
	
	public static  List<String[]> removeStringArrayRepeat(List<String[]> dataList,int[] compareIndexArr){
		Comparator<String[]> comparator=new StringArrayComparator(compareIndexArr);
		return removeArrayRepeat(dataList, comparator);
	}
	public static <T> List<T[]> removeArrayRepeat(List<T[]> dataList,Comparator<? super T[]> comparator){
		List<T[]> retList=new ArrayList<T[]>();
		Collections.sort(dataList,comparator);
		/*for(int i=0;i<dataList.size()-1;i++){
			if(comparator.compare(dataList.get(i),dataList.get(i+1))==0){
				dataList.remove(i);
				i--;
			}
		}*/
		for(T[] t:dataList){
			int index=Collections.binarySearch(retList,t,comparator);
			if(index<0){
				retList.add(t);
			}
		}
		return retList;
	}
	
	public static CompareResult<String> binaryStringCompare(List<String> list1,List<String> list2){
		Comparator<String> comparator=new StringComparator();
		return binaryCompare(list1,list2,comparator,true);
	}
	
	public static CompareResult<String> binaryStringCompare(List<String> list1,List<String> list2,boolean validData){
		Comparator<String> comparator=new StringComparator();
		return binaryCompare(list1,list2,comparator,validData);
	}
	
	public static CompareArrayResult<String> binaryStringArrayCompare(List<String[]> list1,List<String[]> list2){
		return binaryStringArrayCompare(list1, list2,true);
	}
	
	public static CompareArrayResult<String> binaryStringArrayCompare(List<String[]> list1,List<String[]> list2,boolean validData){
		if(list1==null){
			throw new RuntimeException("list1 is null");
		}
		if(list2==null){
			throw new RuntimeException("list2 is null");
		}
		if(validData){
			if(list1==null || list1.size()==0){
				throw new RuntimeException("list1 is no data");
			}
			if(list2==null || list2.size()==0){
				throw new RuntimeException("list2 is no data");
			}
		}
		int min=Math.min(list1.get(0).length,list2.get(0).length);
		int[] putArr=new int[min];
		for(int i=0;i<min;i++){
			putArr[i]=i;
		}
		Comparator<String[]> comparator=new StringArrayComparator(putArr);
		return binaryArrayCompare(list1,list2,comparator,putArr);
	}
	
	public static CompareArrayResult<String> binaryStringArrayCompare(List<String[]> list1,List<String[]> list2,int[] compareArr,int[] outputArr){
		Comparator<String[]> comparator=new StringArrayComparator(compareArr);
		return binaryArrayCompare(list1,list2,comparator,outputArr);
	}
	
	public static <T> CompareArrayResult<T> binaryArrayCompare(List<T[]> list1,List<T[]> list2,Comparator<? super T[]> comparator,int[] outputArr){
		if(list1==null || list1.size()==0){
			throw new RuntimeException("list1 is no data");
		}
		if(list2==null || list2.size()==0){
			throw new RuntimeException("list2 is no data");
		}
		if(list1.get(0).length!=list2.get(0).length){
			System.err.println("比较的数长度不一致["+list1.get(0).length+","+list2.get(0).length+"]");
		}
		//排序
		Collections.sort(list1,comparator);
		Collections.sort(list2,comparator);
		CompareArrayResult<T> result=new CompareArrayResult<T>(outputArr,comparator);
		result.setEquipSourceList(list1);
		result.setStandardSourceList(list2);
		//二进制比较
		//System.out.println("二进制比较2");
		for(T[] s1:list1){
			int index=Collections.binarySearch(list2,s1,comparator);
			if(index<0){
				result.addMore(s1);
			}
		}
		for(T[] s2:list2){
			int index=Collections.binarySearch(list1,s2,comparator);
			if(index<0){
				result.addLess(s2);
			}
		}
		/*//指针推移比较
		int i=0,j=0;
		while(i<list1.size() && j<list2.size()){
			T[] valueI=list1.get(i);
			T[] valueJ=list2.get(j);
			int comp=comparator.compare(valueI, valueJ);
			if(comp>0){//右移动
				result.addLess(valueJ);
				j++;
//				while(j<list2.size() && comparator.compare(valueJ,list2.get(j))==0){
				while(j<list2.size() && comparator.compare(valueI,list2.get(j))>0){
//					result.addLess(valueJ);
					result.addLess(list2.get(j));
					j++;
				}
			}else if(comp<0){//左移动
				result.addMore(valueI);
				i++;
//				while(i<list1.size() && comparator.compare(valueI,list1.get(i))==0){
				while(i<list1.size() && comparator.compare(list1.get(i),valueJ)<0){
//					result.addMore(valueI);
					result.addMore(list1.get(i));
					i++;
				}
			}else{
				int ii=i;
				int jj=j;
				i++;
				j++;
//				while(i<list1.size() && comparator.compare(valueI,list1.get(i))==0){
				while(i<list1.size() && comparator.compare(list1.get(i),valueJ)==0){
					i++;
				}
//				while(j<list2.size() && comparator.compare(valueJ,list2.get(j))==0){
				while(j<list2.size() && comparator.compare(valueI,list2.get(j))==0){
					j++;
				}
			}
		}
		//将未比较完的数据加入
		while(i<list1.size()){
			result.addMore(list1.get(i));
			i++;
		}
		while(j<list2.size()){
			result.addLess(list2.get(j));
			j++;
		}*/
		return result;
	}
	
	public static <T> CompareResult<T> binaryCompare(List<T> list1,List<T> list2,Comparator<? super T> comparator){
		return binaryCompare(list1,list2,comparator,true);
	}
	
	public static <T> CompareResult<T> binaryCompare(List<T> list1,List<T> list2,Comparator<? super T> comparator,boolean validData){
		if(list1==null){
			throw new RuntimeException("list1 is null");
		}
		if(list2==null){
			throw new RuntimeException("list2 is null");
		}
		if(validData){
			if(list1==null || list1.size()==0){
				throw new RuntimeException("list1 is no data");
			}
			if(list2==null || list2.size()==0){
				throw new RuntimeException("list2 is no data");
			}
		}
		//排序
		Collections.sort(list1,comparator);
		Collections.sort(list2,comparator);
		//System.out.println("二进制比较2");
		//二进制比较
		CompareResult<T> result=new CompareResult<T>();
		result.setEquipSourceList(list1);
		result.setStandardSourceList(list2);
		for(T s1:list1){
			int index=Collections.binarySearch(list2,s1,comparator);
			if(index<0){
				result.addMore(s1);
			}
		}
		for(T s2:list2){
			int index=Collections.binarySearch(list1,s2,comparator);
			if(index<0){
				result.addLess(s2);
			}
		}
		return result;
		/*int i=0,j=0;
		while(i<list1.size() && j<list2.size()){
			T valueI=list1.get(i);
			T valueJ=list2.get(j);
			int comp=comparator.compare(valueI, valueJ);
			if(comp>0){
				result.addLess(valueJ);
				j++;
				while(j<list2.size() && comparator.compare(valueJ,list2.get(j))==0){
					result.addLess(valueJ);
					j++;
				}
			}else if(comp<0){
				result.addMore(valueI);
				i++;
				while(i<list1.size() && comparator.compare(valueI,list1.get(i))==0){
					result.addMore(valueI);
					i++;
				}
			}else{
				i++;
				j++;
				while(i<list1.size() && comparator.compare(valueI,list1.get(i))==0){
					i++;
				}
				while(j<list2.size() && comparator.compare(valueJ,list2.get(j))==0){
					j++;
				}
			}
		}
		while(i<list1.size()){
			result.addMore(list1.get(i));
			i++;
		}
		while(j<list2.size()){
			result.addLess(list2.get(j));
			j++;
		}
		return result;*/
	}
	
	//双循环比较
	public static <T> CompareArrayResult<T> douleCycleCompare(List<T[]> list1,List<T[]> list2,Comparator<? super T[]> comparator,int[] outputArr){
		CompareArrayResult<T> result=new CompareArrayResult<T>(outputArr,comparator);
		//排序
		Collections.sort(list1,comparator);
		Collections.sort(list2,comparator);
		result.setEquipSourceList(list1);
		result.setStandardSourceList(list2);
		for(T[] arr1:list1){
			boolean find=false;
			for(T[] arr2:list2){
				if(comparator.compare(arr1, arr2)==0){
					find=true;
					break;
				}
			}
			if(!find){
				result.addMore(arr1);
			}
		}
		for(T[] arr2:list2){
			boolean find=false;
			for(T[] arr1:list1){
				if(comparator.compare(arr1, arr2)==0){
					find=true;
					break;
				}
			}
			if(!find){
				result.addLess(arr2);
			}
		}
		return result;
	}
	
	public static class  CompareArrayResult<T>{
		//多设结果
		private List<T[]> moreList=new ArrayList<T[]>(1024);
		//少设结果
		private List<T[]> lessList=new ArrayList<T[]>(1024);
		
		private List<T[]> noRepeatOrderMoreList=null;
		
		private List<T[]> noRepeatOrderLessList=null;
		
		private List<T[]> standardSourceList=null;
		
		private List<T[]> equipSourceList=null;
		
		private int[] outputArr=null;
		
		private Comparator<? super T[]> comparator=null;
		
		public CompareArrayResult(){
			
		}
		
		public CompareArrayResult(int[] outputArr,Comparator<? super T[]> comparator){
			this.outputArr=outputArr;
			this.comparator=comparator;
		}
		
		public void addMore(T[] more){
			moreList.add(more);
		}
		
		public void addLess(T[] less){
			lessList.add(less);
		}
		
		public List<T[]> getMoreList() {
			return moreList;
		}
		
		public void setMoreList(List<T[]> moreList) {
			this.moreList = moreList;
		}
		public List<T[]> getLessList() {
			return lessList;
		}
		public void setLessList(List<T[]> lessList) {
			this.lessList = lessList;
		}
		
		public List<T[]> getStandardSourceList() {
			return standardSourceList;
		}

		public void setStandardSourceList(List<T[]> standardSourceList) {
			this.standardSourceList = standardSourceList;
		}

		public List<T[]> getEquipSourceList() {
			return equipSourceList;
		}

		public void setEquipSourceList(List<T[]> equipSourceList) {
			this.equipSourceList = equipSourceList;
		}

		public List<T[]> getNoRepeatOrderMoreList() {
			if(noRepeatOrderMoreList==null){
				noRepeatOrderMoreList=new ArrayList<T[]>();
				for(T[] t:moreList){
					int index=Collections.binarySearch(noRepeatOrderMoreList,t,comparator);
					if(index<0){
						noRepeatOrderMoreList.add(t);
					}
				}
			}
			return noRepeatOrderMoreList;
		}

		public List<T[]> getNoRepeatOrderLessList() {
			if(noRepeatOrderLessList==null){
				noRepeatOrderLessList=new ArrayList<T[]>();
				for(T[] t:lessList){
					int index=Collections.binarySearch(noRepeatOrderLessList,t,comparator);
					if(index<0){
						noRepeatOrderLessList.add(t);
					}
				}
			}
			return noRepeatOrderLessList;
		}

		public void writeToTile(String savePath,String name) throws Exception{
			File moreFile=FileUtil.createFile(savePath,name+"多设"+moreList.size()+".txt");
			File lessFile=FileUtil.createFile(savePath,name+"少设"+lessList.size()+".txt");
			TextFileUtil.writeToFile(moreFile,new String[]{name+"多设"+moreList.size()},moreList,outputArr,"utf-8",false);
			TextFileUtil.writeToFile(lessFile,new String[]{name+"少设"+lessList.size()},lessList,outputArr,"utf-8",false);
		}
		
		public void writeNoRepeatOrderResultToFile(String savePath,String name) throws Exception{
			File moreFile=FileUtil.createFile(savePath,name+"去重多设"+getNoRepeatOrderMoreList().size()+".txt");
			File lessFile=FileUtil.createFile(savePath,name+"去重少设"+getNoRepeatOrderLessList().size()+".txt");
			TextFileUtil.writeToFile(moreFile,new String[]{name+"去重多设"+getNoRepeatOrderMoreList().size()},getNoRepeatOrderMoreList(),outputArr,"utf-8",false);
			TextFileUtil.writeToFile(lessFile,new String[]{name+"去重少设"+getNoRepeatOrderLessList().size()},getNoRepeatOrderLessList(),outputArr,"utf-8",false);
		}
	}
	
	public static class  CompareResult<T>{
		//多设结果
		private List<T> moreList=new ArrayList<T>(1024);
		//少设结果
		private List<T> lessList=new ArrayList<T>(1024);
		
		private List<T> standardSourceList=null;
		
		private List<T> equipSourceList=null;
		
		private List<T> noRepeatOrderMoreList=null;
		
		private List<T> noRepeatOrderLessList=null;
		
		public void addMore(T more){
			moreList.add(more);
		}
		
		public void addLess(T less){
			lessList.add(less);
		}
		
		public List<T> getMoreList() {
			return moreList;
		}
		
		public void setMoreList(List<T> moreList) {
			this.moreList = moreList;
		}
		public List<T> getLessList() {
			return lessList;
		}
		public void setLessList(List<T> lessList) {
			this.lessList = lessList;
		}
		
		public List<T> getStandardSourceList() {
			return standardSourceList;
		}

		public void setStandardSourceList(List<T> standardSourceList) {
			this.standardSourceList = standardSourceList;
		}

		public List<T> getEquipSourceList() {
			return equipSourceList;
		}

		public void setEquipSourceList(List<T> equipSourceList) {
			this.equipSourceList = equipSourceList;
		}
		
		public List<T> getNoRepeatOrderMoreList() {
			if(noRepeatOrderMoreList==null){
				Set<T> orderSet=new TreeSet<T>();
				orderSet.addAll(moreList);
				noRepeatOrderMoreList=new ArrayList<T>();
				noRepeatOrderMoreList.addAll(orderSet);
			}
			return noRepeatOrderMoreList;
		}

		public List<T> getNoRepeatOrderLessList() {
			if(noRepeatOrderLessList==null){
				Set<T> orderSet=new TreeSet<T>();
				orderSet.addAll(lessList);
				noRepeatOrderLessList=new ArrayList<T>();
				noRepeatOrderLessList.addAll(orderSet);
			}
			return noRepeatOrderLessList;
		}

		public void writeToTile(String savePath,String name) throws Exception{
			File moreFile=FileUtil.createFile(savePath,name+"多设"+moreList.size()+".txt");
			File lessFile=FileUtil.createFile(savePath,name+"少设"+lessList.size()+".txt");
			if(moreList.size()>0){
				TextFileUtil.writeToFile(moreFile,name+"多设"+moreList.size(),moreList,"utf-8",false);
			}
			if(lessList.size()>0){
				TextFileUtil.writeToFile(lessFile,name+"少设"+lessList.size(),lessList,"utf-8",false);
			}
		}
		
		public void writeNoRepeatOrderResultToFile(String savePath,String name) throws Exception{
			File moreFile=FileUtil.createFile(savePath,name+"去重多设"+getNoRepeatOrderMoreList().size()+".txt");
			File lessFile=FileUtil.createFile(savePath,name+"去重少设"+getNoRepeatOrderLessList().size()+".txt");
			TextFileUtil.writeToFile(moreFile,name+"去重多设"+getNoRepeatOrderMoreList().size(),getNoRepeatOrderMoreList(),"utf-8",false);
			TextFileUtil.writeToFile(lessFile,name+"去重少设"+getNoRepeatOrderLessList().size(),getNoRepeatOrderLessList(),"utf-8",false);
		}
		
	}
	
	public static class StringArrayComparator implements Comparator<String[]>{
		
		private int[] compareArr=null;
		
		public StringArrayComparator(int[] compareArr){
			this.compareArr=compareArr;
		}
		
		@Override
		public int compare(String[] obj1, String[] obj2) {
			int min=Math.min(obj1.length,obj2.length);
			for(int i=0;i<compareArr.length;i++){
				int index=compareArr[i];
				if(index<min){
					int ret=obj1[index].compareTo(obj2[index]);
					if(ret!=0){
						return ret;
					}
				}else{//如果核查范围超过了单个数据的长度
					if(obj1.length>min){
						return 1;
					}else if(obj2.length>min){
						return -1;
					}
					//如果核查范围超过了两个比较的数据的长度，放弃该列核查
					
				}
			}
			return 0;
		}
	}
	
	public static class StringComparator implements Comparator<String>{
		
		@Override
		public int compare(String obj1, String obj2) {
			return obj1.compareTo(obj2);
		}
	}
	public static void main(String[] args) {
		System.out.println(CompareUtil.compareObject(1, 2));
		System.out.println(CompareUtil.compareObject(2.2, 4.3));
		System.out.println(CompareUtil.compareObject(5.2, 3.2));
		System.out.println(CompareUtil.compareObject(123L, 3L));
		System.out.println(CompareUtil.compareObject("aac", "bbc"));
		System.out.println(CompareUtil.compareObject("bbc", "aad"));
		System.out.println(CompareUtil.compareObject("aaa","12"));
		System.out.println(CompareUtil.compareObject("435", "ccc"));
		System.out.println(CompareUtil.compareObject("435", "234"));
		System.out.println(CompareUtil.compareObject("123", "324"));
		System.out.println(CompareUtil.compareObject("123", null));
		System.out.println(CompareUtil.compareObject(null, "324"));
	}
}
