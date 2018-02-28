package com.qm.core.job;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.qm.nms.job.IpSearchProc;

public class SchedureListener implements  ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		JobPlanManager jobManager = new JobPlanManager();
		jobManager.createTask();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		JobPlanManager jobManager = new JobPlanManager();
		jobManager.destoryTask();
		QuartJob.shutDown();
		IpSearchProc.shutDown();
	}
}
