package com.mpai.chatapp.domain.service;

import com.mpai.chatapp.domain.exception.ChatNotFoundException;
import com.mpai.chatapp.domain.model.Chat;
import com.mpai.chatapp.domain.model.GroupChat;
import com.mpai.chatapp.domain.model.SimpleChat;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChatFactory {

	public static Chat buildChat(String type) {
		Chat chat;
		switch (type.toLowerCase()) {
			case "simple":
				chat = new SimpleChat();
				break;
			case "group":
				chat = new GroupChat();
				break;
			default:
				throw new ChatNotFoundException("This chat type doesn't exist");
		}
		return chat;

	}
}
