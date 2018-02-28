package com.qm.core.util;
import java.util.Properties;

public class Constant {
	public static final String SERVER_NAME = "http://www.s-findme.com/wx_mul/";
	public static final String WX_REQUEST_SCOPE_BASE = "snsapi_base";
	public static final String WX_REQUEST_SCOPE_USER_INFO = "snsapi_userinfo";
	public static final String REQUEST_SESSION_CURRENT_USER_TOKEN_NAME = "$_TOKEN";
	public static final String SESSION_BASE_TOKEN = "$_BASE_TOKEN";
	public static final String SESSION_CURRENT_USER_INFO_NAME = "$_WeChat_User";
	public static final String SESSION_LOGIN_USER = "$_LoginUser";
	public static final String SESSION_PUBLIC_NUMBER = "$_PublicNumber";
	public static final String REQUEST_SESSION_BASE_TOKEN = "$_BASE_TOKEN";
	public static final String SESSION_FRONT_REQUEST_URI = "$_FRONT_REQUEST_URI";
	public static final String LANGUAGE_LANG_TYPE_ZH_CN = "zh_CN";
	public static final String LANGUAGE_LANG_TYPE_ZH_TW = "zh_TW";
	public static final String LANGUAGE_LANG_TYPE_EN = "en";
	public static final String GRANT_TYPE_CLIENT_CREDENTIAL = "client_credential";
	public static final String CLIPS_TYPE_IMAGE = "image";
	public static final String CLIPS_TYPE_VIDEO = "video";
	public static final String CLIPS_TYPE_VOICE = "voice";
	public static final String CLIPS_TYPE_NEWS = "news";
	public static final int FUNCTION_TYPE_WITHOUT_FUNC = 0;
	public static final int FUNCTION_TYPE_CRYSTAL_SHOES = 1;
	public static final String FUNCTION_URL_CRYSTAL_SHOES = "/mobile/function/sjx";
	public static final String JS_API_TICKET = "jsApiTicket";
	public static final String DEFAULT_FILTER_PROCESSES_URL = "/register/autho2";
	public static final String DEFAULT_SERVER_REGISTER_URL = "/register/base";
	public static final String ERROR_VIEW_UNLOGIN = "/admin/login";
	public static final String ERROR_VIEW_BIZ = "/error/500.html";
	public static final String ERROR_VIEW_UNAUTHC = "/err/unauthorizated.html";
	public static final String ERROR_VIEW_404 = "/error/404.html";
	public static final String ERROR_VIEW_500 = "/error/500.html";
	private static Properties properties = new PropertiesLoader(new String[] { "classpath:/config.properties", "classpath:/role.properties" }).getProperties();

	public static String getPropertie(String propertiesKey) {
		return properties.getProperty(propertiesKey);
	}
}