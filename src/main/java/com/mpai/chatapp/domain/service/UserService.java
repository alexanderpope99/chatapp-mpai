package com.mpai.chatapp.domain.service;

import com.mpai.chatapp.domain.model.Role;
import com.mpai.chatapp.domain.model.User;
import com.mpai.chatapp.ports.input.*;
import com.mpai.chatapp.ports.output.UserOutputPort;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements
		RegisterUserUseCase,
		LoginUserUseCase,
		UpdateUserUseCase,
		FindUserByUsernameUseCase,
		AddContactToUserUseCase,
		GetContactsOfUserUseCase{

	private final UserOutputPort userOutputPort;

	private final PasswordEncoder passwordEncoder;

	@Override
	public User registerUser(User user) {
		Optional<User> foundUser = userOutputPort.findByUsername(user.getUsername());

		if (foundUser.isPresent())
			throw new EntityExistsException("User already exists");

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		user.setRole(Role.ROLE_USER);

		user = userOutputPort.saveUser(user);

		return user;
	}

	@Override
	public User loginUser(User user) {
		User foundUser = findUserByUsername(user.getUsername());

		return foundUser;
	}

	@Override
	public User updateUser(UUID id, User user) {

		if (user.getPassword() != null)
			user.setPassword(passwordEncoder.encode(user.getPassword()));

		user = userOutputPort.updateUser(id, user);

		return user;
	}

	@Override
	public User updateUser(String username, User user) {
		User foundUser = findUserByUsername(username);

		return this.updateUser(foundUser.getId(), user);
	}

	@Override
	public User findUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userOutputPort.findByUsername(username);

		if (!user.isPresent())
			throw new UsernameNotFoundException("User not found");

		return user.get();
	}

	@Override
	public User addContactToUser(String username, String usernameToAdd) {
		User user = findUserByUsername(username);
		User userToAdd = findUserByUsername(usernameToAdd);

		return userOutputPort.addContactToUser(user.getId(), userToAdd.getId());
	}

	@Override
	public Set<User> getContactsOfUser(String username) {
		User user = findUserByUsername(username);
		return userOutputPort.getContactsOfUser(user.getId());
	}
}
