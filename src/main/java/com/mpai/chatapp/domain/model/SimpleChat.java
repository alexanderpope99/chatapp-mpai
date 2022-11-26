package com.mpai.chatapp.domain.model;

import com.mpai.chatapp.adapters.rest.data.response.UserChatResponse;
import com.mpai.chatapp.adapters.rest.data.response.UserChatResponseBuilderVisitor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class SimpleChat extends Chat {

	private LocalDateTime startedOn;

	private User user1;

	private User user2;

	public Set<User> getUsers() {
		Set<User> set = new HashSet<>();
		set.add(user1);
		set.add(user2);
		return set;
	}


	public void sendMessage(Message message) {
		this.chatHistory.add(message);
	}

	@Override
	public UserChatResponse accept(UserChatResponseBuilderVisitor visitor, String username) {
		return visitor.visitSimpleChat(this, username);
	}

}
