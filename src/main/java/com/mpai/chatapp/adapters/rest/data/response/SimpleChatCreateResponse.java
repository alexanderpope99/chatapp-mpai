package com.mpai.chatapp.adapters.rest.data.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleChatCreateResponse {

	private UUID id;

	private LocalDateTime startedOn;

	private UserModifiedResponse user1;

	private UserModifiedResponse user2;

}
