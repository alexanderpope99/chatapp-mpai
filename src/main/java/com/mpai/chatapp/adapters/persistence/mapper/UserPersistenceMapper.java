package com.mpai.chatapp.adapters.persistence.mapper;

import com.mpai.chatapp.adapters.persistence.entity.UserEntity;
import com.mpai.chatapp.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserPersistenceMapper {

	UserEntity toUserEntity(User user);

	User toUser(UserEntity userEntity);

}
