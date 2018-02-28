package com.qm.nms.query;

import com.qm.core.base.IQuery;

public class LabLessonPlanQuery implements IQuery {
	
	private String plan_id;
	
	private String level;

	public String getPlan_id() {
		return plan_id;
	}

	public String getLevel() {
		return level;
	}

	public void setPlan_id(String plan_id) {
		this.plan_id = plan_id;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
