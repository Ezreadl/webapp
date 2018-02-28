package com.qm.nms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.qm.core.base.BaseDomCfg;
/**
 * 组织信息
 * @author lenovo
 *
 */
@SuppressWarnings("serial")
@Entity public class Organization extends BaseDomCfg{
	@Column private String organID;
	@Column private String organName;
	@Column private int organPreID;
	@Column private String organDesc;
	@Column private int typeVal;
	@Column private String organType;
	@Column private String organMgr;
	@Column private String organmgrTel;
	@Column private String locationUrl;
	public String getOrganID() {
		return organID;
	}
	public void setOrganID(String organID) {
		this.organID = organID;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public int getOrganPreID() {
		return organPreID;
	}
	public void setOrganPreID(int organPreID) {
		this.organPreID = organPreID;
	}
	public String getOrganDesc() {
		return organDesc;
	}
	public void setOrganDesc(String organDesc) {
		this.organDesc = organDesc;
	}
	public String getOrganType() {
		return organType;
	}
	public void setOrganType(String organType) {
		this.organType = organType;
	}
	public String getOrganMgr() {
		return organMgr;
	}
	public void setOrganMgr(String organMgr) {
		this.organMgr = organMgr;
	}
	public String getOrganmgrTel() {
		return organmgrTel;
	}
	public void setOrganmgrTel(String organmgrTel) {
		this.organmgrTel = organmgrTel;
	}
	public String getLocationUrl() {
		return locationUrl;
	}
	public void setLocationUrl(String locationUrl) {
		this.locationUrl = locationUrl;
	}
	public int getTypeVal() {
		return typeVal;
	}
	public void setTypeVal(int typeVal) {
		this.typeVal = typeVal;
	}
	  
}
