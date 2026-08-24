package com.sist.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
// stomp 기반의 WebSocket 기능 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	// 클라이언트가 WebSocket 서버에 처음 접속할 주소 등록
	// /ws-chat
	/*
	 *   registry.addEndPoint("/ws-chat")
	 *   => new SockJS("/ws-chat")
	 *   
	 *   // 실행 순서
	 *   new SockJS("/ws-chat")
	 *         |
	 *    SpringBoot WebSocket
	 *         |
	 *  setAllowedOriginPatterns("*") : 접속 허용
	 *         |
	 *     withSockJS()
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// TODO Auto-generated method stub
		//WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
		// 1. 클라이언트가 서버에 접속할 URI 주소
		// origin => 모든 클라이언트가 접속이 가능하게
		// ==> 실제로는 지정된 도메인만 접근 가능하게 서정
		registry.addEndpoint("/ws-chat")
		        .setAllowedOriginPatterns("*")
		        .withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// TODO Auto-generated method stub
		//WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
		// 채널 => 클라이언트가 서버에서 보낸 데이터를 읽어서 출력
		registry.enableSimpleBroker("/topic");
		// 메시지를 보내는 경우(보내는 곳)
		registry.setApplicationDestinationPrefixes("/app");
	}
	
}
