package com.qm.sys.service;

import java.util.Collection;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qm.core.base.GridListData;
import com.qm.core.base.PageInfo;
import com.qm.sys.domain.SysDictionary;
import com.qm.sys.domain.SysUser;
import com.qm.sys.query.SysDictionaryQuery;

public interface ISysDictionaryService {

	public SysDictionary findSysDictionaryById(int oid) throws Exception ;

	public List<SysDictionary> findSysDictionaryById(List<Integer> oids) throws Exception;

	public GridListData findSysDictionaryList(SysUser sysUser,SysDictionaryQuery query,PageInfo pageInfo) throws Exception ;

	public void saveSysDictionary(SysUser sysUser,SysDictionary equipment) throws Exception;

	public void changeDelFlg(SysUser sysUser,List<Integer> oidList,int delFlg) throws Exception;

}