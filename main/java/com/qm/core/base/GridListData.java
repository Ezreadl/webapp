package com.qm.core.base;

import java.util.List;

/**
 * 
 * @author wangchong
 *
 */
public class GridListData{
	
	//-1表示没有查询总数
	private long total;
	
	@SuppressWarnings("rawtypes")
	private List rows;

	public GridListData() {
		super();
	}
	
	@SuppressWarnings("rawtypes")
	public GridListData(long total, List rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	@SuppressWarnings("rawtypes")
	public List getRows() {
		return rows;
	}

	@SuppressWarnings("rawtypes")
	public void setRows(List rows) {
		this.rows = rows;
	}
	
	@Override
	public String toString() {
		return "GridListData [total=" + total + ", rows=" + rows + "]";
	}

}
