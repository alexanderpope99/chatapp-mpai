package com.mpai.chatapp.adapters.rest.data.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserSimpleChatResponse extends UserChatResponse {

	private String nickname;

	private UserModifiedResponse user1;

	private UserModifiedResponse user2;

	private List<MessageResponse> messages;

}
