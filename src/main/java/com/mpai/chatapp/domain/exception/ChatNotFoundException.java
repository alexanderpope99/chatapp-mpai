package com.mpai.chatapp.domain.exception;

public class ChatNotFoundException extends RuntimeException {

	public ChatNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}
