package com.qm.nms.domain;

import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sn_device", schema="", catalog="snms")
public class SnDevice
{
  private String devId;
  private String devName;
  private String devFirm;
  private String devIp;
  private String devPort;
  private String devUsername;
  private String devPassword;
  private String devSn;
  private String devLevel;
  private String devTypeId;
  private String devTypeChild;
  private String devInstallSite;
  private Timestamp devInstallDate;
  private Integer devEnable;
  private String devState;
  private Integer devDefence;
  private Integer repeatTime;
  private String posiId;
  private String parentDevId;
  private Integer priority;
  private Integer trueChannelCnt;
  private Integer channelCnt;
  private Integer trueIoInCnt;
  private Integer ioInCnt;
  private Integer trueIoOutCnt;
  private Integer ioOutCnt;
  private Integer channelStart;
  private String bizType;
  private String devNo;
  private Integer interfaceType;
  private String comPort;
  private Integer addressCode;
  private Integer baudrate;
  private Integer databit;
  private Integer paritybit;
  private Integer stopbit;
  private String mainCodeRate;
  private String mainFrameRate;
  private String mainDisplay;
  private String subCodeRate;
  private String subFrameRate;
  private String subDisplay;
  private String customId;
  private String manager;
  private String contact;
  private String reserve1;
  private String reserve2;
  private String reserve3;
  private Timestamp addTime;
  private Timestamp updateTime;
  private Integer isDel;
  private Integer dispscr;
  private Integer maxpixel;
  private Integer audiodsp;
  private String scrnum;
  private Timestamp devDefenceTime;
  private String devDefenceTemplate;
  private String digitChannelId;
  private Integer transMode;
  private Integer audioEnable;
  private Integer codeType;
  private Integer transStatus;
  private String connectType;
  private String gbId;
  private String groupId;
  private int isOutland;
  private String domainId;
  private String ipsanRecordState;
  private String frontendRecordState;
  private String mapIconType;
  private String mapLng;
  private String mapLat;
  private String mapId;
  private String mapCustomName;
  private String mapCoordsStr;
  private String checkchannelId;
  
  @Id
  @Column(name="dev_id")
  public String getDevId()
  {
    return this.devId;
  }
  
  public void setDevId(String devId)
  {
    this.devId = devId;
  }
  
  @Basic
  @Column(name="dev_name")
  public String getDevName()
  {
    return this.devName;
  }
  
  public void setDevName(String devName)
  {
    this.devName = devName;
  }
  
  @Basic
  @Column(name="dev_firm")
  public String getDevFirm()
  {
    return this.devFirm;
  }
  
  public void setDevFirm(String devFirm)
  {
    this.devFirm = devFirm;
  }
  
  @Basic
  @Column(name="dev_ip")
  public String getDevIp()
  {
    return this.devIp;
  }
  
  public void setDevIp(String devIp)
  {
    this.devIp = devIp;
  }
  
  @Basic
  @Column(name="dev_port")
  public String getDevPort()
  {
    return this.devPort;
  }
  
  public void setDevPort(String devPort)
  {
    this.devPort = devPort;
  }
  
  @Basic
  @Column(name="dev_username")
  public String getDevUsername()
  {
    return this.devUsername;
  }
  
  public void setDevUsername(String devUsername)
  {
    this.devUsername = devUsername;
  }
  
  @Basic
  @Column(name="dev_password")
  public String getDevPassword()
  {
    return this.devPassword;
  }
  
  public void setDevPassword(String devPassword)
  {
    this.devPassword = devPassword;
  }
  
  @Basic
  @Column(name="dev_sn")
  public String getDevSn()
  {
    return this.devSn;
  }
  
  public void setDevSn(String devSn)
  {
    this.devSn = devSn;
  }
  
  @Basic
  @Column(name="dev_level")
  public String getDevLevel()
  {
    return this.devLevel;
  }
  
  public void setDevLevel(String devLevel)
  {
    this.devLevel = devLevel;
  }
  
  @Basic
  @Column(name="dev_type_id")
  public String getDevTypeId()
  {
    return this.devTypeId;
  }
  
  public void setDevTypeId(String devTypeId)
  {
    this.devTypeId = devTypeId;
  }
  
  @Basic
  @Column(name="dev_type_child")
  public String getDevTypeChild()
  {
    return this.devTypeChild;
  }
  
  public void setDevTypeChild(String devTypeChild)
  {
    this.devTypeChild = devTypeChild;
  }
  
  @Basic
  @Column(name="dev_install_site")
  public String getDevInstallSite()
  {
    return this.devInstallSite;
  }
  
  public void setDevInstallSite(String devInstallSite)
  {
    this.devInstallSite = devInstallSite;
  }
  
  @Basic
  @Column(name="dev_install_date")
  public Timestamp getDevInstallDate()
  {
    return this.devInstallDate;
  }
  
