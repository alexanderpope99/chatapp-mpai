package com.mpai.chatapp.adapters.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupChatEntity extends ChatEntity {

	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_id")
	private UserEntity admin;

}
