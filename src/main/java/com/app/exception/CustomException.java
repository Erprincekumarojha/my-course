package com.app.exception;

import com.app.util.Generated;

@Generated
public class CustomException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;

	public CustomException(String message) {
      super(message);
      this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
}
