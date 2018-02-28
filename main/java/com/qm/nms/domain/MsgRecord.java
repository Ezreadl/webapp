package com.qm.nms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * 消息记录
 * @author lenovo
 *
 */
@Entity public class MsgRecord {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int oid;
    /**
     * 消息主题
     */
    @Column private String msgName;
    /**
     * 发送方
     */    
    @Column private String fromWho;
    @Column private String toWho;
    /**
     * 
     */
    @Column private String content;
    /**
     * 发送时间
     */
    @Column private String startTime;
    @Column private int delFlg;
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}
	public String getFromWho() {
		return fromWho;
	}
	public void setFromWho(String fromWho) {
		this.fromWho = fromWho;
	}
	public String getToWho() {
		return toWho;
	}
	public void setToWho(String toWho) {
		this.toWho = toWho;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public int getDelFlg() {
		return delFlg;
	}
	public void setDelFlg(int delFlg) {
		this.delFlg = delFlg;
	}
}
