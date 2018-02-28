package com.qm.core.util;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtil implements ApplicationContextAware {
	
    private static ApplicationContext applicationContext;
    
    protected static DataSource dataSource;
    
    private static EntityManagerFactory entityManagerFactory;
    
	@Override
	 public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.applicationContext=applicationContext;
	}
	
	public void setDataSource(DataSource dataSource) {
		SpringContextUtil.dataSource = dataSource;
	}

	public static Object getBean(String beanName){
		return SpringContextUtil.applicationContext.getBean(beanName);
	}
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public Session getSession() {
		Session session=entityManagerFactory.createEntityManager().unwrap(org.hibernate.Session.class);
		return session;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		SpringContextUtil.entityManagerFactory = entityManagerFactory;
	}
}