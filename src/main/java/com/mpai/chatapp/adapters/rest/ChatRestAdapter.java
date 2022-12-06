package com.mpai.chatapp.adapters.rest;

import com.mpai.chatapp.adapters.CreateChatFacade;
import com.mpai.chatapp.adapters.config.jwt.JwtUtils;
import com.mpai.chatapp.adapters.rest.data.request.ChatCreateRequest;
import com.mpai.chatapp.adapters.rest.data.request.GroupChatUsersRequest;
import com.mpai.chatapp.adapters.rest.data.request.MessageRequest;
import com.mpai.chatapp.adapters.rest.data.response.GroupChatCreateResponse;
import com.mpai.chatapp.adapters.rest.data.response.UserChatResponse;
import com.mpai.chatapp.adapters.rest.data.response.UserChatResponseBuilderVisitor;
import com.mpai.chatapp.adapters.rest.data.response.UserChatResponseBuilderVisitorImpl;
import com.mpai.chatapp.adapters.rest.mapper.GroupChatRestMapper;
import com.mpai.chatapp.adapters.rest.mapper.MessageRestMapper;
import com.mpai.chatapp.domain.model.Chat;
import com.mpai.chatapp.domain.model.GroupChat;
import com.mpai.chatapp.domain.model.Message;
import com.mpai.chatapp.ports.input.AddUsersToGroupChatUseCase;
import com.mpai.chatapp.ports.input.GetChatForUserUseCase;
import com.mpai.chatapp.ports.input.RemoveUsersFromGroupChatUseCase;
import com.mpai.chatapp.ports.input.SendMessageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping(value = "/chats")
@RequiredArgsConstructor
public class ChatRestAdapter {

	private final AddUsersToGroupChatUseCase addUsersToGroupChatUseCase;

	private final RemoveUsersFromGroupChatUseCase removeUsersFromGroupChatUseCase;

	private final SendMessageUseCase sendMessageUseCase;

	private final GetChatForUserUseCase getChatForUserUseCase;

	private final CreateChatFacade createChatFacade;

	private final GroupChatRestMapper groupChatRestMapper;

	private final MessageRestMapper messageRestMapper;

	private final JwtUtils jwtUtils;

	private final UserChatResponseBuilderVisitor userChatResponseBuilderVisitor;

	private final SSEEventManager sseEventManager;

	@GetMapping
	public ResponseEntity<List<UserChatResponse>> getChats(@RequestHeader(name = "Authorization") String token) {

		String username = jwtUtils.getUserNameFromJwtToken(token.replace("Bearer ", ""));

		List<Chat> chats = getChatForUserUseCase.getChatsForUser(username);

		List<UserChatResponse> chatsResponse = new ArrayList<>();

		UserChatResponseBuilderVisitor visitor = new UserChatResponseBuilderVisitorImpl();

		for (Chat chat : chats)
			chatsResponse.add(chat.accept(visitor, username));

		return new ResponseEntity<>(chatsResponse, HttpStatus.OK);
	}

	@PostMapping(value = "/{type}")
	public ResponseEntity<UserChatResponse> createChat(@RequestHeader(name = "Authorization") String token,
													   @PathVariable String type,
													   @RequestBody @Valid ChatCreateRequest chatCreateRequest) {

		String username = jwtUtils.getUserNameFromJwtToken(token.replace("Bearer ", ""));
		chatCreateRequest.setAdmin(username);

		Chat chat = createChatFacade.createChat(type, chatCreateRequest);

		return new ResponseEntity<>(chat.accept(userChatResponseBuilderVisitor, username), HttpStatus.OK);
	}

	@PostMapping(value = "/{id}/message")
	public ResponseEntity<UserChatResponse> sendMessageToChat(@RequestHeader(name = "Authorization") String token,
															  @PathVariable UUID id,
															  @RequestBody @Valid MessageRequest messageRequest)
			throws IOException {

		String username = jwtUtils.getUserNameFromJwtToken(token.replace("Bearer ", ""));
		messageRequest.setUsername(username);
		messageRequest.setDate(LocalDateTime.now());

		Message message = messageRestMapper.toMessage(messageRequest);

		Chat chat = sendMessageUseCase.sendMessage(id, message);

		UserChatResponse userChatResponse = chat.accept(userChatResponseBuilderVisitor, username);

		sseEventManager.notify("ADDED_MESSAGE", userChatResponse.getMessages().get(userChatResponse.getMessages().size() - 1));

		return new ResponseEntity<>(userChatResponse, HttpStatus.OK);
	}

	@GetMapping("/subscribe")
	public SseEmitter eventEmitter(@RequestHeader(name = "Authorization") String token) throws IOException {
		String username = jwtUtils.getUserNameFromJwtToken(token.replace("Bearer ", ""));
		return sseEventManager.subscribe(username);
	}

	@PutMapping(value = "/group/{id}/add-users")
	public ResponseEntity<GroupChatCreateResponse> addUsersToGroupChat(@PathVariable UUID id,
																	   @RequestBody @Valid GroupChatUsersRequest groupChatUsersRequest) {

		GroupChat groupChat = addUsersToGroupChatUseCase.addUsersToGroupChat(id,
				groupChatRestMapper.toGroupChatUsers(groupChatUsersRequest).getUsers());

		return new ResponseEntity<>(groupChatRestMapper.toGroupChatCreateResponse(groupChat), HttpStatus.OK);
	}

	@PutMapping(value = "/group/{id}/remove-users")
	public ResponseEntity<GroupChatCreateResponse> removeUsersFromGroupChat(@PathVariable UUID id,
																			@RequestBody @Valid GroupChatUsersRequest groupChatUsersRequest) {

		GroupChat groupChat = removeUsersFromGroupChatUseCase.removeUsersFromGroupChat(id,
				groupChatRestMapper.toGroupChatUsers(groupChatUsersRequest).getUsers());

		return new ResponseEntity<>(groupChatRestMapper.toGroupChatCreateResponse(groupChat), HttpStatus.OK);
	}

}
