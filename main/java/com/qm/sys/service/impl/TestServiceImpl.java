package com.qm.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qm.sys.dao.ITestDao;
import com.qm.sys.domain.Test;
import com.qm.sys.service.ITestService;

@Service
public class TestServiceImpl implements ITestService{

	@Autowired
	private ITestDao testDao;
	@Override
	public List<Object> findTestlist(String name) throws Exception {
		// TODO Auto-generated method stub
		List<Object> testList = new ArrayList<>();		
		Test test = testDao.findTestByName(name);
		testList.add(test);
		return testList;
	}
	@Override
	public List<Object> findTestlist() throws Exception {
		// TODO Auto-generated method stub
		List<Object> testList = new ArrayList<>();		
		Test test = testDao.findTestByName("");
		testList.add(test);
		return testList;
	}	
	@Override
	public Test findTest(String name) throws Exception {
		// TODO Auto-generated method stub
		Test test = testDao.findTestByName(name);
		return test;
	}
	@Override
	public Test findTest() throws Exception {
		// TODO Auto-generated method stub
		Test test = testDao.findTestByName("");
		return test;
	}
}
