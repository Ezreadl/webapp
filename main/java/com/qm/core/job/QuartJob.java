package com.qm.core.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qm.core.util.CipherUtil;
import com.qm.core.util.ConvertUtil;
import com.qm.core.util.SpringContextUtil;
import com.qm.core.util.StringUtil;
import com.qm.sys.domain.Equipment;
import com.qm.sys.domain.JobPlan;
import com.qm.sys.domain.JobTaskRecord;
import com.qm.sys.domain.SysDictionary;
import com.qm.sys.service.IEquipmentService;
import com.qm.sys.service.IJobPlanService;
import com.qm.sys.service.ISysDictionaryService;
/**
 * 自动作业启动
 * @author (wangchong)
 */
public class QuartJob implements Job{
	
	private static Logger logger=LoggerFactory.getLogger(QuartJob.class);
	
	/**
	 * key为作业计划id号，为每个作业计划分配一个ExecutorService
	 */
	private static Map<Integer,ThreadPoolExecutor> jobThreadPoolMap=new HashMap<Integer,ThreadPoolExecutor>();
	/**
	 * key为作业调度到的任务,用于监控作业任务是否执行完毕，以及检查是否有重复任务
	 */
	private static Map<JobTaskRecord,List<JobThread>> jobTaskThreadMap=new HashMap<JobTaskRecord,List<JobThread>>();
	/**
	 * 指示是否停止作业
	 */
	private static boolean stopJob=false;
	/**
	 * 任务执行完毕监视线程
	 */
	private static JobTaskMonitorThead taskMonitorListener=null;
	
	//自动作业
	public void execute(JobExecutionContext jobContext) throws JobExecutionException {
		Integer jobPlanId=(Integer)jobContext.getJobDetail().getJobDataMap().get("jobplanid");
		executeAutoJob(jobPlanId,0);
	}
	
	public void executeAutoJob(Integer jobPlanId,int source){
		try {
			IJobPlanService jobPlanService=(IJobPlanService)SpringContextUtil.getBean("jobPlanService");
			JobPlan jobPlan=jobPlanService.findJobPlanById(jobPlanId);
//			JobPlan jobPlan=findJobPlanById(jobPlanId);
			if(stopJob || jobPlan==null || StringUtil.isBlank(jobPlan.getPlanType()) || 
					((jobPlan.getStateFlg()==1 || jobPlan.getDelFlg()==1) && source==0)){
				return ;
			}
			logger.info("触发执行计划"+jobPlanId);
			String[] planTypeArr=jobPlan.getPlanType().split(",");
//			IJobTaskRecordService jobTaskRecordService=(IJobTaskRecordService)SpringContextUtil.getBean("jobTaskRecordService");
			for(int i=0;i<planTypeArr.length;i++){
				int jobType=Integer.parseInt(planTypeArr[i]);
				//创建自动作业任务
				List<JobThread> jobThreadList=createJobThread(jobPlan);
				if(jobThreadList.size()<=0){
					logger.error("作业计划"+jobPlan.getPlanName()+"，类型【"+jobType+"】没有要执行的设备");
					continue;
				}
				JobTaskRecord jobTask=new JobTaskRecord();
				jobTask.setStartTime(new Date());
				jobTask.setEndTime(new Date());
				jobTask.setPlanId(jobPlanId);
				jobTask.setItemType(jobType);
				jobTask.setPlanTaskCnt(jobThreadList.size());
				//移除还有1次任务未完成的设备
				removeNotCompleteTask(jobThreadList,jobTask);
				jobTask.setTriggerTaskCnt(jobThreadList.size());
				saveJobTaskRecord(jobTask);
//				jobTaskRecordService.saveJobTaskRecord(jobTask);
				//获取或创建执行线程池
				ThreadPoolExecutor jobThreadPool=createThreadPool(jobPlan);
				//启动线程
				for(JobThread jobThread:jobThreadList){
					jobThread.setJobTask(jobTask);
					jobThreadPool.submit(jobThread);
				}
				jobTask.setExeStatus(1);
				saveJobTaskRecord(jobTask);
//				jobTaskRecordService.saveJobTaskRecord(jobTask);
				//将作业任务线程放入监控对象
				putJobTaskMonitorThread(jobTask,jobThreadList);
			}
			if(taskMonitorListener==null){
				taskMonitorListener=new JobTaskMonitorThead();
				taskMonitorListener.start();
			}
		} catch (Exception e) {
			logger.error("启动作业计划"+jobPlanId+"错误!",e);
		}
	}
	
