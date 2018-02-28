package com.qm.sys.query;

import com.qm.core.base.IQuery;

public class LoginSolutionQuery implements IQuery{

	private String solutionName;
	
	private Integer delFlg;

	public String getSolutionName() {
		return solutionName;
	}

	public void setSolutionName(String solutionName) {
		this.solutionName = solutionName;
	}

	public Integer getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(Integer delFlg) {
		this.delFlg = delFlg;
	}
}
