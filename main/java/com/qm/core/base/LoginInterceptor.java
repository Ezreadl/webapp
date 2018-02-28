package com.qm.core.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean flag = request.getSession().getAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) != null;
		if(!flag){
			// 设置状态码
			response.setStatus(HttpStatus.OK.value());
			//设置返回类型
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			//避免乱码
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=utf-8");
			//设置头信息
			response.setHeader("Cache-Control","no-cache,must-revalidate");
//			String loginUrl=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
			response.getWriter().write("{\"success\":\"true\",\"state\":"+ GlobalCfg.OPT_CODE_UNLOGIN
				+ ",\"msg\":\"您未登录系统或登录超时，请重新登录系统\"}");
		}
		return flag;
	}
}
