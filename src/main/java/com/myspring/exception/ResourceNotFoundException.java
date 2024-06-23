package com.myspring.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
		super("resource not found exception");
	}
	
	public ResourceNotFoundException(String str) {
		super(str);
	}
}
