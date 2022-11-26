package com.mpai.chatapp.adapters.rest.data.response;

import com.mpai.chatapp.domain.model.GroupChat;
import com.mpai.chatapp.domain.model.SimpleChat;

public interface UserChatResponseBuilderVisitor {

	UserSimpleChatResponse visitSimpleChat(SimpleChat simpleChat, String username);

	UserGroupChatResponse visitGroupChat(GroupChat groupChat, String username);

}
