package com.mpai.chatapp.adapters.rest.data.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupChatUsersRequest {

	@NotEmpty(message = "A List of users is mandatory")
	private Set<String> usernames;

}
