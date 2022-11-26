package com.mpai.chatapp.adapters.rest.data.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleChatCreateRequest {

	private LocalDateTime startedOn;

	private String initialUsername;

	@NotEmpty(message = "Username of second user is mandatory")
	private String username;

}
