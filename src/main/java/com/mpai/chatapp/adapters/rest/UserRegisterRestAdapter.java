package com.mpai.chatapp.adapters.rest;

import com.mpai.chatapp.adapters.config.jwt.JwtUtils;
import com.mpai.chatapp.adapters.rest.data.request.UserLoginRequest;
import com.mpai.chatapp.adapters.rest.data.request.UserRegisterRequest;
import com.mpai.chatapp.adapters.rest.data.response.JwtResponse;
import com.mpai.chatapp.adapters.rest.data.response.UserLoginResponse;
import com.mpai.chatapp.adapters.rest.data.response.UserModifiedResponse;
import com.mpai.chatapp.adapters.rest.mapper.UserRestMapper;
import com.mpai.chatapp.domain.model.User;
import com.mpai.chatapp.ports.input.LoginUserUseCase;
import com.mpai.chatapp.ports.input.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class UserRegisterRestAdapter {

	private final RegisterUserUseCase registerUserUseCase;

	private final LoginUserUseCase loginUserUseCase;

	private final UserRestMapper userRestMapper;

	private final AuthenticationManager authenticationManager;

	private final JwtUtils jwtUtils;

	@PostMapping(value = "/register")
	public ResponseEntity<UserModifiedResponse> registerUser(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
		User user = userRestMapper.toUser(userRegisterRequest);

		user = registerUserUseCase.registerUser(user);

		return new ResponseEntity<>(userRestMapper.toUserModifiedResponse(user), HttpStatus.CREATED);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<UserLoginResponse> loginUser(@RequestBody @Valid UserLoginRequest userLoginRequest, final HttpServletRequest request) {
		User user = userRestMapper.toUser(userLoginRequest);

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(), userLoginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		user = loginUserUseCase.loginUser(user);

		UserLoginResponse userLoginResponse = userRestMapper.toUserLoginResponse(user);

		userLoginResponse.setToken(jwtUtils.generateJwtToken(authentication));

		return ResponseEntity.ok()
				.body(userLoginResponse);
	}

}
