package com.mpai.chatapp.adapters.rest;

import com.mpai.chatapp.adapters.config.jwt.JwtUtils;
import com.mpai.chatapp.adapters.rest.data.request.GroupChatUsersRequest;
import com.mpai.chatapp.adapters.rest.data.request.GroupChatCreateRequest;
import com.mpai.chatapp.adapters.rest.data.request.MessageRequest;
import com.mpai.chatapp.adapters.rest.data.request.SimpleChatCreateRequest;
import com.mpai.chatapp.adapters.rest.data.response.*;
import com.mpai.chatapp.adapters.rest.mapper.ChatRestMapper;
import com.mpai.chatapp.adapters.rest.mapper.GroupChatRestMapper;
import com.mpai.chatapp.adapters.rest.mapper.MessageRestMapper;
import com.mpai.chatapp.adapters.rest.mapper.SimpleChatRestMapper;
import com.mpai.chatapp.domain.model.Chat;
import com.mpai.chatapp.domain.model.GroupChat;
import com.mpai.chatapp.domain.model.Message;
import com.mpai.chatapp.domain.model.SimpleChat;
import com.mpai.chatapp.ports.input.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

	private final ChatRestMapper chatRestMapper;

	private final SimpleChatRestMapper simpleChatRestMapper;

	private final GroupChatRestMapper groupChatRestMapper;

	private final MessageRestMapper messageRestMapper;

	private final JwtUtils jwtUtils;

	@GetMapping
	public ResponseEntity<Set<UserChatResponse>> getChats(@RequestHeader(name = "Authorization") String token) {

		String username = jwtUtils.getUserNameFromJwtToken(token.replace("Bearer ", ""));

		Set<Chat> chats = getChatForUserUseCase.getChatsForUser(username);

		Set<UserChatResponse> chatsResponse = new HashSet<>();

		UserChatResponseBuilderVisitorImpl visitor = new UserChatResponseBuilderVisitorImpl();

		for(Chat chat : chats)
			chatsResponse.add(chat.accept(visitor, username));

		return new ResponseEntity<>(chatsResponse, HttpStatus.OK);
	}

	@PostMapping(value = "/simple")
	public ResponseEntity<SimpleChatCreateResponse> createSimpleChat(@RequestHeader(name = "Authorization") String token,
																	 @RequestBody @Valid SimpleChatCreateRequest simpleChatCreateRequest) {

		simpleChatCreateRequest.setInitialUsername(jwtUtils.getUserNameFromJwtToken(token.replace("Bearer ", "")));

		SimpleChat simpleChat = simpleChatRestMapper.toSimpleChat(simpleChatCreateRequest);

		simpleChat = createChatUseCase.createSimpleChat(simpleChat);

		return new ResponseEntity<>(simpleChatRestMapper.toSimpleChatCreateResponse(simpleChat), HttpStatus.OK);
	}

	@PostMapping(value = "/group")
	public ResponseEntity<GroupChatCreateResponse> createGroupChat(@RequestHeader(name = "Authorization") String token,
																   @RequestBody @Valid GroupChatCreateRequest groupChatCreateRequest) {

		groupChatCreateRequest.setAdmin(jwtUtils.getUserNameFromJwtToken(token.replace("Bearer ", "")));

		GroupChat groupChat = groupChatRestMapper.toGroupChat(groupChatCreateRequest);

		groupChat = createChatUseCase.createGroupChat(groupChat);

		return new ResponseEntity<>(groupChatRestMapper.toGroupChatCreateResponse(groupChat), HttpStatus.OK);
	}

	@PostMapping(value = "/{id}/message")
	public ResponseEntity<ChatResponse> sendMessageToChat(@RequestHeader(name = "Authorization") String token,
														  @PathVariable UUID id,
														  @RequestBody @Valid MessageRequest messageRequest) {

		messageRequest.setUsername(jwtUtils.getUserNameFromJwtToken(token.replace("Bearer ", "")));
		messageRequest.setDateTime(LocalDateTime.now());

		Message message = messageRestMapper.toMessage(messageRequest);

		Chat chat = sendMessageUseCase.sendMessage(id, message);

		return new ResponseEntity<>(chatRestMapper.toChatResponse(chat), HttpStatus.OK);
	}

	@PutMapping (value = "/group/{id}/add-users")
	public ResponseEntity<GroupChatCreateResponse> addUsersToGroupChat(@PathVariable UUID id,
																	  @RequestBody @Valid GroupChatUsersRequest groupChatUsersRequest) {

		GroupChat groupChat = addUsersToGroupChatUseCase.addUsersToGroupChat(id,
				groupChatRestMapper.toGroupChatUsers(groupChatUsersRequest).getUsers());

		return new ResponseEntity<>(groupChatRestMapper.toGroupChatCreateResponse(groupChat), HttpStatus.OK);
	}

	@PutMapping (value = "/group/{id}/remove-users")
	public ResponseEntity<GroupChatCreateResponse> removeUsersFromGroupChat(@PathVariable UUID id,
																	  @RequestBody @Valid GroupChatUsersRequest groupChatUsersRequest) {

		GroupChat groupChat = removeUsersFromGroupChatUseCase.removeUsersFromGroupChat(id,
				groupChatRestMapper.toGroupChatUsers(groupChatUsersRequest).getUsers());

		return new ResponseEntity<>(groupChatRestMapper.toGroupChatCreateResponse(groupChat), HttpStatus.OK);
	}

}
