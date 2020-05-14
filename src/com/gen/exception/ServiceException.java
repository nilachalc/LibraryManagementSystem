package com.gen.exception;

import com.gen.util.LoadProperties;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	private LoadProperties properties;
	
	public ServiceException() {
		super();
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	@Override
	public String toString() {
		super.toString();
		properties = new LoadProperties();
		return new String(properties.getPropertyForValue("serviceExceptionMessage"));
	}
}
