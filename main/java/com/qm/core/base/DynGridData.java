package com.qm.core.base;

import java.util.List;

/**
 * 
 * 后台动态表数据，包括表头和列表数据,用于需要在后台获取表头的场景
 * @author wangchong
 */
public class DynGridData {
	//-1表示没有查询总数
	private long total=-1;
	
	@SuppressWarnings("rawtypes")
	private List titles;
		
	@SuppressWarnings("rawtypes")
	private List rows;
	
	public DynGridData() {
		super();
	}

	@SuppressWarnings("rawtypes")
	public DynGridData(List titles, List rows) {
		super();
		this.titles = titles;
		this.rows = rows;
	}

	@SuppressWarnings("rawtypes")
	public DynGridData(long total, List titles, List rows) {
		super();
		this.total = total;
		this.titles = titles;
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	@SuppressWarnings("rawtypes")
	public List getTitles() {
		return titles;
	}

	@SuppressWarnings("rawtypes")
	public void setTitles(List titles) {
		this.titles = titles;
	}

	@SuppressWarnings("rawtypes")
	public List getRows() {
		return rows;
	}

	@SuppressWarnings("rawtypes")
	public void setRows(List rows) {
		this.rows = rows;
	}
	
}
