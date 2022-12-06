package com.mpai.chatapp.domain.model;

import com.mpai.chatapp.adapters.rest.data.response.UserChatResponse;
import com.mpai.chatapp.adapters.rest.data.response.UserChatResponseBuilderVisitor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public abstract class Chat implements Comparable<Chat> {

	protected UUID id;

	protected List<Message> chatHistory = new ArrayList<>();

	public abstract void sendMessage(Message message);

	public abstract Set<User> getUsers();

	public List<Message> getChatHistory() {
		return chatHistory;
	}

	public abstract UserChatResponse accept(UserChatResponseBuilderVisitor visitor, String username);

	@Override
	public int compareTo(Chat chat) {
		LocalDateTime thisLastMessageDate = this.chatHistory.get(this.chatHistory.size() - 1).getDate();
		LocalDateTime chatLastMessageDate = chat.chatHistory.get(chat.chatHistory.size() - 1).getDate();

		if (thisLastMessageDate.isAfter(chatLastMessageDate))
			return 1;
		else if (thisLastMessageDate.isEqual(chatLastMessageDate))
			return 0;
		return -1;
	}


}
