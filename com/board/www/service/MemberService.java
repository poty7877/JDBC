package com.board.www.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.board.www.dao.MemberDAO;
import com.board.www.dto.MemberDTO;

public class MemberService {
	// 회원에 대한 처리 C(회원가입) R(로그인) U(회원정보수정) D(회원탈퇴)

	public MemberDTO memberMenu(Scanner scanner, MemberDTO loginMember, Connection connection) throws SQLException { // while문으로 부메뉴 반복 처리
		boolean memberRun = true;
		System.out.println("회원관리용 서비스로 진입");

		while (memberRun) {
			System.out.println("1.회원가입 | 2.로그인 | 3.회원정보 수정 | 4.회원탈퇴 | 5.종료");
			System.out.print(">>>>>");
			int select = scanner.nextInt();
			switch (select) {
			case 1:
				join();
				break;

			case 2:
				loginMember = login(scanner, loginMember, connection);
				break;

			case 3:

				modify();
				break;

			case 4:
				delete();
				break;

			case 5:
				System.out.println("회원 관리 메뉴를 종료합니다.");
				memberRun = false;
				break;

			default:
				System.out.println("1~5 사이의 번호만 입력하세요");

			} // switch end

		} // while end
		return loginMember;

	}// memberMenu() method end

	public void join() { // 회원가입용 메서드
		System.out.println("회원가입 메서드로 진입");
	} // join() method end

	public MemberDTO login(Scanner scanner, MemberDTO loginMember, Connection connection) throws SQLException { // 로그인용 메서드
		System.out.println("로그인 메서드로 진입");
		System.out.print("ID : ");
		String loginId = scanner.next();
		System.out.print("PW : ");
		String loginPw = scanner.next();
		MemberDTO loginMemberDTO = new MemberDTO(loginId, loginPw);
		// 키보드로 입력받은 값을 객체로 생성.
		MemberDAO memberDAO = new MemberDAO(connection);
		return memberDAO.login(connection, loginMemberDTO);
		// db에서 넘어온 객체를 return;
	} // login() method end

	public void modify() { // 회원정보 수정용 메서드
		System.out.println("회원정보 수정 메서드로 진입");
	} // modify() method end

	public void delete() { // 회원탈퇴용 메서드
		System.out.println("회원탈퇴 메서드로 진입");
	} // delete() method end

}
