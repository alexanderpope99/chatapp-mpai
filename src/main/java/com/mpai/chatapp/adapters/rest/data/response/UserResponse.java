package com.mpai.chatapp.adapters.rest.data.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {

	private UUID id;

	private String username;

	private String firstName;

	private String lastName;

	private String lastSeen;

	private String avatar;
}