  public void setDevInstallDate(Timestamp devInstallDate)
  {
    this.devInstallDate = devInstallDate;
  }
  
  @Basic
  @Column(name="dev_enable")
  public Integer getDevEnable()
  {
    return this.devEnable;
  }
  
  public void setDevEnable(Integer devEnable)
  {
    this.devEnable = devEnable;
  }
  
  @Basic
  @Column(name="dev_state")
  public String getDevState()
  {
    return this.devState;
  }
  
  public void setDevState(String devState)
  {
    this.devState = devState;
  }
  
  @Basic
  @Column(name="dev_defence")
  public Integer getDevDefence()
  {
    return this.devDefence;
  }
  
  public void setDevDefence(Integer devDefence)
  {
    this.devDefence = devDefence;
  }
  
  @Basic
  @Column(name="repeat_time")
  public Integer getRepeatTime()
  {
    return this.repeatTime;
  }
  
  public void setRepeatTime(Integer repeatTime)
  {
    this.repeatTime = repeatTime;
  }
  
  @Basic
  @Column(name="posi_id")
  public String getPosiId()
  {
    return this.posiId;
  }
  
  public void setPosiId(String posiId)
  {
    this.posiId = posiId;
  }
  
  @Basic
  @Column(name="parent_dev_id")
  public String getParentDevId()
  {
    return this.parentDevId;
  }
  
  public void setParentDevId(String parentDevId)
  {
    this.parentDevId = parentDevId;
  }
  
  @Basic
  @Column(name="priority")
  public Integer getPriority()
  {
    return this.priority;
  }
  
  public void setPriority(Integer priority)
  {
    this.priority = priority;
  }
  
  @Basic
  @Column(name="true_channel_cnt")
  public Integer getTrueChannelCnt()
  {
    return this.trueChannelCnt;
  }
  
  public void setTrueChannelCnt(Integer trueChannelCnt)
  {
    this.trueChannelCnt = trueChannelCnt;
  }
  
  @Basic
  @Column(name="channel_cnt")
  public Integer getChannelCnt()
  {
    return this.channelCnt;
  }
  
  public void setChannelCnt(Integer channelCnt)
  {
    this.channelCnt = channelCnt;
  }
  
  @Basic
  @Column(name="true_io_in_cnt")
  public Integer getTrueIoInCnt()
  {
    return this.trueIoInCnt;
  }
  
  public void setTrueIoInCnt(Integer trueIoInCnt)
  {
    this.trueIoInCnt = trueIoInCnt;
  }
  
  @Basic
  @Column(name="io_in_cnt")
  public Integer getIoInCnt()
  {
    return this.ioInCnt;
  }
  
  public void setIoInCnt(Integer ioInCnt)
  {
    this.ioInCnt = ioInCnt;
  }
  
  @Basic
  @Column(name="true_io_out_cnt")
  public Integer getTrueIoOutCnt()
  {
    return this.trueIoOutCnt;
  }
  
  public void setTrueIoOutCnt(Integer trueIoOutCnt)
  {
    this.trueIoOutCnt = trueIoOutCnt;
  }
  
  @Basic
  @Column(name="io_out_cnt")
  public Integer getIoOutCnt()
  {
    return this.ioOutCnt;
  }
  
  public void setIoOutCnt(Integer ioOutCnt)
  {
    this.ioOutCnt = ioOutCnt;
  }
  
  @Basic
  @Column(name="channel_start")
  public Integer getChannelStart()
  {
    return this.channelStart;
  }
  
  public void setChannelStart(Integer channelStart)
  {
    this.channelStart = channelStart;
  }
  
  @Basic
  @Column(name="bizType")
  public String getBizType()
  {
    return this.bizType;
  }
  
  public void setBizType(String bizType)
  {
    this.bizType = bizType;
  }
  
  @Basic
  @Column(name="dev_no")
  public String getDevNo()
  {
    return this.devNo;
  }
  
  public void setDevNo(String devNo)
  {
    this.devNo = devNo;
  }
  
  @Basic
  @Column(name="interface_type")
  public Integer getInterfaceType()
  {
    return this.interfaceType;
  }
  
  public void setInterfaceType(Integer interfaceType)
  {
    this.interfaceType = interfaceType;
  }
  
  @Basic
  @Column(name="comPort")
  public String getComPort()
  {
    return this.comPort;
  }
  
  public void setComPort(String comPort)
  {
    this.comPort = comPort;
  }
  
  @Basic
  @Column(name="addressCode")
  public Integer getAddressCode()
  {
    return this.addressCode;
  }
  
  public void setAddressCode(Integer addressCode)
  {
    this.addressCode = addressCode;
  }
  
  @Basic
  @Column(name="baudrate")
  public Integer getBaudrate()
  {
    return this.baudrate;
  }
  
