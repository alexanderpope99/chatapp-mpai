package com.mpai.chatapp.adapters.config;

import com.mpai.chatapp.domain.model.User;
import com.mpai.chatapp.domain.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserService userService;

	public UserDetailsServiceImpl(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userService.findUserByUsername(username);
		return UserDetailsImpl.build(user);
	}

}
