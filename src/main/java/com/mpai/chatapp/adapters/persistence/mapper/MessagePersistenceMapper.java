package com.mpai.chatapp.adapters.persistence.mapper;

import com.mpai.chatapp.adapters.persistence.entity.MessageEntity;
import com.mpai.chatapp.domain.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MessagePersistenceMapper {

	MessageEntity toMessageEntity(Message message);

	Message toMessage(MessageEntity message);

}
