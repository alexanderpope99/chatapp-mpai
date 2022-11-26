package com.mpai.chatapp.ports.input;

import com.mpai.chatapp.domain.model.Chat;

import java.util.Set;

public interface GetChatForUserUseCase {

	Set<Chat> getChatsForUser(String username);

}
