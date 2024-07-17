package com.board.www.dto;

import java.sql.Date;

public class BoardDTO {
	// board 객체 처리, getter, setter

	// 필드
	private int bno;
	private String btitle;
	private String bcontents;
	private String bwriter;
	private Date bdate;

	// 생성자 (기본)
	public BoardDTO() {

	}

	public BoardDTO(int bno, String btitle, String bcontents, String bwriter, Date bdate) {
		super();
		this.bno = bno;
		this.btitle = btitle;
		this.bcontents = bcontents;
		this.bwriter = bwriter;
		this.bdate = bdate;
	} // insert시 활용

	// 메서드
	public int getBno() {
		return bno;
	}

	public String getBtitle() {
		return btitle;
	}

	public String getBcontents() {
		return bcontents;
	}

	public String getBwriter() {
		return bwriter;
	}

	public Date getBdate() {
		return bdate;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public void setBcontents(String bcontents) {
		this.bcontents = bcontents;
	}

	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

}
