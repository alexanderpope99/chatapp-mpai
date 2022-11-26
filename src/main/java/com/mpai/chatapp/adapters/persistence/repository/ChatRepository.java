package com.mpai.chatapp.adapters.persistence.repository;

import com.mpai.chatapp.adapters.persistence.entity.ChatEntity;

import javax.transaction.Transactional;

@Transactional
public interface ChatRepository extends ChatBaseRepository<ChatEntity> {
}
