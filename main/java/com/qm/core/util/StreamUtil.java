package com.qm.core.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;

public class StreamUtil {
	
	public static int transmit(InputStream in,OutputStream out) throws Exception{
		int count=0;
		byte[] buffer=new byte[1024];
		int len=in.read(buffer);
		while(len>=0){
			out.write(buffer,0,len);
			count=count+len;
			len=in.read(buffer);
		}
		return count;
	}
	
	public static int transmit(Reader in,Writer out) throws Exception{
		int count=0;
		char[] buffer=new char[1024];
		int len=in.read(buffer);
		while(len>=0){
			out.write(buffer,0,len);
			count=count+len;
			len=in.read(buffer);
		}
		return count;
	}
	
	public static void close(InputStream in,OutputStream out){
		close(in);
		close(out);
	}
	
	public static void close(Reader in,Writer out){
		close(in);
		close(out);
	}
	
	public static void close(InputStream[] inArr,OutputStream[] outArr){
		for(InputStream in:inArr){
			close(in);
		}
		for(OutputStream out:outArr){
			close(out);
		}
	}
	
	public static void close(InputStream in){
		try {
			if(in!=null){
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void close(OutputStream out){
		try {
			if(out!=null){
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void closeIn(Collection<InputStream> inList){
		if(inList!=null){
			for(InputStream in:inList){
				close(in);
			}
		}
	}
	
	public static void closeOut(Collection<OutputStream> outList){
		if(outList!=null){
			for(OutputStream out:outList){
				close(out);
			}
		}
	}
	
	public static void close(Reader in){
		try {
			if(in!=null){
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void close(Writer out){
		try {
			if(out!=null){
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void closeDbConnection(ResultSet rs,PreparedStatement pst,Connection connection){
		try {
			if(rs!=null && !rs.isClosed()){
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(pst!=null && !pst.isClosed()){
				pst.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(connection!=null && !connection.isClosed()){
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void closeDbConnection(ResultSet rs,Statement st,Connection connection){
		try {
			if(rs!=null && !rs.isClosed()){
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(st!=null && !st.isClosed()){
				st.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(connection!=null && !connection.isClosed()){
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
