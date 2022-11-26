package com.mpai.chatapp.adapters.rest.mapper;

import com.mpai.chatapp.adapters.rest.data.request.MessageRequest;
import com.mpai.chatapp.domain.model.Message;
import com.mpai.chatapp.domain.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = { UserService.class })
public interface MessageRestMapper {

	@Mapping(source = "username", target = "sender")
	Message toMessage(MessageRequest messageRequest);

}
