package com.mpai.chatapp.ports.input;

import com.mpai.chatapp.domain.model.GroupChat;
import com.mpai.chatapp.domain.model.User;

import java.util.Set;
import java.util.UUID;

public interface AddUsersToGroupChatUseCase {

	GroupChat addUsersToGroupChat(UUID id, Set<User> users);

}
