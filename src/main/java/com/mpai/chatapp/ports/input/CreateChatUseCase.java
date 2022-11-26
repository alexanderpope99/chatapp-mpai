package com.mpai.chatapp.ports.input;

import com.mpai.chatapp.domain.model.GroupChat;
import com.mpai.chatapp.domain.model.SimpleChat;


public interface CreateChatUseCase {

	SimpleChat createSimpleChat(SimpleChat simpleChat);

	GroupChat createGroupChat(GroupChat groupChat);

}
