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
public class UserRegisterRequest {

	@NotEmpty(message = "Username may not be empty")
	private String username;

	@NotEmpty(message = "Nickname may not be empty")
	private String nickname;

	@NotEmpty(message = "Password may not be empty")
	private String password;

}
