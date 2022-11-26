package com.mpai.chatapp.adapters.rest.data.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserGroupChatResponse extends UserChatResponse {

	private String name;

	private boolean isAdmin;

	private Set<UserResponse> users;

	private List<MessageResponse> messages;

}
