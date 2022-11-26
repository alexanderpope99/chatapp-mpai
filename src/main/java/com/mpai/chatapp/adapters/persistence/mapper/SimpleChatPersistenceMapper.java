package com.mpai.chatapp.adapters.persistence.mapper;

import com.mpai.chatapp.adapters.persistence.entity.SimpleChatEntity;
import com.mpai.chatapp.domain.model.SimpleChat;
import org.mapstruct.Mapper;

@Mapper
public interface SimpleChatPersistenceMapper {

	SimpleChat toSimpleChat(SimpleChatEntity simpleChat);

}
