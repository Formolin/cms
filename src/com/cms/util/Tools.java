package com.cms.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tools {
	public static int readDB() {
		int visits=0;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs = null;
		try {
			 connection = ConnDB.getConnection();
			String sql = "select visits from tb_visits";
			preparedStatement = connection.prepareStatement(sql);
			 rs = preparedStatement.executeQuery();
			if (rs.next()) {
				visits = rs.getInt("visits");
			}
			return visits;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			ConnDB.closeDB(connection, preparedStatement, rs);
		}
	}
	
	public static void writeDB(int visits) {
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs = null;
		try {
			 connection = ConnDB.getConnection();
			String sql = "update tb_visits set visits = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, visits);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			ConnDB.closeDB(connection, preparedStatement, rs);
		}
	}
}
