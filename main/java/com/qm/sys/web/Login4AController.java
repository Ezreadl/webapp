package com.qm.sys.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 4A登录，票据认证接口
 * @author wangchong
 *
 */
@Controller
@RequestMapping("/Login4AAction")
public class Login4AController {
	
	@RequestMapping("!loginValidate.action")
	public String loginValidate(HttpServletRequest request){
		System.out.println("----------4A票据认证--------------");
//		try{
//			URL url = this.getClass().getClassLoader().getResource("casp_client_config.properties");
//			
//			System.out.println(url.toURI().getRawPath());
//			LoginUtil.getInstance().init(url.toURI().getRawPath());
//			
//			if (LoginUtil.getInstance().isEnable()) {
//				if (LoginUtil.getInstance().checkTicket(request)) {
//					String strTic = LoginUtil.getInstance().getTicket(request);
//					TransferTicket ticket = LoginUtil.getInstance().analysTicket(
//							strTic);
//					if (ticket != null && ticket.getRetCode() != null
//							&& ResultCode.RESULT_OK.equals(ticket.getRetCode())) {
//						UserInfo userInfo = LoginUtil.getInstance()
//								.qryUserByTicket(ticket);
//						if (!ResultCode.RESULT_OK.equals(userInfo.getRetCode())) {
//							// 跳转到错误页面，显示错误码；
//							request.setAttribute("errorMsg","<pre>验证错误：<br/>"+userInfo.getRetCode()+"</pre>");
//							return "LoginJump";							
//						} else {
//							// 应用资源根据帐号信息做登录后业务处理；
//							String accSlave=userInfo.getName();//登录用户账号							
//							List<SysUser> userList=userService.findSysUserByUserName(accSlave);
//							SysUser loginUser=userList.get(0);
//							session.setAttribute(GlobalConfig.LONGIN_USER_SESSION_KEY,loginUser);
//							ISysOperationLogDao logDao=(ISysOperationLogDao)SpringContextUtil.getBean("sysOperationLogDao");
//							SysOperationLog log=new SysOperationLog();
//							log.setIpAddress(request.getRemoteAddr());
//							log.setOptUserId(loginUser.getOid());
//							log.setOptUserNick(loginUser.getUserNick());
//							log.setOptTime(new Date());
//							log.setOptDescription("登录系统");
//							logDao.saveImmediately(log);
//							LoginAction.getLoginUserMap().put(session.getId(),loginUser.clone());
//							return "LoginJump";							
//						}
//					} else {
//						// 跳转到错误页面，显示错误码；
//						return "error";						
//					}
//				}
//				if (LoginUtil.getInstance().hasAliveServer()) {
//					LoginUtil.getInstance().redirectToServer(request, response);
//				} else {
//					// 使用应用资源本地认证；
//				}
//			} else {
//				// 使用应用资源本地认证；
//			}			
//		}catch(Exception e){
//			request.setAttribute("errorMsg","登录错误:"+ExceptionUtil.getExceptionDescription(e));
//			e.printStackTrace();
//			return "LoginJump";
//		}
		return "LoginJump";
	}

}
