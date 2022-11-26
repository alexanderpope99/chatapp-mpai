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
public class UserLoginRequest {

	@NotEmpty(message = "Username is mandatory")
	private String username;

	@NotEmpty(message = "Password is mandatory")
	private String password;

}
