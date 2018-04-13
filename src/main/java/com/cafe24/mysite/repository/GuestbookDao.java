package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.GuestbookVo;

@Repository
public class GuestbookDao {
//	@Autowired
//	private DataSource dataSource;
	@Autowired
	private SqlSession sqlSession;
//	private Connection conn = null;
//	private PreparedStatement pstmt = null;
	
	public GuestbookVo get(long no) {
		return sqlSession.selectOne("guestbook.getByNo", no);
	}
	
	public int insertGuestbook(GuestbookVo vo) {
		System.out.println(vo);
		int count = sqlSession.insert("guestbook.insert", vo);
		System.out.println(vo);
		return count;
	}
	
	public List<GuestbookVo> getGuestbookList() {
		return sqlSession.selectList("guestbook.getList");
	}
	
	public int delete(GuestbookVo vo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", vo.getNo());
		map.put("password", vo.getPassword());
		int count = sqlSession.delete("guestbook.delete", map);
		return count;
	}
	
	public List<GuestbookVo> getMessageList(long no) {
		return sqlSession.selectList("guestbook.getList2", no);
	}
	
//	public void deleteGuestbook(long no, String password) {
//		try {
//			conn = dataSource.getConnection();
//			String sql = "delete from guestbook "
//						+ "where no = ? "
//						+ "and password = password(?)";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setLong(1, no);
//			pstmt.setString(2, password);
//			int count = pstmt.executeUpdate();
//			if (count == 0) {
//				System.out.println("삭제 실패");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//	}
	
}
