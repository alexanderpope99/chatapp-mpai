package com.mpai.chatapp.ports.input;

import com.mpai.chatapp.domain.model.User;

public interface FindUserByUsernameUseCase {

	User findUserByUsername(String username);

}
