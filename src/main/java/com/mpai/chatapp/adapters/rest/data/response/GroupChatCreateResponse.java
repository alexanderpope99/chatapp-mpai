package com.mpai.chatapp.adapters.rest.data.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupChatCreateResponse {

	private UUID id;

	private String name;

	private UserModifiedResponse admin;

	private Set<UserModifiedResponse> users;

}

