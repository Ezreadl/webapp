package com.qm.sys.query;

import com.qm.core.base.IQuery;

public class LoginSolutionDetailQuery implements IQuery{
	
	private Integer userType;
	
	private Integer solutionId;

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getSolutionId() {
		return solutionId;
	}

	public void setSolutionId(Integer solutionId) {
		this.solutionId = solutionId;
	}

}
