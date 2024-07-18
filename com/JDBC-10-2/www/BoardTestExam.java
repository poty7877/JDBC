package com.jdbc.www;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.jdbc.www.dao.MemberDAO;
import com.jdbc.www.dto.MemberDTO;
import com.jdbc.www.service.BoardService;
import com.jdbc.www.service.MemberService;

public class BoardTestExam {
	public static Scanner scanner = new Scanner(System.in);
	public static Connection connection = null;
	public static MemberDAO memberDAO = new MemberDAO();
	public static MemberDTO loginMember;
	public static MemberService memberSV = new MemberService();
	public static BoardService boardSV = new BoardService();
	public static int result = 0;

	public BoardTestExam() throws SQLException {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 1단계
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "boardtest", "boardtest");
			// 2단계
		} catch (ClassNotFoundException e) {
			System.out.println("jdbc드라이버나, url, id, pw 문제 발생");
			System.out.println("Board 생성자를 확인하세요");
			e.printStackTrace();
			System.exit(0); // 강제 종료
		}

	}

	public static void main(String[] args) throws SQLException {
		BoardTestExam boardTestExam = new BoardTestExam();
		mainMenu();
	} // main() method end

	public static void mainMenu() throws SQLException {
		// db를 이용하여 자바랑 연결해서 회원, 게시판 만들기
		boolean run = true;
		System.out.println("MBC홈페이지 입니다.");

		while (run) {

			System.out.println("1.로그인 | 2.회원가입 | 3.종료");
			int select = scanner.nextInt();

			switch (select) {
			case 1:
				loginMember = login(scanner, connection);
				if (loginMember != null) {
					System.out.println(loginMember.getNickName() + "님 환영 합니다.");
				} else {
					mainMenu();
				}
				System.out.println("1.회원정보 | 2.게시판 | 3.종료");
				int subSelect = scanner.nextInt();
				switch (subSelect) {
				case 1:
					memberSV.MemberMenu(scanner, connection, loginMember);
					break;

				case 2:
					boardSV.boardMenu(scanner, connection, loginMember);
					break;

				case 3:
					break;

				default:
					System.out.println("1~3번호만 입력하세요");
				}
				break;

			case 2:
				join(scanner, connection);
				break;

			case 3:
				System.out.println("안녕히 가세요");
				run = false;
				break;
			}

		} // while end

	}

	public static MemberDTO login(Scanner scanner, Connection connection) throws SQLException {

		System.out.println("로그인 화면 입니다.");
		System.out.print("ID : ");
		String id = scanner.next();
		System.out.print("PW : ");
		String pw = scanner.next();

		loginMember = memberDAO.login(connection, id, pw);
		return loginMember;

	} // login() method end

	public static void join(Scanner scanner, Connection connection) throws SQLException {
		MemberDTO newMemberDTO = new MemberDTO();

		System.out.println("이름 : ");
		newMemberDTO.setName(scanner.next());
		System.out.println("주민번호 : ");
		newMemberDTO.setSsn(scanner.next());
		System.out.println("ID : ");
		newMemberDTO.setId(scanner.next());
		System.out.println("PW : ");
		newMemberDTO.setPw(scanner.next());
		System.out.println("닉네임 : ");
		newMemberDTO.setNickName(scanner.next());

		memberDAO.join(scanner, connection, newMemberDTO);

	} // join() method end

} // class end
