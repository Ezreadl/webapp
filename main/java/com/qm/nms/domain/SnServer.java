package com.qm.nms.domain;
import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sn_server", schema = "", catalog = "snms")
public class SnServer {
	private String serverId;
	private String serverTypeId;
	private String serverName;
	private String serverIp;
	private String serverPort;
	private String remark;
	private Integer loadNum;
	private String serverParentId;
	private Integer enable;
	private Integer serverState;
	private Timestamp serverUpdateTime;
	private Integer channelLinkNumber;
	private Integer clientLinkNumber;
	private Integer deviceLinkNumber;
	private Integer dataFlow;
	private Double trafficObtainAlarm;
	private Double trafficIssueAlarm;
	private Double cpuLoadFactorAlarm;
	private Double memoryUsedFactorAlarm;
	private String version;
	private String gbId;
	private String posiId;
	private Integer isdel;

	@Id
	@Column(name = "server_id")
	public String getServerId() {
		return this.serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	@Basic
	@Column(name = "server_type_id")
	public String getServerTypeId() {
		return this.serverTypeId;
	}

	public void setServerTypeId(String serverTypeId) {
		this.serverTypeId = serverTypeId;
	}

	@Basic
	@Column(name = "server_name")
	public String getServerName() {
		return this.serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	@Basic
	@Column(name = "server_ip")
	public String getServerIp() {
		return this.serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	@Basic
	@Column(name = "server_port")
	public String getServerPort() {
		return this.serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	@Basic
	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Basic
	@Column(name = "load_num")
	public Integer getLoadNum() {
		return this.loadNum;
	}

	public void setLoadNum(Integer loadNum) {
		this.loadNum = loadNum;
	}

	@Basic
	@Column(name = "server_parent_id")
	public String getServerParentId() {
		return this.serverParentId;
	}

	public void setServerParentId(String serverParentId) {
		this.serverParentId = serverParentId;
	}

	@Basic
	@Column(name = "enable")
	public Integer getEnable() {
		return this.enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	@Basic
	@Column(name = "server_state")
	public Integer getServerState() {
		return this.serverState;
	}

	public void setServerState(Integer serverState) {
		this.serverState = serverState;
	}

	@Basic
	@Column(name = "server_update_time")
	public Timestamp getServerUpdateTime() {
		return this.serverUpdateTime;
	}

	public void setServerUpdateTime(Timestamp serverUpdateTime) {
		this.serverUpdateTime = serverUpdateTime;
	}

	@Basic
	@Column(name = "channel_link_number")
	public Integer getChannelLinkNumber() {
		return this.channelLinkNumber;
	}

	public void setChannelLinkNumber(Integer channelLinkNumber) {
		this.channelLinkNumber = channelLinkNumber;
	}

	@Basic
	@Column(name = "client_link_number")
	public Integer getClientLinkNumber() {
		return this.clientLinkNumber;
	}

	public void setClientLinkNumber(Integer clientLinkNumber) {
		this.clientLinkNumber = clientLinkNumber;
	}

	@Basic
	@Column(name = "device_link_number")
	public Integer getDeviceLinkNumber() {
		return this.deviceLinkNumber;
	}

	public void setDeviceLinkNumber(Integer deviceLinkNumber) {
		this.deviceLinkNumber = deviceLinkNumber;
	}

	@Basic
	@Column(name = "data_flow")
	public Integer getDataFlow() {
		return this.dataFlow;
	}

	public void setDataFlow(Integer dataFlow) {
		this.dataFlow = dataFlow;
	}

	@Basic
	@Column(name = "traffic_obtain_alarm")
	public Double getTrafficObtainAlarm() {
		return this.trafficObtainAlarm;
	}

	public void setTrafficObtainAlarm(Double trafficObtainAlarm) {
		this.trafficObtainAlarm = trafficObtainAlarm;
	}

	@Basic
	@Column(name = "traffic_issue_alarm")
	public Double getTrafficIssueAlarm() {
		return this.trafficIssueAlarm;
	}

	public void setTrafficIssueAlarm(Double trafficIssueAlarm) {
		this.trafficIssueAlarm = trafficIssueAlarm;
	}

	@Basic
	@Column(name = "cpu_load_factor_alarm")
	public Double getCpuLoadFactorAlarm() {
		return this.cpuLoadFactorAlarm;
	}

	public void setCpuLoadFactorAlarm(Double cpuLoadFactorAlarm) {
		this.cpuLoadFactorAlarm = cpuLoadFactorAlarm;
	}

	@Basic
	@Column(name = "memory_used_factor_alarm")
	public Double getMemoryUsedFactorAlarm() {
		return this.memoryUsedFactorAlarm;
	}

	public void setMemoryUsedFactorAlarm(Double memoryUsedFactorAlarm) {
		this.memoryUsedFactorAlarm = memoryUsedFactorAlarm;
	}

	@Basic
	@Column(name = "version")
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Basic
	@Column(name = "gb_id")
	public String getGbId() {
		return this.gbId;
	}

	public void setGbId(String gbId) {
		this.gbId = gbId;
	}

	@Basic
	@Column(name = "posi_id")
	public String getPosiId() {
		return this.posiId;
	}

	public void setPosiId(String posiId) {
		this.posiId = posiId;
	}

	@Basic
	@Column(name = "isdel")
	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		SnServer snServer = (SnServer) o;
		if (this.channelLinkNumber != null ? !this.channelLinkNumber.equals(snServer.channelLinkNumber)
				: snServer.channelLinkNumber != null) {
			return false;
		}
		if (this.clientLinkNumber != null ? !this.clientLinkNumber.equals(snServer.clientLinkNumber)
				: snServer.clientLinkNumber != null) {
			return false;
		}
		if (this.cpuLoadFactorAlarm != null ? !this.cpuLoadFactorAlarm.equals(snServer.cpuLoadFactorAlarm)
				: snServer.cpuLoadFactorAlarm != null) {
			return false;
		}
		if (this.dataFlow != null ? !this.dataFlow.equals(snServer.dataFlow) : snServer.dataFlow != null) {
			return false;
		}
		if (this.deviceLinkNumber != null ? !this.deviceLinkNumber.equals(snServer.deviceLinkNumber)
				: snServer.deviceLinkNumber != null) {
			return false;
		}
		if (this.enable != null ? !this.enable.equals(snServer.enable) : snServer.enable != null) {
			return false;
		}
		if (this.gbId != null ? !this.gbId.equals(snServer.gbId) : snServer.gbId != null) {
			return false;
		}
		if (this.isdel != null ? !this.isdel.equals(snServer.isdel) : snServer.isdel != null) {
			return false;
		}
		if (this.loadNum != null ? !this.loadNum.equals(snServer.loadNum) : snServer.loadNum != null) {
			return false;
		}
		if (this.memoryUsedFactorAlarm != null ? !this.memoryUsedFactorAlarm.equals(snServer.memoryUsedFactorAlarm)
				: snServer.memoryUsedFactorAlarm != null) {
			return false;
		}
		if (this.posiId != null ? !this.posiId.equals(snServer.posiId) : snServer.posiId != null) {
			return false;
		}
		if (this.remark != null ? !this.remark.equals(snServer.remark) : snServer.remark != null) {
			return false;
		}
		if (this.serverId != null ? !this.serverId.equals(snServer.serverId) : snServer.serverId != null) {
			return false;
		}
		if (this.serverIp != null ? !this.serverIp.equals(snServer.serverIp) : snServer.serverIp != null) {
			return false;
		}
		if (this.serverName != null ? !this.serverName.equals(snServer.serverName) : snServer.serverName != null) {
			return false;
		}
		if (this.serverParentId != null ? !this.serverParentId.equals(snServer.serverParentId)
				: snServer.serverParentId != null) {
			return false;
		}
		if (this.serverPort != null ? !this.serverPort.equals(snServer.serverPort) : snServer.serverPort != null) {
			return false;
		}
		if (this.serverState != null ? !this.serverState.equals(snServer.serverState) : snServer.serverState != null) {
			return false;
		}
		if (this.serverTypeId != null ? !this.serverTypeId.equals(snServer.serverTypeId)
				: snServer.serverTypeId != null) {
			return false;
		}
		if (this.serverUpdateTime != null ? !this.serverUpdateTime.equals(snServer.serverUpdateTime)
				: snServer.serverUpdateTime != null) {
			return false;
		}
		if (this.trafficIssueAlarm != null ? !this.trafficIssueAlarm.equals(snServer.trafficIssueAlarm)
				: snServer.trafficIssueAlarm != null) {
			return false;
		}
		if (this.trafficObtainAlarm != null ? !this.trafficObtainAlarm.equals(snServer.trafficObtainAlarm)
				: snServer.trafficObtainAlarm != null) {
			return false;
		}
		if (this.version != null ? !this.version.equals(snServer.version) : snServer.version != null) {
			return false;
		}
		return true;
	}

	public int hashCode() {
		int result = this.serverId != null ? this.serverId.hashCode() : 0;
		result = 31 * result + (this.serverTypeId != null ? this.serverTypeId.hashCode() : 0);
		result = 31 * result + (this.serverName != null ? this.serverName.hashCode() : 0);
		result = 31 * result + (this.serverIp != null ? this.serverIp.hashCode() : 0);
		result = 31 * result + (this.serverPort != null ? this.serverPort.hashCode() : 0);
		result = 31 * result + (this.remark != null ? this.remark.hashCode() : 0);
		result = 31 * result + (this.loadNum != null ? this.loadNum.hashCode() : 0);
		result = 31 * result + (this.serverParentId != null ? this.serverParentId.hashCode() : 0);
		result = 31 * result + (this.enable != null ? this.enable.hashCode() : 0);
		result = 31 * result + (this.serverState != null ? this.serverState.hashCode() : 0);
		result = 31 * result + (this.serverUpdateTime != null ? this.serverUpdateTime.hashCode() : 0);
		result = 31 * result + (this.channelLinkNumber != null ? this.channelLinkNumber.hashCode() : 0);
		result = 31 * result + (this.clientLinkNumber != null ? this.clientLinkNumber.hashCode() : 0);
		result = 31 * result + (this.deviceLinkNumber != null ? this.deviceLinkNumber.hashCode() : 0);
		result = 31 * result + (this.dataFlow != null ? this.dataFlow.hashCode() : 0);
		result = 31 * result + (this.trafficObtainAlarm != null ? this.trafficObtainAlarm.hashCode() : 0);
		result = 31 * result + (this.trafficIssueAlarm != null ? this.trafficIssueAlarm.hashCode() : 0);
		result = 31 * result + (this.cpuLoadFactorAlarm != null ? this.cpuLoadFactorAlarm.hashCode() : 0);
		result = 31 * result + (this.memoryUsedFactorAlarm != null ? this.memoryUsedFactorAlarm.hashCode() : 0);
		result = 31 * result + (this.version != null ? this.version.hashCode() : 0);
		result = 31 * result + (this.gbId != null ? this.gbId.hashCode() : 0);
		result = 31 * result + (this.posiId != null ? this.posiId.hashCode() : 0);
		result = 31 * result + (this.isdel != null ? this.isdel.hashCode() : 0);
		return result;
	}
}
