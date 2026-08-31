package com.sist.web.config;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTTokenProvider {
	// 실무에서는 자동 설정 => 키 설정 부분
	// application => jwt: secret: ${JWT_SECRET}
	private final String SECRET="my-secret-key-my-security-key-my-secret-key";
	public String createToken(String username,String role) {
		/*
		 *   Payload {
		 *   	sub: "admin",
		 *   	role: "ROLE_ADMIN"
		 *   } ==> token 형식(bytes)
		 */
		return Jwts.builder()
				   .setSubject(username) // 사용자 ID 저장 {sub: admin}
				   .claim("role", role) // 권한 추가 => ROLE_ADMIN
				   .setIssuedAt(new Date()) // JWT 발급 시간 저장
				   // 만료시간 등록
				   // 3600000 => 1시간(60분) => 60*60*1000
				   .setExpiration(new Date(System.currentTimeMillis()+3600000))
				   // SECRET key를 이용해서 JWT에 서명
				   .signWith(Keys.hmacShaKeyFor(SECRET.getBytes())) 
				   .compact();
	}
	
	public String getUsername(String token) {
		
		return Jwts.parserBuilder()
				   .setSigningKey(SECRET.getBytes())
				   .build()
				   .parseClaimsJws(token)
				   .getBody()
				   .getSubject();
	}
	public boolean validate(String token) {
		try {
			Jwts.parserBuilder()
			    .setSigningKey(SECRET.getBytes())
			    .build()
			    .parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
