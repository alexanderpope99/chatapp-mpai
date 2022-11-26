package com.mpai.chatapp.adapters.rest.data.response;

import com.mpai.chatapp.domain.model.GroupChat;
import com.mpai.chatapp.domain.model.Message;
import com.mpai.chatapp.domain.model.SimpleChat;
import com.mpai.chatapp.domain.model.User;

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
			if (user.getUsername().equals(username))
				continue;
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

		UserModifiedResponse receiver = new UserModifiedResponse();
		receiver.setId(simpleChat.getUser2().getId());
		receiver.setUsername(simpleChat.getUser2().getUsername());
		receiver.setNickname(simpleChat.getUser2().getNickname());

		response.setReceiver(receiver);

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
