package com.jdbc.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.jdbc.www.dto.BoardDTO;
import com.jdbc.www.dto.MemberDTO;

public class BoardDAO {

	public void create(Scanner scanner, Connection connection, MemberDTO loginMember, BoardDTO newBoard) {
		PreparedStatement preparedStatement = null;

		String sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, SYSDATE)";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, newBoard.getTitle());
			preparedStatement.setString(2, newBoard.getContents());
			preparedStatement.setString(3, loginMember.getNickName());
			int result = preparedStatement.executeUpdate();

			if (result > 0) {
				System.out.println(result + "개 게시물 작성 완료!");
			} else {
				System.out.println("입력 실패.");
			}
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.println("오류발생 : BoardDAO.create() 의 sql문을 확인 하세요.");
			e.printStackTrace();
		}

	}

	public void read(Scanner scanner, Connection connection, int select) {
		PreparedStatement preparedStatement = null;

		String sql = "SELECT * FROM BOARD WHERE BNO = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, select);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				System.out.println("게시물 번호 : " + resultSet.getInt("BNO"));
				System.out.println("제목 : " + resultSet.getString("BTITLE"));
				System.out.println("내용 : " + resultSet.getString("BCONTENTS"));
				System.out.println("작성자 : " + resultSet.getString("BWRITER"));
				System.out.println("작성일 : " + resultSet.getDate("BDATE"));
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void modifyTitle(Scanner scanner, Connection connection, MemberDTO loginMember, int boardNo) {
		PreparedStatement preparedStatement = null;

		String sql1 = "SELECT BTITLE, BWRITER FROM BOARD WHERE BNO = ? ";
		try {
			preparedStatement = connection.prepareStatement(sql1);
			preparedStatement.setInt(1, boardNo);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				if (resultSet.getString("BWRITER").equals(loginMember.getNickName())) {

					System.out.println("현재 제목 : " + resultSet.getString("BTITLE"));
				} else {
					System.out.println("작성자가 아닙니다.");
					return;
				}
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sql = "UPDATE BOARD SET BTITLE =? WHERE BNO = ? ";
		try {
			System.out.print("변경할 제목 : ");
			String modTitle = scanner.next();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, modTitle);
			preparedStatement.setInt(2, boardNo);

			int result = preparedStatement.executeUpdate();

			if (result > 0) {
				System.out.println(result + "개의 정보 수정 완료.");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void modifyContents(Scanner scanner, Connection connection, MemberDTO loginMember, int boardNo) {
		PreparedStatement preparedStatement = null;
		String sql1 = "SELECT BCONTENTS, BWRITER FROM BOARD WHERE BNO = ? ";
		try {
			preparedStatement = connection.prepareStatement(sql1);
			preparedStatement.setInt(1, boardNo);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				if (resultSet.getString("BWRITER").equals(loginMember.getNickName())) {

					System.out.println("현재 내용 : " + resultSet.getString("BCONTENTS"));
				} else {
					System.out.println("작성자가 아닙니다.");
					return;
				}
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sql = "UPDATE BOARD SET BCONTENTS =? WHERE BNO = ?";
		try {
			System.out.print("변경할 내용 : ");
			String modContents = scanner.next();

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, modContents);
			preparedStatement.setInt(2, boardNo);
			int result = preparedStatement.executeUpdate();

			if (result > 0) {
				System.out.println(result + "개의 정보 수정 완료.");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delete(Scanner scanner, Connection connection, MemberDTO loginMember, int select) {
		PreparedStatement preparedStatement = null;
		String sql2 = "SELECT BWRITER FROM BOARD WHERE BNO = ?";
		try {
			preparedStatement = connection.prepareStatement(sql2);
			preparedStatement.setInt(1, select);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				if (resultSet.getString("BWRITER").equals(loginMember.getNickName())) {
					System.out.println("정말 삭제할까요 ? ");
					System.out.println("1.YES | 2.NO");
					int select2 = scanner.nextInt();
					switch (select2) {
					case 1:
						String sql = "DELETE FROM BOARD WHERE BNO = ?";
						try {
							preparedStatement = connection.prepareStatement(sql);
							preparedStatement.setInt(1, select);
							int result = preparedStatement.executeUpdate();

							if (result > 0) {
								System.out.println(result + "개 게시물 삭제 완료!");
							} else {
								System.out.println("삭제 실패!");
							}

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					case 2:
						System.out.println("메뉴로 돌아갑니다.");
						return;
					default:
						System.out.println("1~2번만 눌러주세요");
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void list(Connection connection) {
		PreparedStatement preparedStatement = null;
		String sql = "SELECT * FROM BOARD ORDER BY BNO ASC";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("번호\t 제목\t\t 작성자\t 작성일");
			while (resultSet.next()) {
				System.out.print(resultSet.getInt("BNO") + "\t");
				System.out.print(resultSet.getString("BTITLE") + "\t");
				System.out.print(resultSet.getString("BWRITER") + "\t");
				System.out.println(resultSet.getDate("BDATE"));

			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
