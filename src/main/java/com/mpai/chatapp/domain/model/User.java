package com.mpai.chatapp.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class User {

	private UUID id;
	private String username;
	private String nickname;
	private String password;
	private Role role;

	public User(String username, String nickname, String password) {
		this.username = username;
		this.nickname = nickname;
		this.password = password;
	}

}
