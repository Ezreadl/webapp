package com.qm.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

public class ZipFileUtil {
	/**
	 * 压缩文件（指针对文件）
	 * @param file 被压缩文件
	 * @param zipFile 压缩后的文件
	 */
	public static void compressFile(File file, File zipFile) {
		ZipOutputStream out=null;
		InputStream in=null;
		try {
			out= new ZipOutputStream(new FileOutputStream(zipFile));
			in= new FileInputStream(file);
			ZipEntry entry = new ZipEntry(file.getName());
			out.putNextEntry(entry);
			byte[] b = new byte[1024];
			int len = in.read(b);
			while (len > 0) {
				out.write(b, 0, len);
				len = in.read(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			StreamUtil.close(in,out);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static InputStream getZipFileInputStream(String fileName,File zipFile) throws Exception{
		ZipFile zf = new ZipFile(zipFile);
		Enumeration zfs = zf.getEntries();
		while(zfs.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) zfs.nextElement();
			if(entry.getName().equals(fileName)){
				InputStream in = zf.getInputStream(entry);
				return in;
			}
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public static List<InputStream> getZipFileInputStream(File zipFile) throws Exception{
		List<InputStream> inList=new ArrayList<InputStream>();
		ZipFile zf = new ZipFile(zipFile);
		Enumeration zfs = zf.getEntries();
		while(zfs.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) zfs.nextElement();
			InputStream in = zf.getInputStream(entry);
			inList.add(in);
		}
		return inList;
	}
	@SuppressWarnings("rawtypes")
	public static String unCompressFile(File zipFile,File dir) {
		OutputStream out=null;
		InputStream in=null;
		String fileName="";
		try {
			if(!dir.exists()){
				dir.mkdirs();
			}
			ZipFile zf = new ZipFile(zipFile);
			//Enumeration zfs = zf.entries();
			Enumeration zfs = zf.getEntries();
			if (zfs.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) zfs.nextElement();
				fileName=entry.getName();
				out = new FileOutputStream(new File(dir,fileName));
				in = zf.getInputStream(entry);
				byte[] b = new byte[1024];
				int len = in.read(b);
				while (len > 0) {
					out.write(b, 0, len);
					len = in.read(b);
				}
			}
			zf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			StreamUtil.close(in,out);
		}
		return fileName;
	}

	public static void compressMutiFile(File[] srcfile,File zipfile) {
		String[] fileName=new String[srcfile.length];
		for(int i=0;i<srcfile.length;i++){
			fileName[i]=srcfile[i].getName();
		}
		compressMutiFile(srcfile,fileName, zipfile);
	}
	
	public static void compressMutiFile(File[] srcfile,String[] fileName,File zipfile) {
		byte[] buf = new byte[1024];
		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
			out.setEncoding("gbk");
			for (int i = 0; i < srcfile.length; i++) {
				if(! srcfile[i].exists()){
					continue;
				}
				FileInputStream in = new FileInputStream(srcfile[i]);
				String fname=fileName[i];
				out.putNextEntry(new ZipEntry(fname));
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();
			}
			out.close();
			System.out.println("压缩完成.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void compressMutiFile(String[] srcfileData,String[] fileName,File zipfile) {
		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
			out.setEncoding("gbk");
			for (int i = 0; i < srcfileData.length; i++) {
				String fname=fileName[i];
				out.putNextEntry(new ZipEntry(fname));
				out.write(srcfileData[i].getBytes("utf8"));
				out.closeEntry();
			}
			out.close();
			System.out.println("压缩完成.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	 public static void zipFiles(List<File> srcfile, File zipfile){    
	        byte[] buf = new byte[1024];    
	        try {    
	            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile)); 
	            out.setEncoding("gbk");
	            for (int i = 0; i < srcfile.size(); i++) {    
	                File file = srcfile.get(i);    
	                FileInputStream in = new FileInputStream(file);   
	                String fileName = file.getName();
	                if(file.getPath().indexOf("detail")>1){
	                	fileName = "detail/"+fileName;
	                }
	                out.putNextEntry(new ZipEntry(fileName));    
	                int len;    
	                while ((len = in.read(buf)) > 0) {    
	                    out.write(buf, 0, len);    
	                }    
	                out.closeEntry();    
	                in.close();    
	            }    
	            out.close();
	        } catch (IOException e) {    
	           e.printStackTrace();  
	        }    
	    } 
}
