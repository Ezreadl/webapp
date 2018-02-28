package com.qm.core.base;

/**
 * web请求返回状态
 * @author wangchong
 * @date
 */

public class WebStatus {
	
	private boolean success=true;
	
	private int state=GlobalCfg.OPT_CODE_SUCESS;
	
	private String msg="操作成功";
	
	private Object data="";
	
	public WebStatus() {
		super();
	}
	
	public WebStatus(Object data) {
		super();
		this.data = data;
	}
	public WebStatus(Exception e) {
		this.success = false;
		this.state = GlobalCfg.OPT_CODE_EXCEPTION;
		this.msg = "操作失败\n"+e.getMessage();
	}
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public WebStatus(boolean success, int state, String msg) {
		super();
		this.success = success;
		this.state = state;
		this.msg = msg;
	}
	
	public WebStatus success(){
		this.success = true;
		this.state = GlobalCfg.OPT_CODE_SUCESS;
		this.msg = "操作成功";
		return this;
	}
	
	public WebStatus failure(){
		this.success = false;
		this.state = GlobalCfg.OPT_CODE_EXCEPTION;
		this.msg = "操作失败";
		return this;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
