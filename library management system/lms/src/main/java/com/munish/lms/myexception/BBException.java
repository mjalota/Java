package com.munish.lms.myexception;

public class BBException extends Exception {
	private static final long serialVersionUID = 1L;

	public BBException() {
	}

	public BBException(String message) {
		super(message);
	}

	public BBException(Throwable cause) {
		super(cause);
	}

	public BBException(String message, Throwable cause) {
		super(message, cause);
	}

	public BBException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
