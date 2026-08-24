package com.sist.web.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.vo.ChatMessage;

import lombok.RequiredArgsConstructor;

/*
 *  // 실시간 알림
 *   STOMP(Simple Text Oriented Messaging Protocol)
 *    => 회원, 예약, 댓글 등
 *    - MessageBroker <=> Client 간에 통신을 도와주는 역할
 *    
 *   SockJS
 *    - Server <=> Client 통신
 *    
 *   Stomp 규칙
 *    - 내부 프로토콜(약속)
 *      => 서버=클라이언트 만 알수 있게 만듦(ex. LOGIN/100, LOGOUT/200)
 *      => 임의로 정함
 *      
 *   Stomp 동작
 *    - connect
 *    	- WebSocket 연결(new SockJS('ws-chat')) => Stomp로 요청(Stomp.over(socket))
 *                                  ㄴ EndPoint
 *    - send
 *      - 클라이언트가 특정 목적지(/topic)로 메시지를 발행(public)
 *    - subscribe
 *    	- 클라이언트에서 발행된 데이터 읽기
 *    - disconnect
 *    	- 연결 종료
 *    
 *   - Pinia에서 Stomp 이용
 *    - Store: 변수, 처리하는 기능 존재
 *    
 *   - SockJS : 전화선(서버 연결)
 *   - Stomp  : 통신(송수신) 담당 => URI로 송수신 제어
 *     => (/app) /room/1, /room/2, .. ==> 채팅방 여러개
 */

@Controller
@RequiredArgsConstructor
public class ChatController {
	// 1:1 채팅 시 사용
	private final SimpMessagingTemplate messagingTemplate;
	
	// 전체 채팅
	@MessageMapping("/chat.send")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(ChatMessage message) {
		return message;
	}
	
	// 알림 / 실시간 상담
	@MessageMapping("/chat.private")
	public void privateMessage(ChatMessage message) {
		System.out.println("My: "+message.getSender());
		System.out.println("You: "+message.getReceiver());
		System.out.println("Message: "+message.getMessage());
		// 1:1 채팅, 알림 등에 사용
		messagingTemplate.convertAndSend(
			"/queue/private/"+message.getReceiver(),
			message
		);  
	}
	
	@GetMapping("/chat")
	public String chat_page() {
		return "chat";
	}
}
