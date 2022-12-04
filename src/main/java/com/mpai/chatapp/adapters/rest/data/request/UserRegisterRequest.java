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

	@NotEmpty(message = "First Name may not be empty")
	private String firstName;

	@NotEmpty(message = "Last Name may not be empty")
	private String lastName;

	@NotEmpty(message = "Password may not be empty")
	private String password;

}
