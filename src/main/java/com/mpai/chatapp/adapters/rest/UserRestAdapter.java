package com.mpai.chatapp.adapters.rest;

import com.mpai.chatapp.adapters.config.jwt.JwtUtils;
import com.mpai.chatapp.adapters.rest.data.request.UserUpdateRequest;
import com.mpai.chatapp.adapters.rest.data.response.UserModifiedResponse;
import com.mpai.chatapp.adapters.rest.mapper.UserRestMapper;
import com.mpai.chatapp.domain.model.User;
import com.mpai.chatapp.ports.input.UpdateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserRestAdapter {

	private final UpdateUserUseCase updateUserUseCase;

	private final UserRestMapper userRestMapper;

	private final JwtUtils jwtUtils;

	@PutMapping
	public ResponseEntity<UserModifiedResponse> updateUser(@RequestHeader(name = "Authorization") String token,
														   @RequestBody UserUpdateRequest userUpdateRequest) {
		String username = jwtUtils.getUserNameFromJwtToken(token.replace("Bearer ",""));

		User user = userRestMapper.toUser(userUpdateRequest);

		user = updateUserUseCase.updateUser(username, user);

		return new ResponseEntity<>(userRestMapper.toUserModifiedResponse(user), HttpStatus.OK);
	}

}
