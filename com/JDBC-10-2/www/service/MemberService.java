package com.jdbc.www.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.jdbc.www.BoardTestExam;
import com.jdbc.www.dao.MemberDAO;
import com.jdbc.www.dto.MemberDTO;

public class MemberService {
	public static MemberDAO memberDAO = new MemberDAO();

	public void MemberMenu(Scanner scanner, Connection connection, MemberDTO loginMember) throws SQLException {
		boolean memberRun = true;
		while (memberRun) {

			System.out.println("1.회원정보 확인 | 2.회원정보 수정 | 3.회원탈퇴 | 4. 종료");
			int select = scanner.nextInt();
			switch (select) {
			case 1:
				read(scanner, connection, loginMember);
				break;

			case 2:
				modify(scanner, connection, loginMember);
				break;

			case 3:
				delete(scanner, connection, loginMember);
				break;

			case 4:
				memberRun = false;
				break;

			default:
				System.out.println("1~4번만 입력하세요");
			}
		}
	} // MemberMenu() method end

	public void read(Scanner scanner, Connection connection, MemberDTO loginMember) {
		System.out.println("사용자 정보확인을 위해 ID,와 PW를 입력 해주세요.");
		System.out.print("ID : ");
		String readId = scanner.next();
		System.out.print("PW : ");
		String readPw = scanner.next();
		if (readId.equals(loginMember.getId()) && readPw.equals(loginMember.getPw())) {
			memberDAO.read(connection, loginMember);
		} else {
			System.out.println("ID, PW 입력오류");
		}

	}

	public void modify(Scanner scanner, Connection connection, MemberDTO loginMember) {
		System.out.println("수정할 정보 선택");
		System.out.println("1.PW | 2.닉네임");
		int select = scanner.nextInt();
		if (select == 1) {
			System.out.println("현재 비밀번호 : " + loginMember.getPw());
			System.out.print("변경할 비밀번호 : ");
			String modPw = scanner.next();
			memberDAO.modifyPW(scanner, connection, loginMember, modPw);
		} else if (select == 2) {
			System.out.println("현재 닉네임 : " + loginMember.getNickName());
			System.out.print("변경할 닉네임 : ");
			String modNn = scanner.next();
			memberDAO.modifyNN(scanner, connection, loginMember, modNn);

		} else {
			System.out.println("번호 입력 오류");
		}

	}

	public void delete(Scanner scanner, Connection connection, MemberDTO loginMember) throws SQLException {
		System.out.println("사용자 정보 확인을 위해 ID와 PW를 입력해주세요");
		System.out.print("ID : ");
		String delId = scanner.next();
		System.out.print("PW : ");
		String delPw = scanner.next();

		System.out.println("정말 탈퇴 하시겠습니까?");
		System.out.println("1.YES | 2.NO");
		int select = scanner.nextInt();
		if (select == 1) {
			if (loginMember.getId().equals(delId) && loginMember.getPw().equals(delPw)) {
				memberDAO.delete(connection, loginMember, delId, delPw);
				
			} else {
				System.out.println("아이디, 비밀번호를 확인하세요");
				
			}
		} else if (select == 2) {
			System.out.println("메뉴로 돌아갑니다");
			return;
		} else {
			System.out.println("번호 입력 오류");
		}

	}

}
