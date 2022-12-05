package com.mpai.chatapp.adapters.rest;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SSEEventManagerImpl implements SSEEventManager {
	private Map<String, SseEmitter> sseEmitters = new ConcurrentHashMap<>();

	@Override
	public SseEmitter subscribe(String username) {
		SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
		sseEmitters.put(username, sseEmitter);
		System.out.println("User" + username + " has subscribed for SSE");
		return sseEmitter;
	}

	@Override
	public void unsubscribe(String username) {
		sseEmitters.remove(username);
		System.out.println("User" + username + " has unsubscribed from SSE");
	}

	@Override
	public void notify(String eventName, Object data) throws IOException {
		for(SseEmitter sseEmitter : sseEmitters.values())
			sseEmitter.send(SseEmitter.event().name(eventName).data(data));

	}
}
