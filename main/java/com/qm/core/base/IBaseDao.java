package com.qm.core.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IBaseDao<T,ID extends Serializable> extends JpaRepository<T, ID>,JpaSpecificationExecutor<T> {
	
	public void update(T t);
	
	public long findDataCount1(Object[] params) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List findDataList1(Object[] params,PageInfo pageInfo) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List findDataList2(Object[] params,PageInfo pageInfo) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List findDataList3(Object[] params,PageInfo pageInfo) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List findDataList4(Object[] params,PageInfo pageInfo) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List findDataList5(Object[] params,PageInfo pageInfo) throws Exception;
	
	public GridListData findDataList1(IQuery query,PageInfo pageInfo) throws Exception;
   
	public GridListData findDataList2(IQuery query,PageInfo pageInfo) throws Exception;
   
	public GridListData findDataList3(IQuery query,PageInfo pageInfo) throws Exception;
   
	public GridListData findDataList(String hql,IQuery query,PageInfo pageInfo,boolean nativeQuery) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List findDataList(String sql,Object[] params,PageInfo pageInfo,boolean nativeQuery);
	
	public long findDataCount(String sql,Object[] params,boolean nativeQuery);
   
	public int execute(String sql,Object[] params,boolean nativeQuery) throws Exception;
	
	public void batchUpdate(List<T> list) throws Exception;
	
	public void batchSave(List<T> list) throws Exception;

	GridListData findDataListByMap(HashMap map, PageInfo pageInfo) throws Exception;

}
