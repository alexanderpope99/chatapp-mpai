package com.mpai.chatapp.adapters.persistence;

import com.mpai.chatapp.adapters.persistence.entity.*;
import com.mpai.chatapp.adapters.persistence.mapper.GroupChatPersistenceMapper;
import com.mpai.chatapp.adapters.persistence.mapper.MessagePersistenceMapper;
import com.mpai.chatapp.adapters.persistence.mapper.SimpleChatPersistenceMapper;
import com.mpai.chatapp.adapters.persistence.mapper.UserPersistenceMapper;
import com.mpai.chatapp.adapters.persistence.repository.*;
import com.mpai.chatapp.domain.exception.ChatNotFoundException;
import com.mpai.chatapp.domain.exception.UserNotFoundException;
import com.mpai.chatapp.domain.model.*;
import com.mpai.chatapp.ports.output.ChatOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class ChatPersistenceAdapter implements ChatOutputPort {

	private final ChatRepository chatRepository;

	private final SimpleChatRepository simpleChatRepository;

	private final GroupChatRepository groupChatRepository;

	private final MessageRepository messageRepository;

	private final UserRepository userRepository;

	private final UserPersistenceMapper userPersistenceMapper;

	private final SimpleChatPersistenceMapper simpleChatPersistenceMapper;

	private final GroupChatPersistenceMapper groupChatPersistenceMapper;

	private final MessagePersistenceMapper messagePersistenceMapper;


	@Override
	public Chat findChatById(UUID id) {
		Optional<ChatEntity> chatEntity = chatRepository.findById(id);

		if (!chatEntity.isPresent())
			throw new ChatNotFoundException("Chat not found");

		if (chatEntity.get().getClass().equals(GroupChatEntity.class)) {
			GroupChat groupChat = groupChatPersistenceMapper.toGroupChat(groupChatRepository.findById(id).get());
			groupChat.setId(chatEntity.get().getId());
			List<Message> chatHistory = new ArrayList<>();
			for (MessageEntity messageEntity : chatEntity.get().getMessages())
				chatHistory.add(messagePersistenceMapper.toMessage(messageEntity));
			groupChat.setChatHistory(chatHistory);
			return groupChat;
		} else {
			SimpleChat simpleChat = simpleChatPersistenceMapper.toSimpleChat(simpleChatRepository.findById(id).get());
			simpleChat.setId(chatEntity.get().getId());
			List<Message> chatHistory = new ArrayList<>();
			for (MessageEntity messageEntity : chatEntity.get().getMessages())
				chatHistory.add(messagePersistenceMapper.toMessage(messageEntity));
			simpleChat.setChatHistory(chatHistory);
			Iterator<UserEntity> iterator = chatEntity.get().getUsers().iterator();
			simpleChat.setUser1(userPersistenceMapper.toUser(iterator.next()));
			simpleChat.setUser2(userPersistenceMapper.toUser(iterator.next()));
			return simpleChat;
		}
	}


	@Override
	public GroupChat findGroupChatById(UUID id) {

		Optional<GroupChatEntity> groupChatEntity = groupChatRepository.findById(id);

		if (!groupChatEntity.isPresent())
			throw new ChatNotFoundException("Chat not found");

		return groupChatPersistenceMapper.toGroupChat(groupChatEntity.get());
	}

	@Override
	public SimpleChat saveSimpleChat(SimpleChat simpleChat) {
		SimpleChatEntity simpleChatEntity;
		if (simpleChat.getId() == null)
			simpleChatEntity = new SimpleChatEntity();
		else
			simpleChatEntity = simpleChatRepository.findById(simpleChat.getId()).get();

		Set<UserEntity> userEntities = new HashSet<>();
		List<MessageEntity> messageEntities = new ArrayList<>();

		userEntities.add(userPersistenceMapper.toUserEntity(simpleChat.getUser1()));
		userEntities.add(userPersistenceMapper.toUserEntity(simpleChat.getUser2()));

		for (Message message : simpleChat.getChatHistory()) {
			MessageEntity messageEntity;
			if (message.getId() == null) {
				messageEntity = new MessageEntity();
				messageEntity.setSender(userPersistenceMapper.toUserEntity(message.getSender()));
				messageEntity.setDate(message.getDate());
				messageEntity.setContent(message.getContent());
				messageEntity.setChat(simpleChatEntity);
			} else
				messageEntity = messageRepository.findById(message.getId()).get();
			messageEntity = messageRepository.save(messageEntity);
			messageEntities.add(messageEntity);
		}

		simpleChatEntity.setUsers(userEntities);
		simpleChatEntity.setMessages(messageEntities);

		simpleChatEntity = simpleChatRepository.save(simpleChatEntity);

		SimpleChat newSimpleChat = simpleChatPersistenceMapper.toSimpleChat(simpleChatRepository.findById(simpleChatEntity.getId()).get());
		newSimpleChat.setId(simpleChatEntity.getId());
		List<Message> chatHistory = new ArrayList<>();
		for (MessageEntity messageEntity : simpleChatEntity.getMessages())
			chatHistory.add(messagePersistenceMapper.toMessage(messageEntity));
		newSimpleChat.setChatHistory(chatHistory);
		Iterator<UserEntity> iterator = simpleChatEntity.getUsers().iterator();
		newSimpleChat.setUser1(userPersistenceMapper.toUser(iterator.next()));
		newSimpleChat.setUser2(userPersistenceMapper.toUser(iterator.next()));
		return newSimpleChat;
	}


	@Override
	public GroupChat saveGroupChat(GroupChat groupChat) {
		GroupChatEntity groupChatEntity;
		if (groupChat.getId() == null)
			groupChatEntity = new GroupChatEntity();
		else
			groupChatEntity = groupChatRepository.findById(groupChat.getId()).get();


		Set<UserEntity> userEntities = new HashSet<>();
		List<MessageEntity> messageEntities = new ArrayList<>();

		for (User user : groupChat.getUsers())
			userEntities.add(userPersistenceMapper.toUserEntity(user));

		for (Message message : groupChat.getChatHistory()) {
			MessageEntity messageEntity;
			if (message.getId() == null) {
				messageEntity = new MessageEntity();
				messageEntity.setSender(userPersistenceMapper.toUserEntity(message.getSender()));
				messageEntity.setDate(message.getDate());
				messageEntity.setContent(message.getContent());
				messageEntity.setChat(groupChatEntity);
			} else
				messageEntity = messageRepository.findById(message.getId()).get();
			messageEntity = messageRepository.save(messageEntity);
			messageEntities.add(messageEntity);
		}

		groupChatEntity.setUsers(userEntities);
		groupChatEntity.setName(groupChat.getName());
		groupChatEntity.setAdmin(userPersistenceMapper.toUserEntity(groupChat.getAdmin()));
		groupChatEntity.setMessages(messageEntities);

		groupChatEntity = groupChatRepository.save(groupChatEntity);

		GroupChat newGroupChat = groupChatPersistenceMapper.toGroupChat(groupChatRepository.findById(groupChatEntity.getId()).get());
		newGroupChat.setId(groupChatEntity.getId());
		List<Message> chatHistory = new ArrayList<>();
		for (MessageEntity messageEntity : groupChatEntity.getMessages())
			chatHistory.add(messagePersistenceMapper.toMessage(messageEntity));
		newGroupChat.setChatHistory(chatHistory);
		return newGroupChat;

	}

	@Override
	public Set<Chat> getChatsForUser(String username) {
		Optional<UserEntity> userEntity = userRepository.findByUsername(username);

		if (!userEntity.isPresent())
			throw new UserNotFoundException("User not found");

		Set<Chat> chats = new HashSet<>();

		for (ChatEntity chatEntity : userEntity.get().getChats()) {
			List<Message> messages = new ArrayList<>();
			if (chatEntity.getClass().equals(SimpleChatEntity.class)) {
				SimpleChatEntity simpleChatEntity = simpleChatRepository.findById(chatEntity.getId()).get();
				Iterator<UserEntity> iterator = simpleChatEntity.getUsers().iterator();
				SimpleChat simpleChat = new SimpleChat(
						simpleChatEntity.getStartedOn(),
						userPersistenceMapper.toUser(iterator.next()),
						userPersistenceMapper.toUser(iterator.next()));
				simpleChat.setId(simpleChatEntity.getId());
				for (MessageEntity messageEntity : simpleChatEntity.getMessages())
					messages.add(messagePersistenceMapper.toMessage(messageEntity));
				simpleChat.setChatHistory(messages);
				chats.add(simpleChat);
			} else {
				GroupChatEntity groupChatEntity = groupChatRepository.findById(chatEntity.getId()).get();
				if (!groupChatEntity.getAdmin().getUsername().equals(username)) {
					Set<User> users = new HashSet<>();
					for (UserEntity searchedUserEntity : groupChatEntity.getUsers())
						users.add(userPersistenceMapper.toUser(searchedUserEntity));
					GroupChat groupChat = new GroupChat(groupChatEntity.getName(),
							userPersistenceMapper.toUser(groupChatEntity.getAdmin()), users);
					groupChat.setId(groupChatEntity.getId());
					for (MessageEntity messageEntity : groupChatEntity.getMessages())
						messages.add(messagePersistenceMapper.toMessage(messageEntity));
					groupChat.setChatHistory(messages);
					chats.add(groupChat);
				}
			}
		}

		return chats;
	}

	@Override
	public Set<Chat> getAdministeredChatsForUser(String username) {
		Optional<UserEntity> userEntity = userRepository.findByUsername(username);

		if (!userEntity.isPresent())
			throw new UserNotFoundException("User not found");

		Set<Chat> chats = new HashSet<>();

		for (ChatEntity chatEntity : userEntity.get().getAdministeredGroupChats()) {
			List<Message> messages = new ArrayList<>();
			GroupChatEntity groupChatEntity = groupChatRepository.findById(chatEntity.getId()).get();
			Set<User> users = new HashSet<>();
			for (UserEntity searchedUserEntity : groupChatEntity.getUsers())
				users.add(userPersistenceMapper.toUser(searchedUserEntity));
			GroupChat groupChat = new GroupChat(groupChatEntity.getName(),
					userPersistenceMapper.toUser(groupChatEntity.getAdmin()), users);
			groupChat.setId(groupChatEntity.getId());
			for (MessageEntity messageEntity : groupChatEntity.getMessages())
				messages.add(messagePersistenceMapper.toMessage(messageEntity));
			groupChat.setChatHistory(messages);
			chats.add(groupChat);
		}

		return chats;
	}

}
