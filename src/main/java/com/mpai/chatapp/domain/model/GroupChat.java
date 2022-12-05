package com.mpai.chatapp.domain.model;

import com.mpai.chatapp.adapters.rest.data.response.UserChatResponse;
import com.mpai.chatapp.adapters.rest.data.response.UserChatResponseBuilderVisitor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupChat extends Chat {

	private String name;

	private User admin;

	private Set<User> users;

	public Set<User> getUsers() {
		return users;
	}


	public void sendMessage(Message message) {
		this.chatHistory.add(message);
	}

	public void addUser(User user) {
		this.users.add(user);
	}

	public void removeUser(User user) {
		this.users.removeIf(userFromSet -> user.getId() == userFromSet.getId());
	}

	public boolean hasUserById(UUID id) {
		for (User user : users)
			if (user.getId().equals(id))
				return true;
		return false;
	}

	@Override
	public UserChatResponse accept(UserChatResponseBuilderVisitor visitor, String username) {
		return visitor.visitGroupChat(this, username);
	}

}
