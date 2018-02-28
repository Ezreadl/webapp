package com.qm.core.base;

public class GlobalCfg {
	//登录验证码session的key键
	public static String NOTECODE="LONGIN_NOTECODE_SESSION_KEY";
	//登录用户存放session的key键
	public final static String LONGIN_USER_SESSION_KEY="LONGIN_USER_SESSION_KEY";
	//系统状态码
	public static int OPT_CODE_SUCESS=1;//操作成功
	public static int OPT_CODE_UNLOGIN=2;//未登录系统
	public static int OPT_CODE_EXCEPTION=3;//操作异常
	
	/**
	 * 用户登录
	 */
	public static int SYS_OPTLOG_LOGIN=1;
	/**
	 * 修改密码
	 */
	public static int SYS_OPTLOG_CHGPASSWORD=2;
	
}
