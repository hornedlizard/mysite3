package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.PageVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao dao;
	
	public int countData(String keyword) {
		return dao.searchCount(keyword);
	}
	
	public List<BoardVo> list(PageVo page, String keyword) {
		return dao.getList(page, keyword);
	}
	
	public BoardVo view(long no) {
		return dao.getBoard(no);
	}
	
	public void increaseHits(long no) {
		dao.updateHits(no);
	}
	
	public void write(BoardVo vo) {
		dao.insert(vo);
	}
	
	public void delete(long no) {
		dao.delete(no);
	}
	
	public void update(BoardVo vo) {
		dao.update(vo);
	}
	
	public void increaseOrderNo(long groupNo, long orderNo) {
		dao.updateOrderNo(groupNo, orderNo);
	}
}
