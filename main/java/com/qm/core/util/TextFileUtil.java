package com.qm.core.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class TextFileUtil {
	/**
	 * 读取数据到List<String> 默认为utf8 编码
	 * @param fileUrl
	 * @return
	 * @throws Exception
	 */
	public static List<String> readData(String fileUrl) throws Exception{
		return readData(fileUrl,"utf8");
	}
	
	public static List<String> readData(File fileUrl) throws Exception{
		return readData(fileUrl,"utf8");
	}
	
	public static List<String> readData(String fileUrl,String charSet) throws Exception{
		return readData(new File(fileUrl),charSet);
	}
	
	public static <T> void writeToFile(File file,String data,String charSet) throws Exception{
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),charSet));
		bw.write(data);
		bw.newLine();
		bw.flush();
		bw.close();
	}
	
	public static <T> void writeToFile(File file,String[] titleArr,List<T[]> dataList) throws Exception{
		writeToFile(file, titleArr, dataList,null,"utf-8",false);
	}
	
	public static <T> void writeToFile(String fileUrl,String[] titleArr,List<T[]> dataList) throws Exception{
		writeToFile(new File(fileUrl),titleArr, dataList,null,"utf-8",false);
	}
	
	public static <T> void writeToFile(File file,String[] titleArr,List<T[]> dataList,int[] outputIndexArr) throws Exception{
		writeToFile(file,titleArr, dataList,outputIndexArr,"utf-8",false);
	}
	
	public static <T> void writeToFile(String fileUrl,String[] titleArr,List<T[]> dataList,int[] outputIndexArr) throws Exception{
		writeToFile(new File(fileUrl),titleArr, dataList,outputIndexArr,"utf-8",false);
	}
	
	public static <T> void writeToFileGBK(File file,List<T> dataList) throws Exception{
		writeToFile(file, null, dataList,"gbk",false);
	}
	public static <T> void writeToFile(File file,List<T> dataList) throws Exception{
		writeToFile(file, null, dataList,"utf8",false);
	}
	public static <T> void writeToFile(File file,String title,List<T> dataList) throws Exception{
		writeToFile(file, title, dataList,"utf8",false);
	}
	
	public static <T> void writeToFile(String fileUrl,Collection<T> dataList) throws Exception{
		writeToFile(new File(fileUrl), null, dataList,"utf8",false);
	}
	
	public static <T> void writeToFile(String fileUrl,String title,List<T> dataList) throws Exception{
		writeToFile(new File(fileUrl), title, dataList,"utf8",false);
	}
	
	public static <T> void writeToFile(File file,String data,String charSet,boolean append) throws Exception{
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,append),charSet));
		bw.write(data);
		bw.newLine();
		bw.close();
	}
	
	public static List<String> readData(File file,String charSet) throws Exception{
		return readData(file,charSet,true);
	}
	
	public static String readDataInString(File file,String charSet) throws Exception{
		StringBuilder sb=new StringBuilder(1024);
		List<InputStream> inStreamList=null;
		//读取压缩文件中的全部文件
		if(".zip".equalsIgnoreCase(FileUtil.getFileType(file.getName()))){
			inStreamList=ZipFileUtil.getZipFileInputStream(file);
		}else{
			inStreamList=new ArrayList<InputStream>();
			inStreamList.add(new FileInputStream(file));
		}
		for(InputStream inStream:inStreamList){
			BufferedReader br=new BufferedReader(new InputStreamReader(inStream,charSet));
			String line=br.readLine();
			while(line!=null){
				sb.append(line+"\r\n");
				line=br.readLine();
			}
			br.close();
		}
		return sb.toString();
	}
	
	public static List<String> readData(File file,String charSet,boolean trimSpace) throws Exception{
		return readData(file, charSet, trimSpace, 0, Integer.MAX_VALUE);
	}
	public static List<String> readData(File file,String charSet,boolean trimSpace,int startRow,int endRow) throws Exception{
		List<String> retList=new ArrayList<String>(1024);
		List<InputStream> inStreamList=null;
		//读取压缩文件中的全部文件
		if(".zip".equalsIgnoreCase(FileUtil.getFileType(file.getName()))){
			inStreamList=ZipFileUtil.getZipFileInputStream(file);
		}else{
			inStreamList=new ArrayList<InputStream>();
			inStreamList.add(new FileInputStream(file));
		}
		int rowNum=0;
		for(InputStream inStream:inStreamList){
			BufferedReader br=new BufferedReader(new InputStreamReader(inStream,charSet));
			String line=br.readLine();
			if(trimSpace){
				while(line!=null){
					String data=line.trim();
					if(! "".equals(data)){
						if(rowNum>endRow){
							break;
						}
						if(rowNum>=startRow ){
							retList.add(data);
						}
						rowNum++;
					}
					line=br.readLine();
				}
			}else{
				while(line!=null){
					if(rowNum>endRow){
						break;
					}
					if(rowNum>=startRow ){
						retList.add(line);
					}
					rowNum++;
					line=br.readLine();
				}
			}
			br.close();
		}
		return retList;
	}
	
	public static List<String> readCommandData(File file,String charSet,boolean trimSpace) throws Exception{
		return readCommandData(file, charSet, trimSpace,null,null);
	}
	//读取文件中指令，舍弃以//开头的注释行,start从0行开始
	public static List<String> readCommandData(File file,String charSet,boolean trimSpace,Integer start,Integer end) throws Exception{
		List<String> retList=new ArrayList<String>(1024);
		InputStream inStream=new FileInputStream(file);
		BufferedReader br=new BufferedReader(new InputStreamReader(inStream,charSet));
		String line=br.readLine();
		int rowNum=0;
		if(trimSpace){
			while(line!=null){
				String data=line.trim();
				if(! "".equals(data) && !data.startsWith("//")){
					if(end!=null && rowNum>end){
						break;
					}
					if(start==null || rowNum>=start){
						retList.add(data);
					}
					rowNum++;
//					retList.add(data);
				}
				line=br.readLine();
			}
		}else{
			while(line!=null){
				if(!line.trim().startsWith("//")){
					if(end!=null && rowNum>end){
						break;
					}
					if(start==null || rowNum>=start){
						retList.add(line);
					}
					rowNum++;
//					retList.add(line);
				}
				line=br.readLine();
			}
		}
		br.close();
		return retList;
	}
	
	public static <T> void writeToFile(File file,String title,Collection<T> dataList,String charSet,boolean append) throws Exception{
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,append),charSet));
		if(title!=null && !"".equals(title)){
			bw.write(title.toString());
			bw.newLine();
		}
		for(T data:dataList){
			bw.write(data+"");
			bw.newLine();
		}
		bw.close();
	}
	
	public static <T> void writeToFile(File file,String[] titleArr,List<T[]> dataList,int[] indexArr,String charSet,boolean append) throws Exception{
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,append),charSet));
		if(titleArr!=null && titleArr.length>0){
			String title=ArraysUtil.join(titleArr,',');
			bw.write(title);
			bw.newLine();
		}
		for(T[] dataArr:dataList){
			String data=ArraysUtil.join(dataArr,indexArr,',');
			bw.write(data);
			bw.newLine();
		}
		bw.close();
	}
}
