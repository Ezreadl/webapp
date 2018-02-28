package com.qm.core.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qm.sys.domain.Equipment;
import com.qm.sys.domain.JobTaskRecord;

public abstract class JobThread extends Thread{
	
	protected static Logger logger=LoggerFactory.getLogger(JobThread.class);
	/**
	 * 作业设备
	 */
	protected Equipment equipment;
	/**
	 * 作业任务
	 */
	protected JobTaskRecord jobTask;
	/**
	 * 运行状态0准备，1开始执行，2执行中，3执行完毕
	 */
	protected int runState=0;
	
	@Override
	public void run(){
		try {
			logger.info("设备【"+equipment.getOid()+"】,任务【"+jobTask.getOid()+"】,类型"+jobTask.getItemType()+"开始");
			runState=1;//任务开始
			synchronized (jobTask) {
				jobTask.setExeTaskCount(jobTask.getExeTaskCount()+1);
			}
			executeJob();
		} catch (Exception e) {
			logger.error("设备"+equipment.getChineseName()+"("+equipment.getIpAddress()+"),计划"+jobTask.getPlanId()+"作业错误,连接数"+equipment.getLinkNumber(),e);
		}finally{
			runState=3;//任务完成
			synchronized (jobTask) {
				jobTask.setExeTaskCount(jobTask.getExeTaskCount()+1);
			}
			logger.info("设备【"+equipment.getOid()+"】,任务【"+jobTask.getOid()+"】,类型"+jobTask.getItemType()+"结束");
		}
	}
	
	//实际执行的方法
	public abstract void executeJob() throws Exception;
	
	//用于单次任务完成时触发的方法，子类覆盖实现自己汇总清理工作
	public void taskExecuteOver() throws Exception{
		
	}
	
	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public JobTaskRecord getJobTask() {
		return jobTask;
	}

	public void setJobTask(JobTaskRecord jobTask) {
		this.jobTask = jobTask;
	}
	
	public int getRunState() {
		return runState;
	}
	
}
