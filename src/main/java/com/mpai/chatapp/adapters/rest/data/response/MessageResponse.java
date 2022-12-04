package com.mpai.chatapp.adapters.rest.data.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class MessageResponse {

	private UUID id;

	private String date;

	private String content;

	private UserModifiedResponse sender;

}
