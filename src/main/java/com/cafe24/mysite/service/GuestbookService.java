package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.GuestbookDao;
import com.cafe24.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookDao dao;
	
	public List<GuestbookVo> list() {
		return dao.getGuestbookList();
	}
	
	public void insert(GuestbookVo vo) {
		dao.insertGuestbook(vo);
		// int count = dao.insertGuestbook(vo);
		//return count == 1;
	}
	
	public GuestbookVo insert2(GuestbookVo guestbookVo) {
		GuestbookVo vo = null;
		
		int count = dao.insertGuestbook(guestbookVo);
		if (count == 1) {
			vo = dao.get(guestbookVo.getNo());
		}
		return vo;
	}
	
	public List<GuestbookVo> getMessageList(long no) {
		return dao.getMessageList(no);
	}
	
//	public void delete(long no, String password) {
//		dao.deleteGuestbook(no, password);
//	}

	public void delete(GuestbookVo vo) {
		dao.delete(vo);
	}

	public boolean deleteMessage(GuestbookVo vo) {
		// 성공하면 1, 실패하면 0
		int result = dao.delete(vo);
		return result == 1;
	}
}
