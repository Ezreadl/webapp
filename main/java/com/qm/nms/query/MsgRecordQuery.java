package com.qm.nms.query;

import com.qm.core.base.IQuery;
/**
 * 消息记录
 * @author lenovo
 *
 */
public class MsgRecordQuery implements IQuery {
	private Integer oid;
    /**
     * 消息主题
     */
    private String msgName;
    /**
     * 发送方
     */    
    private String fromWho;
    private String toWho;
    /**
     * 
     */
    private String content;
    /**
     * 发送时间
     */
    private String startTime;
    private Integer delFlg;
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
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
	public Integer getDelFlg() {
		return delFlg;
	}
	public void setDelFlg(Integer delFlg) {
		this.delFlg = delFlg;
	}
}
