package com.app.group15.ExceptionHandler;

public class AdminNotAllowedException extends Exception {

	private static final long serialVersionUID = 1L;

	public AdminNotAllowedException(String msg) {
		super(msg);
	}

}