	protected JobPlan findJobPlanById(Integer jobPlanId) {
		Session session=null;
		try{
			session = SpringContextUtil.getSession();
			Query query = session.createQuery("from JobPlan where oid = :oid");
			query.setParameter("oid", jobPlanId);
			return (JobPlan) query.list().get(0);
		}catch(Exception e){
			throw e;
		}finally{
			if(session!=null){
				session.close();
			}
		}
	}

	protected void saveJobTaskRecord(JobTaskRecord jobTask) {
		Session session=null;
		Transaction transaction=null;
		try {
			session=SpringContextUtil.getSession();
			transaction=session.beginTransaction();
			if(jobTask.getOid()<=0){
				session.save(jobTask);
			}else{
				session.update(jobTask);
			}
			session.save(jobTask);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		}finally{
			if(session!=null){
				session.close();
			}
		}	
		
	}

	//作业任务监视线程，每隔5秒检查任务是否完成
	class JobTaskMonitorThead extends Thread{
		@SuppressWarnings("deprecation")
		@Override
		public void run() {
			long preUpdateTime=System.currentTimeMillis();
			while(! stopJob){
				try {
					Thread.sleep(5*1000);//间隔5秒检查一次，任务执行状态
					synchronized (jobTaskThreadMap) {
						JobThread lastTaskRecord=null;
						Iterator<JobTaskRecord> iterator=jobTaskThreadMap.keySet().iterator();
						while(iterator.hasNext()){
							JobTaskRecord jobTask=iterator.next();
							List<JobThread> jobThreadList=jobTaskThreadMap.get(jobTask);
							boolean runOver=true;
							for(int i=0;i<jobThreadList.size();i++){
								JobThread jobThread=jobThreadList.get(i);
								if(jobThread.getRunState()==3){
									lastTaskRecord=jobThread;
									jobThreadList.remove(i);
								}else{
									runOver=false;
								}
							}
							//如果任务完成移除任务
							if(runOver || jobThreadList.size()==0){
								logger.info("任务"+jobTask.getOid()+"任务完成");
								jobTask.setExeStatus(2);
								iterator.remove();
								//触发全部任务完毕事件
								lastTaskRecord.taskExecuteOver();
								jobTask.setEndTime(new Date());
								jobTask.setUseTime(jobTask.getEndTime().getTime()- jobTask.getStartTime().getTime());
//								IJobTaskRecordService jobTaskRecordService=(IJobTaskRecordService)SpringContextUtil.getBean("jobTaskRecordService");
								saveJobTaskRecord(jobTask);
//								jobTaskRecordService.saveJobTaskRecord(jobTask);
								
							}else if((System.currentTimeMillis()-preUpdateTime)>5*60*1000){
								preUpdateTime=System.currentTimeMillis();
//								IJobTaskRecordService jobTaskRecordService=(IJobTaskRecordService)SpringContextUtil.getBean("jobTaskRecordService");
								saveJobTaskRecord(jobTask);
//								jobTaskRecordService.saveJobTaskRecord(jobTask);
							}else if(jobTask.getExeStatus()==1 && (System.currentTimeMillis()-jobTask.getStartTime().getTime())>2*60*60*1000){
								logger.error("单任务【"+jobTask.getOid()+"】超过2小时，尝试强制终止");
								//单任务超过5小时终止任务，防止任务卡死
								for(JobThread jobThread:jobThreadList){
									jobThread.stop();
								}
							}
						}
					}
				} catch (Exception e) {
					logger.error("任务完毕监视线程异常",e);
				}
			}
		}
	}
	
	//获取执行线程池,如果不存在则创建
	private ThreadPoolExecutor createThreadPool(JobPlan jobPlan){
		ThreadPoolExecutor jobThreadPool=jobThreadPoolMap.get(jobPlan.getOid());
		if(jobThreadPool==null){
			int poolSize=jobPlan.getThreadCount()<=1 ? 1 : jobPlan.getThreadCount();
			jobThreadPool=(ThreadPoolExecutor)Executors.newFixedThreadPool(poolSize);
			jobThreadPoolMap.put(jobPlan.getOid(),jobThreadPool);
		}else{
			if(jobPlan.getThreadCount()>1 && jobThreadPool.getCorePoolSize()!=jobPlan.getThreadCount()){
				jobThreadPool.setCorePoolSize(jobPlan.getThreadCount());
			}
		}
		return jobThreadPool;
	}
	
