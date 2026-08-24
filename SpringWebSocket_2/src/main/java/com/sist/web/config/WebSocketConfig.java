package com.sist.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	// 등록 => WebSocket 연결시 어떤 URI를 사용할건지 설정
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws-chat")
		        .setAllowedOriginPatterns("*")
		        .withSockJS();
	}

	// 기능에 따라서 URI 설정
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic","/queue"); // 귓속말 사용
		registry.setApplicationDestinationPrefixes("/app"); // 전체 채팅
		registry.setUserDestinationPrefix("/user"); // 1:1 채팅(귓속말)
	}
	
}
