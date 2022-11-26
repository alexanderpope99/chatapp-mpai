package com.mpai.chatapp.adapters.rest.mapper;

import com.mpai.chatapp.adapters.rest.data.request.SimpleChatCreateRequest;
import com.mpai.chatapp.adapters.rest.data.response.SimpleChatCreateResponse;
import com.mpai.chatapp.domain.model.SimpleChat;
import com.mpai.chatapp.domain.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = { UserService.class })
public interface SimpleChatRestMapper {

	@Mapping(source = "initialUsername", target = "user1")
	@Mapping(source = "username", target = "user2")
	SimpleChat toSimpleChat(SimpleChatCreateRequest simpleChatCreateRequest);

	SimpleChatCreateResponse toSimpleChatCreateResponse(SimpleChat simpleChat);

}
