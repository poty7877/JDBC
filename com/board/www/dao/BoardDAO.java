package com.board.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.board.www.BoardMain;
import com.board.www.dto.BoardDTO;

public class BoardDAO {
	// 데이터베이스 처리용 CRUD
	BoardMain boardMain = new BoardMain();

	public void list(Connection connection) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		// BoardDTO boardDTO = null;
		try {
			String sql = "SELECT * FROM BOARD ORDER BY BNO ASC";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			// boardDTO = new BoardDTO();
			while (resultSet.next()) { // 표형식으로 리턴된 값 유무.

				System.out.print(resultSet.getInt("BNO") + "\t");
				System.out.print(resultSet.getString("BTITLE") + "\t");
				System.out.print(resultSet.getString("BCONTENTS") + "\t");
				System.out.print(resultSet.getString("BWRITER") + "\t");
				System.out.println(resultSet.getDate("BDATE") + "\t");

				/*
				 * boardDTO.setBno(resultSet.getInt("bno"));
				 * boardDTO.setBtitle(resultSet.getString("BTITLE"));
				 * boardDTO.setBcontents(resultSet.getString("BCONTENTS"));
				 * boardDTO.setBwriter(resultSet.getString("BWIRTER"));
				 * boardDTO.setBdate(resultSet.getDate("DATE"));
				 */
			}
			// 5단계
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.println("BoardDAO.list().SQL문 오류");
			e.printStackTrace();
		}

	} // list method end

}
