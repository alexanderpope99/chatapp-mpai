package com.mpai.chatapp.ports.input;

import com.mpai.chatapp.domain.model.User;

public interface AddContactToUserUseCase {

	User addContactToUser(String username, String usernameToAdd);

}
