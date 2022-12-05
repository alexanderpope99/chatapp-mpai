package com.mpai.chatapp.adapters.rest.data.response;

import com.mpai.chatapp.domain.model.GroupChat;
import com.mpai.chatapp.domain.model.Message;
import com.mpai.chatapp.domain.model.SimpleChat;
import com.mpai.chatapp.domain.model.User;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class UserChatResponseBuilderVisitorImpl implements UserChatResponseBuilderVisitor {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	@Override
	public UserGroupChatResponse visitGroupChat(GroupChat groupChat, String username) {
		UserGroupChatResponse response =
				new UserGroupChatResponse();
		response.setId(groupChat.getId());
		response.setName(groupChat.getName());
		response.setAvatar("https://upload.wikimedia.org/wikipedia/commons/f/f7/Facebook_default_male_avatar.gif");

		Set<UserResponse> usersResponse = new HashSet<>();

		Set<User> allUsers = groupChat.getUsers();
		allUsers.add(groupChat.getAdmin());

		for (User user : allUsers) {
			UserResponse userResponse = new UserResponse();
			userResponse.setId(user.getId());
			userResponse.setUsername(user.getUsername());
			userResponse.setFirstName(user.getFirstName());
			userResponse.setLastName(user.getLastName());
			userResponse.setLastSeen(user.getLastSeen().format(formatter));
			userResponse.setAvatar(user.getAvatar());
			usersResponse.add(userResponse);
		}

		response.setContacts(usersResponse);

		Set<UUID> admins = new HashSet<>();
		admins.add(groupChat.getAdmin().getId());
		response.setAdmins(admins);

		List<MessageResponse> messagesResponse = new ArrayList<>();

		for (Message message : groupChat.getChatHistory()) {
			MessageResponse messageResponse = new MessageResponse();
			messageResponse.setId(message.getId());
			messageResponse.setContent(message.getContent());
			messageResponse.setDate(message.getDate().format(formatter));
			UserModifiedResponse userResponse = new UserModifiedResponse();
			User sender = message.getSender();
			userResponse.setId(sender.getId());
			userResponse.setUsername(sender.getUsername());
			userResponse.setFirstName(sender.getFirstName());
			userResponse.setLastName(sender.getLastName());
			userResponse.setLastSeen(sender.getLastSeen());
			userResponse.setAvatar(sender.getAvatar());
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

		Set<UserResponse> usersResponse = new HashSet<>();


		UserResponse chatSender = new UserResponse();
		chatSender.setId(simpleChat.getUser1().getId());
		chatSender.setUsername(simpleChat.getUser1().getUsername());
		chatSender.setFirstName(simpleChat.getUser1().getFirstName());
		chatSender.setLastName(simpleChat.getUser1().getLastName());
		chatSender.setLastSeen(simpleChat.getUser1().getLastSeen().format(formatter));
		chatSender.setAvatar(simpleChat.getUser1().getAvatar());
		usersResponse.add(chatSender);

		UserResponse receiver = new UserResponse();
		receiver.setId(simpleChat.getUser2().getId());
		receiver.setUsername(simpleChat.getUser2().getUsername());
		receiver.setFirstName(simpleChat.getUser2().getFirstName());
		receiver.setLastName(simpleChat.getUser2().getLastName());
		receiver.setLastSeen(simpleChat.getUser2().getLastSeen().format(formatter));
		receiver.setAvatar(simpleChat.getUser2().getAvatar());
		usersResponse.add(receiver);

		response.setContacts(usersResponse);

		List<MessageResponse> messagesResponse = new ArrayList<>();

		for (Message message : simpleChat.getChatHistory()) {
			MessageResponse messageResponse = new MessageResponse();
			messageResponse.setId(message.getId());
			messageResponse.setContent(message.getContent());
			messageResponse.setDate(message.getDate().format(formatter));
			UserModifiedResponse userResponse = new UserModifiedResponse();
			User sender = message.getSender();
			userResponse.setId(sender.getId());
			userResponse.setUsername(sender.getUsername());
			userResponse.setFirstName(sender.getLastName());
			userResponse.setLastName(sender.getLastName());
			userResponse.setLastSeen(sender.getLastSeen());
			userResponse.setAvatar(sender.getAvatar());
			messageResponse.setSender(userResponse);
			messagesResponse.add(messageResponse);
		}

		response.setMessages(messagesResponse);

		return response;
	}
}
