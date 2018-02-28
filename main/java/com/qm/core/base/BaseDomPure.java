package com.qm.core.base;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 不带任何操作记录的基类
 * @author wangchong
 *
 */
@MappedSuperclass
@SuppressWarnings("serial")
public class BaseDomPure implements Serializable,Cloneable{
	
	/**
	 * 数据库保存的主键，自增长类型
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int oid;

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
