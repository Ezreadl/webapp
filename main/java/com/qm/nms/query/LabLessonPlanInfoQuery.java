package com.qm.nms.query;

import com.qm.core.base.IQuery;
import com.qm.nms.domain.LabType;
import com.qm.nms.domainEnum.CommonStatusEnum;
import com.qm.nms.domainEnum.TeachVersionEnum;

public class LabLessonPlanInfoQuery implements IQuery {
	private long id;
	private String name;
	private LabType schoolType;
	private CommonStatusEnum status;
	private TeachVersionEnum version;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LabType getSchoolType() {
		return this.schoolType;
	}

	public void setSchoolType(LabType schoolType) {
		this.schoolType = schoolType;
	}

	public CommonStatusEnum getStatus() {
		return this.status;
	}

	public void setStatus(CommonStatusEnum status) {
		this.status = status;
	}

	public TeachVersionEnum getVersion() {
		return this.version;
	}

	public void setVersion(TeachVersionEnum version) {
		this.version = version;
	}
}
