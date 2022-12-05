package com.mpai.chatapp.adapters.rest.mapper;

import com.mpai.chatapp.adapters.rest.data.request.GroupChatUsers;
import com.mpai.chatapp.adapters.rest.data.request.GroupChatUsersRequest;
import com.mpai.chatapp.adapters.rest.data.request.ChatCreateRequest;
import com.mpai.chatapp.adapters.rest.data.response.GroupChatCreateResponse;
import com.mpai.chatapp.domain.model.GroupChat;
import com.mpai.chatapp.domain.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {UserService.class})
public interface GroupChatRestMapper {

	@Mapping(source = "usernames", target = "users")
	GroupChat toGroupChat(ChatCreateRequest chatCreateRequest);

	GroupChatCreateResponse toGroupChatCreateResponse(GroupChat groupChat);

	@Mapping(source = "usernames", target = "users")
	GroupChatUsers toGroupChatUsers(GroupChatUsersRequest groupChatUsersRequest);

}
