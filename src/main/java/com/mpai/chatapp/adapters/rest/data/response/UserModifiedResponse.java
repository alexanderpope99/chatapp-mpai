package com.mpai.chatapp.adapters.rest.data.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModifiedResponse {

	private UUID id;

	private String username;

	private String firstName;

	private String lastName;

	private LocalDateTime lastSeen;

	private String avatar;

}
