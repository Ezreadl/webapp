package com.qm.nms.domain;

import java.io.File;
import java.io.IOException;

public class FileTest {
	private static String path;
	public static void main(String[] args) throws IOException {
		File fl = new File("D://111");
		getFile(fl);
	}
	public static String getFile(File fl){
		if(fl.isFile()){
			path = fl.getAbsolutePath().replace("2018", "4_2018");
			fl.renameTo(new File(path));
			System.out.println(fl.getAbsolutePath().replace("2018", "4_2018"));
			return path;
		}
		File[] fls = fl.listFiles();
		for (File ff : fls) {
			path = getFile(ff);
		}
		return null;
	}
}
//if(ff.isFile()){
//File fn = new File(ff.getAbsolutePath().replace(".txt", ".log"));
//ff.renameTo(fn);	
//}
