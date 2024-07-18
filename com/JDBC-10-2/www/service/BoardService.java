package com.jdbc.www.service;

import java.sql.Connection;
import java.util.Scanner;

import com.jdbc.www.dao.BoardDAO;
import com.jdbc.www.dto.BoardDTO;
import com.jdbc.www.dto.MemberDTO;

public class BoardService {
	public static BoardDAO boardDAO = new BoardDAO();

	public void boardMenu(Scanner scanner, Connection connection, MemberDTO loginMember) {
		boolean boardRun = true;
		while (boardRun) {
			boardDAO.list(connection);
			System.out.println("1.게시물작성 | 2.게시물확인 | 3.게시물수정 | 4.게시물삭제 | 5.종료");
			System.out.print(">>>>>");
			int select = scanner.nextInt();

			switch (select) {
			case 1:
				create(scanner, connection, loginMember);
				break;

			case 2:
				boardDAO.list(connection);
				read(scanner, connection);
				break;

			case 3:
				modify(scanner, connection, loginMember);
				break;

			case 4:
				delete(scanner, connection, loginMember);
				break;

			case 5:
				boardRun = false;
				break;

			default:
				System.out.println("1~5번 사이의 숫자만 입력하세요.");

			}

		}
	} // boardMenu() method end

	public void create(Scanner scanner, Connection connection, MemberDTO loginMember) {
		BoardDTO newBoard = new BoardDTO();
		System.out.print("게시물 작성 화면 입니다.");
		System.out.print("제목 : ");
		newBoard.setTitle(scanner.next());
		System.out.print("내용 : ");
		newBoard.setContents(scanner.next());

		boardDAO.create(scanner, connection, loginMember, newBoard);

	}

	public void read(Scanner scanner, Connection connection) {
		System.out.println("확인할 게시물의 번호를 입력하세요");
		System.out.print(">>>");
		int select = scanner.nextInt();
		boardDAO.read(scanner, connection, select);

	}

	public void modify(Scanner scanner, Connection connection, MemberDTO loginMember) {
		System.out.println("수정할 게시물의 번호 입력");
		System.out.print(">>>>>");
		int boardNo = scanner.nextInt();
		System.out.println("어떤 정보를 수정할까요?");
		System.out.println("1.제목 | 2.내용");
		int select = scanner.nextInt();
		if (select == 1) {
			boardDAO.modifyTitle(scanner, connection, loginMember, boardNo);
		} else if (select == 2) {
			boardDAO.modifyContents(scanner, connection, loginMember, boardNo);
		} else {
			System.out.println("입력오류.");
		}

	}

	public void delete(Scanner scanner, Connection connection, MemberDTO loginMember) {
		System.out.println("삭제할 게시물의 번호를 입력하세요");
		System.out.print(">>>");
		int select = scanner.nextInt();
		
		boardDAO.delete(scanner, connection, loginMember, select);
		return;
		
	}
}
