package com.cms.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ConnDB {
	
	//注册驱动
	static{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	//获得数据库连接
		public static Connection getConnection() throws SQLException {
			String url = "jdbc:mysql://localhost:3306/servlet?characterEncoding=utf8&useSSL=false";
			String user = "root";
			String password = "password";
			Connection connection = DriverManager.getConnection(url, user, password);
			return connection;
		}
	//关闭资源
		public static void closeDB(Connection connection,PreparedStatement preparedStatement,ResultSet rs) {
			try {
				if (rs !=null) {
					rs.close();
					rs=null;
				}
				if (preparedStatement !=null) {
					preparedStatement.close();
					preparedStatement=null;
				}
				if (connection !=null) {
					connection.close();
					connection=null;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
}
