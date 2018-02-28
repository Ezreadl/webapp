package com.qm.sys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.qm.core.base.BaseDomCfg;

/*
 * mvc测试
 * @author (goujiangtao)
 */
@Entity
public class Test extends BaseDomCfg{
	/*
	 * 名字
	 */
	@Column
	private String name="";
	/*
	 * 备忘
	 */
	@Column
	private String answer="";	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String toString(){
		return "Test:[name="+name+",answer="+answer+"]";
	}
	
	public static void main(String[] args) {
		System.out.println(new Test().toString());
	}
}
