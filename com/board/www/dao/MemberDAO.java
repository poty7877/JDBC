package com.board.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.board.www.dto.MemberDTO;

public class MemberDAO {
	// 회원 db에 대한 C(회원가입) R(로그인) U(회원정보수정) D(회원탈퇴)

	public MemberDAO() {

	}// 기본 생성자

	public MemberDAO(Connection connection) {
	} // 커스텀 생성자

	public void register() { // 회원가입 처리

	}

	public MemberDTO login(Connection connection, MemberDTO loginMemberDTO) throws SQLException { // 로그인 처리
		// connection -> main에서 넘어온 jdbc 1,2단계
		// loginMemberDTO -> 로그인시 키보드로 입력받은 id,pw 값이 들어있다.
		// db에 있는 로그인 값을 찾아 옴.
		MemberDTO loginDTO = new MemberDTO(); // 리턴용 빈 객체
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT * FROM MEMBER WHERE MID = ? AND MPW = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginMemberDTO.getMid());
			preparedStatement.setString(2, loginMemberDTO.getMpw());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				loginDTO.setMno(resultSet.getInt("MNO"));
				loginDTO.setMid(resultSet.getString("MID"));
				loginDTO.setMpw(resultSet.getString("MPW"));
				loginDTO.setMdate(resultSet.getDate("MDATE"));
				// resultSet 표에 있는 정보를 MemberDTO 객체에 넣음.

				System.out.println(loginDTO.getMid() + "님 환영합니다.");
			}

		} catch (SQLException e) {
			System.out.println("MemberDAO.login() 쿼리문 오류");
			System.out.println("회원 : id, pw를 확인하세요");
			e.printStackTrace();
		} finally {
			resultSet.close();
			preparedStatement.close();
		}

		return loginDTO; // 로그인 완성용 객체
	} // login() Method end

	public void update() { // 회원 정보 수정

	}

	public void delete() { // 회원 탈퇴

	}

}
