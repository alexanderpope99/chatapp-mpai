package com.mpai.chatapp.ports.input;

import com.mpai.chatapp.domain.model.User;

import java.util.UUID;

public interface UpdateUserUseCase {

	User updateUser(UUID id, User user);

	User updateUser(String username, User user);

}
