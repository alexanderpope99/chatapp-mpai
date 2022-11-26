package com.mpai.chatapp.domain.model;

public class Notification {

	private Boolean isRead;
	private Chat chat;
	private Message message;

	public Notification(Boolean isRead, Chat chat, Message message) {
		this.isRead = isRead;
		this.chat = chat;
		this.message = message;
	}

}
