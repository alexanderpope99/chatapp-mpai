package com.mpai.chatapp.adapters.persistence.mapper;

import com.mpai.chatapp.adapters.persistence.entity.GroupChatEntity;
import com.mpai.chatapp.domain.model.GroupChat;
import org.mapstruct.Mapper;

@Mapper
public interface GroupChatPersistenceMapper {

	GroupChat toGroupChat(GroupChatEntity groupChat);

}