  public void setBaudrate(Integer baudrate)
  {
    this.baudrate = baudrate;
  }
  
  @Basic
  @Column(name="databit")
  public Integer getDatabit()
  {
    return this.databit;
  }
  
  public void setDatabit(Integer databit)
  {
    this.databit = databit;
  }
  
  @Basic
  @Column(name="paritybit")
  public Integer getParitybit()
  {
    return this.paritybit;
  }
  
  public void setParitybit(Integer paritybit)
  {
    this.paritybit = paritybit;
  }
  
  @Basic
  @Column(name="stopbit")
  public Integer getStopbit()
  {
    return this.stopbit;
  }
  
  public void setStopbit(Integer stopbit)
  {
    this.stopbit = stopbit;
  }
  
  @Basic
  @Column(name="mainCodeRate")
  public String getMainCodeRate()
  {
    return this.mainCodeRate;
  }
  
  public void setMainCodeRate(String mainCodeRate)
  {
    this.mainCodeRate = mainCodeRate;
  }
  
  @Basic
  @Column(name="mainFrameRate")
  public String getMainFrameRate()
  {
    return this.mainFrameRate;
  }
  
  public void setMainFrameRate(String mainFrameRate)
  {
    this.mainFrameRate = mainFrameRate;
  }
  
  @Basic
  @Column(name="mainDisplay")
  public String getMainDisplay()
  {
    return this.mainDisplay;
  }
  
  public void setMainDisplay(String mainDisplay)
  {
    this.mainDisplay = mainDisplay;
  }
  
  @Basic
  @Column(name="subCodeRate")
  public String getSubCodeRate()
  {
    return this.subCodeRate;
  }
  
  public void setSubCodeRate(String subCodeRate)
  {
    this.subCodeRate = subCodeRate;
  }
  
  @Basic
  @Column(name="subFrameRate")
  public String getSubFrameRate()
  {
    return this.subFrameRate;
  }
  
  public void setSubFrameRate(String subFrameRate)
  {
    this.subFrameRate = subFrameRate;
  }
  
  @Basic
  @Column(name="subDisplay")
  public String getSubDisplay()
  {
    return this.subDisplay;
  }
  
  public void setSubDisplay(String subDisplay)
  {
    this.subDisplay = subDisplay;
  }
  
  @Basic
  @Column(name="custom_id")
  public String getCustomId()
  {
    return this.customId;
  }
  
  public void setCustomId(String customId)
  {
    this.customId = customId;
  }
  
  @Basic
  @Column(name="manager")
  public String getManager()
  {
    return this.manager;
  }
  
  public void setManager(String manager)
  {
    this.manager = manager;
  }
  
  @Basic
  @Column(name="contact")
  public String getContact()
  {
    return this.contact;
  }
  
  public void setContact(String contact)
  {
    this.contact = contact;
  }
  
  @Basic
  @Column(name="reserve1")
  public String getReserve1()
  {
    return this.reserve1;
  }
  
  public void setReserve1(String reserve1)
  {
    this.reserve1 = reserve1;
  }
  
  @Basic
  @Column(name="reserve2")
  public String getReserve2()
  {
    return this.reserve2;
  }
  
  public void setReserve2(String reserve2)
  {
    this.reserve2 = reserve2;
  }
  
  @Basic
  @Column(name="reserve3")
  public String getReserve3()
  {
    return this.reserve3;
  }
  
  public void setReserve3(String reserve3)
  {
    this.reserve3 = reserve3;
  }
  
  @Basic
  @Column(name="addTime")
  public Timestamp getAddTime()
  {
    return this.addTime;
  }
  
  public void setAddTime(Timestamp addTime)
  {
    this.addTime = addTime;
  }
  
  @Basic
  @Column(name="updateTime")
  public Timestamp getUpdateTime()
  {
    return this.updateTime;
  }
  
  public void setUpdateTime(Timestamp updateTime)
  {
    this.updateTime = updateTime;
  }
  
  @Basic
  @Column(name="isDel")
  public Integer getIsDel()
  {
    return this.isDel;
  }
  
  public void setIsDel(Integer isDel)
  {
    this.isDel = isDel;
  }
  
  @Basic
  @Column(name="dispscr")
  public Integer getDispscr()
  {
    return this.dispscr;
  }
  
  public void setDispscr(Integer dispscr)
  {
    this.dispscr = dispscr;
  }
  
  @Basic
  @Column(name="maxpixel")
  public Integer getMaxpixel()
  {
    return this.maxpixel;
  }
  
  public void setMaxpixel(Integer maxpixel)
  {
    this.maxpixel = maxpixel;
  }
  
  @Basic
  @Column(name="audiodsp")
  public Integer getAudiodsp()
  {
    return this.audiodsp;
  }
  
