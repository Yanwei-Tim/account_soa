package com.tianque.core.exception;

public class ServiceException extends RuntimeException {

	public ServiceException() {
		super();
	}

	public ServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ServiceException(String arg0) {
		super(arg0);
	}

	public ServiceException(Throwable arg0) {
		super(arg0);
	}

}
