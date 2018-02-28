package com.qm.core.util;

import java.util.Collection;

import org.apache.commons.mail.HtmlEmail;

public class EmailUtil {
	
	public static void sendEmail(String emailAddress,String subject,String emailContent) throws Exception{
		if(emailAddress==null || "".equals(emailAddress)){
			return ;
		}
		HtmlEmail email = new HtmlEmail();
		//smtp host 
		email.setHostName("123.125.50.111");
		//登陆邮件服务器的用户名和密码
		email.setAuthentication("scgprs@126.com","!QAZ2wsx");
		//接收人
		for(String address:emailAddress.split("[,，;；]+")){
			if(address!=null && !"".equals(address)){
				email.addTo(address,address);
			}
		}
		//发送人
		email.setFrom("scgprs@126.com", "设备配置核查");
		email.setCharset("UTF-8");  
		email.buildMimeMessage(); 
		//标题
		email.setSubject(subject);
		//邮件内容
		email.setHtmlMsg(emailContent);
		//发送
		email.send();
	}
	
	public static void sendEmail(Collection<String> emailAddressList,String subject,String emailContent) throws Exception{
		if(emailAddressList==null || emailAddressList.size()<=0){
			return ;
		}
		HtmlEmail email = new HtmlEmail();
		//smtp host 
		email.setHostName("123.125.50.111");
		//登陆邮件服务器的用户名和密码
		email.setAuthentication("scgprs@126.com","!QAZ2wsx");
		//接收人
		for(String address:emailAddressList){
			if(address!=null && !"".equals(address)){
				email.addTo(address,address);
			}
		}
		//发送人
		email.setFrom("scgprs@126.com", "设备配置核查");
		email.setCharset("UTF-8");  
		email.buildMimeMessage(); 
		//标题
		email.setSubject(subject);
		//邮件内容
		email.setHtmlMsg(emailContent);
		//发送
		email.send();
	}
	
	public static void main(String[] args) throws Exception{
		sendEmail("wangchong_86@126.com","测试","这是测试邮件");
	}
}
