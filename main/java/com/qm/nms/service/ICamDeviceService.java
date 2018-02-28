package com.qm.nms.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qm.nms.domain.CamDevice;

@Repository
public interface ICamDeviceService {

	public List<CamDevice> findByOrganID(long organid);
	
	public List<CamDevice> findAll();
	
}
