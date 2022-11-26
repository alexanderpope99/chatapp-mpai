package com.mpai.chatapp.adapters.rest.data.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserSimpleChatResponse extends UserChatResponse {

	private LocalDateTime startedOn;

	private UserModifiedResponse receiver;

	private List<MessageResponse> messages;

}
