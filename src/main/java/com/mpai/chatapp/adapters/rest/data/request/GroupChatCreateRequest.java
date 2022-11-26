package com.mpai.chatapp.adapters.rest.data.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupChatCreateRequest {

	@NotEmpty(message = "Name is mandatory")
	private String name;

	private String admin;

	@NotEmpty(message = "A List of users is mandatory")
	@Size(min = 2, message = "Minimum two users are required")
	private Set<String> usernames;

}
