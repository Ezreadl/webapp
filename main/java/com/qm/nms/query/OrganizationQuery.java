package com.qm.nms.query;

import com.qm.core.base.IQuery;

/**
 * 组织信息
 * 
 * @author lenovo
 *
 */
public class OrganizationQuery implements IQuery {
	private String organID;
	private String organName;
	private int organPreID;
	private String organDesc;
	private int typeVal;
	private String organType;
	private String organMgr;
	private String organmgrTel;
	private String locationUrl;

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

	public Integer getOrganPreID() {
		return organPreID;
	}

	public void setOrganPreID(Integer organPreID) {
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

	public void setOrganPreID(int organPreID) {
		this.organPreID = organPreID;
	}

	public void setTypeVal(int typeVal) {
		this.typeVal = typeVal;
	}

}
