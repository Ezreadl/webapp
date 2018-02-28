package com.qm.core.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * web请求返回状态
 * @author wangchong
 * @date
 */

public class WebStatus4P {
	private int code = 0;
	
	private long count = 0;
	
	private String msg="操作成功";
	
	private Object data="";
	public WebStatus4P(){
		super();
	}
	public WebStatus4P(GridListData grid) {
		this.count = grid.getTotal();
		this.setData(grid.getRows());
	}
	
	public WebStatus4P(List grid) {
		this.count = grid.size();
		this.setData(grid);
	}	
	public WebStatus4P(Map<String,List<Map<String, Object>>> grid) {
		this.count = 1;
		this.setData(grid);
	}		
	public WebStatus4P(boolean code,String msg) {
		super();
		this.code = 0;
	}
	
	public WebStatus4P success(){
		this.code = 0;
		this.msg = "操作成功";
		return this;
	}
	
	public WebStatus4P failure(){
		this.code = 1;
		this.msg = "操作失败";
		return this;
	}
	public int getCode() {
		return code;
	}

	public long getCount() {
		return count;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public String getMsg() {
		return msg;
	}

	public Object getData() {
		return data;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setData(Object data) {
		this.data = data;
	}

	
}