  public void setAudiodsp(Integer audiodsp)
  {
    this.audiodsp = audiodsp;
  }
  
  @Basic
  @Column(name="scrnum")
  public String getScrnum()
  {
    return this.scrnum;
  }
  
  public void setScrnum(String scrnum)
  {
    this.scrnum = scrnum;
  }
  
  @Basic
  @Column(name="dev_defence_time")
  public Timestamp getDevDefenceTime()
  {
    return this.devDefenceTime;
  }
  
  public void setDevDefenceTime(Timestamp devDefenceTime)
  {
    this.devDefenceTime = devDefenceTime;
  }
  
  @Basic
  @Column(name="dev_defence_template")
  public String getDevDefenceTemplate()
  {
    return this.devDefenceTemplate;
  }
  
  public void setDevDefenceTemplate(String devDefenceTemplate)
  {
    this.devDefenceTemplate = devDefenceTemplate;
  }
  
  @Basic
  @Column(name="digit_channel_id")
  public String getDigitChannelId()
  {
    return this.digitChannelId;
  }
  
  public void setDigitChannelId(String digitChannelId)
  {
    this.digitChannelId = digitChannelId;
  }
  
  @Basic
  @Column(name="trans_mode")
  public Integer getTransMode()
  {
    return this.transMode;
  }
  
  public void setTransMode(Integer transMode)
  {
    this.transMode = transMode;
  }
  
  @Basic
  @Column(name="audio_enable")
  public Integer getAudioEnable()
  {
    return this.audioEnable;
  }
  
  public void setAudioEnable(Integer audioEnable)
  {
    this.audioEnable = audioEnable;
  }
  
  @Basic
  @Column(name="code_type")
  public Integer getCodeType()
  {
    return this.codeType;
  }
  
  public void setCodeType(Integer codeType)
  {
    this.codeType = codeType;
  }
  
  @Basic
  @Column(name="trans_status")
  public Integer getTransStatus()
  {
    return this.transStatus;
  }
  
  public void setTransStatus(Integer transStatus)
  {
    this.transStatus = transStatus;
  }
  
  @Basic
  @Column(name="connect_type")
  public String getConnectType()
  {
    return this.connectType;
  }
  
  public void setConnectType(String connectType)
  {
    this.connectType = connectType;
  }
  
  @Basic
  @Column(name="gb_id")
  public String getGbId()
  {
    return this.gbId;
  }
  
  public void setGbId(String gbId)
  {
    this.gbId = gbId;
  }
  
  @Basic
  @Column(name="group_id")
  public String getGroupId()
  {
    return this.groupId;
  }
  
  public void setGroupId(String groupId)
  {
    this.groupId = groupId;
  }
  
  @Basic
  @Column(name="is_outland")
  public int getIsOutland()
  {
    return this.isOutland;
  }
  
  public void setIsOutland(int isOutland)
  {
    this.isOutland = isOutland;
  }
  
  @Basic
  @Column(name="domain_id")
  public String getDomainId()
  {
    return this.domainId;
  }
  
  public void setDomainId(String domainId)
  {
    this.domainId = domainId;
  }
  
  @Basic
  @Column(name="ipsan_record_state")
  public String getIpsanRecordState()
  {
    return this.ipsanRecordState;
  }
  
  public void setIpsanRecordState(String ipsanRecordState)
  {
    this.ipsanRecordState = ipsanRecordState;
  }
  
  @Basic
  @Column(name="frontend_record_state")
  public String getFrontendRecordState()
  {
    return this.frontendRecordState;
  }
  
  public void setFrontendRecordState(String frontendRecordState)
  {
    this.frontendRecordState = frontendRecordState;
  }
  
  @Basic
  @Column(name="map_icon_type")
  public String getMapIconType()
  {
    return this.mapIconType;
  }
  
  public void setMapIconType(String mapIconType)
  {
    this.mapIconType = mapIconType;
  }
  
  @Basic
  @Column(name="map_lng")
  public String getMapLng()
  {
    return this.mapLng;
  }
  
  public void setMapLng(String mapLng)
  {
    this.mapLng = mapLng;
  }
  
  @Basic
  @Column(name="map_lat")
  public String getMapLat()
  {
    return this.mapLat;
  }
  
  public void setMapLat(String mapLat)
  {
    this.mapLat = mapLat;
  }
  
  @Basic
  @Column(name="map_id")
  public String getMapId()
  {
    return this.mapId;
  }
  
  public void setMapId(String mapId)
  {
    this.mapId = mapId;
  }
  
  @Basic
  @Column(name="map_custom_name")
  public String getMapCustomName()
  {
    return this.mapCustomName;
  }
  
  public void setMapCustomName(String mapCustomName)
  {
    this.mapCustomName = mapCustomName;
  }
  
