package com.mpai.chatapp.ports.input;

import com.mpai.chatapp.domain.model.GroupChat;

public interface UpdateGroupChatDetailsUseCase {

	GroupChat updateGroupChat(GroupChat groupChat);

}
