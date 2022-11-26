package com.mpai.chatapp.adapters.persistence;

import com.mpai.chatapp.adapters.persistence.entity.UserEntity;
import com.mpai.chatapp.adapters.persistence.mapper.ObjectModifier;
import com.mpai.chatapp.adapters.persistence.mapper.UserPersistenceMapper;
import com.mpai.chatapp.adapters.persistence.repository.UserRepository;
import com.mpai.chatapp.domain.model.User;
import com.mpai.chatapp.ports.output.UserOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserOutputPort {

	private final UserRepository userRepository;

	private final UserPersistenceMapper userPersistenceMapper;

	@Override
	public User saveUser(User user) {
		UserEntity userEntity = userPersistenceMapper.toUserEntity(user);
		userEntity = userRepository.save(userEntity);
		return userPersistenceMapper.toUser(userEntity);
	}

	@Override
	public Optional<User> findById(UUID id) {
		Optional<UserEntity> userEntity = userRepository.findById(id);

		return userEntity.map(userPersistenceMapper::toUser);
	}

	@Override
	public Optional<User> findByUsername(String email) {
		Optional<UserEntity> userEntity = userRepository.findByUsername(email);

		return userEntity.map(userPersistenceMapper::toUser);
	}

	@Override
	public User updateUser(UUID id, User user) {
		Optional<UserEntity> userEntity = userRepository.findById(id);

		if (!userEntity.isPresent())
			throw new EntityNotFoundException("The user entity has not been found");

		ObjectModifier.copyNonNullProperties(user, userEntity.get());

		return userPersistenceMapper.toUser(userRepository.save(userEntity.get()));
	}

	@Override
	public void deleteUserById(UUID id) {
		userRepository.deleteById(id);
	}

}
