package com.mpai.chatapp.adapters.rest.data.request;

import com.mpai.chatapp.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupChatUsers {

	private Set<User> users;

}