	//将作业线程列表加入作业任务监控对象
	private static void putJobTaskMonitorThread(JobTaskRecord jobTask,List<JobThread> jobThread){
		synchronized (jobTaskThreadMap) {
			jobTaskThreadMap.put(jobTask,jobThread);
		}
	}
	
	//根据作业计划创建作业执行线程（根据字典配置，实例化作业对象，要求必须继承至JobThread）
	private List<JobThread> createJobThread(JobPlan jobPlan) throws Exception{
		List<Integer> itemTypes=ConvertUtil.convertStr2IntegerList(jobPlan.getPlanType());
		IEquipmentService equipmentService=(IEquipmentService)SpringContextUtil.getBean("equipmentService");
		ISysDictionaryService sysDictionaryService=(ISysDictionaryService)SpringContextUtil.getBean("sysDictionaryService");
		List<Equipment> equipList=null;
		if(StringUtil.isNotBlank(jobPlan.getEquipIds())){
			List<Integer> equipIds=ConvertUtil.convertStr2IntegerList(jobPlan.getEquipIds());
			equipList=equipmentService.findEquipmentById(equipIds);
//			equipList = findEquipmentById(equipIds);
		}else{
			equipList=equipmentService.findEquipmentAll();//如果为空取全部设备
//			equipList = findEquipmentAll();
		}
		List<SysDictionary> sysDicList=sysDictionaryService.findSysDictionaryById(itemTypes);
//		List<SysDictionary> sysDicList=findSysDictionaryById(itemTypes);
		List<JobThread> jobThreadList=new ArrayList<JobThread>();
		for(SysDictionary sysDic : sysDicList){
			if(StringUtil.isNotBlank(sysDic.getAdditional())){
				for(Equipment equipment : equipList){
					JobThread exeClass=(JobThread)Class.forName(sysDic.getAdditional().trim()).newInstance();
					exeClass.setEquipment(equipment);
					jobThreadList.add(exeClass);
				}
			}
		}
		return jobThreadList;
	}
	
