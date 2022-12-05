package com.mpai.chatapp.adapters;

import com.mpai.chatapp.adapters.rest.data.request.ChatCreateRequest;
import com.mpai.chatapp.domain.model.Chat;

public interface CreateChatFacade {

	Chat createChat(String type, ChatCreateRequest chatCreateRequest);

}
