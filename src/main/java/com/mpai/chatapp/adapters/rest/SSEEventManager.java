package com.mpai.chatapp.adapters.rest;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

public interface SSEEventManager {

	SseEmitter subscribe(String username);

	void unsubscribe(String username);

	void notify(String eventName, Object data) throws IOException;

}
