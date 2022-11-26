package com.mpai.chatapp.adapters.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "chats")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "chat_type",
		discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type = "uuid-char")
	private UUID id;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "users_chats",
			joinColumns = @JoinColumn(name = "chat_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private Set<UserEntity> users = new HashSet<>();

	@OneToMany(mappedBy="chat")
	private List<MessageEntity> messages;

}
