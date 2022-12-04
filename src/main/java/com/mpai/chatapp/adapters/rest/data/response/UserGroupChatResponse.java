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
public class UserGroupChatResponse extends UserChatResponse {

	private String type = "group";

	private String name;

	private String avatar;

	private Set<UUID> admins;

	private List<MessageResponse> messages;

}
