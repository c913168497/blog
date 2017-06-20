package com.cnc.common.lang.exception;


public class CommonException extends RuntimeException {

	public CommonException() {}

	public CommonException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommonException(Throwable cause) {
		super(cause);
	}

	public CommonException(String message) {
		super(message);
	}
}
