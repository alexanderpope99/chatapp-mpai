package com.mpai.chatapp.adapters.rest.data.response;

import com.mpai.chatapp.domain.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserChatResponseBuilderVisitorImpl implements UserChatResponseBuilderVisitor {
	@Override
	public UserGroupChatResponse visitGroupChat(GroupChat groupChat, String username) {
		UserGroupChatResponse response =
				new UserGroupChatResponse();
		response.setId(groupChat.getId());
		response.setName(groupChat.getName());
		response.setAdmin(groupChat.getAdmin().getUsername().equals(username));

		Set<UserResponse> usersResponse = new HashSet<>();

		for (User user : groupChat.getUsers()) {
			UserResponse userResponse = new UserResponse();
			userResponse.setId(user.getId());
			userResponse.setUsername(user.getUsername());
			userResponse.setNickname(user.getNickname());
			userResponse.setAdmin(user.getUsername().equals(groupChat.getAdmin().getUsername()));
			usersResponse.add(userResponse);
		}

		response.setUsers(usersResponse);

		List<MessageResponse> messagesResponse = new ArrayList<>();

		for (Message message : groupChat.getChatHistory()) {
			MessageResponse messageResponse = new MessageResponse();
			messageResponse.setContent(message.getContent());
			messageResponse.setDateTime(message.getDateTime());
			UserModifiedResponse userResponse = new UserModifiedResponse();
			User sender = message.getSender();
			userResponse.setId(sender.getId());
			userResponse.setUsername(sender.getUsername());
			userResponse.setNickname(sender.getNickname());
			messageResponse.setSender(userResponse);
			messagesResponse.add(messageResponse);
		}

		response.setMessages(messagesResponse);

		return response;
	}

	@Override
	public UserSimpleChatResponse visitSimpleChat(SimpleChat simpleChat, String username) {
		UserSimpleChatResponse response =
				new UserSimpleChatResponse();
		response.setId(simpleChat.getId());
		response.setStartedOn(simpleChat.getStartedOn());

		UserModifiedResponse userResponse1 = new UserModifiedResponse();
		userResponse1.setId(simpleChat.getUser1().getId());
		userResponse1.setUsername(simpleChat.getUser1().getUsername());
		userResponse1.setNickname(simpleChat.getUser1().getNickname());

		UserModifiedResponse userResponse2 = new UserModifiedResponse();
		userResponse2.setId(simpleChat.getUser2().getId());
		userResponse2.setUsername(simpleChat.getUser2().getUsername());
		userResponse2.setNickname(simpleChat.getUser2().getNickname());

		response.setUser1(userResponse1);
		response.setUser2(userResponse2);

		List<MessageResponse> messagesResponse = new ArrayList<>();

		for (Message message : simpleChat.getChatHistory()) {
			MessageResponse messageResponse = new MessageResponse();
			messageResponse.setContent(message.getContent());
			messageResponse.setDateTime(message.getDateTime());
			UserModifiedResponse userResponse = new UserModifiedResponse();
			User sender = message.getSender();
			userResponse.setId(sender.getId());
			userResponse.setUsername(sender.getUsername());
			userResponse.setNickname(sender.getNickname());
			messageResponse.setSender(userResponse);
			messagesResponse.add(messageResponse);
		}

		response.setMessages(messagesResponse);

		return response;
	}
}
