package com.mpai.chatapp.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Message {

	private UUID id;
	private User sender;
	private LocalDateTime dateTime;
	private String content;

	public Message(User sender, LocalDateTime dateTime, String content) {
		this.sender = sender;
		this.dateTime = dateTime;
		this.content = content;
	}

	public User getSender() {
		return sender;
	}

	public LocalDateTime getDate() {
		return dateTime;
	}

	public String getContent() {
		return content;
	}

}
