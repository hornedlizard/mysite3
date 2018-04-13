package com.cafe24.mysite.exception;

public class UserDaoException extends RuntimeException {
	private static final long serialVersionUID = 1l;
	
	public UserDaoException() {
		super("UserDaoException Occuers");
		
	}
	public UserDaoException(String message) {
		super(message);
		
	}
}
