package com.mpai.chatapp.adapters.persistence.repository;

import com.mpai.chatapp.adapters.persistence.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface ChatBaseRepository<T extends ChatEntity> extends JpaRepository<T, UUID> {

}
