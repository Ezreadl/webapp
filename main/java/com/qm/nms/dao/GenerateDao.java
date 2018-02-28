package com.qm.nms.dao;

import java.lang.reflect.Field;

import com.qm.nms.service.impl.ApptRcdServiceImpl;

public class GenerateDao {
	public static void main(String[] args) throws SecurityException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		ApptRcdServiceImpl ari = new ApptRcdServiceImpl();
		String path = "com.qm.nms.domain.Schedule";
		Field[] fds = Class.forName(path).getDeclaredFields();
		System.out.println(fds.length);
		String obj = path.split("domain.")[1];
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<fds.length;i++)
		  {
		   if(fds[i].toString().contains("private")){
			   System.out.println(fds[i].toString().substring(fds[i].toString().lastIndexOf(".")+1));
			   String ans = " #& par."+fds[i].toString().substring(fds[i].toString().lastIndexOf(".")+1) +"=:"+fds[i].toString().substring(fds[i].toString().lastIndexOf(".")+1);
			   sb.append(ans);
		   }
		  }
//		System.out.println(sb.toString());
	}
}
