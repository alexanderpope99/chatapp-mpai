package com.mpai.chatapp.adapters.persistence.repository;

import com.mpai.chatapp.adapters.persistence.entity.GroupChatEntity;

import javax.transaction.Transactional;

@Transactional
public interface GroupChatRepository extends ChatBaseRepository<GroupChatEntity> {
}
