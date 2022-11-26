package com.mpai.chatapp.adapters.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("simple")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleChatEntity extends ChatEntity {

	private LocalDateTime startedOn;

}
