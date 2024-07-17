package com.board.www;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import com.board.www.dao.BoardDAO;
import com.board.www.dto.MemberDTO;
import com.board.www.service.BoardService;
import com.board.www.service.MemberService;

public class BoardMain {
	public static Scanner scanner = new Scanner(System.in);
	public static Connection connection = null;
	public static MemberDTO loginMember = null; // 로그인 후 객체
	// 생성자

	public BoardMain() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 1단계(드라이버명)
			connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.111.103:1521:orcl", "mbc", "1234"); // 2단계(URL,ID,PW)
		} catch (ClassNotFoundException e) {
			System.out.println("jdbc드라이버나, url, id, pw 문제발생");
			System.out.println("Board 생성자를 확인하세요");
			e.printStackTrace();
			System.exit(0); // 강제종료
		} catch (SQLException e) {
			System.out.println("url, id, pw나 쿼리문이 잘못됨.");
			e.printStackTrace();
			System.exit(0); // 강제종료
		}

	}

	public static void main(String[] args) throws SQLException {

		BoardMain boardMain = new BoardMain(); // 생성자 호출 -> 1단계, 2단계 실행.

		boolean run = true;
		System.out.println("MBC 아카데미 대나무숲 오신걸 환영합니다.");
		while (run) {
			System.out.println("1.회원 | 2.게시판 | 3.종료");
			System.out.print(">>>>>");
			int select = scanner.nextInt();

			switch (select) {
			case 1:
				System.out.println("회원용 서비스로 진입합니다.");
				MemberService memberService = new MemberService();
				loginMember = memberService.memberMenu(scanner, loginMember, connection);
				System.out.println(loginMember.getMid() + "님 환영합니다.");
				// 회원 서비스에서 나올 때 로그인 정보가 유지되어야 함.
				break;
			case 2:
				System.out.println("게시판 서비스로 진입합니다.");
				BoardService boardService = new BoardService();
				boardService.list(connection);

				break;
			case 3:
				System.out.println("종료 합니다.");
				run = false;
				break;

			default:
				System.out.println("1~3사이의 숫자만 입력하세요");

			} // switch end
		} // while end
	}// main method end

}
