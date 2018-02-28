package com.qm.sys.web;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.qm.core.base.GlobalCfg;
import com.qm.core.base.WebStatus;
import com.qm.core.util.CipherUtil;
import com.qm.sys.domain.SysUser;
import com.qm.sys.query.SysOptLogQuery;
import com.qm.sys.query.SysUserQuery;
import com.qm.sys.service.ISysOptLogService;
import com.qm.sys.service.ISysUserService;

/**
 * 
 * @author xiaoze
 * 2017年8月25日下午7:41:22
 */
@Controller
@RequestMapping("/Login")
public class LoginController {
	
	@Autowired
	private ISysUserService userService;
	
	@Autowired
	private ISysOptLogService sysOptLogService;
	
	private static Map<String,SysUser> loginUserMap = new LinkedHashMap<String,SysUser>();
	
	private static Map<Integer,HttpSession> loginSessionMap = new LinkedHashMap<Integer,HttpSession>();
	
	
	@RequestMapping("/loginValidate")
	@ResponseBody
	public Object loginValidate(String userName,String userPassword,
			String code,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String checkCode = (String) session.getAttribute(GlobalCfg.NOTECODE);
		if(!code.equals(checkCode)){
			return "{\"success\":\"false\"}";
		}
		userPassword = CipherUtil.baseEncrypt(userPassword);
		SysUser user = userService.findUser(userName,CipherUtil.md5(userPassword));
		session.setAttribute("LONGIN_USER_SESSION_KEY", user);
		if(user != null){
			userService.saveUserOptInfo(user,request);
			SysOptLogQuery optLogquery = new SysOptLogQuery();
			optLogquery.setOptType(GlobalCfg.SYS_OPTLOG_LOGIN);
			sysOptLogService.saveSysOptLog(user, optLogquery);
			loginUserMap.put(session.getId(),user);
			loginSessionMap.put(user.getOid(), session);
			return "{\"success\":\"true\"}";
		}
		return "{\"success\":\"false\"}"; 
	}
	
	/**
	 * 注销登录
	 * 
	 * @param session
	 * @return 返回登录页
	 */
	@RequestMapping("/invalidate")
	public String invalidate(HttpSession session) {
		loginUserMap.remove(session.getId());
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/loginUserCount")
	@ResponseBody
	public String loginUserCount(HttpSession session) throws Exception{
		SysUser countUser=loginUserMap.get(session.getId());
		if(countUser!=null){
			countUser.setResponseTime(System.currentTimeMillis());
		}
		removeNoResponseUser();
		return "{\"success\":\"true\",\"state\":1,\"data\":"+loginUserMap.size()+"}";
	}
	
	private void removeNoResponseUser(){
		try {
			synchronized(loginUserMap){
				Iterator<String> interator=loginUserMap.keySet().iterator();
				while(interator.hasNext()){
					String sessionId=interator.next();
					SysUser sysUser=loginUserMap.get(sessionId);
					//30分钟无响应去除
					if(sysUser!=null && (System.currentTimeMillis()-sysUser.getResponseTime())>(30*60*1000)){
						interator.remove();
						HttpSession session = loginSessionMap.get(sysUser.getOid());
						session.invalidate();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@RequestMapping("/changePassword")
	@ResponseBody
	public WebStatus changePassword(SysUserQuery query,
			@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY) SysUser sysUser){
		boolean flag = userService.changePassword(query,sysUser);
		if(flag){
			SysOptLogQuery optLogquery = new SysOptLogQuery();
			optLogquery.setOptType(GlobalCfg.SYS_OPTLOG_CHGPASSWORD);
			sysOptLogService.saveSysOptLog(sysUser, optLogquery);
			return new WebStatus().success();
		}else{
			return new WebStatus().failure();
		}
	}
}
