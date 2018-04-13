package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	private static Connection conn;
	
	public static Connection getConnection() {
		try {
			if (conn != null && conn.isClosed() == false) {
				return conn;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return makeConnection();
	}
	
	private static Connection makeConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
