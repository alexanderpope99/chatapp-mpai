package com.mpai.chatapp.adapters.rest;

import com.mpai.chatapp.adapters.config.jwt.JwtUtils;
import com.mpai.chatapp.adapters.rest.data.request.AddContactRequest;
import com.mpai.chatapp.adapters.rest.data.request.UserUpdateRequest;
import com.mpai.chatapp.adapters.rest.data.response.UserModifiedResponse;
import com.mpai.chatapp.adapters.rest.data.response.UserResponse;
import com.mpai.chatapp.adapters.rest.mapper.UserRestMapper;
import com.mpai.chatapp.domain.model.User;
import com.mpai.chatapp.ports.input.AddContactToUserUseCase;
import com.mpai.chatapp.ports.input.FindUserByUsernameUseCase;
import com.mpai.chatapp.ports.input.GetContactsOfUserUseCase;
import com.mpai.chatapp.ports.input.UpdateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserRestAdapter {

	private final UpdateUserUseCase updateUserUseCase;

	private final FindUserByUsernameUseCase findUserByUsernameUseCase;

	private final AddContactToUserUseCase addContactToUserUseCase;

	private final GetContactsOfUserUseCase getContactsOfUserUseCase;

	private final UserRestMapper userRestMapper;

	private final JwtUtils jwtUtils;

	@GetMapping("/whoami")
	public ResponseEntity<UserModifiedResponse> getUser(@RequestHeader(name = "Authorization") String token) {
		String username = jwtUtils.getUserNameFromJwtToken(token.replace("Bearer ", ""));

		User user = findUserByUsernameUseCase.findUserByUsername(username);

		return new ResponseEntity<>(userRestMapper.toUserModifiedResponse(user), HttpStatus.OK);
	}

	@PostMapping("/contacts")
	public ResponseEntity<Set<UserResponse>> addContact(@RequestHeader(name = "Authorization") String token,
													   @RequestBody AddContactRequest addContactRequest) {
		String username = jwtUtils.getUserNameFromJwtToken(token.replace("Bearer ", ""));

		User user = addContactToUserUseCase.addContactToUser(username, addContactRequest.getUsername());

		Set<UserResponse> contactsResponse = new HashSet<>();

		for (User contact : getContactsOfUserUseCase.getContactsOfUser(user.getUsername()))
			contactsResponse.add(userRestMapper.toUserResponse(contact));

		return new ResponseEntity<>(contactsResponse, HttpStatus.OK);
	}

	@GetMapping("/contacts")
	public ResponseEntity<Set<UserResponse>> getContacts(@RequestHeader(name = "Authorization") String token) {
		String username = jwtUtils.getUserNameFromJwtToken(token.replace("Bearer ", ""));

		User user = findUserByUsernameUseCase.findUserByUsername(username);

		Set<UserResponse> contactsResponse = new HashSet<>();

		for (User contact : getContactsOfUserUseCase.getContactsOfUser(user.getUsername()))
			contactsResponse.add(userRestMapper.toUserResponse(contact));

		return new ResponseEntity<>(contactsResponse, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<UserModifiedResponse> updateUser(@RequestHeader(name = "Authorization") String token,
														   @RequestBody UserUpdateRequest userUpdateRequest) {
		String username = jwtUtils.getUserNameFromJwtToken(token.replace("Bearer ", ""));

		User user = userRestMapper.toUser(userUpdateRequest);

		user = updateUserUseCase.updateUser(username, user);

		return new ResponseEntity<>(userRestMapper.toUserModifiedResponse(user), HttpStatus.OK);
	}

}
