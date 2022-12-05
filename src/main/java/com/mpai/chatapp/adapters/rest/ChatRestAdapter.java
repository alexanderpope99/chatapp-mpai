package com.mpai.chatapp.adapters.rest;

import com.mpai.chatapp.adapters.config.jwt.JwtUtils;
import com.mpai.chatapp.adapters.rest.data.request.GroupChatCreateRequest;
import com.mpai.chatapp.adapters.rest.data.request.GroupChatUsersRequest;
import com.mpai.chatapp.adapters.rest.data.request.MessageRequest;
import com.mpai.chatapp.adapters.rest.data.request.SimpleChatCreateRequest;
import com.mpai.chatapp.adapters.rest.data.response.GroupChatCreateResponse;
import com.mpai.chatapp.adapters.rest.data.response.UserChatResponse;
import com.mpai.chatapp.adapters.rest.data.response.UserChatResponseBuilderVisitor;
import com.mpai.chatapp.adapters.rest.data.response.UserChatResponseBuilderVisitorImpl;
import com.mpai.chatapp.adapters.rest.mapper.GroupChatRestMapper;
import com.mpai.chatapp.adapters.rest.mapper.MessageRestMapper;
import com.mpai.chatapp.adapters.rest.mapper.SimpleChatRestMapper;
import com.mpai.chatapp.domain.model.*;
import com.mpai.chatapp.ports.input.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(value = "/chats")
@RequiredArgsConstructor
public class ChatRestAdapter {

	private final CreateChatUseCase createChatUseCase;

	private final AddUsersToGroupChatUseCase addUsersToGroupChatUseCase;

	private final RemoveUsersFromGroupChatUseCase removeUsersFromGroupChatUseCase;

	private final SendMessageUseCase sendMessageUseCase;

	private final GetChatForUserUseCase getChatForUserUseCase;

	private final SimpleChatRestMapper simpleChatRestMapper;

	private final GroupChatRestMapper groupChatRestMapper;

	private final MessageRestMapper messageRestMapper;

	private final JwtUtils jwtUtils;

	private final UserChatResponseBuilderVisitor userChatResponseBuilderVisitor;

	private final SSEEventManager sseEventManager;

	@GetMapping
	public ResponseEntity<Set<UserChatResponse>> getChats(@RequestHeader(name = "Authorization") String token) {

		String username = jwtUtils.getUserNameFromJwtToken(token.replace("Bearer ", ""));

		Set<Chat> chats = getChatForUserUseCase.getChatsForUser(username);

		Set<UserChatResponse> chatsResponse = new HashSet<>();

		UserChatResponseBuilderVisitor visitor = new UserChatResponseBuilderVisitorImpl();

		for (Chat chat : chats)
			chatsResponse.add(chat.accept(visitor, username));

		return new ResponseEntity<>(chatsResponse, HttpStatus.OK);
	}

	@PostMapping(value = "/simple")
	public ResponseEntity<UserChatResponse> createSimpleChat(@RequestHeader(name = "Authorization") String token,
															 @RequestBody @Valid SimpleChatCreateRequest simpleChatCreateRequest) {

		String username = jwtUtils.getUserNameFromJwtToken(token.replace("Bearer ", ""));
		simpleChatCreateRequest.setInitialUsername(username);

		SimpleChat simpleChat = simpleChatRestMapper.toSimpleChat(simpleChatCreateRequest);

		simpleChat = createChatUseCase.createSimpleChat(simpleChat);

		return new ResponseEntity<>(simpleChat.accept(userChatResponseBuilderVisitor, username), HttpStatus.OK);
	}

	@PostMapping(value = "/group")
	public ResponseEntity<UserChatResponse> createGroupChat(@RequestHeader(name = "Authorization") String token,
															@RequestBody @Valid GroupChatCreateRequest groupChatCreateRequest) {

		String username = jwtUtils.getUserNameFromJwtToken(token.replace("Bearer ", ""));
		groupChatCreateRequest.setAdmin(username);

		GroupChat groupChat = groupChatRestMapper.toGroupChat(groupChatCreateRequest);

		groupChat = createChatUseCase.createGroupChat(groupChat);

		return new ResponseEntity<>(groupChat.accept(userChatResponseBuilderVisitor, username), HttpStatus.OK);
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
