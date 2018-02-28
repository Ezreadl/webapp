package com.qm.nms.service.impl;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qm.core.base.PageInfo;
import com.qm.core.util.DateUtil;
import com.qm.nms.dao.IScheduleDao;
import com.qm.nms.domain.UsageGoal;
import com.qm.nms.service.IUsageService;
import com.qm.sys.domain.SysUser;
@Service
public class UsageServiceImpl implements IUsageService{
	@Autowired
	public IScheduleDao scheduleDao;
	public static void main(String[] args) {
		String []str = {"a","b","c"};
		char []cr = {'a','b','c'};
		Calendar cl = Calendar.getInstance();
		cl.setTime(new Date());
		cl.set(2018, 0, 17);
		Date dt = cl.getTime();
		System.out.println(DateUtil.getYmd(dt));
		Calendar cal = Calendar.getInstance();
		System.out.println("num:"+(cl.getTimeInMillis()-cal.getTimeInMillis())/(24*60*60*1000));
		cal.add(Calendar.YEAR, 10);
//		for (int i = 0; i < 2; i++) {	//0//1
//			for (int j = 0; j < 2; j++) {//0//0
//				System.out.println(i+j);//0//1
//				if(j==0){
//					break;
////					continue;
//				}
//			}
//		}
		String str1= "200";
	
		System.out.println(Integer.parseInt(str1,16));

		String str2= "123456";

		System.out.println(Float.parseFloat(str2));
	}
	@Override
	public List<Map<String, Object>> findMyLabUsage(SysUser user, PageInfo pageInfo) throws Exception {
		List<Map<String, Object>> lsch = scheduleDao.findDataList1(new Object[]{}, pageInfo);
//		List<Map<String, Object>> lsch = gld.getRows();
		for (Map mp : lsch) {
			mp.put("num", 2);
			mp.put("rate", Double.parseDouble(mp.get("num").toString())/50);
		}
		return lsch;
	}
	
	@Override
	public List<Map<String, Object>> findByType(int schoolType,PageInfo pageInfo) throws Exception {
		List<Map<String, Object>> lsch = scheduleDao.findDataList1(new Object[]{}, pageInfo);
//		List<Map<String, Object>> lsch = gld.getRows();
		DecimalFormat df = new DecimalFormat("#.00");
		for (Map mp : lsch) {
			mp.put("num", 2);
			mp.put("rate", (float)Double.parseDouble(mp.get("num").toString())/50);
		}
		return lsch;
	}
	@Transactional
	@Override
	public List<Map<String, Object>> findRateRank(SysUser user, PageInfo pageInfo) throws Exception {
		List<Map<String, Object>> lsch =scheduleDao.findDataList2(new Object[]{},pageInfo);
		for (Map<String, Object> map : lsch) {
			map.put("num", 2);
			map.put("rate", (float)(Integer.parseInt(map.get("num").toString())*0.01));
		}
//		List<Map<String, Object>> lsch = gld.getRows();
		return lsch;
	}
	@Transactional
	@Override
	public List<Map<String, Object>> findUsageGoal() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional
	@Override
	public void updateGoal(SysUser user, UsageGoal goal) {
		// TODO Auto-generated method stub
		
	}


}
