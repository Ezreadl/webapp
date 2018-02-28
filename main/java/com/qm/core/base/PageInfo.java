package com.qm.core.base;

/**
 * 分页参数信息
 * @author wangchong
 *
 */
public class PageInfo {
	/**
	 * 系统列表分页开始位置（null表示不设置开始行）
	 */
	protected Integer start=0;
	/**
	 * 第几页（null表示不设置第几页）
	 */
	protected Integer page=0;

	/**
	 * 系统列表分页限制大小（null表示不设置分页大小）
	 */
	protected Integer limit=30;
	
	/**
	 * 排序字段名称（null或""表示不设置排序字段）
	 */
	protected String sort="";
	/**
	 * 排序方式（null或""表示不设置排序顺序）
	 */
	protected String dir="";
	
	/**
	 * 是否查询列表的记录总数，默认是
	 */
	private boolean findTotal=true;
	
	public PageInfo() {
		super();
	}

	/**
	 * 设置分页条件，不排序
	 * @param start
	 * @param limit
	 */
	public PageInfo(Integer start, Integer limit) {
		super();
		this.start = start;
		this.limit = limit;
	}

	/**
	 * 设置排序条件，不分页
	 * @param sort
	 * @param dir
	 */
	public PageInfo(String sort, String dir) {
		super();
		this.start=null;
		this.limit=null;
		this.sort = sort;
		this.dir = dir;
	}

	/**
	 * 设置分页条件和排序信息
	 * @param start
	 * @param limit
	 * @param sort
	 * @param dir
	 */
	public PageInfo(Integer start, Integer limit, String sort, String dir) {
		super();
		this.start = start;
		this.limit = limit;
		this.sort = sort;
		this.dir = dir;
	}

	/**
	 * 设置分页条件和排序信息，以及是否查询总条目数
	 * @param start
	 * @param limit
	 * @param sort
	 * @param dir
	 * @param findTotal
	 */
	public PageInfo(Integer start, Integer limit, String sort, String dir, boolean findTotal) {
		super();
		this.start = start;
		this.limit = limit;
		this.sort = sort;
		this.dir = dir;
		this.findTotal = findTotal;
	}
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		start=(page-1)*limit;
		this.page = page;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public boolean isFindTotal() {
		return findTotal;
	}

	public void setFindTotal(boolean findTotal) {
		this.findTotal = findTotal;
	}

	@Override
	public String toString() {
		return "PageInfo [start=" + start + ", limit=" + limit + ", sort=" + sort + ", dir=" + dir + ", findTotal="
				+ findTotal + "]";
	}
	
}
