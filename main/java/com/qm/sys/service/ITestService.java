package com.qm.sys.service;

import java.util.List;

import com.qm.sys.domain.Test;

public interface ITestService {
	
	public List<Object> findTestlist(String name) throws Exception;
	
	public List<Object> findTestlist() throws Exception;	
	
	public Test findTest(String name) throws Exception;

	public Test findTest() throws Exception;
	
}
