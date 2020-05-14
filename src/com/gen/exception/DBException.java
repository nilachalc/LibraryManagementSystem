package com.gen.exception;

import com.gen.util.LoadProperties;

public class DBException extends Exception {

	private static final long serialVersionUID = 1L;
	private LoadProperties properties;
	
	public DBException() {
		super();
	}

	public DBException(Throwable cause) {
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
		return new String(properties.getPropertyForValue("dbExceptionMessage"));
	}
}
