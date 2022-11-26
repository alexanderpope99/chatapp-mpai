package com.mpai.chatapp.adapters.rest.data.response;

import com.mpai.chatapp.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponse {

	private UUID id;

	private String username;

	private String nickname;

	private Role role;

}
