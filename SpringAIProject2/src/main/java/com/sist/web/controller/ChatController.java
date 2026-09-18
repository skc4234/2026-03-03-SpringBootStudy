package com.sist.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sist.web.service.ChatService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
public class ChatController {
	private final ChatService chatService;
	
	@GetMapping("/chat")
	public String chatform() {
		return "chat";
	}
	
	// sync => 결과값을 한번에 모아서 처리
	// stream 방식 => 타자 형식으로 한 글자씩 출력 (MediaType 지정)
	/*
	 *   주의사항
	 *    1. JSP는 stream 방식으로 사용못함
	 */
	@GetMapping(value="/chat/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@ResponseBody
	public Flux<String> chat_stream(
			@RequestParam("message") String message
			) {
		return chatService.streamChat(message);
	}
}
