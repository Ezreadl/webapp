package com.qm.core.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qm.core.util.DateUtil;
import com.qm.core.util.SpringContextUtil;
import com.qm.nms.job.StatisticInterfaceRecordJob;
import com.qm.sys.domain.JobPlan;
import com.qm.sys.service.IJobPlanService;
/**
 * 作业管理类型，
 * 用于控制作业计划的加入和清除
 * 以及作业计划的变更
 * @author (wangchong)
 */
public class JobPlanManager {
	
	private Logger logger=LoggerFactory.getLogger(JobPlanManager.class);
	
	//创建所有作业任务计划
	public void createTask(){
		try {
			SchedulerFactory factory = new StdSchedulerFactory();
			Scheduler scheduler = factory.getScheduler();
			IJobPlanService jobPlanService=(IJobPlanService)SpringContextUtil.getBean("jobPlanService");
			List<JobPlan> jobPlanList = jobPlanService.findJobPlanAll();
//			List<JobPlan> jobPlanList=findJobPlanAll();
			for(JobPlan jobPlan:jobPlanList){
				if(jobPlan.getStateFlg()==0 && jobPlan.getDelFlg()==0){
					addJob(jobPlan);
				}
			}
			scheduler.start();
			addInterFlowStatisticob();
		} catch (Exception e) {
			logger.error("启动定时任务失败!",e);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected List<JobPlan> findJobPlanAll() {
		Session session = null;
		try{
			session = SpringContextUtil.getSession();
			Query query = session.createQuery("from JobPlan where delFlg = 0");
			List<JobPlan> list = query.list();
			if(list != null && list.get(0).getOid() > 0){
				return list;
			}
			return null;
		}catch(Exception e){
			throw e;
		}finally{
			if(session!=null){
				session.close();
			}
		}
	}

	//根据作业任务计划获取quartz作业表达式
	public String getExpression(JobPlan jobPlan,Date executeTime){
		Calendar c=Calendar.getInstance();
		c.setTime(executeTime);
		if(jobPlan.getUnit()==2){//天
			return "0 "+c.get(Calendar.MINUTE)+" "+c.get(Calendar.HOUR_OF_DAY)+" * * ?";
		}else if(jobPlan.getUnit()==3){//周
			return "0 "+c.get(Calendar.MINUTE)+" "+c.get(Calendar.HOUR_OF_DAY)+" ? * "+c.get(Calendar.WEEK_OF_MONTH);
		}else if(jobPlan.getUnit()==4){//月
			return "0 "+c.get(Calendar.MINUTE)+" "+c.get(Calendar.HOUR_OF_DAY)+" "+c.get(Calendar.DAY_OF_MONTH)+" * ?";
		}else if(jobPlan.getUnit()==5){//年
			return "0 "+c.get(Calendar.MINUTE)+" "+c.get(Calendar.HOUR_OF_DAY)+" "+c.get(Calendar.DAY_OF_MONTH)+" "+c.get(Calendar.MONTH)+" ?";
		}
		return null;
	}
	
	public Date getExecuteStartTime(JobPlan jobPlan){
		try {
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			Date startDate=new Date();//默认为当前日期
			Date setedDate=df.parse(jobPlan.getStartJobDate());
			if(startDate.before(setedDate)){
				startDate=setedDate;
			}
			Calendar calendar=Calendar.getInstance();
			String hour=jobPlan.getStartJobHour();
			String minute=jobPlan.getStartJobMinute();
			//如果为*，则取当前时间
			if("**".equals(hour)){
				hour=calendar.get(Calendar.HOUR_OF_DAY)+"";
			}
			if("**".equals(minute)){
				minute=calendar.get(Calendar.MINUTE)+"";
			}
			hour=hour.length()==1?"0"+hour:hour;
			minute=(Integer.parseInt(minute)+1)+"";//推迟1分钟
			minute=minute.length()==1?"0"+minute:minute;
			String startStr=df.format(startDate)+" "+hour+":"+minute+":00";
			SimpleDateFormat dateDf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startTime=dateDf.parse(startStr);
			if(jobPlan.getStartJobHour().contains("*") || jobPlan.getStartJobMinute().contains("*")){
				//如果过时则推迟一个小时
				if(startTime.before(new Date())){
					Calendar c=Calendar.getInstance();
					c.setTime(startTime);
					c.add(Calendar.HOUR_OF_DAY,1);
					startTime=c.getTime();
				}
			}
			return startTime;
		} catch (Exception e) {
			logger.error("日期解析异常!",e);
		}
		return new Date();
	}
	
	//删除所有作业任务计划
	public boolean destoryTask(){
		try {
			SchedulerFactory factory = new StdSchedulerFactory();
			Scheduler scheduler = factory.getScheduler();
			String[] triggerGroups = scheduler.getTriggerGroupNames();
			String[] jobGroups = scheduler.getJobGroupNames();
			for(String triggerGroup:triggerGroups){
				String[] tiggers= scheduler.getTriggerNames(triggerGroup);
				for(String tigger:tiggers){
					scheduler.unscheduleJob(tigger, triggerGroup);//停止触发
				}
			}
			for(String jobGroup:jobGroups){
				String[] jobs= scheduler.getJobNames(jobGroup);
				for(String job:jobs){
					scheduler.deleteJob(job, jobGroup);//删除任务
				}
			}
			scheduler.shutdown(false);
			return true;
		} catch (Exception e) {
			logger.error("终止定时任务异常!",e);
		}
		return false;
	}
	
	//添加任务
	public boolean addJob(JobPlan jobPlan){
		try {
			SchedulerFactory factory = new StdSchedulerFactory();
			Scheduler scheduler = factory.getScheduler();
			JobDetail detail = new JobDetail("detail"+jobPlan.getOid(),"detailgroup"+jobPlan.getOid(), QuartJob.class);
			detail.getJobDataMap().put("jobplanid", jobPlan.getOid());
			Date startTime=getExecuteStartTime(jobPlan);
			logger.info(jobPlan.getPlanName()+" 开始时间"+DateUtil.getYmdHms(startTime));
			if(jobPlan.getUnit()>=2){
				String expression =getExpression(jobPlan,startTime);
				logger.info("执行表达式"+expression);
				CronExpression cronExp = new CronExpression(expression);
				CronTrigger trigger = new CronTrigger("trigger"+jobPlan.getOid(),"triggergroup"+jobPlan.getOid());
				trigger.setCronExpression(cronExp);
				scheduler.scheduleJob(detail, trigger);
			}else{//周期为分钟或小时
				SimpleTrigger trigger=new SimpleTrigger("trigger"+jobPlan.getOid(),"triggergroup"+jobPlan.getOid());
				long repeatInterval=0;
				if(jobPlan.getUnit()==0){
					repeatInterval=jobPlan.getIntervalTime()*1000*60;//分钟
				}else if(jobPlan.getUnit()==1){
					repeatInterval=jobPlan.getIntervalTime()*1000*60*60;//小时
				}
				trigger.setStartTime(startTime);
				trigger.setRepeatCount(Integer.MAX_VALUE);
				trigger.setRepeatInterval(repeatInterval);
				scheduler.scheduleJob(detail, trigger);
			}
			scheduler.start();
			return true;
		} catch (Exception e) {
			logger.error("加入作业错误",e);
		}
		return false;
	}
	
	//删除任务
	public boolean deleteJob(int jobPlanId){
		try {
			SchedulerFactory factory = new StdSchedulerFactory();
			Scheduler scheduler = factory.getScheduler();
			scheduler.unscheduleJob("trigger"+jobPlanId,"triggergroup"+jobPlanId);
			scheduler.deleteJob("detail"+jobPlanId,"detailgroup"+jobPlanId);
			return true;
		} catch (Exception e) {
			logger.error("删除作业错误",e);
		}
		return false;
	}
	//添加流量统计
	public boolean addInterFlowStatisticob(){
		try {
			SchedulerFactory factory = new StdSchedulerFactory();
			Scheduler scheduler = factory.getScheduler();
			JobDetail detail = new JobDetail("StatisticInterfaceRecorddetail","StatisticInterfaceRecordgroup" , StatisticInterfaceRecordJob.class);
			CronExpression cronExp = new CronExpression("0 08 * * * ?");
			CronTrigger trigger = new CronTrigger("StatisticInterfaceRecordtrigger", "StatisticInterfaceRecordgroup");
			trigger.setCronExpression(cronExp);
			scheduler.scheduleJob(detail, trigger);
			scheduler.start();
			return true;
		} catch (Exception e) {
			logger.error("启动流量统计错误",e);
		}
		return false;
	}
}
