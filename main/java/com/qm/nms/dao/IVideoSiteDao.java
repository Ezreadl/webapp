package com.qm.nms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qm.core.base.IBaseDao;
import com.qm.nms.domain.VideoSite;

@Repository
public interface IVideoSiteDao extends IBaseDao<VideoSite,Integer>{

	@Query(value="select * from videosite where organid=?1 ",nativeQuery = true)
	public List<VideoSite> findByOrgan(int organid);
}