  @Basic
  @Column(name="map_coordsStr")
  public String getMapCoordsStr()
  {
    return this.mapCoordsStr;
  }
  
  public void setMapCoordsStr(String mapCoordsStr)
  {
    this.mapCoordsStr = mapCoordsStr;
  }
  
  @Basic
  @Column(name="checkchannel_id")
  public String getCheckchannelId()
  {
    return this.checkchannelId;
  }
  
  public void setCheckchannelId(String checkchannelId)
  {
    this.checkchannelId = checkchannelId;
  }
  
  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if ((o == null) || (getClass() != o.getClass())) {
      return false;
    }
    SnDevice snDevice = (SnDevice)o;
    if (this.isOutland != snDevice.isOutland) {
      return false;
    }
    if (this.addTime != null ? !this.addTime.equals(snDevice.addTime) : snDevice.addTime != null) {
      return false;
    }
    if (this.addressCode != null ? !this.addressCode.equals(snDevice.addressCode) : snDevice.addressCode != null) {
      return false;
    }
    if (this.audioEnable != null ? !this.audioEnable.equals(snDevice.audioEnable) : snDevice.audioEnable != null) {
      return false;
    }
    if (this.audiodsp != null ? !this.audiodsp.equals(snDevice.audiodsp) : snDevice.audiodsp != null) {
      return false;
    }
    if (this.baudrate != null ? !this.baudrate.equals(snDevice.baudrate) : snDevice.baudrate != null) {
      return false;
    }
    if (this.bizType != null ? !this.bizType.equals(snDevice.bizType) : snDevice.bizType != null) {
      return false;
    }
    if (this.channelCnt != null ? !this.channelCnt.equals(snDevice.channelCnt) : snDevice.channelCnt != null) {
      return false;
    }
    if (this.channelStart != null ? !this.channelStart.equals(snDevice.channelStart) : snDevice.channelStart != null) {
      return false;
    }
    if (this.checkchannelId != null ? !this.checkchannelId.equals(snDevice.checkchannelId) : snDevice.checkchannelId != null) {
      return false;
    }
    if (this.codeType != null ? !this.codeType.equals(snDevice.codeType) : snDevice.codeType != null) {
      return false;
    }
    if (this.comPort != null ? !this.comPort.equals(snDevice.comPort) : snDevice.comPort != null) {
      return false;
    }
    if (this.connectType != null ? !this.connectType.equals(snDevice.connectType) : snDevice.connectType != null) {
      return false;
    }
    if (this.contact != null ? !this.contact.equals(snDevice.contact) : snDevice.contact != null) {
      return false;
    }
    if (this.customId != null ? !this.customId.equals(snDevice.customId) : snDevice.customId != null) {
      return false;
    }
    if (this.databit != null ? !this.databit.equals(snDevice.databit) : snDevice.databit != null) {
      return false;
    }
    if (this.devDefence != null ? !this.devDefence.equals(snDevice.devDefence) : snDevice.devDefence != null) {
      return false;
    }
    if (this.devDefenceTemplate != null ? !this.devDefenceTemplate.equals(snDevice.devDefenceTemplate) : snDevice.devDefenceTemplate != null) {
      return false;
    }
    if (this.devDefenceTime != null ? !this.devDefenceTime.equals(snDevice.devDefenceTime) : snDevice.devDefenceTime != null) {
      return false;
    }
    if (this.devEnable != null ? !this.devEnable.equals(snDevice.devEnable) : snDevice.devEnable != null) {
      return false;
    }
    if (this.devFirm != null ? !this.devFirm.equals(snDevice.devFirm) : snDevice.devFirm != null) {
      return false;
    }
    if (this.devId != null ? !this.devId.equals(snDevice.devId) : snDevice.devId != null) {
      return false;
    }
    if (this.devInstallDate != null ? !this.devInstallDate.equals(snDevice.devInstallDate) : snDevice.devInstallDate != null) {
      return false;
    }
    if (this.devInstallSite != null ? !this.devInstallSite.equals(snDevice.devInstallSite) : snDevice.devInstallSite != null) {
      return false;
    }
    if (this.devIp != null ? !this.devIp.equals(snDevice.devIp) : snDevice.devIp != null) {
      return false;
    }
    if (this.devLevel != null ? !this.devLevel.equals(snDevice.devLevel) : snDevice.devLevel != null) {
      return false;
    }
    if (this.devName != null ? !this.devName.equals(snDevice.devName) : snDevice.devName != null) {
      return false;
    }
    if (this.devNo != null ? !this.devNo.equals(snDevice.devNo) : snDevice.devNo != null) {
      return false;
    }
    if (this.devPassword != null ? !this.devPassword.equals(snDevice.devPassword) : snDevice.devPassword != null) {
      return false;
    }
    if (this.devPort != null ? !this.devPort.equals(snDevice.devPort) : snDevice.devPort != null) {
      return false;
    }
    if (this.devSn != null ? !this.devSn.equals(snDevice.devSn) : snDevice.devSn != null) {
      return false;
    }
    if (this.devState != null ? !this.devState.equals(snDevice.devState) : snDevice.devState != null) {
      return false;
    }
    if (this.devTypeChild != null ? !this.devTypeChild.equals(snDevice.devTypeChild) : snDevice.devTypeChild != null) {
      return false;
    }
    if (this.devTypeId != null ? !this.devTypeId.equals(snDevice.devTypeId) : snDevice.devTypeId != null) {
      return false;
    }
    if (this.devUsername != null ? !this.devUsername.equals(snDevice.devUsername) : snDevice.devUsername != null) {
      return false;
    }
    if (this.digitChannelId != null ? !this.digitChannelId.equals(snDevice.digitChannelId) : snDevice.digitChannelId != null) {
      return false;
    }
    if (this.dispscr != null ? !this.dispscr.equals(snDevice.dispscr) : snDevice.dispscr != null) {
      return false;
    }
    if (this.domainId != null ? !this.domainId.equals(snDevice.domainId) : snDevice.domainId != null) {
      return false;
    }
    if (this.frontendRecordState != null ? !this.frontendRecordState.equals(snDevice.frontendRecordState) : snDevice.frontendRecordState != null) {
      return false;
    }
    if (this.gbId != null ? !this.gbId.equals(snDevice.gbId) : snDevice.gbId != null) {
      return false;
    }
    if (this.groupId != null ? !this.groupId.equals(snDevice.groupId) : snDevice.groupId != null) {
      return false;
    }
    if (this.interfaceType != null ? !this.interfaceType.equals(snDevice.interfaceType) : snDevice.interfaceType != null) {
      return false;
    }
    if (this.ioInCnt != null ? !this.ioInCnt.equals(snDevice.ioInCnt) : snDevice.ioInCnt != null) {
      return false;
    }
    if (this.ioOutCnt != null ? !this.ioOutCnt.equals(snDevice.ioOutCnt) : snDevice.ioOutCnt != null) {
      return false;
    }
    if (this.ipsanRecordState != null ? !this.ipsanRecordState.equals(snDevice.ipsanRecordState) : snDevice.ipsanRecordState != null) {
      return false;
    }
    if (this.isDel != null ? !this.isDel.equals(snDevice.isDel) : snDevice.isDel != null) {
      return false;
    }
    if (this.mainCodeRate != null ? !this.mainCodeRate.equals(snDevice.mainCodeRate) : snDevice.mainCodeRate != null) {
      return false;
    }
    if (this.mainDisplay != null ? !this.mainDisplay.equals(snDevice.mainDisplay) : snDevice.mainDisplay != null) {
      return false;
    }
    if (this.mainFrameRate != null ? !this.mainFrameRate.equals(snDevice.mainFrameRate) : snDevice.mainFrameRate != null) {
      return false;
    }
    if (this.manager != null ? !this.manager.equals(snDevice.manager) : snDevice.manager != null) {
      return false;
    }
    if (this.mapCoordsStr != null ? !this.mapCoordsStr.equals(snDevice.mapCoordsStr) : snDevice.mapCoordsStr != null) {
      return false;
    }
    if (this.mapCustomName != null ? !this.mapCustomName.equals(snDevice.mapCustomName) : snDevice.mapCustomName != null) {
      return false;
    }
    if (this.mapIconType != null ? !this.mapIconType.equals(snDevice.mapIconType) : snDevice.mapIconType != null) {
      return false;
    }
    if (this.mapId != null ? !this.mapId.equals(snDevice.mapId) : snDevice.mapId != null) {
      return false;
    }
    if (this.mapLat != null ? !this.mapLat.equals(snDevice.mapLat) : snDevice.mapLat != null) {
      return false;
    }
    if (this.mapLng != null ? !this.mapLng.equals(snDevice.mapLng) : snDevice.mapLng != null) {
      return false;
    }
    if (this.maxpixel != null ? !this.maxpixel.equals(snDevice.maxpixel) : snDevice.maxpixel != null) {
      return false;
    }
    if (this.parentDevId != null ? !this.parentDevId.equals(snDevice.parentDevId) : snDevice.parentDevId != null) {
      return false;
    }
    if (this.paritybit != null ? !this.paritybit.equals(snDevice.paritybit) : snDevice.paritybit != null) {
      return false;
    }
    if (this.posiId != null ? !this.posiId.equals(snDevice.posiId) : snDevice.posiId != null) {
      return false;
    }
    if (this.priority != null ? !this.priority.equals(snDevice.priority) : snDevice.priority != null) {
      return false;
    }
    if (this.repeatTime != null ? !this.repeatTime.equals(snDevice.repeatTime) : snDevice.repeatTime != null) {
      return false;
    }
    if (this.reserve1 != null ? !this.reserve1.equals(snDevice.reserve1) : snDevice.reserve1 != null) {
      return false;
    }
    if (this.reserve2 != null ? !this.reserve2.equals(snDevice.reserve2) : snDevice.reserve2 != null) {
      return false;
    }
    if (this.reserve3 != null ? !this.reserve3.equals(snDevice.reserve3) : snDevice.reserve3 != null) {
      return false;
    }
    if (this.scrnum != null ? !this.scrnum.equals(snDevice.scrnum) : snDevice.scrnum != null) {
      return false;
    }
    if (this.stopbit != null ? !this.stopbit.equals(snDevice.stopbit) : snDevice.stopbit != null) {
      return false;
    }
    if (this.subCodeRate != null ? !this.subCodeRate.equals(snDevice.subCodeRate) : snDevice.subCodeRate != null) {
      return false;
    }
    if (this.subDisplay != null ? !this.subDisplay.equals(snDevice.subDisplay) : snDevice.subDisplay != null) {
      return false;
    }
    if (this.subFrameRate != null ? !this.subFrameRate.equals(snDevice.subFrameRate) : snDevice.subFrameRate != null) {
      return false;
    }
    if (this.transMode != null ? !this.transMode.equals(snDevice.transMode) : snDevice.transMode != null) {
      return false;
    }
    if (this.transStatus != null ? !this.transStatus.equals(snDevice.transStatus) : snDevice.transStatus != null) {
      return false;
    }
    if (this.trueChannelCnt != null ? !this.trueChannelCnt.equals(snDevice.trueChannelCnt) : snDevice.trueChannelCnt != null) {
      return false;
    }
    if (this.trueIoInCnt != null ? !this.trueIoInCnt.equals(snDevice.trueIoInCnt) : snDevice.trueIoInCnt != null) {
      return false;
    }
    if (this.trueIoOutCnt != null ? !this.trueIoOutCnt.equals(snDevice.trueIoOutCnt) : snDevice.trueIoOutCnt != null) {
      return false;
    }
    if (this.updateTime != null ? !this.updateTime.equals(snDevice.updateTime) : snDevice.updateTime != null) {
      return false;
    }
    return true;
  }
  
  public int hashCode()
  {
    int result = this.devId != null ? this.devId.hashCode() : 0;
    result = 31 * result + (this.devName != null ? this.devName.hashCode() : 0);
    result = 31 * result + (this.devFirm != null ? this.devFirm.hashCode() : 0);
    result = 31 * result + (this.devIp != null ? this.devIp.hashCode() : 0);
    result = 31 * result + (this.devPort != null ? this.devPort.hashCode() : 0);
    result = 31 * result + (this.devUsername != null ? this.devUsername.hashCode() : 0);
    result = 31 * result + (this.devPassword != null ? this.devPassword.hashCode() : 0);
    result = 31 * result + (this.devSn != null ? this.devSn.hashCode() : 0);
    result = 31 * result + (this.devLevel != null ? this.devLevel.hashCode() : 0);
    result = 31 * result + (this.devTypeId != null ? this.devTypeId.hashCode() : 0);
    result = 31 * result + (this.devTypeChild != null ? this.devTypeChild.hashCode() : 0);
    result = 31 * result + (this.devInstallSite != null ? this.devInstallSite.hashCode() : 0);
    result = 31 * result + (this.devInstallDate != null ? this.devInstallDate.hashCode() : 0);
    result = 31 * result + (this.devEnable != null ? this.devEnable.hashCode() : 0);
    result = 31 * result + (this.devState != null ? this.devState.hashCode() : 0);
    result = 31 * result + (this.devDefence != null ? this.devDefence.hashCode() : 0);
    result = 31 * result + (this.repeatTime != null ? this.repeatTime.hashCode() : 0);
    result = 31 * result + (this.posiId != null ? this.posiId.hashCode() : 0);
    result = 31 * result + (this.parentDevId != null ? this.parentDevId.hashCode() : 0);
    result = 31 * result + (this.priority != null ? this.priority.hashCode() : 0);
    result = 31 * result + (this.trueChannelCnt != null ? this.trueChannelCnt.hashCode() : 0);
    result = 31 * result + (this.channelCnt != null ? this.channelCnt.hashCode() : 0);
    result = 31 * result + (this.trueIoInCnt != null ? this.trueIoInCnt.hashCode() : 0);
    result = 31 * result + (this.ioInCnt != null ? this.ioInCnt.hashCode() : 0);
    result = 31 * result + (this.trueIoOutCnt != null ? this.trueIoOutCnt.hashCode() : 0);
    result = 31 * result + (this.ioOutCnt != null ? this.ioOutCnt.hashCode() : 0);
    result = 31 * result + (this.channelStart != null ? this.channelStart.hashCode() : 0);
    result = 31 * result + (this.bizType != null ? this.bizType.hashCode() : 0);
    result = 31 * result + (this.devNo != null ? this.devNo.hashCode() : 0);
    result = 31 * result + (this.interfaceType != null ? this.interfaceType.hashCode() : 0);
    result = 31 * result + (this.comPort != null ? this.comPort.hashCode() : 0);
    result = 31 * result + (this.addressCode != null ? this.addressCode.hashCode() : 0);
    result = 31 * result + (this.baudrate != null ? this.baudrate.hashCode() : 0);
    result = 31 * result + (this.databit != null ? this.databit.hashCode() : 0);
    result = 31 * result + (this.paritybit != null ? this.paritybit.hashCode() : 0);
    result = 31 * result + (this.stopbit != null ? this.stopbit.hashCode() : 0);
    result = 31 * result + (this.mainCodeRate != null ? this.mainCodeRate.hashCode() : 0);
    result = 31 * result + (this.mainFrameRate != null ? this.mainFrameRate.hashCode() : 0);
    result = 31 * result + (this.mainDisplay != null ? this.mainDisplay.hashCode() : 0);
    result = 31 * result + (this.subCodeRate != null ? this.subCodeRate.hashCode() : 0);
    result = 31 * result + (this.subFrameRate != null ? this.subFrameRate.hashCode() : 0);
    result = 31 * result + (this.subDisplay != null ? this.subDisplay.hashCode() : 0);
    result = 31 * result + (this.customId != null ? this.customId.hashCode() : 0);
    result = 31 * result + (this.manager != null ? this.manager.hashCode() : 0);
    result = 31 * result + (this.contact != null ? this.contact.hashCode() : 0);
    result = 31 * result + (this.reserve1 != null ? this.reserve1.hashCode() : 0);
    result = 31 * result + (this.reserve2 != null ? this.reserve2.hashCode() : 0);
    result = 31 * result + (this.reserve3 != null ? this.reserve3.hashCode() : 0);
    result = 31 * result + (this.addTime != null ? this.addTime.hashCode() : 0);
    result = 31 * result + (this.updateTime != null ? this.updateTime.hashCode() : 0);
    result = 31 * result + (this.isDel != null ? this.isDel.hashCode() : 0);
    result = 31 * result + (this.dispscr != null ? this.dispscr.hashCode() : 0);
    result = 31 * result + (this.maxpixel != null ? this.maxpixel.hashCode() : 0);
    result = 31 * result + (this.audiodsp != null ? this.audiodsp.hashCode() : 0);
    result = 31 * result + (this.scrnum != null ? this.scrnum.hashCode() : 0);
    result = 31 * result + (this.devDefenceTime != null ? this.devDefenceTime.hashCode() : 0);
    result = 31 * result + (this.devDefenceTemplate != null ? this.devDefenceTemplate.hashCode() : 0);
    result = 31 * result + (this.digitChannelId != null ? this.digitChannelId.hashCode() : 0);
    result = 31 * result + (this.transMode != null ? this.transMode.hashCode() : 0);
    result = 31 * result + (this.audioEnable != null ? this.audioEnable.hashCode() : 0);
    result = 31 * result + (this.codeType != null ? this.codeType.hashCode() : 0);
    result = 31 * result + (this.transStatus != null ? this.transStatus.hashCode() : 0);
    result = 31 * result + (this.connectType != null ? this.connectType.hashCode() : 0);
    result = 31 * result + (this.gbId != null ? this.gbId.hashCode() : 0);
    result = 31 * result + (this.groupId != null ? this.groupId.hashCode() : 0);
    result = 31 * result + this.isOutland;
    result = 31 * result + (this.domainId != null ? this.domainId.hashCode() : 0);
    result = 31 * result + (this.ipsanRecordState != null ? this.ipsanRecordState.hashCode() : 0);
    result = 31 * result + (this.frontendRecordState != null ? this.frontendRecordState.hashCode() : 0);
    result = 31 * result + (this.mapIconType != null ? this.mapIconType.hashCode() : 0);
    result = 31 * result + (this.mapLng != null ? this.mapLng.hashCode() : 0);
    result = 31 * result + (this.mapLat != null ? this.mapLat.hashCode() : 0);
    result = 31 * result + (this.mapId != null ? this.mapId.hashCode() : 0);
    result = 31 * result + (this.mapCustomName != null ? this.mapCustomName.hashCode() : 0);
    result = 31 * result + (this.mapCoordsStr != null ? this.mapCoordsStr.hashCode() : 0);
    result = 31 * result + (this.checkchannelId != null ? this.checkchannelId.hashCode() : 0);
    return result;
  }
}
