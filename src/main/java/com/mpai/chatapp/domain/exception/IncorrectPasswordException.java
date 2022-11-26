package com.mpai.chatapp.domain.exception;

public class IncorrectPasswordException extends RuntimeException {

	public IncorrectPasswordException(String errorMessage) {
		super(errorMessage);
	}

}
