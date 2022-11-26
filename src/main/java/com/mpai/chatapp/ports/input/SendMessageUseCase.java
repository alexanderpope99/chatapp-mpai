package com.mpai.chatapp.ports.input;

import com.mpai.chatapp.domain.model.Chat;
import com.mpai.chatapp.domain.model.Message;

import java.util.UUID;

public interface SendMessageUseCase {

	Chat sendMessage(UUID id, Message message);

}
