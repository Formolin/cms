package com.cms.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;



public class C3P0Util {
	//得到一个数据源
		private static DataSource dataSource = new ComboPooledDataSource();
		
		//从数据源中得到一个连接对象
		public static Connection getConnection() throws SQLException {

				return dataSource.getConnection();

		}
	public static void release(Connection conn,Statement stmt,ResultSet rs){
			//关闭资源
			if(rs!=null){
			  try {
				rs.close();
			  } catch (Exception e) {
				e.printStackTrace();
			  }
			  rs = null;
			}
			if(stmt!=null){
			  try {
				stmt.close();
			  } catch (Exception e) {
				e.printStackTrace();
			  }
			  stmt = null;
			}
			if(conn!=null){
			  try {
				conn.close();  //关闭,其实是交还给池子来处理
			  } catch (Exception e) {
				e.printStackTrace();
			  }
			  conn = null;
			}
		}
	

	}



