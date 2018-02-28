package com.qm.nms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qm.core.base.IBaseDao;
import com.qm.nms.domain.CamDevice;

@Repository
public interface ICamDeviceDao extends IBaseDao<CamDevice,Integer>{
	
	@Query(value="select * from  CamDevice par where par.organid=?1 and delFlg=0 ",nativeQuery=true)	
	public List<CamDevice> findByOrganID(long organid);
	
//	@Query(value="select * from  CamDevice par where par.organid=?1 and delFlg=0 ",nativeQuery=true)	
//	public List<CamDevice> findByOrganID(long courseid);	
//	
}
