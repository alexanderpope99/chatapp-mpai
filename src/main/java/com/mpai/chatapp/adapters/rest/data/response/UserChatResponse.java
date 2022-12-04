package com.mpai.chatapp.adapters.rest.data.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserChatResponse {

	protected UUID id;

	protected Set<UserResponse> contacts;

	protected List<MessageResponse> messages;

}
