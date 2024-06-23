package com.myspring.exception;

public class BadApiRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BadApiRequestException() {
		super("invalid username password");
	}
	
	public BadApiRequestException(String str) {
		super(str);
	}
}
