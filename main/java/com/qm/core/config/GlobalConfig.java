package com.qm.core.config;

public class GlobalConfig {
	public static String NOTECODE="LONGIN_NOTECODE_SESSION_KEY";
	//登录用户存放session的key建
	public static String LONGIN_USER_SESSION_KEY="LONGIN_USER_SESSION_KEY";
	//系统状态码
	public static int OPT_CODE_SUCESS=1;//操作成功
	public static int OPT_CODE_UNLOGIN=2;//未登录系统
	public static int OPT_CODE_EXCEPTION=3;//操作异常
	
	public static String USER_OPT_LOG_UPLOAD_JOBTASK="USER_OPT_LOG_UPLOAD_JOBTASK";//用户上传网元操作日志文件jobtask
	
	public static String EQUIPMENT_CHECK_ITEM = "EQUIPMENT_CHECK_ITEM";//设备参数核查和基本核查 检查规则说明
}
