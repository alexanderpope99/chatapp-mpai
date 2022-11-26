package com.mpai.chatapp.ports.input;

import com.mpai.chatapp.domain.model.User;

public interface RegisterUserUseCase {

	User registerUser(User user);

}
