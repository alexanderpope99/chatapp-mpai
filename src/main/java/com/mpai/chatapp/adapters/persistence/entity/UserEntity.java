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

	private String firstName;

	private String lastName;

	private String password;

	private String lastSeen;

	@Column(columnDefinition = "TEXT")
	private String avatar;

	@Enumerated(EnumType.STRING)
	private Role role;

	@ManyToMany(mappedBy = "users")
	private Set<ChatEntity> chats = new HashSet<>();

	@OneToMany(mappedBy = "admin")
	private List<GroupChatEntity> administeredGroupChats;

	@OneToMany(mappedBy = "sender")
	private List<MessageEntity> messages;

	@ManyToMany(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
	@JoinTable(name = "USERS_CONTACTS",
			joinColumns = {@JoinColumn(name = "USER_ID")},
			inverseJoinColumns = {@JoinColumn(name = "CONTACT_ID")})
	private Set<UserEntity> contacts = new HashSet<>();

	@ManyToMany(mappedBy = "contacts")
	private Set<UserEntity> usersThatHaveMeAsContact = new HashSet<>();
}
