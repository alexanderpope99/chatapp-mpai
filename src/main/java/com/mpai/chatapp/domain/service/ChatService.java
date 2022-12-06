package com.mpai.chatapp.domain.service;

import com.mpai.chatapp.domain.model.*;
import com.mpai.chatapp.ports.input.*;
import com.mpai.chatapp.ports.output.ChatOutputPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ChatService implements
		CreateChatUseCase,
		AddUsersToGroupChatUseCase,
		RemoveUsersFromGroupChatUseCase,
		SendMessageUseCase,
		GetChatForUserUseCase {

	private final ChatOutputPort chatOutputPort;

	public GroupChat findById(UUID id) {
		return chatOutputPort.findGroupChatById(id);
	}

	@Override
	public SimpleChat createSimpleChat(SimpleChat simpleChat) {
		return chatOutputPort.saveSimpleChat(simpleChat);
	}

	@Override
	public GroupChat createGroupChat(GroupChat groupChat) {
		return chatOutputPort.saveGroupChat(groupChat);
	}

	@Override
	public GroupChat addUsersToGroupChat(UUID id, Set<User> users) {

		GroupChat groupChat = findById(id);

		for (User user : users) {
			if (!groupChat.hasUserById(user.getId()))
				groupChat.addUser(user);
		}

		return chatOutputPort.saveGroupChat(groupChat);
	}

	@Override
	public GroupChat removeUsersFromGroupChat(UUID id, Set<User> users) {

		GroupChat groupChat = findById(id);

		for (User user : users)
			groupChat.removeUser(user);

		return chatOutputPort.saveGroupChat(groupChat);

	}

	@Override
	public Chat sendMessage(UUID id, Message message) {
		Chat chat = chatOutputPort.findChatById(id);

		chat.sendMessage(message);

		if (chat instanceof GroupChat) {
			return chatOutputPort.saveGroupChat((GroupChat) chat);
		}
		else {
			SimpleChat simpleChat = chatOutputPort.saveSimpleChat((SimpleChat) chat);
			return simpleChat;
		}
	}

	@Override
	public List<Chat> getChatsForUser(String username) {
		Set<Chat> chats = new HashSet<>();
		chats.addAll(chatOutputPort.getChatsForUser(username));
		chats.addAll(chatOutputPort.getAdministeredChatsForUser(username));

		List<Chat> sortedChats = new ArrayList<>(chats);

		sortedChats.sort(Collections.reverseOrder());

		return sortedChats;
	}
}
