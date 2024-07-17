package com.board.www.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import com.board.www.dao.BoardDAO;


public class BoardService {
	// board의 부메뉴.
	BoardDAO boardDAO = new BoardDAO();

	public void list(Connection connection) throws SQLException { // 게시물 목록 보기.
		System.out.println("=========================");
		System.out.println("=======대나무숲 게시판========");
		System.out.println("[게시물 목록]");
		System.out.println("---------------------------------------------");
		System.out.println("no\t title\t contents\t writer\t date");
		System.out.println("---------------------------------------------");
		boardDAO.list(connection);

		// BOARD 테이블에 있는 데이터를 가져옴.

	}

	public void BoardMenu(Scanner scanner) {

	}
}
