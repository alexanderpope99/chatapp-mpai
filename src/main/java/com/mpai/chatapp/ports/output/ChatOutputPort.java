package com.mpai.chatapp.ports.output;

import com.mpai.chatapp.domain.model.Chat;
import com.mpai.chatapp.domain.model.GroupChat;
import com.mpai.chatapp.domain.model.SimpleChat;

import java.util.Set;
import java.util.UUID;

public interface ChatOutputPort {

	Chat findChatById(UUID id);

	GroupChat findGroupChatById(UUID id);

	SimpleChat saveSimpleChat(SimpleChat simpleChat);

	GroupChat saveGroupChat(GroupChat groupChat);

	Set<Chat> getChatsForUser(String username);

	Set<Chat> getAdministeredChatsForUser(String username);

}
