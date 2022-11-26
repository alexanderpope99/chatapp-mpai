package com.mpai.chatapp.domain.exception;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}
