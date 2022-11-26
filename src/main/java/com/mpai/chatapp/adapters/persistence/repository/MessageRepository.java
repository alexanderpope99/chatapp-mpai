package com.mpai.chatapp.adapters.persistence.repository;

import com.mpai.chatapp.adapters.persistence.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, UUID> {
}
