package com.sist.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.vo.ChatMessage;

@Controller
public class ChatController {
	@GetMapping("/chat")
	public String chat() {
		return "chat";
	}
	
	@MessageMapping("chat.send")
	@SendTo("/topic/public") // 전체 : public / 귓속말 : private
	public ChatMessage sendMessage(ChatMessage message) {
		message.setTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		return message;
	}
}
