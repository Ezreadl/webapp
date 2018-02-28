package com.qm.nms.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qm.nms.domain.TermInfo;

@Repository
public interface ITermInfoService {

	public List<TermInfo> findTermInfoByName(String name) throws Exception;
}
