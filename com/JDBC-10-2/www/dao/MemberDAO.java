package com.jdbc.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.jdbc.www.BoardTestExam;
import com.jdbc.www.dto.MemberDTO;
import com.jdbc.www.service.BoardService;
import com.jdbc.www.service.MemberService;

public class MemberDAO {

	public MemberDTO login(Connection connection, String id, String pw) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		MemberDTO loginMember = null;

		String sql = "SELECT * FROM MEMBER WHERE MID = ? AND MPW = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, pw);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				loginMember = new MemberDTO();
				loginMember.setId(resultSet.getString("MID"));
				loginMember.setPw(resultSet.getString("MPW"));
				loginMember.setName(resultSet.getString("MNAME"));
				loginMember.setSsn(resultSet.getString("MSSN"));
				loginMember.setMno(resultSet.getInt("MNO"));
				loginMember.setNickName(resultSet.getString("MNN"));
				loginMember.setMdate(resultSet.getDate("MDATE"));
				return loginMember;

			} else {
				System.out.println("아이디, 비밀번호 입력오류");
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			resultSet.close();
			preparedStatement.close();
		}

		return loginMember;

	}// login() end

	public void join(Scanner scanner, Connection connection, MemberDTO newMemberDTO) throws SQLException {
		PreparedStatement preparedStatement = null;

		String idcheck = "SELECT MID FROM MEMBER WHERE MID = ?";
		preparedStatement = connection.prepareStatement(idcheck);
		preparedStatement.setString(1, newMemberDTO.getId());
		ResultSet resultSetCheck = preparedStatement.executeQuery();
		if (resultSetCheck.next()) {
			System.out.println("아이디 중복 오류");
			return;
		}

		resultSetCheck.close();
		preparedStatement.close();
		String sql = "INSERT INTO MEMBER VALUES (MEMBER_SEQ.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE )";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, newMemberDTO.getName());
			preparedStatement.setString(2, newMemberDTO.getSsn());
			preparedStatement.setString(3, newMemberDTO.getId());
			preparedStatement.setString(4, newMemberDTO.getPw());
			preparedStatement.setString(5, newMemberDTO.getNickName());

			int result = preparedStatement.executeUpdate();

			if (result > 0) {
				System.out.println(result + "개의 값이 추가됨.");
			} else {
				System.out.println("회원가입 실패");
			}

		} catch (SQLException e) {
			System.out.println("회원가입 실패");
			return;

		} finally {

			preparedStatement.close();
		}

	}

	public void read(Connection connection, MemberDTO loginMember) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String sql = "SELECT * FROM MEMBER WHERE MID = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginMember.getId());
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				System.out.println("회원번호\t이름\t주민번호\t\tID\tPW\t닉네임\t가입날짜");
				System.out.print(resultSet.getInt("MNO") + "\t");
				System.out.print(resultSet.getString("MNAME") + "\t");
				System.out.print(resultSet.getString("MSSN") + "\t");
				System.out.print(resultSet.getString("MID") + "\t");
				System.out.print(resultSet.getString("MPW") + "\t");
				System.out.print(resultSet.getString("MNN") + "\t");
				System.out.println(resultSet.getDate("MDATE"));
			} else {
				System.out.println("가입된 회원이 없습니다.");
			}

			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void modifyPW(Scanner scanner, Connection connection, MemberDTO loginMember, String modPw) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "UPDATE MEMBER SET MPW = ? WHERE MID = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, modPw);
			preparedStatement.setString(2, loginMember.getId());
			result = preparedStatement.executeUpdate();
			if (result > 0) {
				System.out.println("수정 완료!");
			} else {
				System.out.println("수정 실패!");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void modifyNN(Scanner scanner, Connection connection, MemberDTO loginMember, String modNn) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "UPDATE MEMBER SET MNN = ? WHERE MID = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, modNn);
			preparedStatement.setString(2, loginMember.getId());
			result = preparedStatement.executeUpdate();
			if (result > 0) {
				System.out.println("수정 완료!");
			} else {
				System.out.println("수정 실패!");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delete(Connection connection, MemberDTO loginMember, String delId, String delPw) {
		PreparedStatement preparedStatement = null;
		String sql = "DELETE FROM MEMBER WHERE MID = ? AND MPW = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, delId);
			preparedStatement.setString(2, delPw);

			int result = preparedStatement.executeUpdate();

			if (result > 0) {
				System.out.println(result + "개 객체 삭제 완료");

			} else {
				System.out.println("삭제 실패.");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
