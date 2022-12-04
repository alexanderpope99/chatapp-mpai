package com.mpai.chatapp.ports.input;

import com.mpai.chatapp.domain.model.User;

import java.util.Set;

public interface GetContactsOfUserUseCase {

	Set<User> getContactsOfUser(String username);

}
