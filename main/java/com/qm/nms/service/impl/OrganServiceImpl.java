package com.qm.nms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.core.util.CipherUtil;
import com.qm.core.util.DBConnectionPool;
import com.qm.core.util.DateUtil;
import com.qm.nms.dao.IOrganDao;
import com.qm.nms.dao.IVideoSiteDao;
import com.qm.nms.domain.Organization;
import com.qm.nms.domain.VideoSite;
import com.qm.nms.query.OrganizationQuery;
import com.qm.nms.service.IOrganService;
import com.qm.sys.domain.SysUser;

@Service
public class OrganServiceImpl implements IOrganService {
	String organId = "未录入组织ID";
	String organName = "未录入组织名称";
	String orgId = "未录入组织ID";
	String orgName = "未录入组织名称";
	@Autowired
	public IOrganDao organDao;
	@Autowired
	public IVideoSiteDao videoDao;

	public GridListData findOrgan(OrganizationQuery query, PageInfo pageInfo) throws Exception {
		return null;
	}

	public Organization findByID(int id) {
		Organization org = organDao.findByID(id);
		return org;
	}

	/**
	 * 学校领导导入
	 */
	@Transactional
	public void importLab(SysUser user, Organization organ) throws Exception {

	}

	public static void main(String[] args) {
		System.out.println(CipherUtil.baseEncrypt("menglinkeji"));
		System.out.println(CipherUtil.md5("menglinkeji"));
		System.out.println(CipherUtil.baseEncrypt(CipherUtil.baseEncrypt("#Jwh@!2#308")));

	}

	/**
	 * 添加组织
	 */
	@Transactional
	public void saveRegion(SysUser user, Organization organ) throws Exception {
		int oid = organ.getOid();
		Organization old = organDao.findOne(oid);
		if (null == old) {
			organ.setAddUserName(user.getUserName());
			organ.setAddDateTime(new Date());
			organDao.save(organ);
		} else {
			old.setOptDateTime(new Date());
			old.setOptUserName(user.getUserName());
			organDao.update(old);
		}
		organ.updateOptInfo(old, user.getUserName());
	}

	/**
	 * 删除组织
	 */
	@Override
	@Transactional
	public void changeDelFlg(SysUser user, List<Integer> oidList, int delFlg) throws Exception {
		List<Organization> lor = organDao.findAll(oidList);
		for (Organization old : lor) {
			old.setOptUserName(user.getUserName());
			old.setOptDateTime(DateUtil.getDate());
			old.setDelFlg(delFlg);
			organDao.update(old);
		}
	}

	/**
	 * 区领导查找学校
	 */
	@Override
	@Transactional
	public List<Organization> findByUserAndType(int uid, int type, PageInfo page) throws Exception {
		List<Organization> list = organDao.findByUserAndType(uid, type);
		return list;
	}
	/**
	 * 区领导查找
	 */
	@Override
	@Transactional
	public List<Map<String, Object>> findByUser(SysUser user, PageInfo pageInfo) throws Exception {
		List<Map<String, Object>> la = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> lb = new ArrayList<Map<String, Object>>();
		if(user.getUserType()==2){
			la = organDao.findDataList3(new Integer[]{user.getOid()}, pageInfo);
			lb = organDao.findDataList4(new Integer[]{user.getOid()}, pageInfo);
			la.addAll(lb);
		}
		return la;
	}
	
