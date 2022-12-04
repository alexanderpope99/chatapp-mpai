package com.mpai.chatapp.adapters.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type = "uuid-char")
	private UUID id;

	@ManyToOne
	@JoinColumn(name="chat_id", nullable=false)
	private ChatEntity chat;

	@ManyToOne
	@JoinColumn(name="sender_id", nullable=false)
	private UserEntity sender;

	private LocalDateTime date;

	private String content;

}
