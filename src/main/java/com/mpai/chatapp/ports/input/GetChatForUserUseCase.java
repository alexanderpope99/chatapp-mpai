package com.mpai.chatapp.ports.input;

import com.mpai.chatapp.domain.model.Chat;

import java.util.List;

public interface GetChatForUserUseCase {

	List<Chat> getChatsForUser(String username);

}
