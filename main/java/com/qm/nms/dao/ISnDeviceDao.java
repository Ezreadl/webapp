package com.qm.nms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qm.core.base.GridListData;
import com.qm.core.base.IBaseDao;
import com.qm.core.base.IQuery;
import com.qm.core.base.PageInfo;
import com.qm.core.base.QmQuery;
import com.qm.nms.domain.SnDevice;
import com.qm.nms.domain.SnRoleUser;

@Repository
public interface ISnDeviceDao extends IBaseDao<SnDevice, Integer> {
	@Query(value = "select * from SnDevice par { #& par.devId=:devId #& par.devName=:devName #& par.devFirm=:devFirm "
			+ "#& par.devIp=:devIp #& par.devPort=:devPort #& par.devUsername=:devUsername "
			+ "#& par.devPassword=:devPassword #& par.devSn=:devSn #& par.devLevel=:devLevel "
			+ "#& par.devTypeId=:devTypeId #& par.devTypeChild=:devTypeChild #& par.devInstallSite=:devInstallSite "
			+ "#& par.devInstallDate=:devInstallDate #& par.devEnable=:devEnable #& par.devState=:devState "
			+ "#& par.devDefence=:devDefence #& par.repeatTime=:repeatTime #& par.posiId=:posiId "
			+ "#& par.parentDevId=:parentDevId #& par.priority=:priority #& par.trueChannelCnt=:trueChannelCnt "
			+ "#& par.channelCnt=:channelCnt #& par.trueIoInCnt=:trueIoInCnt #& par.ioInCnt=:ioInCnt "
			+ "#& par.trueIoOutCnt=:trueIoOutCnt #& par.ioOutCnt=:ioOutCnt #& par.channelStart=:channelStart "
			+ "#& par.bizType=:bizType #& par.devNo=:devNo #& par.interfaceType=:interfaceType #& par.comPort=:comPort "
			+ "#& par.addressCode=:addressCode #& par.baudrate=:baudrate #& par.databit=:databit #& par.paritybit=:paritybit "
			+ "#& par.stopbit=:stopbit #& par.mainCodeRate=:mainCodeRate #& par.mainFrameRate=:mainFrameRate "
			+ "#& par.mainDisplay=:mainDisplay #& par.subCodeRate=:subCodeRate #& par.subFrameRate=:subFrameRate "
			+ "#& par.subDisplay=:subDisplay #& par.customId=:customId #& par.manager=:manager #& par.contact=:contact "
			+ "#& par.reserve1=:reserve1 #& par.reserve2=:reserve2 #& par.reserve3=:reserve3 #& par.addTime=:addTime "
			+ "#& par.updateTime=:updateTime #& par.isDel=:isDel #& par.dispscr=:dispscr #& par.maxpixel=:maxpixel "
			+ "#& par.audiodsp=:audiodsp #& par.scrnum=:scrnum #& par.devDefenceTime=:devDefenceTime "
			+ "#& par.devDefenceTemplate=:devDefenceTemplate #& par.digitChannelId=:digitChannelId #& par.transMode=:transMode "
			+ "#& par.audioEnable=:audioEnable #& par.codeType=:codeType #& par.transStatus=:transStatus #& par.connectType=:connectType "
			+ "#& par.gbId=:gbId #& par.groupId=:groupId #& par.isOutland=:isOutland #& par.domainId=:domainId "
			+ "#& par.ipsanRecordState=:ipsanRecordState #& par.frontendRecordState=:frontendRecordState "
			+ "#& par.mapIconType=:mapIconType #& par.mapLng=:mapLng #& par.mapLat=:mapLat "
			+ "#& par.mapId=:mapId #& par.mapCustomName=:mapCustomName #& par.mapCoordsStr=:mapCoordsStr "
			+ "#& par.checkchannelId=:checkchannelId }", nativeQuery = true)
	public GridListData findDataList1(IQuery query, PageInfo pageInfo);
	//from SnDevice where devId = ?
	@Query(value="select *from SnPosition par where par.parentPosiId in ( :posiIds )", nativeQuery = true)
	public List<SnDevice> findSnPos();	
	//from SnVideoDvr where dvrId = ?
	
	@QmQuery(value="select * from SnDeviceRole par where par.roleId in ( :roleIds ) and par.nodeType= :nodeType and par.checkState= :checkState", nativeQuery = true)
	public GridListData findDataList2(IQuery query, PageInfo pageInfo);
	
	@Query(value="select *from SnDevice par where (par.posiId in ( :posiIds ) or par.devId in ( :devIds )) and par.devTypeId = :devTypeId", nativeQuery = true)	
	public List<SnDevice> findByPos(List<String> posiIds, List<String> devId, String nodeType);
	
	@Query(value="select *from SnDevice par where par.devId in ( :devIds ) and par.devTypeId = :devTypeId", nativeQuery = true)
	public List<SnDevice> findByDev(List<String> devIds, String nodeType);
	@Query(value="select *from SnDevice par where par.devId =:devId", nativeQuery = true)
	public SnDevice findByID(String devId);	
}
