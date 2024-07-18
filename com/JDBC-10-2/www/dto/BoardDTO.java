package com.jdbc.www.dto;

import java.sql.Date;

public class BoardDTO {

	private int bno;
	private String title;
	private String contents;
	private String writer;
	private Date date;

	public int getBno() {
		return bno;
	}

	public String getTitle() {
		return title;
	}

	public String getContents() {
		return contents;
	}

	public String getWriter() {
		return writer;
	}

	public Date getDate() {
		return date;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
