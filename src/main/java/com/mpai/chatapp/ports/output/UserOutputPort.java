package com.mpai.chatapp.ports.output;

import com.mpai.chatapp.domain.model.User;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserOutputPort {

	User saveUser(User user);

	Optional<User> findById(UUID id);

	Optional<User> findByUsername(String username);

	User updateUser(UUID id, User user);

	void deleteUserById(UUID id);

	User addContactToUser(UUID id, UUID id2);

	Set<User> getContactsOfUser(UUID id);
}
