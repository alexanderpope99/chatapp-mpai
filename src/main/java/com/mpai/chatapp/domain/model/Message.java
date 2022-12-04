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
	private LocalDateTime date;
	private String content;

	public Message(User sender, LocalDateTime date, String content) {
		this.sender = sender;
		this.date = date;
		this.content = content;
	}

	public User getSender() {
		return sender;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getContent() {
		return content;
	}

}