	/**
	 * 区领导查找学校
	 */
	@Override
	@Transactional
	public List<Organization> findByIDUserAndType(int oid, int uid, int type, PageInfo page) throws Exception {
		List<Organization> list = organDao.findByIDUserAndType(oid, uid, type);
		return list;
	}
	//typeVal组织类型数量
	public List<Map<String, Object>> findTypeCount(SysUser user, int typeVal, PageInfo pageInfo) throws Exception {
		List<Map<String, Object>> la = new ArrayList<Map<String, Object>>();
		if(user.getUserType()==2)
			la = organDao.findDataList1(new Integer[]{typeVal}, pageInfo);
		return la;
		
	}
	//查找各个学校实验员总数
	public List<Map<String, Object>> findTeacherNum(SysUser user, int typeVal, PageInfo pageInfo) throws Exception {
		List<Map<String, Object>> la = new ArrayList<Map<String, Object>>();
		int areaid = user.getAreaid();
		if(user.getUserType()==2)
			la = organDao.findDataList2(new Integer[]{typeVal}, pageInfo);
		return la;
	}
	public int findMyLibCount(SysUser user, int typeVal, PageInfo page) throws Exception {
		List<Map<String, Object>> la = new ArrayList<Map<String, Object>>();
		List<Organization> gld = new ArrayList<Organization>();
		int uid = user.getOid();
		int count = 0;
		gld = organDao.findByUserID(uid);
		// 区管理员
		if (user.getUserType() == 2) {
			gld = organDao.findSonByPreID(gld.get(0).getOid(), 3);
		}
		// 学校管理员gld是一个学校
		for (Organization school : gld) {
//			List<Map<String, Object>> lschool = new ArrayList<Map<String, Object>>();
			StringBuilder videoUrl = new StringBuilder();
			String sql = "select dev_ip,dev_no,dev_name,audio_enable from sn_device where dev_name=? group by dev_name ";
//			lschool = DBConnectionPool.executeQuery(sql, new Object[] { school.getOrganName() });
			String schoolUrl = "";
			String regionUrl = "";
//			if (lschool.size() > 0) {
//				schoolUrl = lschool.get(0).get("dev_ip") + ":554/";
//			}
			schoolUrl = school.getLocationUrl() + ":554/";
			// 区县
			Organization region = organDao.findByID(school.getOrganPreID());

			// List<Map<String, Object>> lregion =
			// DBConnectionPool.executeQuery(sql,
			// new Object[] { region.getOrganName() });
			// if (lregion.size() > 0) {
			// regionUrl = lregion.get(0).get("dev_ip") + "/";
			// }else{
			regionUrl = "rtsp://" + region.getLocationUrl() + ":554/";
			// }
			videoUrl.append(regionUrl);
			videoUrl.append(schoolUrl);
			// 实验室
			count += organDao.findCountSonByPreID(school.getOid(), 6);
		}
		return count;
	}
	/**
	 * 找到当前用户所管辖的实验室
	 */
	@Override
	public List<Map<String, Object>> findMyLibGroup(SysUser user, int typeVal, PageInfo page) throws Exception {	
		List<Map<String, Object>> la = new ArrayList<Map<String, Object>>();
		List<Organization> gld = new ArrayList<Organization>();
		int uid = user.getOid();
		gld = organDao.findByUserID(uid);
		// 区管理员
		if (user.getUserType() == 2) {
			gld = organDao.findSonByPreID(gld.get(0).getOid(), 3);
		}
		// 学校管理员gld是一个学校
		
		for (Organization school : gld) {
			Map<String, Object> mp = new HashMap<String, Object>(); 
			List<Organization> lo = organDao.findSonByPreID(school.getOid(), 6);
			List<String> lstr = new ArrayList<String>();
			for (Organization organ : lo) {
				lstr.add(organ.getOrganName());
			}
			mp.put("school", school.getOrganName());
			mp.put("labName", lstr);
			la.add(mp);
		}
		return la;
	}
	/**
	 * 找到当前用户所管辖的实验室
	 */
	@Override
	public List<Map<String, Object>> findMyLib(SysUser user, int typeVal, PageInfo page) throws Exception {
		List<Map<String, Object>> la = new ArrayList<Map<String, Object>>();
		List<Organization> gld = new ArrayList<Organization>();
		int uid = user.getOid();
		gld = organDao.findByUserID(uid);
		// 区管理员
		if (user.getUserType() == 2) {
			gld = organDao.findSonByPreID(gld.get(0).getOid(), 3);
		}
		// 学校管理员gld是一个学校
		for (Organization school : gld) {
//			List<Map<String, Object>> lschool = new ArrayList<Map<String, Object>>();
			StringBuilder videoUrl = new StringBuilder();
//			String sql = "select dev_ip,dev_no,dev_name,audio_enable from sn_device where dev_name=? group by dev_name ";
//			lschool = DBConnectionPool.executeQuery(sql, new Object[] { school.getOrganName() });
			String schoolUrl = "";
			String regionUrl = "";
//			if (lschool.size() > 0) {
//				schoolUrl = lschool.get(0).get("dev_ip") + ":554/";
//			}
			schoolUrl = school.getLocationUrl() + ":554/";			
			// 区县
			Organization region = organDao.findByID(school.getOrganPreID());

			// List<Map<String, Object>> lregion =
			// DBConnectionPool.executeQuery(sql,
			// new Object[] { region.getOrganName() });
			// if (lregion.size() > 0) {
			// regionUrl = lregion.get(0).get("dev_ip") + "/";
			// }else{
			regionUrl = "rtsp://" + region.getLocationUrl() + ":554/";
			// }
			videoUrl.append(regionUrl);
			videoUrl.append(schoolUrl);
			// 实验室
			List<Organization> lo = organDao.findSonByPreID(school.getOid(), 6);
			String mql = "";
			for (Organization reg : lo) {
				List<Map<String, Object>> lvd = new ArrayList<Map<String, Object>>();
				// 实验室
				// List<Map<String, Object>> lvdio =
				// DBConnectionPool.executeQuery(sql,
				// new Object[] { reg.getOrganName() });
				List<VideoSite> lvdio = videoDao.findByOrgan(reg.getOid());

				StringBuilder videoName = new StringBuilder();
				videoName.append(videoUrl);
				for (VideoSite videoSite : lvdio) {
					Map<String, Object> mp = new HashMap<String, Object>();
					videoName.append("ch" + videoSite.getChanNo() + "/0");
					mp.put("chanNo", videoName.toString());
					mp.put("channel", videoSite.getChannel());
					lvd.add(mp);
				}
				Map<String, Object> mp = new HashMap<String, Object>();
				mp.put("videoUrl", lvd);
				mp.put("organType", reg.getOrganType());
				mp.put("organName", reg.getOrganName());
				la.add(mp);
			}
		}
		return la;
	}

