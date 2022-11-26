package com.mpai.chatapp.adapters.rest.data.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MessageResponse {

	private LocalDateTime dateTime;

	private String content;

	private UserModifiedResponse sender;

}