	@SuppressWarnings("unchecked")
	protected List<SysDictionary> findSysDictionaryById(List<Integer> itemTypes) {
		Session session = null;
		try{
			session = SpringContextUtil.getSession();
			Query query = session.createQuery("from SysDictionary d where d.oid in(:oidList)");
			query.setParameterList("oidList", itemTypes);
			List<SysDictionary> list = query.list();
			if(list != null && list.get(0).getOid()>0){
				return list; 
			}
			return null;
		}catch(Exception e){
			throw e;
		}finally{
			if(session != null){
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected List<Equipment> findEquipmentAll() {
		Session session = null;
		try{
			session = SpringContextUtil.getSession();
			Query query = session.createQuery("from Equipment e where e.stateFlg = 0 and e.delFlg = 0 order by(e.chineseName) asc");
			List<Equipment> list = query.list();
			if(list != null && list.get(0).getOid()>0){
				return baseDecryptEquipment(list); 
			}
			return null;
		}catch(Exception e){
			throw e;
		}finally{
			if(session != null){
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected List<Equipment> findEquipmentById(List<Integer> equipIds) {
		Session session = null;
		try{
			session = SpringContextUtil.getSession();
			Query query = session.createQuery("from Equipment e where e.oid in(:oidList)");
			query.setParameterList("oidList", equipIds);
			List<Equipment> list = query.list();
			if(list != null && list.get(0).getOid()>0){
				return baseDecryptEquipment(list); 
			}
			return null;
		}catch(Exception e){
			throw e;
		}finally{
			if(session != null){
				session.close();
			}
		}
	}

	public static List<Equipment> baseEncryptEquipment(List<Equipment> equipList){
		if(equipList==null){
			return equipList;
		}
		for(Equipment e:equipList){
			baseEncryptEquipment(e);
		}
		return equipList;
	}
	
	//对设备的账号、密码进行加密
	public static Equipment baseEncryptEquipment(Equipment equipment){
		if(equipment==null){
			return equipment;
		}
		synchronized (equipment) {
			if(equipment.isHasDeDecrypt()){
				equipment.setMaintainUser(CipherUtil.baseEncrypt(CipherUtil.baseEncrypt(equipment.getMaintainUser())));
				equipment.setMaintainPwd(CipherUtil.baseEncrypt(CipherUtil.baseEncrypt(equipment.getMaintainPwd())));
				equipment.setManagerPwd(CipherUtil.baseEncrypt(CipherUtil.baseEncrypt(equipment.getManagerPwd())));
				equipment.setManagerUser(CipherUtil.baseEncrypt(CipherUtil.baseEncrypt(equipment.getManagerUser())));
				equipment.setFtpUserName(CipherUtil.baseEncrypt(CipherUtil.baseEncrypt(equipment.getFtpUserName())));
				equipment.setFtpPassword(CipherUtil.baseEncrypt(CipherUtil.baseEncrypt(equipment.getFtpPassword())));
				equipment.setHasDeDecrypt(false);
			}
		}
		return equipment;
	}
	
	public static List<Equipment> baseDecryptEquipment(List<Equipment> equipList){
		if(equipList==null){
			return equipList;
		}
		for(Equipment e:equipList){
			baseDecryptEquipment(e);
		}
		return equipList;
	}
	
	//对设备的账号、密码进行解密
	public static Equipment baseDecryptEquipment(Equipment equipment){
		if(equipment==null){
			return equipment;
		}
		synchronized (equipment) {
			if(!equipment.isHasDeDecrypt()){
				equipment.setMaintainUser(CipherUtil.baseDecrypt(CipherUtil.baseDecrypt(equipment.getMaintainUser())));
				equipment.setMaintainPwd(CipherUtil.baseDecrypt(CipherUtil.baseDecrypt(equipment.getMaintainPwd())));
				equipment.setManagerPwd(CipherUtil.baseDecrypt(CipherUtil.baseDecrypt(equipment.getManagerPwd())));
				equipment.setManagerUser(CipherUtil.baseDecrypt(CipherUtil.baseDecrypt(equipment.getManagerUser())));
				equipment.setFtpUserName(CipherUtil.baseDecrypt(CipherUtil.baseDecrypt(equipment.getFtpUserName())));
				equipment.setFtpPassword(CipherUtil.baseDecrypt(CipherUtil.baseDecrypt(equipment.getFtpPassword())));
				equipment.setHasDeDecrypt(true);
			}
		}
		return equipment;
	}

	//去除任务队列中已经存在2次的相同未完成的任务
	private void removeNotCompleteTask(List<JobThread> jobThreadList,JobTaskRecord jobTask){
		if(jobThreadList==null || jobThreadList.size()<=0){
			return ;
		}
		//查找还有1次及以上没有完成的任务设备
		Set<Integer> oldEquipIdSet=new HashSet<Integer>();
		for(JobTaskRecord oldJobTask:jobTaskThreadMap.keySet()){
			if(oldJobTask.getItemType()==jobTask.getItemType() && oldJobTask.getPlanId()==jobTask.getPlanId()){
				for(JobThread oldJobThread:jobTaskThreadMap.get(oldJobTask)){
					if(oldJobThread.getEquipment()!=null && oldJobThread.getRunState()!=3){
						oldEquipIdSet.add(oldJobThread.getEquipment().getOid());
					}
				}
			}
		}
		if(oldEquipIdSet.size()>0){
			//删除未完成任务大于1次的任务设备
			for(int i=0;i<jobThreadList.size();i++){
				JobThread jobThread=jobThreadList.get(i);
				if(jobThread.getEquipment()!=null && oldEquipIdSet.contains(jobThread.getEquipment().getOid())){
					jobThreadList.remove(i);
					i--;
					logger.error("设备"+jobThread.getEquipment().getOid()+"任务"+jobTask.getPlanId()+"作业类型"+jobTask.getItemType()+"被移除");
				}
			}
		}
	}
	
	//关闭作业线程
	@SuppressWarnings("deprecation")
	public static void shutDown(){
		try {
			stopJob=true;
			for(JobTaskRecord jobTask:jobTaskThreadMap.keySet()){
				for(JobThread jobThread:jobTaskThreadMap.get(jobTask)){
					jobThread.stop();
				}
			}
			for(Integer planId:jobThreadPoolMap.keySet()){
				jobThreadPoolMap.get(planId).shutdownNow();
			}
			if(taskMonitorListener!=null){
				taskMonitorListener.stop();
				taskMonitorListener=null;
			}
		} catch (Exception e) {
			logger.error("作业线程关闭异常!",e);
		}
		taskMonitorListener=null;
	}

	public static Map<JobTaskRecord, List<JobThread>> getJobTaskThreadMap() {
		return jobTaskThreadMap;
	}
}