	/**
	 * 找到当前用户所管辖的单位(实验室!=4,年级!=6)
	 */
	@Override
	public Map<String, Object> findMyOrgan(int oid, int typeVal, PageInfo page) throws Exception {
		List<Organization> gld = organDao.findPreByUserID(oid, typeVal);
		Map<String, Object> lob = new HashMap<>();
		for (Organization og : gld) {
			Map<String, Object> mp0 = new HashMap<String, Object>();
			Map<String, Object> mp = new HashMap<String, Object>();
			List<Organization> logan1 = organDao.findPreByID(og.getOid(), typeVal);
			if (null != og.getOrganID()) {
				organId = og.getOrganID();
			}
			if (null != og.getOrganName()) {
				organName = og.getOrganName();
			}
			mp.put("var", organId);
			mp.put("items", findData(logan1, typeVal));
			mp0.put(organName, mp);
			lob.putAll(mp0);
		}
		return lob;
	}
	//学校领导查实验室
	public List<Organization> findLib(SysUser usr, int typeVal, PageInfo page) throws Exception {
		int orgid = usr.getAreaid();
		Organization og = organDao.findByID(orgid);
		List<Organization> logan1 = organDao.findPreByID(og.getOid(), typeVal);
		return logan1;
	}

	public Map<String, Object> findData(List<Organization> lo, int typeVal) throws Exception {
		if (null == lo || lo.size() == 0) {
			return null;
		}
		Map<String, Object> lob = new HashMap<>();
		lob.put("全部", null);
		for (Organization og : lo) {
			Map<String, Object> mp0 = new HashMap<String, Object>();
			Map<String, Object> mp = new HashMap<String, Object>();
			List<Organization> logan1 = organDao.findPreByID(og.getOid(), typeVal);
			if (null != og.getOrganID()) {
				orgId = og.getOrganID();
			}
			if (null != og.getOrganName()) {
				orgName = og.getOrganName();
			}

			if (null != findData(logan1, typeVal) && 0 != findData(logan1, typeVal).size()) {
				mp.put("var", orgId);
				mp.put("items", findData(logan1, typeVal));
				mp0.put(orgName, mp);
			} else
				mp0.put(orgName, orgId);
			lob.putAll(mp0);
		}
		return lob;
	}

}
