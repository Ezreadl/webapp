package com.qm.core.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qm.core.util.DateUtil;

/**
 * 带操作记录的基类，不含删除标记
 * @author wangchong
 *
 */
@MappedSuperclass
@SuppressWarnings("serial")
public class BaseDomOpt implements Serializable,Cloneable{
	
	/**
	 * 数据库保存的主键，自增长类型
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int oid;
	
	/**
	 * 最近操作该记录的用户ID
	 */
	@Column
	protected String optUserName;
	
	/**
	 * 添加该记录的用户ID
	 */
	@Column
	protected String addUserName;
	
	/**
	 * 记录最近一次编辑该记录的时间
	 */
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using=DateTimeSerializer.class)
	protected Date optDateTime;
	
	/**
	 * 该记录被添加的时间
	 */
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using=DateTimeSerializer.class)
	protected Date addDateTime;

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getOptUserName() {
		return optUserName;
	}

	public void setOptUserName(String optUserName) {
		this.optUserName = optUserName;
	}

	public String getAddUserName() {
		return addUserName;
	}

	public void setAddUserName(String addUserName) {
		this.addUserName = addUserName;
	}

	public Date getOptDateTime() {
		return optDateTime;
	}

	public void setOptDateTime(Date optDateTime) {
		this.optDateTime = optDateTime;
	}

	public Date getAddDateTime() {
		return addDateTime;
	}

	public void setAddDateTime(Date addDateTime) {
		this.addDateTime = addDateTime;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	/**
	 * 更新用户操作记录信息
	 * @param oldCfg
	 * @param userName
	 */
	public void updateOptInfo(BaseDomCfg oldCfg,String userName){
		this.setOptUserName(userName);
		this.setOptDateTime(DateUtil.getDate());
		if(oldCfg==null){
			this.setAddUserName(userName);
			this.setAddDateTime(DateUtil.getDate());
		}else{
			this.setAddUserName(oldCfg.getAddUserName());
			this.setAddDateTime(oldCfg.getAddDateTime());
		}
	}
}
