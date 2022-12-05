package com.mpai.chatapp.adapters.rest;

import com.mpai.chatapp.adapters.CreateChatFacade;
import com.mpai.chatapp.adapters.rest.data.request.ChatCreateRequest;
import com.mpai.chatapp.domain.model.Chat;
import com.mpai.chatapp.domain.model.GroupChat;
import com.mpai.chatapp.domain.model.SimpleChat;
import com.mpai.chatapp.domain.model.User;
import com.mpai.chatapp.domain.service.ChatFactory;
import com.mpai.chatapp.ports.input.CreateChatUseCase;
import com.mpai.chatapp.ports.input.FindUserByUsernameUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Component
@AllArgsConstructor
public class CreateChatFacadeImpl implements CreateChatFacade {

	private final FindUserByUsernameUseCase findUserByUsernameUseCase;

	private final CreateChatUseCase createChatUseCase;

	@Override
	public Chat createChat(String type, ChatCreateRequest chatCreateRequest) {
		Chat chat = ChatFactory.buildChat(type);

		if (chat instanceof SimpleChat) {
			SimpleChat simpleChat = (SimpleChat) chat;
			Iterator<String> iterator = chatCreateRequest.getUsernames().iterator();
			simpleChat.setUser1(findUserByUsernameUseCase.findUserByUsername(chatCreateRequest.getAdmin()));
			simpleChat.setUser2(findUserByUsernameUseCase.findUserByUsername(iterator.next().toString()));
			return createChatUseCase.createSimpleChat(simpleChat);
		} else if (chat instanceof GroupChat) {
			GroupChat groupChat = (GroupChat) chat;
			groupChat.setName(chatCreateRequest.getName());
			groupChat.setAdmin(findUserByUsernameUseCase.findUserByUsername(chatCreateRequest.getAdmin()));
			Set<User> users = new HashSet<>();

			for (String username : chatCreateRequest.getUsernames())
				users.add(findUserByUsernameUseCase.findUserByUsername(username));

			groupChat.setUsers(users);

			return createChatUseCase.createGroupChat(groupChat);
		} else
			return null;

	}
}
