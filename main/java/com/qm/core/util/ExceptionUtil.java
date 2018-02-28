package com.qm.core.util;

public class ExceptionUtil {
	/**
	 * 获取异常的描述信息
	 * @param e
	 * @return
	 */
	public static String getExceptionDescription(Exception e){
		try {
			StackTraceElement[] arr=e.getStackTrace();
			StringBuilder sb=new StringBuilder();
			sb.append(e.getMessage()+"\r\n");
			for(StackTraceElement element:arr){
				sb.append(element.toString()+"\r\n");
			}
			return sb.toString();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return e.getMessage();
	}
}
