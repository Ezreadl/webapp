package com.qm.nms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.qm.core.base.BaseDomCfg;

/**
 * 教学目录
 * @author lenovo
 *
 */
@SuppressWarnings("serial")
@Entity public class TeachContent extends BaseDomCfg{
	/**
	 * 教学目录名称
	 */
	  @Column private String contName;
		/**
		 * 目录版本
		 */	  
	  @Column private String contVersion;
		/**
		 * 适用学校类型
		 */	  
	  @Column private String fitType;
		public String getContName() {
			return contName;
		}
		public String getContVersion() {
			return contVersion;
		}
		public String getFitType() {
			return fitType;
		}
		public void setContName(String contName) {
			this.contName = contName;
		}
		public void setContVersion(String contVersion) {
			this.contVersion = contVersion;
		}
		public void setFitType(String fitType) {
			this.fitType = fitType;
		}
}
