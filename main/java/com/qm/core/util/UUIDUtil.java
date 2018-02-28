package com.qm.core.util;

import java.util.Date;
import java.util.UUID;

public class UUIDUtil {
	public static String getUUID(){
		try {
			String uuid=UUID.randomUUID().toString();
			return uuid.replaceAll("-", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "t"+new Date().getTime();
	}
	public static String getTimeId(){
		return ""+new Date().getTime();
	}
}
