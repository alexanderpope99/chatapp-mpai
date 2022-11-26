package com.mpai.chatapp.ports.input;

import com.mpai.chatapp.domain.model.User;

public interface LoginUserUseCase {

	User loginUser(User user);

}
