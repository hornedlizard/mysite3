package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClosingConn {

	public static void closingConnection(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if (rs != null && rs.isClosed()) {
				rs.close();
			}
			if (pstmt != null && pstmt.isClosed()) {
				pstmt.isClosed();
			}
			if (conn != null && conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closingConnection(PreparedStatement pstmt, Connection conn) {
		try {
			if (pstmt != null && pstmt.isClosed()) {
				pstmt.close();
			}
			if (conn != null && conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
