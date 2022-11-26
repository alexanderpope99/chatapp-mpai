package com.mpai.chatapp.adapters.persistence.repository;

import com.mpai.chatapp.adapters.persistence.entity.SimpleChatEntity;

import javax.transaction.Transactional;

@Transactional
public interface SimpleChatRepository extends ChatBaseRepository<SimpleChatEntity> {
}
