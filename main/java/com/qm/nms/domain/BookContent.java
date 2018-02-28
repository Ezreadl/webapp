package com.qm.nms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.qm.core.base.BaseDomCfg;
/**
 * 课程目录
 * @author lenovo 
 */
@SuppressWarnings("serial")
@Entity
public class BookContent extends BaseDomCfg{

  /**
   * @课程名称
   */
  @Column 
  private String bookName;
  /**
   * 课程备注
   */
  @Column private String bookCont;
  /**
   * 上级目录ID
   */
  @Column private int pid;
  /**
   * 实验类型
   */
  @Column private String grade ;
  /**
   * 年级
   */
  @Column private String classType ;
  /**
   * 学期
   */
  @Column private String term;
  public String getBookName() {
	return bookName;
  }
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookCont() {
		return bookCont;
	}
	public void setBookCont(String bookCont) {
		this.bookCont = bookCont;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	@Override
	public String toString() {
		return "BookContent [bookName=" + bookName + ", bookCont=" + bookCont + ", pid=" + pid + ", grade=" + grade
				+ ", classType=" + classType + ", term=" + term + "]";
	}
	
}
