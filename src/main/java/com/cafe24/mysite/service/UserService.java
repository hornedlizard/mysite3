package com.cafe24.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.mysite.repository.UserDao;
import com.cafe24.mysite.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public void join(UserVo vo) {
		userDao.insert(vo);
	}
	
	public UserVo getUser(UserVo vo) {
		return userDao.get(vo);
	}
	
	@Transactional
	public void modifyUser(UserVo vo) {
		userDao.update(vo);
	}
	
	public UserVo getUser(Long no) {
		return userDao.get(no);
	}
}
