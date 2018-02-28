package com.qm.sys.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qm.core.base.WebStatus;
import com.qm.core.util.CipherUtil;
import com.qm.sys.domain.Test;
import com.qm.sys.service.ITestService;

@Controller
@RequestMapping("/Test")
public class TestController {

	@Autowired
	private ITestService testService;

	@RequestMapping(value = "/{name}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("name") String name, Model model)
		throws Exception {
		System.out.println(name);
		List<Object> loj = testService.findTestlist(name);
		if (name == null) {
			return "redirect:/Test/list";
		}
		if (loj == null) {
			return "forward:/Test/list";
		}
		model.addAttribute("", loj);
		return "detail";
	}
	@RequestMapping(value = "/{name}/details", method = RequestMethod.GET)
	@ResponseBody
	public WebStatus detail(@PathVariable("name") String name)
		throws Exception {
		Test test= testService.findTest(name);
		System.out.println("SIZE:::::::::::::::::"+test.getAnswer());
		return new WebStatus(test);
	}
	@RequestMapping(value = "/{name}/export", method = RequestMethod.POST, 
			produces ={"application/json;chrset=UTF-8" })
	@ResponseBody
	public Test exportTest(@PathVariable("name") String name) {
		Test test = null;
		try {
			test = testService.findTest(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return test;
	}
	@RequestMapping(value = "/exportTest",
			produces ={"application/json;chrset=UTF-8" })
	@ResponseBody
	public Test exportTests() {
		Test test = null;
		try {
			test = testService.findTest("name1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return test;
	}
	@RequestMapping(value = "/{name}/export", method = RequestMethod.POST)
	public Test exeCute(@PathVariable("name") String name,@CookieValue(value="ipAddr",required=false) String ipAddr) {
		Test test = null;
		try {
			test = testService.findTest(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return test;
	}
	public static void main(String[] args) {
		String pwd = CipherUtil.baseEncrypt("guest123");
		System.out.println(pwd);
		pwd = CipherUtil.md5(pwd);
//		pwd = CipherUtil.md5(pwd);
		System.out.println(pwd);
	}
}
