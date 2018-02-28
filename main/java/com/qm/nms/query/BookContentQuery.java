package com.qm.nms.query;

import com.qm.core.base.IQuery;

public class BookContentQuery  implements IQuery {

	  /**
	   * @课程名称
	   */
	  private String bookName;
	  /**
	   * 课程备注
	   */
	  private String bookCont;
	  /**
	   * 上级目录ID
	   */
	  private Integer pid;
	  /**
	   * 实验类型
	   */
	  private String grade ;
	  /**
	   * 年级
	   */
	  private String classType;
	  /**
	   * 学期
	   */
	  private String term;
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
		public Integer getPid() {
			return pid;
		}
		public void setPid(Integer pid) {
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
}
