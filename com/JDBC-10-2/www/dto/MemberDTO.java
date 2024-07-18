package com.jdbc.www.dto;

import java.sql.Date;

public class MemberDTO {

	private int mno;
	private String id;
	private String pw;
	private String name;
	private String ssn;
	private String nickName;
	private Date mdate;

	public MemberDTO() {

	} // 기본생성자

	public int getMno() {
		return mno;
	}

	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public String getName() {
		return name;
	}

	public String getSsn() {
		return ssn;
	}

	public String getNickName() {
		return nickName;
	}

	public Date getMdate() {
		return mdate;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}

}