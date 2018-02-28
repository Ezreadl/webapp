package com.qm.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;
import java.text.DecimalFormat;

public class FileUtil {
	//创建目录
	public static File createDir(String dirName){
		File file=new File(dirName);
		if(!file.exists()){
			file.mkdirs();
		}
		return file;
	}
	//创建文件
	public static File createFile(String file){
		File retFile=new File(file);
		if(!retFile.exists()){
			retFile.getParentFile().mkdirs();
		}
		return retFile;
	}
	//创建文件
	public static File createFile(String path,String file){
		File retFile=new File(path,file);
		if(!retFile.exists()){
			retFile.getParentFile().mkdirs();
		}
		return retFile;
	}
	//获取去除文件格式的文件名称
	public static String getFileNameNoSuffix(String fileName){
		int index=fileName.lastIndexOf(".");
		if(index>=0){
			return fileName.substring(0,index);
		}else{
			return fileName;
		}
	}
	//获取文件类型
	public static String getFileType(String fileName){
		if(fileName!=null){
			int index=fileName.lastIndexOf(".");
			if(index>0){
				return fileName.substring(index).toLowerCase();
			}
		}
		return "";
	}
	//修改文件类型名称
	public static String modifyFileType(String srcFileName,String toFileType){
		if(srcFileName!=null){
			int index=srcFileName.lastIndexOf(".");
			if(index>0){
				return srcFileName.substring(0,index)+toFileType.toLowerCase();
			}else{
				return srcFileName+toFileType.toLowerCase();
			}
		}
		return "";
	}
	
	//计算文件大小
	public static String getFileSize(long length){
		String[] sizeArr={"B","KB","MB","GB","TB"};
		int index=0;
		double size=length;
		while(size>=1024){
			size=size/1024;
			index++;
		}
		DecimalFormat df=new DecimalFormat("##.##");
		return df.format(size)+sizeArr[index];
	}
	//删除文件或目录
	public static void deleteFile(File file){
		if(!file.exists()){
			return;
		}
		if(file.isDirectory()){
			File[] files=file.listFiles();
			for(File f:files){
				deleteFile(f);
			}
			if(file.list().length<=0){
				file.delete();
			}
		}else if(file.isFile()){
			file.delete();
		}
	}
	//为文件或目录授权
	public static void dealPermission(String path,String auth){
		try {
			Runtime r=Runtime.getRuntime();
			Process p=r.exec("chmod "+auth+" "+path);
			InputStream in=p.getInputStream();
			byte[] b=new byte[100];
			int len=in.read(b);
			while(len>0){
				//System.out.print(new String(b,0,len));
				len=in.read(b);
			}
			if(in!=null){
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//剪切文件
	public static boolean cutFile(File sourceFile,File targetFile){
		boolean b=copyFile(sourceFile,targetFile);
		if(b){
			deleteFile(sourceFile);
		}
		return b;
	}
	//拷贝文件
	public static boolean copyFile(File sourceFile,File targetFile){
		try {
			if(!targetFile.getParentFile().exists()){
				targetFile.mkdirs();
			}
			if(targetFile.exists()){
				targetFile.delete();
			}
			InputStream in=new FileInputStream(sourceFile);
			OutputStream out=new FileOutputStream(targetFile);
			byte[] b=new byte[1024];
			int len=in.read(b);
			while(len>-1){
				out.write(b,0, len);
				len=in.read(b);
			}
			in.close();
			out.flush();
			out.close();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//将文件内容读取后写入流中
	public static void writeFileToStream(File file,Writer out) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf8"));
		String line=br.readLine();
		while(line!=null){
			out.write(line+"\r\n");
			line=br.readLine();
		}
		br.close();
	}
	//自动判断文件编码格式(对于java程序写入的文件类型，部分不准确)
	public static String getCharSet(File file) throws IOException{
		InputStream inputStream = new FileInputStream(file);
		String code=getCharSet(inputStream);
		return code;
	}
	//自动判断输入流中的编码格式(不完全准确)
	public static String getCharSet(InputStream inputStream) throws IOException{
		String code = "GBK";
		try {
			int[] head = new int[4];
			for (int i = 0; i < 4; i++) {
				head[i] = inputStream.read();
			}
			if (head[0] == 0xef && head[1] == 0xbb && head[2] == 0xbf) {
				code = "UTF-8";
			} else if (head[0] == 0xfe && head[1] == 0xff) {
//			code = "utf-16/ucs2, little endian";
				code = "utf-16";
			} else if (head[0] == 0xff && head[1] == 0xfe) {
//			code = "utf-16/ucs2, big endian";
				code = "utf-16";
			} else if (head[0] == 0xff && head[1] == 0xfe && head[2] == 0x0
					&& head[3] == 0x0) {
//			code = "UTF-32/ucs4, little endian";
				code = "utf-16";
			} else if (head[0] == 0x0 && head[1] == 0x0 && head[2] == 0xfe
					&& head[3] == 0xff) {
//			code = "UTF-32/ucs4, big endian";
				code = "utf-32";
			}
		} catch (Exception e) {
			code="GBK";
			e.printStackTrace();
		}
		return code;
	}
	public static void main(String[] args) {
//		System.out.println(getFileNameNoSuffix("aaa.bbb.txt"));
		File file=new File("D:/dns_log/");
		File file2=new File("D:/DnsSrcBaK/dns_log2015-11-09");
		file.renameTo(file2);
		System.out.println("file2="+file2.exists());
//		File file=new File("C:/");
//		System.out.println(file.getTotalSpace()/(1024*1024*1024));
//		System.out.println(file.getUsableSpace()/(1024*1024*1024));
//		System.out.println(file.getFreeSpace()/(1024*1024*1024));
	}
}
