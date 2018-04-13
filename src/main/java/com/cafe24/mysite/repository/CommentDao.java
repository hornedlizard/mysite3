package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cafe24.mysite.vo.CommentVo;

public class CommentDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	public void insert(CommentVo vo, long boardNo) {
		try {
			conn = MyConnection.getConnection();
			String sql = "insert into comment "
							+ "select "
							+ "null, ?, now(), "
							+ "if(?=0, ifnull(max(group_no)+1, 1), ?), "
							+ "if(?=0, 1, ?+1), "
							+ "if(?=0, 0, ?), 0, ?, ? " 
							+ "from board";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getContent());
			pstmt.setLong(2, vo.getGroupNo());
			pstmt.setLong(3, vo.getGroupNo());
			pstmt.setLong(4, vo.getOrderNo());
			pstmt.setLong(5, vo.getOrderNo());
			pstmt.setLong(6, vo.getDepth());
			pstmt.setLong(7, vo.getDepth());
			pstmt.setLong(8, vo.getUser().getNo());
			pstmt.setLong(9, vo.getBoardNo());
			int count = pstmt.executeUpdate();
			if (count == 0) {
				System.out.println("댓글 등록 실패");
			} else {
				System.out.println("댓글 등록 성공");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
