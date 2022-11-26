package com.mpai.chatapp.adapters.persistence.entity;

import com.mpai.chatapp.domain.model.Role;
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
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type = "uuid-char")
	private UUID id;

	@Column(unique = true)
	private String username;

	private String nickname;

	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	@ManyToMany(mappedBy = "users")
	private Set<ChatEntity> chats = new HashSet<>();

	@OneToMany(mappedBy = "admin")
	private List<GroupChatEntity> administeredGroupChats;

	@OneToMany(mappedBy="sender")
	private List<MessageEntity> messages;
}
