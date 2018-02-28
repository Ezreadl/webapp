package com.qm.core.base;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义全局异常处理
 *
 */
public class GlobalExceptionHandler implements HandlerExceptionResolver {
	
	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		ModelAndView mv = new ModelAndView();
		// 设置状态码
		response.setStatus(HttpStatus.OK.value());
		//设置返回类型
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		//避免乱码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		//设置头信息
		response.setHeader("Cache-Control","no-cache,must-revalidate");
		ex.printStackTrace();
		String exceptionName = (ex.getMessage() == null || "".equals(ex
				.getMessage().trim())) ? "系统处理错误" : ex.getMessage()
				.replaceAll("\"", "");
		logger.error("拦截到操作异常:" + request.getRequestURI(), ex);
		try{
			// 将异常拼装为json格式返回
			response.getWriter().write("{success:false,state:"+ GlobalCfg.OPT_CODE_EXCEPTION +",msg:\""+exceptionName+"\"}");
		}catch(IOException e){
			logger.error("与客户端通讯异常:"+ e.getMessage(), e);  
        }  
		return mv;
	}

}
