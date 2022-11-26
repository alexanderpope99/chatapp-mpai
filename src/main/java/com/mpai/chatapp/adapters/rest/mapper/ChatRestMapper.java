package com.mpai.chatapp.adapters.rest.mapper;

import com.mpai.chatapp.adapters.rest.data.response.ChatResponse;
import com.mpai.chatapp.domain.model.Chat;
import org.mapstruct.Mapper;

@Mapper
public interface ChatRestMapper {

	ChatResponse toChatResponse(Chat chat);

}
