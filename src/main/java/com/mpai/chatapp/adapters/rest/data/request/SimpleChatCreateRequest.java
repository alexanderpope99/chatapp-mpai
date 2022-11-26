package com.mpai.chatapp.adapters.rest.data.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleChatCreateRequest {

	private String nickname;

	private String initialUsername;

	@NotEmpty(message = "Username of second user is mandatory")
	private String username;

}
