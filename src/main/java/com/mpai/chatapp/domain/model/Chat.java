package com.mpai.chatapp.domain.model;

import com.mpai.chatapp.adapters.rest.data.response.UserChatResponse;
import com.mpai.chatapp.adapters.rest.data.response.UserChatResponseBuilderVisitor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public abstract class Chat {

	protected UUID id;

	protected List<Message> chatHistory = new ArrayList<>();

	public abstract void sendMessage(Message message);

	public abstract Set<User> getUsers();

	public List<Message> getChatHistory() {
		return chatHistory;
	}

	public abstract UserChatResponse accept(UserChatResponseBuilderVisitor visitor, String username);


}
