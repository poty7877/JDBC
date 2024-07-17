package com.board.www.dto;

import java.sql.Date;

public class MemberDTO {
	// 필드
	private int mno;
	private String mid;
	private String mpw;
	private Date mdate;

	// 생성자
	public MemberDTO() {

	} // 기본 생성자 -> new MemberDTO();

	public MemberDTO(int mno, String mid, String mpw, Date mdate) {
		super();
		this.mno = mno;
		this.mid = mid;
		this.mpw = mpw;
		this.mdate = mdate;
	} // 커스텀 생성자 -> new MemberDTO(mno, mid, mpw, mdate);

	public MemberDTO(String loginId, String loginPw) {
		this.mid = loginId;
		this.mpw = loginPw;
	} // 커스텀 생성자 -> ID, PW 처리용

	// 메서드 -> getter, setter
	public int getMno() {
		return mno;
	}

	public String getMid() {
		return mid;
	}

	public String getMpw() {
		return mpw;
	}

	public Date getMdate() {
		return mdate;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public void setMpw(String mpw) {
		this.mpw = mpw;
	}

	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}

}
