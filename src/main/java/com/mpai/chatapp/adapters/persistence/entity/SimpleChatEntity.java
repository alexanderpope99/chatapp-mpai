package com.mpai.chatapp.adapters.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("simple")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleChatEntity extends ChatEntity {

	private String nickname;

}
