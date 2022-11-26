package com.mpai.chatapp.adapters.rest.mapper;

import com.mpai.chatapp.adapters.rest.data.request.UserLoginRequest;
import com.mpai.chatapp.adapters.rest.data.request.UserRegisterRequest;
import com.mpai.chatapp.adapters.rest.data.request.UserUpdateRequest;
import com.mpai.chatapp.adapters.rest.data.response.UserModifiedResponse;
import com.mpai.chatapp.domain.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserRestMapper {

	User toUser(UserRegisterRequest userRegisterRequest);

	UserModifiedResponse toUserModifiedResponse(User user);

	User toUser(UserUpdateRequest userUpdateRequest);

	User toUser(UserLoginRequest userLoginRequest);

}
