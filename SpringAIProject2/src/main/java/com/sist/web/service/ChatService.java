package com.sist.web.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Service
public class ChatService {
	private final ChatClient chatClient;
	
	public ChatService(ChatClient.Builder chatClientBuilder) {
		this.chatClient=chatClientBuilder.build();
	}
	
	// 데이터 여러개를 순차적으로 전송하는 통로 => Flux
	public Flux<String> streamChat(String userMsg) {
		String systemPrompt="한글로 답변하세요";
		Flux<String> f=chatClient.prompt()
				           .system(systemPrompt)
				           .user(userMsg)
				           .stream()
				           .content()
				           .doOnNext(System.out::println);
		System.out.println(f);
		return f;
	}
}
