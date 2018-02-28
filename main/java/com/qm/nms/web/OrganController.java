package com.qm.nms.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.qm.core.base.GlobalCfg;
import com.qm.core.base.PageInfo;
import com.qm.core.base.WebStatus4P;
import com.qm.nms.domain.Organization;
import com.qm.nms.service.IOrganService;
import com.qm.sys.domain.SysUser;

@Controller
@RequestMapping("/organ")
public class OrganController {

	@Autowired
	public IOrganService organService;

	@RequestMapping("/findCls")
	@ResponseBody
	public Map<String,Object> findMyCls(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY)SysUser sysUser,PageInfo page) throws Exception {
		Map<String,Object> gld = new HashMap<>();
		gld = organService.findMyOrgan(sysUser.getOid(),6, page);
		return gld;
	}
	@RequestMapping("/findLib")
	@ResponseBody
	public Map<String,Object> findLib(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY)SysUser sysUser,PageInfo page) throws Exception {
		Map<String,Object> gld = new HashMap<>();
		gld = organService.findMyOrgan(sysUser.getOid(),4,page);
		return gld;
	}
	@RequestMapping("/findSchoolLib")
	@ResponseBody
	public WebStatus4P findSchoolLib(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY)SysUser sysUser,PageInfo page) throws Exception {
		List<Map<String,Object>> lmap = new ArrayList<Map<String,Object>>();
		List<Organization> lorg = organService.findLib(sysUser, 4, page);
		for (Organization org : lorg) {
			Map<String,Object> mp = new HashMap<String,Object>();
			mp.put(org.getOrganName(), org.getOid());
			lmap.add(mp);
		}
		return new WebStatus4P(lmap);
	}	
	//学校管理员,查看实验室
	@RequestMapping("/findMyLib")
	@ResponseBody
	public WebStatus4P findMyLib(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY)SysUser sysUser,PageInfo page) throws Exception {
		List<Map<String,Object>> gld = new ArrayList<Map<String,Object>>();
		gld = organService.findMyLib(sysUser,4,page);
		return new WebStatus4P(gld).success();
	}
	//学校管理员,查看自己实验室
	@RequestMapping("/findMyOrgan")
	@ResponseBody
	public WebStatus4P findMyOrgan(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY)SysUser sysUser,PageInfo page) throws Exception {
		List<Map<String, Object>> gld = new ArrayList<Map<String, Object>>();
		gld = organService.findByUser(sysUser,page);
		return new WebStatus4P(gld).success();
	}
	@RequestMapping("/findMyschool")
	@ResponseBody
	public WebStatus4P findSchool(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY)SysUser sysUser,PageInfo page) throws Exception {
		List<Organization> lorg= organService.findByUserAndType(sysUser.getOid(), 3,page);
		return new WebStatus4P(lorg);
	}
	@RequestMapping("/findSchool/{organid}")
	@ResponseBody
	public WebStatus4P findOrganByID(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY)SysUser sysUser,@PathVariable("oid") int oid,PageInfo page) throws Exception {
		List<Organization> lorg= organService.findByIDUserAndType(sysUser.getOid(), 3 ,oid,page);
		return new WebStatus4P(lorg);
	}
	@RequestMapping("/saveLab")
	@ResponseBody
	public WebStatus4P saveLib(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY)SysUser sysUser, Organization organ) throws Exception {
		try{
			organ.setTypeVal(4);
			organService.saveRegion(sysUser, organ);
			return new WebStatus4P().success();
		}catch(Exception e){
			return new WebStatus4P().failure();			
		}
	}	
	@RequestMapping("/saveSchool")
	@ResponseBody
	public WebStatus4P saveSchool(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY)SysUser sysUser, Organization organ) throws Exception {
		try{
			organ.setTypeVal(3);
			organService.saveRegion(sysUser, organ);
			return new WebStatus4P().success();
		}catch(Exception e){
			return new WebStatus4P().failure();
		}
	}
	@RequestMapping("/delete")
	@ResponseBody
	public WebStatus4P delSchool(@SessionAttribute(GlobalCfg.LONGIN_USER_SESSION_KEY)SysUser user, Organization organ) throws Exception {
		List<Integer> oidList = new ArrayList<Integer>();
		try{
			oidList.add(organ.getOid());
			organService.changeDelFlg(user, oidList, (-1)*(organ.getDelFlg()-1));
			return new WebStatus4P().success();
		}catch(Exception e){
			return new WebStatus4P().failure();			
		}
	}
	
	
}
