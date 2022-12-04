package com.mpai.chatapp.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class User {

	private UUID id;
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private Role role;
	private LocalDateTime lastSeen;
	private String avatar;

	public User(String username, String firstName, String lastName, String password) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.lastSeen = LocalDateTime.now();
		this.avatar = "https://upload.wikimedia.org/wikipedia/commons/f/f7/Facebook_default_male_avatar.gif";
	}


}
